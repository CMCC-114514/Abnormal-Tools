package calculators.probability;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class probabilityGUI extends JFrame {

    private final JTextField rvField = new JTextField();
    private final JTextArea resultArea = new JTextArea(8, 30);

    private JPanel binomialPanel;

    public probabilityGUI() {
        setTitle("概率计算");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 创建面板
        getBinomialPanel();

        // 创建结果显示区域
        resultArea.setEditable(false);
        resultArea.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setBorder(new TitledBorder("计算结果"));

        // 创建下拉框，后续根据下拉框的选项更改输入界面
        String[] probabilityModels = {
                "二项分布", "超几何分布", "泊松分布", "几何分布"
        };
        JComboBox<String> modelBox = new JComboBox<>(probabilityModels);


        mainPanel.add(modelBox, BorderLayout.NORTH);
        mainPanel.add(binomialPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void getBinomialPanel() {
        // 二项分布
        binomialPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField nField = new JTextField();               // 实验次数
        JTextField probabilityField = new JTextField();     // 发生概率
        JButton calculateButton = new JButton("计算");
        binomialPanel.add(new JLabel("实验次数："));
        binomialPanel.add(nField);
        binomialPanel.add(new JLabel("事件发生概率："));
        binomialPanel.add(probabilityField);
        binomialPanel.add(new JLabel("随机变量X取值："));
        binomialPanel.add(rvField);
        binomialPanel.add(new JLabel());
        binomialPanel.add(calculateButton);

        calculateButton.addActionListener(e -> {
            int n = Integer.parseInt(nField.getText().trim());
            double p = Double.parseDouble(probabilityField.getText().trim());
            int x = Integer.parseInt(rvField.getText().trim());

            double[] result = Calculators.binomial(n, p);

            resultArea.setText(String.format("""
                    X ~ B（%d, %.2f）
                    P（X = %d） = %.3f
                    """, n, p, x, result[x]));
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(probabilityGUI::new);
    }
}
