package calculators.factorial;

public class Calculators {

    // 阶乘计算
    public static int Factorial(int num) {
        int result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }

    // 排列数
    public static int Permutation(int m, int n) {
        int molecule = Factorial(n);                // 分子：n!
        int denominator = Factorial(n - m);     // 分母：（n-m）!
        return molecule / denominator;
    }

    // 组合数
    public static int Combination(int m, int n) {
        int molecule = Factorial(n);
        int denominator = Factorial(n - m) * Factorial(m);
        return molecule / denominator;
    }
}
