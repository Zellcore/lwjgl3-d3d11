package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.d3d11.ID3DBlob;
import org.lwjgl.system.MemoryUtil;

public class D3DBlobImpl extends UnknownImpl implements ID3DBlob {

    static {
        Sys.touch();
    }

    public D3DBlobImpl(long ptr) {
        super(ptr);
    }

    public static final native long nGetBufferPointer(long thisPtr);

    public static final native int nGetBufferSize(long thisPtr);

    @Override
    public ByteBuffer GetBufferPointer() {
        int size = nGetBufferSize(ptr);
        long addr = nGetBufferPointer(ptr);
        ByteBuffer buf = MemoryUtil.memByteBuffer(addr, size);
        return buf;
    }

    @Override
    public int GetBufferSize() {
        return nGetBufferSize(ptr);
    }

}
