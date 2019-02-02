package Implicit;

import java.util.Random;

public final class ImplicitAutoCorrect extends ImplicitModuleBase {
    private ImplicitModuleBase source;
    
    private double low;
    
    private double high;
    
    private double scale2D;
    
    private double offset2D;
    
    private double scale3D;
    
    private double offset3D;
    
    private double scale4D;
    
    private double offset4D;
    
    private double scale6D;
    
    private double offset6D;
    
    public ImplicitAutoCorrect(final ImplicitModuleBase source, final double low, final double high) {
        this.source = source;
        this.low = low;
        this.high = high;
        this.Calculate();
    }
    
    private ImplicitModuleBase Source;
    
    public ImplicitModuleBase getSource() {
        return this.source;
    }
    
    public void setSource(final ImplicitModuleBase value) {
        this.source = value;
        this.Calculate();
    }
    
    public double getLow() {
        return this.low;
    }
    
    public void setLow(final double value) {
        this.low = value;
        this.Calculate();
    }
    
    public double getHigh() {
        return this.high;
    }
    
    public void setHigh(final double value) {
        this.high = value;
        this.Calculate();
    }
    
    private void Calculate() {
        final Random random = new Random();
        
        // Calculate 2D
        double mn = 10000.0;
        double mx = -10000.0;
        for (int c = 0; c < 10000; ++c) {
            final double nx = random.nextDouble() * 4.0 - 2.0;
            final double ny = random.nextDouble() * 4.0 - 2.0;
            
            final double value = this.Source.Get(nx, ny);
            if (value < mn) {
                mn = value;
            }
            if (value > mx) {
                mx = value;
            }
        }
        this.scale2D = (this.high - this.low) / (mx - mn);
        this.offset2D = this.low - mn * this.scale2D;
        
        // Calculate 3D
        mn = 10000.0;
        mx = -10000.0;
        for (int c = 0; c < 10000; ++c) {
            final double nx = random.nextDouble() * 4.0 - 2.0;
            final double ny = random.nextDouble() * 4.0 - 2.0;
            final double nz = random.nextDouble() * 4.0 - 2.0;
            
            final double value = this.Source.Get(nx, ny, nz);
            if (value < mn) {
                mn = value;
            }
            if (value > mx) {
                mx = value;
            }
        }
        this.scale3D = (this.high - this.low) / (mx - mn);
        this.offset3D = this.low - mn * this.scale3D;
        
        // Calculate 4D
        mn = 10000.0;
        mx = -10000.0;
        for (int c = 0; c < 10000; ++c) {
            final double nx = random.nextDouble() * 4.0 - 2.0;
            final double ny = random.nextDouble() * 4.0 - 2.0;
            final double nz = random.nextDouble() * 4.0 - 2.0;
            final double nw = random.nextDouble() * 4.0 - 2.0;
            
            final double value = this.Source.Get(nx, ny, nz, nw);
            if (value < mn) {
                mn = value;
            }
            if (value > mx) {
                mx = value;
            }
        }
        this.scale4D = (this.high - this.low) / (mx - mn);
        this.offset4D = this.low - mn * this.scale4D;
        
        // Calculate 6D
        mn = 10000.0;
        mx = -10000.0;
        for (int c = 0; c < 10000; ++c) {
            final double nx = random.nextDouble() * 4.0 - 2.0;
            final double ny = random.nextDouble() * 4.0 - 2.0;
            final double nz = random.nextDouble() * 4.0 - 2.0;
            final double nw = random.nextDouble() * 4.0 - 2.0;
            final double nu = random.nextDouble() * 4.0 - 2.0;
            final double nv = random.nextDouble() * 4.0 - 2.0;
            
            final double value = this.Source.Get(nx, ny, nz, nw, nu, nv);
            if (value < mn) {
                mn = value;
            }
            if (value > mx) {
                mx = value;
            }
        }
        this.scale6D = (this.high - this.low) / (mx - mn);
        this.offset6D = this.low - mn * this.scale6D;
    }
    
    public void SetRange(final double low, final double high) {
        this.low = low;
        this.high = high;
        this.Calculate();
    }
    
    @Override
    public double Get(final double x, final double y) {
        return Math.max(this.low, Math.min(this.high, this.Source.Get(x, y) * this.scale2D + this.offset2D));
    }
    
    @Override
    public double Get(final double x, final double y, final double z) {
        return Math.max(this.low, Math.min(this.high, this.Source.Get(x, y, z) * this.scale3D + this.offset3D));
    }
    
    @Override
    public double Get(final double x, final double y, final double z, final double w) {
        return Math.max(this.low, Math.min(this.high, this.Source.Get(x, y, z, w) * this.scale4D + this.offset4D));
    }
    
    @Override
    public double Get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Math.max(this.low,
                Math.min(this.high, this.Source.Get(x, y, z, w, u, v) * this.scale6D + this.offset6D));
    }
}
