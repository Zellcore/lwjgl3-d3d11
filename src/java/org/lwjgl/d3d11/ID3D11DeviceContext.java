package org.lwjgl.d3d11;

public interface ID3D11DeviceContext extends ID3D11DeviceChild {

    void ClearRenderTargetView(ID3D11RenderTargetView view, float[] color);

    void OMSetRenderTargets(ID3D11RenderTargetView[] renderTargetView, ID3D11DepthStencilView depthStencilView);

    void RSSetViewports(D3D11_VIEWPORT[] d3d11_VIEWPORTs);

    void IASetInputLayout(ID3D11InputLayout inputLayout);

}
