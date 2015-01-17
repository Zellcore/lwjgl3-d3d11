package org.lwjgl.d3d11;

import java.nio.ByteBuffer;

public interface ID3DBlob extends IUnknown {

    ByteBuffer GetBufferPointer();

}
