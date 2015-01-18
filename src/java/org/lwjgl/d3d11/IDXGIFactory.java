package org.lwjgl.d3d11;

public interface IDXGIFactory extends IDXGIObject {

    long MakeWindowAssociation(long hwnd, int dxgiMwa);

    long CreateSwapChain(ID3D11Device device, DXGI_SWAP_CHAIN_DESC sd, Out<IDXGISwapChain> swapChainOut);

}
