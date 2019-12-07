package Implicit;

import util.Maths;

public final class ImplicitFloor extends ImplicitModuleBase {
    
    private ImplicitModuleBase Source;
    
    public ImplicitFloor() {
        this.Source = new ImplicitConstant(0.00);
    }
    
    public ImplicitFloor(final ImplicitModuleBase source) {
        this.Source = source;
    }
    
    public ImplicitModuleBase getSource() {
        return this.Source;
    }
    
    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }
    
    @Override
    public double get(final double x, final double y) {
        return Maths.floor(this.Source.get(x, y));
    }
    
    @Override
    public double get(final double x, final double y, final double z) {
        return Maths.floor(this.Source.get(x, y, z));
    }
    
    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return Maths.floor(this.Source.get(x, y, z, w));
    }
    
    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return Maths.floor(this.Source.get(x, y, z, w, u, v));
    }
}
