package noise;

import java.util.Arrays;
import java.util.Comparator;

import enums.InterpolationType;
import util.Maths;

public class SimplexNoise implements Noise {
    
    private static final double F2 = 0.36602540378443864676372317075294;
    private static final double G2 = 0.21132486540518711774542560974902;
    private static final double F3 = 1.0 / 3.0;
    private static final double G3 = 1.0 / 6.0;
    
    private static final int[][] Simplex = { { 0, 1, 2, 3 }, { 0, 1, 3, 2 }, { 0, 0, 0, 0 }, { 0, 2, 3, 1 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 2, 3, 0 }, { 0, 2, 1, 3 }, { 0, 0, 0, 0 },
            { 0, 3, 1, 2 }, { 0, 3, 2, 1 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 3, 2, 0 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 1, 2, 0, 3 }, { 0, 0, 0, 0 }, { 1, 3, 0, 2 }, { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 3, 0, 1 }, { 2, 3, 1, 0 }, { 1, 0, 2, 3 }, { 1, 0, 3, 2 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 3, 1 }, { 0, 0, 0, 0 }, { 2, 1, 3, 0 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 2, 0, 1, 3 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 },
            { 3, 0, 1, 2 }, { 3, 0, 2, 1 }, { 0, 0, 0, 0 }, { 3, 1, 2, 0 }, { 2, 1, 0, 3 }, { 0, 0, 0, 0 },
            { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 3, 1, 0, 2 }, { 0, 0, 0, 0 }, { 3, 2, 0, 1 }, { 3, 2, 1, 0 } };
    
    private class VectorOrdering {
        private Double Value;
        
        private int Axis;
        
        private VectorOrdering(final double v, final int a) {
            this.Value = v;
            this.Axis = a;
        }
        
    }
    
    private static Comparator<VectorOrdering> VectorOrderingCompare = (v1, v2) -> v1.Value.compareTo(v2.Value);
    
    private static void SortBy4(final double[] l1, final int[] l2) {
        final VectorOrdering[] a = new VectorOrdering[4];
        for (int c = 0; c < 4; c += 1) {
            a[c].Value = l1[c];
            a[c].Axis = l2[c];
        }
        
        Arrays.sort(a, VectorOrderingCompare);
        
        for (int c = 0; c < 4; c += 1) {
            l2[c] = a[c].Axis;
        }
    }
    
    private static void SortBy6(final double[] l1, final int[] l2) {
        final VectorOrdering[] a = new VectorOrdering[6];
        for (int c = 0; c < 6; c += 1) {
            a[c].Value = l1[c];
            a[c].Axis = l2[c];
        }
        
        Arrays.sort(a, VectorOrderingCompare);
        
        for (int c = 0; c < 6; c += 1) {
            l2[c] = a[c].Axis;
        }
    }
    
    @Override
    public double noise(final double x, final double y, final int seed, final InterpolationType interpolator) {
        final double s = (x + y) * F2;
        final double i = Maths.floor(x + s);
        final double j = Maths.floor(y + s);
        
        final double t = (i + j) * G2;
        final double X0 = i - t;
        final double Y0 = j - t;
        final double x0 = x - X0;
        final double y0 = y - Y0;
        
        int i1, j1;
        if (x0 > y0) {
            i1 = 1;
            j1 = 0;
        } else {
            i1 = 0;
            j1 = 1;
        }
        
        final double x1 = x0 - i1 + G2;
        final double y1 = y0 - j1 + G2;
        final double x2 = x0 - 1.0 + 2.0 * G2;
        final double y2 = y0 - 1.0 + 2.0 * G2;
        
        // Hash the triangle coordinates to index the gradient table
        final int h0 = Maths.HashCoordinates(i, j, seed);
        final int h1 = Maths.HashCoordinates(i + i1, j + j1, seed);
        final int h2 = Maths.HashCoordinates(i + 1, j + 1, seed);
        
        // Now, index the tables
        final double[] g0 = { NoiseLookupTable.Gradient2D[h0][0], NoiseLookupTable.Gradient2D[h0][1] };
        final double[] g1 = { NoiseLookupTable.Gradient2D[h1][0], NoiseLookupTable.Gradient2D[h1][1] };
        final double[] g2 = { NoiseLookupTable.Gradient2D[h2][0], NoiseLookupTable.Gradient2D[h2][1] };
        
        double n0, n1, n2;
        // Calculate the contributions from the 3 corners
        double t0 = 0.5 - x0 * x0 - y0 * y0;
        if (t0 < 0) {
            n0 = 0;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * Maths.ArrayDot(g0, x0, y0);
        }
        
        double t1 = 0.5 - x1 * x1 - y1 * y1;
        if (t1 < 0) {
            n1 = 0;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * Maths.ArrayDot(g1, x1, y1);
        }
        
        double t2 = 0.5 - x2 * x2 - y2 * y2;
        if (t2 < 0) {
            n2 = 0;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * Maths.ArrayDot(g2, x2, y2);
        }
        
        // Add contributions together
        return (70.0 * (n0 + n1 + n2)) * 1.42188695 + 0.001054489;
    }
    
