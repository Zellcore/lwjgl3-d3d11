#include <d3d11.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>
        
#ifdef __cplusplus
extern "C" {
#endif
    /*
    * Class:     org_lwjgl_d3d11_impl_D3D11
    * Method:    nD3D11CreateDevice
    * Signature: (JIJIIJIJJJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11_nD3D11CreateDevice
        (JNIEnv * env, jclass clazz, jlong adapterPtr, jint driverType, 
        jlong hmodule_Software, jint flags, jint numFeatureLevels, jlong featureLevelsPtr, 
        jint sdkVersion, jlong ppDeviceAddr, jlong pFeatureLevelAddr, jlong ppImmediateContextAddr) {
        IDXGIAdapter* adapter = (IDXGIAdapter*)(intptr_t)adapterPtr;
        D3D_DRIVER_TYPE dt = (D3D_DRIVER_TYPE)driverType;
        HMODULE software = (HMODULE)hmodule_Software;
        UINT Flags = (UINT)flags;
        const D3D_FEATURE_LEVEL* pFeatureLevels = (const D3D_FEATURE_LEVEL*)(intptr_t)featureLevelsPtr;
        UINT FeatureLevels = (UINT)numFeatureLevels;
        UINT SDKVersion = (UINT)sdkVersion;
        ID3D11Device** ppDevice = (ID3D11Device**)(intptr_t)ppDeviceAddr;
        D3D_FEATURE_LEVEL* pFeatureLevel = (D3D_FEATURE_LEVEL*)(intptr_t)pFeatureLevelAddr;
        ID3D11DeviceContext** ppImmediateContext = (ID3D11DeviceContext **)(intptr_t)ppImmediateContextAddr;
        HRESULT res = D3D11CreateDevice(adapter, dt, software, Flags, pFeatureLevels, FeatureLevels, SDKVersion, ppDevice, pFeatureLevel, ppImmediateContext);
        return (jlong) res;
    }
#ifdef __cplusplus
}
#endif
