package org.lwjgl.d3d11;

public class DXGIModeDesc {

    public static final int SIZEOF = 2 * 4 + DXGIRational.SIZEOF + 3 * 4;

    int Width;
    int Height;
    DXGIRational RefreshRate = new DXGIRational();
    DXGIFormat Format = DXGIFormat.DXGI_FORMAT_UNKNOWN;
    DXGIModeScanlineOrder ScanlineOrdering = DXGIModeScanlineOrder.DXGI_MODE_SCANLINE_ORDER_UNSPECIFIED;
    DXGIModeScaling Scaling = DXGIModeScaling.DXGI_MODE_SCALING_UNSPECIFIED;

}
