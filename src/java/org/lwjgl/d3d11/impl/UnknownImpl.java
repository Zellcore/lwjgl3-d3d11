package org.lwjgl.d3d11.impl;

import org.lwjgl.d3d11.IUnknown;

/**
 * Declarations of the native methods for {@link IUnknown}.
 * 
 * @author kai
 *
 */
public class UnknownImpl {

    /**
     * Release the COM instance with the given pointer.
     * 
     * @param ptr
     */
    public static final native void release0(long ptr);

}
