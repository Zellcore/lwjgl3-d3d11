package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3D11_BUFFER_DESC;
import org.lwjgl.d3d11.D3D11_INPUT_ELEMENT_DESC;
import org.lwjgl.d3d11.D3D11_RENDER_TARGET_VIEW_DESC;
import org.lwjgl.d3d11.ID3D11Buffer;
import org.lwjgl.d3d11.ID3D11ClassLinkage;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.ID3D11InputLayout;
import org.lwjgl.d3d11.ID3D11PixelShader;
import org.lwjgl.d3d11.ID3D11RenderTargetView;
import org.lwjgl.d3d11.ID3D11Texture2D;
import org.lwjgl.d3d11.ID3D11VertexShader;
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

    public static final native long nCreateBuffer(long thisPtr, long bufferDescPtr, long bufferAddr);

    public static final native long nCreateRenderTargetView(long thisPtr, long backBufferPtr, long descPtr,
            long renderTargetViewOutPtr);

    public static final native long nCreateVertexShader(long thisPtr, long shaderBytecodePtr, int bytecodeLength,
            long classLinkagePtr, long vertexShaderOutPtr);

    public static final native long nCreateInputLayout(long thisPtr, D3D11_INPUT_ELEMENT_DESC[] layout,
            long shaderBytecodeWithInputSignaturePtr, int bytecodeLength, long inputLayoutOutPtr);

    public static final native long nCreatePixelShader(long thisPtr, long shaderBytecodePtr, int bytecodeLength,
            long classLinkagePtr, long pixelShaderOutPtr);

    public long CreateBuffer(D3D11_BUFFER_DESC desc, Object NULL, ID3D11Buffer buffer) {
        ByteBuffer bufferDesc = BufferPool.byteBuffer(D3D11_BUFFER_DESC.SIZEOF);
        StructUtils.write(desc, bufferDesc);
        bufferDesc.flip();
        D3D11BufferImpl bufferImpl = (D3D11BufferImpl) buffer;
        long bufferAddr = bufferImpl.ptr;
        return nCreateBuffer(ptr, MemoryUtil.memAddressSafe(bufferDesc), bufferAddr);
    }

    @Override
    public long CreateRenderTargetView(ID3D11Texture2D backBuffer, D3D11_RENDER_TARGET_VIEW_DESC desc,
            Out<ID3D11RenderTargetView> renderTargetViewOut) {
        D3D11Texture2DImpl backBufferImpl = (D3D11Texture2DImpl) backBuffer;
        long backBufferPtr = backBufferImpl != null ? backBufferImpl.ptr : 0L;
        long descPtr = 0L;
        if (desc != null) {
            ByteBuffer descBuffer = BufferPool.byteBuffer(D3D11_BUFFER_DESC.SIZEOF);
            StructUtils.write(desc, descBuffer);
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

    @Override
    public long CreateVertexShader(ByteBuffer shaderBytecode, ID3D11ClassLinkage classLinkage,
            Out<ID3D11VertexShader> vertexShaderOut) {
        long shaderBytecodePtr = MemoryUtil.memAddress(shaderBytecode);
        int length = shaderBytecode.remaining();
        NativeObjectImpl classLinkageImpl = (NativeObjectImpl) classLinkage;
        long classLinkagePtr = 0L;
        if (classLinkageImpl != null) {
            classLinkagePtr = classLinkageImpl.ptr;
        }
        PointerBuffer vertexShaderOutPb = BufferPool.pointerBuffer(1);
        long ret = nCreateVertexShader(ptr, shaderBytecodePtr, length, classLinkagePtr,
                MemoryUtil.memAddress(vertexShaderOutPb));
        if (winerror.SUCCEEDED(ret)) {
            D3D11VertexShaderImpl vsImpl = new D3D11VertexShaderImpl(vertexShaderOutPb.get(0));
            vertexShaderOut.value = vsImpl;
        }
        return ret;
    }

    @Override
    public long CreateInputLayout(D3D11_INPUT_ELEMENT_DESC[] layout, ByteBuffer shaderBytecodeWithInputSignature,
            Out<ID3D11InputLayout> inputLayoutOut) {
        PointerBuffer inputLayoutOutPb = BufferPool.pointerBuffer(1);
        long ret = nCreateInputLayout(ptr, layout, MemoryUtil.memAddress(shaderBytecodeWithInputSignature),
                shaderBytecodeWithInputSignature.remaining(), MemoryUtil.memAddress(inputLayoutOutPb));
        if (winerror.SUCCEEDED(ret)) {
            D3D11InputLayoutImpl layoutImpl = new D3D11InputLayoutImpl(inputLayoutOutPb.get(0));
            inputLayoutOut.value = layoutImpl;
        }
        return ret;
    }

    @Override
    public long CreatePixelShader(ByteBuffer shaderBytecode, ID3D11ClassLinkage classLinkage,
            Out<ID3D11PixelShader> pixelShaderOut) {
        long shaderBytecodePtr = MemoryUtil.memAddress(shaderBytecode);
        int length = shaderBytecode.remaining();
        NativeObjectImpl classLinkageImpl = (NativeObjectImpl) classLinkage;
        long classLinkagePtr = 0L;
        if (classLinkageImpl != null) {
            classLinkagePtr = classLinkageImpl.ptr;
        }
        PointerBuffer pixelShaderOutPb = BufferPool.pointerBuffer(1);
        long ret = nCreatePixelShader(ptr, shaderBytecodePtr, length, classLinkagePtr,
                MemoryUtil.memAddress(pixelShaderOutPb));
        if (winerror.SUCCEEDED(ret)) {
            D3D11PixelShaderImpl psImpl = new D3D11PixelShaderImpl(pixelShaderOutPb.get(0));
            pixelShaderOut.value = psImpl;
        }
        return ret;
    }

}
