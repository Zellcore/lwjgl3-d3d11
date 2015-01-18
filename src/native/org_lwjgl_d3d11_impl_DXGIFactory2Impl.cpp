#include <d3d11_1.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

/*
 * Class:     org_lwjgl_d3d11_impl_DXGIFactory2Impl
 * Method:    nCreateSwapChainForHwnd
 * Signature: (JJJJJJJ)J
 */
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGIFactory2Impl_nCreateSwapChainForHwnd
(JNIEnv * env, jclass clazz, jlong thisPtr, jlong devicePtr, jlong hwnd, jlong swapChainDescPtr,
jlong swapChainFullscreenDescPtr, jlong dxgiOutputPtr, jlong swapChainOutPtr) {
    IDXGIFactory2* factory = (IDXGIFactory2*)(intptr_t)thisPtr;
    IUnknown* pDevice = (IUnknown*)(intptr_t)devicePtr;
    HWND hWnd = (HWND)hwnd;
    const DXGI_SWAP_CHAIN_DESC1 * pDesc = (const DXGI_SWAP_CHAIN_DESC1*)(intptr_t)swapChainDescPtr;
    const DXGI_SWAP_CHAIN_FULLSCREEN_DESC * pFullscreenDesc = (const DXGI_SWAP_CHAIN_FULLSCREEN_DESC*)(intptr_t)swapChainFullscreenDescPtr;
    IDXGIOutput * pRestrictToOutput = (IDXGIOutput *)(intptr_t)dxgiOutputPtr;
    IDXGISwapChain1 ** ppSwapChain = (IDXGISwapChain1 **)(intptr_t)swapChainOutPtr;
    return factory->CreateSwapChainForHwnd(pDevice, hWnd, pDesc, pFullscreenDesc, pRestrictToOutput, ppSwapChain);
}
