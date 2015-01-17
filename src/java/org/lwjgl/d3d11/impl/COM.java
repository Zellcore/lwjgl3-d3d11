package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.d3d11.GUID;
import org.lwjgl.system.MemoryUtil;

public class COM {

    static {
        Sys.touch();
    }

    public static final native void __uuidof_IDXGIDevice0(long uuidOutPtr);

    public static final native void __uuidof_IDXGIFactory10(long uuidOutPtr);

    public static GUID __uuidof_IDXGIDevice() {
        ByteBuffer bb = GUID.malloc();
        __uuidof_IDXGIDevice0(MemoryUtil.memAddressSafe(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_IDXGIFactory1() {
        ByteBuffer bb = GUID.malloc();
        __uuidof_IDXGIFactory10(MemoryUtil.memAddressSafe(bb));
        return new GUID(bb);
    }

}
