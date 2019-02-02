package implicit;

public final class ImplicitSphere extends ImplicitModuleBase {
    public ImplicitSphere(final double xCenter, final double yCenter, final double zCenter, final double wCenter,
            final double uCenter, final double vCenter, final double radius) {
        this.XCenter = new ImplicitConstant(xCenter);
        this.YCenter = new ImplicitConstant(yCenter);
        this.ZCenter = new ImplicitConstant(zCenter);
        this.WCenter = new ImplicitConstant(wCenter);
        this.UCenter = new ImplicitConstant(uCenter);
        this.VCenter = new ImplicitConstant(vCenter);
        this.Radius = new ImplicitConstant(radius);
    }

    private ImplicitModuleBase XCenter;

    private ImplicitModuleBase YCenter;

    private ImplicitModuleBase ZCenter;

    private ImplicitModuleBase WCenter;

    private ImplicitModuleBase UCenter;

    private ImplicitModuleBase VCenter;

    private ImplicitModuleBase Radius;

    public ImplicitModuleBase getXCenter() {
        return this.XCenter;
    }

    public void setXCenter(final ImplicitModuleBase xCenter) {
        this.XCenter = xCenter;
    }

    public ImplicitModuleBase getYCenter() {
        return this.YCenter;
    }

    public void setYCenter(final ImplicitModuleBase yCenter) {
        this.YCenter = yCenter;
    }

    public ImplicitModuleBase getZCenter() {
        return this.ZCenter;
    }

    public void setZCenter(final ImplicitModuleBase zCenter) {
        this.ZCenter = zCenter;
    }

    public ImplicitModuleBase getWCenter() {
        return this.WCenter;
    }

    public void setWCenter(final ImplicitModuleBase wCenter) {
        this.WCenter = wCenter;
    }

    public ImplicitModuleBase getUCenter() {
        return this.UCenter;
    }

    public void setUCenter(final ImplicitModuleBase uCenter) {
        this.UCenter = uCenter;
    }

    public ImplicitModuleBase getVCenter() {
        return this.VCenter;
    }

    public void setVCenter(final ImplicitModuleBase vCenter) {
        this.VCenter = vCenter;
    }

    public ImplicitModuleBase getRadius() {
        return this.Radius;
    }

    public void setRadius(final ImplicitModuleBase radius) {
        this.Radius = radius;
    }

    @Override
    public double get(final double x, final double y) {
        final double dx = x - this.XCenter.get(x, y);
        final double dy = y - this.YCenter.get(x, y);
        final double len = Math.sqrt(dx * dx + dy * dy);
        final double rad = this.Radius.get(x, y);
        double i = (rad - len) / rad;
        if (i < 0) {
            i = 0;
        }
        if (i > 1) {
            i = 1;
        }

        return i;
    }

    @Override
    public double get(final double x, final double y, final double z) {
        final double dx = x - this.XCenter.get(x, y, z);
        final double dy = y - this.YCenter.get(x, y, z);
        final double dz = z - this.ZCenter.get(x, y, z);
        final double len = Math.sqrt(dx * dx + dy * dy + dz * dz);
        final double rad = this.Radius.get(x, y, z);
        double i = (rad - len) / rad;
        if (i < 0) {
            i = 0;
        }
        if (i > 1) {
            i = 1;
        }

        return i;
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        final double dx = x - this.XCenter.get(x, y, z, w);
        final double dy = y - this.YCenter.get(x, y, z, w);
        final double dz = z - this.ZCenter.get(x, y, z, w);
        final double dw = w - this.WCenter.get(x, y, z, w);
        final double len = Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw);
        final double rad = this.Radius.get(x, y, z, w);
        double i = (rad - len) / rad;
        if (i < 0) {
            i = 0;
        }
        if (i > 1) {
            i = 1;
        }

        return i;
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        final double dx = x - this.XCenter.get(x, y, z, w, u, v);
        final double dy = y - this.YCenter.get(x, y, z, w, u, v);
        final double dz = z - this.ZCenter.get(x, y, z, w, u, v);
        final double dw = w - this.WCenter.get(x, y, z, w, u, v);
        final double du = u - this.UCenter.get(x, y, z, w, u, v);
        final double dv = v - this.VCenter.get(x, y, z, w, u, v);
        final double len = Math.sqrt(dx * dx + dy * dy + dz * dz + dw * dw + du * du + dv * dv);
        final double rad = this.Radius.get(x, y, z, w, u, v);
        double i = (rad - len) / rad;
        if (i < 0) {
            i = 0;
        }
        if (i > 1) {
            i = 1;
        }

        return i;
    }
}
