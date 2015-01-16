package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

public class DXGISwapChainDesc {

    public static final int SIZEOF = DXGIModeDesc.SIZEOF
            + DXGISampleDesc.SIZEOF + 2 * 4 + 8 + 1 + 2 * 4;

    DXGIModeDesc BufferDesc = new DXGIModeDesc();
    DXGISampleDesc SampleDesc = new DXGISampleDesc();
    int BufferUsage;
    int BufferCount;
    long OutputWindow;
    boolean Windowed;
    DXGISwapEffect SwapEffect = null;
    int Flags = 0;

    public void writeInto(ByteBuffer swapChainDescBuffer) {

    }

}
