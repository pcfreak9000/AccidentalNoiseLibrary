package noise;

import Enums.InterpolationType;
import top.Maths;
import top.NoiseLookupTable;

public class WhiteNoise implements Noise {
    
    @Override
    public double noise(double x, double y, int seed, InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, seed)];
        
    }
    
    @Override
    public double noise(double x, double y, double z, int seed, InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, z, seed)];
        
    }
    
    @Override
    public double noise(double x, double y, double z, double w, int seed, InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, z, w, seed)];
        
    }
    
    @Override
    public double noise(double x, double y, double z, double w, double u, double v, int seed,
            InterpolationType interpolator) {
        return NoiseLookupTable.WhiteNoise[Maths.HashCoordinates(x, y, z, w, u, v, seed)];
        
    }
    
}
