package org.lwjgl.d3d11.impl;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.IUnknown;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.system.MemoryUtil;

/**
 * Declarations of the native methods for {@link IUnknown}.
 * 
 * @author kai
 *
 */
public class UnknownImpl extends NativeObjectImpl implements IUnknown {

    static {
        Sys.touch();
    }

    public UnknownImpl(long ptr) {
        super(ptr);
    }

    /**
     * Release the COM instance with the given pointer.
     * 
     * @param ptr
     */
    public static final native void nRelease(long thisPtr);

    public static final native long nQueryInterface(long thisPtr, long guidPtr, long objectOutPtr);

    @Override
    public void Release() {
        nRelease(ptr);
    }

    @Override
    public <T> long QueryInterface(Class<? extends T> clazz, Out<T> objectOut) {
        try {
            PointerBuffer pb = BufferPool.pointerBuffer(1);
            Field uuidField = clazz.getDeclaredField("__uuid");
            GUID uuid = (GUID) uuidField.get(null);
            long res = nQueryInterface(ptr, MemoryUtil.memAddressSafe(uuid.bb), MemoryUtil.memAddress(pb));
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
