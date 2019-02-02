using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitTiers : ImplicitModuleBase
    {
        public ImplicitTiers(ImplicitModuleBase source, int tiers = 0, Boolean smooth = true)
        {
            this.Source = source;
            this.Tiers = tiers;
            this.Smooth = smooth;
        }

        public ImplicitModuleBase Source { get; set; }

        public int Tiers { get; set; }

        public Boolean Smooth { get; set; }

        public override double Get(double x, double y)
        {
            var numsteps = Tiers;
            if (this.Smooth) --numsteps;
            var val = Source.Get(x, y);
            var tb = Math.Floor(val * numsteps);
            var tt = tb + 1.0;
            var t = val * numsteps - tb;
            tb /= numsteps;
            tt /= numsteps;
            var u = (this.Smooth ? Utilities.QuinticBlend(t) : 0.0);
            return tb + u * (tt - tb);
        }

        public override double Get(double x, double y, double z)
        {
            var numsteps = Tiers;
            if (this.Smooth) --numsteps;
            var val = Source.Get(x, y, z);
            var tb = Math.Floor(val * numsteps);
            var tt = tb + 1.0;
            var t = val * numsteps - tb;
            tb /= numsteps;
            tt /= numsteps;
            var u = (this.Smooth ? Utilities.QuinticBlend(t) : 0.0);
            return tb + u * (tt - tb);
        }

        public override double Get(double x, double y, double z, double w)
        {
            var numsteps = Tiers;
            if (this.Smooth) --numsteps;
            var val = Source.Get(x, y, z, w);
            var tb = Math.Floor(val * numsteps);
            var tt = tb + 1.0;
            var t = val * numsteps - tb;
            tb /= numsteps;
            tt /= numsteps;
            var u = (this.Smooth ? Utilities.QuinticBlend(t) : 0.0);
            return tb + u * (tt - tb);
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            var numsteps = Tiers;
            if (this.Smooth) --numsteps;
            var val = Source.Get(x, y, z, w, u, v);
            var tb = Math.Floor(val * numsteps);
            var tt = tb + 1.0;
            var t = val * numsteps - tb;
            tb /= numsteps;
            tt /= numsteps;
            var s = (this.Smooth ? Utilities.QuinticBlend(t) : 0.0);
            return tb + s * (tt - tb);
        }
    }
}
