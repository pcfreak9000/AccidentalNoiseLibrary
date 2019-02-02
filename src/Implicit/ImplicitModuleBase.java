package Implicit;

public abstract class ImplicitModuleBase {
    
    public static final int MAX_SOURCES = 20;
    
    public int getSeed() {
        return 0;
    }
    
    public void setSeed(int seed) {
        
    }
    
    public double Get(double x, double y) {
        return 0.00;
    }
    
    public double Get(double x, double y, double z) {
        return 0.00;
    }
    
    public double Get(double x, double y, double z, double w) {
        return 0.00;
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return 0.00;
    }
    
    /*
     * TODO public static implicit operator ImplicitModuleBase(double value) {
     * return new ImplicitConstant(value); }
     */
}
