package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.ID3DBlob;
import org.lwjgl.d3d11.Out;

public class d3dcompiler {

    static {
        Sys.touch();
    }

    public static final int D3DCOMPILE_DEBUG = (1 << 0);
    public static final int D3DCOMPILE_SKIP_VALIDATION = (1 << 1);
    public static final int D3DCOMPILE_SKIP_OPTIMIZATION = (1 << 2);
    public static final int D3DCOMPILE_PACK_MATRIX_ROW_MAJOR = (1 << 3);
    public static final int D3DCOMPILE_PACK_MATRIX_COLUMN_MAJOR = (1 << 4);
    public static final int D3DCOMPILE_PARTIAL_PRECISION = (1 << 5);
    public static final int D3DCOMPILE_FORCE_VS_SOFTWARE_NO_OPT = (1 << 6);
    public static final int D3DCOMPILE_FORCE_PS_SOFTWARE_NO_OPT = (1 << 7);
    public static final int D3DCOMPILE_NO_PRESHADER = (1 << 8);
    public static final int D3DCOMPILE_AVOID_FLOW_CONTROL = (1 << 9);
    public static final int D3DCOMPILE_PREFER_FLOW_CONTROL = (1 << 10);
    public static final int D3DCOMPILE_ENABLE_STRICTNESS = (1 << 11);
    public static final int D3DCOMPILE_ENABLE_BACKWARDS_COMPATIBILITY = (1 << 12);
    public static final int D3DCOMPILE_IEEE_STRICTNESS = (1 << 13);
    public static final int D3DCOMPILE_OPTIMIZATION_LEVEL0 = (1 << 14);
    public static final int D3DCOMPILE_OPTIMIZATION_LEVEL1 = 0;
    public static final int D3DCOMPILE_OPTIMIZATION_LEVEL2 = ((1 << 14) | (1 << 15));
    public static final int D3DCOMPILE_OPTIMIZATION_LEVEL3 = (1 << 15);
    public static final int D3DCOMPILE_RESERVED16 = (1 << 16);
    public static final int D3DCOMPILE_RESERVED17 = (1 << 17);
    public static final int D3DCOMPILE_WARNINGS_ARE_ERRORS = (1 << 18);
    public static final int D3DCOMPILE_RESOURCES_MAY_ALIAS = (1 << 19);

    public static int D3DCompileFromFile(String fileName, Object object, Object object2, String entryPoint,
            String szShaderModel, int dwShaderFlags, int i, Out<ID3DBlob> blobOut, Out<ID3DBlob> pErrorBlob) {
        return 0;
    }

}
