package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.GUID;
import org.lwjgl.d3d11.ID3D11Device1;

public class D3D11Device1Impl extends D3D11DeviceImpl implements ID3D11Device1 {

    public static final GUID __uuid = COM.__uuidof_ID3D11Device1();

    public D3D11Device1Impl(long ptr) {
        super(ptr);
    }

}
