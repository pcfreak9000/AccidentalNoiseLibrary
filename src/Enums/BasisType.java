package Enums;

import top.Noise;

public enum BasisType {
    Value, Gradient, GradientValue, Simplex, White;
    
    public double noise2d(double x, double y, int seed, InterpolationType interp) {
        switch (this) {
        case Gradient:
            return Noise.GradientNoise(x, y, seed, interp);
        case GradientValue:
            return Noise.GradientValueNoise(x, y, seed, interp);
        case Simplex:
            return Noise.SimplexNoise(x, y, seed, interp);
        case Value:
            return Noise.ValueNoise(x, y, seed, interp);
        case White:
            return Noise.WhiteNoise(x, y, seed, interp);
        default:
            return Noise.GradientNoise(x, y, seed, interp);
        
        }
    }
    
    public double noise3d(double x, double y, double z, int seed, InterpolationType interp) {
        switch (this) {
        case Gradient:
            return Noise.GradientNoise(x, y, z, seed, interp);
        case GradientValue:
            return Noise.GradientValueNoise(x, y, z, seed, interp);
        case Simplex:
            return Noise.SimplexNoise(x, y, z, seed, interp);
        case Value:
            return Noise.ValueNoise(x, y, z, seed, interp);
        case White:
            return Noise.WhiteNoise(x, y, z, seed, interp);
        default:
            return Noise.GradientNoise(x, y, z, seed, interp);
        
        }
    }
    
    public double noise4d(double x, double y, double z, double w, int seed, InterpolationType interp) {
        switch (this) {
        case Gradient:
            return Noise.GradientNoise(x, y, z, w, seed, interp);
        case GradientValue:
            return Noise.GradientValueNoise(x, y, z, w, seed, interp);
        case Simplex:
            return Noise.SimplexNoise(x, y, z, w, seed, interp);
        case Value:
            return Noise.ValueNoise(x, y, z, w, seed, interp);
        case White:
            return Noise.WhiteNoise(x, y, z, w, seed, interp);
        default:
            return Noise.GradientNoise(x, y, z, w, seed, interp);
        
        }
    }
    
    public double noise2d(double x, double y, double z, double w, double u, double v, int seed,
            InterpolationType interp) {
        switch (this) {
        case Gradient:
            return Noise.GradientNoise(x, y, z, w, u, v, seed, interp);
        case GradientValue:
            return Noise.GradientValueNoise(x, y, z, w, u, v, seed, interp);
        case Simplex:
            return Noise.SimplexNoise(x, y, z, w, u, v, seed, interp);
        case Value:
            return Noise.ValueNoise(x, y, z, w, u, v, seed, interp);
        case White:
            return Noise.WhiteNoise(x, y, z, w, u, v, seed, interp);
        default:
            return Noise.GradientNoise(x, y, z, w, u, v, seed, interp);
        
        }
    }
}
