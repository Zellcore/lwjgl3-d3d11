#include <d3d11_1.h>
#include <d3dcompiler.h>

#include <string>

#pragma comment(lib, "d3d11.lib")
#pragma comment(lib, "d3dcompiler.lib")

#include <jni.h>

#ifdef __cplusplus
extern "C" {
#endif
    /*
     * Class:     org_lwjgl_d3d11_impl_d3dcompiler
    * Method:    nD3DCompileFromFile
    * Signature: (JJJJJIIJJJJ)I
    */
    JNIEXPORT jint JNICALL Java_org_lwjgl_d3d11_impl_d3dcompiler_nD3DCompileFromFile
        (JNIEnv * env, jclass clazz, jlong fileNamePtr, jlong definesPtr, jlong includePtr, jlong entryPointPtr, 
        jlong targetPtr, jint flags1, jint flags2,
        jlong codeOutPtr, jlong errorMsgsOutPtr) {
        const char* fileNameCStr = (const char*)(intptr_t)fileNamePtr;
        size_t fileNameSize = strlen(fileNameCStr) + 1;
        wchar_t* wstr = (wchar_t*) malloc(sizeof(wchar_t) * fileNameSize);
        mbstowcs(wstr, fileNameCStr, fileNameSize);
        const D3D_SHADER_MACRO* defines = (const D3D_SHADER_MACRO*)(intptr_t)definesPtr;
        ID3DInclude* include = (ID3DInclude*)includePtr;
        LPCSTR entryPoint = (LPCSTR)(intptr_t)entryPointPtr;
        LPCSTR target = (LPCSTR)(intptr_t)targetPtr;
        UINT Flags1 = (UINT)flags1;
        UINT Flags2 = (UINT)flags2;
        ID3DBlob** codeOut = (ID3DBlob**)(intptr_t)codeOutPtr;
        ID3DBlob** errorMsgsOut = (ID3DBlob**)(intptr_t)errorMsgsOutPtr;
        HRESULT res = D3DCompileFromFile(wstr, defines, include, entryPoint, target, Flags1, Flags2, codeOut, errorMsgsOut);
        free(wstr);
        return (jint)res;
    }

#ifdef __cplusplus
}
#endif
