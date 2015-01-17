package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.DXGI_SWAP_CHAIN_DESC1;
import org.lwjgl.d3d11.DXGI_SWAP_CHAIN_FULLSCREEN_DESC;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.IDXGIFactory2;
import org.lwjgl.d3d11.IDXGIOutput;
import org.lwjgl.d3d11.IDXGISwapChain1;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.d3d11.util.StructUtils;
import org.lwjgl.system.MemoryUtil;

public class DXGIFactory2Impl extends DXGIFactory1Impl implements IDXGIFactory2 {

    static {
        Sys.touch();
    }

    public DXGIFactory2Impl(long ptr) {
        super(ptr);
    }

    public static final native long nCreateSwapChainForHwnd(long thisPtr, long devicePtr, long hwnd,
            long swapChainDescPtr, long swapChainFullscreenDescPtr, long dxgiOutputPtr, long swapChainOutPtr);

    @Override
    public long CreateSwapChainForHwnd(ID3D11Device device, long hwnd, DXGI_SWAP_CHAIN_DESC1 desc,
            DXGI_SWAP_CHAIN_FULLSCREEN_DESC fullscreenDesc, IDXGIOutput restrictToOutput,
            Out<IDXGISwapChain1> swapChainOut) {
        D3D11DeviceImpl deviceImpl = (D3D11DeviceImpl) device;
        long devicePtr = deviceImpl != null ? deviceImpl.ptr : 0L;
        long swapChainDescPtr = 0L;
        if (desc != null) {
            ByteBuffer swapChainDescBuf = BufferPool.byteBuffer(DXGI_SWAP_CHAIN_DESC1.SIZEOF);
            StructUtils.write(desc, swapChainDescBuf);
            swapChainDescBuf.rewind();
            swapChainDescPtr = MemoryUtil.memAddress(swapChainDescBuf);
        }
        long swapChainFullscreenDescPtr = fullscreenDesc != null ? MemoryUtil.memAddressSafe(fullscreenDesc.bb) : 0L;
        DXGIOutputImpl outputImpl = (DXGIOutputImpl) restrictToOutput;
        long dxgiOutputPtr = outputImpl != null ? outputImpl.ptr : 0L;
        PointerBuffer swapChainOutPb = BufferPool.pointerBuffer(1);
        long res = nCreateSwapChainForHwnd(ptr, devicePtr, hwnd, swapChainDescPtr, swapChainFullscreenDescPtr,
                dxgiOutputPtr, MemoryUtil.memAddress(swapChainOutPb));
        if (winerror.SUCCEEDED(res)) {
            IDXGISwapChain1 swapChain = new DXGISwapChain1Impl(swapChainOutPb.get(0));
            swapChainOut.value = swapChain;
        }
        return res;
    }

}
