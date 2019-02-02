using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitPow : ImplicitModuleBase
    {
        public ImplicitPow(ImplicitModuleBase source, double power)
        {
            this.Source = source;
            this.Power = new ImplicitConstant(power);
        }

        public ImplicitPow(ImplicitModuleBase source, ImplicitModuleBase power)
        {
            this.Source = source;
            this.Power = power;
        }

        public ImplicitModuleBase Source { get; set; }

        public ImplicitModuleBase Power { get; set; }

        public override double Get(double x, double y)
        {
            return Math.Pow(this.Source.Get(x, y), this.Power.Get(x, y));
        }

        public override double Get(double x, double y, double z)
        {
            return Math.Pow(this.Source.Get(x, y, z), this.Power.Get(x, y, z));
        }

        public override double Get(double x, double y, double z, double w)
        {
            return Math.Pow(this.Source.Get(x, y, z, w), this.Power.Get(x, y, z, w));
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return Math.Pow(this.Source.Get(x, y, z, w, u, v), this.Power.Get(x, y, z, w, u, v));
        }
    }
}