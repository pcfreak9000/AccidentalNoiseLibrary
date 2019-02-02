package Implicit;

import java.util.HashSet;
import java.util.Set;

import Enums.CombinerType;

public final class ImplicitCombiner extends ImplicitModuleBase {
    private final Set<ImplicitModuleBase> sources = new HashSet<ImplicitModuleBase>();
    
    public ImplicitCombiner(CombinerType type) {
        this.CombinerType = type;
    }
    
    public CombinerType CombinerType;
    
    public CombinerType getCombinerType() {
        return CombinerType;
    }
    
    public void setCombinerType(CombinerType combinerType) {
        CombinerType = combinerType;
    }
    
    public void AddSource(ImplicitModuleBase module) {
        this.sources.add(module);
    }
    
    public void RemoveSource(ImplicitModuleBase module) {
        this.sources.remove(module);
    }
    
    public void ClearSources() {
        this.sources.clear();
    }
    
    public double Get(double x, double y) {
        switch (this.CombinerType) {
        case Add:
            return this.AddGet(x, y);
        case Multiply:
            return this.MultiplyGet(x, y);
        case Max:
            return this.MaxGet(x, y);
        case Min:
            return this.MinGet(x, y);
        case Average:
            return this.AverageGet(x, y);
        default:
            return 0.0;
        }
    }
    
    public double Get(double x, double y, double z) {
        switch (this.CombinerType) {
        case Add:
            return this.AddGet(x, y, z);
        case Multiply:
            return this.MultiplyGet(x, y, z);
        case Max:
            return this.MaxGet(x, y, z);
        case Min:
            return this.MinGet(x, y, z);
        case Average:
            return this.AverageGet(x, y, z);
        default:
            return 0.0;
        }
    }
    
    public double Get(double x, double y, double z, double w) {
        switch (this.CombinerType) {
        case Add:
            return this.AddGet(x, y, z, w);
        case Multiply:
            return this.MultiplyGet(x, y, z, w);
        case Max:
            return this.MaxGet(x, y, z, w);
        case Min:
            return this.MinGet(x, y, z, w);
        case Average:
            return this.AverageGet(x, y, z, w);
        default:
            return 0.0;
        }
    }
    
    public double Get(double x, double y, double z, double w, double u, double v) {
        switch (this.CombinerType) {
        case Add:
            return this.AddGet(x, y, z, w, u, v);
        case Multiply:
            return this.MultiplyGet(x, y, z, w, u, v);
        case Max:
            return this.MaxGet(x, y, z, w, u, v);
        case Min:
            return this.MinGet(x, y, z, w, u, v);
        case Average:
            return this.AverageGet(x, y, z, w, u, v);
        default:
            return 0.0;
        }
    }
    
    private double AddGet(double x, double y) {
        double d = 0;
        for (ImplicitModuleBase imb : sources) {
            d += imb.Get(x, y);
        }
        return d;
    }
    
    private double AddGet(double x, double y, double z) {
        double d = 0;
        for (ImplicitModuleBase imb : sources) {
            d += imb.Get(x, y, z);
        }
        return d;
    }
    
    private double AddGet(double x, double y, double z, double w) {
        double d = 0;
        for (ImplicitModuleBase imb : sources) {
            d += imb.Get(x, y, z, w);
        }
        return d;
    }
    
    private double AddGet(double x, double y, double z, double w, double u, double v) {
        double d = 0;
        for (ImplicitModuleBase imb : sources) {
            d += imb.Get(x, y, z, w, u, v);
        }
        return d;
    }
    
    private double MultiplyGet(double x, double y) {
        double d = 1;
        for (ImplicitModuleBase imb : sources) {
            d *= imb.Get(x, y);
        }
        return d;
    }
    
    private double MultiplyGet(double x, double y, double z) {
        double d = 1;
        for (ImplicitModuleBase imb : sources) {
            d *= imb.Get(x, y, z);
        }
        return d;
    }
    
    private double MultiplyGet(double x, double y, double z, double w) {
        double d = 1;
        for (ImplicitModuleBase imb : sources) {
            d *= imb.Get(x, y, z, w);
        }
        return d;
    }
    
    private double MultiplyGet(double x, double y, double z, double w, double u, double v) {
        double d = 1;
        for (ImplicitModuleBase imb : sources) {
            d *= imb.Get(x, y, z, w, u, v);
        }
        return d;
    }
    
    //TODO correct?
    private double MinGet(double x, double y) {
        double d = Double.POSITIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.min(d, imb.Get(x, y));
        }
        return d;
    }
    
    private double MinGet(double x, double y, double z) {
        double d = Double.POSITIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.min(d, imb.Get(x, y, z));
        }
        return d;
    }
    
    private double MinGet(double x, double y, double z, double w) {
        double d = Double.POSITIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.min(d, imb.Get(x, y, z, w));
        }
        return d;
    }
    
    private double MinGet(double x, double y, double z, double w, double u, double v) {
        double d = Double.POSITIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.min(d, imb.Get(x, y, z, w, u, v));
        }
        return d;
    }
    
    private double MaxGet(double x, double y) {
        double d = Double.NEGATIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.max(d, imb.Get(x, y));
        }
        return d;
    }
    
    private double MaxGet(double x, double y, double z) {
        double d = Double.NEGATIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.max(d, imb.Get(x, y, z));
        }
        return d;
    }
    
    private double MaxGet(double x, double y, double z, double w) {
        double d = Double.NEGATIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.max(d, imb.Get(x, y, z, w));
        }
        return d;
    }
    
    private double MaxGet(double x, double y, double z, double w, double u, double v) {
        double d = Double.NEGATIVE_INFINITY;
        for (ImplicitModuleBase imb : sources) {
            d = Math.max(d, imb.Get(x, y, z, w, u, v));
        }
        return d;
    }
    
    private double AverageGet(double x, double y) {
        return AddGet(x, y) / sources.size();
    }
    
    private double AverageGet(double x, double y, double z) {
        return AddGet(x, y, z) / sources.size();
    }
    
    private double AverageGet(double x, double y, double z, double w) {
        return AddGet(x, y, z, w) / sources.size();
    }
    
    private double AverageGet(double x, double y, double z, double w, double u, double v) {
        return AddGet(x, y, z, w, u, v) / sources.size();
    }
}
