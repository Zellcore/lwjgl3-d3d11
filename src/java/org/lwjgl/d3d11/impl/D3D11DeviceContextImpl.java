package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3D11_VIEWPORT;
import org.lwjgl.d3d11.ID3D11DepthStencilView;
import org.lwjgl.d3d11.ID3D11DeviceContext;
import org.lwjgl.d3d11.ID3D11InputLayout;
import org.lwjgl.d3d11.ID3D11RenderTargetView;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.d3d11.util.StructUtils;
import org.lwjgl.system.MemoryUtil;

public class D3D11DeviceContextImpl extends D3D11DeviceChildImpl implements ID3D11DeviceContext {

    static {
        Sys.touch();
    }

    public D3D11DeviceContextImpl(long ptr) {
        super(ptr);
    }

    public static final native void nClearRenderTargetView(long thisPtr, long renderTargetViewPtr, long colorPtr);

    public static final native void nOMSetRenderTargets(long thisPtr, int numViews, long renderTargetViewsPtr,
            long depthStencilViewPtr);

    public static final native void nRSSetViewports(long thisPtr, int numViewports, long viewportsPtr);

    public static final native void nIASetInputLayout(long thisPtr, long inputLayoutPtr);

    @Override
    public void ClearRenderTargetView(ID3D11RenderTargetView view, float[] color) {
        ByteBuffer colorBuffer = BufferPool.byteBuffer(4 * 4);
        colorBuffer.putFloat(color[0]);
        colorBuffer.putFloat(color[1]);
        colorBuffer.putFloat(color[2]);
        colorBuffer.putFloat(color[3]);
        colorBuffer.flip();
        D3D11RenderTargetViewImpl viewImpl = (D3D11RenderTargetViewImpl) view;
        nClearRenderTargetView(ptr, viewImpl.ptr, MemoryUtil.memAddress(colorBuffer));
    }

    @Override
    public void OMSetRenderTargets(ID3D11RenderTargetView[] renderTargetViews, ID3D11DepthStencilView depthStencilView) {
        PointerBuffer rtvPointers = BufferPool.pointerBuffer(renderTargetViews.length);
        for (int i = 0; i < renderTargetViews.length; i++) {
            D3D11RenderTargetViewImpl rtvImpl = (D3D11RenderTargetViewImpl) renderTargetViews[i];
            rtvPointers.put(rtvImpl != null ? rtvImpl.ptr : 0L);
        }
        rtvPointers.rewind();
        D3D11DepthStencilViewImpl dsvImpl = (D3D11DepthStencilViewImpl) depthStencilView;
        long dsvPtr = dsvImpl != null ? dsvImpl.ptr : 0L;
        nOMSetRenderTargets(ptr, renderTargetViews.length, MemoryUtil.memAddress(rtvPointers), dsvPtr);
    }

    @Override
    public void RSSetViewports(D3D11_VIEWPORT[] viewports) {
        ByteBuffer vpBuffer = BufferPool.byteBuffer(D3D11_VIEWPORT.SIZEOF * viewports.length);
        for (int i = 0; i < viewports.length; i++) {
            StructUtils.write(viewports[i], vpBuffer);
        }
        vpBuffer.rewind();
        nRSSetViewports(ptr, viewports.length, MemoryUtil.memAddress(vpBuffer));
    }

    @Override
    public void IASetInputLayout(ID3D11InputLayout inputLayout) {
        NativeObjectImpl inputLayoutImpl = (NativeObjectImpl) inputLayout;
        nIASetInputLayout(ptr, inputLayoutImpl.ptr);
    }

}
