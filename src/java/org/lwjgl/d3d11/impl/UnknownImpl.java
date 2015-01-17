package org.lwjgl.d3d11.impl;

import java.lang.reflect.Constructor;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.IUnknown;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
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
    public static final native void Release0(long thisPtr);

    public static final native long QueryInterface0(long thisPtr, long guidPtr, long objectOutPtr);

    @Override
    public void Release() {
        Release0(ptr);
    }

    @Override
    public <T> long QueryInterface(GUID riid, Class<? extends T> clazz, Out<T> objectOut) {
        PointerBuffer pb = BufferUtils.createPointerBuffer(1);
        long res = QueryInterface0(ptr, MemoryUtil.memAddressSafe(riid.bb), MemoryUtil.memAddressSafe(pb));
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
