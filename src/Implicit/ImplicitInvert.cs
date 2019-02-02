using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitInvert : ImplicitModuleBase
    {
        public ImplicitInvert(ImplicitModuleBase source)
        {
            this.Source = source;
        }
        
        public ImplicitModuleBase Source { set; get; }

        public override double Get(double x, double y)
        {
            return -this.Source.Get(x, y);
        }

        public override double Get(double x, double y, double z)
        {
            return -this.Source.Get(x, y, z);
        }

        public override double Get(double x, double y, double z, double w)
        {
            return -this.Source.Get(x, y, z, w);
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return -this.Source.Get(x, y, z, w, u, v);
        }
    }
}
