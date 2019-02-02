package noise;

import top.Maths;

public abstract class InterpolatedNoise implements Noise {
    
    //non-integer-doubles do not get used, why?
    //TODO make internal public or is protected ok?
    protected abstract double internalNoise(double x, double y, double ix, double iy, int seed);
    
    protected abstract double internalNoise(double x, double y, double z, double ix, double iy, double iz, int seed);
    
    protected abstract double internalNoise(double x, double y, double z, double w, double ix, double iy, double iz,
            double iw, int seed);
    
    protected abstract double internalNoise(double x, double y, double z, double w, double u, double v, double ix,
            double iy, double iz, double iw, double iu, double iv, int seed);
    
    private double interpolate_X_2(double x, double y, double xs, double x0, double x1, double iy, int seed) {
        double v1 = internalNoise(x, y, x0, iy, seed);
        double v2 = internalNoise(x, y, x1, iy, seed);
        
        return Maths.Lerp(xs, v1, v2);
    }
    
    protected double interpolate_XY_2(double x, double y, double xs, double ys, double x0, double x1, double y0,
            double y1, int seed) {
        double v1 = interpolate_X_2(x, y, xs, x0, x1, y0, seed);
        double v2 = interpolate_X_2(x, y, xs, x0, x1, y1, seed);
        
        return Maths.Lerp(ys, v1, v2);
    }
    
    private double interpolate_X_3(double x, double y, double z, double xs, double x0, double x1, double iy, double iz,
            int seed) {
        double v1 = internalNoise(x, y, z, x0, iy, iz, seed);
        double v2 = internalNoise(x, y, z, x1, iy, iz, seed);
        
        return Maths.Lerp(xs, v1, v2);
    }
    
    private double interpolate_XY_3(double x, double y, double z, double xs, double ys, double x0, double x1, double y0,
            double y1, double iz, int seed) {
        double v1 = interpolate_X_3(x, y, z, xs, x0, x1, y0, iz, seed);
        double v2 = interpolate_X_3(x, y, z, xs, x0, x1, y1, iz, seed);
        
        return Maths.Lerp(ys, v1, v2);
    }
    
    protected double interpolate_XYZ_3(double x, double y, double z, double xs, double ys, double zs, double x0,
            double x1, double y0, double y1, double z0, double z1, int seed) {
        double v1 = interpolate_XY_3(x, y, z, xs, ys, x0, x1, y0, y1, z0, seed);
        double v2 = interpolate_XY_3(x, y, z, xs, ys, x0, x1, y0, y1, z1, seed);
        
        return Maths.Lerp(zs, v1, v2);
    }
    
    private double interpolate_X_4(double x, double y, double z, double w, double xs, double x0, double x1, double iy,
            double iz, double iw, int seed) {
        double v1 = internalNoise(x, y, z, w, x0, iy, iz, iw, seed);
        double v2 = internalNoise(x, y, z, w, x1, iy, iz, iw, seed);
        
        return Maths.Lerp(xs, v1, v2);
    }
    
    private double interpolate_XY_4(double x, double y, double z, double w, double xs, double ys, double x0, double x1,
            double y0, double y1, double iz, double iw, int seed) {
        double v1 = interpolate_X_4(x, y, z, w, xs, x0, x1, y0, iz, iw, seed);
        double v2 = interpolate_X_4(x, y, z, w, xs, x0, x1, y1, iz, iw, seed);
        
        return Maths.Lerp(ys, v1, v2);
    }
    
    private double interpolate_XYZ_4(double x, double y, double z, double w, double xs, double ys, double zs, double x0,
            double x1, double y0, double y1, double z0, double z1, double iw, int seed) {
        double v1 = interpolate_XY_4(x, y, z, w, xs, ys, x0, x1, y0, y1, z0, iw, seed);
        double v2 = interpolate_XY_4(x, y, z, w, xs, ys, x0, x1, y0, y1, z1, iw, seed);
        
        return Maths.Lerp(zs, v1, v2);
    }
    
