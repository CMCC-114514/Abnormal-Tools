package otherCalculation.ncmdump;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NCMConverterGUI extends JFrame {
    private final JTextField inputField;
    private final JTextField outputField;

    public NCMConverterGUI() {
        setTitle("NCM转MP3转换器");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 输入面板
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(30);
        JButton inputBtn = new JButton("选择NCM文件");
        inputBtn.addActionListener(e -> chooseInputFile());
        inputPanel.add(new JLabel("NCM文件:"));
        inputPanel.add(inputField);
        inputPanel.add(inputBtn);

        // 输出面板
        JPanel outputPanel = new JPanel(new FlowLayout());
        outputField = new JTextField(30);
        JButton outputBtn = new JButton("选择输出位置");
        outputBtn.addActionListener(e -> chooseOutputFile());
        outputPanel.add(new JLabel("MP3文件:"));
        outputPanel.add(outputField);
        outputPanel.add(outputBtn);

        // 转换按钮
        JButton convertBtn = new JButton("开始转换");
        convertBtn.addActionListener(e -> convertFile());

        // 布局
        JPanel mainPanel = new JPanel(new GridLayout(3, 1));
        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);
        mainPanel.add(convertBtn);

        add(mainPanel, BorderLayout.CENTER);

        // 状态栏
        JLabel statusLabel = new JLabel("就绪");
        add(statusLabel, BorderLayout.SOUTH);
    }

    private void chooseInputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".ncm") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "NCM文件 (*.ncm)";
            }
        });

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputField.setText(chooser.getSelectedFile().getAbsolutePath());
            // 自动生成输出文件名
            String inputPath = inputField.getText();
            String outputPath = inputPath.replace(".ncm", ".mp3");
            outputField.setText(outputPath);
        }
    }

    private void chooseOutputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setSelectedFile(new File(outputField.getText()));

        if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            outputField.setText(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void convertFile() {
        try {
            String input = inputField.getText();
            String output = outputField.getText();

            if (input.isEmpty() || output.isEmpty()) {
                JOptionPane.showMessageDialog(this, "请选择输入和输出文件");
                return;
            }

            NeteaseCrypt crypt = new NeteaseCrypt(input);
            crypt.dump("");
            crypt.fixMetadata();
            JOptionPane.showMessageDialog(this, "转换成功!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "转换失败: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new NCMConverterGUI().setVisible(true);
        });
    }
}