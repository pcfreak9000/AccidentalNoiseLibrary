package Implicit;

public final class ImplicitCos extends ImplicitModuleBase {
    private ImplicitModuleBase Source;
    
    public ImplicitCos(ImplicitModuleBase source) {
        this.Source = source;
    }
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public double Get(double x, double y) {
        return Math.cos(this.Source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Math.cos(this.Source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Math.cos(this.Source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Math.cos(this.Source.Get(x, y, z, w, u, v));
    }
}
