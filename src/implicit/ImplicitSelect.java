package implicit;

import util.Maths;

public final class ImplicitSelect extends ImplicitModuleBase {
    public ImplicitSelect(final ImplicitModuleBase source, final double low, final double high, final double falloff,
            final double threshold) {
        this.Source = source;
        this.Low = new ImplicitConstant(low);
        this.High = new ImplicitConstant(high);
        this.Falloff = new ImplicitConstant(falloff);
        this.Threshold = new ImplicitConstant(threshold);
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase Low;

    private ImplicitModuleBase High;

    private ImplicitModuleBase Threshold;

    private ImplicitModuleBase Falloff;

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

    public ImplicitModuleBase getThreshold() {
        return this.Threshold;
    }

    public void setThreshold(final ImplicitModuleBase threshold) {
        this.Threshold = threshold;
    }

    public ImplicitModuleBase getFalloff() {
        return this.Falloff;
    }

    public void setFalloff(final ImplicitModuleBase falloff) {
        this.Falloff = falloff;
    }

    @Override
    public double get(final double x, final double y) {
        final double value = this.Source.get(x, y);
        final double falloff = this.Falloff.get(x, y);
        final double threshold = this.Threshold.get(x, y);

        if (falloff > 0.0) {
            if (value < (threshold - falloff)) {
                // Lies outside of falloff area below threshold, return first source
                return this.Low.get(x, y);
            }
            if (value > (threshold + falloff)) {
                // Lies outside of falloff area above threshold, return second source
                return this.High.get(x, y);
            }
            // Lies within falloff area.
            final double lower = threshold - falloff;
            final double upper = threshold + falloff;
            final double blend = Maths.QuinticBlend((value - lower) / (upper - lower));
            return Maths.Lerp(blend, this.Low.get(x, y), this.High.get(x, y));
        }

        return (value < threshold ? this.Low.get(x, y) : this.High.get(x, y));
    }

    @Override
    public double get(final double x, final double y, final double z) {
        final double value = this.Source.get(x, y, z);
        final double falloff = this.Falloff.get(x, y, z);
        final double threshold = this.Threshold.get(x, y, z);

        if (falloff > 0.0) {
            if (value < (threshold - falloff)) {
                // Lies outside of falloff area below threshold, return first source
                return this.Low.get(x, y, z);
            }
            if (value > (threshold + falloff)) {
                // Lies outside of falloff area above threshold, return second source
                return this.High.get(x, y, z);
            }
            // Lies within falloff area.
            final double lower = threshold - falloff;
            final double upper = threshold + falloff;
            final double blend = Maths.QuinticBlend((value - lower) / (upper - lower));
            return Maths.Lerp(blend, this.Low.get(x, y, z), this.High.get(x, y, z));
        }

        return (value < threshold ? this.Low.get(x, y, z) : this.High.get(x, y, z));
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        final double value = this.Source.get(x, y, z, w);
        final double falloff = this.Falloff.get(x, y, z, w);
        final double threshold = this.Threshold.get(x, y, z, w);

        if (falloff > 0.0) {
            if (value < (threshold - falloff)) {
                // Lies outside of falloff area below threshold, return first source
                return this.Low.get(x, y, z, w);
            }
            if (value > (threshold + falloff)) {
                // Lise outside of falloff area above threshold, return second source
                return this.High.get(x, y, z, w);
            }
            // Lies within falloff area.
            final double lower = threshold - falloff;
            final double upper = threshold + falloff;
            final double blend = Maths.QuinticBlend((value - lower) / (upper - lower));
            return Maths.Lerp(blend, this.Low.get(x, y, z, w), this.High.get(x, y, z, w));
        }

        return value < threshold ? this.Low.get(x, y, z, w) : this.High.get(x, y, z, w);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        final double value = this.Source.get(x, y, z, w, u, v);
        final double falloff = this.Falloff.get(x, y, z, w, u, v);
        final double threshold = this.Threshold.get(x, y, z, w, u, v);

        if (falloff > 0.0) {
            if (value < (threshold - falloff)) {
                // Lies outside of falloff area below threshold, return first source
                return this.Low.get(x, y, z, w, u, v);
            }
            if (value > (threshold + falloff)) {
                // Lies outside of falloff area above threshold, return second source
                return this.High.get(x, y, z, w, u, v);
            }
            // Lies within falloff area.
            final double lower = threshold - falloff;
            final double upper = threshold + falloff;
            final double blend = Maths.QuinticBlend((value - lower) / (upper - lower));
            return Maths.Lerp(blend, this.Low.get(x, y, z, w, u, v), this.High.get(x, y, z, w, u, v));
        }

        return (value < threshold ? this.Low.get(x, y, z, w, u, v) : this.High.get(x, y, z, w, u, v));
    }
}
