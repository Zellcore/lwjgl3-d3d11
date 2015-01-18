package org.lwjgl.d3d11;

import org.lwjgl.d3d11.annotation.Pointer;

public class DXGI_SWAP_CHAIN_DESC implements Struct {

    public static final int SIZEOF = 72;

    public DXGI_MODE_DESC BufferDesc = new DXGI_MODE_DESC();
    public DXGI_SAMPLE_DESC SampleDesc = new DXGI_SAMPLE_DESC();
    public int BufferUsage;
    public int BufferCount;
    @Pointer
    public long OutputWindow;
    public boolean Windowed;
    public DXGI_SWAP_EFFECT SwapEffect = DXGI_SWAP_EFFECT.DXGI_SWAP_EFFECT_DISCARD;
    public int Flags = 0;

}
