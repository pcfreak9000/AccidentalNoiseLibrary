package Implicit;

import util.Maths;

public final class ImplicitTiers extends ImplicitModuleBase {
    public ImplicitTiers(final ImplicitModuleBase source, final int tiers, final boolean smooth) {
        this.Source = source;
        this.Tiers = tiers;
        this.Smooth = smooth;
    }

    private ImplicitModuleBase Source;

    private int Tiers;

    private boolean Smooth;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public int getTiers() {
        return this.Tiers;
    }

    public void setTiers(final int tiers) {
        this.Tiers = tiers;
    }

    public boolean isSmooth() {
        return this.Smooth;
    }

    public void setSmooth(final boolean smooth) {
        this.Smooth = smooth;
    }

    @Override
    public double get(final double x, final double y) {
        int numsteps = this.Tiers;
        if (this.Smooth) {
            --numsteps;
        }
        final double val = this.Source.get(x, y);
        double tb = Maths.floor(val * numsteps);
        double tt = tb + 1.0;
        final double t = val * numsteps - tb;
        tb /= numsteps;
        tt /= numsteps;
        final double u = (this.Smooth ? Maths.QuinticBlend(t) : 0.0);
        return tb + u * (tt - tb);
    }

    @Override
    public double get(final double x, final double y, final double z) {
        int numsteps = this.Tiers;
        if (this.Smooth) {
            --numsteps;
        }
        final double val = this.Source.get(x, y, z);
        double tb = Maths.floor(val * numsteps);
        double tt = tb + 1.0;
        final double t = val * numsteps - tb;
        tb /= numsteps;
        tt /= numsteps;
        final double u = (this.Smooth ? Maths.QuinticBlend(t) : 0.0);
        return tb + u * (tt - tb);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        int numsteps = this.Tiers;
        if (this.Smooth) {
            --numsteps;
        }
        final double val = this.Source.get(x, y, z, w);
        double tb = Maths.floor(val * numsteps);
        double tt = tb + 1.0;
        final double t = val * numsteps - tb;
        tb /= numsteps;
        tt /= numsteps;
        final double u = (this.Smooth ? Maths.QuinticBlend(t) : 0.0);
        return tb + u * (tt - tb);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        int numsteps = this.Tiers;
        if (this.Smooth) {
            --numsteps;
        }
        final double val = this.Source.get(x, y, z, w, u, v);
        double tb = Maths.floor(val * numsteps);
        double tt = tb + 1.0;
        final double t = val * numsteps - tb;
        tb /= numsteps;
        tt /= numsteps;
        final double s = (this.Smooth ? Maths.QuinticBlend(t) : 0.0);
        return tb + s * (tt - tb);
    }
}
