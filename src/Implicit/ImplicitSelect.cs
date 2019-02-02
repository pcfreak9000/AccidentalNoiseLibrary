using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitSelect : ImplicitModuleBase
    {
        public ImplicitSelect(ImplicitModuleBase source, double low = 0.00, double high = 0.00, double falloff = 0.00, double threshold = 0.00)
        {
            this.Source = source;
            this.Low = new ImplicitConstant(low);
            this.High = new ImplicitConstant(high);
            this.Falloff = new ImplicitConstant(falloff);
            this.Threshold = new ImplicitConstant(threshold);
        }

        public ImplicitModuleBase Source { get; set; }

        public ImplicitModuleBase Low { get; set; }

        public ImplicitModuleBase High { get; set; }

        public ImplicitModuleBase Threshold { get; set; }

        public ImplicitModuleBase Falloff { get; set; }

        public override double Get(double x, double y)
        {
            var value = this.Source.Get(x, y);
            var falloff = this.Falloff.Get(x, y);
            var threshold = this.Threshold.Get(x, y);

            if (falloff > 0.0)
            {
                if (value < (threshold - falloff))
                {
                    // Lies outside of falloff area below threshold, return first source
                    return this.Low.Get(x, y);
                }
                if (value > (threshold + falloff))
                {
                    // Lies outside of falloff area above threshold, return second source
                    return this.High.Get(x, y);
                }
                // Lies within falloff area.
                var lower = threshold - falloff;
                var upper = threshold + falloff;
                var blend = Utilities.QuinticBlend((value - lower) / (upper - lower));
                return Utilities.Maths.Lerp(blend, this.Low.Get(x, y), this.High.Get(x, y));
            }

            return (value < threshold ? this.Low.Get(x, y) : this.High.Get(x, y));
        }

        public override double Get(double x, double y, double z)
        {
            var value = this.Source.Get(x, y, z);
            var falloff = this.Falloff.Get(x, y, z);
            var threshold = this.Threshold.Get(x, y, z);

            if (falloff > 0.0)
            {
                if (value < (threshold - falloff))
                {
                    // Lies outside of falloff area below threshold, return first source
                    return this.Low.Get(x, y, z);
                }
                if (value > (threshold + falloff))
                {
                    // Lies outside of falloff area above threshold, return second source
                    return this.High.Get(x, y, z);
                }
                // Lies within falloff area.
                var lower = threshold - falloff;
                var upper = threshold + falloff;
                var blend = Utilities.QuinticBlend((value - lower) / (upper - lower));
                return Utilities.Maths.Lerp(blend, this.Low.Get(x, y, z), this.High.Get(x, y, z));
            }

            return (value < threshold ? this.Low.Get(x, y, z) : this.High.Get(x, y, z));
        }

        public override double Get(double x, double y, double z, double w)
        {
            var value = this.Source.Get(x, y, z, w);
            var falloff = this.Falloff.Get(x, y, z, w);
            var threshold = this.Threshold.Get(x, y, z, w);

            if (falloff > 0.0)
            {
                if (value < (threshold - falloff))
                {
                    // Lies outside of falloff area below threshold, return first source
                    return this.Low.Get(x, y, z, w);
                }
                if (value > (threshold + falloff))
                {
                    // Lise outside of falloff area above threshold, return second source
                    return this.High.Get(x, y, z, w);
                }
                // Lies within falloff area.
                var lower = threshold - falloff;
                var upper = threshold + falloff;
                var blend = Utilities.QuinticBlend((value - lower) / (upper - lower));
                return Utilities.Maths.Lerp(blend, this.Low.Get(x, y, z, w), this.High.Get(x, y, z, w));
            }

            return value < threshold ? this.Low.Get(x, y, z, w) : this.High.Get(x, y, z, w);
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            var value = this.Source.Get(x, y, z, w, u, v);
            var falloff = this.Falloff.Get(x, y, z, w, u, v);
            var threshold = this.Threshold.Get(x, y, z, w, u, v);

            if (falloff > 0.0)
            {
                if (value < (threshold - falloff))
                {
                    // Lies outside of falloff area below threshold, return first source
                    return this.Low.Get(x, y, z, w, u, v);
                }
                if (value > (threshold + falloff))
                {
                    // Lies outside of falloff area above threshold, return second source
                    return this.High.Get(x, y, z, w, u, v);
                }
                // Lies within falloff area.
                var lower = threshold - falloff;
                var upper = threshold + falloff;
                var blend = Utilities.QuinticBlend((value - lower) / (upper - lower));
                return Utilities.Maths.Lerp(blend, this.Low.Get(x, y, z, w, u, v), this.High.Get(x, y, z, w, u, v));
            }

            return (value < threshold ? this.Low.Get(x, y, z, w, u, v) : this.High.Get(x, y, z, w, u, v));
        }
    }

}
