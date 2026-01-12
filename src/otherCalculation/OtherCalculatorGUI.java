package otherCalculation;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class OtherCalculatorGUI extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    // 计算面板
    private JPanel bmiPanel;
    private JPanel md5Panel;

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
        createMd5Panel();

        // 添加标签页
        tabbedPane.addTab("BMI计算", bmiPanel);
        tabbedPane.addTab("MD5摘要", md5Panel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // 添加状态栏
        JLabel infoLabel = new JLabel(
                "<html><center>输入数值进行计算，这里整合一些不能归类的其他计算，单位按要求换算</center></html>",
                SwingConstants.CENTER
        );
        infoLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(infoLabel, BorderLayout.SOUTH);
    }

    private void createBmiPanel() {
        bmiPanel = new JPanel(new BorderLayout(10, 10));
        bmiPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 输入面板
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

                double result = BMI.calculate(weight, height);
                String type = BMI.returnType(result);

                String outText = String.format(
                        """
                        身高：%.2f m
                        体重：%.2f kg
                        
                        BMI：%.2f
                        你的体重%s""", height, weight, result, type);

                resultArea.setText(outText);
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的数字！");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("错误：" + ex.getMessage());
            }
        });
    }

    private void createMd5Panel() {
        md5Panel = new JPanel(new BorderLayout(10, 10));
        md5Panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 输入面板
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入字符"));

        JTextField strField = new JTextField();

        inputPanel.add(strField);

        JButton encryptButton = new JButton("获取MD5");
        inputPanel.add(encryptButton);

        // 结果面板
        JTextArea resultArea = new JTextArea(8, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("加密结果"));

        md5Panel.add(inputPanel, BorderLayout.NORTH);
        md5Panel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        encryptButton.addActionListener(e -> {
            try {
                String str = strField.getText();
                String md5 = MD5.getMD5(str);

                resultArea.setText("MD5: " + md5);
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的内容！");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("错误：" + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
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
