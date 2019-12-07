package implicit;

import util.Maths;

public final class ImplicitClamp extends ImplicitModuleBase {
    public ImplicitClamp(final ImplicitModuleBase source, final double low, final double high) {
        this.Source = source;
        this.Low = new ImplicitConstant(low);
        this.High = new ImplicitConstant(high);
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase Low;

    private ImplicitModuleBase High;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getLow() {
        return this.Low;
    }

    public void setLow(final ImplicitModuleBase low) {
        this.Low = low;
    }

    public ImplicitModuleBase getHigh() {
        return this.High;
    }

    public void setHigh(final ImplicitModuleBase high) {
        this.High = high;
    }

    @Override
    public double get(final double x, final double y) {
        return Maths.Clamp(this.Source.get(x, y), this.Low.get(x, y), this.High.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        return Maths.Clamp(this.Source.get(x, y, z), this.Low.get(x, y, z), this.High.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return Maths.Clamp(this.Source.get(x, y, z, w), this.Low.get(x, y, z, w), this.High.get(x, y, z, w));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Maths.Clamp(this.Source.get(x, y, z, w, u, v), this.Low.get(x, y, z, w, u, v),
                this.High.get(x, y, z, w, u, v));
    }
}
