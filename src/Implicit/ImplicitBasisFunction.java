package Implicit;

import java.util.Random;

import Enums.BasisType;
import Enums.InterpolationType;
import noise.Noise;

public final class ImplicitBasisFunction extends ImplicitModuleBase {
    private final double[] scale = new double[4];
    
    private final double[] offset = new double[4];
    
    private Noise noiseGen;
    
    private int seed;
    
    private BasisType basisType;
    
    private InterpolationType interpolationType;
    
    private final double[][] rotationMatrix = new double[3][3];
    
    private double cos2D;
    
    private double sin2D;
    
    public ImplicitBasisFunction(BasisType basisType /* = BasisType.Gradient */,
            InterpolationType interpolationType /* = InterpolationType.Quintic */) {
        this.setBasisType(basisType);
        this.setInterpolationType(interpolationType);
        //TODO seed make long?
        this.setSeed((int) System.currentTimeMillis());
    }
    
    public void setSeed(int value) {
        this.seed = value;
        Random random = new Random(value);
        
        double ax = random.nextDouble();
        double ay = random.nextDouble();
        double az = random.nextDouble();
        double len = Math.sqrt(ax * ax + ay * ay + az * az);
        ax /= len;
        ay /= len;
        az /= len;
        SetRotationAngle(ax, ay, az, random.nextDouble() * Math.PI * 2.0);
        double angle = random.nextDouble() * Math.PI * 2.0;
        cos2D = Math.cos(angle);
        sin2D = Math.sin(angle);
    }
    
    public int getSeed() {
        return seed;
    }
    
    public BasisType getBasisType() {
        return basisType;
    }
    
    public void setBasisType(BasisType value) {
        this.basisType = value;
        this.noiseGen = value.noiseGen;
        
        SetMagicNumbers(this.basisType);
    }
    
    public InterpolationType getInterpolationType() {
        return interpolationType;
    }
    
    public void setInterpolationType(InterpolationType value) {
        this.interpolationType = value;
    }
    
    public double Get(double x, double y) {
        double nx = x * cos2D - y * sin2D;
        double ny = y * cos2D + x * sin2D;
        
        return this.noiseGen.noise(nx, ny, this.seed, this.interpolationType);
    }
    
    public double Get(double x, double y, double z) {
        double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y) + (this.rotationMatrix[2][0] * z);
        double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y) + (this.rotationMatrix[2][1] * z);
        double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y) + (this.rotationMatrix[2][2] * z);
        
        return this.noiseGen.noise(nx, ny, nz, this.seed, this.interpolationType);
    }
    
    public double Get(double x, double y, double z, double w) {
        double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y) + (this.rotationMatrix[2][0] * z);
        double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y) + (this.rotationMatrix[2][1] * z);
        double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y) + (this.rotationMatrix[2][2] * z);
        
        return this.noiseGen.noise(nx, ny, nz, w, this.seed, this.interpolationType);
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        double nx = (this.rotationMatrix[0][0] * x) + (this.rotationMatrix[1][0] * y) + (this.rotationMatrix[2][0] * z);
        double ny = (this.rotationMatrix[0][1] * x) + (this.rotationMatrix[1][1] * y) + (this.rotationMatrix[2][1] * z);
        double nz = (this.rotationMatrix[0][2] * x) + (this.rotationMatrix[1][2] * y) + (this.rotationMatrix[2][2] * z);
        
        return this.noiseGen.noise(nx, ny, nz, w, u, v, this.seed, this.interpolationType);
    }
    
    private void SetRotationAngle(double x, double y, double z, double angle) {
        this.rotationMatrix[0][0] = 1 + (1 - Math.cos(angle)) * (x * x - 1);
        this.rotationMatrix[1][0] = -z * Math.sin(angle) + (1 - Math.cos(angle)) * x * y;
        this.rotationMatrix[2][0] = y * Math.sin(angle) + (1 - Math.cos(angle)) * x * z;
        
        this.rotationMatrix[0][1] = z * Math.sin(angle) + (1 - Math.cos(angle)) * x * y;
        this.rotationMatrix[1][1] = 1 + (1 - Math.cos(angle)) * (y * y - 1);
        this.rotationMatrix[2][1] = -x * Math.sin(angle) + (1 - Math.cos(angle)) * y * z;
        
        this.rotationMatrix[0][2] = -y * Math.sin(angle) + (1 - Math.cos(angle)) * x * z;
        this.rotationMatrix[1][2] = x * Math.sin(angle) + (1 - Math.cos(angle)) * y * z;
        this.rotationMatrix[2][2] = 1 + (1 - Math.cos(angle)) * (z * z - 1);
    }
    
    private void SetMagicNumbers(BasisType type) {
        // This function is a damned hack.
        // The underlying noise functions don't return values in the range [-1,1] cleanly, and the ranges vary depending
        // on basis type and dimensionality. There's probably a better way to correct the ranges, but for now I'm just
        // setting he magic numbers scale and offset manually to empirically determined magic numbers.
        switch (type) {
        case Value:
            this.scale[0] = 1.0;
            this.offset[0] = 0.0;
            this.scale[1] = 1.0;
            this.offset[1] = 0.0;
            this.scale[2] = 1.0;
            this.offset[2] = 0.0;
            this.scale[3] = 1.0;
            this.offset[3] = 0.0;
            break;
        
        case Gradient:
            this.scale[0] = 1.86848;
            this.offset[0] = -0.000118;
            this.scale[1] = 1.85148;
            this.offset[1] = -0.008272;
            this.scale[2] = 1.64127;
            this.offset[2] = -0.01527;
            this.scale[3] = 1.92517;
            this.offset[3] = 0.03393;
            break;
        
        case GradientValue:
            this.scale[0] = 0.6769;
            this.offset[0] = -0.00151;
            this.scale[1] = 0.6957;
            this.offset[1] = -0.133;
            this.scale[2] = 0.74622;
            this.offset[2] = 0.01916;
            this.scale[3] = 0.7961;
            this.offset[3] = -0.0352;
            break;
        
        case White:
            this.scale[0] = 1.0;
            this.offset[0] = 0.0;
            this.scale[1] = 1.0;
            this.offset[1] = 0.0;
            this.scale[2] = 1.0;
            this.offset[2] = 0.0;
            this.scale[3] = 1.0;
            this.offset[3] = 0.0;
            break;
        
        default:
            this.scale[0] = 1.0;
            this.offset[0] = 0.0;
            this.scale[1] = 1.0;
            this.offset[1] = 0.0;
            this.scale[2] = 1.0;
            this.offset[2] = 0.0;
            this.scale[3] = 1.0;
            this.offset[3] = 0.0;
            break;
        }
    }
}
