package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3DDriverType;
import org.lwjgl.d3d11.D3DFeatureLevel;
import org.lwjgl.d3d11.DXGISwapChainDesc;
import org.lwjgl.d3d11.ID3D11Device;
import org.lwjgl.d3d11.ID3D11DeviceContext;
import org.lwjgl.d3d11.IDXGIAdapter;
import org.lwjgl.d3d11.IDXGISwapChain;
import org.lwjgl.d3d11.Out;

public class D3D11 {

    public static final int D3D11_SDK_VERSION = 7;

    private static final native long D3D11CreateDeviceAndSwapChain0(
            long adapterPtr, int driverType,
            long softwareRasterizerModuleHandle, int flags,
            IntBuffer featureLevels, int sdkVersion, ByteBuffer swapChainDesc,
            PointerBuffer swapChainOut, PointerBuffer deviceOut,
            IntBuffer featureLevelOut, PointerBuffer immediateContextOut);

    public static long D3D11CreateDeviceAndSwapChain(IDXGIAdapter adapter,
            D3DDriverType driverType, long softwareRasterizerModuleHandle,
            int flags, D3DFeatureLevel[] featureLevels, int sdkVersion,
            DXGISwapChainDesc swapChainDesc, Out<IDXGISwapChain> swapChainOut,
            Out<ID3D11Device> deviceOut, Out<D3DFeatureLevel> featureLevelOut,
            Out<ID3D11DeviceContext> immediateContextOut) {
        IntBuffer featureLevelsBuffer = Memory.buffer(4 * featureLevels.length)
                .asIntBuffer();
        for (int i = 0; i < featureLevels.length; i++) {
            featureLevelsBuffer.put(featureLevels[i].ordinal());
        }
        featureLevelsBuffer.flip();
        ByteBuffer swapChainDescBuffer = BufferUtils
                .createByteBuffer(DXGISwapChainDesc.SIZEOF);
        swapChainDesc.writeInto(swapChainDescBuffer);
        swapChainDescBuffer.flip();
        PointerBuffer swapChainOutBuffer = BufferUtils.createPointerBuffer(1);
        PointerBuffer deviceOutBuffer = BufferUtils.createPointerBuffer(1);
        IntBuffer featureLevelOutBuffer = BufferUtils.createIntBuffer(1);
        PointerBuffer immediateContextOutBuffer = BufferUtils
                .createPointerBuffer(1);
        long ret = D3D11CreateDeviceAndSwapChain0(0L, driverType.ordinal(),
                softwareRasterizerModuleHandle, flags, featureLevelsBuffer,
                sdkVersion, swapChainDescBuffer, swapChainOutBuffer,
                deviceOutBuffer, featureLevelOutBuffer,
                immediateContextOutBuffer);
        IDXGISwapChain swapChain = new DXGISwapChainImpl(
                swapChainOutBuffer.get(0));
        ID3D11Device device = deviceOutBuffer.get(0) != 0L ? new D3D11DeviceImpl(
                deviceOutBuffer.get(0)) : null;
        D3DFeatureLevel featureLevel = D3DFeatureLevel.values()[featureLevelOutBuffer
                .get(0)];
        ID3D11DeviceContext immediateContext = immediateContextOutBuffer.get(0) != 0L ? new D3D11DeviceContextImpl(
                immediateContextOutBuffer.get(0)) : null;
        swapChainOut.set(swapChain);
        deviceOut.set(device);
        featureLevelOut.set(featureLevel);
        immediateContextOut.set(immediateContext);
        return ret;
    }

}
