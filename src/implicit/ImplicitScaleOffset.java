package implicit;

public final class ImplicitScaleOffset extends ImplicitModuleBase {
    public ImplicitScaleOffset(final ImplicitModuleBase source, final double scale, final double offset) {
        this.Source = source;
        this.Scale = new ImplicitConstant(scale);
        this.Offset = new ImplicitConstant(offset);
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase Scale;

    private ImplicitModuleBase Offset;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getScale() {
        return this.Scale;
    }

    public void setScale(final ImplicitModuleBase scale) {
        this.Scale = scale;
    }

    public ImplicitModuleBase getOffset() {
        return this.Offset;
    }

    public void setOffset(final ImplicitModuleBase offset) {
        this.Offset = offset;
    }

    @Override
    public double get(final double x, final double y) {
        return this.Source.get(x, y) * this.Scale.get(x, y) + this.Offset.get(x, y);
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return this.Source.get(x, y, z) * this.Scale.get(x, y, z) + this.Offset.get(x, y, z);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return this.Source.get(x, y, z, w) * this.Scale.get(x, y, z, w) + this.Offset.get(x, y, z, w);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return this.Source.get(x, y, z, w, u, v) * this.Scale.get(x, y, z, w, u, v) + this.Offset.get(x, y, z, w, u, v);
    }
}
