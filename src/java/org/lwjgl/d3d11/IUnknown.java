package org.lwjgl.d3d11;

public interface IUnknown {

    void Release();

    <T> long QueryInterface(GUID/* REFIID */riid, Class<? extends T> clazz, Out<T> objectOut);

}
