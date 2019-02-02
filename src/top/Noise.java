package top;

import java.util.Arrays;
import java.util.Comparator;

import Enums.InterpolationType;

public class Noise {
    
    //    public abstract double Noise2DDelegate(double x, double y, int seed, InterpolationDelegate interp);
    //    
    //    public abstract double Noise3DDelegate(double x, double y, double z, int seed, InterpolationDelegate interp);
    //    
    //    public abstract double Noise4DDelegate(double x, double y, double z, double w, int seed,
    //            InterpolationDelegate interp);
    //    
    //    public abstract double Noise6DDelegate(double x, double y, double z, double w, double u, double v, int seed,
    //            InterpolationDelegate interp);
    
    public final int MAX_SOURCES = 20;
    
    //TODO this is not so good
    private static int FastFloor(double t) {
        return (t > 0 ? (int) t : (int) t - 1);
    }
    
    private static void AddDistance(double[] f, double[] disp, double testdist, double testdisp) {
        // Compare the given distance to the ones already in f
        if (testdist >= f[3])
            return;
        
        int index = 3;
        while (index > 0 && testdist < f[index - 1])
            index--;
        
        for (int i = 3; i-- > index;) {
            f[i + 1] = f[i];
            disp[i + 1] = disp[i];
        }
        f[index] = testdist;
        disp[index] = testdisp;
    }
    
    
    
    //    protected abstract double WorkerNoise2(double x, double y, int ix, int iy, int seed);
    //    
    //    protected abstract double WorkerNoise3(double x, double y, double z, int ix, int iy, int iz, int seed);
    //    
    //    protected abstract double WorkerNoise4(double x, double y, double z, double w, int ix, int iy, int iz, int iw,
    //            int seed);
    //    
    //    protected abstract double WorkerNoise6(double x, double y, double z, double w, double u, double v, int ix, int iy,
    //            int iz, int iw, int iu, int iv, int seed);

    
    public static double GradientValueNoise(double x, double y, int seed, InterpolationType interp) {
        return ValueNoise(x, y, seed, interp) + GradientNoise(x, y, seed, interp);
    }
    
    public static double GradientValueNoise(double x, double y, double z, int seed, InterpolationType interp) {
        return ValueNoise(x, y, z, seed, interp) + GradientNoise(x, y, z, seed, interp);
    }
    
    public static double GradientValueNoise(double x, double y, double z, double w, int seed,
            InterpolationType interp) {
        return ValueNoise(x, y, z, w, seed, interp) + GradientNoise(x, y, z, w, seed, interp);
    }
    
    public static double GradientValueNoise(double x, double y, double z, double w, double u, double v, int seed,
            InterpolationType interp) {
        return ValueNoise(x, y, z, w, u, v, seed, interp) + GradientNoise(x, y, z, w, u, v, seed, interp);
    }
    
