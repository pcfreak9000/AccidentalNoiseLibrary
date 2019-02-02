package Implicit;

public final class ImplicitTan extends ImplicitModuleBase {
    public ImplicitTan(ImplicitModuleBase source) {
        this.Source = source;
    }
    
    public ImplicitModuleBase Source;
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public double Get(double x, double y) {
        return Math.tan(this.Source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Math.tan(this.Source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Math.tan(this.Source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Math.tan(this.Source.Get(x, y, z, w, u, v));
    }
}
