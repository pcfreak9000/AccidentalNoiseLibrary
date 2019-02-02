package Implicit;

import java.util.Objects;

import noise.CellularCache;
import noise.CellularGenerator;

public class ImplicitCellular extends ImplicitModuleBase {
    private CellularGenerator generator;
    //FIXME what does this do?
    public final double[] Coefficients = new double[4];
    
    public ImplicitCellular(CellularGenerator generator) {
        Objects.requireNonNull(generator);
        
        this.generator = generator;
    }
    
    public CellularGenerator getGenerator() {
        return generator;
    }
    
    public void setGenerator(CellularGenerator generator) {
        Objects.requireNonNull(generator);
        this.generator = generator;
    }
    
    public double Get(double x, double y) {
        CellularCache c = this.generator.Get(x, y);
        
        return c.F[0] * Coefficients[0] + c.F[1] * Coefficients[1] + c.F[2] * Coefficients[2]
                + c.F[3] * Coefficients[3];
    }
    
    public double Get(double x, double y, double z) {
        CellularCache c = this.generator.Get(x, y, z);
        
        return c.F[0] * Coefficients[0] + c.F[1] * Coefficients[1] + c.F[2] * Coefficients[2]
                + c.F[3] * Coefficients[3];
    }
    
    public double Get(double x, double y, double z, double w) {
        CellularCache c = this.generator.Get(x, y, z, w);
        
        return c.F[0] * Coefficients[0] + c.F[1] * Coefficients[1] + c.F[2] * Coefficients[2]
                + c.F[3] * Coefficients[3];
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        CellularCache c = this.generator.Get(x, y, z, w, u, v);
        
        return c.F[0] * Coefficients[0] + c.F[1] * Coefficients[1] + c.F[2] * Coefficients[2]
                + c.F[3] * Coefficients[3];
    }
}
