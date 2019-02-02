package Implicit;

import top.Maths;

public final class ImplicitCeiling extends ImplicitModuleBase {
    public ImplicitCeiling(ImplicitModuleBase source) {
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
        return Maths.ceil(this.Source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Maths.ceil(this.Source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Maths.ceil(this.Source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Maths.ceil(this.Source.Get(x, y, z, w, u, v));
    }
}
