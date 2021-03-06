#include <d3d11_1.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>


/*
* Class:     org_lwjgl_d3d11_impl_D3DBlobImpl
* Method:    nGetBufferPointer
* Signature: (J)J
*/
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3DBlobImpl_nGetBufferPointer
(JNIEnv * env, jclass clazz, jlong thisPtr) {
    ID3DBlob* blob = (ID3DBlob*)(intptr_t)thisPtr;
    return (jlong)(intptr_t)blob->GetBufferPointer();
}

/*
 * Class:     org_lwjgl_d3d11_impl_D3DBlobImpl
 * Method:    nGetBufferSize
 * Signature: (J)I
 */
extern "C" JNIEXPORT jint JNICALL Java_org_lwjgl_d3d11_impl_D3DBlobImpl_nGetBufferSize
(JNIEnv * env, jclass clazz, jlong thisPtr) {
    ID3DBlob* blob = (ID3DBlob*)(intptr_t)thisPtr;
    return (jint)blob->GetBufferSize();
}
