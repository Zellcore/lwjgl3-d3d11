#include "jniObjects.h"

jclass D3D11_INPUT_ELEMENT_DESC_class;
jfieldID D3D11_INPUT_ELEMENT_DESC_SemanticName;
jfieldID D3D11_INPUT_ELEMENT_DESC_SemanticIndex;
jfieldID D3D11_INPUT_ELEMENT_DESC_Format;
jfieldID D3D11_INPUT_ELEMENT_DESC_InputSlot;
jfieldID D3D11_INPUT_ELEMENT_DESC_AlignedByteOffset;
jfieldID D3D11_INPUT_ELEMENT_DESC_InputSlotClass;
jfieldID D3D11_INPUT_ELEMENT_DESC_InstanceDataStepRate;

jclass DXGI_FORMAT_class;
jfieldID DXGI_FORMAT_value;

jclass D3D11_INPUT_CLASSIFICATION_class;
jfieldID D3D11_INPUT_CLASSIFICATION_value;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved) {
    JNIEnv* env;
    if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_1) != JNI_OK) {
        return -1;
    }

    jclass clazz = env->FindClass("org/lwjgl/d3d11/D3D11_INPUT_ELEMENT_DESC");
    D3D11_INPUT_ELEMENT_DESC_class = (jclass)env->NewGlobalRef(clazz);
    D3D11_INPUT_ELEMENT_DESC_SemanticName = env->GetFieldID(clazz, "SemanticName", "Ljava/lang/String;");
    D3D11_INPUT_ELEMENT_DESC_SemanticIndex = env->GetFieldID(clazz, "SemanticIndex", "I");
    D3D11_INPUT_ELEMENT_DESC_Format = env->GetFieldID(clazz, "Format", "Lorg/lwjgl/d3d11/DXGI_FORMAT;");
    D3D11_INPUT_ELEMENT_DESC_InputSlot = env->GetFieldID(clazz, "InputSlot", "I");
    D3D11_INPUT_ELEMENT_DESC_AlignedByteOffset = env->GetFieldID(clazz, "AlignedByteOffset", "I");
    D3D11_INPUT_ELEMENT_DESC_InputSlotClass = env->GetFieldID(clazz, "InputSlotClass", "Lorg/lwjgl/d3d11/D3D11_INPUT_CLASSIFICATION;");
    D3D11_INPUT_ELEMENT_DESC_InstanceDataStepRate = env->GetFieldID(clazz, "InstanceDataStepRate", "I");

    clazz = env->FindClass("org/lwjgl/d3d11/DXGI_FORMAT");
    DXGI_FORMAT_class = (jclass)env->NewGlobalRef(clazz);
    DXGI_FORMAT_value = env->GetFieldID(clazz, "value", "I");

    clazz = env->FindClass("org/lwjgl/d3d11/D3D11_INPUT_CLASSIFICATION");
    D3D11_INPUT_CLASSIFICATION_class = (jclass)env->NewGlobalRef(clazz);
    D3D11_INPUT_CLASSIFICATION_value = env->GetFieldID(clazz, "value", "I");

    return JNI_VERSION_1_1;
}
