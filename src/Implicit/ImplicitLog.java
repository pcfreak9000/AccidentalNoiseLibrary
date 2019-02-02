package Implicit;

public final class ImplicitLog extends ImplicitModuleBase {
    public ImplicitLog(ImplicitModuleBase source) {
        this.Source = source;
    }
    
    public ImplicitModuleBase Source;
    
    public double Get(double x, double y) {
        return Math.log(this.Source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Math.log(this.Source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Math.log(this.Source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Math.log(this.Source.Get(x, y, z, w, u, v));
    }
}
