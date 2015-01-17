package org.lwjgl.d3d11;

public class winerror {

    public static final int E_INVALIDARG = _HRESULT_TYPEDEF_(0x80070057);

    public static int _HRESULT_TYPEDEF_(int _sc) {
        return (/* (HRESULT) */_sc);
    }

    public static boolean SUCCEEDED(long val) {
        return val >= 0L;
    }
    
    public static boolean FAILED(long val) {
        return val < 0L;
    }

}
