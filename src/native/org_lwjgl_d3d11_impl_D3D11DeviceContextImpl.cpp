#include <d3d11_1.h>

#pragma comment(lib, "d3d11.lib")

#include <jni.h>


/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceContextImpl
* Method:    nClearRenderTargetView
* Signature: (JJJ)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceContextImpl_nClearRenderTargetView
(JNIEnv * env, jclass clazz, jlong thisPtr, jlong renderTargetViewPtr, jlong colorPtr) {
    ID3D11DeviceContext* deviceContext = (ID3D11DeviceContext*)(intptr_t)thisPtr;
    ID3D11RenderTargetView* renderTargetView = (ID3D11RenderTargetView*)(intptr_t)renderTargetViewPtr;
    const FLOAT* color = (const FLOAT*)(intptr_t)colorPtr;
    deviceContext->ClearRenderTargetView(renderTargetView, color);
}

/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceContextImpl
* Method:    nOMSetRenderTargets
* Signature: (JIJJ)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceContextImpl_nOMSetRenderTargets
(JNIEnv * env, jclass clazz, jlong thisPtr, jint numViews, jlong renderTargetViewsPtr, jlong depthStencilViewPtr) {
    ID3D11DeviceContext* deviceContext = (ID3D11DeviceContext*)(intptr_t)thisPtr;
    UINT NumViews = (UINT)numViews;
    ID3D11RenderTargetView* const* rtvs = (ID3D11RenderTargetView* const*)(intptr_t)renderTargetViewsPtr;
    ID3D11DepthStencilView* dsv = (ID3D11DepthStencilView*)(intptr_t)depthStencilViewPtr;
    deviceContext->OMSetRenderTargets(NumViews, rtvs, dsv);
}

/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceContextImpl
* Method:    nRSSetViewports
* Signature: (JIJ)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceContextImpl_nRSSetViewports
(JNIEnv * env, jclass clazz, jlong thisPtr, jint numViewports, jlong viewportsPtr) {
    ID3D11DeviceContext* deviceContext = (ID3D11DeviceContext*)(intptr_t)thisPtr;
    UINT NumViewports = (UINT)numViewports;
    D3D11_VIEWPORT* viewports = (D3D11_VIEWPORT*)(intptr_t)viewportsPtr;
    deviceContext->RSSetViewports(NumViewports, viewports);
}

/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceContextImpl
* Method:    nIASetInputLayout
* Signature: (JJ)V
*/
extern "C" JNIEXPORT void JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceContextImpl_nIASetInputLayout
(JNIEnv * env, jclass clazz, jlong thisPtr, jlong inputLayoutPtr) {
    ID3D11DeviceContext* deviceContext = (ID3D11DeviceContext*)(intptr_t)thisPtr;
    ID3D11InputLayout* inputLayout = (ID3D11InputLayout*)(intptr_t)inputLayoutPtr;
    deviceContext->IASetInputLayout(inputLayout);
}
