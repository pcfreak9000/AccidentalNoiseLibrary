package Implicit;

import util.Maths;

public final class ImplicitCeiling extends ImplicitModuleBase {
    public ImplicitCeiling(final ImplicitModuleBase source) {
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
        return Maths.ceil(this.Source.Get(x, y));
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        return Maths.ceil(this.Source.Get(x, y, z));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return Maths.ceil(this.Source.Get(x, y, z, w));
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Maths.ceil(this.Source.Get(x, y, z, w, u, v));
    }
}
