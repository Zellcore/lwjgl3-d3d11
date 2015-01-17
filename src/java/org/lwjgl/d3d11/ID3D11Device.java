package org.lwjgl.d3d11;

public interface ID3D11Device extends IUnknown {

    long CreateBuffer(D3D11_BUFFER_DESC desc, Object NULL, ID3D11Buffer buffer);

    long CreateRenderTargetView(ID3D11Texture2D pBackBuffer, D3D11_RENDER_TARGET_VIEW_DESC pDesc,
            Out<ID3D11RenderTargetView> renderTargetViewOut);

}
