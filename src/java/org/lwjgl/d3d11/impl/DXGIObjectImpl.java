package org.lwjgl.d3d11.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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
    public <T> long GetParent(Class<? extends T> clazz, Out<T> objectOut) {
        try {
            PointerBuffer pb = BufferPool.pointerBuffer(1);
            Field uuidField = clazz.getDeclaredField("__uuid");
            GUID uuid = (GUID) uuidField.get(null);
            long res = nGetParent(ptr, MemoryUtil.memAddressSafe(uuid.bb), MemoryUtil.memAddress(pb));
            if (winerror.SUCCEEDED(res)) {
                Constructor<? extends T> ctor = clazz.getConstructor(long.class);
                objectOut.value = (T) ctor.newInstance(pb.get(0));
            }
            return res;
        } catch (Exception e) {
            throw new IllegalArgumentException("Not a reflectively instantiable clazz: " + clazz);
        }
    }

}
