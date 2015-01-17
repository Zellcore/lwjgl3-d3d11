package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;

public class GUID {

    public static int SIZEOF = 16;

    public final ByteBuffer bb;

    public GUID(ByteBuffer bb) {
        this.bb = bb;
    }

    public static ByteBuffer malloc() {
        return BufferUtils.createByteBuffer(SIZEOF);
    }

}
