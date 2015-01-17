package org.lwjgl.d3d11;

import static org.lwjgl.d3d11.D3D_DRIVER_TYPE.*;
import static org.lwjgl.d3d11.D3D_FEATURE_LEVEL.*;
import static org.lwjgl.d3d11.DXGI_SWAP_CHAIN_FLAG.DXGI_SWAP_CHAIN_FLAG_ALLOW_MODE_SWITCH;
import static org.lwjgl.d3d11.impl.D3D11.*;
import static org.lwjgl.d3d11.D3D11_CREATE_DEVICE_FLAG.*;
import static org.lwjgl.system.windows.WinUser.*;
import static org.lwjgl.d3d11.winerror.*;

import java.nio.ByteBuffer;

import org.lwjgl.system.windows.Direct3DDisplay;
import org.lwjgl.system.windows.MSG;

/**
 * This is a port of the Tutorial01 from the <a
 * href="https://code.msdn.microsoft.com/windowsdesktop/Direct3D-Tutorial-Win32-829979ef">Direct3D Tutorial Win32
 * Sample</a>.
 * 
 * @author kai
 *
 */
public class Tutorial01 {

    Direct3DDisplay window = null;
    D3D_DRIVER_TYPE g_driverType = D3D_DRIVER_TYPE_NULL;
    D3D_FEATURE_LEVEL g_featureLevel = D3D_FEATURE_LEVEL_10_0;
    ID3D11Device g_pd3dDevice = null;
    ID3D11Device1 g_pd3dDevice1 = null;
    ID3D11DeviceContext g_pImmediateContext = null;
    ID3D11DeviceContext1 g_pImmediateContext1 = null;
    IDXGISwapChain g_pSwapChain = null;
    IDXGISwapChain1 g_pSwapChain1 = null;
    ID3D11RenderTargetView g_pRenderTargetView = null;

    private void winMain() {
        if (FAILED(InitWindow())) {
            return;
        }

        if (FAILED(InitDevice())) {
            CleanupDevice();
            return;
        }

        ByteBuffer msgBuf = MSG.malloc();
        while (!window.isCloseRequested()) {
            int status;
            while ((status = PeekMessage(msgBuf, 0, 0, 0, PM_REMOVE)) != 0) {
                if (status == -1) // error
                    return;

                TranslateMessage(msgBuf);
                DispatchMessage(msgBuf);
            }
            Thread.yield();
        }
        window.destroy();
    }

    private long InitWindow() {
        window = new Direct3DDisplay("Direct3D 11 Tutorial 1: Direct3D 11 Basics", 800, 600);
        window.setVisible(true);
        return 0L;
    }

    private long InitDevice() {
        int createDeviceFlags = D3D11_CREATE_DEVICE_DEBUG;

        D3D_DRIVER_TYPE[] driverTypes = { D3D_DRIVER_TYPE_HARDWARE, D3D_DRIVER_TYPE_WARP, D3D_DRIVER_TYPE_REFERENCE };

        D3D_FEATURE_LEVEL[] featureLevels = { D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_11_1,
                D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_11_0, D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_10_1,
                D3D_FEATURE_LEVEL.D3D_FEATURE_LEVEL_10_0 };

        DXGI_SWAP_CHAIN_DESC swapChainDesc = new DXGI_SWAP_CHAIN_DESC();
        swapChainDesc.BufferCount = 1;
        swapChainDesc.BufferDesc.Width = 800;
        swapChainDesc.BufferDesc.Height = 600;
        swapChainDesc.BufferDesc.Format = DXGI_FORMAT.DXGI_FORMAT_R8G8B8A8_UNORM;
        swapChainDesc.BufferDesc.RefreshRate.Numerator = 60;
        swapChainDesc.BufferDesc.RefreshRate.Denominator = 1;
        swapChainDesc.BufferUsage = DXGI.DXGI_USAGE_RENDER_TARGET_OUTPUT;
        swapChainDesc.OutputWindow = window.getHwnd();
        swapChainDesc.SwapEffect = DXGI_SWAP_EFFECT.DXGI_SWAP_EFFECT_DISCARD;
        swapChainDesc.Windowed = true;
        swapChainDesc.SampleDesc.Count = 1;
        swapChainDesc.SampleDesc.Quality = 0;
        swapChainDesc.Flags = DXGI_SWAP_CHAIN_FLAG_ALLOW_MODE_SWITCH;

        long hr = 0;
        for (int driverTypeIndex = 0; driverTypeIndex < driverTypes.length; driverTypeIndex++) {
            g_driverType = driverTypes[driverTypeIndex];
            Out<ID3D11Device> device = new Out<ID3D11Device>();
            Out<D3D_FEATURE_LEVEL> featureLevel = new Out<D3D_FEATURE_LEVEL>();
            Out<ID3D11DeviceContext> immediateContext = new Out<ID3D11DeviceContext>();
            hr = D3D11CreateDevice(null, g_driverType, 0L, createDeviceFlags, featureLevels, D3D11_SDK_VERSION, device,
                    featureLevel, immediateContext);
            if (SUCCEEDED(hr))
                break;
        }
        if (FAILED(hr))
            return hr;

        return 0;
    }

    private void CleanupDevice() {

    }

    private void Render() {
        // Just clear the backbuffer
        g_pImmediateContext.ClearRenderTargetView(g_pRenderTargetView, DirectXColors.MidnightBlue);
        g_pSwapChain.Present(0, 0);
    }

    public static void main(String[] args) {
        new Tutorial01().winMain();
    }

}
