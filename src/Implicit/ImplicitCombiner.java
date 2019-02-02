package implicit;

import java.util.HashSet;
import java.util.Set;

import enums.CombinerType;

public final class ImplicitCombiner extends ImplicitModuleBase {
    private final Set<ImplicitModuleBase> sources = new HashSet<>();

    public ImplicitCombiner(final CombinerType type) {
        this.CombinerType = type;
    }

    private CombinerType CombinerType;

    public CombinerType getCombinerType() {
        return this.CombinerType;
    }

    public void setCombinerType(final CombinerType combinerType) {
        this.CombinerType = combinerType;
    }

    public void AddSource(final ImplicitModuleBase module) {
        this.sources.add(module);
    }

    public void RemoveSource(final ImplicitModuleBase module) {
        this.sources.remove(module);
    }

    public void ClearSources() {
        this.sources.clear();
    }

    @Override
    public double get(final double x, final double y) {
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

    @Override
    public double get(final double x, final double y, final double z) {
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

    @Override
    public double get(final double x, final double y, final double z, final double w) {
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

    @Override
    public double get(final double x, final double y, final double z, final double w, final double u, final double v) {
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

    private double AddGet(final double x, final double y) {
        double d = 0;
        for (final ImplicitModuleBase imb : this.sources) {
            d += imb.get(x, y);
        }
        return d;
    }

    private double AddGet(final double x, final double y, final double z) {
        double d = 0;
        for (final ImplicitModuleBase imb : this.sources) {
            d += imb.get(x, y, z);
        }
        return d;
    }

    private double AddGet(final double x, final double y, final double z, final double w) {
        double d = 0;
        for (final ImplicitModuleBase imb : this.sources) {
            d += imb.get(x, y, z, w);
        }
        return d;
    }

    private double AddGet(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        double d = 0;
        for (final ImplicitModuleBase imb : this.sources) {
            d += imb.get(x, y, z, w, u, v);
        }
        return d;
    }

    private double MultiplyGet(final double x, final double y) {
        double d = 1;
        for (final ImplicitModuleBase imb : this.sources) {
            d *= imb.get(x, y);
        }
        return d;
    }

    private double MultiplyGet(final double x, final double y, final double z) {
        double d = 1;
        for (final ImplicitModuleBase imb : this.sources) {
            d *= imb.get(x, y, z);
        }
        return d;
    }

    private double MultiplyGet(final double x, final double y, final double z, final double w) {
        double d = 1;
        for (final ImplicitModuleBase imb : this.sources) {
            d *= imb.get(x, y, z, w);
        }
        return d;
    }

    private double MultiplyGet(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        double d = 1;
        for (final ImplicitModuleBase imb : this.sources) {
            d *= imb.get(x, y, z, w, u, v);
        }
        return d;
    }

    //TODO correct?
    private double MinGet(final double x, final double y) {
        double d = Double.POSITIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.min(d, imb.get(x, y));
        }
        return d;
    }

    private double MinGet(final double x, final double y, final double z) {
        double d = Double.POSITIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.min(d, imb.get(x, y, z));
        }
        return d;
    }

    private double MinGet(final double x, final double y, final double z, final double w) {
        double d = Double.POSITIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.min(d, imb.get(x, y, z, w));
        }
        return d;
    }

    private double MinGet(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        double d = Double.POSITIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.min(d, imb.get(x, y, z, w, u, v));
        }
        return d;
    }

    private double MaxGet(final double x, final double y) {
        double d = Double.NEGATIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.max(d, imb.get(x, y));
        }
        return d;
    }

    private double MaxGet(final double x, final double y, final double z) {
        double d = Double.NEGATIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.max(d, imb.get(x, y, z));
        }
        return d;
    }

    private double MaxGet(final double x, final double y, final double z, final double w) {
        double d = Double.NEGATIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.max(d, imb.get(x, y, z, w));
        }
        return d;
    }

    private double MaxGet(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        double d = Double.NEGATIVE_INFINITY;
        for (final ImplicitModuleBase imb : this.sources) {
            d = Math.max(d, imb.get(x, y, z, w, u, v));
        }
        return d;
    }

    private double AverageGet(final double x, final double y) {
        return AddGet(x, y) / this.sources.size();
    }

    private double AverageGet(final double x, final double y, final double z) {
        return AddGet(x, y, z) / this.sources.size();
    }

    private double AverageGet(final double x, final double y, final double z, final double w) {
        return AddGet(x, y, z, w) / this.sources.size();
    }

    private double AverageGet(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        return AddGet(x, y, z, w, u, v) / this.sources.size();
    }
}
