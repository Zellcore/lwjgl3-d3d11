package org.lwjgl.d3d11;

public class D3D11_BUFFER_DESC implements Struct {

    public static final int SIZEOF = 24;

    public int ByteWidth;
    public D3D11_USAGE Usage;
    public int BindFlags;
    public int CPUAccessFlags;
    public int MiscFlags;
    public int StructureByteStride;

}
