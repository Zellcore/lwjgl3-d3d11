#include <d3d11_1.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    /*
     * Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
     * Method:    nCreateBuffer
    * Signature: (JJJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateBuffer
        (JNIEnv *, jclass, jlong, jlong, jlong) {
        return -1L;
    }

    /*
    * Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
    * Method:    nCreateRenderTargetView
    * Signature: (JJJJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateRenderTargetView
        (JNIEnv * env, jclass clazz, jlong thisPtr, jlong resourcePtr, jlong descPtr, jlong renderTargetViewOutPtr) {
        ID3D11Device* device = (ID3D11Device*)(intptr_t)thisPtr;
        ID3D11Resource* resource = (ID3D11Resource*)(intptr_t)resourcePtr;
        D3D11_RENDER_TARGET_VIEW_DESC* rtvDesc = (D3D11_RENDER_TARGET_VIEW_DESC*)(intptr_t)descPtr;
        ID3D11RenderTargetView** rtv = (ID3D11RenderTargetView**)(intptr_t)renderTargetViewOutPtr;
        return (jlong) device->CreateRenderTargetView(resource, rtvDesc, rtv);
    }

    /*
    * Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
    * Method:    nCreateVertexShader
    * Signature: (JJIJJ)J
    */
    JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateVertexShader
        (JNIEnv * env, jclass clazz, jlong thisPtr, jlong shaderBytecodePtr, jint bytecodeLength,
        jlong classLinkagePtr, jlong vertexShaderOutPtr) {
        ID3D11Device* device = (ID3D11Device*)(intptr_t)thisPtr;
        const void* shaderBytecode = (const void*)(intptr_t)shaderBytecodePtr;
        SIZE_T length = (SIZE_T)bytecodeLength;
        ID3D11ClassLinkage* linkage = (ID3D11ClassLinkage*)(intptr_t)classLinkagePtr;
        ID3D11VertexShader** vertexShaderOut = (ID3D11VertexShader**)(intptr_t)vertexShaderOutPtr;
        HRESULT res = device->CreateVertexShader(shaderBytecode, length, linkage, vertexShaderOut);
        return (jlong)res;
    }
#ifdef __cplusplus
}
#endif
