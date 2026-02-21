// UnitConverterGUI.java
package calculators.unitsConversion;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Arrays;

public class UnitConverterGUI extends JFrame {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    // 各个换算面板
    private JPanel lengthPanel;
    private JPanel areaPanel;
    private JPanel volumePanel;
    private JPanel massPanel;
    private JPanel numSystemPanel;
    private JPanel speedPanel;
    private JPanel temperaturePanel;
    private JPanel storagePanel;
    private JPanel colorCodePanel;

    public UnitConverterGUI() {
        setTitle("单位换算器");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 550);
        setLocationRelativeTo(null);

        initComponents();
        setupLayout();

        setContentPane(mainPanel);
    }

    private void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        // 创建各个功能面板
        createLengthPanel();
        createAreaPanel();
        createVolumePanel();
        createMassPanel();
        createNumSystemPanel();
        createSpeedPanel();
        createTemperaturePanel();
        createStoragePanel();
        createColorCodePanel();

        // 添加标签页
        tabbedPane.addTab("长度换算", lengthPanel);
        tabbedPane.addTab("面积换算", areaPanel);
        tabbedPane.addTab("体积换算", volumePanel);
        tabbedPane.addTab("质量换算", massPanel);
        tabbedPane.addTab("进制换算", numSystemPanel);
        tabbedPane.addTab("速度换算", speedPanel);
        tabbedPane.addTab("温度换算", temperaturePanel);
        tabbedPane.addTab("存储单位换算", storagePanel);
        tabbedPane.addTab("颜色码转换", colorCodePanel);

        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // 添加说明
        JLabel infoLabel = new JLabel(
                "<html><center>输入数值并选择输入单位，系统将自动计算所有单位的换算结果<br>" +
                        "部分类型的换算支持国际单位、英制单位和市制单位之间的换算</center></html>",
                SwingConstants.CENTER
        );
        infoLabel.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(infoLabel, BorderLayout.SOUTH);
    }

    private void createLengthPanel() {
        lengthPanel = new JPanel(new BorderLayout(10, 10));
        lengthPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("长度换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.LENGTH_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("长度换算结果"));

        lengthPanel.add(inputPanel, BorderLayout.WEST);
        lengthPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.length(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    // 移除多余的零
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.LENGTH_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createAreaPanel() {
        areaPanel = new JPanel(new BorderLayout(10, 10));
        areaPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("面积换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.AREA_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);
        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("面积换算结果"));

        areaPanel.add(inputPanel, BorderLayout.WEST);
        areaPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.area(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.AREA_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createVolumePanel() {
        volumePanel = new JPanel(new BorderLayout(10, 10));
        volumePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("体积换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.VOLUME_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("体积换算结果"));

        volumePanel.add(inputPanel, BorderLayout.WEST);
        volumePanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.volume(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.VOLUME_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createMassPanel() {
        massPanel = new JPanel(new BorderLayout(10, 10));
        massPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("质量换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.MASS_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("质量换算结果"));

        massPanel.add(inputPanel, BorderLayout.WEST);
        massPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.mass(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.MASS_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createNumSystemPanel() {
        numSystemPanel = new JPanel(new BorderLayout(10, 10));
        numSystemPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("进制换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.NUM_SYSTEM_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"进制", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("进制换算结果"));

        numSystemPanel.add(inputPanel, BorderLayout.WEST);
        numSystemPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                String value = valueField.getText().trim();
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                String[] results = Converts.numSystem(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%s", results[i]);

                    Object[] rowData = {
                            Converts.NUM_SYSTEM_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createSpeedPanel() {
        speedPanel = new JPanel(new BorderLayout(10, 10));
        speedPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("速度换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.SPEED_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("单位换算结果"));

        speedPanel.add(inputPanel, BorderLayout.WEST);
        speedPanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.speed(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.SPEED_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createTemperaturePanel() {
        temperaturePanel = new JPanel(new BorderLayout(10, 10));
        temperaturePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("温度换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.TEMPERATURE_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("单位换算结果"));

        temperaturePanel.add(inputPanel, BorderLayout.WEST);
        temperaturePanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.temperature(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.TEMPERATURE_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createStoragePanel() {
        storagePanel = new JPanel(new BorderLayout(10, 10));
        storagePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 输入面板 - 修改布局
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("数据单位换算输入"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 第一列：数值输入
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JTextField valueField = new JTextField();
        inputPanel.add(valueField, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第二列：单位选择
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("单位:"), gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JComboBox<String> unitCombo = new JComboBox<>(Converts.STORAGE_UNITS);
        inputPanel.add(unitCombo, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 第三列：按钮
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"单位", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("单位换算结果"));

        storagePanel.add(inputPanel, BorderLayout.WEST);
        storagePanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        convertButton.addActionListener(e -> {
            try {
                double value = Double.parseDouble(valueField.getText().trim());
                int selectedIndex = unitCombo.getSelectedIndex();
                byte unitChoice = (byte)(selectedIndex + 1);

                double[] results = Converts.storage(unitChoice, value);

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%.10f", results[i]);
                    formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.STORAGE_UNITS[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "换算过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> {
            valueField.setText("");
            tableModel.setRowCount(0);
        });

        // 回车键转换
        valueField.addActionListener(e -> convertButton.doClick());
    }

    private void createColorCodePanel() {
        colorCodePanel = new JPanel(new BorderLayout(10, 10));
        colorCodePanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBorder(new TitledBorder("输入编码"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 信息面板：选择编码类型
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("编码类型："), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1;
        JComboBox<String> codeBox = new JComboBox<>(Converts.COLOR_CODES);
        inputPanel.add(codeBox, gbc);

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 1;
        inputPanel.add(new JLabel(" "), gbc);

        // 数据输入面板
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        inputPanel.add(new JLabel("数值："), gbc);

        JPanel hexPanel = new JPanel(new GridLayout(2,1, 10, 10));
        JTextField hexField = new JTextField();
        hexPanel.add(hexField);
        hexPanel.add(new JLabel());

        JPanel rgbPanel = new JPanel(new GridLayout(3,1, 10, 10));
        JTextField rField = new JTextField();
        JTextField gField = new JTextField();
        JTextField bField = new JTextField();
        rgbPanel.add(rField);
        rgbPanel.add(gField);
        rgbPanel.add(bField);

        JPanel cmykPanel = new JPanel(new GridLayout(4,1, 10, 10));
        JTextField cField = new JTextField();
        JTextField mField = new JTextField();
        JTextField yField = new JTextField();
        JTextField kField = new JTextField();
        cmykPanel.add(cField);
        cmykPanel.add(mField);
        cmykPanel.add(yField);
        cmykPanel.add(kField);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        JPanel[] codePanels = {rgbPanel, cmykPanel, hexPanel};
        inputPanel.add(codePanels[0], gbc);
        final int[] panelIndex = {0, -1};

        // 分隔符
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.weightx = 0;
        inputPanel.add(new JLabel(" "), gbc);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new GridLayout(2,1, 5, 5));
        JButton convertButton = new JButton("换算");
        JButton clearButton = new JButton("清空");
        buttonPanel.add(convertButton);
        buttonPanel.add(clearButton);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.weightx = 1;
        inputPanel.add(buttonPanel, gbc);

        // 结果表格
        String[] columnNames = {"编码", "换算结果"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable resultTable = new JTable(tableModel);
        resultTable.setRowHeight(25);
        resultTable.getColumnModel().getColumn(0).setPreferredWidth(150);
        resultTable.getColumnModel().getColumn(1).setPreferredWidth(200);

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setBorder(new TitledBorder("颜色码换算结果"));

        colorCodePanel.add(inputPanel, BorderLayout.WEST);
        colorCodePanel.add(scrollPane, BorderLayout.CENTER);

        // 事件处理
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 1;
        codeBox.addActionListener(e -> {
            if (panelIndex[0] != codeBox.getSelectedIndex()) {
                panelIndex[1] = codeBox.getSelectedIndex();
                inputPanel.add(codePanels[panelIndex[1]], gbc);
                inputPanel.remove(codePanels[panelIndex[0]]);
                inputPanel.revalidate();
                inputPanel.repaint();
                panelIndex[0] = panelIndex[1];
            }
        });

        convertButton.addActionListener(e -> {
            try {
                String[] results = new String[3];
                switch (codeBox.getSelectedIndex()) {
                    case 0 : {
                        int r = Integer.parseInt(rField.getText().trim());
                        int g = Integer.parseInt(gField.getText().trim());
                        int b = Integer.parseInt(bField.getText().trim());
                        int[] rgb = {r, g, b};
                        String hex = Converts.RGB2HEX(rgb).toUpperCase();
                        int[] cmyk = Converts.RGB2CMYK(rgb);

                        results = new String[]{Arrays.toString(rgb), Arrays.toString(cmyk), hex};
                        break;
                    }

                    case 1 : {
                        int c = Integer.parseInt(cField.getText().trim());
                        int m = Integer.parseInt(mField.getText().trim());
                        int y = Integer.parseInt(yField.getText().trim());
                        int k = Integer.parseInt(kField.getText().trim());
                        int[] cmyk = {c, m, y, k};
                        int[] rgb = Converts.CMYK2RGB(cmyk);
                        String hex = Converts.RGB2HEX(rgb).toUpperCase();

                        results = new String[]{Arrays.toString(rgb), Arrays.toString(cmyk), hex};
                        break;
                    }

                    case 2 : {
                        String hex = hexField.getText().toUpperCase();
                        int[] rgb = Converts.HEX2RGB(hex);
                        int[] cmyk = Converts.RGB2CMYK(rgb);
                        results = new String[]{Arrays.toString(rgb), Arrays.toString(cmyk), "#" + hex};
                    }
                }

                // 清空表格
                tableModel.setRowCount(0);

                // 添加结果
                for (int i = 0; i < results.length; i++) {
                    String formattedResult = String.format("%s", results[i]);
                    //formattedResult = formattedResult.replaceAll("0*$", "").replaceAll("\\.$", "");

                    Object[] rowData = {
                            Converts.COLOR_CODES[i],
                            formattedResult
                    };
                    tableModel.addRow(rowData);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "请输入有效的数字！", "错误", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "转换过程中出现错误！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        });

        clearButton.addActionListener(e -> tableModel.setRowCount(0));

        hexField.addActionListener(e -> convertButton.doClick());
        bField.addActionListener(e -> convertButton.doClick());
        kField.addActionListener(e -> convertButton.doClick());
    }

    private void setupLayout() {
        // 设置统一的字体
        Font titleFont = new Font("微软雅黑", Font.BOLD, 16);
        Font labelFont = new Font("宋体", Font.PLAIN, 14);
        Font buttonFont = new Font("微软雅黑", Font.PLAIN, 13);
        Font tableFont = new Font("微软雅黑", Font.PLAIN, 12);

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
            UnitConverterGUI gui = new UnitConverterGUI();
            gui.setVisible(true);
        });
    }
}