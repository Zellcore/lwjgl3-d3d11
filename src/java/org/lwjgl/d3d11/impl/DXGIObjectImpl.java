package org.lwjgl.d3d11.impl;

import java.lang.reflect.Constructor;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.IDXGIObject;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.system.MemoryUtil;

public class DXGIObjectImpl extends UnknownImpl implements IDXGIObject {

    public DXGIObjectImpl(long ptr) {
        super(ptr);
    }

    public static final native long nGetParent(long thisPtr, long guidPtr, long objectOutPtr);

    @Override
    public <T> long GetParent(GUID riid, Class<? extends T> clazz, Out<T> objectOut) {
        PointerBuffer pb = BufferPool.pointerBuffer(1);
        long res = nGetParent(ptr, MemoryUtil.memAddressSafe(riid.bb), MemoryUtil.memAddress(pb));
        if (winerror.SUCCEEDED(res)) {
            try {
                Constructor<? extends T> ctor = clazz.getConstructor(long.class);
                objectOut.value = (T) ctor.newInstance(pb.get(0));
            } catch (Exception e) {
                throw new IllegalArgumentException("Not a reflectively instantiable clazz: " + clazz);
            }
        }
        return res;
    }

}
