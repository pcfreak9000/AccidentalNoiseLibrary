package noise;

import Enums.InterpolationType;
import top.Maths;
import top.NoiseLookupTable;

public class GradientNoise extends InterpolatedNoise {
    
    @Override
    public double noise(double x, double y, int seed, InterpolationType interpolator) {
        double x0 = Maths.floor(x);
        double y0 = Maths.floor(y);
        
        double x1 = x0 + 1;
        double y1 = y0 + 1;
        
        double xs = interpolator.interpolate((x - x0));
        double ys = interpolator.interpolate((y - y0));
        
        return interpolate_XY_2(x, y, xs, ys, x0, x1, y0, y1, seed);
    }
    
    @Override
    public double noise(double x, double y, double z, int seed, InterpolationType interpolator) {
        double x0 = Maths.floor(x);
        double y0 = Maths.floor(y);
        double z0 = Maths.floor(z);
        
        double x1 = x0 + 1;
        double y1 = y0 + 1;
        double z1 = z0 + 1;
        
        double xs = interpolator.interpolate((x - x0));
        double ys = interpolator.interpolate((y - y0));
        double zs = interpolator.interpolate((z - z0));
        
        return interpolate_XYZ_3(x, y, z, xs, ys, zs, x0, x1, y0, y1, z0, z1, seed);
    }
    
    @Override
    public double noise(double x, double y, double z, double w, int seed, InterpolationType interpolator) {
        double x0 = Maths.floor(x);
        double y0 = Maths.floor(y);
        double z0 = Maths.floor(z);
        double w0 = Maths.floor(w);
        
        double x1 = x0 + 1;
        double y1 = y0 + 1;
        double z1 = z0 + 1;
        double w1 = w0 + 1;
        
        double xs = interpolator.interpolate((x - x0));
        double ys = interpolator.interpolate((y - y0));
        double zs = interpolator.interpolate((z - z0));
        double ws = interpolator.interpolate((w - w0));
        
        return interpolate_XYZW_4(x, y, z, w, xs, ys, zs, ws, x0, x1, y0, y1, z0, z1, w0, w1, seed
                );
    }
    
    @Override
    public double noise(double x, double y, double z, double w, double u, double v, int seed,
            InterpolationType interpolator) {
        double x0 = Maths.floor(x);
        double y0 = Maths.floor(y);
        double z0 = Maths.floor(z);
        double w0 = Maths.floor(w);
        double u0 = Maths.floor(u);
        double v0 = Maths.floor(v);
        
        double x1 = x0 + 1;
        double y1 = y0 + 1;
        double z1 = z0 + 1;
        double w1 = w0 + 1;
        double u1 = u0 + 1;
        double v1 = v0 + 1;
        
        double xs = interpolator.interpolate((x - x0));
        double ys = interpolator.interpolate((y - y0));
        double zs = interpolator.interpolate((z - z0));
        double ws = interpolator.interpolate((w - w0));
        double us = interpolator.interpolate((u - u0));
        double vs = interpolator.interpolate((v - v0));
        
        return interpolate_XYZWUV_6(x, y, z, w, u, v, xs, ys, zs, ws, us, vs, x0, x1, y0, y1, z0, z1, w0, w1, u0, u1,
                v0, v1, seed);
    }
    
    @Override
    protected double internalNoise(double x, double y, double ix, double iy, int seed) {
        byte hash = Maths.HashCoordinates(ix, iy, seed);
        
        double dx = x - ix;
        double dy = y - iy;
        
        return (dx * NoiseLookupTable.Gradient2D[hash][0] + dy * NoiseLookupTable.Gradient2D[hash][1]);
    }
    
    @Override
    protected double internalNoise(double x, double y, double z, double ix, double iy, double iz, int seed) {
        byte hash = Maths.HashCoordinates(ix, iy, iz, seed);
        
        double dx = x - ix;
        double dy = y - iy;
        double dz = z - iz;
        
        return (dx * NoiseLookupTable.Gradient3D[hash][0] + dy * NoiseLookupTable.Gradient3D[hash][1]
                + dz * NoiseLookupTable.Gradient3D[hash][2]);
    }
    
    @Override
    protected double internalNoise(double x, double y, double z, double w, double ix, double iy, double iz, double iw,
            int seed) {
        byte hash = Maths.HashCoordinates(ix, iy, iz, iw, seed);
        
        double dx = x - ix;
        double dy = y - iy;
        double dz = z - iz;
        double dw = w - iw;
        
        return (dx * NoiseLookupTable.Gradient4D[hash][0] + dy * NoiseLookupTable.Gradient4D[hash][1]
                + dz * NoiseLookupTable.Gradient4D[hash][2] + dw * NoiseLookupTable.Gradient4D[hash][3]);
    }
    
    @Override
    protected double internalNoise(double x, double y, double z, double w, double u, double v, double ix, double iy,
            double iz, double iw, double iu, double iv, int seed) {
        byte hash = Maths.HashCoordinates(ix, iy, iz, iw, iu, iv, seed);
        
        double dx = x - ix;
        double dy = y - iy;
        double dz = z - iz;
        double dw = w - iw;
        double du = u - iu;
        double dv = v - iv;
        
        return (dx * NoiseLookupTable.Gradient6D[hash][0] + dy * NoiseLookupTable.Gradient6D[hash][1]
                + dz * NoiseLookupTable.Gradient6D[hash][2] + dw * NoiseLookupTable.Gradient6D[hash][3]
                + du * NoiseLookupTable.Gradient6D[hash][4] + dv * NoiseLookupTable.Gradient6D[hash][5]);
    }
    
}
