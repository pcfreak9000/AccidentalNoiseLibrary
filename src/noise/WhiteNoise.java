package noise;

import Enums.InterpolationType;
import util.Maths;

public class WhiteNoise implements Noise {

    @Override
    public double noise(final double x, final double y, final int seed, final InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, seed)];

    }

    @Override
    public double noise(final double x, final double y, final double z, final int seed,
            final InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, z, seed)];

    }

    @Override
    public double noise(final double x, final double y, final double z, final double w, final int seed,
            final InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, z, w, seed)];

    }

    @Override
    public double noise(final double x, final double y, final double z, final double w, final double u, final double v,
            final int seed, final InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, z, w, u, v, seed)];

    }

}
