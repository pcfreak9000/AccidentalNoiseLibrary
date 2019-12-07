package implicit;

public final class ImplicitTranslateDomain extends ImplicitModuleBase {
    public ImplicitTranslateDomain(final ImplicitModuleBase source, final double xAxis, final double yAxis,
            final double zAxis, final double wAxis, final double uAxis, final double vAxis) {
        this.Source = source;
        this.XAxis = new ImplicitConstant(xAxis);
        this.YAxis = new ImplicitConstant(yAxis);
        this.ZAxis = new ImplicitConstant(zAxis);
        this.WAxis = new ImplicitConstant(wAxis);
        this.UAxis = new ImplicitConstant(uAxis);
        this.VAxis = new ImplicitConstant(vAxis);
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase XAxis;

    private ImplicitModuleBase YAxis;

    private ImplicitModuleBase ZAxis;

    private ImplicitModuleBase WAxis;

    private ImplicitModuleBase UAxis;

    private ImplicitModuleBase VAxis;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getXAxis() {
        return this.XAxis;
    }

    public void setXAxis(final ImplicitModuleBase xAxis) {
        this.XAxis = xAxis;
    }

    public ImplicitModuleBase getYAxis() {
        return this.YAxis;
    }

    public void setYAxis(final ImplicitModuleBase yAxis) {
        this.YAxis = yAxis;
    }

    public ImplicitModuleBase getZAxis() {
        return this.ZAxis;
    }

    public void setZAxis(final ImplicitModuleBase zAxis) {
        this.ZAxis = zAxis;
    }

    public ImplicitModuleBase getWAxis() {
        return this.WAxis;
    }

    public void setWAxis(final ImplicitModuleBase wAxis) {
        this.WAxis = wAxis;
    }

    public ImplicitModuleBase getUAxis() {
        return this.UAxis;
    }

    public void setUAxis(final ImplicitModuleBase uAxis) {
        this.UAxis = uAxis;
    }

    public ImplicitModuleBase getVAxis() {
        return this.VAxis;
    }

    public void setVAxis(final ImplicitModuleBase vAxis) {
        this.VAxis = vAxis;
    }

    @Override
    public double get(final double x, final double y) {
        return this.Source.get(x + this.XAxis.get(x, y), y + this.YAxis.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return this.Source.get(x + this.XAxis.get(x, y, z), y + this.YAxis.get(x, y, z), z + this.ZAxis.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return this.Source.get(x + this.XAxis.get(x, y, z, w), y + this.YAxis.get(x, y, z, w),
                z + this.ZAxis.get(x, y, z, w), w + this.WAxis.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return this.Source.get(x + this.XAxis.get(x, y, z, w, u, v), y + this.YAxis.get(x, y, z, w, u, v),
                z + this.ZAxis.get(x, y, z, w, u, v), w + this.WAxis.get(x, y, z, w, u, v),
                u + this.UAxis.get(x, y, z, w, u, v), v + this.VAxis.get(x, y, z, w, u, v));
    }
}
