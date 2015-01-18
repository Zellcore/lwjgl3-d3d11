package org.lwjgl.d3d11.impl;

import java.security.AccessController;
import java.security.PrivilegedAction;

import org.lwjgl.LWJGLUtil;

public class Sys {

    private static final String D3DCOMPILER_LIBRARY_NAME = System.getProperty("org.lwjgl-d3d11.d3dcompiler.libname",
            "D3DCompiler_47");
    private static final String JNI_LIBRARY_NAME = System.getProperty("org.lwjgl-d3d11.libname", "lwjgl-d3d11");

    static {
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {
                LWJGLUtil.loadLibrarySystem(D3DCOMPILER_LIBRARY_NAME);
                LWJGLUtil.loadLibrarySystem(JNI_LIBRARY_NAME);
                return null;
            }
        });
    }

    public static void touch() {
    }

}
