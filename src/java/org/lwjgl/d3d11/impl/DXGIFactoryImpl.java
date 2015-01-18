package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.DXGI_SWAP_CHAIN_DESC;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.IDXGIFactory;
import org.lwjgl.d3d11.IDXGISwapChain;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.d3d11.util.StructUtils;
import org.lwjgl.system.MemoryUtil;

public class DXGIFactoryImpl extends DXGIObjectImpl implements IDXGIFactory {

    static {
        Sys.touch();
    }

    public DXGIFactoryImpl(long ptr) {
        super(ptr);
    }

    public static final native long nMakeWindowAssociation(long thisPtr, long hwnd, int dxgiMwa);

    public static final native long nCreateSwapChain(long thisPtr, long devicePtr, long swapChainDescPtr,
            long swapChainOutPtr);

    @Override
    public long MakeWindowAssociation(long hwnd, int dxgiMwa) {
        return nMakeWindowAssociation(ptr, hwnd, dxgiMwa);
    }

    @Override
    public long CreateSwapChain(ID3D11Device device, DXGI_SWAP_CHAIN_DESC sd, Out<IDXGISwapChain> swapChainOut) {
        D3D11DeviceImpl deviceImpl = (D3D11DeviceImpl) device;
        long devicePtr = deviceImpl.ptr;
        long swapChainDescPtr = 0L;
        if (sd != null) {
            ByteBuffer sdBuffer = BufferPool.byteBuffer(DXGI_SWAP_CHAIN_DESC.SIZEOF);
            StructUtils.write(sd, sdBuffer);
            sdBuffer.flip();
            swapChainDescPtr = MemoryUtil.memAddress(sdBuffer);
        }
        PointerBuffer swapChainOutBuffer = BufferPool.pointerBuffer(1);
        long res = nCreateSwapChain(ptr, devicePtr, swapChainDescPtr, MemoryUtil.memAddress(swapChainOutBuffer));
        if (winerror.SUCCEEDED(res)) {
            IDXGISwapChain swapChain = new DXGISwapChainImpl(swapChainOutBuffer.get(0));
            swapChainOut.value = swapChain;
        }
        return res;
    }

}
