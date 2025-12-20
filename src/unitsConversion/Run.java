package unitsConversion;

import java.util.Scanner;

public class Run {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("""
                ========== 单位换算 ==========
                1. 长度单位换算
                2. 面积单位换算
                3. 体积单位换算
                4. 质量单位换算
                输入选择：""");
        byte choose = sc.nextByte();

        switch (choose) {
            case 1 -> Converts.length();
            case 2 -> Converts.area();
            case 3 -> Converts.volume();
            case 4 -> Converts.mass();
        }
    }
}
