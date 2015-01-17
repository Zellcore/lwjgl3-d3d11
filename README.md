lwjgl3-d3d11
====
Direct3D 11 binding for LWJGL 3.

This repository will contain a Direct3D 11 binding for LWJGL 3.

Mapping Direct3D 11 classes
----
Since Direct3D is meant to be object-oriented and to be used with C++, I will follow that path and map the C++ classes to Java classes and interfaces as closely as possible.
For the time being, I will not use the Kotlin generator language provided by LWJGL 3, since I want to first get a feeling for what it would take to map an object-oriented API such as Direct3D to Java.

Naming conventions
----
All structs, classes and enums in the D3D11 C++ API will keep their names in their Java pendants. Also I want to keep the method names and the names like they are in the original C++ API with CamelCase. That will also allow people to easily move from C++ to Java/LWJGL3 (and vice versa).

Status
----
The first demo (Tutorial01) of the [Direct3D Tutorial Win32 Sample](https://code.msdn.microsoft.com/windowsdesktop/Direct3D-Tutorial-Win32-829979ef) is successfully [ported](https://github.com/httpdigest/lwjgl3-d3d11/blob/master/test/org/lwjgl/d3d11/Tutorial01.java) to LWJGL 3 with Direct3D 11.
