package Implicit;

public final class ImplicitInvert extends ImplicitModuleBase {
    public ImplicitInvert(ImplicitModuleBase source) {
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
        return -this.Source.Get(x, y);
    }
    
    public double Get(double x, double y, double z) {
        return -this.Source.Get(x, y, z);
    }
    
    public double Get(double x, double y, double z, double w) {
        return -this.Source.Get(x, y, z, w);
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return -this.Source.Get(x, y, z, w, u, v);
    }
}
