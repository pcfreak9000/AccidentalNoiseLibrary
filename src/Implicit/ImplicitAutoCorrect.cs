﻿using System;

namespace TinkerWorX.AccidentalNoiseLibrary
{
    public sealed class ImplicitAutoCorrect : ImplicitModuleBase
    {
        private ImplicitModuleBase source;

        private double low;

        private double high;

        private double scale2D;

        private double offset2D;

        private double scale3D;

        private double offset3D;

        private double scale4D;

        private double offset4D;

        private double scale6D;

        private double offset6D;

        public ImplicitAutoCorrect(ImplicitModuleBase source, double low = -1.00, double high = 1.00)
        {
            this.source = source;
            this.low = low;
            this.high = high;
            this.Calculate();
        }

        public ImplicitModuleBase Source
        {
            get { return this.source; }
            set
            {
                this.source = value;
                this.Calculate();
            }
        }

        public double Low
        {
            get { return this.low; }
            set
            {
                this.low = value;
                this.Calculate();
            }
        }

        public double High
        {
            get { return this.high; }
            set
            {
                this.high = value;
                this.Calculate();
            }
        }

        private void Calculate()
        {
            var random = new Random();

            // Calculate 2D
            var mn = 10000.0;
            var mx = -10000.0;
            for (var c = 0; c < 10000; ++c)
            {
                var nx = random.NextDouble() * 4.0 - 2.0;
                var ny = random.NextDouble() * 4.0 - 2.0;

                var value = this.Source.Get(nx, ny);
                if (value < mn) mn = value;
                if (value > mx) mx = value;
            }
            this.scale2D = (this.high - this.low) / (mx - mn);
            this.offset2D = this.low - mn * this.scale2D;

            // Calculate 3D
            mn = 10000.0;
            mx = -10000.0;
            for (var c = 0; c < 10000; ++c)
            {
                var nx = random.NextDouble() * 4.0 - 2.0;
                var ny = random.NextDouble() * 4.0 - 2.0;
                var nz = random.NextDouble() * 4.0 - 2.0;

                var value = this.Source.Get(nx, ny, nz);
                if (value < mn) mn = value;
                if (value > mx) mx = value;
            }
            this.scale3D = (this.high - this.low) / (mx - mn);
            this.offset3D = this.low - mn * this.scale3D;

            // Calculate 4D
            mn = 10000.0;
            mx = -10000.0;
            for (var c = 0; c < 10000; ++c)
            {
                var nx = random.NextDouble() * 4.0 - 2.0;
                var ny = random.NextDouble() * 4.0 - 2.0;
                var nz = random.NextDouble() * 4.0 - 2.0;
                var nw = random.NextDouble() * 4.0 - 2.0;

                var value = this.Source.Get(nx, ny, nz, nw);
                if (value < mn) mn = value;
                if (value > mx) mx = value;
            }
            this.scale4D = (this.high - this.low) / (mx - mn);
            this.offset4D = this.low - mn * this.scale4D;

            // Calculate 6D
            mn = 10000.0;
            mx = -10000.0;
            for (var c = 0; c < 10000; ++c)
            {
                var nx = random.NextDouble() * 4.0 - 2.0;
                var ny = random.NextDouble() * 4.0 - 2.0;
                var nz = random.NextDouble() * 4.0 - 2.0;
                var nw = random.NextDouble() * 4.0 - 2.0;
                var nu = random.NextDouble() * 4.0 - 2.0;
                var nv = random.NextDouble() * 4.0 - 2.0;

                var value = this.Source.Get(nx, ny, nz, nw, nu, nv);
                if (value < mn) mn = value;
                if (value > mx) mx = value;
            }
            this.scale6D = (this.high - this.low) / (mx - mn);
            this.offset6D = this.low - mn * this.scale6D;
        }

        public void SetRange(double low, double high)
        {
            this.low = low;
            this.high = high;
            this.Calculate();
        }

        public override double Get(double x, double y)
        {
            return Math.Max(this.low, Math.Min(this.high, this.Source.Get(x, y) * this.scale2D + this.offset2D));
        }

        public override double Get(double x, double y, double z)
        {
            return Math.Max(this.low, Math.Min(this.high, this.Source.Get(x, y, z) * this.scale3D + this.offset3D));
        }

        public override double Get(double x, double y, double z, double w)
        {
            return Math.Max(this.low, Math.Min(this.high, this.Source.Get(x, y, z, w) * this.scale4D + this.offset4D));
        }

        public override double Get(double x, double y, double z, double w, double u, double v)
        {
            return Math.Max(this.low, Math.Min(this.high, this.Source.Get(x, y, z, w, u, v) * this.scale6D + this.offset6D));
        }
    }
}