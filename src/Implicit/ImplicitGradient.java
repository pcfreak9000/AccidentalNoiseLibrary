package Implicit;

public final class ImplicitGradient extends ImplicitModuleBase {
    private double gradientX0;

    private double gradientY0;

    private double gradientZ0;

    private double gradientW0;

    private double gradientU0;

    private double gradientV0;

    private double gradientX1;

    private double gradientY1;

    private double gradientZ1;

    private double gradientW1;

    private double gradientU1;

    private double gradientV1;

    private double length2;

    private double length3;

    private double length4;

    private double length6;

    /**
     * 0,1, 0,1, 0,1, 0,1, 0,1, 0,1
     */
    public ImplicitGradient(final double x0, final double x1, final double y0, final double y1, final double z0,
            final double z1, final double w0, final double w1, final double u0, final double u1, final double v0,
            final double v1) {
        this.SetGradient(x0, x1, y0, y1, z0, z1, w0, w1, u0, u1, v0, v1);
    }

    public void SetGradient(final double x0, final double x1, final double y0, final double y1) {
        this.SetGradient(x0, x1, y0, y1, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00);
    }

    public void SetGradient(final double x0, final double x1, final double y0, final double y1, final double z0,
            final double z1) {
        this.SetGradient(x0, x1, y0, y1, z0, z1, 0.00, 0.00, 0.00, 0.00, 0.00, 0.00);
    }

    public void SetGradient(final double x0, final double x1, final double y0, final double y1, final double z0,
            final double z1, final double w0, final double w1) {
        this.SetGradient(x0, x1, y0, y1, z0, z1, w0, w1, 0.00, 0.00, 0.00, 0.00);
    }

    public void SetGradient(final double x0, final double x1, final double y0, final double y1, final double z0,
            final double z1, final double w0, final double w1, final double u0, final double u1, final double v0,
            final double v1) {
        this.gradientX0 = x0;
        this.gradientY0 = y0;
        this.gradientZ0 = z0;
        this.gradientW0 = w0;
        this.gradientU0 = u0;
        this.gradientV0 = v0;

        this.gradientX1 = x1 - x0;
        this.gradientY1 = y1 - y0;
        this.gradientZ1 = z1 - z0;
        this.gradientW1 = w1 - w0;
        this.gradientU1 = u1 - u0;
        this.gradientV1 = v1 - v0;

        this.length2 = (this.gradientX1 * this.gradientX1 + this.gradientY1 * this.gradientY1);
        this.length3 = (this.gradientX1 * this.gradientX1 + this.gradientY1 * this.gradientY1
                + this.gradientZ1 * this.gradientZ1);
        this.length4 = (this.gradientX1 * this.gradientX1 + this.gradientY1 * this.gradientY1
                + this.gradientZ1 * this.gradientZ1 + this.gradientW1 * this.gradientW1);
        this.length6 = (this.gradientX1 * this.gradientX1 + this.gradientY1 * this.gradientY1
                + this.gradientZ1 * this.gradientZ1 + this.gradientW1 * this.gradientW1
                + this.gradientU1 * this.gradientU1 + this.gradientV1 * this.gradientV1);
    }

    @Override
    public double Get(final double x, final double y) {
        final double dx = x - this.gradientX0;
        final double dy = y - this.gradientY0;
        double dp = dx * this.gradientX1 + dy * this.gradientY1;
        dp /= this.length2;
        return dp;
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        final double dx = x - this.gradientX0;
        final double dy = y - this.gradientY0;
        final double dz = z - this.gradientZ0;
        double dp = dx * this.gradientX1 + dy * this.gradientY1 + dz * this.gradientZ1;
        dp /= this.length3;
        return dp;
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        final double dx = x - this.gradientX0;
        final double dy = y - this.gradientY0;
        final double dz = z - this.gradientZ0;
        final double dw = w - this.gradientW0;
        double dp = dx * this.gradientX1 + dy * this.gradientY1 + dz * this.gradientZ1 + dw * this.gradientW1;
        dp /= this.length4;
        return dp;
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        final double dx = x - this.gradientX0;
        final double dy = y - this.gradientY0;
        final double dz = z - this.gradientZ0;
        final double dw = w - this.gradientW0;
        final double du = u - this.gradientU0;
        final double dv = v - this.gradientV0;
        double dp = dx * this.gradientX1 + dy * this.gradientY1 + dz * this.gradientZ1 + dw * this.gradientW1
                + du * this.gradientU1 + dv * this.gradientV1;
        dp /= this.length6;
        return dp;
    }
}