    public static void CellularFunction(double x, double y, int seed, double[] f, double[] disp) {
        int xInt = FastFloor(x);
        int yInt = FastFloor(y);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int ycur = yInt - 3; ycur <= yInt + 3; ++ycur) {
            for (int xcur = xInt - 3; xcur <= xInt + 3; ++xcur) {
                double xpos = xcur + InternalValueNoise(x, y, xcur, ycur, seed);
                double ypos = ycur + InternalValueNoise(x, y, xcur, ycur, seed + 1);
                double xdist = xpos - x;
                double ydist = ypos - y;
                double dist = (xdist * xdist + ydist * ydist);
                int xval = FastFloor(xpos);
                int yval = FastFloor(ypos);
                double dsp = InternalValueNoise(x, y, xval, yval, seed + 3);
                AddDistance(f, disp, dist, dsp);
            }
        }
    }
    
    public static void CellularFunction(double x, double y, double z, int seed, double[] f, double[] disp) {
        int xInt = FastFloor(x);
        int yInt = FastFloor(y);
        int zInt = FastFloor(z);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
            for (int ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                for (int xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                    double xpos = xcur + InternalValueNoise(x, y, z, xcur, ycur, zcur, seed);
                    double ypos = ycur + InternalValueNoise(x, y, z, xcur, ycur, zcur, seed + 1);
                    double zpos = zcur + InternalValueNoise(x, y, z, xcur, ycur, zcur, seed + 2);
                    double xdist = xpos - x;
                    double ydist = ypos - y;
                    double zdist = zpos - z;
                    double dist = (xdist * xdist + ydist * ydist + zdist * zdist);
                    int xval = FastFloor(xpos);
                    int yval = FastFloor(ypos);
                    int zval = FastFloor(zpos);
                    double dsp = InternalValueNoise(x, y, z, xval, yval, zval, seed + 3);
                    AddDistance(f, disp, dist, dsp);
                }
            }
        }
    }
    
    public static void CellularFunction(double x, double y, double z, double w, int seed, double[] f, double[] disp) {
        int xInt = FastFloor(x);
        int yInt = FastFloor(y);
        int zInt = FastFloor(z);
        int wInt = FastFloor(w);
        
        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }
        
        for (int wcur = wInt - 2; wcur <= wInt + 2; ++wcur) {
            for (int zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
                for (int ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                    for (int xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                        double xpos = xcur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed);
                        double ypos = ycur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 1);
                        double zpos = zcur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 2);
                        double wpos = wcur + InternalValueNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 3);
                        double xdist = xpos - x;
                        double ydist = ypos - y;
                        double zdist = zpos - z;
                        double wdist = wpos - w;
                        double dist = (xdist * xdist + ydist * ydist + zdist * zdist + wdist * wdist);
                        int xval = FastFloor(xpos);
                        int yval = FastFloor(ypos);
                        int zval = FastFloor(zpos);
                        int wval = FastFloor(wpos);
                        double dsp = InternalValueNoise(x, y, z, w, xval, yval, zval, wval, seed + 3);
                        AddDistance(f, disp, dist, dsp);
                    }
                }
            }
        }
    }
    
    public static void CellularFunction(double x, double y, double z, double w, double u, double v, int seed,
            double[] f, double[] disp) {
        int xInt = FastFloor(x);
        int yInt = FastFloor(y);
        int zInt = FastFloor(z);
        int wInt = FastFloor(w);
        int uInt = FastFloor(u);
        int vInt = FastFloor(v);
        
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
                                double xpos = xcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur, ucur,
                                        vcur, seed);
                                double ypos = ycur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur, ucur,
                                        vcur, seed + 1);
                                double zpos = zcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur, ucur,
                                        vcur, seed + 2);
                                double wpos = wcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur, ucur,
                                        vcur, seed + 3);
                                double upos = ucur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur, ucur,
                                        vcur, seed + 4);
                                double vpos = vcur + InternalValueNoise(x, y, z, w, u, v, xcur, ycur, zcur, wcur, ucur,
                                        vcur, seed + 5);
                                double xdist = xpos - x;
                                double ydist = ypos - y;
                                double zdist = zpos - z;
                                double wdist = wpos - w;
                                double udist = upos - u;
                                double vdist = vpos - v;
                                double dist = (xdist * xdist + ydist * ydist + zdist * zdist + wdist * wdist
                                        + udist * udist + vdist * vdist);
                                int xval = FastFloor(xpos);
                                int yval = FastFloor(ypos);
                                int zval = FastFloor(zpos);
                                int wval = FastFloor(wpos);
                                int uval = FastFloor(upos);
                                int vval = FastFloor(vpos);
                                double dsp = InternalValueNoise(x, y, z, w, u, v, xval, yval, zval, wval, uval, vval,
                                        seed + 6);
                                AddDistance(f, disp, dist, dsp);
                            }
                        }
                    }
                }
            }
        }
    }
    

    
}