package Implicit;

import top.Maths;

public final class ImplicitFloor extends ImplicitModuleBase {
    
    private ImplicitModuleBase Source;
    
    public ImplicitFloor() {
        this.Source = new ImplicitConstant(0.00);
    }
    
    public ImplicitFloor(ImplicitModuleBase source) {
        this.Source = source;
    }
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public double Get(double x, double y) {
        return Maths.floor(this.Source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Maths.floor(this.Source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Maths.floor(this.Source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Maths.floor(this.Source.Get(x, y, z, w, u, v));
    }
}
