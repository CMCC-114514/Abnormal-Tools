import java.util.Scanner;

public class Main {
    final static int DATE_CALCULATING = 1;
    final static int UNITS_CONVERSION = 2;
    final static int GEOMETRIC_CALCULATION = 3;
    final static int EXIT = 4;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //输出菜单
        System.out.print("""
                ==========多功能计算器==========
                1.日期计算
                2.单位换算
                3.几何计算
                4.退出程序
                输入选项：""");

        int choose = sc.nextByte();
        switch (choose) {
            case DATE_CALCULATING -> dateCalculation.Run.main(args);
            case UNITS_CONVERSION -> unitsConversion.Run.main(args);
            case GEOMETRIC_CALCULATION -> geometricCalculation.Run.main(args);
            case EXIT -> System.exit(0);
            default -> System.out.print("没有这个选项！");
        }
    }
}