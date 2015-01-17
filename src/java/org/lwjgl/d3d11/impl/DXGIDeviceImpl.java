package org.lwjgl.d3d11.impl;

import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.IDXGIAdapter;
import org.lwjgl.d3d11.IDXGIDevice;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.d3d11.util.BufferPool;
import org.lwjgl.system.MemoryUtil;

public class DXGIDeviceImpl extends DXGIObjectImpl implements IDXGIDevice {

    static {
        Sys.touch();
    }

    public static final GUID __uuid = COM.__uuidof_IDXGIDevice();

    public DXGIDeviceImpl(long ptr) {
        super(ptr);
    }

    public static final native long nGetAdapter(long thisPtr, long adapterOutPtr);

    @Override
    public long GetAdapter(Out<IDXGIAdapter> adapterOut) {
        PointerBuffer pb = BufferPool.pointerBuffer(1);
        long res = nGetAdapter(ptr, MemoryUtil.memAddressSafe(pb));
        if (winerror.SUCCEEDED(res)) {
            IDXGIAdapter adapter = new DXGIAdapterImpl(pb.get(0));
            adapterOut.value = adapter;
        }
        return res;
    }

}
