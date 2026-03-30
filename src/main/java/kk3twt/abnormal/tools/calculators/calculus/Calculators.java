package kk3twt.abnormal.tools.calculators.calculus;

public class Calculators {

    private final Functions functions;
    public Calculators(Functions functions) {
        this.functions = functions;
    }

    /**
     * 定积分计算方法
     * 使用辛普森法进行积分
     * 需要自行输入精度和递归深度
     *
     * @param a 积分上限
     * @param b 积分下限
     * @param i 函数类型
     * @param tolerance 计算精度
     * @param maxDepth 最大递归深度
     * @return 积分结果
     */
    public double integral(double a, double b, int i, double tolerance, int maxDepth) {
        double mid = (a + b) / 2;
        double s = simpson(a, b, i);
        double s_left = simpson(a, mid, i);
        double s_right = simpson(mid, b, i);

        if (maxDepth <= 0 || Math.abs(s_left + s_right - s) < 15 * tolerance) {
            return s_left + s_right + (s_left + s_right - s) / 15;
        }

        return integral(a, mid, i, tolerance / 2, maxDepth - 1) + integral(mid, b, i, tolerance / 2, maxDepth - 1);
    }

    /**
     * 辅助方法：使用辛普森公式计算区间的积分
     *
     * @param a 区间下界
     * @param b 区间上界
     * @param i 函数类型
     * @return 积分结果
     */
    private double simpson(double a, double b, int i) {
        return (b - a) / 6 * (functions.getF(a, i) + 4 * functions.getF((a + b) / 2, i) + functions.getF(b, i));
    }

    /**
     * 函数求导方法
     *
     * @param x 求导点
     * @param tolerance 求导精度
     * @param i 函数类型
     * @return 求导结果
     */
    public double differentiation(double x, double tolerance, int i) {
        return (functions.getF((x + tolerance), i) - functions.getF((x - tolerance), i)) / (2 * tolerance);
    }
}
