using System;
using System.Collections.Generic;
using System.Linq;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitCombiner : ImplicitModuleBase
    {
        private final HashSet<ImplicitModuleBase> sources = new HashSet<ImplicitModuleBase>();

        public ImplicitCombiner(CombinerType type)
        {
            this.CombinerType = type;
        }

        public CombinerType CombinerType { get; set; }

        public void AddSource(ImplicitModuleBase module)
        {
            this.sources.Add(module);
        }

        public void RemoveSource(ImplicitModuleBase module)
        {
            this.sources.Remove(module);
        }

        public void ClearSources()
        {
            this.sources.Clear();
        }

        public override double Get(double x, double y)
        {
            switch (this.CombinerType)
            {
                case CombinerType.Add:
                    return this.AddGet(x, y);
                case CombinerType.Multiply:
                    return this.MultiplyGet(x, y);
                case CombinerType.Max:
                    return this.MaxGet(x, y);
                case CombinerType.Min:
                    return this.MinGet(x, y);
                case CombinerType.Average:
                    return this.AverageGet(x, y);
                default:
                    return 0.0;
            }
        }

        public override double Get(double x, double y, double z)
        {
            switch (this.CombinerType)
            {
                case CombinerType.Add:
                    return this.AddGet(x, y, z);
                case CombinerType.Multiply:
                    return this.MultiplyGet(x, y, z);
                case CombinerType.Max:
                    return this.MaxGet(x, y, z);
                case CombinerType.Min:
                    return this.MinGet(x, y, z);
                case CombinerType.Average:
                    return this.AverageGet(x, y, z);
                default:
                    return 0.0;
            }
        }

        public override double Get(double x, double y, double z, double w)
        {
            switch (this.CombinerType)
            {
                case CombinerType.Add:
                    return this.AddGet(x, y, z, w);
                case CombinerType.Multiply:
                    return this.MultiplyGet(x, y, z, w);
                case CombinerType.Max:
                    return this.MaxGet(x, y, z, w);
                case CombinerType.Min:
                    return this.MinGet(x, y, z, w);
                case CombinerType.Average:
                    return this.AverageGet(x, y, z, w);
                default:
                    return 0.0;
            }
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            switch (this.CombinerType)
            {
                case CombinerType.Add:
                    return this.AddGet(x, y, z, w, u, v);
                case CombinerType.Multiply:
                    return this.MultiplyGet(x, y, z, w, u, v);
                case CombinerType.Max:
                    return this.MaxGet(x, y, z, w, u, v);
                case CombinerType.Min:
                    return this.MinGet(x, y, z, w, u, v);
                case CombinerType.Average:
                    return this.AverageGet(x, y, z, w, u, v);
                default:
                    return 0.0;
            }
        }


        private double AddGet(double x, double y)
        {
            return this.sources.Sum(source => source.Get(x, y));
        }

        private double AddGet(double x, double y, double z)
        {
            return this.sources.Sum(source => source.Get(x, y, z));
        }

        private double AddGet(double x, double y, double z, double w)
        {
            return this.sources.Sum(source => source.Get(x, y, z, w));
        }

        private double AddGet(double x, double y, double z, double w, double u, double v)
        {
            return this.sources.Sum(source => source.Get(x, y, z, w, u, v));
        }


        private double MultiplyGet(double x, double y)
        {
            return this.sources.Aggregate(1.00, (current, source) => current * source.Get(x, y));
        }

        private double MultiplyGet(double x, double y, double z)
        {
            return this.sources.Aggregate(1.00, (current, source) => current * source.Get(x, y, z));
        }

        private double MultiplyGet(double x, double y, double z, double w)
        {
            return this.sources.Aggregate(1.00, (current, source) => current * source.Get(x, y,z,w));
        }

        private double MultiplyGet(double x, double y, double z, double w, double u, double v)
        {
            return this.sources.Aggregate(1.00, (current, source) => current * source.Get(x, y, z, w, u, v));
        }


        private double MinGet(double x, double y)
        {
            return this.sources.Min(source => source.Get(x, y));
        }

        private double MinGet(double x, double y, double z)
        {
            return this.sources.Min(source => source.Get(x, y, z));
        }

        private double MinGet(double x, double y, double z, double w)
        {
            return this.sources.Min(source => source.Get(x, y, z, w));
        }

        private double MinGet(double x, double y, double z, double w, double u, double v)
        {
            return this.sources.Min(source => source.Get(x, y, z, w, u, v));
        }


        private double MaxGet(double x, double y)
        {
            return this.sources.Max(source => source.Get(x, y));
        }

        private double MaxGet(double x, double y, double z)
        {
            return this.sources.Max(source => source.Get(x, y, z));
        }

        private double MaxGet(double x, double y, double z, double w)
        {
            return this.sources.Max(source => source.Get(x, y, z, w));
        }

        private double MaxGet(double x, double y, double z, double w, double u, double v)
        {
            return this.sources.Max(source => source.Get(x, y, z, w, u, v));
        }


        private double AverageGet(double x, double y)
        {
            return this.sources.Average(source => source.Get(x, y));
        }

        private double AverageGet(double x, double y, double z)
        {
            return this.sources.Average(source => source.Get(x, y, z));
        }

        private double AverageGet(double x, double y, double z, double w)
        {
            return this.sources.Average(source => source.Get(x, y, z, w));
        }

        private double AverageGet(double x, double y, double z, double w, double u, double v)
        {
            return this.sources.Average(source => source.Get(x, y, z, w, u, v));
        }
    }
}