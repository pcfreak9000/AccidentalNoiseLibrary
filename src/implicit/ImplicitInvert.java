﻿package implicit;

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
    public double get(final double x, final double y) {
        return -this.Source.get(x, y);
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return -this.Source.get(x, y, z);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return -this.Source.get(x, y, z, w);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return -this.Source.get(x, y, z, w, u, v);
    }
}
