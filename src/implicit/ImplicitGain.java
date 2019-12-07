package implicit;

import util.Maths;

public final class ImplicitGain extends ImplicitModuleBase {
    public ImplicitGain(final ImplicitModuleBase source, final double gain) {
        this.Source = source;
        this.Gain = new ImplicitConstant(gain);
    }

    public ImplicitGain(final ImplicitModuleBase source, final ImplicitModuleBase gain) {
        this.Source = source;
        this.Gain = gain;
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase Gain;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getGain() {
        return this.Gain;
    }

    public void setGain(final ImplicitModuleBase gain) {
        this.Gain = gain;
    }

    @Override
    public double get(final double x, final double y) {
        return Maths.Gain(this.Gain.get(x, y), this.Source.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return Maths.Gain(this.Gain.get(x, y, z), this.Source.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return Maths.Gain(this.Gain.get(x, y, z, w), this.Source.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Maths.Gain(this.Gain.get(x, y, z, w, u, v), this.Source.get(x, y, z, w, u, v));
    }
}
