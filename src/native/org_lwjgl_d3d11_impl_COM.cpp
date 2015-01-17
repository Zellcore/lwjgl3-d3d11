#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    /*
    * Class:     org_lwjgl_d3d11_impl_COM
    * Method:    __uuidof_IDXGIDevice0
    * Signature: (J)V
    */
    JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM__1_1uuidof_1IDXGIDevice0
        (JNIEnv * env, jclass clazz, jlong guidOutPtr) {
        GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
        *guidPtr = __uuidof(IDXGIDevice);
    }

    /*
     * Class:     org_lwjgl_d3d11_impl_COM
    * Method:    __uuidof_IDXGIFactory10
    * Signature: (J)V
    */
    JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM__1_1uuidof_1IDXGIFactory10
        (JNIEnv * env, jclass clazz, jlong guidOutPtr) {
        GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
        *guidPtr = __uuidof(IDXGIFactory1);
    }

#ifdef __cplusplus
}
#endif
