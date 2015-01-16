package org.lwjgl.d3d11.impl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class Memory {

    static ByteBuffer buffer(int capacity) {
        return ByteBuffer.allocateDirect(capacity).order(
                ByteOrder.nativeOrder());
    }

}
