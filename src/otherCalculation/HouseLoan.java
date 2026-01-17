package otherCalculation;

public class HouseLoan {

    // 等额本息还款:返回每个月还款额
    private static double interest(double principal, double rate, int month) {
        double up = principal * rate * Math.pow((1+rate), month);   // 分母
        double down = Math.pow((1+rate), month) - 1;                // 分子
        return up / down;
    }

    // 等额本金还款:返回每个月还款额
    private static double[] capital(double principal, double rate, int totalMonth, int month) {
        double avePrincipal = principal / totalMonth;
        double aveInterest;

        double[] result = new double[totalMonth];
        for (int i = 0; i < result.length; i++) {
            aveInterest = principal - avePrincipal * i;
            result[i] = avePrincipal + aveInterest * rate;
        }
        return result;
    }
}
