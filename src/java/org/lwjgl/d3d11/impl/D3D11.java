package org.lwjgl.d3d11.impl;

import static org.lwjgl.d3d11.winerror.*;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3D_DRIVER_TYPE;
import org.lwjgl.d3d11.D3D_FEATURE_LEVEL;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.ID3D11DeviceContext;
import org.lwjgl.d3d11.IDXGIAdapter;
import org.lwjgl.d3d11.Out;
import org.lwjgl.system.MemoryUtil;

public class D3D11 {

    static {
        Sys.touch();
    }

    public static final int D3D11_SDK_VERSION = 7;

    private static final native long nD3D11CreateDevice(long adapterPtr, int driverType, long hmodule_Software,
            int flags, int numFeatureLevels, long featureLevelsPtr, int sdkVersion, long ppDevicePtr,
            long pFeatureLevel, long ppImmediateContextPtr);

    public static long D3D11CreateDevice(IDXGIAdapter pAdapter, D3D_DRIVER_TYPE DriverType, long hmodule_Software,
            int Flags, D3D_FEATURE_LEVEL[] pFeatureLevels, int SDKVersion, Out<ID3D11Device> ppDevice,
            Out<D3D_FEATURE_LEVEL> pFeatureLevel, Out<ID3D11DeviceContext> ppImmediateContext) {
        DXGIAdapterImpl pAdapterImpl = (DXGIAdapterImpl) pAdapter;
        long adapterPtr = pAdapterImpl != null ? pAdapterImpl.ptr : 0L;
        ByteBuffer featureLevelsBuffer = BufferUtils.createByteBuffer(4 * pFeatureLevels.length);
        IntBuffer featureLevelsIntBuffer = featureLevelsBuffer.asIntBuffer();
        for (D3D_FEATURE_LEVEL fl : pFeatureLevels) {
            featureLevelsIntBuffer.put(fl.value);
        }
        PointerBuffer ppDeviceBuffer = BufferUtils.createPointerBuffer(1);
        IntBuffer selectedFeatureLevelBuffer = BufferUtils.createIntBuffer(1);
        PointerBuffer ppImmediateContextBuffer = BufferUtils.createPointerBuffer(1);
        long res = nD3D11CreateDevice(adapterPtr, DriverType.ordinal(), hmodule_Software, Flags,
                featureLevelsBuffer.remaining() / 4, MemoryUtil.memAddressSafe(featureLevelsBuffer), SDKVersion,
                MemoryUtil.memAddressSafe(ppDeviceBuffer), MemoryUtil.memAddressSafe(selectedFeatureLevelBuffer),
                MemoryUtil.memAddressSafe(ppImmediateContextBuffer));
        if (SUCCEEDED(res)) {
            ID3D11Device device = new D3D11DeviceImpl(ppDeviceBuffer.get(0));
            D3D_FEATURE_LEVEL featureLevel = D3D_FEATURE_LEVEL.byValue(selectedFeatureLevelBuffer.get(0));
            ID3D11DeviceContext deviceContext = new D3D11DeviceContextImpl(ppImmediateContextBuffer.get(0));
            ppDevice.value = device;
            pFeatureLevel.value = featureLevel;
            ppImmediateContext.value = deviceContext;
        }
        return res;
    }
}
