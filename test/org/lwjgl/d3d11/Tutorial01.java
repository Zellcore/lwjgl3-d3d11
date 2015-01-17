package org.lwjgl.d3d11;

import static org.lwjgl.d3d11.D3D_DRIVER_TYPE.*;
import static org.lwjgl.d3d11.D3D_FEATURE_LEVEL.*;
import static org.lwjgl.system.windows.WinUser.*;

import java.nio.ByteBuffer;

import org.lwjgl.system.windows.Direct3DDisplay;
import org.lwjgl.system.windows.MSG;
import org.lwjgl.system.windows.WindowProc;

public class Tutorial01 {

    Direct3DDisplay window = null;
    D3D_DRIVER_TYPE g_driverType = D3D_DRIVER_TYPE_NULL;
    D3D_FEATURE_LEVEL g_featureLevel = D3D_FEATURE_LEVEL_11_0;
    ID3D11Device g_pd3dDevice = null;
    ID3D11Device1 g_pd3dDevice1 = null;
    ID3D11DeviceContext g_pImmediateContext = null;
    ID3D11DeviceContext1 g_pImmediateContext1 = null;
    IDXGISwapChain g_pSwapChain = null;
    IDXGISwapChain1 g_pSwapChain1 = null;
    ID3D11RenderTargetView g_pRenderTargetView = null;

    private void winMain() {
        initWindow();

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

    private void initWindow() {
        window = new Direct3DDisplay("Direct3D 11 Tutorial 1: Direct3D 11 Basics", 800, 600);
        window.setVisible(true);
    }

    private void initDevice() {
        
    }

    private void cleanupDevice() {

    }

    private void render() {
        // Just clear the backbuffer
        g_pImmediateContext.ClearRenderTargetView(g_pRenderTargetView, DirectXColors.MidnightBlue);
        g_pSwapChain.Present(0, 0);
    }

    public static void main(String[] args) {
        new Tutorial01().winMain();
    }

}
