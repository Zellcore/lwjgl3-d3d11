package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.ID3D11Texture2D;

public class D3D11Texture2DImpl extends D3D11ResourceImpl implements ID3D11Texture2D {

    public static final GUID __uuid = COM.__uuidof_ID3D11Texture2D();

    public D3D11Texture2DImpl(long ptr) {
        super(ptr);
    }

}
