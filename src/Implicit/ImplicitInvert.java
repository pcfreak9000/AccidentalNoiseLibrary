package Implicit;

public final class ImplicitInvert extends ImplicitModuleBase {
    public ImplicitInvert(final ImplicitModuleBase source) {
        this.Source = source;
    }

    private ImplicitModuleBase Source;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    @Override
    public double Get(final double x, final double y) {
        return -this.Source.Get(x, y);
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        return -this.Source.Get(x, y, z);
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return -this.Source.Get(x, y, z, w);
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return -this.Source.Get(x, y, z, w, u, v);
    }
}
