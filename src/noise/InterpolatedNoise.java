package noise;

import util.Maths;

public abstract class InterpolatedNoise implements Noise {

    //non-integer-doubles do not get used, why?
    //TODO make internal public or is protected ok?
    protected abstract double internalNoise(double x, double y, double ix, double iy, int seed);

    protected abstract double internalNoise(double x, double y, double z, double ix, double iy, double iz, int seed);

    protected abstract double internalNoise(double x, double y, double z, double w, double ix, double iy, double iz,
            double iw, int seed);

    protected abstract double internalNoise(double x, double y, double z, double w, double u, double v, double ix,
            double iy, double iz, double iw, double iu, double iv, int seed);

    private double interpolate_X_2(final double x, final double y, final double xs, final double x0, final double x1,
            final double iy, final int seed) {
        final double v1 = internalNoise(x, y, x0, iy, seed);
        final double v2 = internalNoise(x, y, x1, iy, seed);

        return Maths.Lerp(xs, v1, v2);
    }

    protected double interpolate_XY_2(final double x, final double y, final double xs, final double ys, final double x0,
            final double x1, final double y0, final double y1, final int seed) {
        final double v1 = interpolate_X_2(x, y, xs, x0, x1, y0, seed);
        final double v2 = interpolate_X_2(x, y, xs, x0, x1, y1, seed);

        return Maths.Lerp(ys, v1, v2);
    }

    private double interpolate_X_3(final double x, final double y, final double z, final double xs, final double x0,
            final double x1, final double iy, final double iz, final int seed) {
        final double v1 = internalNoise(x, y, z, x0, iy, iz, seed);
        final double v2 = internalNoise(x, y, z, x1, iy, iz, seed);

        return Maths.Lerp(xs, v1, v2);
    }

    private double interpolate_XY_3(final double x, final double y, final double z, final double xs, final double ys,
            final double x0, final double x1, final double y0, final double y1, final double iz, final int seed) {
        final double v1 = interpolate_X_3(x, y, z, xs, x0, x1, y0, iz, seed);
        final double v2 = interpolate_X_3(x, y, z, xs, x0, x1, y1, iz, seed);

        return Maths.Lerp(ys, v1, v2);
    }

    protected double interpolate_XYZ_3(final double x, final double y, final double z, final double xs, final double ys,
            final double zs, final double x0, final double x1, final double y0, final double y1, final double z0,
            final double z1, final int seed) {
        final double v1 = interpolate_XY_3(x, y, z, xs, ys, x0, x1, y0, y1, z0, seed);
        final double v2 = interpolate_XY_3(x, y, z, xs, ys, x0, x1, y0, y1, z1, seed);

        return Maths.Lerp(zs, v1, v2);
    }

    private double interpolate_X_4(final double x, final double y, final double z, final double w, final double xs,
            final double x0, final double x1, final double iy, final double iz, final double iw, final int seed) {
        final double v1 = internalNoise(x, y, z, w, x0, iy, iz, iw, seed);
        final double v2 = internalNoise(x, y, z, w, x1, iy, iz, iw, seed);

        return Maths.Lerp(xs, v1, v2);
    }

    private double interpolate_XY_4(final double x, final double y, final double z, final double w, final double xs,
            final double ys, final double x0, final double x1, final double y0, final double y1, final double iz,
            final double iw, final int seed) {
        final double v1 = interpolate_X_4(x, y, z, w, xs, x0, x1, y0, iz, iw, seed);
        final double v2 = interpolate_X_4(x, y, z, w, xs, x0, x1, y1, iz, iw, seed);

        return Maths.Lerp(ys, v1, v2);
    }

    private double interpolate_XYZ_4(final double x, final double y, final double z, final double w, final double xs,
            final double ys, final double zs, final double x0, final double x1, final double y0, final double y1,
            final double z0, final double z1, final double iw, final int seed) {
        final double v1 = interpolate_XY_4(x, y, z, w, xs, ys, x0, x1, y0, y1, z0, iw, seed);
        final double v2 = interpolate_XY_4(x, y, z, w, xs, ys, x0, x1, y0, y1, z1, iw, seed);

        return Maths.Lerp(zs, v1, v2);
    }

