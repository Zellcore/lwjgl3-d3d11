package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.ID3D11Buffer;

public class D3D11BufferImpl implements ID3D11Buffer {

    final long ptr;

    D3D11BufferImpl(long ptr) {
        super();
        this.ptr = ptr;
    }

}
