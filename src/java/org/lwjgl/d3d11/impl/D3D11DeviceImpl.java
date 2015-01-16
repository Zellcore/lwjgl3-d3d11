package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.d3d11.D3D11BufferDesc;
import org.lwjgl.d3d11.ID3D11Buffer;
import org.lwjgl.d3d11.ID3D11Device;

public class D3D11DeviceImpl implements ID3D11Device {

    private final long ptr;

    D3D11DeviceImpl(long ptr) {
        super();
        this.ptr = ptr;
    }

    private static final native long CreateBuffer0(ByteBuffer bufferDesc,
            long bufferAddr);

    public long CreateBuffer(D3D11BufferDesc desc, Object NULL,
            ID3D11Buffer buffer) {
        ByteBuffer bufferDesc = Memory.buffer(D3D11BufferDesc.SIZEOF);
        desc.write(bufferDesc);
        bufferDesc.flip();
        D3D11BufferImpl bufferImpl = (D3D11BufferImpl) buffer;
        long bufferAddr = bufferImpl.ptr;
        return CreateBuffer0(bufferDesc, bufferAddr);
    }

}