    @Override
    public double noise(final double x, final double y, final double z, final int seed,
            final InterpolationType interpolator) {
        double n0, n1, n2, n3;
        
        final double s = (x + y + z) * F3;
        final double i = Maths.floor(x + s);
        final double j = Maths.floor(y + s);
        final double k = Maths.floor(z + s);
        
        final double t = (i + j + k) * G3;
        final double X0 = i - t;
        final double Y0 = j - t;
        final double Z0 = k - t;
        
        final double x0 = x - X0;
        final double y0 = y - Y0;
        final double z0 = z - Z0;
        
        int i1, j1, k1;
        int i2, j2, k2;
        
        if (x0 >= y0) {
            if (y0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 1;
                k2 = 0;
            } else if (x0 >= z0) {
                i1 = 1;
                j1 = 0;
                k1 = 0;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            } else {
                i1 = 0;
                j1 = 0;
                k1 = 1;
                i2 = 1;
                j2 = 0;
                k2 = 1;
            }
        } else {
            if (y0 < z0) {
                i1 = 0;
                j1 = 0;
                k1 = 1;
                i2 = 0;
                j2 = 1;
                k2 = 1;
            } else if (x0 < z0) {
                i1 = 0;
                j1 = 1;
                k1 = 0;
                i2 = 0;
                j2 = 1;
                k2 = 1;
            } else {
                i1 = 0;
                j1 = 1;
                k1 = 0;
                i2 = 1;
                j2 = 1;
                k2 = 0;
            }
        }
        
        final double x1 = x0 - i1 + G3;
        final double y1 = y0 - j1 + G3;
        final double z1 = z0 - k1 + G3;
        final double x2 = x0 - i2 + 2.0 * G3;
        final double y2 = y0 - j2 + 2.0 * G3;
        final double z2 = z0 - k2 + 2.0 * G3;
        final double x3 = x0 - 1.0 + 3.0 * G3;
        final double y3 = y0 - 1.0 + 3.0 * G3;
        final double z3 = z0 - 1.0 + 3.0 * G3;
        
        final int h0 = Maths.HashCoordinates(i, j, k, seed);
        final int h1 = Maths.HashCoordinates(i + i1, j + j1, k + k1, seed);
        final int h2 = Maths.HashCoordinates(i + i2, j + j2, k + k2, seed);
        final int h3 = Maths.HashCoordinates(i + 1, j + 1, k + 1, seed);
        
        final double[] g0 = { NoiseLookupTable.Gradient3D[h0][0], NoiseLookupTable.Gradient3D[h0][1],
                NoiseLookupTable.Gradient3D[h0][2] };
        final double[] g1 = { NoiseLookupTable.Gradient3D[h1][0], NoiseLookupTable.Gradient3D[h1][1],
                NoiseLookupTable.Gradient3D[h1][2] };
        final double[] g2 = { NoiseLookupTable.Gradient3D[h2][0], NoiseLookupTable.Gradient3D[h2][1],
                NoiseLookupTable.Gradient3D[h2][2] };
        final double[] g3 = { NoiseLookupTable.Gradient3D[h3][0], NoiseLookupTable.Gradient3D[h3][1],
                NoiseLookupTable.Gradient3D[h3][2] };
        
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0;
        if (t0 < 0.0) {
            n0 = 0.0;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * Maths.ArrayDot(g0, x0, y0, z0);
        }
        
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1;
        if (t1 < 0.0) {
            n1 = 0.0;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * Maths.ArrayDot(g1, x1, y1, z1);
        }
        
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2;
        if (t2 < 0) {
            n2 = 0.0;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * Maths.ArrayDot(g2, x2, y2, z2);
        }
        
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3;
        if (t3 < 0) {
            n3 = 0.0;
        } else {
            t3 *= t3;
            n3 = t3 * t3 * Maths.ArrayDot(g3, x3, y3, z3);
        }
        
        return (32.0 * (n0 + n1 + n2 + n3)) * 1.25086885 + 0.0003194984;
    }
    
