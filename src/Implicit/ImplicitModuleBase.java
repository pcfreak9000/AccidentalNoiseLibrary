package Implicit;

public abstract class ImplicitModuleBase {

    public static final int MAX_SOURCES = 20;

    public int getSeed() {
        return 0;
    }

    public void setSeed(final int seed) {

    }

    public double Get(final double x, final double y) {
        return 0.00;
    }

    public double Get(final double x, final double y, final double z) {
        return 0.00;
    }

    public double Get(final double x, final double y, final double z, final double w) {
        return 0.00;
    }

    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return 0.00;
    }

    /*
     * TODO public static implicit operator ImplicitModuleBase(double value) {
     * return new ImplicitConstant(value); }
     */
}
