package util;

import enums.InterpolationType;

public class Maths {
    // The "new" FNV-1A hashing
    private static final int FNV_32_PRIME = 0x01000193;
    private static final long FNV_32_INIT = 2166136261L;
    private static final int FNV_MASK_8 = (1 << 8) - 1;
    
    private static final int DOUBLE_SIZE = 8;
    private static final double TWO_POW_52 = 4503599627370496.0d;
    private static final double logPointFive = Math.log(0.5);
    
    public static double Clamp(final double value, final double low, final double high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }
    
    public static int Clamp(final int value, final int low, final int high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }
    
    public static double Bias(final double bias, final double target) {
        return Math.pow(target, Math.log(bias) / logPointFive);
    }
    
    public static double Gain(final double g, final double t) {
        if (t < 0.50) {
            return Bias(1.00 - g, 2.00 * t) / 2.00;
        }
        return 1.00 - Bias(1.00 - g, 2.00 - 2.00 * t) / 2.00;
    }
    
    // Edge/Face/Cube/Hypercube interpolation
    public static double Lerp(final double s, final double v1, final double v2) {
        return v1 + s * (v2 - v1);
    }
    
    public static double QuinticBlend(final double t) {
        return InterpolationType.Quintic.interpolate(t);
        //return t * t * t * (t * (t * 6 - 15) + 10);
    }
    
    public static double floor(final double value) {
        if (value != value) {
            // NaN
            return value;
        }
        if (value >= TWO_POW_52 || value <= -TWO_POW_52) {
            return value;
        }
        long intvalue = (long) value;
        if (value < 0 && intvalue != value) {
            intvalue--;
        }
        return intvalue;
    }
    
    public static double ceil(final double value) {
        if (value != value) {
            // NaN
            return value;
        }
        if (value >= TWO_POW_52 || value <= -TWO_POW_52) {
            return value;
        }
        long intvalue = (long) value;
        if (value > 0 && intvalue != value) {
            intvalue++;
        }
        return intvalue;
    }
    
    private static long FNV32Buffer(final double[] doubleBuffer, final int len) {
        ////NOTE: Completely untested. //Does not work here in java (didn't use Buffers though)
        //final byte[] buffer = new byte[len];
        //System.arraycopy(doubleBuffer, 0, buffer, 0, buffer.length);
        
        long hval = FNV_32_INIT;
        for (int i = 0; i < doubleBuffer.length/* i < len */;) {
            //hval ^= buffer[i++];
            hval ^= Double.doubleToRawLongBits(doubleBuffer[i++]);
            hval *= FNV_32_PRIME;
        }
        return hval;
    }
    
    private static byte XORFoldHash(final long hash) {
        // Implement XOR-folding to reduce from 32 to 8-bit hash
        return (byte) ((hash >> 8) ^ (hash & FNV_MASK_8));
    }
    
    public static int HashCoordinates(final double x, final double y, final int seed) {
        final double[] d = { x, y, seed };
        return Byte.toUnsignedInt(XORFoldHash(FNV32Buffer(d, DOUBLE_SIZE * 3)));
    }
    
    public static int HashCoordinates(final double x, final double y, final double z, final int seed) {
        final double[] d = { x, y, z, seed };
        return Byte.toUnsignedInt(XORFoldHash(FNV32Buffer(d, DOUBLE_SIZE * 4)));
    }
    
    public static int HashCoordinates(final double x, final double y, final double z, final double w, final int seed) {
        final double[] d = { x, y, z, w, seed };
        return Byte.toUnsignedInt(XORFoldHash(FNV32Buffer(d, DOUBLE_SIZE * 5)));
    }
    
    public static int HashCoordinates(final double x, final double y, final double z, final double w, final double u,
            final double v, final int seed) {
        final double[] d = { x, y, z, w, u, v, seed };
        return Byte.toUnsignedInt(XORFoldHash(FNV32Buffer(d, DOUBLE_SIZE * 7)));
    }
    
    public static double ArrayDot(final double[] arr, final double a, final double b) {
        return a * arr[0] + b * arr[1];
    }
    
    public static double ArrayDot(final double[] arr, final double a, final double b, final double c) {
        return a * arr[0] + b * arr[1] + c * arr[2];
    }
    
    public static double ArrayDot(final double[] arr, final double x, final double y, final double z, final double w) {
        return x * arr[0] + y * arr[1] + z * arr[2] + w * arr[3];
    }
    
    public static double ArrayDot(final double[] arr, final double x, final double y, final double z, final double w,
            final double u, final double v) {
        return x * arr[0] + y * arr[1] + z * arr[2] + w * arr[3] + u * arr[4] + v * arr[5];
    }
    
    //    private static final int INT_SIZE = 4;
    //
    //    private static long FNV32Buffer(int[] uintBuffer, int len) {
    //        //NOTE: Completely untested.
    //        byte[] buffer = new byte[len];
    //        System.arraycopy(uintBuffer, 0, buffer, 0, buffer.length);
    //
    //        long hval = FNV_32_INIT;
    //        for (int i = 0; i < len;) {
    //            hval ^= buffer[i++];
    //            hval *= FNV_32_PRIME;
    //        }
    //        return hval;
    //    }
    //
    //    private static long FNV32Buffer(byte[] buffer, int len) {
    //        long hval = FNV_32_INIT;
    //        for (int i = 0; i < len;) {
    //            hval ^= buffer[i++];
    //            hval *= FNV_32_PRIME;
    //        }
    //        return hval;
    //    }
    //
    //    private static long FNV1A_3d(double x, double y, double z, int seed) {
    //        double[] d = { x, y, z, seed };
    //        return FNV32Buffer(d, DOUBLE_SIZE * 4);
    //    }
    //    // FNV-based coordinate hashes
    //    private static byte HashCoordinates(int x, int y, int seed) {
    //        int[] d = { x, y, seed };
    //        return XORFoldHash(FNV32Buffer(d, INT_SIZE * 3));
    //    }
    //
    //    private static byte HashCoordinates(int x, int y, int z, int seed) {
    //        int[] d = { x, y, z, seed };
    //        return XORFoldHash(FNV32Buffer(d, INT_SIZE * 4));
    //    }
    //
    //    private static byte HashCoordinates(int x, int y, int z, int w, int seed) {
    //        int[] d = { x, y, z, w, seed };
    //        return XORFoldHash(FNV32Buffer(d, INT_SIZE * 5));
    //    }
    //
    //    private static byte HashCoordinates(int x, int y, int z, int w, int u, int v, int seed) {
    //        int[] d = { x, y, z, w, u, v, seed };
    //        return XORFoldHash(FNV32Buffer(d, INT_SIZE * 7));
    //    }
    
}