    @Override
    public double noise(final double x, final double y, final double z, final double w, final int seed,
            final InterpolationType interpolator) {
        final double F4 = (Math.sqrt(5.0) - 1.0) / 4.0;
        final double G4 = (5.0 - Math.sqrt(5.0)) / 20.0;
        double n0, n1, n2, n3, n4; // Noise contributions from the five corners
        // Skew the (x,y,z,w) space to determine which cell of 24 simplices we're in
        final double s = (x + y + z + w) * F4; // Factor for 4D skewing
        final double i = Maths.floor(x + s);
        final double j = Maths.floor(y + s);
        final double k = Maths.floor(z + s);
        final double l = Maths.floor(w + s);
        final double t = (i + j + k + l) * G4; // Factor for 4D unskewing
        final double X0 = i - t; // Unskew the cell origin back to (x,y,z,w) space
        final double Y0 = j - t;
        final double Z0 = k - t;
        final double W0 = l - t;
        final double x0 = x - X0; // The x,y,z,w distances from the cell origin
        final double y0 = y - Y0;
        final double z0 = z - Z0;
        final double w0 = w - W0;
        // For the 4D case, the simplex is a 4D shape I won't even try to describe.
        // To find out which of the 24 possible simplices we're in, we need to
        // determine the magnitude ordering of x0, y0, z0 and w0.
        // The method below is a good way of finding the ordering of x,y,z,w and
        // then find the correct traversal order for the simplex we�re in.
        // First, six pair-wise comparisons are performed between each possible pair
        // of the four coordinates, and the results are used to add up binary bits
        // for an integer index.
        final int c1 = (x0 > y0) ? 32 : 0;
        final int c2 = (x0 > z0) ? 16 : 0;
        final int c3 = (y0 > z0) ? 8 : 0;
        final int c4 = (x0 > w0) ? 4 : 0;
        final int c5 = (y0 > w0) ? 2 : 0;
        final int c6 = (z0 > w0) ? 1 : 0;
        final int c = c1 + c2 + c3 + c4 + c5 + c6;
        int i1, j1, k1, l1; // The integer offsets for the second simplex corner
        int i2, j2, k2, l2; // The integer offsets for the third simplex corner
        int i3, j3, k3, l3; // The integer offsets for the fourth simplex corner
        // simplex[c] is a 4-vector with the numbers 0, 1, 2 and 3 in some order.
        // Many values of c will never occur, since e.g. x>y>z>w makes x<z, y<w and x<w
        // impossible. Only the 24 indices which have non-zero entries make any sense.
        // We use a thresholding to set the coordinates in turn from the largest magnitude.
        // The number 3 in the "simplex" array is at the position of the largest coordinate.
        i1 = Simplex[c][0] >= 3 ? 1 : 0;
        j1 = Simplex[c][1] >= 3 ? 1 : 0;
        k1 = Simplex[c][2] >= 3 ? 1 : 0;
        l1 = Simplex[c][3] >= 3 ? 1 : 0;
        // The number 2 in the "simplex" array is at the second largest coordinate.
        i2 = Simplex[c][0] >= 2 ? 1 : 0;
        j2 = Simplex[c][1] >= 2 ? 1 : 0;
        k2 = Simplex[c][2] >= 2 ? 1 : 0;
        l2 = Simplex[c][3] >= 2 ? 1 : 0;
        // The number 1 in the "simplex" array is at the second smallest coordinate.
        i3 = Simplex[c][0] >= 1 ? 1 : 0;
        j3 = Simplex[c][1] >= 1 ? 1 : 0;
        k3 = Simplex[c][2] >= 1 ? 1 : 0;
        l3 = Simplex[c][3] >= 1 ? 1 : 0;
        // The fifth corner has all coordinate offsets = 1, so no need to look that up.
        final double x1 = x0 - i1 + G4; // Offsets for second corner in (x,y,z,w) coords
        final double y1 = y0 - j1 + G4;
        final double z1 = z0 - k1 + G4;
        final double w1 = w0 - l1 + G4;
        final double x2 = x0 - i2 + 2.0 * G4; // Offsets for third corner in (x,y,z,w) coords
        final double y2 = y0 - j2 + 2.0 * G4;
        final double z2 = z0 - k2 + 2.0 * G4;
        final double w2 = w0 - l2 + 2.0 * G4;
        final double x3 = x0 - i3 + 3.0 * G4; // Offsets for fourth corner in (x,y,z,w) coords
        final double y3 = y0 - j3 + 3.0 * G4;
        final double z3 = z0 - k3 + 3.0 * G4;
        final double w3 = w0 - l3 + 3.0 * G4;
        final double x4 = x0 - 1.0 + 4.0 * G4; // Offsets for last corner in (x,y,z,w) coords
        final double y4 = y0 - 1.0 + 4.0 * G4;
        final double z4 = z0 - 1.0 + 4.0 * G4;
        final double w4 = w0 - 1.0 + 4.0 * G4;
        // Work out the hashed gradient indices of the five simplex corners
        final int h0 = Maths.HashCoordinates(i, j, k, l, seed);
        final int h1 = Maths.HashCoordinates(i + i1, j + j1, k + k1, l + l1, seed);
        final int h2 = Maths.HashCoordinates(i + i2, j + j2, k + k2, l + l2, seed);
        final int h3 = Maths.HashCoordinates(i + i3, j + j3, k + k3, l + l3, seed);
        final int h4 = Maths.HashCoordinates(i + 1, j + 1, k + 1, l + 1, seed);
        
        final double[] g0 = { NoiseLookupTable.Gradient4D[h0][0], NoiseLookupTable.Gradient4D[h0][1],
                NoiseLookupTable.Gradient4D[h0][2], NoiseLookupTable.Gradient4D[h0][3] };
        final double[] g1 = { NoiseLookupTable.Gradient4D[h1][0], NoiseLookupTable.Gradient4D[h1][1],
                NoiseLookupTable.Gradient4D[h1][2], NoiseLookupTable.Gradient4D[h1][3] };
        final double[] g2 = { NoiseLookupTable.Gradient4D[h2][0], NoiseLookupTable.Gradient4D[h2][1],
                NoiseLookupTable.Gradient4D[h2][2], NoiseLookupTable.Gradient4D[h2][3] };
        final double[] g3 = { NoiseLookupTable.Gradient4D[h3][0], NoiseLookupTable.Gradient4D[h3][1],
                NoiseLookupTable.Gradient4D[h3][2], NoiseLookupTable.Gradient4D[h3][3] };
        final double[] g4 = { NoiseLookupTable.Gradient4D[h4][0], NoiseLookupTable.Gradient4D[h4][1],
                NoiseLookupTable.Gradient4D[h4][2], NoiseLookupTable.Gradient4D[h4][3] };
        
        // Calculate the contribution from the five corners
        double t0 = 0.6 - x0 * x0 - y0 * y0 - z0 * z0 - w0 * w0;
        if (t0 < 0) {
            n0 = 0.0;
        } else {
            t0 *= t0;
            n0 = t0 * t0 * Maths.ArrayDot(g0, x0, y0, z0, w0);
        }
        double t1 = 0.6 - x1 * x1 - y1 * y1 - z1 * z1 - w1 * w1;
        if (t1 < 0) {
            n1 = 0.0;
        } else {
            t1 *= t1;
            n1 = t1 * t1 * Maths.ArrayDot(g1, x1, y1, z1, w1);
        }
        double t2 = 0.6 - x2 * x2 - y2 * y2 - z2 * z2 - w2 * w2;
        if (t2 < 0) {
            n2 = 0.0;
        } else {
            t2 *= t2;
            n2 = t2 * t2 * Maths.ArrayDot(g2, x2, y2, z2, w2);
        }
        double t3 = 0.6 - x3 * x3 - y3 * y3 - z3 * z3 - w3 * w3;
        if (t3 < 0) {
            n3 = 0.0;
        } else {
            t3 *= t3;
            n3 = t3 * t3 * Maths.ArrayDot(g3, x3, y3, z3, w3);
        }
        double t4 = 0.6 - x4 * x4 - y4 * y4 - z4 * z4 - w4 * w4;
        if (t4 < 0) {
            n4 = 0.0;
        } else {
            t4 *= t4;
            n4 = t4 * t4 * Maths.ArrayDot(g4, x4, y4, z4, w4);
        }
        // Sum up and scale the result to cover the range [-1,1]
        return 27.0 * (n0 + n1 + n2 + n3 + n4);
    }
    
