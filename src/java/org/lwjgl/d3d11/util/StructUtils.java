package org.lwjgl.d3d11.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

import org.lwjgl.d3d11.Struct;
import org.lwjgl.d3d11.annotation.Pointer;

public class StructUtils {

    private static void writeField(Struct struct, Field field, ByteBuffer bb) throws IllegalArgumentException,
            IllegalAccessException {
        Type type = field.getGenericType();
        if (type == int.class) {
            int val = field.getInt(struct);
            bb.putInt(val);
        } else if (type == float.class) {
            float val = field.getFloat(struct);
            bb.putFloat(val);
        } else if (type == long.class) {
            long val = field.getLong(struct);
            if (org.lwjgl.Pointer.POINTER_SIZE == 8) {
                bb.putLong(val);
            } else {
                bb.putInt((int) val);
            }
        } else if (type == boolean.class) {
            byte val = field.getBoolean(struct) ? (byte) 1 : 0;
            bb.putInt(val);
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isEnum()) {
                Enum<?> e = (Enum<?>) field.get(struct);
                if (e == null) {
                    bb.putInt(0);
                } else {
                    int ordinal = e.ordinal();
                    bb.putInt(ordinal);
                }
            } else if (Struct.class.isAssignableFrom(clazz)) {
                Struct str = (Struct) field.get(struct);
                boolean hasPtrAnnot = field.isAnnotationPresent(Pointer.class);
                if (hasPtrAnnot) {
                    /* Write pointer */
                    throw new UnsupportedOperationException("NYI");
                    // long ptr = str.ptr();
                    // if (org.lwjgl.Pointer.POINTER_SIZE == 4) {
                    // int ptrAsInt = (int) ptr;
                    // bb.putInt(ptrAsInt);
                    // } else {
                    // bb.putLong(ptr);
                    // }
                } else {
                    /* Delegate to that struct */
                    write(str, bb);
                }
            } else {
                throw new IllegalStateException("Cannot write: " + clazz);
            }
        } else {
            throw new UnsupportedOperationException("NYI: " + type);
        }
    }

    public static void write(Struct struct, ByteBuffer bb) {
        Class<?> clazz = struct.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            try {
                writeField(struct, f, bb);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
