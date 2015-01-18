package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

public class D3D11_SUBRESOURCE_DATA implements Struct {

    public static final int SIZEOF = org.lwjgl.Pointer.POINTER_SIZE + 4 + 4;

    public ByteBuffer pSysMem;
    public int SysMemPitch;
    public int SysMemSlicePitch;

}
