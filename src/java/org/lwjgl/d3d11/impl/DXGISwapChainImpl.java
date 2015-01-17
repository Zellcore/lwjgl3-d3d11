package org.lwjgl.d3d11.impl;

import java.lang.reflect.Constructor;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.IDXGISwapChain;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.system.MemoryUtil;

public class DXGISwapChainImpl extends DXGIDeviceSubObjectImpl implements IDXGISwapChain {

    public DXGISwapChainImpl(long ptr) {
        super(ptr);
    }

    public static final native long nPresent(long thisPtr, int syncInterval, int flags);

    public static final native long nGetBuffer(long thisPtr, int buffer, long guidPtr, long surfaceOutPtr);

    @Override
    public long Present(int syncInterval, int flags) {
        return nPresent(ptr, syncInterval, flags);
    }

    @Override
    public <T> long GetBuffer(int buffer, GUID riid, Class<? extends T> clazz, Out<T> bufferOut) {
        PointerBuffer pb = BufferUtils.createPointerBuffer(1);
        long res = nGetBuffer(ptr, buffer, MemoryUtil.memAddressSafe(riid.bb), MemoryUtil.memAddressSafe(pb));
        if (winerror.SUCCEEDED(res)) {
            try {
                Constructor<? extends T> ctor = clazz.getConstructor(long.class);
                bufferOut.value = (T) ctor.newInstance(pb.get(0));
            } catch (Exception e) {
                throw new IllegalArgumentException("Not a reflectively instantiable clazz: " + clazz);
            }
        }
        return res;
    }

}
