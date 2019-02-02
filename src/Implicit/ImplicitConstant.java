package Implicit;
public final class ImplicitConstant extends ImplicitModuleBase {
    private final double value;
    
    public ImplicitConstant() {
        this.value = 0.00;
    }
    
    public ImplicitConstant(double value) {
        this.value = value;
    }
    
    public double Get(double x, double y) {
        return this.value;
    }
    
    public double Get(double x, double y, double z) {
        return this.value;
    }
    
    public double Get(double x, double y, double z, double w) {
        return this.value;
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return this.value;
    }
}
