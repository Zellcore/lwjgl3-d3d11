package org.lwjgl.d3d11;

public interface IDXGIFactory extends IDXGIObject {

    long MakeWindowAssociation(long hwnd, int dxgiMwa);

}
