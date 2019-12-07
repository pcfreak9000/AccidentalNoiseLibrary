package implicit;

public final class ImplicitPow extends ImplicitModuleBase {
    public ImplicitPow(final ImplicitModuleBase source, final double power) {
        this.Source = source;
        this.Power = new ImplicitConstant(power);
    }

    public ImplicitPow(final ImplicitModuleBase source, final ImplicitModuleBase power) {
        this.Source = source;
        this.Power = power;
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase Power;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getPower() {
        return this.Power;
    }

    public void setPower(final ImplicitModuleBase power) {
        this.Power = power;
    }

    @Override
    public double get(final double x, final double y) {
        return Math.pow(this.Source.get(x, y), this.Power.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return Math.pow(this.Source.get(x, y, z), this.Power.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return Math.pow(this.Source.get(x, y, z, w), this.Power.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.pow(this.Source.get(x, y, z, w, u, v), this.Power.get(x, y, z, w, u, v));
    }
}
