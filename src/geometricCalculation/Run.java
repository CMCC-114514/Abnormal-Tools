package geometricCalculation;

import java.util.Scanner;

public class Run {
    static final int RECTANGLE = 1;
    static final int CIRCLE = 2;
    static final int TRIANGLE = 3;
    static final int RHOMBOID = 4;
    static final int TRAPEZOID = 5;
    static final int CONE = 6;
    static final int SPHERE = 7;
    static final int CUBOID = 8;
    static final int CYLINDER = 9;

    public static void clearConsole() {
        System.out.print("\u001b[H\u001b[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        clearConsole();
        System.out.print("========== 简易几何计算 ==========\n1. 矩形\n2. 圆形\n3. 三角形\n4. 平行四边形\n5. 梯形\n6. 圆锥\n7. 球体\n8. 长方体\n9. 圆柱\n输入选项：");
        byte choose = sc.nextByte();
        switch (choose) {
            case RECTANGLE -> Calculators.rectangle();
            case CIRCLE -> Calculators.circle();
            case TRIANGLE -> Calculators.triangle();
            case RHOMBOID -> Calculators.rhomboid();
            case TRAPEZOID -> Calculators.trapezoid();
            case CONE -> Calculators.cone();
            case SPHERE -> Calculators.sphere();
            case CUBOID -> Calculators.cuboid();
            case CYLINDER -> Calculators.cylinder();
        }

    }
}

