package geometricCalculation;

import java.util.Scanner;

public class Calculators {
    static Scanner sc = new Scanner(System.in);

    private Calculators() {
    }

    public static void rectangle() {
        System.out.print("输入矩形的两条边长，用空格隔开：");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        System.out.println("面积为：" + a * b);
        System.out.println("周长为：" + 2 * (a + b));
        System.out.println("对角线长为：" + Math.sqrt(a * a + b * b));
    }

    public static void circle() {
        System.out.print("1. 半径\n2. 周长\n3. 面积\n请选择一个输入的类型：\n");
        byte choose = sc.nextByte();
        System.out.print("输入数值：");
        double num = sc.nextDouble();
        double r = 0;
        switch (choose) {
            case 1 -> r = num;
            case 2 -> r = num / 2 * Math.PI;
            case 3 -> r = Math.sqrt(num / Math.PI);
        }

        System.out.println("半径为：" + r);
        System.out.println("周长为：" + (Math.PI * 2) * r);
        System.out.println("面积为：" + Math.PI * r * r);
    }

    public static void triangle() {
        System.out.print("输入底和高，用空格隔开：");
        double a = sc.nextDouble();
        double h = sc.nextDouble();
        System.out.println("面积为：" + a * h / 2);
    }

    public static void rhomboid() {
        System.out.print("输入底和高，用空格隔开：");
        double a = sc.nextDouble();
        double h = sc.nextDouble();
        System.out.println("面积为：" + a * h);
    }

    public static void trapezoid() {
        System.out.print("输入上底、下底和高，用空格隔开：");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double h = sc.nextDouble();
        System.out.println("面积为：" + (a + b) * h / 2);
    }

    public static void cone() {
        System.out.print("输入底面半径和高，用空格隔开：");
        double r = sc.nextDouble();
        double h = sc.nextDouble();
        double l = Math.sqrt(r * r + h * h);
        System.out.println("表面积为：" + (Math.PI * r * r + Math.PI * r * l));
        System.out.println("体积为：" + Math.PI * r * r * h / 3);
    }

    public static void sphere() {
        System.out.print("1. 半径\n2. 表面积\n3. 体积\n请选择一个输入的类型：\n");
        byte choose = sc.nextByte();
        System.out.print("输入数值：");
        double num = sc.nextDouble();
        double r = 0;
        switch (choose) {
            case 1 -> r = num;
            case 2 -> r = Math.sqrt(num / 4 * Math.PI);
            case 3 -> r = Math.pow(num / Math.PI, 1.0/3);
        }

        System.out.println("半径为：" + r);
        System.out.println("表面积为：" + 4 * Math.PI * r * r);
        System.out.println("体积为：" + Math.PI * Math.pow(r, 3));
    }

    public static void cuboid() {
        System.out.println("输入长、宽和高，用空格隔开：");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        double h = sc.nextDouble();
        System.out.println("对角线长为：" + Math.sqrt(a * a + b * b + h * h));
        System.out.println("表面积为：" + 2 * (a * b + a * h + b * h));
        System.out.println("体积为：" + a * b * h);
    }

    public static void cylinder() {
        System.out.print("输入底面半径和高，用空格隔开：");
        double r = sc.nextDouble();
        double h = sc.nextDouble();
        System.out.println("表面积为：" + ((Math.PI * 2) * r + (Math.PI * 2) * r * h));
        System.out.println("体积为：" + h * Math.PI * r * r);
    }
}

