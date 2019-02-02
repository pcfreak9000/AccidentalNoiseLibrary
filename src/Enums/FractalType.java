package Enums;

public enum FractalType {
    FractionalBrownianMotion(1, 0, 0), RidgedMulti(0.9, 2, 1), Billow(1, 0, 0), Multi(1, 0, 0),
    HybridMulti(0.25, 1, 0.7);

    public final double h, gain, offset;

    private FractalType(final double h, final double gain, final double offset) {
        this.h = h;
        this.gain = gain;
        this.offset = offset;
    }

}
