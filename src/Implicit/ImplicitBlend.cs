using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitBlend : ImplicitModuleBase
    {
        public ImplicitBlend(ImplicitModuleBase source, double low = 0.00, double high = 0.00)
        {
            this.Source = source;
            this.Low = new ImplicitConstant(low);
            this.High = new ImplicitConstant(high);
        }

        public ImplicitModuleBase Source { get; set; }

        public ImplicitModuleBase Low { get; set; }

        public ImplicitModuleBase High { get; set; }

        public override double Get(double x, double y)
        {
            var v1 = this.Low.Get(x, y);
            var v2 = this.High.Get(x, y);
            var blend = (this.Source.Get(x, y) + 1.0) * 0.5;
            return Utilities.Maths.Lerp(blend, v1, v2);
        }

        public override double Get(double x, double y, double z)
        {
            var v1 = this.Low.Get(x, y, z);
            var v2 = this.High.Get(x, y, z);
            var blend = this.Source.Get(x, y, z);
            return Utilities.Maths.Lerp(blend, v1, v2);
        }

        public override double Get(double x, double y, double z, double w)
        {
            var v1 = this.Low.Get(x, y, z, w);
            var v2 = this.High.Get(x, y, z, w);
            var blend = this.Source.Get(x, y, z, w);
            return Utilities.Maths.Lerp(blend, v1, v2);
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            var v1 = this.Low.Get(x, y, z, w, u, v);
            var v2 = this.High.Get(x, y, z, w, u, v);
            var blend = this.Source.Get(x, y, z, w, u, v);
            return Utilities.Maths.Lerp(blend, v1, v2);
        }
    }
}
