package implicit;

public final class ImplicitScaleDomain extends ImplicitModuleBase {
    public ImplicitScaleDomain(final ImplicitModuleBase source, final double xScale, final double yScale,
            final double zScale, final double wScale, final double uScale, final double vScale) {
        this.Source = source;
        this.XScale = new ImplicitConstant(xScale);
        this.YScale = new ImplicitConstant(yScale);
        this.ZScale = new ImplicitConstant(zScale);
        this.WScale = new ImplicitConstant(wScale);
        this.UScale = new ImplicitConstant(uScale);
        this.VScale = new ImplicitConstant(vScale);
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase XScale;

    private ImplicitModuleBase YScale;

    private ImplicitModuleBase ZScale;

    private ImplicitModuleBase WScale;

    private ImplicitModuleBase UScale;

    private ImplicitModuleBase VScale;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getXScale() {
        return this.XScale;
    }

    public void setXScale(final ImplicitModuleBase xScale) {
        this.XScale = xScale;
    }

    public ImplicitModuleBase getYScale() {
        return this.YScale;
    }

    public void setYScale(final ImplicitModuleBase yScale) {
        this.YScale = yScale;
    }

    public ImplicitModuleBase getZScale() {
        return this.ZScale;
    }

    public void setZScale(final ImplicitModuleBase zScale) {
        this.ZScale = zScale;
    }

    public ImplicitModuleBase getWScale() {
        return this.WScale;
    }

    public void setWScale(final ImplicitModuleBase wScale) {
        this.WScale = wScale;
    }

    public ImplicitModuleBase getUScale() {
        return this.UScale;
    }

    public void setUScale(final ImplicitModuleBase uScale) {
        this.UScale = uScale;
    }

    public ImplicitModuleBase getVScale() {
        return this.VScale;
    }

    public void setVScale(final ImplicitModuleBase vScale) {
        this.VScale = vScale;
    }

    public void SetScales(final double xScale, final double yScale, final double zScale, final double wScale,
            final double uScale, final double vScale) {
        this.XScale = new ImplicitConstant(xScale);
        this.YScale = new ImplicitConstant(yScale);
        this.ZScale = new ImplicitConstant(zScale);
        this.WScale = new ImplicitConstant(wScale);
        this.UScale = new ImplicitConstant(uScale);
        this.VScale = new ImplicitConstant(vScale);
    }

    @Override
    public double get(final double x, final double y) {
        return this.Source.get(x * this.XScale.get(x, y), y * this.YScale.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return this.Source.get(x * this.XScale.get(x, y, z), y * this.YScale.get(x, y, z),
                z * this.ZScale.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return this.Source.get(x * this.XScale.get(x, y, z, w), y * this.YScale.get(x, y, z, w),
                z * this.ZScale.get(x, y, z, w), w * this.WScale.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return this.Source.get(x * this.XScale.get(x, y, z, w, u, v), y * this.YScale.get(x, y, z, w, u, v),
                z * this.ZScale.get(x, y, z, w, u, v), w * this.WScale.get(x, y, z, w, u, v),
                u * this.UScale.get(x, y, z, w, u, v), v * this.VScale.get(x, y, z, w, u, v));
    }
}
