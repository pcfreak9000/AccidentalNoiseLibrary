package Implicit;

import enums.BasisType;
import enums.FractalType;
import enums.InterpolationType;
import util.Maths;

public final class ImplicitFractal extends ImplicitModuleBase {
    //default basisFunctions of this Fractal. reset() will reset sources to the contents of this array
    private final ImplicitBasisFunction[] basisFunctions = new ImplicitBasisFunction[ImplicitModuleBase.MAX_SOURCES];
    //Noise functions that will be used for each octave
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
    
    public ImplicitFractal(final FractalType fractalType, final BasisType basisType,
            final InterpolationType interpolationType) {
        this.setOctaves(8);
        this.setFrequency(1.00);
        this.setLacunarity(2.00);
        this.setFractalType(fractalType);
        this.SetAllSourceTypes(basisType, interpolationType);
        this.ResetAllSources();
    }
    
    @Override
    public int getSeed() {
        return this.seed;
    }
    
    @Override
    public void setSeed(final int value) {
        this.seed = value;
        for (int source = 0; source < ImplicitModuleBase.MAX_SOURCES; source += 1) {
            this.sources[source].setSeed(((this.seed + source * 300)));
        }
    }
    
    public FractalType getType() {
        return this.type;
    }
    
    public ImplicitFractal setFractalType(final FractalType value) {
        this.type = value;
        this.h = (value.h);
        this.gain = (value.gain);
        this.offset = (value.offset);
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
        return this;
    }
    
    public int getOctaves() {
        return this.octaves;
    }
    
    public ImplicitFractal setOctaves(int value) {
        if (value >= ImplicitModuleBase.MAX_SOURCES) {
            value = ImplicitModuleBase.MAX_SOURCES - 1;
        }
        this.octaves = value;
        return this;
    }
    
