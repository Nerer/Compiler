package Compiler.Type;

/**
 * Created by SteinerT on 2017/4/1.
 */

public class ArrayType extends Type{
    public int arrayDim;
    public Type baseType;
    public ArrayType() {
        this.baseType = null;
        this.arrayDim = 0;
    }

    public  ArrayType(Type baseType, int arrayDim) {
        this.baseType = baseType;
        this.arrayDim = arrayDim;
    }

    public static Type getType(Type baseType, int arrayDim) {
        if (baseType instanceof VoidType) {
            throw new Error();
        }
        if (arrayDim == 0) {
            throw new Error();
        }
        return new ArrayType(baseType, arrayDim);
    }
    public static Type getType(Type baseType) {
        if (baseType instanceof VoidType) {
            throw new Error();
        }
        if (baseType instanceof ArrayType) {
            ArrayType arrayType = (ArrayType)baseType;
            return new ArrayType(arrayType.baseType, arrayType.arrayDim + 1);
        } else {
            return new ArrayType(baseType, 1);
        }
    }
    public Type getReduced() {
        if (arrayDim == 1) {
            return baseType;
        } else {
            return ArrayType.getType(baseType, arrayDim - 1);
        }
    }
    @Override
    public String toString() {
        String ret = this.baseType.toString();
        for (int i = 0; i < arrayDim; i++) {
            ret += "[]";
        }
        return ret;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null) {
            if (obj instanceof NullType) {
                return true;
            }
            if (obj instanceof ArrayType) {
                if (this.baseType.equals(((ArrayType) obj).baseType)) {
                    if (this.arrayDim == ((ArrayType) obj).arrayDim) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
