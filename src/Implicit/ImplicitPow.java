package Implicit;

public final class ImplicitPow extends ImplicitModuleBase {
    public ImplicitPow(ImplicitModuleBase source, double power) {
        this.Source = source;
        this.Power = new ImplicitConstant(power);
    }
    
    public ImplicitPow(ImplicitModuleBase source, ImplicitModuleBase power) {
        this.Source = source;
        this.Power = power;
    }
    
    public ImplicitModuleBase Source;
    
    public ImplicitModuleBase Power;
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public ImplicitModuleBase getPower() {
        return Power;
    }
    
    public void setPower(ImplicitModuleBase power) {
        Power = power;
    }
    
    public double Get(double x, double y) {
        return Math.pow(this.Source.Get(x, y), this.Power.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return Math.pow(this.Source.Get(x, y, z), this.Power.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return Math.pow(this.Source.Get(x, y, z, w), this.Power.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return Math.pow(this.Source.Get(x, y, z, w, u, v), this.Power.Get(x, y, z, w, u, v));
    }
}
