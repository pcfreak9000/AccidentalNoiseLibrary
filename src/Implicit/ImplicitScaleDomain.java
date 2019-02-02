package Implicit;

public final class ImplicitScaleDomain extends ImplicitModuleBase {
    public ImplicitScaleDomain(ImplicitModuleBase source, double xScale, double yScale, double zScale, double wScale,
            double uScale, double vScale) {
        this.Source = source;
        this.XScale = new ImplicitConstant(xScale);
        this.YScale = new ImplicitConstant(yScale);
        this.ZScale = new ImplicitConstant(zScale);
        this.WScale = new ImplicitConstant(wScale);
        this.UScale = new ImplicitConstant(uScale);
        this.VScale = new ImplicitConstant(vScale);
    }
    
    public ImplicitModuleBase Source;
    
    public ImplicitModuleBase XScale;
    
    public ImplicitModuleBase YScale;
    
    public ImplicitModuleBase ZScale;
    
    public ImplicitModuleBase WScale;
    
    public ImplicitModuleBase UScale;
    
    public ImplicitModuleBase VScale;
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public ImplicitModuleBase getXScale() {
        return XScale;
    }
    
    public void setXScale(ImplicitModuleBase xScale) {
        XScale = xScale;
    }
    
    public ImplicitModuleBase getYScale() {
        return YScale;
    }
    
    public void setYScale(ImplicitModuleBase yScale) {
        YScale = yScale;
    }
    
    public ImplicitModuleBase getZScale() {
        return ZScale;
    }
    
    public void setZScale(ImplicitModuleBase zScale) {
        ZScale = zScale;
    }
    
    public ImplicitModuleBase getWScale() {
        return WScale;
    }
    
    public void setWScale(ImplicitModuleBase wScale) {
        WScale = wScale;
    }
    
    public ImplicitModuleBase getUScale() {
        return UScale;
    }
    
    public void setUScale(ImplicitModuleBase uScale) {
        UScale = uScale;
    }
    
    public ImplicitModuleBase getVScale() {
        return VScale;
    }
    
    public void setVScale(ImplicitModuleBase vScale) {
        VScale = vScale;
    }
    
    public void SetScales(double xScale, double yScale, double zScale, double wScale, double uScale, double vScale) {
        this.XScale = new ImplicitConstant(xScale);
        this.YScale = new ImplicitConstant(yScale);
        this.ZScale = new ImplicitConstant(zScale);
        this.WScale = new ImplicitConstant(wScale);
        this.UScale = new ImplicitConstant(uScale);
        this.VScale = new ImplicitConstant(vScale);
    }
    
    public double Get(double x, double y) {
        return this.Source.Get(x * this.XScale.Get(x, y), y * this.YScale.Get(x, y));
    }
    
    public double Get(double x, double y, double z) {
        return this.Source.Get(x * this.XScale.Get(x, y, z), y * this.YScale.Get(x, y, z),
                z * this.ZScale.Get(x, y, z));
    }
    
    public double Get(double x, double y, double z, double w) {
        return this.Source.Get(x * this.XScale.Get(x, y, z, w), y * this.YScale.Get(x, y, z, w),
                z * this.ZScale.Get(x, y, z, w), w * this.WScale.Get(x, y, z, w));
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        return this.Source.Get(x * this.XScale.Get(x, y, z, w, u, v), y * this.YScale.Get(x, y, z, w, u, v),
                z * this.ZScale.Get(x, y, z, w, u, v), w * this.WScale.Get(x, y, z, w, u, v),
                u * this.UScale.Get(x, y, z, w, u, v), v * this.VScale.Get(x, y, z, w, u, v));
    }
}
