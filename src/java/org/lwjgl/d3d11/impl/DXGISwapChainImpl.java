package org.lwjgl.d3d11.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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

    public static GUID __uuid = COM.__uuidof_IDXGISwapChain();

    public static final native long nPresent(long thisPtr, int syncInterval, int flags);

    public static final native long nGetBuffer(long thisPtr, int buffer, long guidPtr, long surfaceOutPtr);

    @Override
    public long Present(int syncInterval, int flags) {
        return nPresent(ptr, syncInterval, flags);
    }

    @Override
    public <T> long GetBuffer(int buffer, Class<? extends T> clazz, Out<T> bufferOut) {
        try {
            PointerBuffer pb = BufferUtils.createPointerBuffer(1);
            Field uuidField = clazz.getDeclaredField("__uuid");
            GUID uuid = (GUID) uuidField.get(null);
            long res = nGetBuffer(ptr, buffer, MemoryUtil.memAddressSafe(uuid.bb), MemoryUtil.memAddressSafe(pb));
            if (winerror.SUCCEEDED(res)) {
                Constructor<? extends T> ctor = clazz.getConstructor(long.class);
                bufferOut.value = (T) ctor.newInstance(pb.get(0));
            }
            return res;
        } catch (Exception e) {
            throw new IllegalArgumentException("Not a reflectively instantiable clazz: " + clazz);
        }
    }

}
