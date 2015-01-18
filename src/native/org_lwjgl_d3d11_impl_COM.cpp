#include <d3d11_1.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

/*
* Class:     org_lwjgl_d3d11_impl_COM
* Method:    n__uuidof_IDXGIDevice
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1IDXGIDevice
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(IDXGIDevice);
}

/*
 * Class:     org_lwjgl_d3d11_impl_COM
 * Method:    n__uuidof_IDXGIFactory1
 * Signature: (J)V
 */
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1IDXGIFactory1
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(IDXGIFactory1);
}

/*
* Class:     org_lwjgl_d3d11_impl_COM
* Method:    n__uuidof_IDXGIFactory2
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1IDXGIFactory2
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(IDXGIFactory2);
}

/*
* Class:     org_lwjgl_d3d11_impl_COM
* Method:    n__uuidof_ID3D11Device1
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1ID3D11Device1
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(ID3D11Device1);
}

/*
* Class:     org_lwjgl_d3d11_impl_COM
* Method:    n__uuidof_ID3D11DeviceContext1
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1ID3D11DeviceContext1
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(ID3D11DeviceContext1);
}

/*
* Class:     org_lwjgl_d3d11_impl_COM
* Method:    n__uuidof_IDXGISwapChain
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1IDXGISwapChain
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(IDXGISwapChain);
}

/*
* Class:     org_lwjgl_d3d11_impl_COM
* Method:    n__uuidof_ID3D11Texture2D
* Signature: (J)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_COM_n_1_1uuidof_1ID3D11Texture2D
(JNIEnv * env, jclass clazz, jlong guidOutPtr) {
    GUID* guidPtr = (GUID*)(intptr_t)guidOutPtr;
    *guidPtr = __uuidof(ID3D11Texture2D);
}
