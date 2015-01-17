package org.lwjgl.d3d11.impl;

public class Sys {
    
    static {
        Runtime.getRuntime().loadLibrary("d3d11binding");
    }

    public static void touch() {
    }

}
