#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

/*
* Class:     org_lwjgl_d3d11_impl_DXGIDeviceImpl
* Method:    nGetAdapter
* Signature: (JJ)J
*/
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_DXGIDeviceImpl_nGetAdapter
(JNIEnv * env, jclass clazz, jlong thisPtr, jlong adapterOutPtr) {
    IDXGIDevice* device = (IDXGIDevice*)(intptr_t)thisPtr;
    IDXGIAdapter** adapter = (IDXGIAdapter**)(intptr_t)adapterOutPtr;
    return device->GetAdapter(adapter);
}
