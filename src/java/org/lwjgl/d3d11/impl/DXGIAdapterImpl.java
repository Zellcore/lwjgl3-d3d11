package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.IDXGIAdapter;

public class DXGIAdapterImpl implements IDXGIAdapter {

    public long ptr;

    public DXGIAdapterImpl(long ptr) {
        super();
        this.ptr = ptr;
    }

}