    @Deprecated //TODO what is this? is this finished?
    public double NewSimplexNoise4D(final double x, final double y, final double z, final double w, final int seed,
            final InterpolationType interp) {
        //TODO make this a constant because does not change
        final double f4 = (Math.sqrt(5.0) - 1.0) / 4.0;
        final double sideLength = 2.0 / (4.0 * f4 + 1.0);
        final double a = Math.sqrt((sideLength * sideLength) - ((sideLength / 2.0) * (sideLength / 2.0)));
        final double cornerToFace = Math.sqrt((a * a + (a / 2.0) * (a / 2.0)));
        final double cornerToFaceSquared = cornerToFace * cornerToFace;
        
        double valueScaler = Math.pow(3.0, -0.5);
        
        final double g4 = f4 / (1.0 + 4.0 * f4);
        valueScaler *= Math.pow(3.0, -3.5) * 100.0 + 13.0;
        
        final double[] loc = { x, y, z, w };
        double s = 0;
        for (int c = 0; c < 4; ++c) {
            s += loc[c];
        }
        s *= f4;
        
        final double[] skewLoc = new double[] { Maths.floor(x + s), Maths.floor(y + s), Maths.floor(z + s),
                Maths.floor(w + s) };
        final double[] intLoc = new double[] { Maths.floor(x + s), Maths.floor(y + s), Maths.floor(z + s),
                Maths.floor(w + s) };
        double unskew = 0.00;
        for (int c = 0; c < 4; ++c) {
            unskew += skewLoc[c];
        }
        unskew *= g4;
        final double[] cellDist = new double[] { loc[0] - skewLoc[0] + unskew, loc[1] - skewLoc[1] + unskew,
                loc[2] - skewLoc[2] + unskew, loc[3] - skewLoc[3] + unskew };
        final int[] distOrder = new int[] { 0, 1, 2, 3 };
        SortBy4(cellDist, distOrder);
        
        final int[] newDistOrder = new int[] { -1, distOrder[0], distOrder[1], distOrder[2], distOrder[3] };
        
        double n = 0.00;
        double skewOffset = 0.00;
        
        for (int c = 0; c < 5; ++c) {
            final int i = newDistOrder[c];
            if (i != -1) {
                intLoc[i] += 1;
            }
            
            final double[] u = new double[4];
            for (int d = 0; d < 4; ++d) {
                u[d] = cellDist[d] - (intLoc[d] - skewLoc[d]) + skewOffset;
            }
            
            double t = cornerToFaceSquared;
            
            for (int d = 0; d < 4; ++d) {
                t -= u[d] * u[d];
            }
            
            if (t > 0.0) {
                final int h = Maths.HashCoordinates(intLoc[0], intLoc[1], intLoc[2], intLoc[3], seed);
                double gr = 0.00;
                for (int d = 0; d < 4; ++d) {
                    gr += NoiseLookupTable.Gradient4D[h][d] * u[d];
                }
                
                n += gr * t * t * t * t;
            }
            skewOffset += g4;
        }
        n *= valueScaler;
        return n;
    }
    
