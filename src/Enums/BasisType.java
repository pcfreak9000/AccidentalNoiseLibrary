package Enums;

import noise.GradientNoise;
import noise.GradientValueNoise;
import noise.Noise;
import noise.SimplexNoise;
import noise.ValueNoise;
import noise.WhiteNoise;

public enum BasisType {
    Value(new ValueNoise()), Gradient(new GradientNoise()), GradientValue(new GradientValueNoise()),
    Simplex(new SimplexNoise()), White(new WhiteNoise());
    
    public final Noise noiseGen;
    
    private BasisType(final Noise mynoise) {
        this.noiseGen = mynoise;
    }
    
}