    protected double interpolate_XYZW_4(final double x, final double y, final double z, final double w, final double xs,
            final double ys, final double zs, final double ws, final double x0, final double x1, final double y0,
            final double y1, final double z0, final double z1, final double w0, final double w1, final int seed) {
        final double v1 = interpolate_XYZ_4(x, y, z, w, xs, ys, zs, x0, x1, y0, y1, z0, z1, w0, seed);
        final double v2 = interpolate_XYZ_4(x, y, z, w, xs, ys, zs, x0, x1, y0, y1, z0, z1, w1, seed);

        return Maths.Lerp(ws, v1, v2);
    }

    private double interpolate_X_6(final double x, final double y, final double z, final double w, final double u,
            final double v, final double xs, final double x0, final double x1, final double iy, final double iz,
            final double iw, final double iu, final double iv, final int seed) {
        final double v1 = internalNoise(x, y, z, w, u, v, x0, iy, iz, iw, iu, iv, seed);
        final double v2 = internalNoise(x, y, z, w, u, v, x1, iy, iz, iw, iu, iv, seed);

        return Maths.Lerp(xs, v1, v2);
    }

    private double interpolate_XY_6(final double x, final double y, final double z, final double w, final double u,
            final double v, final double xs, final double ys, final double x0, final double x1, final double y0,
            final double y1, final double iz, final double iw, final double iu, final double iv, final int seed) {
        final double v1 = interpolate_X_6(x, y, z, w, u, v, xs, x0, x1, y0, iz, iw, iu, iv, seed);
        final double v2 = interpolate_X_6(x, y, z, w, u, v, xs, x0, x1, y1, iz, iw, iu, iv, seed);

        return Maths.Lerp(ys, v1, v2);
    }

    private double interpolate_XYZ_6(final double x, final double y, final double z, final double w, final double u,
            final double v, final double xs, final double ys, final double zs, final double x0, final double x1,
            final double y0, final double y1, final double z0, final double z1, final double iw, final double iu,
            final double iv, final int seed) {
        final double v1 = interpolate_XY_6(x, y, z, w, u, v, xs, ys, x0, x1, y0, y1, z0, iw, iu, iv, seed);
        final double v2 = interpolate_XY_6(x, y, z, w, u, v, xs, ys, x0, x1, y0, y1, z1, iw, iu, iv, seed);

        return Maths.Lerp(zs, v1, v2);
    }

    private double interpolate_XYZW_6(final double x, final double y, final double z, final double w, final double u,
            final double v, final double xs, final double ys, final double zs, final double ws, final double x0,
            final double x1, final double y0, final double y1, final double z0, final double z1, final double w0,
            final double w1, final double iu, final double iv, final int seed) {
        final double v1 = interpolate_XYZ_6(x, y, z, w, u, v, xs, ys, zs, x0, x1, y0, y1, z0, z1, w0, iu, iv, seed);
        final double v2 = interpolate_XYZ_6(x, y, z, w, u, v, xs, ys, zs, x0, x1, y0, y1, z0, z1, w1, iu, iv, seed);

        return Maths.Lerp(ws, v1, v2);
    }

    private double interpolate_XYZWU_6(final double x, final double y, final double z, final double w, final double u,
            final double v, final double xs, final double ys, final double zs, final double ws, final double us,
            final double x0, final double x1, final double y0, final double y1, final double z0, final double z1,
            final double w0, final double w1, final double u0, final double u1, final double iv, final int seed) {
        final double v1 = interpolate_XYZW_6(x, y, z, w, u, v, xs, ys, zs, ws, x0, x1, y0, y1, z0, z1, w0, w1, u0, iv,
                seed);
        final double v2 = interpolate_XYZW_6(x, y, z, w, u, v, xs, ys, zs, ws, x0, x1, y0, y1, z0, z1, w0, w1, u1, iv,
                seed);

        return Maths.Lerp(us, v1, v2);
    }

    protected double interpolate_XYZWUV_6(final double x, final double y, final double z, final double w,
            final double u, final double v, final double xs, final double ys, final double zs, final double ws,
            final double us, final double vs, final double x0, final double x1, final double y0, final double y1,
            final double z0, final double z1, final double w0, final double w1, final double u0, final double u1,
            final double v0, final double v1, final int seed) {
        final double val1 = interpolate_XYZWU_6(x, y, z, w, u, v, xs, ys, zs, ws, us, x0, x1, y0, y1, z0, z1, w0, w1,
                u0, u1, v0, seed);
        final double val2 = interpolate_XYZWU_6(x, y, z, w, u, v, xs, ys, zs, ws, us, x0, x1, y0, y1, z0, z1, w0, w1,
                u0, u1, v1, seed);

        return Maths.Lerp(vs, val1, val2);
    }

}
