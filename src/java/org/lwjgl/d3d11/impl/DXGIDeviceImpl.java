package org.lwjgl.d3d11.impl;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.IDXGIAdapter;
import org.lwjgl.d3d11.IDXGIDevice;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.system.MemoryUtil;

public class DXGIDeviceImpl extends DXGIObjectImpl implements IDXGIDevice {

    static {
        Sys.touch();
    }

    public DXGIDeviceImpl(long ptr) {
        super(ptr);
    }

    public static final native long GetAdapter0(long thisPtr, long adapterOutPtr);

    @Override
    public long GetAdapter(Out<IDXGIAdapter> adapterOut) {
        PointerBuffer pb = BufferUtils.createPointerBuffer(1);
        long res = GetAdapter0(ptr, MemoryUtil.memAddressSafe(pb));
        if (winerror.SUCCEEDED(res)) {
            IDXGIAdapter adapter = new DXGIAdapterImpl(pb.get(0));
            adapterOut.value = adapter;
        }
        return res;
    }

}
