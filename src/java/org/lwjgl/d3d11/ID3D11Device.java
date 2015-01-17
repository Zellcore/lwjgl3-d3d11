package org.lwjgl.d3d11;

public interface ID3D11Device extends IUnknown {

    long CreateBuffer(D3D11_BUFFER_DESC desc, Object NULL, ID3D11Buffer buffer);

}
