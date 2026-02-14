package otherFunctions.randomGenerator;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class RandomGUI extends JFrame{
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    private JPanel numPanel;
    private JPanel strPanel;

    public RandomGUI() {
        setTitle("随机数生成");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350,350);
        setLocationRelativeTo(null);

        initComponents();
        setupLayout();

        setContentPane(mainPanel);
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        createNumPanel();
        createStrPanel();

        tabbedPane.addTab("随机数生成", numPanel);
        tabbedPane.addTab("随机字符串生成", strPanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        JLabel infoLabel = new JLabel(
                "<html><center>随机生成的字符串包括符号</center></html>",
                SwingConstants.CENTER
        );
        infoLabel.setFont(new Font("宋体", Font.PLAIN, 12));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(infoLabel, BorderLayout.SOUTH);
    }

    private void createNumPanel() {
        numPanel = new JPanel(new BorderLayout(10, 10));
        numPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JPanel inputPanel = new JPanel(new GridLayout(3,2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入参数"));

        JTextField minField = new JTextField();
        inputPanel.add(new JLabel("最小值："));
        inputPanel.add(minField);

        JTextField maxField = new JTextField();
        inputPanel.add(new JLabel("最大值"));
        inputPanel.add(maxField);

        JButton generateButton = new JButton("生成");
        inputPanel.add(new Label(""));
        inputPanel.add(generateButton);

        JTextArea resultArea = new JTextArea(1, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("生成结果"));

        numPanel.add(inputPanel, BorderLayout.NORTH);
        numPanel.add(scrollPane, BorderLayout.CENTER);

        generateButton.addActionListener(e -> {
            try {
                int min = Integer.parseInt(minField.getText().trim());
                int max = Integer.parseInt(maxField.getText().trim());

                resultArea.setText(Generators.randomNum(min, max) + " ");
            } catch (NumberFormatException ex) {
                resultArea.setText("错误：请输入有效的内容！");
            } catch (IllegalArgumentException ex) {
                resultArea.setText("错误：" + ex.getMessage());
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    private void createStrPanel() {
        strPanel = new JPanel(new BorderLayout(10, 10));
        strPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        JPanel inputPanel = new JPanel(new GridLayout(2,2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入参数"));

        JTextField lengthField = new JTextField();
        inputPanel.add(new JLabel("字符串长度："));
        inputPanel.add(lengthField);

        JButton generateButton = new JButton("生成");
        inputPanel.add(new Label(""));
        inputPanel.add(generateButton);

        JTextArea resultArea = new JTextArea(1, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("宋体", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("生成结果"));

        strPanel.add(inputPanel, BorderLayout.NORTH);
        strPanel.add(scrollPane, BorderLayout.CENTER);

        generateButton.addActionListener(e -> {
            try {
                int length = Integer.parseInt(lengthField.getText().trim());

                resultArea.setText(Generators.randomStr(length));
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
        Font tableFont = new Font("宋体", Font.PLAIN, 13);

        // 设置所有组件的字体
        Component[] components = tabbedPane.getComponents();
        for (Component comp : components) {
            if (comp instanceof JPanel) {
                setComponentFont((JPanel) comp, labelFont, buttonFont, tableFont);
            }
        }
    }

    private void setComponentFont(JPanel panel, Font labelFont, Font buttonFont, Font tableFont) {
        for (Component comp : panel.getComponents()) {
            if (comp instanceof JLabel) {
                comp.setFont(labelFont);
            } else if (comp instanceof JButton) {
                comp.setFont(buttonFont);
            } else if (comp instanceof JComboBox) {
                comp.setFont(labelFont);
            } else if (comp instanceof JTextField) {
                comp.setFont(labelFont);
            } else if (comp instanceof JTable) {
                comp.setFont(tableFont);
            } else if (comp instanceof JScrollPane) {
                // 处理滚动面板中的组件
                Component view = ((JScrollPane) comp).getViewport().getView();
                if (view instanceof JTable) {
                    view.setFont(tableFont);
                }
            } else if (comp instanceof JPanel) {
                setComponentFont((JPanel) comp, labelFont, buttonFont, tableFont);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // 设置系统外观
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

                // 设置UI样式
                UIManager.put("TabbedPane.selected", new Color(230, 240, 255));

            } catch (Exception e) {
                e.printStackTrace();
            }

            new RandomGUI().setVisible(true);
        });
    }
}
