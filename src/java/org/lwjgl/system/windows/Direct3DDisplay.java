package org.lwjgl.system.windows;

import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;

import static org.lwjgl.Pointer.POINTER_SIZE;
import static org.lwjgl.system.MemoryUtil.*;
import static org.lwjgl.system.windows.WinBase.*;
import static org.lwjgl.system.windows.WinUser.*;
import static org.lwjgl.system.windows.WindowsLibrary.*;
import static org.lwjgl.system.windows.WindowsPlatform.*;

public class Direct3DDisplay {

	/** Handle to the default window proc. */
	public static final long DEF_WINDOW_PROC = GetProcAddress(GetModuleHandle(memEncodeUTF16("User32.dll")),
			memEncodeASCII("DefWindowProcW"));

	static {
		if (DEF_WINDOW_PROC == 0)
			throw new RuntimeException("Failed to retrieve the default window proc.");
	}

	/** Each window created increments this integer. */
	private static final AtomicInteger WINDOW_ID = new AtomicInteger(0);

	private final String className;

	private final int id;

	public final WindowProc defaultWndProc;

	private final short classAtom;

	private final long hwnd;
	private final long hdc;

	private int x;
	private int y;
	private int width;
	private int height;
	private int clientWidth;
	private int clientHeight;

	private boolean closeRequested;

	public Direct3DDisplay(String title, int clientWidth, int clientHeight, int style) {
		this.id = WINDOW_ID.incrementAndGet();

		this.defaultWndProc = new WindowProcImpl();
		this.clientWidth = clientWidth;
		this.clientHeight = clientHeight;

		className = "DXAPPWNDCLASS" + id;

		ByteBuffer in = WNDCLASSEX.malloc(WNDCLASSEX.SIZEOF, CS_HREDRAW | CS_VREDRAW, defaultWndProc.getPointer(), 0,
				POINTER_SIZE + 4, // WNDPROC reference + reserved int
				HINSTANCE, nLoadIcon(0, IDI_APPLICATION), nLoadCursor(0, IDC_ARROW), NULL, null, className, NULL);

		this.classAtom = RegisterClassEx(in);
		windowsCheckHandle(classAtom, "Failed to register window class");

		/* Compute window size from client area size */
		ByteBuffer rectBuffer = RECT.malloc();
		RECT rc = new RECT(rectBuffer);
		rc.setTop(0);
		rc.setLeft(0);
		rc.setRight(clientWidth);
		rc.setBottom(clientHeight);
		AdjustWindowRectEx(rectBuffer, style, 0, 0);
		this.width = rc.getRight() - rc.getLeft();
		this.height = rc.getBottom() - rc.getTop();

		/* Create window */
		hwnd = CreateWindowEx(WS_EX_APPWINDOW, className, title,  
                style, CW_USEDEFAULT, CW_USEDEFAULT, this.width, this.height,
				NULL, NULL, HINSTANCE, defaultWndProc.getPointer());
		windowsCheckHandle(hwnd, "Failed to create window");

		hdc = GetDC(hwnd);
		windowsCheckHandle(hdc, "Failed to get device context handle.");
	}

	public long getHwnd() {
		return hwnd;
	}

	public long getHdc() {
		return hdc;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getClientWidth() {
		return clientWidth;
	}

	public int getClientHeight() {
		return clientHeight;
	}

	public void setLocation(int x, int y) {
		windowsCheckResult(SetWindowPos(hwnd, 0, x, y, width, height, SWP_NOOWNERZORDER | SWP_NOSIZE), "SetWindowPos");
	}

	public void setSize(int width, int height) {
		windowsCheckResult(SetWindowPos(hwnd, 0, x, y, width, height, SWP_NOOWNERZORDER | SWP_NOMOVE), "SetWindowPos");
	}

	public void setVisible(boolean visible) {
		ShowWindow(hwnd, visible ? SW_SHOW : SW_HIDE);
	}

	public void destroy() {
		windowsCheckResult(DestroyWindow(hwnd), "DestroyWindow");
		windowsCheckResult(UnregisterClass(className, HINSTANCE), "UnregisterClass");

		defaultWndProc.release();
	}

	public boolean isCloseRequested() {
		return closeRequested;
	}

	private class WindowProcImpl extends WindowProc {
		@Override
		public long invoke(long hWnd, int msg, long wParam, long lParam) {
			// System.out.println("In WINDOW PROC: " + Integer.toHexString(msg)
			// + " - " +wParam + " - " +lParam);

			switch (msg) {
			case WM_QUIT:
				closeRequested = true;
				return 0;
			case WM_SYSCOMMAND:
				switch ((int) (wParam & 0xfff0)) {
				case SC_KEYMENU:
				case SC_MOUSEMENU:
				case SC_SCREENSAVE:
				case SC_MONITORPOWER:
					return 0;
				case SC_CLOSE:
					closeRequested = true;
					return 0;
				default:
					break;
				}
				break;
			case WM_WINDOWPOSCHANGED:
				// memCopy(lParam, memAddress(windowPos), WINDOWPOS.SIZEOF);
				break;
			}

			return nDefWindowProc(hWnd, msg, wParam, lParam);
		}
	}

}