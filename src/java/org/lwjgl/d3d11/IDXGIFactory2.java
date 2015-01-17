package org.lwjgl.d3d11;

import org.lwjgl.d3d11.impl.COM;

public interface IDXGIFactory2 extends IDXGIFactory1 {

    static GUID __uuid = COM.__uuidof_IDXGIFactory2();

    long CreateSwapChainForHwnd(ID3D11Device device, long hwnd, DXGI_SWAP_CHAIN_DESC1 desc,
            DXGI_SWAP_CHAIN_FULLSCREEN_DESC fullscreenDesc, IDXGIOutput restrictToOutput,
            Out<IDXGISwapChain1> swapChainOut);

}
