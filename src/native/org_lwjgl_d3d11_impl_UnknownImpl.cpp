#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>
        
#ifdef __cplusplus
extern "C" {
#endif
    /*
    * Class:     org_lwjgl_d3d11_impl_UnknownImpl
    * Method:    Release0
    * Signature: (J)V
    */
    JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_UnknownImpl_Release0
        (JNIEnv * env, jclass clazz, jlong thisPtr) {
        IUnknown* obj = (IUnknown*)(intptr_t)thisPtr;
        obj->Release();
    }

    /*
    * Class:     org_lwjgl_d3d11_impl_UnknownImpl
    * Method:    QueryInterface0
    * Signature: (JJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_UnknownImpl_QueryInterface0
        (JNIEnv * env, jclass clazz, jlong thisPtr, jlong guidPtr, jlong objectOutPtr) {
        IUnknown* obj = (IUnknown*)(intptr_t)thisPtr;
        const IID* guid = (IID*)(intptr_t)guidPtr;
        void** outPtr = (void**)(intptr_t)objectOutPtr;
        return obj->QueryInterface(*guid, outPtr);
    }
#ifdef __cplusplus
}
#endif
