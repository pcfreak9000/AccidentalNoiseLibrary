﻿package implicit;

import util.Maths;

public final class ImplicitBias extends ImplicitModuleBase {
    public ImplicitBias(final ImplicitModuleBase source, final double bias) {
        this.Source = source;
        this.Bias = new ImplicitConstant(bias);
    }

    public ImplicitBias(final ImplicitModuleBase source, final ImplicitModuleBase bias) {
        this.Source = source;
        this.Bias = bias;
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase Bias;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getBias() {
        return this.Bias;
    }

    public void setBias(final ImplicitModuleBase bias) {
        this.Bias = bias;
    }

    @Override
    public double get(final double x, final double y) {
        return Maths.Bias(this.Bias.get(x, y), this.Source.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return Maths.Bias(this.Bias.get(x, y, z), this.Source.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return Maths.Bias(this.Bias.get(x, y, z, w), this.Source.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Maths.Bias(this.Bias.get(x, y, z, w, u, v), this.Source.get(x, y, z, w, u, v));
    }
}
