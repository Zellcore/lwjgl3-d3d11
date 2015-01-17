#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    /*
    * Class:     org_lwjgl_d3d11_impl_DXGISwapChainImpl
    * Method:    nPresent
    * Signature: (JII)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGISwapChainImpl_nPresent
        (JNIEnv * env, jclass clazz, jlong thisPtr, jint syncInterval, jint flags) {
        IDXGISwapChain* sc = (IDXGISwapChain*)(intptr_t)thisPtr;
        UINT SyncInterval = (UINT)syncInterval;
        UINT Flags = (UINT)flags;
        return (jlong)sc->Present(SyncInterval, Flags);
    }

    /*
    * Class:     org_lwjgl_d3d11_impl_DXGISwapChainImpl
    * Method:    nGetBuffer
    * Signature: (JIJJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGISwapChainImpl_nGetBuffer
        (JNIEnv * env, jclass clazz, jlong thisPtr, jint buffer, jlong guidPtr, jlong surfaceOutPtr) {
        IDXGISwapChain* sc = (IDXGISwapChain*)(intptr_t)thisPtr;
        UINT Buffer = (UINT)buffer;
        const IID* guid = (IID*)(intptr_t)guidPtr;
        void** surfacePtr = (void**)(intptr_t)surfaceOutPtr;
        return (jlong)sc->GetBuffer(Buffer, *guid, surfacePtr);
    }

#ifdef __cplusplus
}
#endif
