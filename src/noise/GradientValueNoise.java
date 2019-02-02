package noise;

import Enums.InterpolationType;

public class GradientValueNoise implements Noise {
    
    private static final GradientNoise GRADIANT_NOISE = new GradientNoise();
    private static final ValueNoise VALUE_NOISE = new ValueNoise();
    
    @Override
    public double noise(double x, double y, int seed, InterpolationType interpolator) {
        
        return GRADIANT_NOISE.noise(x, y, seed, interpolator) + VALUE_NOISE.noise(x, y, seed, interpolator);
    }
    
    @Override
    public double noise(double x, double y, double z, int seed, InterpolationType interpolator) {
        
        return GRADIANT_NOISE.noise(x, y, z, seed, interpolator) + VALUE_NOISE.noise(x, y, z, seed, interpolator);
    }
    
    @Override
    public double noise(double x, double y, double z, double w, int seed, InterpolationType interpolator) {
        
        return GRADIANT_NOISE.noise(x, y, z, w, seed, interpolator) + VALUE_NOISE.noise(x, y, z, w, seed, interpolator);
    }
    
    @Override
    public double noise(double x, double y, double z, double w, double u, double v, int seed,
            InterpolationType interpolator) {
        
        return GRADIANT_NOISE.noise(x, y, z, w, u, v, seed, interpolator)
                + VALUE_NOISE.noise(x, y, z, w, u, v, seed, interpolator);
    }
    
}
