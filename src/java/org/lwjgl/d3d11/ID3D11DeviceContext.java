package org.lwjgl.d3d11;

public interface ID3D11DeviceContext extends ID3D11DeviceChild {

    void ClearRenderTargetView(ID3D11RenderTargetView view, float[] color);

    void OMSetRenderTargets(ID3D11RenderTargetView[] renderTargetView, ID3D11DepthStencilView depthStencilView);

    void RSSetViewports(D3D11_VIEWPORT[] d3d11_VIEWPORTs);

    void IASetInputLayout(ID3D11InputLayout inputLayout);

    void IASetVertexBuffers(int startSlot, ID3D11Buffer[] ppVertexBuffers, int[] strides, int[] offsets);

    void IASetVertexBuffers(int startSlot, ID3D11Buffer ppVertexBuffer, int stride, int offset);

    void IASetPrimitiveTopology(D3D_PRIMITIVE_TOPOLOGY primitiveTopology);

    void VSSetShader(ID3D11VertexShader vertexShader, ID3D11ClassInstance[] classInstances);

    void PSSetShader(ID3D11PixelShader pixelShader, ID3D11ClassInstance[] classInstances);

    void Draw(int vertexCount, int startVertexLocation);

    void IASetIndexBuffer(ID3D11Buffer indexBuffer, DXGI_FORMAT format, int offset);

    void DrawIndexed(int indexCount, int startIndexLocation, int baseVertexLocation);

}
