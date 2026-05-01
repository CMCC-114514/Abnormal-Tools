package kk3twt.abnormal.tools.Pages;

import kk3twt.abnormal.tools.calculators.bmi.BmiGUI;
import kk3twt.abnormal.tools.calculators.calculus.CalculusGUI;
import kk3twt.abnormal.tools.calculators.date.DateCalculatorGUI;
import kk3twt.abnormal.tools.calculators.factorial.FactorialGUI;
import kk3twt.abnormal.tools.calculators.geometry.GeometricCalculatorGUI;
import kk3twt.abnormal.tools.calculators.houseLoan.HouseLoanGUI;
import kk3twt.abnormal.tools.calculators.probability.ProbabilityGUI;
import kk3twt.abnormal.tools.calculators.unitsConversion.UnitConverterGUI;

import javax.swing.*;
import java.awt.*;

import static kk3twt.abnormal.tools.Pages.MainFrame.BUTTON_SIZE;

public class CalculatorPage extends JPanel {
    public CalculatorPage(Font font) {
        this.setLayout(new GridLayout(3, 3, 20, 20));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton dateCalculation = new JButton("日期计算");
        JButton unitConversion = new JButton("单位换算");
        JButton geometricCalculation = new JButton("几何计算");
        JButton bmiCalculation = new JButton("BMI计算");
        JButton houseLoanCalculation = new JButton("房贷计算");
        JButton factorialCalculation = new JButton("阶乘计算");
        JButton probabilityCalculation = new JButton("概率计算");
        JButton calculusCalculation = new JButton("简单微积分");

        // 设置按钮字体
        dateCalculation.setFont(font);
        unitConversion.setFont(font);
        geometricCalculation.setFont(font);
        bmiCalculation.setFont(font);
        houseLoanCalculation.setFont(font);
        factorialCalculation.setFont(font);
        probabilityCalculation.setFont(font);
        calculusCalculation.setFont(font);

        // 设置按钮大小
        dateCalculation.setPreferredSize(BUTTON_SIZE);
        unitConversion.setPreferredSize(BUTTON_SIZE);
        geometricCalculation.setPreferredSize(BUTTON_SIZE);
        bmiCalculation.setPreferredSize(BUTTON_SIZE);
        houseLoanCalculation.setPreferredSize(BUTTON_SIZE);
        factorialCalculation.setPreferredSize(BUTTON_SIZE);
        probabilityCalculation.setPreferredSize(BUTTON_SIZE);
        calculusCalculation.setPreferredSize(BUTTON_SIZE);

        // 按钮监听
        dateCalculation.addActionListener(e -> new DateCalculatorGUI());
        unitConversion.addActionListener(e -> new UnitConverterGUI());
        geometricCalculation.addActionListener(e -> new GeometricCalculatorGUI());
        bmiCalculation.addActionListener(e -> new BmiGUI());
        houseLoanCalculation.addActionListener(e -> new HouseLoanGUI());
        factorialCalculation.addActionListener(e -> new FactorialGUI());
        probabilityCalculation.addActionListener(e -> new ProbabilityGUI());
        calculusCalculation.addActionListener(e -> new CalculusGUI());

        // 添加按钮到面板
        this.add(dateCalculation);
        this.add(unitConversion);
        this.add(geometricCalculation);
        this.add(bmiCalculation);
        this.add(houseLoanCalculation);
        this.add(factorialCalculation);
        this.add(probabilityCalculation);
        this.add(calculusCalculation);
    }
}
