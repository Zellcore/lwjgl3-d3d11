package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

public class D3D11BufferDesc {

    public static final int SIZEOF = 6 * 4;

    int ByteWidth;
    D3D11Usage Usage;
    int BindFlags;
    int CPUAccessFlags;
    int MiscFlags;
    int StructureByteStride;

    public void write(ByteBuffer buffer) {
        buffer.putInt(ByteWidth);
        buffer.putInt(Usage.ordinal());
        buffer.putInt(BindFlags);
        buffer.putInt(CPUAccessFlags);
        buffer.putInt(MiscFlags);
        buffer.putInt(StructureByteStride);
    }

}
