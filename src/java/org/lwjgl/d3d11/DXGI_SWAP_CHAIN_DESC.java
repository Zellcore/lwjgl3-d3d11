package org.lwjgl.d3d11;

public class DXGI_SWAP_CHAIN_DESC {

    DXGI_MODE_DESC BufferDesc = new DXGI_MODE_DESC();
    DXGI_SAMPLE_DESC SampleDesc = new DXGI_SAMPLE_DESC();
    int BufferUsage;
    int BufferCount;
    long OutputWindow;
    boolean Windowed;
    DXGI_SWAP_EFFECT SwapEffect = null;
    int Flags = 0;

}
