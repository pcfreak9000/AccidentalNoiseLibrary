package Implicit;

import Enums.BasisType;
import Enums.FractalType;
import Enums.InterpolationType;
import top.Maths;

public final class ImplicitFractal extends ImplicitModuleBase {
    private final ImplicitBasisFunction[] basisFunctions = new ImplicitBasisFunction[ImplicitModuleBase.MAX_SOURCES];
    
    private final ImplicitModuleBase[] sources = new ImplicitModuleBase[ImplicitModuleBase.MAX_SOURCES];
    
    private final double[] expArray = new double[ImplicitModuleBase.MAX_SOURCES];
    
    private final double[][] correct = new double[ImplicitModuleBase.MAX_SOURCES][2];
    
    private int seed;
    
    private FractalType type;
    
    private int octaves;
    
    private double frequency;
    
    private double lacunarity;
    
    private double gain;
    
    private double offset;
    
    private double h;
    
    public ImplicitFractal(FractalType fractalType, BasisType basisType, InterpolationType interpolationType) {
        this.setOctaves(8);
        this.setFrequency(1.00);
        this.setLacunarity(2.00);
        this.setFractalType(fractalType);
        this.SetAllSourceTypes(basisType, interpolationType);
        this.ResetAllSources();
    }
    
    public int getSeed() {
        return this.seed;
    }
    
    public void setSeed(int value) {
        this.seed = value;
        for (int source = 0; source < ImplicitModuleBase.MAX_SOURCES; source += 1)
            this.sources[source].setSeed(((this.seed + source * 300)));
    }
    
    public FractalType getType() {
        return this.type;
    }
    
    public void setFractalType(FractalType value) {
        this.type = value;
        this.setH(value.h);
        this.setGain(value.gain);
        this.setOffset(value.offset);
        switch (this.type) {
        case FractionalBrownianMotion:
            
            this.FractionalBrownianMotion_CalculateWeights();
            break;
        case RidgedMulti:
            
            this.RidgedMulti_CalculateWeights();
            break;
        case Billow:
            
            this.Billow_CalculateWeights();
            break;
        case Multi:
            
            this.Multi_CalculateWeights();
            break;
        case HybridMulti:
            
            this.HybridMulti_CalculateWeights();
            break;
        default:
            
            this.FractionalBrownianMotion_CalculateWeights();
            break;
        }
    }
    
    public int getOctaves() {
        return this.octaves;
    }
    
    public void setOctaves(int value) {
        if (value >= ImplicitModuleBase.MAX_SOURCES)
            value = ImplicitModuleBase.MAX_SOURCES - 1;
        this.octaves = value;
    }
    
