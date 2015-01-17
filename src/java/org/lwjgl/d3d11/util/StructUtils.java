package org.lwjgl.d3d11.util;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

import org.lwjgl.d3d11.Struct;
import org.lwjgl.d3d11.annotation.Pointer;

public class StructUtils {

    private static void writeField(Struct struct, Field field, ByteBuffer bb)
            throws IllegalArgumentException, IllegalAccessException {
        Type type = field.getGenericType();
        if (type == int.class) {
            int val = field.getInt(struct);
            bb.putInt(val);
        } else if (type == long.class) {
            long val = field.getLong(struct);
            if (org.lwjgl.Pointer.POINTER_SIZE == 8) {
                bb.putLong(val);
            } else {
                bb.putInt((int) val);
            }
        } else if (type == boolean.class) {
            byte val = field.getBoolean(struct) ? (byte) 1 : 0;
            bb.put(val);
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (!Struct.class.isAssignableFrom(clazz)) {
                throw new IllegalStateException("Cannot write: " + clazz);
            }
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
        }
    }

    public static void write(Struct struct, ByteBuffer bb) {
        Class<?> clazz = struct.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            try {
                writeField(struct, f, bb);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int sizeof(Field field) {
        Type type = field.getGenericType();
        if (type == int.class) {
            return 4;
        } else if (type == long.class) {
            /*
             * long is likely to be 8 byte on a 64-bit system and 4 byte on a
             * 32-bit system, so it's the same as POINTER_SIZE!
             */
            return org.lwjgl.Pointer.POINTER_SIZE;
        } else if (type == boolean.class) {
            return 1;
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (!Struct.class.isAssignableFrom(clazz)) {
                throw new IllegalStateException("Cannot write: " + clazz);
            }
            boolean hasPtrAnnot = field.isAnnotationPresent(Pointer.class);
            if (hasPtrAnnot) {
                return org.lwjgl.Pointer.POINTER_SIZE;
            } else {
                return sizeof(clazz);
            }
        }
        throw new IllegalArgumentException("Unknown size for: " + field);
    }

    public static int sizeof(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        int size = 0;
        for (Field f : fields) {
            size += sizeof(f);
        }
        return size;
    }

}
