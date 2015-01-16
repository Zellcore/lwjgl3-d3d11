package org.lwjgl.d3d11;

import static org.lwjgl.system.windows.WinUser.*;
import static org.lwjgl.system.windows.WindowsDisplay.*;
import static org.lwjgl.system.windows.WindowsLibrary.*;
import static org.lwjgl.d3d11.DXGISwapChainFlag.*;

import static org.lwjgl.d3d11.impl.D3D11.*;
import org.lwjgl.system.windows.WNDCLASSEX;

public class SimpleTest {

    public static void main(String[] args) throws Throwable {
        String menuName = null;

        WNDCLASSEX in = new WNDCLASSEX();

        in.setSize(WNDCLASSEX.SIZEOF);
        in.setStyle(CS_HREDRAW | CS_VREDRAW);
        in.setWndProc(DEF_WINDOW_PROC);
        in.setClsExtra(0);
        in.setWndExtra(0);
        in.setInstance(HINSTANCE);
        in.setIcon(nLoadIcon(0, IDI_APPLICATION));
        in.setCursor(nLoadCursor(0, IDC_ARROW));
        in.setBackground(0);
        in.setMenuName(menuName);
        in.setClassName("DXAPPWNDCLASS");
        in.setIconSm(0);

        short classAtom = RegisterClassEx(in.buffer());

        WNDCLASSEX out = new WNDCLASSEX();
        out.setSize(WNDCLASSEX.SIZEOF);

        int success = GetClassInfoEx(HINSTANCE, "DXAPPWNDCLASS", out.buffer());

        long hwnd = CreateWindowEx(WS_EX_APPWINDOW, "DXAPPWNDCLASS", "LWJGL Test",
        // WS_OVERLAPPED | WS_BORDER | WS_CAPTION | WS_MINIMIZEBOX | WS_SYSMENU
        // | WS_CLIPCHILDREN | WS_CLIPSIBLINGS,
                WS_OVERLAPPEDWINDOW, 0, 0, 640, 480, 0, 0, HINSTANCE, 0);
        ShowWindow(hwnd, 0x5);
        Thread.sleep(100000L);
        DestroyWindow(hwnd);

        System.exit(0);

        D3DFeatureLevel[] featureLevels = {
                D3DFeatureLevel.D3D_FEATURE_LEVEL_11_0,
                D3DFeatureLevel.D3D_FEATURE_LEVEL_10_1,
                D3DFeatureLevel.D3D_FEATURE_LEVEL_10_0,
                D3DFeatureLevel.D3D_FEATURE_LEVEL_9_3 };

        DXGISwapChainDesc swapChainDesc = new DXGISwapChainDesc();
        swapChainDesc.BufferCount = 1;
        swapChainDesc.BufferDesc.Width = 800;
        swapChainDesc.BufferDesc.Height = 600;
        swapChainDesc.BufferDesc.Format = DXGIFormat.DXGI_FORMAT_R8G8B8A8_UNORM;
        swapChainDesc.BufferDesc.RefreshRate.Numerator = 60;
        swapChainDesc.BufferDesc.RefreshRate.Denominator = 1;
        swapChainDesc.BufferUsage = DXGI.DXGI_USAGE_RENDER_TARGET_OUTPUT;
        swapChainDesc.OutputWindow = hwnd;
        swapChainDesc.SwapEffect = DXGISwapEffect.DXGI_SWAP_EFFECT_DISCARD;
        swapChainDesc.Windowed = true;
        swapChainDesc.SampleDesc.Count = 1;
        swapChainDesc.SampleDesc.Quality = 0;
        swapChainDesc.Flags = DXGI_SWAP_CHAIN_FLAG_ALLOW_MODE_SWITCH;

        Out<IDXGISwapChain> swapChainOut = new Out<>();
        Out<ID3D11Device> deviceOut = new Out<>();
        Out<D3DFeatureLevel> featureLevelOut = new Out<>();
        Out<ID3D11DeviceContext> immediateContextOut = new Out<>();

        long hresult = D3D11CreateDeviceAndSwapChain(null,
                D3DDriverType.D3D_DRIVER_TYPE_HARDWARE, 0L, 0, featureLevels,
                D3D11_SDK_VERSION, swapChainDesc, swapChainOut,
                deviceOut, featureLevelOut, immediateContextOut);

    }

}
