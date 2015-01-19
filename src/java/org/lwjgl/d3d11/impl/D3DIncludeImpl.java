package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.ID3DInclude;
import org.lwjgl.d3d11.ID3DIncludeCloseCallback;
import org.lwjgl.d3d11.ID3DIncludeOpenCallback;

public class D3DIncludeImpl extends UnknownImpl implements ID3DInclude {

    public ID3DIncludeOpenCallback open;

    public ID3DIncludeCloseCallback close;

    public D3DIncludeImpl(long ptr) {
        super(ptr);
    }

    @Override
    public ID3DIncludeOpenCallback Open() {
        return open;
    }

    @Override
    public ID3DIncludeCloseCallback Close() {
        return close;
    }

}
