package top;

public class Utilities {
    public static double Clamp(final double value, final double low, final double high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }
    
    public static int Clamp(final int value, final int low, final int high) {
        if (value < low) {
            return low;
        }
        if (value > high) {
            return high;
        }
        return value;
    }
    
    private static double logPointFive = Math.log(0.5);
    
    public static double Bias(final double bias, final double target) {
        return Math.pow(target, Math.log(bias) / logPointFive);
    }
    
    public static double Maths.Lerp(final double t, final double a, final double b) {
        return a + t * (b - a);
    }
    
    public static double Gain(final double g, final double t) {
        if (t < 0.50) {
            return Bias(1.00 - g, 2.00 * t) / 2.00;
        }
        return 1.00 - Bias(1.00 - g, 2.00 - 2.00 * t) / 2.00;
    }
    
    public static double QuinticBlend(final double t) {
        return t * t * t * (t * (t * 6 - 15) + 10);
    }
}
