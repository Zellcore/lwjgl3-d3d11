package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.ID3DInclude;
import org.lwjgl.PointerBuffer;
import org.lwjgl.d3d11.D3D_SHADER_MACRO;
import org.lwjgl.d3d11.ID3DBlob;
import org.lwjgl.d3d11.Out;
import org.lwjgl.d3d11.winerror;
import org.lwjgl.system.MemoryUtil;

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

    public static final native int nD3DCompileFromFile(long fileNamePtr, long nullTerminatedDefinesPtr,
            long includePtr, long entryPointPtr, long targetPtr, int flags1, int flags2, long codeOutPtr,
            long errorMsgsOutPtr);

    public static int D3DCompileFromFile(CharSequence fileName, D3D_SHADER_MACRO[] defines, ID3DInclude include,
            CharSequence entryPoint, CharSequence target, int flags1, int flags2, Out<ID3DBlob> codeOut,
            Out<ID3DBlob> errorMsgsOut) {
        ByteBuffer fileNameBuffer = MemoryUtil.memEncodeUTF8(fileName, true);
        ByteBuffer entryPointBuffer = MemoryUtil.memEncodeUTF8(entryPoint, true);
        ByteBuffer targetBuffer = MemoryUtil.memEncodeUTF8(target, true);
        PointerBuffer definesPb = null;
        if (defines != null) {
            definesPb = BufferUtils.createPointerBuffer(defines.length * 2 + 2);
            for (int i = 0; i < defines.length; i++) {
                ByteBuffer defineNameBuffer = MemoryUtil.memEncodeUTF8(defines[i].Name, true);
                ByteBuffer defineDefinitionBuffer = MemoryUtil.memEncodeUTF8(defines[i].Definition, true);
                definesPb.put(i, MemoryUtil.memAddress(defineNameBuffer));
                definesPb.put(i, MemoryUtil.memAddress(defineDefinitionBuffer));
            }
            /* Terminating (NULL, NULL) element!!! */
            definesPb.put(defines.length, 0L).put(defines.length + 1, 0L);
        }
        D3DIncludeImpl includeImpl = (D3DIncludeImpl) include;
        long includePtr = includeImpl != null ? includeImpl.ptr : 0L;
        PointerBuffer codeOutPb = BufferUtils.createPointerBuffer(1);
        PointerBuffer errorMsgsOutPb = errorMsgsOut != null ? BufferUtils.createPointerBuffer(1) : null;
        int res = nD3DCompileFromFile(MemoryUtil.memAddress(fileNameBuffer), MemoryUtil.memAddressSafe(definesPb),
                includePtr, MemoryUtil.memAddress(entryPointBuffer), MemoryUtil.memAddress(targetBuffer), flags1,
                flags2, MemoryUtil.memAddress(codeOutPb), MemoryUtil.memAddressSafe(errorMsgsOutPb));
        if (winerror.SUCCEEDED(res)) {
            D3DBlobImpl codeOutBlob = new D3DBlobImpl(codeOutPb.get(0));
            codeOut.value = codeOutBlob;
            if (errorMsgsOut != null) {
                D3DBlobImpl errorMsgsOutBlob = null;
                long addr = errorMsgsOutPb.get(0);
                if (addr != 0L) {
                    errorMsgsOutBlob = new D3DBlobImpl(addr);
                }
                errorMsgsOut.value = errorMsgsOutBlob;
            }
        }
        return res;
    }

}
