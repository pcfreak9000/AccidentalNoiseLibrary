package Implicit;

public final class ImplicitBrightContrast extends ImplicitModuleBase {
    public ImplicitBrightContrast(final ImplicitModuleBase source, final double brightness,
            final double contrastThreshold, final double contrastFactor) {
        this.Source = source;
        this.Brightness = new ImplicitConstant(brightness);
        this.ContrastThreshold = new ImplicitConstant(contrastThreshold);
        this.ContrastFactor = new ImplicitConstant(contrastFactor);
    }

    private ImplicitModuleBase Source;
    private ImplicitModuleBase Brightness;
    private ImplicitModuleBase ContrastThreshold;
    private ImplicitModuleBase ContrastFactor;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getBrightness() {
        return this.Brightness;
    }

    public void setBrightness(final ImplicitModuleBase brightness) {
        this.Brightness = brightness;
    }

    public ImplicitModuleBase getContrastThreshold() {
        return this.ContrastThreshold;
    }

    public void setContrastThreshold(final ImplicitModuleBase contrastThreshold) {
        this.ContrastThreshold = contrastThreshold;
    }

    public ImplicitModuleBase getContrastFactor() {
        return this.ContrastFactor;
    }

    public void setContrastFactor(final ImplicitModuleBase contrastFactor) {
        this.ContrastFactor = contrastFactor;
    }

    @Override
    public double Get(final double x, final double y) {
        double value = this.Source.Get(x, y);
        // Apply brightness
        value += this.Brightness.Get(x, y);

        // Subtract contrastThreshold, scale by contrastFactor, add contrastThreshold
        final double threshold = this.ContrastThreshold.Get(x, y);
        value -= threshold;
        value *= this.ContrastFactor.Get(x, y);
        value += threshold;
        return value;
    }

    @Override
    public double Get(final double x, final double y, final double z) {
        double value = this.Source.Get(x, y, z);
        // Apply brightness
        value += this.Brightness.Get(x, y, z);

        // Subtract contrastThreshold, scale by contrastFactor, add contrastThreshold
        final double threshold = this.ContrastThreshold.Get(x, y, z);
        value -= threshold;
        value *= this.ContrastFactor.Get(x, y, z);
        value += threshold;
        return value;
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        double value = this.Source.Get(x, y, z, w);
        // Apply brightness
        value += this.Brightness.Get(x, y, z, w);

        // Subtract contrastThreshold, scale by contrastFactor, add contrastThreshold
        final double threshold = this.ContrastThreshold.Get(x, y, z, w);
        value -= threshold;
        value *= this.ContrastFactor.Get(x, y, z, w);
        value += threshold;
        return value;
    }

    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        double value = this.Source.Get(x, y, z, w, u, v);
        // Apply brightness
        value += this.Brightness.Get(x, y, z, w, u, v);

        // Subtract contrastThreshold, scale by contrastFactor, add contrastThreshold
        final double threshold = this.ContrastThreshold.Get(x, y, z, w, u, v);
        value -= threshold;
        value *= this.ContrastFactor.Get(x, y, z, w, u, v);
        value += threshold;
        return value;
    }
}
