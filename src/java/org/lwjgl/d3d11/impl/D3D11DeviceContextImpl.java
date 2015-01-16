package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.ID3D11DeviceContext;

public class D3D11DeviceContextImpl implements ID3D11DeviceContext {

    private final long ptr;

    public D3D11DeviceContextImpl(long ptr) {
        super();
        this.ptr = ptr;
    }

}