    public void SetAllSourceTypes(final BasisType newBasisType, final InterpolationType newInterpolationType) {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            this.basisFunctions[i] = new ImplicitBasisFunction(newBasisType, newInterpolationType);
        }
    }
    
    public void SetSourceType(final int which, final BasisType newBasisType,
            final InterpolationType newInterpolationType) {
        if (which >= ImplicitModuleBase.MAX_SOURCES || which < 0) {
            return;
        }
        
        this.basisFunctions[which].setBasisType(newBasisType);
        this.basisFunctions[which].setInterpolationType(newInterpolationType);
    }
    
    public void SetSourceOverride(final int which, final ImplicitModuleBase newSource) {
        if (which < 0 || which >= ImplicitModuleBase.MAX_SOURCES) {
            return;
        }
        
        this.sources[which] = newSource;
    }
    
    public void ResetSource(final int which) {
        if (which < 0 || which >= ImplicitModuleBase.MAX_SOURCES) {
            return;
        }
        
        this.sources[which] = this.basisFunctions[which];
    }
    
    public void ResetAllSources() {
        for (int c = 0; c < ImplicitModuleBase.MAX_SOURCES; ++c) {
            this.sources[c] = this.basisFunctions[c];
        }
    }
    
    public ImplicitBasisFunction GetBasis(final int which) {
        if (which < 0 || which >= ImplicitModuleBase.MAX_SOURCES) {
            return null;
        }
        
        return this.basisFunctions[which];
    }
    
    public double getFrequency() {
        return this.frequency;
    }
    
    public ImplicitFractal setFrequency(final double frequency) {
        this.frequency = frequency;
        return this;
    }
    
    public double getLacunarity() {
        return this.lacunarity;
    }
    
    public ImplicitFractal setLacunarity(final double lacunarity) {
        this.lacunarity = lacunarity;
        return this;
    }
    
    @Override
    public double get(final double x, final double y) {
        double v;
        switch (this.type) {
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
    
    @Override
    public double get(final double x, final double y, final double z) {
        double val;
        switch (this.type) {
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
    
    @Override
    public double get(final double x, final double y, final double z, final double w) {
        double val;
        switch (this.type) {
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
    
    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
        double val;
        switch (this.type) {
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
            this.expArray[i] = Math.pow(this.lacunarity, -i * this.h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 0.00;
        double maxvalue = 0.00;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue += -1.0 * this.expArray[i];
            maxvalue += 1.0 * this.expArray[i];
            
            final double a = -1.0;
            final double b = 1.0;
            final double scale = (b - a) / (maxvalue - minvalue);
            final double bias = a - minvalue * scale;
            this.correct[i][0] = scale;
            this.correct[i][1] = bias;
        }
    }
    
    private void RidgedMulti_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            this.expArray[i] = Math.pow(this.lacunarity, -i * this.h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 0.00;
        double maxvalue = 0.00;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue += (this.offset - 1.0) * (this.offset - 1.0) * this.expArray[i];
            maxvalue += (this.offset) * (this.offset) * this.expArray[i];
            
            final double a = -1.0;
            final double b = 1.0;
            final double scale = (b - a) / (maxvalue - minvalue);
            final double bias = a - minvalue * scale;
            this.correct[i][0] = scale;
            this.correct[i][1] = bias;
        }
        
    }
    
    private void Billow_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            this.expArray[i] = Math.pow(this.lacunarity, -i * this.h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 0.0;
        double maxvalue = 0.0;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue += -1.0 * this.expArray[i];
            maxvalue += 1.0 * this.expArray[i];
            
            final double a = -1.0;
            final double b = 1.0;
            final double scale = (b - a) / (maxvalue - minvalue);
            final double bias = a - minvalue * scale;
            this.correct[i][0] = scale;
            this.correct[i][1] = bias;
        }
        
    }
    
    private void Multi_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            this.expArray[i] = Math.pow(this.lacunarity, -i * this.h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        double minvalue = 1.0;
        double maxvalue = 1.0;
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            minvalue *= -1.0 * this.expArray[i] + 1.0;
            maxvalue *= 1.0 * this.expArray[i] + 1.0;
            
            final double a = -1.0;
            final double b = 1.0;
            final double scale = (b - a) / (maxvalue - minvalue);
            final double bias = a - minvalue * scale;
            this.correct[i][0] = scale;
            this.correct[i][1] = bias;
        }
        
    }
    
    private void HybridMulti_CalculateWeights() {
        for (int i = 0; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            this.expArray[i] = Math.pow(this.lacunarity, -i * this.h);
        }
        
        // Calculate scale/bias pairs by guessing at minimum and maximum values and remapping to [-1,1]
        final double a = -1.0;
        final double b = 1.0;
        
        double minvalue = this.offset - 1.0;
        double maxvalue = this.offset + 1.0;
        double weightmin = this.gain * minvalue;
        double weightmax = this.gain * maxvalue;
        
        double scale = (b - a) / (maxvalue - minvalue);
        double bias = a - minvalue * scale;
        this.correct[0][0] = scale;
        this.correct[0][1] = bias;
        
        for (int i = 1; i < ImplicitModuleBase.MAX_SOURCES; ++i) {
            if (weightmin > 1.00) {
                weightmin = 1.00;
            }
            if (weightmax > 1.00) {
                weightmax = 1.00;
            }
            
            double signal = (this.offset - 1.0) * this.expArray[i];
            minvalue += signal * weightmin;
            weightmin *= this.gain * signal;
            
            signal = (this.offset + 1.0) * this.expArray[i];
            maxvalue += signal * weightmax;
            weightmax *= this.gain * signal;
            
            scale = (b - a) / (maxvalue - minvalue);
            bias = a - minvalue * scale;
            this.correct[i][0] = scale;
            this.correct[i][1] = bias;
        }
        
    }
    
    private double FractionalBrownianMotion_Get(double x, double y) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            final double signal = this.sources[i].get(x, y) * this.expArray[i];
            value += signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
        }
        
        return value;
    }
    
    private double FractionalBrownianMotion_Get(double x, double y, double z) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            final double signal = this.sources[i].get(x, y, z) * this.expArray[i];
            value += signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
        }
        
        return value;
    }
    
    private double FractionalBrownianMotion_Get(double x, double y, double z, double w) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            final double signal = this.sources[i].get(x, y, z, w) * this.expArray[i];
            value += signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double FractionalBrownianMotion_Get(double x, double y, double z, double w, double u, double v) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        u *= this.frequency;
        v *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            final double signal = this.sources[i].get(x, y, z, w, u, v) * this.expArray[i];
            value += signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
            u *= this.lacunarity;
            v *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y) {
        double value = 1.00;
        x *= this.frequency;
        y *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            value *= this.sources[i].get(x, y) * this.expArray[i] + 1.0;
            x *= this.lacunarity;
            y *= this.lacunarity;
            
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y, double z, double w) {
        double value = 1.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            value *= this.sources[i].get(x, y, z, w) * this.expArray[i] + 1.0;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y, double z) {
        double value = 1.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            value *= this.sources[i].get(x, y, z) * this.expArray[i] + 1.0;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Multi_Get(double x, double y, double z, double w, double u, double v) {
        double value = 1.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        u *= this.frequency;
        v *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            value *= this.sources[i].get(x, y, z, w, u, v) * this.expArray[i] + 1.00;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
            u *= this.lacunarity;
            v *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            
        }
        
        value += 0.5;
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y, double z, double w) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y, z, w);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
        }
        
        value += 0.5;
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y, double z) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y, z);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
        }
        
        value += 0.5;
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double Billow_Get(double x, double y, double z, double w, double u, double v) {
        double value = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        u *= this.frequency;
        v *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y, z, w, u, v);
            signal = 2.0 * Math.abs(signal) - 1.0;
            value += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
            u *= this.lacunarity;
            v *= this.lacunarity;
        }
        
        value += 0.5;
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y) {
        double result = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y);
            signal = this.offset - Math.abs(signal);
            signal *= signal;
            result += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            
        }
        
        return result * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y, double z, double w) {
        double result = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y, z, w);
            signal = this.offset - Math.abs(signal);
            signal *= signal;
            result += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
        }
        
        return result * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y, double z) {
        double result = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y, z);
            signal = this.offset - Math.abs(signal);
            signal *= signal;
            result += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
        }
        
        return result * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double RidgedMulti_Get(double x, double y, double z, double w, double u, double v) {
        double result = 0.00;
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        u *= this.frequency;
        v *= this.frequency;
        
        for (int i = 0; i < this.octaves; ++i) {
            double signal = this.sources[i].get(x, y, z, w, u, v);
            signal = this.offset - Math.abs(signal);
            signal *= signal;
            result += signal * this.expArray[i];
            
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
            u *= this.lacunarity;
            v *= this.lacunarity;
        }
        
        return result * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y) {
        x *= this.frequency;
        y *= this.frequency;
        
        double value = this.sources[0].get(x, y) + this.offset;
        double weight = this.gain * value;
        x *= this.lacunarity;
        y *= this.lacunarity;
        
        for (int i = 1; i < this.octaves; ++i) {
            if (weight > 1.0) {
                weight = 1.0;
            }
            final double signal = (this.sources[i].get(x, y) + this.offset) * this.expArray[i];
            value += weight * signal;
            weight *= this.gain * signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y, double z) {
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        
        double value = this.sources[0].get(x, y, z) + this.offset;
        double weight = this.gain * value;
        x *= this.lacunarity;
        y *= this.lacunarity;
        z *= this.lacunarity;
        
        for (int i = 1; i < this.octaves; ++i) {
            if (weight > 1.0) {
                weight = 1.0;
            }
            final double signal = (this.sources[i].get(x, y, z) + this.offset) * this.expArray[i];
            value += weight * signal;
            weight *= this.gain * signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y, double z, double w) {
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        
        double value = this.sources[0].get(x, y, z, w) + this.offset;
        double weight = this.gain * value;
        x *= this.lacunarity;
        y *= this.lacunarity;
        z *= this.lacunarity;
        w *= this.lacunarity;
        
        for (int i = 1; i < this.octaves; ++i) {
            if (weight > 1.0) {
                weight = 1.0;
            }
            final double signal = (this.sources[i].get(x, y, z, w) + this.offset) * this.expArray[i];
            value += weight * signal;
            weight *= this.gain * signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
    
    private double HybridMulti_Get(double x, double y, double z, double w, double u, double v) {
        x *= this.frequency;
        y *= this.frequency;
        z *= this.frequency;
        w *= this.frequency;
        u *= this.frequency;
        v *= this.frequency;
        
        double value = this.sources[0].get(x, y, z, w, u, v) + this.offset;
        double weight = this.gain * value;
        x *= this.lacunarity;
        y *= this.lacunarity;
        z *= this.lacunarity;
        w *= this.lacunarity;
        u *= this.lacunarity;
        v *= this.lacunarity;
        
        for (int i = 1; i < this.octaves; ++i) {
            if (weight > 1.0) {
                weight = 1.0;
            }
            final double signal = (this.sources[i].get(x, y, z, w, u, v) + this.offset) * this.expArray[i];
            value += weight * signal;
            weight *= this.gain * signal;
            x *= this.lacunarity;
            y *= this.lacunarity;
            z *= this.lacunarity;
            w *= this.lacunarity;
            u *= this.lacunarity;
            v *= this.lacunarity;
        }
        
        return value * this.correct[this.octaves - 1][0] + this.correct[this.octaves - 1][1];
    }
}
