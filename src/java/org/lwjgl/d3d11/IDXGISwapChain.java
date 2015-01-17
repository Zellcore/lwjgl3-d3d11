package org.lwjgl.d3d11;

public interface IDXGISwapChain extends IDXGIDeviceSubObject {

    long Present(int syncInterval, int flags);

    <T> long GetBuffer(int buffer, Class<? extends T> clazz, Out<T> bufferOut);

}
