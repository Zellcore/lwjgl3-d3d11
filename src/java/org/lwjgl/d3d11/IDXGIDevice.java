package org.lwjgl.d3d11;

public interface IDXGIDevice extends IDXGIObject {

    long GetAdapter(Out<IDXGIAdapter> adapter);

}
