package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.d3d11.ID3D11DeviceContext;
import org.lwjgl.d3d11.ID3D11RenderTargetView;

public class D3D11DeviceContextImpl extends D3D11DeviceChildImpl implements ID3D11DeviceContext {

    static {
        Sys.touch();
    }

    public D3D11DeviceContextImpl(long ptr) {
        super(ptr);
    }

    public static final native void ClearRenderTargetView0(long renderTargetViewPtr, ByteBuffer colorBuffer);

    @Override
    public void ClearRenderTargetView(ID3D11RenderTargetView view, float[] color) {
        ByteBuffer colorBuffer = BufferUtils.createByteBuffer(4 * 4);
        colorBuffer.asFloatBuffer().put(color);
        D3D11RenderTargetViewImpl viewImpl = (D3D11RenderTargetViewImpl) view;
        ClearRenderTargetView0(viewImpl.ptr, colorBuffer);
    }

}
