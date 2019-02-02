package Implicit;

import util.Maths;

public final class ImplicitClamp extends ImplicitModuleBase {
    public ImplicitClamp(ImplicitModuleBase source, double low, double high) {
        this.Source = source;
        this.Low = new ImplicitConstant(low);
        this.High = new ImplicitConstant(high);
    }
    
    public ImplicitModuleBase Source;
    
    public ImplicitModuleBase Low;
    
    public ImplicitModuleBase High;
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public ImplicitModuleBase getLow() {
        return Low;
    }
    
    public void setLow(ImplicitModuleBase low) {
        Low = low;
    }
    
    public ImplicitModuleBase getHigh() {
        return High;
    }
    
    public void setHigh(ImplicitModuleBase high) {
        High = high;
    }
    
    public double Get(double x, double y) {
        return Maths.Clamp(Source.Get(x, y), Low.Get(x, y), High.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Maths.Clamp(Source.Get(x, y, z), Low.Get(x, y, z), High.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Maths.Clamp(Source.Get(x, y, z, w), Low.Get(x, y, z, w), High.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Maths.Clamp(Source.Get(x, y, z, w, u, v), Low.Get(x, y, z, w, u, v), High.Get(x, y, z, w, u, v));
    }
}
