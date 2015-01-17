package org.lwjgl.d3d11;

import org.lwjgl.d3d11.impl.COM;

public interface IDXGIDevice extends IDXGIObject {

    /**
     * This is nasty! We need to query the COM implementation's native method for the GUID.
     */
    static GUID __uuid = COM.__uuidof_IDXGIDevice();

    long GetAdapter(Out<IDXGIAdapter> adapter);

}
