package noise;

import Enums.InterpolationType;

public class GradientValueNoise implements Noise {

    private static final GradientNoise GRADIANT_NOISE = new GradientNoise();
    private static final ValueNoise VALUE_NOISE = new ValueNoise();

    @Override
    public double noise(final double x, final double y, final int seed, final InterpolationType interpolator) {

        return GRADIANT_NOISE.noise(x, y, seed, interpolator) + VALUE_NOISE.noise(x, y, seed, interpolator);
    }

    @Override
    public double noise(final double x, final double y, final double z, final int seed,
            final InterpolationType interpolator) {

        return GRADIANT_NOISE.noise(x, y, z, seed, interpolator) + VALUE_NOISE.noise(x, y, z, seed, interpolator);
    }

    @Override
    public double noise(final double x, final double y, final double z, final double w, final int seed,
            final InterpolationType interpolator) {

        return GRADIANT_NOISE.noise(x, y, z, w, seed, interpolator) + VALUE_NOISE.noise(x, y, z, w, seed, interpolator);
    }

    @Override
    public double noise(final double x, final double y, final double z, final double w, final double u, final double v,
            final int seed, final InterpolationType interpolator) {

        return GRADIANT_NOISE.noise(x, y, z, w, u, v, seed, interpolator)
                + VALUE_NOISE.noise(x, y, z, w, u, v, seed, interpolator);
    }

}
