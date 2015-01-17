package org.lwjgl.d3d11.util;

import java.nio.ByteBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.PointerBuffer;

/**
 * Really simple ByteBuffer pool.
 *
 */
public class BufferPool {

    private static ByteBuffer buffer;

    private static PointerBuffer pointerBuffer;

    public static ByteBuffer byteBuffer(int minCapacity) {
        if (buffer == null || buffer.capacity() < minCapacity) {
            buffer = BufferUtils.createByteBuffer(minCapacity);
        } else {
            buffer.clear();
        }
        return buffer;
    }

    public static PointerBuffer pointerBuffer(int minCapacity) {
        if (pointerBuffer == null || pointerBuffer.capacity() < minCapacity) {
            pointerBuffer = BufferUtils.createPointerBuffer(minCapacity);
        } else {
            pointerBuffer.clear();
        }
        return pointerBuffer;
    }

}