    protected double interpolate_XYZW_4(double x, double y, double z, double w, double xs, double ys, double zs,
            double ws, double x0, double x1, double y0, double y1, double z0, double z1, double w0, double w1,
            int seed) {
        double v1 = interpolate_XYZ_4(x, y, z, w, xs, ys, zs, x0, x1, y0, y1, z0, z1, w0, seed);
        double v2 = interpolate_XYZ_4(x, y, z, w, xs, ys, zs, x0, x1, y0, y1, z0, z1, w1, seed);
        
        return Maths.Lerp(ws, v1, v2);
    }
    
    private double interpolate_X_6(double x, double y, double z, double w, double u, double v, double xs, double x0,
            double x1, double iy, double iz, double iw, double iu, double iv, int seed) {
        double v1 = internalNoise(x, y, z, w, u, v, x0, iy, iz, iw, iu, iv, seed);
        double v2 = internalNoise(x, y, z, w, u, v, x1, iy, iz, iw, iu, iv, seed);
        
        return Maths.Lerp(xs, v1, v2);
    }
    
    private double interpolate_XY_6(double x, double y, double z, double w, double u, double v, double xs, double ys,
            double x0, double x1, double y0, double y1, double iz, double iw, double iu, double iv, int seed) {
        double v1 = interpolate_X_6(x, y, z, w, u, v, xs, x0, x1, y0, iz, iw, iu, iv, seed);
        double v2 = interpolate_X_6(x, y, z, w, u, v, xs, x0, x1, y1, iz, iw, iu, iv, seed);
        
        return Maths.Lerp(ys, v1, v2);
    }
    
    private double interpolate_XYZ_6(double x, double y, double z, double w, double u, double v, double xs, double ys,
            double zs, double x0, double x1, double y0, double y1, double z0, double z1, double iw, double iu,
            double iv, int seed) {
        double v1 = interpolate_XY_6(x, y, z, w, u, v, xs, ys, x0, x1, y0, y1, z0, iw, iu, iv, seed);
        double v2 = interpolate_XY_6(x, y, z, w, u, v, xs, ys, x0, x1, y0, y1, z1, iw, iu, iv, seed);
        
        return Maths.Lerp(zs, v1, v2);
    }
    
    private double interpolate_XYZW_6(double x, double y, double z, double w, double u, double v, double xs, double ys,
            double zs, double ws, double x0, double x1, double y0, double y1, double z0, double z1, double w0,
            double w1, double iu, double iv, int seed) {
        double v1 = interpolate_XYZ_6(x, y, z, w, u, v, xs, ys, zs, x0, x1, y0, y1, z0, z1, w0, iu, iv, seed);
        double v2 = interpolate_XYZ_6(x, y, z, w, u, v, xs, ys, zs, x0, x1, y0, y1, z0, z1, w1, iu, iv, seed);
        
        return Maths.Lerp(ws, v1, v2);
    }
    
    private double interpolate_XYZWU_6(double x, double y, double z, double w, double u, double v, double xs, double ys,
            double zs, double ws, double us, double x0, double x1, double y0, double y1, double z0, double z1,
            double w0, double w1, double u0, double u1, double iv, int seed) {
        double v1 = interpolate_XYZW_6(x, y, z, w, u, v, xs, ys, zs, ws, x0, x1, y0, y1, z0, z1, w0, w1, u0, iv, seed);
        double v2 = interpolate_XYZW_6(x, y, z, w, u, v, xs, ys, zs, ws, x0, x1, y0, y1, z0, z1, w0, w1, u1, iv, seed);
        
        return Maths.Lerp(us, v1, v2);
    }
    
    protected double interpolate_XYZWUV_6(double x, double y, double z, double w, double u, double v, double xs,
            double ys, double zs, double ws, double us, double vs, double x0, double x1, double y0, double y1,
            double z0, double z1, double w0, double w1, double u0, double u1, double v0, double v1, int seed) {
        double val1 = interpolate_XYZWU_6(x, y, z, w, u, v, xs, ys, zs, ws, us, x0, x1, y0, y1, z0, z1, w0, w1, u0, u1,
                v0, seed);
        double val2 = interpolate_XYZWU_6(x, y, z, w, u, v, xs, ys, zs, ws, us, x0, x1, y0, y1, z0, z1, w0, w1, u0, u1,
                v1, seed);
        
        return Maths.Lerp(vs, val1, val2);
    }
    
}
