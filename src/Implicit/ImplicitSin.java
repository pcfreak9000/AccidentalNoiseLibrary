package Implicit;

public final class ImplicitSin extends ImplicitModuleBase {
    private ImplicitModuleBase source;
    
    public ImplicitSin(ImplicitModuleBase source) {
        this.source = source;
    }
    
    public ImplicitModuleBase getSource() {
        return source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        this.source = source;
    }
    
    public double Get(double x, double y) {
        return Math.sin(this.source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Math.sin(this.source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Math.sin(this.source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Math.sin(this.source.Get(x, y, z, w, u, v));
    }
}
