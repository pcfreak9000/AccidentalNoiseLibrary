package Implicit;

public final class ImplicitSin extends ImplicitModuleBase {
    private ImplicitModuleBase source;
    
    public ImplicitSin(final ImplicitModuleBase source) {
        this.source = source;
    }
    
    public ImplicitModuleBase getSource() {
        return this.source;
    }
    
    public void setSource(final ImplicitModuleBase source) {
        this.source = source;
    }
    
    @Override
    public double Get(final double x, final double y) {
        return Math.sin(this.source.Get(x, y));
    }
    
    @Override
    public double Get(final double x, final double y, final double z) {
        return Math.sin(this.source.Get(x, y, z));
    }
    
    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return Math.sin(this.source.Get(x, y, z, w));
    }
    
    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.sin(this.source.Get(x, y, z, w, u, v));
    }
}
