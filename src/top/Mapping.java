package top;
import java.lang.reflect.Array;

import Enums.MappingMode;
import Implicit.ImplicitModuleBase;

//TODO port Math class
public class Mapping {
    private static final double PI2 = Math.PI * 2.0;
    
    public static void Map2D(MappingMode mappingMode, double[][] array, ImplicitModuleBase module, MappingRanges ranges,
            double z) {
        
        //TODO correct?
        int width = array.length;
        int height = array[0].length;
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                double p = x / (double) width;
                double q = y / (double) height;
                double r;
                double nx;
                double ny;
                double nz;
                double nw;
                double nu;
                double dx;
                double dy;
                double dz;
                double val = 0.00;
                switch (mappingMode) {
                case SeamlessNone:
                    nx = ranges.MapX0 + p * (ranges.MapX1 - ranges.MapX0);
                    ny = ranges.MapY0 + q * (ranges.MapY1 - ranges.MapY0);
                    nz = z;
                    val = module.Get(nx, ny, nz);
                    break;
                case SeamlessX:
                    dx = ranges.LoopX1 - ranges.LoopX0;
                    dy = ranges.MapY1 - ranges.MapY0;
                    p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                    nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                    ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                    nz = ranges.MapY0 + q * dy;
                    nw = z;
                    val = module.Get(nx, ny, nz, nw);
                    break;
                case SeamlessY:
                    dx = ranges.MapX1 - ranges.MapX0;
                    dy = ranges.LoopY1 - ranges.LoopY0;
                    q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                    nx = ranges.MapX0 + p * dx;
                    ny = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                    nz = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                    nw = z;
                    val = module.Get(nx, ny, nz, nw);
                    break;
                case SeamlessZ:
                    dx = ranges.MapX1 - ranges.MapX0;
                    dy = ranges.MapY1 - ranges.MapY0;
                    dz = ranges.LoopZ1 - ranges.LoopZ0;
                    nx = ranges.MapX0 + p * dx;
                    ny = ranges.MapY0 + p * dy;
                    r = (z - ranges.MapZ0) / (ranges.MapZ1 - ranges.MapZ0);
                    double zval = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                    nz = ranges.LoopZ0 + Math.cos(zval * PI2) * dz / PI2;
                    nw = ranges.LoopZ0 + Math.sin(zval * PI2) * dz / PI2;
                    val = module.Get(nx, ny, nz, nw);
                    break;
                case SeamlessXY:
                    dx = ranges.LoopX1 - ranges.LoopX0;
                    dy = ranges.LoopY1 - ranges.LoopY0;
                    p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                    q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                    nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                    ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                    nz = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                    nw = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                    nu = z;
                    val = module.Get(nx, ny, nz, nw, nu, 0);
                    break;
                case SeamlessXZ:
                    dx = ranges.LoopX1 - ranges.LoopX0;
                    dy = ranges.MapY1 - ranges.MapY0;
                    dz = ranges.LoopZ1 - ranges.LoopZ0;
                    r = (z - ranges.MapZ0) / (ranges.MapZ1 - ranges.MapZ0);
                    double xzval = r * (ranges.MapX1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                    p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                    nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                    ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                    nz = ranges.MapY0 + q * dy;
                    nw = ranges.LoopZ0 + Math.cos(xzval * PI2) * dz / PI2;
                    nu = ranges.LoopZ0 + Math.sin(xzval * PI2) * dz / PI2;
                    val = module.Get(nx, ny, nz, nw, nu, 0);
                    break;
                case SeamlessYZ:
                    dx = ranges.MapX1 - ranges.MapX0;
                    dy = ranges.LoopY1 - ranges.LoopY0;
                    dz = ranges.LoopZ1 - ranges.LoopZ0;
                    r = (z - ranges.MapZ0) / (ranges.MapZ1 - ranges.MapZ0);
                    double yzval = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                    q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                    nx = ranges.MapX0 + p * dx;
                    ny = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                    nz = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                    nw = ranges.LoopZ0 + Math.cos(yzval * PI2) * dz / PI2;
                    nu = ranges.LoopZ0 + Math.sin(yzval * PI2) * dz / PI2;
                    val = module.Get(nx, ny, nz, nw, nu, 0);
                    break;
                case SeamlessXYZ:
                    dx = ranges.LoopX1 - ranges.LoopX0;
                    dy = ranges.LoopY1 - ranges.LoopY0;
                    dz = ranges.LoopZ1 - ranges.LoopZ0;
                    p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                    q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                    r = (z - ranges.MapZ0) / (ranges.MapZ1 - ranges.MapZ0);
                    double xyzval = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                    nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                    ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                    nz = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                    nw = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                    nu = ranges.LoopZ0 + Math.cos(xyzval * PI2) * dz / PI2;
                    double nv = ranges.LoopZ0 + Math.sin(xyzval * PI2) * dz / PI2;
                    val = module.Get(nx, ny, nz, nw, nu, nv);
                    break;
                }
                array[x][y] = val;
            }
        }
    }
    
    public static void Map2DNoZ(MappingMode mappingMode, double[][] array, ImplicitModuleBase module,
            MappingRanges ranges) {
        //TODO correct?
        int width = array.length;
        int height = array[0].length;
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                double p = x / (double) width;
                double q = y / (double) height;
                double nx;
                double ny;
                double nz;
                double dx;
                double dy;
                double val = 0.00;
                switch (mappingMode) {
                case SeamlessNone:
                    nx = ranges.MapX0 + p * (ranges.MapX1 - ranges.MapX0);
                    ny = ranges.MapY0 + q * (ranges.MapY1 - ranges.MapY0);
                    val = module.Get(nx, ny);
                    break;
                case SeamlessX:
                    dx = ranges.LoopX1 - ranges.LoopX0;
                    dy = ranges.MapY1 - ranges.MapY0;
                    p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                    nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                    ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                    nz = ranges.MapY0 + q * dy;
                    val = module.Get(nx, ny, nz);
                    break;
                case SeamlessY:
                    dx = ranges.MapX1 - ranges.MapX0;
                    dy = ranges.LoopY1 - ranges.LoopY0;
                    q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                    nx = ranges.MapX0 + p * dx;
                    ny = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                    nz = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                    val = module.Get(nx, ny, nz);
                    break;
                
                case SeamlessXY:
                    dx = ranges.LoopX1 - ranges.LoopX0;
                    dy = ranges.LoopY1 - ranges.LoopY0;
                    p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                    q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                    nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                    ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                    nz = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                    double nw = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                    val = module.Get(nx, ny, nz, nw);
                    break;
                default:
                    throw new IllegalArgumentException("" + mappingMode);
                    
                }
                array[x][y] = val;
            }
        }
    }
    
    public static void Map3D(MappingMode mappingMode, double[][][] array, ImplicitModuleBase module,
            MappingRanges ranges) {//TODO correct
        int width = array.length;
        int height = array[0].length;
        int depth = array[0][0].length;
        
        for (int x = 0; x < width; ++x) {
            for (int y = 0; y < height; ++y) {
                for (int z = 0; z < depth; ++z) {
                    double p = x / (double) width;
                    double q = y / (double) height;
                    double r = z / (double) depth;
                    double nx;
                    double ny;
                    double nz;
                    double nw;
                    double nu;
                    double dx;
                    double dy;
                    double dz;
                    double val = 0.0;
                    
                    switch (mappingMode) {
                    case SeamlessNone:
                        dx = ranges.MapX1 - ranges.MapX0;
                        dy = ranges.MapY1 - ranges.MapY0;
                        dz = ranges.MapZ1 - ranges.MapZ0;
                        nx = ranges.MapX0 + p * dx;
                        ny = ranges.MapY0 + q * dy;
                        nz = ranges.MapZ0 + r * dz;
                        val = module.Get(nx, ny, nz);
                        break;
                    case SeamlessX:
                        dx = ranges.LoopX1 - ranges.LoopX0;
                        dy = ranges.MapY1 - ranges.MapY0;
                        dz = ranges.MapZ1 - ranges.MapZ0;
                        p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                        nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                        ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                        nz = ranges.MapY0 + q * dy;
                        nw = ranges.MapZ0 + depth * dz;
                        val = module.Get(nx, ny, nz, nw);
                        break;
                    case SeamlessY:
                        dx = ranges.MapX1 - ranges.MapX0;
                        dy = ranges.LoopY1 - ranges.LoopY0;
                        dz = ranges.MapZ1 - ranges.MapZ0;
                        q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                        nx = ranges.MapX0 + p * dx;
                        ny = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                        nz = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                        nw = ranges.MapZ0 + r * dz;
                        val = module.Get(nx, ny, nz, nw);
                        break;
                    case SeamlessZ:
                        dx = ranges.MapX1 - ranges.MapX0;
                        dy = ranges.MapY1 - ranges.MapY0;
                        dz = ranges.LoopZ1 - ranges.LoopZ0;
                        r = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                        nx = ranges.MapX0 + p * dx;
                        ny = ranges.MapY0 + q * dy;
                        nz = ranges.LoopZ0 + Math.cos(r * PI2) * dz / PI2;
                        nw = ranges.LoopZ0 + Math.sin(r * PI2) * dz / PI2;
                        val = module.Get(nx, ny, nz, nw);
                        break;
                    case SeamlessXY:
                        dx = ranges.LoopX1 - ranges.LoopX0;
                        dy = ranges.LoopY1 - ranges.LoopY0;
                        dz = ranges.MapZ1 - ranges.MapZ0;
                        p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                        q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                        nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                        ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                        nz = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                        nw = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                        nu = ranges.MapZ0 + r * dz;
                        val = module.Get(nx, ny, nz, nw, nu, 0);
                        break;
                    case SeamlessXZ:
                        dx = ranges.LoopX1 - ranges.LoopX0;
                        dy = ranges.MapY1 - ranges.MapY0;
                        dz = ranges.LoopZ1 - ranges.LoopZ0;
                        p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                        r = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                        nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                        ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                        nz = ranges.MapY0 + q * dy;
                        nw = ranges.LoopZ0 + Math.cos(r * PI2) * dz / PI2;
                        nu = ranges.LoopZ0 + Math.sin(r * PI2) * dz / PI2;
                        val = module.Get(nx, ny, nz, nw, nu, 0);
                        break;
                    case SeamlessYZ:
                        dx = ranges.MapX1 - ranges.MapX0;
                        dy = ranges.LoopY1 - ranges.LoopY0;
                        dz = ranges.LoopZ1 - ranges.LoopZ0;
                        q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                        r = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                        nx = ranges.MapX0 + p * dx;
                        ny = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                        nz = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                        nw = ranges.LoopZ0 + Math.cos(r * PI2) * dz / PI2;
                        nu = ranges.LoopZ0 + Math.sin(r * PI2) * dz / PI2;
                        val = module.Get(nx, ny, nz, nw, nu, 0);
                        break;
                    case SeamlessXYZ:
                        dx = ranges.LoopX1 - ranges.LoopX0;
                        dy = ranges.LoopY1 - ranges.LoopY0;
                        dz = ranges.LoopZ1 - ranges.LoopZ0;
                        p = p * (ranges.MapX1 - ranges.MapX0) / (ranges.LoopX1 - ranges.LoopX0);
                        q = q * (ranges.MapY1 - ranges.MapY0) / (ranges.LoopY1 - ranges.LoopY0);
                        r = r * (ranges.MapZ1 - ranges.MapZ0) / (ranges.LoopZ1 - ranges.LoopZ0);
                        nx = ranges.LoopX0 + Math.cos(p * PI2) * dx / PI2;
                        ny = ranges.LoopX0 + Math.sin(p * PI2) * dx / PI2;
                        nz = ranges.LoopY0 + Math.cos(q * PI2) * dy / PI2;
                        nw = ranges.LoopY0 + Math.sin(q * PI2) * dy / PI2;
                        nu = ranges.LoopZ0 + Math.cos(r * PI2) * dz / PI2;
                        double nv = ranges.LoopZ0 + Math.sin(r * PI2) * dz / PI2;
                        val = module.Get(nx, ny, nz, nw, nu, nv);
                        break;
                    }
                    array[x][y][z] = val;
                }
            }
        }
    }
    
}
