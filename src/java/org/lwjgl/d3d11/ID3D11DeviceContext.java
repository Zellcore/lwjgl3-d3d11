package org.lwjgl.d3d11;

import org.lwjgl.ID3D11DepthStencilView;

public interface ID3D11DeviceContext extends ID3D11DeviceChild {

    void ClearRenderTargetView(ID3D11RenderTargetView view, float[] color);

    void OMSetRenderTargets(ID3D11RenderTargetView[] renderTargetView, ID3D11DepthStencilView depthStencilView);

    void RSSetViewports(D3D11_VIEWPORT[] d3d11_VIEWPORTs);

}
