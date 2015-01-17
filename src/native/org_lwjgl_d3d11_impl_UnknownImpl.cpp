#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>
        
#ifdef __cplusplus
extern "C" {
#endif
#undef org_lwjgl_d3d11_impl_D3D11_D3D11_SDK_VERSION
#define org_lwjgl_d3d11_impl_D3D11_D3D11_SDK_VERSION 7L
    /*
    * Class:     org_lwjgl_d3d11_impl_UnknownImpl
    * Method:    release0
    * Signature: (J)V
    */
    JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_UnknownImpl_release0
        (JNIEnv * env, jclass clazz, jlong ptr) {
        IUnknown* obj = (IUnknown*)(intptr_t)ptr;
        obj->Release();
    }
#ifdef __cplusplus
}
#endif
