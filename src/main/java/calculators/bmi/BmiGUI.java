package calculators.bmi;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class BmiGUI extends JFrame {
    public BmiGUI() {
        setTitle("BMI计算器");
        setSize(400,320);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel bmiPanel = new JPanel(new BorderLayout(10, 10));
        bmiPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 输入面板
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入信息"));

        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();

        inputPanel.add(new JLabel("身高（cm）："));
        inputPanel.add(heightField);
        inputPanel.add(new JLabel("体重（kg)："));
        inputPanel.add(weightField);

        JButton calculateButton = new JButton("计算");
        inputPanel.add(new JLabel(""));
        inputPanel.add(calculateButton);

        // 结果面板
        JTextArea resultArea = new JTextArea(4, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("计算结果"));

        bmiPanel.add(inputPanel, BorderLayout.NORTH);
        bmiPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        calculateButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightField.getText().trim()) / 100;
                double weight = Double.parseDouble(weightField.getText().trim());

                double result = BMI.calculate(weight, height);
                String type = BMI.returnType(result);

                String outText = String.format(
                        """
                        身高：%.1f cm / %.2f m
                        体重：%.2f kg
                        
                        BMI：%.2f
                        你的体重%s""", height * 100, height, weight, result, type);

                resultArea.setText(outText);
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的数字！");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("错误：" + ex.getMessage());
            }
        });

        add(bmiPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new BmiGUI().setVisible(true));
    }
}
