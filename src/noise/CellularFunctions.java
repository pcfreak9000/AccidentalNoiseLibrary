package noise;

import util.Maths;

public class CellularFunctions {

    private static final ValueNoise VALUE_NOISE = new ValueNoise();

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
        final double xInt = Maths.floor(x);
        final double yInt = Maths.floor(y);

        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }

        for (double ycur = yInt - 3; ycur <= yInt + 3; ++ycur) {
            for (double xcur = xInt - 3; xcur <= xInt + 3; ++xcur) {
                final double xpos = xcur + VALUE_NOISE.internalNoise(x, y, xcur, ycur, seed);
                final double ypos = ycur + VALUE_NOISE.internalNoise(x, y, xcur, ycur, seed + 1);
                final double xdist = xpos - x;
                final double ydist = ypos - y;
                final double dist = (xdist * xdist + ydist * ydist);
                final double xval = Maths.floor(xpos);
                final double yval = Maths.floor(ypos);
                final double dsp = VALUE_NOISE.internalNoise(x, y, xval, yval, seed + 3);
                AddDistance(f, disp, dist, dsp);
            }
        }
    }

    public static void CellularFunction(final double x, final double y, final double z, final int seed,
            final double[] f, final double[] disp) {
        final double xInt = Maths.floor(x);
        final double yInt = Maths.floor(y);
        final double zInt = Maths.floor(z);

        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }

        for (double zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
            for (double ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                for (double xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                    final double xpos = xcur + VALUE_NOISE.internalNoise(x, y, z, xcur, ycur, zcur, seed);
                    final double ypos = ycur + VALUE_NOISE.internalNoise(x, y, z, xcur, ycur, zcur, seed + 1);
                    final double zpos = zcur + VALUE_NOISE.internalNoise(x, y, z, xcur, ycur, zcur, seed + 2);
                    final double xdist = xpos - x;
                    final double ydist = ypos - y;
                    final double zdist = zpos - z;
                    final double dist = (xdist * xdist + ydist * ydist + zdist * zdist);
                    final double xval = Maths.floor(xpos);
                    final double yval = Maths.floor(ypos);
                    final double zval = Maths.floor(zpos);
                    final double dsp = VALUE_NOISE.internalNoise(x, y, z, xval, yval, zval, seed + 3);
                    AddDistance(f, disp, dist, dsp);
                }
            }
        }
    }

    public static void CellularFunction(final double x, final double y, final double z, final double w, final int seed,
            final double[] f, final double[] disp) {
        final double xInt = Maths.floor(x);
        final double yInt = Maths.floor(y);
        final double zInt = Maths.floor(z);
        final double wInt = Maths.floor(w);

        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }

        for (double wcur = wInt - 2; wcur <= wInt + 2; ++wcur) {
            for (double zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
                for (double ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                    for (double xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                        final double xpos = xcur + VALUE_NOISE.internalNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed);
                        final double ypos = ycur
                                + VALUE_NOISE.internalNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 1);
                        final double zpos = zcur
                                + VALUE_NOISE.internalNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 2);
                        final double wpos = wcur
                                + VALUE_NOISE.internalNoise(x, y, z, w, xcur, ycur, zcur, wcur, seed + 3);
                        final double xdist = xpos - x;
                        final double ydist = ypos - y;
                        final double zdist = zpos - z;
                        final double wdist = wpos - w;
                        final double dist = (xdist * xdist + ydist * ydist + zdist * zdist + wdist * wdist);
                        final double xval = Maths.floor(xpos);
                        final double yval = Maths.floor(ypos);
                        final double zval = Maths.floor(zpos);
                        final double wval = Maths.floor(wpos);
                        final double dsp = VALUE_NOISE.internalNoise(x, y, z, w, xval, yval, zval, wval, seed + 3);
                        AddDistance(f, disp, dist, dsp);
                    }
                }
            }
        }
    }

    public static void CellularFunction(final double x, final double y, final double z, final double w, final double u,
            final double v, final int seed, final double[] f, final double[] disp) {
        final double xInt = Maths.floor(x);
        final double yInt = Maths.floor(y);
        final double zInt = Maths.floor(z);
        final double wInt = Maths.floor(w);
        final double uInt = Maths.floor(u);
        final double vInt = Maths.floor(v);

        for (int c = 0; c < 4; ++c) {
            f[c] = 99999.0;
            disp[c] = 0.0;
        }

        for (double vcur = vInt - 1; vcur <= vInt + 1; ++vcur) {
            for (double ucur = uInt - 1; ucur <= uInt + 1; ++ucur) {
                for (double wcur = wInt - 2; wcur <= wInt + 2; ++wcur) {
                    for (double zcur = zInt - 2; zcur <= zInt + 2; ++zcur) {
                        for (double ycur = yInt - 2; ycur <= yInt + 2; ++ycur) {
                            for (double xcur = xInt - 2; xcur <= xInt + 2; ++xcur) {
                                final double xpos = xcur + VALUE_NOISE.internalNoise(x, y, z, w, u, v, xcur, ycur, zcur,
                                        wcur, ucur, vcur, seed);
                                final double ypos = ycur + VALUE_NOISE.internalNoise(x, y, z, w, u, v, xcur, ycur, zcur,
                                        wcur, ucur, vcur, seed + 1);
                                final double zpos = zcur + VALUE_NOISE.internalNoise(x, y, z, w, u, v, xcur, ycur, zcur,
                                        wcur, ucur, vcur, seed + 2);
                                final double wpos = wcur + VALUE_NOISE.internalNoise(x, y, z, w, u, v, xcur, ycur, zcur,
                                        wcur, ucur, vcur, seed + 3);
                                final double upos = ucur + VALUE_NOISE.internalNoise(x, y, z, w, u, v, xcur, ycur, zcur,
                                        wcur, ucur, vcur, seed + 4);
                                final double vpos = vcur + VALUE_NOISE.internalNoise(x, y, z, w, u, v, xcur, ycur, zcur,
                                        wcur, ucur, vcur, seed + 5);
                                final double xdist = xpos - x;
                                final double ydist = ypos - y;
                                final double zdist = zpos - z;
                                final double wdist = wpos - w;
                                final double udist = upos - u;
                                final double vdist = vpos - v;
                                final double dist = (xdist * xdist + ydist * ydist + zdist * zdist + wdist * wdist
                                        + udist * udist + vdist * vdist);
                                final double xval = Maths.floor(xpos);
                                final double yval = Maths.floor(ypos);
                                final double zval = Maths.floor(zpos);
                                final double wval = Maths.floor(wpos);
                                final double uval = Maths.floor(upos);
                                final double vval = Maths.floor(vpos);
                                final double dsp = VALUE_NOISE.internalNoise(x, y, z, w, u, v, xval, yval, zval, wval,
                                        uval, vval, seed + 6);
                                AddDistance(f, disp, dist, dsp);
                            }
                        }
                    }
                }
            }
        }
    }

}