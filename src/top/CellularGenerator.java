package top;

public class CellularGenerator {
    private int seed;

    private final CellularCache cache2D = new CellularCache();

    private final CellularCache cache3D = new CellularCache();

    private final CellularCache cache4D = new CellularCache();

    private final CellularCache cache6D = new CellularCache();

    public CellularGenerator() {
        this.seed = 10000;
    }

    public int getSeed() {
        return this.seed;
    }

    public void setSeed(final int value) {
        if (value == this.seed) {
            return;
        }
        this.seed = value;
        this.cache2D.IsValid = false;
        this.cache3D.IsValid = false;
        this.cache4D.IsValid = false;
        this.cache6D.IsValid = false;
    }

    private CellularCache Get(final double x, final double y) {
        if (!this.cache2D.IsValid || x != this.cache2D.X || y != this.cache2D.Y) {
            Noise.CellularFunction(x, y, this.seed, this.cache2D.F, this.cache2D.D);
            this.cache2D.X = x;
            this.cache2D.Y = y;
            this.cache2D.IsValid = true;
        }
        return this.cache2D;
    }

    private CellularCache Get(final double x, final double y, final double z) {
        if (!this.cache3D.IsValid || x != this.cache3D.X || y != this.cache3D.Y || z != this.cache3D.Z) {
            Noise.CellularFunction(x, y, z, this.seed, this.cache3D.F, this.cache3D.D);
            this.cache3D.X = x;
            this.cache3D.Y = y;
            this.cache3D.Z = z;
            this.cache3D.IsValid = true;
        }
        return this.cache3D;
    }

    private CellularCache Get(final double x, final double y, final double z, final double w) {
        if (!this.cache4D.IsValid || x != this.cache4D.X || y != this.cache4D.Y || z != this.cache4D.Z
                || w != this.cache4D.W) {
            Noise.CellularFunction(x, y, z, w, this.seed, this.cache4D.F, this.cache4D.D);
            this.cache4D.X = x;
            this.cache4D.Y = y;
            this.cache4D.Z = z;
            this.cache4D.W = w;
            this.cache4D.IsValid = true;
        }
        return this.cache4D;
    }

    private CellularCache Get(final double x, final double y, final double z, final double w, final double u,
            final double v) {
        if (!this.cache6D.IsValid || x != this.cache6D.X || y != this.cache6D.Y || z != this.cache6D.Z
                || w != this.cache6D.W || u != this.cache6D.U || v != this.cache6D.V) {
            Noise.CellularFunction(x, y, z, w, u, v, this.seed, this.cache6D.F, this.cache6D.D);
            this.cache6D.X = x;
            this.cache6D.Y = y;
            this.cache6D.Z = z;
            this.cache6D.W = w;
            this.cache6D.U = u;
            this.cache6D.V = v;
            this.cache6D.IsValid = true;
        }

        return this.cache6D;
    }
}
