package org.lwjgl.d3d11;

public interface ID3D11DeviceContext extends ID3D11DeviceChild {

    void ClearRenderTargetView(ID3D11RenderTargetView view, float[] color);

}
