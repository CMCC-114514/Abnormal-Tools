import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

// 计算功能
import calculators.dateCalculation.DateCalculatorGUI;
import calculators.geometricCalculation.GeometricCalculatorGUI;
import calculators.unitsConversion.UnitConverterGUI;
import calculators.bmi.BmiGUI;
import calculators.houseLoan.HouseLoanGUI;

// 文件功能
import fileFunctions.audioConversion.AudioConversionGUI;
import fileFunctions.imgConversion.ImageFormatConverterGUI;
import fileFunctions.ncmdump.NCMConverterGUI;

// 其他功能
import otherFunctions.base64.Bas64GUI;
import otherFunctions.md5.Md5GUI;

public class MainGUI {
    private static final Dimension BUTTON_SIZE = new Dimension(80, 40);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("某科学的工具箱");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // 中文字体
            Font font = new Font("Microsoft YaHei", Font.PLAIN, 12);
            UIManager.put("Button.font", font);
            UIManager.put("Label.font", font);
            UIManager.put("Menu.font", font);
            UIManager.put("MenuItem.font", font);

            // 窗口属性
            frame.setSize(380, 400);
            frame.setLocationRelativeTo(null);      // 使窗口居中显示
            frame.setLayout(new BorderLayout());

            // 创建按钮面板，根据功能分类
            JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
            JPanel calculatorPanel = getCalculatorPanel(args, font);
            JPanel convertorPanel = getFileFunctionPanel(args, font);
            JPanel otherFunctionPanel = getOtherFunctionPanel(args, font);

            mainPanel.add(calculatorPanel, BorderLayout.CENTER);
            mainPanel.add(convertorPanel, BorderLayout.CENTER);
            mainPanel.add(otherFunctionPanel, BorderLayout.CENTER);
            frame.add(mainPanel, BorderLayout.CENTER);

            // 创建菜单栏和底部标签
            JMenuBar menuBar = getMenuBar(font, frame);
            JLabel footerLabel = getFooterLabel(font);
            frame.setJMenuBar(menuBar);
            frame.add(footerLabel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    // 底部标签
    private static JLabel getFooterLabel(Font font) {
        JLabel footerLabel = new JLabel("选择功能开始使用", SwingConstants.CENTER);
        footerLabel.setFont(font);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return footerLabel;
    }

    // 菜单栏和关于界面
    private static JMenuBar getMenuBar(Font font, JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu aboutMenu = new JMenu("关于");
        aboutMenu.setFont(font);

        JMenuItem aboutItem = new JMenuItem("关于本程序");
        aboutItem.setFont(font);
        aboutItem.addActionListener(e -> JOptionPane.showMessageDialog(
                frame,
                "某科学的工具箱 v1.4.3\n\n爱来自kk3TWT\n\n作者不会排版，别问为什么这么丑了\n\n",
                "关于",
                JOptionPane.INFORMATION_MESSAGE
        ));

        aboutMenu.add(aboutItem);
        menuBar.add(aboutMenu);
        return menuBar;
    }

    // 计算功能
    private static JPanel getCalculatorPanel(String[] args, Font font) {
        // 计算功能
        JPanel calculatorPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        calculatorPanel.setBorder(new TitledBorder("计算功能"));

        JButton dateCalculation = new JButton("日期计算");
        JButton unitConversion = new JButton("单位换算");
        JButton geometricCalculation = new JButton("几何计算");
        JButton bmiCalculation = new JButton("BMI计算");
        JButton houseLoanCalculation = new JButton("房贷计算");

        // 设置按钮字体
        dateCalculation.setFont(font);
        unitConversion.setFont(font);
        geometricCalculation.setFont(font);
        bmiCalculation.setFont(font);
        houseLoanCalculation.setFont(font);

        // 设置按钮大小
        dateCalculation.setPreferredSize(BUTTON_SIZE);
        unitConversion.setPreferredSize(BUTTON_SIZE);
        geometricCalculation.setPreferredSize(BUTTON_SIZE);
        bmiCalculation.setPreferredSize(BUTTON_SIZE);
        houseLoanCalculation.setPreferredSize(BUTTON_SIZE);

        // 按钮监听
        dateCalculation.addActionListener(e -> DateCalculatorGUI.main(args));
        unitConversion.addActionListener(e -> UnitConverterGUI.main(args));
        geometricCalculation.addActionListener(e -> GeometricCalculatorGUI.main(args));
        bmiCalculation.addActionListener(e -> BmiGUI.main(args));
        houseLoanCalculation.addActionListener(e -> HouseLoanGUI.main(args));

        // 添加按钮到面板
        calculatorPanel.add(dateCalculation);
        calculatorPanel.add(unitConversion);
        calculatorPanel.add(geometricCalculation);
        calculatorPanel.add(bmiCalculation);
        calculatorPanel.add(houseLoanCalculation);

        return calculatorPanel;
    }

    // 文件功能
    private static JPanel getFileFunctionPanel(String[] args, Font font) {
        // 文件功能
        JPanel convertorPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        convertorPanel.setBorder(new TitledBorder("文件功能"));

        JButton ncmDump = new JButton("ncmDump");
        JButton imgConversion = new JButton("图片格式转换");
        JButton audioConversion = new JButton("mp3转wav");

        // 设置按钮字体
        ncmDump.setFont(font);
        imgConversion.setFont(font);
        audioConversion.setFont(font);

        // 设置按钮大小
        ncmDump.setPreferredSize(BUTTON_SIZE);
        imgConversion.setPreferredSize(BUTTON_SIZE);
        audioConversion.setPreferredSize(BUTTON_SIZE);

        // 按钮监听
        ncmDump.addActionListener(e -> NCMConverterGUI.main(args));
        imgConversion.addActionListener(e -> ImageFormatConverterGUI.main(args));
        audioConversion.addActionListener(e -> AudioConversionGUI.main(args));

        // 添加按钮
        convertorPanel.add(ncmDump);
        convertorPanel.add(imgConversion);
        convertorPanel.add(audioConversion);

        return convertorPanel;
    }

    // 其他功能
    private static JPanel getOtherFunctionPanel(String[] args, Font font) {
        // 其他功能
        JPanel otherFunctionPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        otherFunctionPanel.setBorder(new TitledBorder("其他功能"));

        JButton md5 = new JButton("MD5摘要");
        JButton base64 = new JButton("Base64编解码");
        // JButton bpmAnalyser = new JButton("BPM分析");

        // 设置按钮字体
        md5.setFont(font);
        base64.setFont(font);
        // bpmAnalyser.setFont(font);

        // 设置按钮大小
        md5.setPreferredSize(BUTTON_SIZE);
        base64.setPreferredSize(BUTTON_SIZE);
        // bpmAnalyser.setPreferredSize(BUTTON_SIZE);

        // 按钮监听
        md5.addActionListener(e -> Md5GUI.main(args));
        base64.addActionListener(e -> Bas64GUI.main(args));

        // 添加按钮
        otherFunctionPanel.add(md5);
        otherFunctionPanel.add(base64);
        otherFunctionPanel.add(new JLabel(""));
        // otherFunctionPanel.add(bpmAnalyser);

        return otherFunctionPanel;
    }
}
