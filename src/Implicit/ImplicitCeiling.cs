﻿using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitCeiling : ImplicitModuleBase
    {
        public ImplicitCeiling(ImplicitModuleBase source)
        {
            this.Source = source;
        }

        public ImplicitModuleBase Source { get; set; }

        public override double Get(double x, double y)
        {
            return Math.Ceiling(this.Source.Get(x, y));
        }

        public override double Get(double x, double y, double z)
        {
            return Math.Ceiling(this.Source.Get(x, y, z));
        }

        public override double Get(double x, double y, double z, double w)
        {
            return Math.Ceiling(this.Source.Get(x, y, z, w));
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return Math.Ceiling(this.Source.Get(x, y, z, w, u, v));
        }
    }
}