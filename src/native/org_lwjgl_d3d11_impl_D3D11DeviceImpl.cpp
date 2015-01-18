#include <d3d11_1.h>
#include "jniObjects.h"

#pragma comment(lib, "d3d11.lib")

#include <jni.h>

/*
 * Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
 * Method:    nCreateBuffer
 * Signature: (JJJ)J
 */
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateBuffer
(JNIEnv *, jclass, jlong, jlong, jlong) {
    return -1L;
}

/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
* Method:    nCreateRenderTargetView
* Signature: (JJJJ)J
*/
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateRenderTargetView
(JNIEnv * env, jclass clazz, jlong thisPtr, jlong resourcePtr, jlong descPtr, jlong renderTargetViewOutPtr) {
    ID3D11Device* device = (ID3D11Device*)(intptr_t)thisPtr;
    ID3D11Resource* resource = (ID3D11Resource*)(intptr_t)resourcePtr;
    D3D11_RENDER_TARGET_VIEW_DESC* rtvDesc = (D3D11_RENDER_TARGET_VIEW_DESC*)(intptr_t)descPtr;
    ID3D11RenderTargetView** rtv = (ID3D11RenderTargetView**)(intptr_t)renderTargetViewOutPtr;
    return (jlong)device->CreateRenderTargetView(resource, rtvDesc, rtv);
}

/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
* Method:    nCreateVertexShader
* Signature: (JJIJJ)J
*/
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateVertexShader
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

/*
* Class:     org_lwjgl_d3d11_impl_D3D11DeviceImpl
* Method:    nCreateInputLayout
* Signature: (J[Lorg/lwjgl/d3d11/D3D11_INPUT_ELEMENT_DESC;JIJ)J
*/
extern "C" JNIEXPORT jlong JNICALL Java_org_lwjgl_d3d11_impl_D3D11DeviceImpl_nCreateInputLayout
(JNIEnv * env, jclass clazz, jlong thisPtr, jobjectArray layoutArray,
jlong shaderBytecodePtr, jint bytecodeLength, jlong inputLayoutOutPtr) {
    ID3D11Device* device = (ID3D11Device*)(intptr_t)thisPtr;

    jsize arraySize = env->GetArrayLength(layoutArray);
    D3D11_INPUT_ELEMENT_DESC* descs = (D3D11_INPUT_ELEMENT_DESC*)malloc(sizeof(D3D11_INPUT_ELEMENT_DESC) * arraySize);
    const char** chars = (const char**)malloc(sizeof(char*) * arraySize);
    jstring* jstrings = (jstring*)malloc(sizeof(jstring) * arraySize);
    /* Allocate structs from Java objects */
    for (int i = 0; i < arraySize; i++) {
        jobject e = env->GetObjectArrayElement(layoutArray, i);
        jstring SemanticName = (jstring)env->GetObjectField(e, D3D11_INPUT_ELEMENT_DESC_SemanticName);
        jboolean isCopy;
        const char* SemanticNameCStr = env->GetStringUTFChars(SemanticName, &isCopy);
        chars[i] = SemanticNameCStr;
        jstrings[i] = SemanticName;
        jint SemanticIndex = env->GetIntField(e, D3D11_INPUT_ELEMENT_DESC_SemanticIndex);
        jobject Format = env->GetObjectField(e, D3D11_INPUT_ELEMENT_DESC_Format);
        jint FormatOrdinal = env->GetIntField(Format, DXGI_FORMAT_value);
        jint InputSlot = env->GetIntField(e, D3D11_INPUT_ELEMENT_DESC_InputSlot);
        jint AlignedByteOffset = env->GetIntField(e, D3D11_INPUT_ELEMENT_DESC_AlignedByteOffset);
        jobject InputSlotClass = env->GetObjectField(e, D3D11_INPUT_ELEMENT_DESC_InputSlotClass);
        jint InputSlotClassOrdinal = env->GetIntField(InputSlotClass, D3D11_INPUT_CLASSIFICATION_value);
        jint InstanceDataStepRate = env->GetIntField(e, D3D11_INPUT_ELEMENT_DESC_InstanceDataStepRate);
        descs[i].SemanticName = SemanticNameCStr;
        descs[i].SemanticIndex = SemanticIndex;
        descs[i].Format = (DXGI_FORMAT)FormatOrdinal;
        descs[i].InputSlot = InputSlot;
        descs[i].AlignedByteOffset = AlignedByteOffset;
        descs[i].InputSlotClass = (D3D11_INPUT_CLASSIFICATION)InputSlotClassOrdinal;
        descs[i].InstanceDataStepRate = InstanceDataStepRate;
    }
    const void* shaderBytecode = (const void*)(intptr_t)shaderBytecodePtr;
    ID3D11InputLayout** inputLayout = (ID3D11InputLayout**)(intptr_t)inputLayoutOutPtr;

    HRESULT res = device->CreateInputLayout(descs, arraySize, shaderBytecode, bytecodeLength, inputLayout);

    /* Free strings */
    for (int i = 0; i < arraySize; i++) {
        env->ReleaseStringUTFChars(jstrings[i], chars[i]);
    }
    free(chars);
    free(jstrings);

    return (jlong)res;
}
