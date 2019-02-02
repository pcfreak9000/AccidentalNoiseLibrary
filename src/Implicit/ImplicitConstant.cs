using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitConstant : ImplicitModuleBase
    {
        public ImplicitConstant()
        {
            this.Value = 0.00;
        }

        public ImplicitConstant(double value)
        {
            this.Value = value;
        }

        public double Value { get;  set; }

        public override double Get(double x, double y)
        {
            return this.Value;
        }

        public override double Get(double x, double y, double z)
        {
            return this.Value;
        }

        public override double Get(double x, double y, double z, double w)
        {
            return this.Value;
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return this.Value;
        }
    }
}
