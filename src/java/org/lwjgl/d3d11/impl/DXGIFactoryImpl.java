package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.IDXGIFactory;

public class DXGIFactoryImpl extends DXGIObjectImpl implements IDXGIFactory {

    static {
        Sys.touch();
    }

    public DXGIFactoryImpl(long ptr) {
        super(ptr);
    }

    public static final native long nMakeWindowAssociation(long thisPtr, long hwnd, int dxgiMwa);

    @Override
    public long MakeWindowAssociation(long hwnd, int dxgiMwa) {
        return nMakeWindowAssociation(ptr, hwnd, dxgiMwa);
    }

}
