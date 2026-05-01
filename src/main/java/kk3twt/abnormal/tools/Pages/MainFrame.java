package kk3twt.abnormal.tools.Pages;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    static final Dimension BUTTON_SIZE = new Dimension(80, 40);

    public MainFrame() {
        setTitle("某科学的工具箱");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 设置中文字体
        Font font = new Font("微软雅黑", Font.PLAIN, 12);
        UIManager.put("Button.font", font);
        UIManager.put("Label.font", font);
        UIManager.put("Menu.font", font);
        UIManager.put("MenuItem.font", font);

        // 设置窗口属性
        setSize(450, 320);
        setLocationRelativeTo(null);      // 使窗口居中显示
        setLayout(new BorderLayout());
        setVisible(true);

        // 创建标签页
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setFont(font);

        // 创建面板
        AboutPage about = new AboutPage(font);
        CalculatorPage calculator = new CalculatorPage(font);
        FileFunctionPage fileFunction = new FileFunctionPage(font);
        OtherFunctionPage otherFunction = new OtherFunctionPage(font);

        // 添加面板

        tabbedPane.addTab("计算功能", calculator);
        tabbedPane.addTab("文件功能", fileFunction);
        tabbedPane.addTab("其他功能", otherFunction);
        tabbedPane.addTab("关于", about);
        add(tabbedPane);

        // 创建底部标签
        JLabel footerLabel = getFooterLabel(font);
        add(footerLabel, BorderLayout.SOUTH);
    }

    // 底部标签
    private JLabel getFooterLabel(Font font) {
        JLabel footerLabel = new JLabel("<html><center>选择功能开始使用</center></html>", SwingConstants.CENTER);
        footerLabel.setFont(font);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return footerLabel;
    }
}