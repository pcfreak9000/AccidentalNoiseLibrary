package Implicit;

public final class ImplicitTan extends ImplicitModuleBase {
    public ImplicitTan(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase Source;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    @Override
    public double get(final double x, final double y) {
        return Math.tan(this.Source.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return Math.tan(this.Source.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return Math.tan(this.Source.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.tan(this.Source.get(x, y, z, w, u, v));
    }
}
