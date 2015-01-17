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

#ifdef __cplusplus
}
#endif
