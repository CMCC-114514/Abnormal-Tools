package otherCalculation;

public class BMI {
    public static double calculate(double weight, double height) {
        if (weight <= 0 || height <= 0) {
            throw new IllegalArgumentException("输入的体重或身高必须为正数！");
        }
        return weight / (height * height);
    }

    public static String returnType(double bmi) {
        if (bmi < 18.5)
            return "偏瘦，建议增加营养摄入";
        else if (bmi >= 18.5 && bmi < 24)
            return "正常，请继续保持";
        else if (bmi >= 24 && bmi < 28)
            return "偏重，建议科学规划饮食，抽空适当运动";
        else
            return "肥胖，请控制饮食并适量运动";
    }
}
