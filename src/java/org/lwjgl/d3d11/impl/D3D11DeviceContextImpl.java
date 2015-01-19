package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3D11_VIEWPORT;
import org.lwjgl.d3d11.D3D_PRIMITIVE_TOPOLOGY;
import org.lwjgl.d3d11.ID3D11Buffer;
import org.lwjgl.d3d11.ID3D11ClassInstance;
import org.lwjgl.d3d11.ID3D11DepthStencilView;
import org.lwjgl.d3d11.ID3D11DeviceContext;
import org.lwjgl.d3d11.ID3D11InputLayout;
import org.lwjgl.d3d11.ID3D11PixelShader;
import org.lwjgl.d3d11.ID3D11RenderTargetView;
import org.lwjgl.d3d11.ID3D11VertexShader;
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

    public static final native void nIASetVertexBuffers(long thisPtr, int startSlot, long vertexBuffersPtr,
            long stridesPtr, long offsetsPtr);

    public static final native void nIASetPrimitiveTopology(long thisPtr, int primitiveTopology);

    /**
     * Convenience alternative when only a single buffer is wanted.
     * 
     * @param thisPtr
     * @param startSlot
     * @param vertexBuffersPtr
     * @param strides
     * @param offsets
     */
    public static final native void nIASetVertexBuffers1(long thisPtr, int startSlot, long vertexBuffersPtr,
            int stride, int offset);

    public static final native void nVSSetShader(long thisPtr, long vertexShaderPtr, long classInstancesPtr,
            int numClassInstances);

    public static final native void nPSSetShader(long thisPtr, long pixelShaderPtr, long classInstancesPtr,
            int numClassInstances);

    public static final native void nDraw(long thisPtr, int vertexCount, int startVertexLocation);

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
            rtvPointers.put(i, rtvImpl != null ? rtvImpl.ptr : 0L);
        }
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

    @Override
    public void IASetVertexBuffers(int startSlot, ID3D11Buffer ppVertexBuffers, int stride, int offset) {
        D3D11BufferImpl bufferImpl = (D3D11BufferImpl) ppVertexBuffers;
        long bufferPtr = bufferImpl.ptr;
        nIASetVertexBuffers1(ptr, startSlot, bufferPtr, stride, offset);
    }

    @Override
    public void IASetVertexBuffers(int startSlot, ID3D11Buffer[] ppVertexBuffers, int[] strides, int[] offsets) {
        if (ppVertexBuffers.length == 1) {
            IASetVertexBuffers(startSlot, ppVertexBuffers[0], strides[0], offsets[0]);
        } else {
            PointerBuffer vertexBuffersPb = BufferPool.pointerBuffer(ppVertexBuffers.length);
            IntBuffer stridesIb = BufferUtils.createIntBuffer(strides.length);
            IntBuffer offsetsIb = BufferUtils.createIntBuffer(offsets.length);
            for (int i = 0; i < ppVertexBuffers.length; i++) {
                D3D11BufferImpl bufferImpl = (D3D11BufferImpl) ppVertexBuffers[0];
                vertexBuffersPb.put(i, bufferImpl.ptr);
                stridesIb.put(i, strides[i]);
                offsetsIb.put(i, offsets[i]);
            }
            nIASetVertexBuffers(ptr, startSlot, MemoryUtil.memAddress(vertexBuffersPb),
                    MemoryUtil.memAddress(stridesIb), MemoryUtil.memAddress(offsetsIb));
        }
    }

    @Override
    public void IASetPrimitiveTopology(D3D_PRIMITIVE_TOPOLOGY primitiveTopology) {
        nIASetPrimitiveTopology(ptr, primitiveTopology.value);
    }

    @Override
    public void VSSetShader(ID3D11VertexShader vertexShader, ID3D11ClassInstance[] classInstances) {
        D3D11VertexShaderImpl vsImpl = (D3D11VertexShaderImpl) vertexShader;
        long vsPtr = vsImpl.ptr;
        long classInstancesPtr = 0L;
        int numClassInstances = 0;
        if (classInstances != null && classInstances.length > 0) {
            PointerBuffer pb = BufferPool.pointerBuffer(classInstances.length);
            for (int i = 0; i < classInstances.length; i++) {
                D3D11ClassInstanceImpl classInstanceImpl = (D3D11ClassInstanceImpl) classInstances[i];
                pb.put(i, classInstanceImpl.ptr);
            }
            classInstancesPtr = MemoryUtil.memAddress(pb);
            numClassInstances = classInstances.length;
        }
        nVSSetShader(ptr, vsPtr, classInstancesPtr, numClassInstances);
    }

    @Override
    public void PSSetShader(ID3D11PixelShader pixelShader, ID3D11ClassInstance[] classInstances) {
        D3D11PixelShaderImpl psImpl = (D3D11PixelShaderImpl) pixelShader;
        long psPtr = psImpl.ptr;
        long classInstancesPtr = 0L;
        int numClassInstances = 0;
        if (classInstances != null && classInstances.length > 0) {
            PointerBuffer pb = BufferPool.pointerBuffer(classInstances.length);
            for (int i = 0; i < classInstances.length; i++) {
                D3D11ClassInstanceImpl classInstanceImpl = (D3D11ClassInstanceImpl) classInstances[i];
                pb.put(i, classInstanceImpl.ptr);
            }
            classInstancesPtr = MemoryUtil.memAddress(pb);
            numClassInstances = classInstances.length;
        }
        nPSSetShader(ptr, psPtr, classInstancesPtr, numClassInstances);
    }

    @Override
    public void Draw(int vertexCount, int startVertexLocation) {
        nDraw(ptr, vertexCount, startVertexLocation);
    }

}
