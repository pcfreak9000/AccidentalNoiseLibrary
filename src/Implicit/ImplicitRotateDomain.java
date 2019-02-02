package Implicit;

public final class ImplicitRotateDomain extends ImplicitModuleBase {
    private final double[][] rotationMatrix = new double[3][3];
    
    public ImplicitRotateDomain(ImplicitModuleBase source, double x, double y, double z, double angle) {
        this.Source = source;
        this.X = new ImplicitConstant(x);
        this.Y = new ImplicitConstant(y);
        this.Z = new ImplicitConstant(z);
        this.Angle = new ImplicitConstant(angle);
    }
    
    public ImplicitModuleBase Source;
    
    public ImplicitModuleBase X;
    
    public ImplicitModuleBase Y;
    public ImplicitModuleBase Z;
    
    public ImplicitModuleBase Angle;
    
    public ImplicitModuleBase getSource() {
        return Source;
    }
    
    public void setSource(ImplicitModuleBase source) {
        Source = source;
    }
    
    public ImplicitModuleBase getX() {
        return X;
    }
    
    public void setX(ImplicitModuleBase x) {
        X = x;
    }
    
    public ImplicitModuleBase getY() {
        return Y;
    }
    
    public void setY(ImplicitModuleBase y) {
        Y = y;
    }
    
    public ImplicitModuleBase getZ() {
        return Z;
    }
    
    public void setZ(ImplicitModuleBase z) {
        Z = z;
    }
    
    public ImplicitModuleBase getAngle() {
        return Angle;
    }
    
    public void setAngle(ImplicitModuleBase angle) {
        Angle = angle;
    }
    
    public void SetAxis(double x, double y, double z) {
        this.X = new ImplicitConstant(x);
        this.Y = new ImplicitConstant(y);
        this.Z = new ImplicitConstant(z);
    }
    
    public double Get(double x, double y) {
        double d = this.Angle.Get(x, y) * 360.0 * 3.14159265 / 180.0;
        double cos2D = Math.cos(d);
        double sin2D = Math.sin(d);
        double nx = x * cos2D - y * sin2D;
        double ny = y * cos2D + x * sin2D;
        return this.Source.Get(nx, ny);
    }
    
    public double Get(double x, double y, double z) {
        this.CalculateRotMatrix(x, y, z);
        double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y) + (this.rotationMatrix[2][0] * z);
        double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y) + (this.rotationMatrix[2][1] * z);
        double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y) + (this.rotationMatrix[2][2] * z);
        return this.Source.Get(nx, ny, nz);
    }
    
    public double Get(double x, double y, double z, double w) {
        this.CalculateRotMatrix(x, y, z, w);
        double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y) + (this.rotationMatrix[2][0] * z);
        double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y) + (this.rotationMatrix[2][1] * z);
        double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y) + (this.rotationMatrix[2][2] * z);
        return this.Source.Get(nx, ny, nz, w);
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        this.CalculateRotMatrix(x, y, z, w, u, v);
        double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y) + (this.rotationMatrix[2][0] * z);
        double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y) + (this.rotationMatrix[2][1] * z);
        double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y) + (this.rotationMatrix[2][2] * z);
        return this.Source.Get(nx, ny, nz, w, u, v);
    }
    
    @Deprecated
    private void CalculateRotMatrix(double x, double y) {
        double angle = this.Angle.Get(x, y) * 360.0 * Math.PI / 180.0;
        double ax = this.X.Get(x, y);
        double ay = this.Y.Get(x, y);
        double az = this.Z.Get(x, y);
        
        double cosangle = Math.cos(angle);
        double sinangle = Math.sin(angle);
        
        rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;
        
        rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;
        
        rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }
    
    private void CalculateRotMatrix(double x, double y, double z) {
        double angle = this.Angle.Get(x, y, z) * 360.0 * Math.PI / 180.0;
        double ax = this.X.Get(x, y, z);
        double ay = this.Y.Get(x, y, z);
        double az = this.Z.Get(x, y, z);
        
        double cosangle = Math.cos(angle);
        double sinangle = Math.sin(angle);
        
        rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;
        
        rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;
        
        rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }
    
    private void CalculateRotMatrix(double x, double y, double z, double w) {
        double angle = this.Angle.Get(x, y, z, w) * 360.0 * Math.PI / 180.0;
        double ax = this.X.Get(x, y, z, w);
        double ay = this.Y.Get(x, y, z, w);
        double az = this.Z.Get(x, y, z, w);
        
        double cosangle = Math.cos(angle);
        double sinangle = Math.sin(angle);
        
        rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;
        
        rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;
        
        rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }
    
    private void CalculateRotMatrix(double x, double y, double z, double w, double u, double v) {
        double angle = this.Angle.Get(x, y, z, w, u, v) * 360.0 * Math.PI / 180.0;
        double ax = this.X.Get(x, y, z, w, u, v);
        double ay = this.Y.Get(x, y, z, w, u, v);
        double az = this.Z.Get(x, y, z, w, u, v);
        
        double cosangle = Math.cos(angle);
        double sinangle = Math.sin(angle);
        
        rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;
        
        rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;
        
        rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }
}
