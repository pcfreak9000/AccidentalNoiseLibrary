package implicit;

public final class ImplicitRotateDomain extends ImplicitModuleBase {
    private final double[][] rotationMatrix = new double[3][3];

    public ImplicitRotateDomain(final ImplicitModuleBase source, final double x, final double y, final double z,
            final double angle) {
        this.Source = source;
        this.X = new ImplicitConstant(x);
        this.Y = new ImplicitConstant(y);
        this.Z = new ImplicitConstant(z);
        this.Angle = new ImplicitConstant(angle);
    }

    private ImplicitModuleBase Source;

    private ImplicitModuleBase X;

    private ImplicitModuleBase Y;
    private ImplicitModuleBase Z;

    private ImplicitModuleBase Angle;

    public ImplicitModuleBase getSource() {
        return this.Source;
    }

    public void setSource(final ImplicitModuleBase source) {
        this.Source = source;
    }

    public ImplicitModuleBase getX() {
        return this.X;
    }

    public void setX(final ImplicitModuleBase x) {
        this.X = x;
    }

    public ImplicitModuleBase getY() {
        return this.Y;
    }

    public void setY(final ImplicitModuleBase y) {
        this.Y = y;
    }

    public ImplicitModuleBase getZ() {
        return this.Z;
    }

    public void setZ(final ImplicitModuleBase z) {
        this.Z = z;
    }

    public ImplicitModuleBase getAngle() {
        return this.Angle;
    }

    public void setAngle(final ImplicitModuleBase angle) {
        this.Angle = angle;
    }

    public void SetAxis(final double x, final double y, final double z) {
        this.X = new ImplicitConstant(x);
        this.Y = new ImplicitConstant(y);
        this.Z = new ImplicitConstant(z);
    }

    @Override
    public double get(final double x, final double y) {
        final double d = this.Angle.get(x, y) * 360.0 * 3.14159265 / 180.0;
        final double cos2D = Math.cos(d);
        final double sin2D = Math.sin(d);
        final double nx = x * cos2D - y * sin2D;
        final double ny = y * cos2D + x * sin2D;
        return this.Source.get(nx, ny);
    }

    @Override
    public double get(final double x, final double y, final double z) {
        this.CalculateRotMatrix(x, y, z);
        final double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y)
                + (this.rotationMatrix[2][0] * z);
        final double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y)
                + (this.rotationMatrix[2][1] * z);
        final double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y)
                + (this.rotationMatrix[2][2] * z);
        return this.Source.get(nx, ny, nz);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w) {
        this.CalculateRotMatrix(x, y, z, w);
        final double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y)
                + (this.rotationMatrix[2][0] * z);
        final double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y)
                + (this.rotationMatrix[2][1] * z);
        final double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y)
                + (this.rotationMatrix[2][2] * z);
        return this.Source.get(nx, ny, nz, w);
    }

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        this.CalculateRotMatrix(x, y, z, w, u, v);
        final double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y)
                + (this.rotationMatrix[2][0] * z);
        final double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y)
                + (this.rotationMatrix[2][1] * z);
        final double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y)
                + (this.rotationMatrix[2][2] * z);
        return this.Source.get(nx, ny, nz, w, u, v);
    }

    @Deprecated
    private void CalculateRotMatrix(final double x, final double y) {
        final double angle = this.Angle.get(x, y) * 360.0 * Math.PI / 180.0;
        final double ax = this.X.get(x, y);
        final double ay = this.Y.get(x, y);
        final double az = this.Z.get(x, y);

        final double cosangle = Math.cos(angle);
        final double sinangle = Math.sin(angle);

        this.rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        this.rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;

        this.rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        this.rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;

        this.rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        this.rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        this.rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }

    private void CalculateRotMatrix(final double x, final double y, final double z) {
        final double angle = this.Angle.get(x, y, z) * 360.0 * Math.PI / 180.0;
        final double ax = this.X.get(x, y, z);
        final double ay = this.Y.get(x, y, z);
        final double az = this.Z.get(x, y, z);

        final double cosangle = Math.cos(angle);
        final double sinangle = Math.sin(angle);

        this.rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        this.rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;

        this.rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        this.rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;

        this.rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        this.rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        this.rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }

    private void CalculateRotMatrix(final double x, final double y, final double z, final double w) {
        final double angle = this.Angle.get(x, y, z, w) * 360.0 * Math.PI / 180.0;
        final double ax = this.X.get(x, y, z, w);
        final double ay = this.Y.get(x, y, z, w);
        final double az = this.Z.get(x, y, z, w);

        final double cosangle = Math.cos(angle);
        final double sinangle = Math.sin(angle);

        this.rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        this.rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;

        this.rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        this.rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;

        this.rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        this.rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        this.rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }

    private void CalculateRotMatrix(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        final double angle = this.Angle.get(x, y, z, w, u, v) * 360.0 * Math.PI / 180.0;
        final double ax = this.X.get(x, y, z, w, u, v);
        final double ay = this.Y.get(x, y, z, w, u, v);
        final double az = this.Z.get(x, y, z, w, u, v);

        final double cosangle = Math.cos(angle);
        final double sinangle = Math.sin(angle);

        this.rotationMatrix[0][0] = 1.0 + (1.0 - cosangle) * (ax * ax - 1.0);
        this.rotationMatrix[1][0] = -az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[2][0] = ay * sinangle + (1.0 - cosangle) * ax * az;

        this.rotationMatrix[0][1] = az * sinangle + (1.0 - cosangle) * ax * ay;
        this.rotationMatrix[1][1] = 1.0 + (1.0 - cosangle) * (ay * ay - 1.0);
        this.rotationMatrix[2][1] = -ax * sinangle + (1.0 - cosangle) * ay * az;

        this.rotationMatrix[0][2] = -ay * sinangle + (1.0 - cosangle) * ax * az;
        this.rotationMatrix[1][2] = ax * sinangle + (1.0 - cosangle) * ay * az;
        this.rotationMatrix[2][2] = 1.0 + (1.0 - cosangle) * (az * az - 1.0);
    }
}
