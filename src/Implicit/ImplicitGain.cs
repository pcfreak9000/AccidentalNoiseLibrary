using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitGain : ImplicitModuleBase
    {
        public ImplicitGain(ImplicitModuleBase source, double gain = 0.00)
        {
            this.Source = source;
            this.Gain = new ImplicitConstant(gain);
        }

        public ImplicitGain(ImplicitModuleBase source, ImplicitModuleBase gain)
        {
            this.Source = source;
            this.Gain = gain;
        }

        public ImplicitModuleBase Source { get; set; }

        public ImplicitModuleBase Gain { get; set; }

        public override double Get(double x, double y)
        {
            return Utilities.Gain(this.Gain.Get(x, y), this.Source.Get(x, y));
        }

        public override double Get(double x, double y, double z)
        {
            return Utilities.Gain(this.Gain.Get(x, y, z), this.Source.Get(x, y, z));
        }

        public override double Get(double x, double y, double z, double w)
        {
            return Utilities.Gain(this.Gain.Get(x, y, z, w), this.Source.Get(x, y, z, w));
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return Utilities.Gain(this.Gain.Get(x, y, z, w, u, v), this.Source.Get(x, y, z, w, u, v));
        }
    }
}
