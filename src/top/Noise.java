package top;

import Enums.InterpolationType;

public class Noise {
    
    private static void AddDistance(final double[] f, final double[] disp, final double testdist,
            final double testdisp) {
        // Compare the given distance to the ones already in f
        if (testdist >= f[3]) {
            return;
        }
        
        int index = 3;
        while (index > 0 && testdist < f[index - 1]) {
            index--;
        }
        
        for (int i = 3; i-- > index;) {
            f[i + 1] = f[i];
            disp[i + 1] = disp[i];
        }
        f[index] = testdist;
        disp[index] = testdisp;
    }
    
    public static void CellularFunction(final double x, final double y, final int seed, final double[] f,
            final double[] disp) {
        final int xInt = FastFloor(x);
        final int yInt = FastFloor(y);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int ycur = yInt - 3; ycur <= yInt + 3; ++ycur) {
            for (int xcur = xInt - 3; xcur <= xInt + 3; ++xcur) {
                final double xpos = xcur + InternalValueNoise(x, y, xcur, ycur, seed);
                final double ypos = ycur + InternalValueNoise(x, y, xcur, ycur, seed + 1);
                final double xdist = xpos - x;
                final double ydist = ypos - y;
                final double dist = (xdist * xdist + ydist * ydist);
                final int xval = FastFloor(xpos);
                final int yval = FastFloor(ypos);
                final double dsp = InternalValueNoise(x, y, xval, yval, seed + 3);
                AddDistance(f, disp, dist, dsp);
            }
        }
    }
    
    public static void CellularFunction(final double x, final double y, final double z, final int seed,
            final double[] f, final double[] disp) {
        final int xInt = FastFloor(x);
        final int yInt = FastFloor(y);
        final int zInt = FastFloor(z);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
            for (int ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                for (int xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                    final double xpos = xcur + InternalValueNoise(x, y, z, xcur, ycur, zcur, seed);
                    final double ypos = ycur + InternalValueNoise(x, y, z, xcur, ycur, zcur, seed + 1);
                    final double zpos = zcur + InternalValueNoise(x, y, z, xcur, ycur, zcur, seed + 2);
                    final double xdist = xpos - x;
                    final double ydist = ypos - y;
                    final double zdist = zpos - z;
                    final double dist = (xdist * xdist + ydist * ydist + zdist * zdist);
                    final int xval = FastFloor(xpos);
                    final int yval = FastFloor(ypos);
                    final int zval = FastFloor(zpos);
                    final double dsp = InternalValueNoise(x, y, z, xval, yval, zval, seed + 3);
                    AddDistance(f, disp, dist, dsp);
                }
            }
        }
    }
    
    public static void CellularFunction(final double x, final double y, final double z, final double w, final int seed,
            final double[] f, final double[] disp) {
        final int xInt = FastFloor(x);
        final int yInt = FastFloor(y);
        final int zInt = FastFloor(z);
        final int wInt = FastFloor(w);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int wcur = wInt - 2; wcur <= wInt + 2; ++wcur) {
            for (int zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
                for (int ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                    for (int xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                        final double xpos = xcur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed);
                        final double ypos = ycur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 1);
                        final double zpos = zcur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 2);
                        final double wpos = wcur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 3);
                        final double xdist = xpos - x;
                        final double ydist = ypos - y;
                        final double zdist = zpos - z;
                        final double wdist = wpos - w;
                        final double dist = (xdist * xdist + ydist * ydist + zdist * zdist + wdist * wdist);
                        final int xval = FastFloor(xpos);
                        final int yval = FastFloor(ypos);
                        final int zval = FastFloor(zpos);
                        final int wval = FastFloor(wpos);
                        final double dsp = InternalValueNoise(x, y, z, w, xval, yval, zval, wval, seed + 3);
                        AddDistance(f, disp, dist, dsp);
                    }
                }
            }
        }
    }
    
    public static void CellularFunction(final double x, final double y, final double z, final double w, final double u,
            final double v, final int seed, final double[] f, final double[] disp) {
        final int xInt = FastFloor(x);
        final int yInt = FastFloor(y);
        final int zInt = FastFloor(z);
        final int wInt = FastFloor(w);
        final int uInt = FastFloor(u);
        final int vInt = FastFloor(v);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int vcur = vInt - 1; vcur <= vInt + 1; ++vcur) {
            for (int ucur = uInt - 1; ucur <= uInt + 1; ++ucur) {
                
                for (int wcur = wInt - 2; wcur <= wInt + 2; ++wcur) {
                    for (int zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
                        for (int ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                            for (int xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                                final double xpos = xcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur,
                                        ucur, vcur, seed);
                                final double ypos = ycur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur,
                                        ucur, vcur, seed + 1);
                                final double zpos = zcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur,
                                        ucur, vcur, seed + 2);
                                final double wpos = wcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur,
                                        ucur, vcur, seed + 3);
                                final double upos = ucur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur,
                                        ucur, vcur, seed + 4);
                                final double vpos = vcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur,
                                        ucur, vcur, seed + 5);
                                final double xdist = xpos - x;
                                final double ydist = ypos - y;
                                final double zdist = zpos - z;
                                final double wdist = wpos - w;
                                final double udist = upos - u;
                                final double vdist = vpos - v;
                                final double dist = (xdist * xdist + ydist * ydist + zdist * zdist + wdist * wdist
                                        + udist * udist + vdist * vdist);
                                final int xval = FastFloor(xpos);
                                final int yval = FastFloor(ypos);
                                final int zval = FastFloor(zpos);
                                final int wval = FastFloor(wpos);
                                final int uval = FastFloor(upos);
                                final int vval = FastFloor(vpos);
                                final double dsp = InternalValueNoise(x, y, z, w, u, v, xval, yval, zval, wval, uval,
                                        vval, seed + 6);
                                AddDistance(f, disp, dist, dsp);
                            }
                        }
                    }
                }
            }
        }
    }
    
}