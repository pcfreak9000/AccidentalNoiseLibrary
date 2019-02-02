package noise;

import enums.InterpolationType;
import util.Maths;

public class GradientNoise extends InterpolatedNoise {
    
    @Override
    public double noise(final double x, final double y, final int seed, final InterpolationType interpolator) {
        final double x0 = Maths.floor(x);
        final double y0 = Maths.floor(y);
        
        final double x1 = x0 + 1;
        final double y1 = y0 + 1;
        
        final double xs = interpolator.interpolate((x - x0));
        final double ys = interpolator.interpolate((y - y0));
        
        return interpolate_XY_2(x, y, xs, ys, x0, x1, y0, y1, seed);
    }
    
    @Override
    public double noise(final double x, final double y, final double z, final int seed,
            final InterpolationType interpolator) {
        final double x0 = Maths.floor(x);
        final double y0 = Maths.floor(y);
        final double z0 = Maths.floor(z);
        
        final double x1 = x0 + 1;
        final double y1 = y0 + 1;
        final double z1 = z0 + 1;
        
        final double xs = interpolator.interpolate((x - x0));
        final double ys = interpolator.interpolate((y - y0));
        final double zs = interpolator.interpolate((z - z0));
        
        return interpolate_XYZ_3(x, y, z, xs, ys, zs, x0, x1, y0, y1, z0, z1, seed);
    }
    
    @Override
    public double noise(final double x, final double y, final double z, final double w, final int seed,
            final InterpolationType interpolator) {
        final double x0 = Maths.floor(x);
        final double y0 = Maths.floor(y);
        final double z0 = Maths.floor(z);
        final double w0 = Maths.floor(w);
        
        final double x1 = x0 + 1;
        final double y1 = y0 + 1;
        final double z1 = z0 + 1;
        final double w1 = w0 + 1;
        
        final double xs = interpolator.interpolate((x - x0));
        final double ys = interpolator.interpolate((y - y0));
        final double zs = interpolator.interpolate((z - z0));
        final double ws = interpolator.interpolate((w - w0));
        
        return interpolate_XYZW_4(x, y, z, w, xs, ys, zs, ws, x0, x1, y0, y1, z0, z1, w0, w1, seed);
    }
    
    @Override
    public double noise(final double x, final double y, final double z, final double w, final double u, final double v,
            final int seed, final InterpolationType interpolator) {
        final double x0 = Maths.floor(x);
        final double y0 = Maths.floor(y);
        final double z0 = Maths.floor(z);
        final double w0 = Maths.floor(w);
        final double u0 = Maths.floor(u);
        final double v0 = Maths.floor(v);
        
        final double x1 = x0 + 1;
        final double y1 = y0 + 1;
        final double z1 = z0 + 1;
        final double w1 = w0 + 1;
        final double u1 = u0 + 1;
        final double v1 = v0 + 1;
        
        final double xs = interpolator.interpolate((x - x0));
        final double ys = interpolator.interpolate((y - y0));
        final double zs = interpolator.interpolate((z - z0));
        final double ws = interpolator.interpolate((w - w0));
        final double us = interpolator.interpolate((u - u0));
        final double vs = interpolator.interpolate((v - v0));
        
        return interpolate_XYZWUV_6(x, y, z, w, u, v, xs, ys, zs, ws, us, vs, x0, x1, y0, y1, z0, z1, w0, w1, u0, u1,
                v0, v1, seed);
    }
    
    @Override
    protected double internalNoise(final double x, final double y, final double ix, final double iy, final int seed) {
        final int hash = Maths.HashCoordinates(ix, iy, seed);
        
        final double dx = x - ix;
        final double dy = y - iy;
        
        return (dx * NoiseLookupTable.Gradient2D[hash][0] + dy * NoiseLookupTable.Gradient2D[hash][1]);
    }
    
    @Override
    protected double internalNoise(final double x, final double y, final double z, final double ix, final double iy,
            final double iz, final int seed) {
        final int hash = Maths.HashCoordinates(ix, iy, iz, seed);
        
        final double dx = x - ix;
        final double dy = y - iy;
        final double dz = z - iz;
        
        return (dx * NoiseLookupTable.Gradient3D[hash][0] + dy * NoiseLookupTable.Gradient3D[hash][1]
                + dz * NoiseLookupTable.Gradient3D[hash][2]);
    }
    
    @Override
    protected double internalNoise(final double x, final double y, final double z, final double w, final double ix,
            final double iy, final double iz, final double iw, final int seed) {
        final int hash = Maths.HashCoordinates(ix, iy, iz, iw, seed);
        
        final double dx = x - ix;
        final double dy = y - iy;
        final double dz = z - iz;
        final double dw = w - iw;
        
        return (dx * NoiseLookupTable.Gradient4D[hash][0] + dy * NoiseLookupTable.Gradient4D[hash][1]
                + dz * NoiseLookupTable.Gradient4D[hash][2] + dw * NoiseLookupTable.Gradient4D[hash][3]);
    }
    
    @Override
    protected double internalNoise(final double x, final double y, final double z, final double w, final double u,
            final double v, final double ix, final double iy, final double iz, final double iw, final double iu,
            final double iv, final int seed) {
        final int hash = Maths.HashCoordinates(ix, iy, iz, iw, iu, iv, seed);
        
        final double dx = x - ix;
        final double dy = y - iy;
        final double dz = z - iz;
        final double dw = w - iw;
        final double du = u - iu;
        final double dv = v - iv;
        
        return (dx * NoiseLookupTable.Gradient6D[hash][0] + dy * NoiseLookupTable.Gradient6D[hash][1]
                + dz * NoiseLookupTable.Gradient6D[hash][2] + dw * NoiseLookupTable.Gradient6D[hash][3]
                + du * NoiseLookupTable.Gradient6D[hash][4] + dv * NoiseLookupTable.Gradient6D[hash][5]);
    }
    
}
