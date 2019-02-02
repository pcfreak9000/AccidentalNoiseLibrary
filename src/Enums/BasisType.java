package Enums;

import noise.*;

public enum BasisType {
    Value(new ValueNoise()), Gradient(new GradientNoise()), GradientValue(new GradientValueNoise()),
    Simplex(new SimplexNoise()), White(new WhiteNoise());
    
    public final Noise noiseGen;
    
    private BasisType(Noise mynoise) {
        this.noiseGen = mynoise;
    }
    
}
