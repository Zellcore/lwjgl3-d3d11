package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

public interface ID3D11Device extends IUnknown {

    long CreateBuffer(D3D11_BUFFER_DESC desc, Object NULL, ID3D11Buffer buffer);

    long CreateRenderTargetView(ID3D11Texture2D pBackBuffer, D3D11_RENDER_TARGET_VIEW_DESC desc,
            Out<ID3D11RenderTargetView> renderTargetViewOut);

    long CreateVertexShader(ByteBuffer shaderBytecode, ID3D11ClassLinkage classLinkage,
            Out<ID3D11VertexShader> vertexShaderOut);

    long CreateInputLayout(D3D11_INPUT_ELEMENT_DESC[] layout, ByteBuffer shaderBytecodeWithInputSignature,
            Out<ID3D11InputLayout> inputLayoutOut);

    long CreatePixelShader(ByteBuffer shaderBytecode, ID3D11ClassLinkage classLinkage,
            Out<ID3D11PixelShader> pixelShaderOut);

}
