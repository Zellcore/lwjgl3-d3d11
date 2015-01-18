#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

/*
* Class:     org_lwjgl_d3d11_impl_UnknownImpl
* Method:    nRelease
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_UnknownImpl_nRelease
(JNIEnv * env, jclass clazz, jlong thisPtr) {
    IUnknown* obj = (IUnknown*)(intptr_t)thisPtr;
    obj->Release();
}

/*
* Class:     org_lwjgl_d3d11_impl_UnknownImpl
* Method:    nQueryInterface
* Signature: (JJ)J
*/
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_UnknownImpl_nQueryInterface
(JNIEnv * env, jclass clazz, jlong thisPtr, jlong guidPtr, jlong objectOutPtr) {
    IUnknown* obj = (IUnknown*)(intptr_t)thisPtr;
    const IID* guid = (IID*)(intptr_t)guidPtr;
    void** outPtr = (void**)(intptr_t)objectOutPtr;
    return obj->QueryInterface(*guid, outPtr);
}
