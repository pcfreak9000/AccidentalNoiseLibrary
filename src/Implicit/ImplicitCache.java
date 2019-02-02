package Implicit;

import noise.Cache;

public final class ImplicitCache extends ImplicitModuleBase {
    private final Cache cache2D = new Cache();

    private final Cache cache3D = new Cache();

    private final Cache cache4D = new Cache();

    private final Cache cache6D = new Cache();

    public ImplicitCache(final ImplicitModuleBase source) {
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
        if (!this.cache2D.IsValid || this.cache2D.X != x || this.cache2D.Y != y) {
            this.cache2D.X = x;
            this.cache2D.Y = y;
            this.cache2D.IsValid = true;
            this.cache2D.Value = this.Source.Get(x, y);
        }
        return this.cache2D.Value;
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        if (!this.cache3D.IsValid || this.cache3D.X != x || this.cache3D.Y != y || this.cache3D.Z != z) {
            this.cache3D.X = x;
            this.cache3D.Y = y;
            this.cache3D.Z = z;
            this.cache3D.IsValid = true;
            this.cache3D.Value = this.Source.Get(x, y, z);
        }
        return this.cache3D.Value;
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        if (!this.cache4D.IsValid || this.cache4D.X != x || this.cache4D.Y != y || this.cache4D.Z != z
                || this.cache4D.W != w) {
            this.cache4D.X = x;
            this.cache4D.Y = y;
            this.cache4D.Z = z;
            this.cache4D.W = w;
            this.cache4D.IsValid = true;
            this.cache4D.Value = this.Source.Get(x, y, z, w);
        }
        return this.cache4D.Value;
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        if (!this.cache6D.IsValid || this.cache6D.X != x || this.cache6D.Y != y || this.cache6D.Z != z
                || this.cache6D.W != w || this.cache6D.U != u || this.cache6D.V != v) {
            this.cache6D.X = x;
            this.cache6D.Y = y;
            this.cache6D.Z = z;
            this.cache6D.W = w;
            this.cache6D.U = u;
            this.cache6D.V = v;
            this.cache6D.IsValid = true;
            this.cache6D.Value = this.Source.Get(x, y, z, w, u, v);
        }
        return this.cache6D.Value;
    }
}
