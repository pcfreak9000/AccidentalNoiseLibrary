package Implicit;

public final class ImplicitCos extends ImplicitModuleBase {
    private ImplicitModuleBase Source;

    public ImplicitCos(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    @Override
    public double Get(final double x, final double y) {
        return Math.cos(this.Source.Get(x, y));
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        return Math.cos(this.Source.Get(x, y, z));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return Math.cos(this.Source.Get(x, y, z, w));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.cos(this.Source.Get(x, y, z, w, u, v));
    }
}
