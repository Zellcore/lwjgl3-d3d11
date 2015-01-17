package org.lwjgl.d3d11;

import static org.lwjgl.system.windows.WinUser.*;
import static org.lwjgl.d3d11.DXGI_SWAP_CHAIN_FLAG.*;
import static org.lwjgl.d3d11.impl.D3D11.*;

import java.nio.ByteBuffer;

import org.lwjgl.system.windows.Direct3DDisplay;
import org.lwjgl.system.windows.MSG;

public class SimpleTest {

    public static void main(String[] args) throws Throwable {
        Direct3DDisplay display = new Direct3DDisplay("Direct3D 11 Tutorial 1: Direct3D 11 Basics", 800, 600);
        display.setVisible(true);

        ByteBuffer msgBuf = MSG.malloc();
        MSG msg = new MSG(msgBuf);
        while (!display.isCloseRequested()) {
            int status;
            while ((status = PeekMessage(msgBuf, 0, 0, 0, PM_REMOVE)) != 0) {
                System.err.println(msg.getMessage());
                if (status == -1) // error
                    return;

                TranslateMessage(msgBuf);
                DispatchMessage(msgBuf);
            }
            Thread.yield();
        }
        display.destroy();

        System.exit(0);

        D3D_FEATURE_LEVEL[] featureLevels = {
                D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_11_0,
                D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_10_1,
                D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_10_0,
                D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_9_3 };

        DXGI_SWAP_CHAIN_DESC swapChainDesc = new DXGI_SWAP_CHAIN_DESC();
        swapChainDesc.BufferCount = 1;
        swapChainDesc.BufferDesc.Width = 800;
        swapChainDesc.BufferDesc.Height = 600;
        swapChainDesc.BufferDesc.Format = DXGI_FORMAT.DXGI_FORMAT_R8G8B8A8_UNORM;
        swapChainDesc.BufferDesc.RefreshRate.Numerator = 60;
        swapChainDesc.BufferDesc.RefreshRate.Denominator = 1;
        swapChainDesc.BufferUsage = DXGI.DXGI_USAGE_RENDER_TARGET_OUTPUT;
        swapChainDesc.OutputWindow = display.getHwnd();
        swapChainDesc.SwapEffect = DXGI_SWAP_EFFECT.DXGI_SWAP_EFFECT_DISCARD;
        swapChainDesc.Windowed = true;
        swapChainDesc.SampleDesc.Count = 1;
        swapChainDesc.SampleDesc.Quality = 0;
        swapChainDesc.Flags = DXGI_SWAP_CHAIN_FLAG_ALLOW_MODE_SWITCH;

        Out<IDXGISwapChain> swapChainOut = new Out<>();
        Out<ID3D11Device> deviceOut = new Out<>();
        Out<D3D_FEATURE_LEVEL> featureLevelOut = new Out<>();
        Out<ID3D11DeviceContext> immediateContextOut = new Out<>();

        long hresult = D3D11CreateDeviceAndSwapChain(null,
                D3D_DRIVER_TYPE.D3D_DRIVER_TYPE_HARDWARE, 0L, 0, featureLevels,
                D3D11_SDK_VERSION, swapChainDesc, swapChainOut, deviceOut,
                featureLevelOut, immediateContextOut);

    }

}
