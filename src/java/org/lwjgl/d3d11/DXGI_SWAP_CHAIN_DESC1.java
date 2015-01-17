package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

public class DXGI_SWAP_CHAIN_DESC1 {

    public ByteBuffer bb;

    public int Width;
    public int Height;
    public DXGI_FORMAT Format = DXGI_FORMAT.DXGI_FORMAT_UNKNOWN;
    public boolean Stereo;
    public DXGI_SAMPLE_DESC SampleDesc = new DXGI_SAMPLE_DESC();
    public int BufferUsage;
    public int BufferCount;
    public DXGI_SCALING Scaling = DXGI_SCALING.DXGI_SCALING_STRETCH;
    public DXGI_SWAP_EFFECT SwapEffect = DXGI_SWAP_EFFECT.DXGI_SWAP_EFFECT_DISCARD;
    public DXGI_ALPHA_MODE AlphaMode;
    public int Flags;

}
