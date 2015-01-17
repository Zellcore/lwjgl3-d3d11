package org.lwjgl.d3d11.tutorial01;

import static org.lwjgl.d3d11.D3D_DRIVER_TYPE.*;
import static org.lwjgl.d3d11.D3D_FEATURE_LEVEL.*;
import static org.lwjgl.d3d11.impl.D3D11.*;
import static org.lwjgl.d3d11.DXGI_FORMAT.*;
import static org.lwjgl.d3d11.DXGI.*;
import static org.lwjgl.d3d11.D3D11_CREATE_DEVICE_FLAG.*;
import static org.lwjgl.system.windows.WinUser.*;
import static org.lwjgl.d3d11.winerror.*;

import java.nio.ByteBuffer;

import org.lwjgl.d3d11.D3D11_VIEWPORT;
import org.lwjgl.d3d11.D3D_DRIVER_TYPE;
import org.lwjgl.d3d11.D3D_FEATURE_LEVEL;
import org.lwjgl.d3d11.DXGI_SWAP_CHAIN_DESC1;
import org.lwjgl.d3d11.DirectXColors;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.ID3D11Device1;
import org.lwjgl.d3d11.ID3D11DeviceContext;
import org.lwjgl.d3d11.ID3D11DeviceContext1;
import org.lwjgl.d3d11.ID3D11RenderTargetView;
import org.lwjgl.d3d11.ID3D11Texture2D;
import org.lwjgl.d3d11.IDXGIAdapter;
import org.lwjgl.d3d11.IDXGIDevice;
import org.lwjgl.d3d11.IDXGIFactory1;
import org.lwjgl.d3d11.IDXGIFactory2;
import org.lwjgl.d3d11.IDXGISwapChain;
import org.lwjgl.d3d11.IDXGISwapChain1;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.impl.D3D11Device1Impl;
import org.lwjgl.d3d11.impl.D3D11DeviceContext1Impl;
import org.lwjgl.d3d11.impl.D3D11Texture2DImpl;
import org.lwjgl.d3d11.impl.DXGIDeviceImpl;
import org.lwjgl.d3d11.impl.DXGIFactory1Impl;
import org.lwjgl.d3d11.impl.DXGIFactory2Impl;
import org.lwjgl.d3d11.impl.DXGISwapChainImpl;
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
            Render();
        }
        window.destroy();
    }

    private long InitWindow() {
        window = new Direct3DDisplay("Direct3D 11 Tutorial 1: Direct3D 11 Basics", 800, 600, WS_OVERLAPPED | WS_CAPTION
                | WS_SYSMENU | WS_MINIMIZEBOX);
        window.setVisible(true);
        return 0L;
    }

    private long InitDevice() {
        int createDeviceFlags = D3D11_CREATE_DEVICE_DEBUG;

        D3D_DRIVER_TYPE[] driverTypes = { D3D_DRIVER_TYPE_HARDWARE, D3D_DRIVER_TYPE_WARP, D3D_DRIVER_TYPE_REFERENCE };

        D3D_FEATURE_LEVEL[] featureLevels = { D3D_FEATURE_LEVEL_11_1, D3D_FEATURE_LEVEL_11_0, D3D_FEATURE_LEVEL_10_1,
                D3D_FEATURE_LEVEL_10_0 };

        long hr = 0;
        Out<ID3D11Device> deviceOut = new Out<ID3D11Device>();
        Out<D3D_FEATURE_LEVEL> featureLevelOut = new Out<D3D_FEATURE_LEVEL>();
        Out<ID3D11DeviceContext> immediateContextOut = new Out<ID3D11DeviceContext>();
        for (int driverTypeIndex = 0; driverTypeIndex < driverTypes.length; driverTypeIndex++) {
            g_driverType = driverTypes[driverTypeIndex];
            hr = D3D11CreateDevice(null, g_driverType, 0L, createDeviceFlags, featureLevels, D3D11_SDK_VERSION,
                    deviceOut, featureLevelOut, immediateContextOut);
            g_pd3dDevice = deviceOut.value;
            g_featureLevel = featureLevelOut.value;
            g_pImmediateContext = immediateContextOut.value;
            if (SUCCEEDED(hr))
                break;
        }
        if (FAILED(hr))
            return hr;

        // Obtain DXGI factory from device (since we used nullptr for pAdapter above)
        IDXGIFactory1 dxgiFactory = null;
        {
            IDXGIDevice dxgiDevice = null;
            Out<IDXGIDevice> dxgiDeviceOut = new Out<IDXGIDevice>();
            hr = g_pd3dDevice.QueryInterface(DXGIDeviceImpl.class, dxgiDeviceOut);
            dxgiDevice = dxgiDeviceOut.value;
            if (SUCCEEDED(hr)) {
                IDXGIAdapter adapter = null;
                Out<IDXGIAdapter> adapterOut = new Out<IDXGIAdapter>();
                hr = dxgiDevice.GetAdapter(adapterOut);
                adapter = adapterOut.value;
                if (SUCCEEDED(hr)) {
                    Out<IDXGIFactory1> dxgiFactoryOut = new Out<IDXGIFactory1>();
                    hr = adapter.GetParent(DXGIFactory1Impl.class, dxgiFactoryOut);
                    dxgiFactory = dxgiFactoryOut.value;
                    adapter.Release();
                }
                dxgiDevice.Release();
            }
        }
        if (FAILED(hr))
            return hr;

        // Create swap chain
        IDXGIFactory2 dxgiFactory2 = null;
        Out<IDXGIFactory2> dxgiFactory2Out = new Out<IDXGIFactory2>();
        hr = dxgiFactory.QueryInterface(DXGIFactory2Impl.class, dxgiFactory2Out);
        dxgiFactory2 = dxgiFactory2Out.value;
        if (dxgiFactory2 != null) {
            // DirectX 11.1 or later
            Out<ID3D11Device1> pd3dDevice1Out = new Out<ID3D11Device1>();
            hr = g_pd3dDevice.QueryInterface(D3D11Device1Impl.class, pd3dDevice1Out);
            g_pd3dDevice1 = pd3dDevice1Out.value;
            if (SUCCEEDED(hr)) {
                Out<ID3D11DeviceContext1> pImmediateContext1Out = new Out<ID3D11DeviceContext1>();
                g_pImmediateContext.QueryInterface(D3D11DeviceContext1Impl.class, pImmediateContext1Out);
                g_pImmediateContext1 = pImmediateContext1Out.value;
            }

            DXGI_SWAP_CHAIN_DESC1 sd = new DXGI_SWAP_CHAIN_DESC1();
            sd.Width = window.getClientWidth();
            sd.Height = window.getClientHeight();
            sd.Format = DXGI_FORMAT_R8G8B8A8_UNORM;
            sd.SampleDesc.Count = 1;
            sd.SampleDesc.Quality = 0;
            sd.BufferUsage = DXGI_USAGE_RENDER_TARGET_OUTPUT;
            sd.BufferCount = 1;

            Out<IDXGISwapChain1> swapChain1Out = new Out<IDXGISwapChain1>();
            hr = dxgiFactory2.CreateSwapChainForHwnd(g_pd3dDevice, window.getHwnd(), sd, null, null, swapChain1Out);
            g_pSwapChain1 = swapChain1Out.value;
            if (SUCCEEDED(hr)) {
                Out<IDXGISwapChain> swapChainOut = new Out<IDXGISwapChain>();
                hr = g_pSwapChain1.QueryInterface(DXGISwapChainImpl.class, swapChainOut);
                g_pSwapChain = swapChainOut.value;
            }
            dxgiFactory2.Release();
        } else {
            throw new UnsupportedOperationException("NYI");
        }

        // Note this tutorial doesn't handle full-screen swapchains so we block the ALT+ENTER shortcut
        hr = dxgiFactory.MakeWindowAssociation(window.getHwnd(), DXGI_MWA_NO_ALT_ENTER);
        dxgiFactory.Release();

        if (FAILED(hr))
            return hr;

        // Create a render target view
        ID3D11Texture2D pBackBuffer = null;
        Out<ID3D11Texture2D> pBackBufferOut = new Out<ID3D11Texture2D>();
        hr = g_pSwapChain.GetBuffer(0, D3D11Texture2DImpl.class, pBackBufferOut);
        pBackBuffer = pBackBufferOut.value;
        if (FAILED(hr))
            return hr;

        Out<ID3D11RenderTargetView> renderTargetViewOut = new Out<ID3D11RenderTargetView>();
        hr = g_pd3dDevice.CreateRenderTargetView(pBackBuffer, null, renderTargetViewOut);
        g_pRenderTargetView = renderTargetViewOut.value;
        pBackBuffer.Release();
        if (FAILED(hr))
            return hr;

        g_pImmediateContext.OMSetRenderTargets(new ID3D11RenderTargetView[] { g_pRenderTargetView }, null);

        // Setup the viewport
        D3D11_VIEWPORT vp = new D3D11_VIEWPORT();
        vp.Width = window.getClientWidth();
        vp.Height = window.getClientHeight();
        vp.MinDepth = 0.0f;
        vp.MaxDepth = 1.0f;
        vp.TopLeftX = 0;
        vp.TopLeftY = 0;
        g_pImmediateContext.RSSetViewports(new D3D11_VIEWPORT[] { vp });

        return 0;
    }

    private void CleanupDevice() {

    }

    private void Render() {
        // Just clear the backbuffer
        g_pImmediateContext.ClearRenderTargetView(g_pRenderTargetView, DirectXColors.CornflowerBlue);
        g_pSwapChain.Present(0, 0);
    }

    public static void main(String[] args) {
        new Tutorial01().winMain();
    }

}
