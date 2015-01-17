package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.d3d11.GUID;
import org.lwjgl.system.MemoryUtil;

public class COM {

    static {
        Sys.touch();
    }

    public static final native void n__uuidof_IDXGIDevice(long uuidOutPtr);

    public static final native void n__uuidof_IDXGIFactory1(long uuidOutPtr);

    public static final native void n__uuidof_IDXGIFactory2(long uuidOutPtr);

    public static final native void n__uuidof_ID3D11Device1(long uuidOutPtr);

    public static final native void n__uuidof_ID3D11DeviceContext1(long uuidOutPtr);

    public static final native void n__uuidof_IDXGISwapChain(long uuidOutPtr);

    public static final native void n__uuidof_ID3D11Texture2D(long uuidOutPtr);

    public static GUID __uuidof_IDXGIDevice() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_IDXGIDevice(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_IDXGIFactory1() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_IDXGIFactory1(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_IDXGIFactory2() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_IDXGIFactory2(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_ID3D11Device1() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_ID3D11Device1(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_ID3D11DeviceContext1() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_ID3D11DeviceContext1(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_IDXGISwapChain() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_IDXGISwapChain(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

    public static GUID __uuidof_ID3D11Texture2D() {
        ByteBuffer bb = GUID.malloc();
        n__uuidof_ID3D11Texture2D(MemoryUtil.memAddress(bb));
        return new GUID(bb);
    }

}
