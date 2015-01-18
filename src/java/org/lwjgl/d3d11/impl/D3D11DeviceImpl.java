package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3D11_BUFFER_DESC;
import org.lwjgl.d3d11.D3D11_RENDER_TARGET_VIEW_DESC;
import org.lwjgl.d3d11.ID3D11Buffer;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.ID3D11RenderTargetView;
import org.lwjgl.d3d11.ID3D11Texture2D;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.d3d11.util.StructUtils;
import org.lwjgl.system.MemoryUtil;

public class D3D11DeviceImpl extends UnknownImpl implements ID3D11Device {

    static {
        Sys.touch();
    }

    public D3D11DeviceImpl(long ptr) {
        super(ptr);
    }

    public static final native long nCreateBuffer(long bufferDescPtr, long bufferAddr);

    public static final native long nCreateRenderTargetView(long thisPtr, long backBufferPtr, long descPtr,
            long renderTargetViewOutPtr);

    public long CreateBuffer(D3D11_BUFFER_DESC desc, Object NULL, ID3D11Buffer buffer) {
        ByteBuffer bufferDesc = BufferPool.byteBuffer(D3D11_BUFFER_DESC.SIZEOF);
        StructUtils.write(desc, bufferDesc);
        bufferDesc.flip();
        D3D11BufferImpl bufferImpl = (D3D11BufferImpl) buffer;
        long bufferAddr = bufferImpl.ptr;
        return nCreateBuffer(MemoryUtil.memAddressSafe(bufferDesc), bufferAddr);
    }

    @Override
    public long CreateRenderTargetView(ID3D11Texture2D pBackBuffer, D3D11_RENDER_TARGET_VIEW_DESC pDesc,
            Out<ID3D11RenderTargetView> renderTargetViewOut) {
        D3D11Texture2DImpl backBufferImpl = (D3D11Texture2DImpl) pBackBuffer;
        long backBufferPtr = backBufferImpl != null ? backBufferImpl.ptr : 0L;
        long descPtr = 0L;
        if (pDesc != null) {
            ByteBuffer descBuffer = BufferPool.byteBuffer(D3D11_BUFFER_DESC.SIZEOF);
            StructUtils.write(pDesc, descBuffer);
            descBuffer.rewind();
        }
        PointerBuffer pb = BufferPool.pointerBuffer(1);
        long ret = nCreateRenderTargetView(ptr, backBufferPtr, descPtr, MemoryUtil.memAddress(pb));
        if (winerror.SUCCEEDED(ret)) {
            ID3D11RenderTargetView rtv = new D3D11RenderTargetViewImpl(pb.get(0));
            renderTargetViewOut.value = rtv;
        }
        return ret;
    }

}
