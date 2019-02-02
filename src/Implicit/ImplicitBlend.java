package Implicit;

import util.Maths;

public final class ImplicitBlend extends ImplicitModuleBase {
    public ImplicitBlend(final ImplicitModuleBase source, final double low, final double high) {
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
    public double Get(final double x, final double y) {
        final double v1 = this.Low.Get(x, y);
        final double v2 = this.High.Get(x, y);
        final double blend = (this.Source.Get(x, y) + 1.0) * 0.5;
        return Maths.Lerp(blend, v1, v2);
    }
    
    @Override
    public double Get(final double x, final double y, final double z) {
        final double v1 = this.Low.Get(x, y, z);
        final double v2 = this.High.Get(x, y, z);
        final double blend = this.Source.Get(x, y, z);
        return Maths.Lerp(blend, v1, v2);
    }
    
    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        final double v1 = this.Low.Get(x, y, z, w);
        final double v2 = this.High.Get(x, y, z, w);
        final double blend = this.Source.Get(x, y, z, w);
        return Maths.Lerp(blend, v1, v2);
    }
    
    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        final double v1 = this.Low.Get(x, y, z, w, u, v);
        final double v2 = this.High.Get(x, y, z, w, u, v);
        final double blend = this.Source.Get(x, y, z, w, u, v);
        return Maths.Lerp(blend, v1, v2);
    }
}
