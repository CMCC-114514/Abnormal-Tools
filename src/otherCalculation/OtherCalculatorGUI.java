package otherCalculation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OtherCalculatorGUI extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    // 计算面板
    private JPanel bmiPanel;

    // 设置面板框架
    public OtherCalculatorGUI() {
        setTitle("其他类型计算器");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);

        initComponents();
        setupLayout();

        setContentPane(mainPanel);
    }

    // 初始化面板
    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // 创建各功能面板
        createBmiPanel();

        // 添加标签页
        tabbedPane.addTab("BMI计算", bmiPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // 添加状态栏
        JLabel infoLabel = new JLabel(
                "<html><center>输入数值进行计算，这里整合一些不能归类的其他计算，单位使用标准单位</center></html>",
                SwingConstants.CENTER
        );
        infoLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(infoLabel, BorderLayout.SOUTH);
    }

    private void createBmiPanel() {
        bmiPanel = new JPanel(new BorderLayout(10, 10));
        bmiPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 输出面板
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入信息"));

        JTextField weightField = new JTextField();
        JTextField heightField = new JTextField();

        inputPanel.add(new JLabel("身高（m）："));
        inputPanel.add(heightField);
        inputPanel.add(new JLabel("体重（kg)："));
        inputPanel.add(weightField);

        JButton calculateButton = new JButton("计算");
        inputPanel.add(new JLabel(""));
        inputPanel.add(calculateButton);

        // 结果面板
        JTextArea resultArea = new JTextArea(8, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("计算结果"));

        bmiPanel.add(inputPanel, BorderLayout.NORTH);
        bmiPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        calculateButton.addActionListener(e -> {
            try {
                double height = Double.parseDouble(heightField.getText().trim());
                double weight = Double.parseDouble(weightField.getText().trim());

                double results = BMICalculator.calculate(weight, height);

                resultArea.setText("BMI：" + results);
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的数字！");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("错误：" + ex.getMessage());
            }
        });
    }

    private void setupLayout() {
        // 设置统一的字体
        Font titleFont = new Font("微软雅黑", Font.BOLD, 16);
        Font labelFont = new Font("宋体", Font.PLAIN, 14);
        Font buttonFont = new Font("宋体", Font.BOLD, 14);

        // 设置所有组件的字体
        Component[] components = tabbedPane.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                setComponentFont((JPanel) comp, labelFont, buttonFont);
            }
        }
    }

    private void setComponentFont(JPanel panel, Font labelFont, Font buttonFont) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(labelFont);
            } else if (comp instanceof JButton) {
                comp.setFont(buttonFont);
            } else if (comp instanceof JComboBox) {
                comp.setFont(labelFont);
            } else if (comp instanceof JTextField) {
                comp.setFont(labelFont);
            } else if (comp instanceof JTextArea) {
                comp.setFont(labelFont);
            } else if (comp instanceof JPanel) {
                setComponentFont((JPanel) comp, labelFont, buttonFont);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 设置系统外观
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            OtherCalculatorGUI gui = new OtherCalculatorGUI();
            gui.setVisible(true);
        });
    }
}
