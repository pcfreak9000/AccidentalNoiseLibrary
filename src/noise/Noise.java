package noise;

import Enums.InterpolationType;

public interface Noise {
    
    double noise(double x, double y, int seed, InterpolationType interpolator);
    
    double noise(double x, double y, double z, int seed, InterpolationType interpolator);
    
    double noise(double x, double y, double z, double w, int seed, InterpolationType interpolator);
    
    double noise(double x, double y, double z, double w, double u, double v, int seed, InterpolationType interpolator);
    
}
