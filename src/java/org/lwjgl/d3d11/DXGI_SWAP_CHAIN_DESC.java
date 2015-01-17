package org.lwjgl.d3d11;

public class DXGI_SWAP_CHAIN_DESC {

    public static final int SIZEOF = 72;

    public DXGI_MODE_DESC BufferDesc = new DXGI_MODE_DESC();
    public DXGI_SAMPLE_DESC SampleDesc = new DXGI_SAMPLE_DESC();
    public int BufferUsage;
    public int BufferCount;
    public long OutputWindow;
    public boolean Windowed;
    public DXGI_SWAP_EFFECT SwapEffect = null;
    public int Flags = 0;

}
