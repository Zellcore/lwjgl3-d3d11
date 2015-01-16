package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.IDXGISwapChain;

public class DXGISwapChainImpl implements IDXGISwapChain {

    private final long ptr;

    public DXGISwapChainImpl(long ptr) {
        super();
        this.ptr = ptr;
    }

}
