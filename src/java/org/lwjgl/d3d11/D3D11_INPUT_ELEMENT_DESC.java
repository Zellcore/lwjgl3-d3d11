package org.lwjgl.d3d11;

public class D3D11_INPUT_ELEMENT_DESC implements Struct {

    public String SemanticName;
    public int SemanticIndex;
    public DXGI_FORMAT Format;
    public int InputSlot;
    public int AlignedByteOffset;
    public D3D11_INPUT_CLASSIFICATION InputSlotClass;
    public int InstanceDataStepRate;

    public D3D11_INPUT_ELEMENT_DESC() {
    }

    public D3D11_INPUT_ELEMENT_DESC(String semanticName, int semanticIndex, DXGI_FORMAT format, int inputSlot,
            int alignedByteOffset, D3D11_INPUT_CLASSIFICATION inputSlotClass, int instanceDataStepRate) {
        super();
        SemanticName = semanticName;
        SemanticIndex = semanticIndex;
        Format = format;
        InputSlot = inputSlot;
        AlignedByteOffset = alignedByteOffset;
        InputSlotClass = inputSlotClass;
        InstanceDataStepRate = instanceDataStepRate;
    }

}
