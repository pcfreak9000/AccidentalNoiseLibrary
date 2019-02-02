package Implicit;

import top.Maths;

public final class ImplicitGain extends ImplicitModuleBase {
    public ImplicitGain(ImplicitModuleBase source, double gain) {
        this.Source = source;
        this.Gain = new ImplicitConstant(gain);
    }
    
    public ImplicitGain(ImplicitModuleBase source, ImplicitModuleBase gain) {
        this.Source = source;
        this.Gain = gain;
    }
    
    public ImplicitModuleBase Source;
    
    public ImplicitModuleBase Gain;
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public ImplicitModuleBase getGain() {
        return Gain;
    }
    
    public void setGain(ImplicitModuleBase gain) {
        Gain = gain;
    }
    
    public double Get(double x, double y) {
        return Maths.Gain(this.Gain.Get(x, y), this.Source.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Maths.Gain(this.Gain.Get(x, y, z), this.Source.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Maths.Gain(this.Gain.Get(x, y, z, w), this.Source.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Maths.Gain(this.Gain.Get(x, y, z, w, u, v), this.Source.Get(x, y, z, w, u, v));
    }
}