    @Override
    public double noise(final double x, final double y, final double z, final double w, final double u, final double v,
            final int seed, final InterpolationType interpolator) {
        //TODO constant!
        // Skew
        final double f4 = (Math.sqrt(7.0) - 1.0) / 6.0;
        
        // Unskew
        final double g4 = f4 / (1.0 + 6.0 * f4);
        
        final double sideLength = Math.sqrt(6.0) / (6.0 * f4 + 1.0);
        final double a = Math.sqrt((sideLength * sideLength) - ((sideLength / 2.0) * (sideLength / 2.0)));
        final double cornerFace = Math.sqrt(a * a + (a / 2.0) * (a / 2.0));
        
        final double cornerFaceSqrd = cornerFace * cornerFace;
        
        double valueScaler = Math.pow(5.0, -0.5);
        valueScaler *= Math.pow(5.0, -3.5) * 100 + 13;
        
        final double[] loc = new double[] { x, y, z, w, u, v };
        double s = 0.00;
        for (int c = 0; c < 6; ++c) {
            s += loc[c];
        }
        s *= f4;
        
        final double[] skewLoc = new double[] { Maths.floor(x + s), Maths.floor(y + s), Maths.floor(z + s),
                Maths.floor(w + s), Maths.floor(u + s), Maths.floor(v + s) };
        final double[] intLoc = new double[] { Maths.floor(x + s), Maths.floor(y + s), Maths.floor(z + s),
                Maths.floor(w + s), Maths.floor(u + s), Maths.floor(v + s) };
        double unskew = 0.0;
        for (int c = 0; c < 6; ++c) {
            unskew += skewLoc[c];
        }
        unskew *= g4;
        
        final double[] cellDist = new double[] { loc[0] - skewLoc[0] + unskew, loc[1] - skewLoc[1] + unskew,
                loc[2] - skewLoc[2] + unskew, loc[3] - skewLoc[3] + unskew, loc[4] - skewLoc[4] + unskew,
                loc[5] - skewLoc[5] + unskew };
        final int[] distOrder = new int[] { 0, 1, 2, 3, 4, 5 };
        SortBy6(cellDist, distOrder);
        
        final int[] newDistOrder = new int[] { -1, distOrder[0], distOrder[1], distOrder[2], distOrder[3], distOrder[4],
                distOrder[5] };
        
        double n = 0.00;
        double skewOffset = 0.00;
        
        for (int c = 0; c < 7; ++c) {
            final int i = newDistOrder[c];
            if (i != -1) {
                intLoc[i] += 1;
            }
            
            final double[] uu = new double[6];
            for (int d = 0; d < 6; ++d) {
                uu[d] = cellDist[d] - (intLoc[d] - skewLoc[d]) + skewOffset;
            }
            
            double t = cornerFaceSqrd;
            
            for (int d = 0; d < 6; ++d) {
                t -= uu[d] * uu[d];
            }
            
            if (t > 0.0) {
                final int h = Maths.HashCoordinates(intLoc[0], intLoc[1], intLoc[2], intLoc[3], intLoc[4], intLoc[5],
                        seed);
                double gr = 0.00;
                
                gr += NoiseLookupTable.Gradient6D[h][0] * uu[0];
                gr += NoiseLookupTable.Gradient6D[h][1] * uu[1];
                gr += NoiseLookupTable.Gradient6D[h][2] * uu[2];
                gr += NoiseLookupTable.Gradient6D[h][3] * uu[3];
                gr += NoiseLookupTable.Gradient6D[h][4] * uu[4];
                gr += NoiseLookupTable.Gradient6D[h][5] * uu[5];
                
                n += gr * t * t * t * t;
            }
            skewOffset += g4;
        }
        n *= valueScaler;
        return n;
    }
    
}