    public void SetAllSourceTypes(BasisType newBasisType, InterpolationType newInterpolationType) {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            this.basisFunctions[i] = new ImplicitBasisFunction(newBasisType, newInterpolationType);
        }
    }
    
    public void SetSourceType(int which, BasisType newBasisType, InterpolationType newInterpolationType) {
        if (which >= ImplicitModuleBase.MAX_SOURCES || which < 0)
            return;
        
        this.basisFunctions[which].setBasisType(newBasisType);
        this.basisFunctions[which].setInterpolationType(newInterpolationType);
    }
    
    public void SetSourceOverride(int which, ImplicitModuleBase newSource) {
        if (which < 0 || which >= ImplicitModuleBase.MAX_SOURCES)
            return;
        
        this.sources[which] = newSource;
    }
    
    public void ResetSource(int which) {
        if (which < 0 || which >= ImplicitModuleBase.MAX_SOURCES)
            return;
        
        this.sources[which] = this.basisFunctions[which];
    }
    
    public void ResetAllSources() {
        for (int c = 0; c < ImplicitModuleBase.MAX_SOURCES; ++c)
            this.sources[c] = this.basisFunctions[c];
    }
    
    public ImplicitBasisFunction GetBasis(int which) {
        if (which < 0 || which >= ImplicitModuleBase.MAX_SOURCES)
            return null;
        
        return this.basisFunctions[which];
    }
    
    public double getFrequency() {
        return frequency;
    }
    
    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }
    
    public double getLacunarity() {
        return lacunarity;
    }
    
    public void setLacunarity(double lacunarity) {
        this.lacunarity = lacunarity;
    }
    
    public double getGain() {
        return gain;
    }
    
    public void setGain(double gain) {
        this.gain = gain;
    }
    
    public double getOffset() {
        return offset;
    }
    
    public void setOffset(double offset) {
        this.offset = offset;
    }
    
    public double getH() {
        return h;
    }
    
    public void setH(double h) {
        this.h = h;
    }
    
    public double Get(double x, double y) {
        double v;
        switch (type) {
        case FractionalBrownianMotion:
            v = FractionalBrownianMotion_Get(x, y);
            break;
        case RidgedMulti:
            v = RidgedMulti_Get(x, y);
            break;
        case Billow:
            v = Billow_Get(x, y);
            break;
        case Multi:
            v = Multi_Get(x, y);
            break;
        case HybridMulti:
            v = HybridMulti_Get(x, y);
            break;
        default:
            v = FractionalBrownianMotion_Get(x, y);
            break;
        }
        return Maths.Clamp(v, -1.0, 1.0);
    }
    
    public double Get(double x, double y, double z) {
        double val;
        switch (type) {
        case FractionalBrownianMotion:
            val = FractionalBrownianMotion_Get(x, y, z);
            break;
        case RidgedMulti:
            val = RidgedMulti_Get(x, y, z);
            break;
        case Billow:
            val = Billow_Get(x, y, z);
            break;
        case Multi:
            val = Multi_Get(x, y, z);
            break;
        case HybridMulti:
            val = HybridMulti_Get(x, y, z);
            break;
        default:
            val = FractionalBrownianMotion_Get(x, y, z);
            break;
        }
        return Maths.Clamp(val, -1.0, 1.0);
    }
    
    public double Get(double x, double y, double z, double w) {
        double val;
        switch (type) {
        case FractionalBrownianMotion:
            val = FractionalBrownianMotion_Get(x, y, z, w);
            break;
        case RidgedMulti:
            val = RidgedMulti_Get(x, y, z, w);
            break;
        case Billow:
            val = Billow_Get(x, y, z, w);
            break;
        case Multi:
            val = Multi_Get(x, y, z, w);
            break;
        case HybridMulti:
            val = HybridMulti_Get(x, y, z, w);
            break;
        default:
            val = FractionalBrownianMotion_Get(x, y, z, w);
            break;
        }
        return Maths.Clamp(val, -1.0, 1.0);
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        double val;
        switch (type) {
        case FractionalBrownianMotion:
            val = FractionalBrownianMotion_Get(x, y, z, w, u, v);
            break;
        case RidgedMulti:
            val = RidgedMulti_Get(x, y, z, w, u, v);
            break;
        case Billow:
            val = Billow_Get(x, y, z, w, u, v);
            break;
        case Multi:
            val = Multi_Get(x, y, z, w, u, v);
            break;
        case HybridMulti:
            val = HybridMulti_Get(x, y, z, w, u, v);
            break;
        default:
            val = FractionalBrownianMotion_Get(x, y, z, w, u, v);
            break;
        }
        
        return Maths.Clamp(val, -1.0, 1.0);
    }
    
    private void FractionalBrownianMotion_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            expArray[i] = Math.pow(lacunarity, -i * h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 0.00;
        double maxvalue = 0.00;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue += -1.0 * expArray[i];
            maxvalue += 1.0 * expArray[i];
            
            final double a = -1.0;
            final double b = 1.0;
            double scale = (b - a) / (maxvalue - minvalue);
            double bias = a - minvalue * scale;
            correct[i][0] = scale;
            correct[i][1] = bias;
        }
    }
    
    private void RidgedMulti_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            expArray[i] = Math.pow(lacunarity, -i * h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 0.00;
        double maxvalue = 0.00;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue += (offset - 1.0) * (offset - 1.0) * expArray[i];
            maxvalue += (offset) * (offset) * expArray[i];
            
            final double a = -1.0;
            final double b = 1.0;
            double scale = (b - a) / (maxvalue - minvalue);
            double bias = a - minvalue * scale;
            correct[i][0] = scale;
            correct[i][1] = bias;
        }
        
    }
    
    private void Billow_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            expArray[i] = Math.pow(lacunarity, -i * h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 0.0;
        double maxvalue = 0.0;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue += -1.0 * expArray[i];
            maxvalue += 1.0 * expArray[i];
            
            final double a = -1.0;
            final double b = 1.0;
            double scale = (b - a) / (maxvalue - minvalue);
            double bias = a - minvalue * scale;
            correct[i][0] = scale;
            correct[i][1] = bias;
        }
        
    }
    
    private void Multi_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            expArray[i] = Math.pow(lacunarity, -i * h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 1.0;
        double maxvalue = 1.0;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue *= -1.0 * expArray[i] + 1.0;
            maxvalue *= 1.0 * expArray[i] + 1.0;
            
            final double a = -1.0;
            final double b = 1.0;
            double scale = (b - a) / (maxvalue - minvalue);
            double bias = a - minvalue * scale;
            correct[i][0] = scale;
            correct[i][1] = bias;
        }
        
    }
    
    private void HybridMulti_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            expArray[i] = Math.pow(lacunarity, -i * h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        final double a = -1.0;
        final double b = 1.0;
        
        double minvalue = offset - 1.0;
        double maxvalue = offset + 1.0;
        double weightmin = gain * minvalue;
        double weightmax = gain * maxvalue;
        
        double scale = (b - a) / (maxvalue - minvalue);
        double bias = a - minvalue * scale;
        correct[0][0] = scale;
        correct[0][1] = bias;
        
        for (int i = 1; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            if (weightmin > 1.00)
                weightmin = 1.00;
            if (weightmax > 1.00)
                weightmax = 1.00;
            
            double signal = (offset - 1.0) * expArray[i];
            minvalue += signal * weightmin;
            weightmin *= gain * signal;
            
            signal = (offset + 1.0) * expArray[i];
            maxvalue += signal * weightmax;
            weightmax *= gain * signal;
            
            scale = (b - a) / (maxvalue - minvalue);
            bias = a - minvalue * scale;
            correct[i][0] = scale;
            correct[i][1] = bias;
        }
        
    }
    
    private double FractionalBrownianMotion_Get(double x, double y) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y) * expArray[i];
            value += signal;
            x *= lacunarity;
            y *= lacunarity;
        }
        
        return value;
    }
    
    private double FractionalBrownianMotion_Get(double x, double y, double z) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z) * expArray[i];
            value += signal;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
        }
        
        return value;
    }
    
    private double FractionalBrownianMotion_Get(double x, double y, double z, double w) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z, w) * expArray[i];
            value += signal;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double FractionalBrownianMotion_Get(double x, double y, double z, double w, double u, double v) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        u *= frequency;
        v *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z, w, u, v) * expArray[i];
            value += signal;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
            u *= lacunarity;
            v *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y) {
        double value = 1.00;
        x *= frequency;
        y *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            value *= sources[i].Get(x, y) * expArray[i] + 1.0;
            x *= lacunarity;
            y *= lacunarity;
            
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y, double z, double w) {
        double value = 1.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            value *= sources[i].Get(x, y, z, w) * expArray[i] + 1.0;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y, double z) {
        double value = 1.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            value *= sources[i].Get(x, y, z) * expArray[i] + 1.0;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y, double z, double w, double u, double v) {
        double value = 1.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        u *= frequency;
        v *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            value *= sources[i].Get(x, y, z, w, u, v) * expArray[i] + 1.00;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
            u *= lacunarity;
            v *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            
        }
        
        value += 0.5;
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y, double z, double w) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z, w);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
        }
        
        value += 0.5;
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y, double z) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
        }
        
        value += 0.5;
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y, double z, double w, double u, double v) {
        double value = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        u *= frequency;
        v *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z, w, u, v);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
            u *= lacunarity;
            v *= lacunarity;
        }
        
        value += 0.5;
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y) {
        double result = 0.00;
        x *= frequency;
        y *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y);
            signal = offset - Math.abs(signal);
            signal *= signal;
            result += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            
        }
        
        return result * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y, double z, double w) {
        double result = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z, w);
            signal = offset - Math.abs(signal);
            signal *= signal;
            result += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
        }
        
        return result * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y, double z) {
        double result = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z);
            signal = offset - Math.abs(signal);
            signal *= signal;
            result += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
        }
        
        return result * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y, double z, double w, double u, double v) {
        double result = 0.00;
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        u *= frequency;
        v *= frequency;
        
        for (int i = 0; i < octaves; ++i) {
            double signal = sources[i].Get(x, y, z, w, u, v);
            signal = offset - Math.abs(signal);
            signal *= signal;
            result += signal * expArray[i];
            
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
            u *= lacunarity;
            v *= lacunarity;
        }
        
        return result * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y) {
        x *= frequency;
        y *= frequency;
        
        double value = sources[0].Get(x, y) + offset;
        double weight = gain * value;
        x *= lacunarity;
        y *= lacunarity;
        
        for (int i = 1; i < octaves; ++i) {
            if (weight > 1.0)
                weight = 1.0;
            double signal = (sources[i].Get(x, y) + offset) * expArray[i];
            value += weight * signal;
            weight *= gain * signal;
            x *= lacunarity;
            y *= lacunarity;
            
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y, double z) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        
        double value = sources[0].Get(x, y, z) + offset;
        double weight = gain * value;
        x *= lacunarity;
        y *= lacunarity;
        z *= lacunarity;
        
        for (int i = 1; i < octaves; ++i) {
            if (weight > 1.0)
                weight = 1.0;
            double signal = (sources[i].Get(x, y, z) + offset) * expArray[i];
            value += weight * signal;
            weight *= gain * signal;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y, double z, double w) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        
        double value = sources[0].Get(x, y, z, w) + offset;
        double weight = gain * value;
        x *= lacunarity;
        y *= lacunarity;
        z *= lacunarity;
        w *= lacunarity;
        
        for (int i = 1; i < octaves; ++i) {
            if (weight > 1.0)
                weight = 1.0;
            double signal = (sources[i].Get(x, y, z, w) + offset) * expArray[i];
            value += weight * signal;
            weight *= gain * signal;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y, double z, double w, double u, double v) {
        x *= frequency;
        y *= frequency;
        z *= frequency;
        w *= frequency;
        u *= frequency;
        v *= frequency;
        
        double value = sources[0].Get(x, y, z, w, u, v) + offset;
        double weight = gain * value;
        x *= lacunarity;
        y *= lacunarity;
        z *= lacunarity;
        w *= lacunarity;
        u *= lacunarity;
        v *= lacunarity;
        
        for (int i = 1; i < octaves; ++i) {
            if (weight > 1.0)
                weight = 1.0;
            double signal = (sources[i].Get(x, y, z, w, u, v) + offset) * expArray[i];
            value += weight * signal;
            weight *= gain * signal;
            x *= lacunarity;
            y *= lacunarity;
            z *= lacunarity;
            w *= lacunarity;
            u *= lacunarity;
            v *= lacunarity;
        }
        
        return value * correct[octaves - 1][0] + correct[octaves - 1][1];
    }
}
