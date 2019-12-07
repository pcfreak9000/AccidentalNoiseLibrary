package implicit;

import java.util.Objects;

import noise.CellularCache;
import noise.CellularGenerator;

public class ImplicitCellular extends ImplicitModuleBase {
    private CellularGenerator generator;
    private final double[] coefficients = new double[4];
    
    public ImplicitCellular(final CellularGenerator generator) {
        Objects.requireNonNull(generator);
        
        this.generator = generator;
    }
    
    public CellularGenerator getGenerator() {
        return this.generator;
    }
    
    public void setGenerator(final CellularGenerator generator) {
        Objects.requireNonNull(generator);
        this.generator = generator;
    }
    
    public void setCoefficients(double a, double b, double c, double d) {
        this.coefficients[0] = a;
        this.coefficients[1] = b;
        this.coefficients[2] = c;
        this.coefficients[3] = d;
    }
    
    public double getCoefficient(int index) {
        return coefficients[index];
    }
    
    @Override
    public double get(final double x, final double y) {
        final CellularCache c = this.generator.Get(x, y);
        
        return c.F[0] * this.coefficients[0] + c.F[1] * this.coefficients[1] + c.F[2] * this.coefficients[2]
                + c.F[3] * this.coefficients[3];
    }
    
    @Override
    public double get(final double x, final double y, final double z) {
        final CellularCache c = this.generator.Get(x, y, z);
        
        return c.F[0] * this.coefficients[0] + c.F[1] * this.coefficients[1] + c.F[2] * this.coefficients[2]
                + c.F[3] * this.coefficients[3];
    }
    
    @Override
    public double get(final double x, final double y, final double z, final double w) {
        final CellularCache c = this.generator.Get(x, y, z, w);
        
        return c.F[0] * this.coefficients[0] + c.F[1] * this.coefficients[1] + c.F[2] * this.coefficients[2]
                + c.F[3] * this.coefficients[3];
    }
    
    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        final CellularCache c = this.generator.Get(x, y, z, w, u, v);
        
        return c.F[0] * this.coefficients[0] + c.F[1] * this.coefficients[1] + c.F[2] * this.coefficients[2]
                + c.F[3] * this.coefficients[3];
    }
}
