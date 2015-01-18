#ifndef _JNI_OBJECTS_
#define _JNI_OBJECTS_

#include <jni.h>

extern jclass D3D11_INPUT_ELEMENT_DESC_class;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_SemanticName;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_SemanticIndex;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_Format;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_InputSlot;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_AlignedByteOffset;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_InputSlotClass;
extern jfieldID D3D11_INPUT_ELEMENT_DESC_InstanceDataStepRate;

extern jclass DXGI_FORMAT_class;
extern jfieldID DXGI_FORMAT_value;

extern jclass D3D11_INPUT_CLASSIFICATION_class;
extern jfieldID D3D11_INPUT_CLASSIFICATION_value;

extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved);

#endif
