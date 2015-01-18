package org.lwjgl.d3d11;

import static org.lwjgl.d3d11.DXGI_FORMAT.*;
import static org.lwjgl.d3d11.DXGI_MODE_SCANLINE_ORDER.*;
import static org.lwjgl.d3d11.DXGI_MODE_SCALING.*;

public class DXGI_MODE_DESC implements Struct {

    public static final int SIZEOF = 28;

    public int Width;
    public int Height;
    public DXGI_RATIONAL RefreshRate = new DXGI_RATIONAL();
    public DXGI_FORMAT Format = DXGI_FORMAT_UNKNOWN;
    public DXGI_MODE_SCANLINE_ORDER ScanlineOrdering = DXGI_MODE_SCANLINE_ORDER_UNSPECIFIED;
    public DXGI_MODE_SCALING Scaling = DXGI_MODE_SCALING_UNSPECIFIED;

}
