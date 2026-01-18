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
    private JPanel houseLoanPanel;
    private JPanel base64Panel;

    // 设置面板框架
    public OtherCalculatorGUI() {
        setTitle("其他计算");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 550);
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
        createHouseLoanPanel();
        createBase64Panel();

        // 添加标签页
        tabbedPane.addTab("BMI计算", bmiPanel);
        tabbedPane.addTab("MD5摘要", md5Panel);
        tabbedPane.addTab("房贷计算", houseLoanPanel);
        tabbedPane.addTab("Base64编码", base64Panel);

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

    // bmi计算面板
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

    // md5摘要界面
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

    // 房贷计算界面
    private void createHouseLoanPanel() {
        houseLoanPanel = new JPanel(new BorderLayout(10, 10));
        houseLoanPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20,20));

        // 输入面板
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(new TitledBorder("输入信息"));

        JComboBox<String> patternCombo = new JComboBox<>(HouseLoan.PATTERNS);
        JTextField limitField = new JTextField();
        JTextField loanAmountField = new JTextField();
        JTextField rateField = new JTextField();

        inputPanel.add(new JLabel("贷款方式："));
        inputPanel.add(patternCombo);
        inputPanel.add(new JLabel("贷款期数："));
        inputPanel.add(limitField);
        inputPanel.add(new JLabel("贷款金额（万元）："));
        inputPanel.add(loanAmountField);
        inputPanel.add(new JLabel("贷款年利率（%）："));
        inputPanel.add(rateField);

        JButton calculateButton = new JButton("计算");
        inputPanel.add(new JLabel(""));
        inputPanel.add(calculateButton);

        // 结果面板
        JTextArea resultArea = new JTextArea(8, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("计算结果"));

        houseLoanPanel.add(inputPanel, BorderLayout.NORTH);
        houseLoanPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        calculateButton.addActionListener(e -> {
            try {
                int selectedIndex = patternCombo.getSelectedIndex();
                int totalMonth = Integer.parseInt(limitField.getText().trim());
                double principal = Double.parseDouble(loanAmountField.getText().trim()) * 1e4;
                double rate = Double.parseDouble(rateField.getText().trim()) / 1200;

                if (selectedIndex == 0) {
                    double result = HouseLoan.interest(principal, rate, totalMonth);
                    double totalAmount = result * totalMonth;
                    double totalInterest = totalAmount - principal;

                    String outText = String.format("""
                            贷款金额：%.2f 万元
                            贷款期数：%d 期 （约 %.1f 年）
                            
                            还款总额：%.2f 元
                            每月还款：%.2f 元
                            总利息：%.2f 元
                            """
                    , principal/1e4, totalMonth, totalMonth/12.0, totalAmount, result, totalInterest);

                    resultArea.setText(outText);

                } else if (selectedIndex == 1) {
                    double[] result = HouseLoan.capital(principal, rate, totalMonth);
                    double totalAmount = (result[0] + result[result.length - 1]) * result.length / 2;
                    double firstMonth = result[0];
                    double degressive = result[0] - result[1];
                    double lastMonth = result[result.length - 1];
                    double totalInterest = principal * rate * (result.length + 1) / 2;

                    String outText = String.format("""
                            贷款金额：%.2f 万元
                            贷款期数：%d 期 （约 %.1f 年）
                            
                            还款总额：%.2f 元
                            首月还款：%.2f 元
                            最后一月还款：%.2f 元
                            每月递减：%.2f 元
                            总利息：%.2f 元
                            """,
                            principal/1e4, totalMonth, totalMonth/12.0, totalAmount, firstMonth, degressive, lastMonth, totalInterest);

                    resultArea.setText(outText);
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的数字！");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("错误：" + ex.getMessage());
            }
        });
    }

    // Base64编码界面
    private void createBase64Panel() {
        base64Panel = new JPanel(new BorderLayout(10, 10));
        base64Panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // 输入面板
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入内容"));

        JComboBox<String> patternCombo = new JComboBox<>(Bas64.PATTERNS);
        JTextField strField = new JTextField();
        JButton convertButton = new JButton("转换");

        inputPanel.add(new JLabel("转换方式："));
        inputPanel.add(patternCombo);
        inputPanel.add(strField);
        inputPanel.add(convertButton);

        // 结果面板
        JTextArea resultArea = new JTextArea(8, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("转换结果"));

        base64Panel.add(inputPanel, BorderLayout.NORTH);
        base64Panel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                int selectIndex = patternCombo.getSelectedIndex();
                String str = strField.getText();
                String result;

                switch (selectIndex) {
                    case 0 -> result = Bas64.enCode(str);
                    case 1 -> result = Bas64.deCode(str);
                    default -> result = "错误：不合法的选项";
                }

                resultArea.setText("结果：" + result);
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的内容！");
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
