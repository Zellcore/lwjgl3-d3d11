#include <d3d11_1.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    /*
     * Class:     org_lwjgl_d3d11_impl_DXGIFactoryImpl
    * Method:    nMakeWindowAssociation
    * Signature: (JJI)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGIFactoryImpl_nMakeWindowAssociation
        (JNIEnv * env, jclass clazz, jlong thisPtr, jlong hwnd, jint flags) {
        IDXGIFactory* factory = (IDXGIFactory*)(intptr_t)thisPtr;
        HWND hWnd = (HWND)hwnd;
        UINT Flags = (UINT)flags;
        return (jlong) factory->MakeWindowAssociation(hWnd, Flags);
    }

    /*
    * Class:     org_lwjgl_d3d11_impl_DXGIFactoryImpl
    * Method:    nCreateSwapChain
    * Signature: (JJJJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGIFactoryImpl_nCreateSwapChain
        (JNIEnv * env, jclass clazz, jlong thisPtr, jlong devicePtr, jlong swapChainDescPtr, jlong swapChainOutPtr) {
        IDXGIFactory* factory = (IDXGIFactory*)(intptr_t)thisPtr;
        IUnknown* device = (IUnknown*)(intptr_t)devicePtr;
        DXGI_SWAP_CHAIN_DESC* swapChainDesc = (DXGI_SWAP_CHAIN_DESC*)(intptr_t)swapChainDescPtr;
        IDXGISwapChain** swapChain = (IDXGISwapChain**)(intptr_t)swapChainOutPtr;
        return (jlong)factory->CreateSwapChain(device, swapChainDesc, swapChain);
    }

#ifdef __cplusplus
}
#endif
