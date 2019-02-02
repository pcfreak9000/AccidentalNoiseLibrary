package Implicit;

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
    public double Get(final double x, final double y) {
        return Math.pow(this.Source.Get(x, y), this.Power.Get(x, y));
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        return Math.pow(this.Source.Get(x, y, z), this.Power.Get(x, y, z));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return Math.pow(this.Source.Get(x, y, z, w), this.Power.Get(x, y, z, w));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.pow(this.Source.Get(x, y, z, w, u, v), this.Power.Get(x, y, z, w, u, v));
    }
}
