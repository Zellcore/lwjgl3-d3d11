package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.d3d11.ID3DBlob;

public class D3DBlobImpl extends UnknownImpl implements ID3DBlob {

    public D3DBlobImpl(long ptr) {
        super(ptr);
    }

    @Override
    public ByteBuffer GetBufferPointer() {
        return null;
    }

}
