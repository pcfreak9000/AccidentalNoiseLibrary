package Implicit;

public final class ImplicitLog extends ImplicitModuleBase {
    public ImplicitLog(final ImplicitModuleBase source) {
        this.Source = source;
    }

    private final ImplicitModuleBase Source;

    @Override
    public double Get(final double x, final double y) {
        return Math.log(this.Source.Get(x, y));
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        return Math.log(this.Source.Get(x, y, z));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return Math.log(this.Source.Get(x, y, z, w));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.log(this.Source.Get(x, y, z, w, u, v));
    }
}
