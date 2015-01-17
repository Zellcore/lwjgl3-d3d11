package org.lwjgl.d3d11;

import org.lwjgl.d3d11.impl.COM;

public interface IDXGISwapChain extends IDXGIDeviceSubObject {

    static GUID __uuid = COM.__uuidof_IDXGISwapChain();

    long Present(int syncInterval, int flags);

    <T> long GetBuffer(int buffer, GUID uuid, Class<? extends T> clazz, Out<T> bufferOut);

}
