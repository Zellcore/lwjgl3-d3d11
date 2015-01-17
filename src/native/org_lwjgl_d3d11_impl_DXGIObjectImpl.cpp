#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    /*
     * Class:     org_lwjgl_d3d11_impl_DXGIObjectImpl
     * Method:    nGetParent
    * Signature: (JJJ)J
     */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGIObjectImpl_nGetParent
        (JNIEnv * env, jclass clazz, jlong thisPtr, jlong guidPtr, jlong objectOutPtr) {
        IDXGIObject* obj = (IDXGIObject*)(intptr_t)thisPtr;
        const IID* guid = (IID*)(intptr_t)guidPtr;
        void** outPtr = (void**)(intptr_t)objectOutPtr;
        return obj->GetParent(*guid, outPtr);
    }
#ifdef __cplusplus
}
#endif
