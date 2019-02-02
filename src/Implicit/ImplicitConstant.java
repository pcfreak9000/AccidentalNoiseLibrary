package implicit;

public final class ImplicitConstant extends ImplicitModuleBase {
    private final double value;
    
    public ImplicitConstant() {
        this.value = 0.00;
    }
    
    public ImplicitConstant(final double value) {
        this.value = value;
    }
    
    @Override
    public double get(final double x, final double y) {
        return this.value;
    }
    
    @Override
    public double get(final double x, final double y, final double z) {
        return this.value;
    }
    
    @Override
    public double get(final double x, final double y, final double z, final double w) {
        return this.value;
    }
    
    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        return this.value;
    }
}
