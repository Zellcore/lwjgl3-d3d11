package org.lwjgl.d3d11;

public interface IDXGIObject extends IUnknown {

    <T> long GetParent(Class<? extends T> clazz, Out<T> objectOut);

}
