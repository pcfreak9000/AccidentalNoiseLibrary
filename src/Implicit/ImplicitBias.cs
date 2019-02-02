using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitBias : ImplicitModuleBase
    {
        public ImplicitBias(ImplicitModuleBase source, double bias)
        {
            this.Source = source;
            this.Bias = new ImplicitConstant(bias);
        }

        public ImplicitBias(ImplicitModuleBase source, ImplicitModuleBase bias)
        {
            this.Source = source;
            this.Bias = bias;
        }

        public ImplicitModuleBase Source { get; set; }

        public ImplicitModuleBase Bias { get; set; }

        public override double Get(double x, double y)
        {
            return Utilities.Bias(this.Bias.Get(x, y), this.Source.Get(x, y));
        }

        public override double Get(double x, double y, double z)
        {
            return Utilities.Bias(this.Bias.Get(x, y, z), this.Source.Get(x, y, z));
        }

        public override double Get(double x, double y, double z, double w)
        {
            return Utilities.Bias(this.Bias.Get(x, y, z, w), this.Source.Get(x, y, z, w));
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return Utilities.Bias(this.Bias.Get(x, y, z, w, u, v), this.Source.Get(x, y, z, w, u, v));
        }
    }
}