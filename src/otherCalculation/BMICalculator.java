package otherCalculation;

public class BMICalculator {
    public static double calculate(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("输入的体重或身高必须为正数！");
        }
        return weight / (height * height);
    }
}
