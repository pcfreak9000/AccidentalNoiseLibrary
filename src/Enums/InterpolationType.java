package Enums;

public enum InterpolationType {
    None, Linear, Cubic, Quintic;
    
    public double interpolate(final double t) {
        switch (this) {
        case Cubic:
            return (t * t * (3 - 2 * t));
        case Linear:
            return t;
        case None:
            return 0;
        case Quintic:
        default:
            return t * t * t * (t * (t * 6 - 15) + 10);
        
        }
    }
}
