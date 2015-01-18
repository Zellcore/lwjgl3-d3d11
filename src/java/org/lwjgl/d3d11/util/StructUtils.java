package org.lwjgl.d3d11.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.nio.ByteBuffer;

import org.lwjgl.d3d11.Struct;
import org.lwjgl.d3d11.annotation.Pointer;

public class StructUtils {

    private static int pad(ByteBuffer bb, int off, int align) {
        int padded = 0;
        while (((off + padded) % align) != 0) {
            padded++;
            bb.put((byte) 0);
        }
        return padded;
    }

    private static int writeField(Struct struct, Field field, ByteBuffer bb, int off) throws IllegalArgumentException,
            IllegalAccessException {
        Type type = field.getGenericType();
        int newOff = off;
        if (type == int.class) {
            newOff += pad(bb, off, 4);
            int val = field.getInt(struct);
            bb.putInt(val);
            newOff += 4;
        } else if (type == float.class) {
            newOff += pad(bb, off, 4);
            float val = field.getFloat(struct);
            bb.putFloat(val);
            newOff += 4;
        } else if (type == long.class) {
            boolean hasPtrAnnot = field.isAnnotationPresent(Pointer.class);
            if (hasPtrAnnot) {
                if (org.lwjgl.Pointer.POINTER_SIZE == 4) {
                    newOff += pad(bb, off, 4);
                    long val = field.getLong(struct);
                    bb.putInt((int) val);
                    newOff += 4;
                } else {
                    newOff += pad(bb, off, 8);
                    long val = field.getLong(struct);
                    bb.putLong(val);
                    newOff += 8;
                }
            } else {
                newOff += pad(bb, off, 8);
                long val = field.getLong(struct);
                bb.putLong(val);
                newOff += 8;
            }
        } else if (type == boolean.class) {
            /* BOOL in MSVC is actually defined as int! */
            newOff += pad(bb, off, 4);
            byte val = field.getBoolean(struct) ? (byte) 1 : 0;
            bb.putInt(val);
            newOff += 4;
        } else if (type instanceof Class) {
            Class<?> clazz = (Class<?>) type;
            if (clazz.isEnum()) {
                Enum<?> e = (Enum<?>) field.get(struct);
                if (e == null) {
                    newOff += pad(bb, off, 4);
                    bb.putInt(0);
                    newOff += 4;
                } else {
                    newOff += pad(bb, off, 4);
                    int ordinal = e.ordinal();
                    bb.putInt(ordinal);
                    newOff += 4;
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
                    newOff = write(str, bb, newOff);
                }
            } else {
                throw new IllegalStateException("Cannot write: " + clazz);
            }
        } else {
            throw new UnsupportedOperationException("NYI: " + type);
        }
        return newOff;
    }

    public static int write(Struct struct, ByteBuffer bb) {
        return write(struct, bb, 0);
    }

    public static int write(Struct struct, ByteBuffer bb, int off) {
        Class<?> clazz = struct.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            try {
                off = writeField(struct, f, bb, off);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return off;
    }

}
