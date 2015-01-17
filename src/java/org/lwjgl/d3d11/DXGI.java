package org.lwjgl.d3d11;

public interface DXGI {

    int DXGI_USAGE_SHADER_INPUT = (1 << (0 + 4));
    int DXGI_USAGE_RENDER_TARGET_OUTPUT = (1 << (1 + 4));
    int DXGI_USAGE_BACK_BUFFER = (1 << (2 + 4));
    int DXGI_USAGE_SHARED = (1 << (3 + 4));
    int DXGI_USAGE_READ_ONLY = (1 << (4 + 4));
    int DXGI_USAGE_DISCARD_ON_PRESENT = (1 << (5 + 4));
    int DXGI_USAGE_UNORDERED_ACCESS = (1 << (6 + 4));

    int DXGI_MWA_NO_WINDOW_CHANGES = (1 << 0);
    int DXGI_MWA_NO_ALT_ENTER = (1 << 1);
    int DXGI_MWA_NO_PRINT_SCREEN = (1 << 2);
    int DXGI_MWA_VALID = (0x7);

}
