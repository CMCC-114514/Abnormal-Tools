package fileFunctions.audioConversion;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AudioConversionGUI extends JFrame {
    private final JTextField inputField;
    private final JTextField outputField;

    public AudioConversionGUI() {

        // 窗口基本参数
        setTitle("mp3转wav");
        setSize(500, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主面板
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // 选择文件面板
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        //inputPanel.setBorder(new TitledBorder("文件选择"));

        inputField = new JTextField(30);
        JButton inputBtn = new JButton("选择mp3文件");
        inputBtn.addActionListener(e -> chooseInputFile());
        inputPanel.add(inputField);
        inputPanel.add(inputBtn);

        // 选择输出位置面板
        JPanel outputPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        //outputPanel.setBorder(new TitledBorder("输出位置选择"));

        outputField = new JTextField(30);
        JButton outputBtn = new JButton("选择输出位置");
        outputBtn.addActionListener(e -> chooseOutputFile());
        outputPanel.add(outputField);
        outputPanel.add(outputBtn);

        // 转换按钮
        JButton convertBtn = new JButton("开始转换");
        convertBtn.addActionListener(e -> convertFile());

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(outputPanel, BorderLayout.CENTER);
        mainPanel.add(convertBtn, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        JLabel footerLabel = getFooterLabel(new Font("Microsoft YaHei", Font.PLAIN, 12));
        add(footerLabel, BorderLayout.SOUTH);
    }

    private void chooseInputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".mp3") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "MP3文件 (*.mp3)";
            }
        });

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputField.setText(chooser.getSelectedFile().getAbsolutePath());
            // 自动生成输出文件名
            String inputPath = inputField.getText();
            String outputPath = inputPath.replace(".mp3", ".wav");
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
        String input = inputField.getText();
        String output = outputField.getText();

        if (input.isEmpty() || output.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请选择输入和输出文件");
            return;
        }

        try {
            Mp3ToWav.convert(input, output);
            JOptionPane.showMessageDialog(this, "转换成功！");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "转换失败: " + e.getMessage());
        }
    }

    private static JLabel getFooterLabel(Font font) {
        JLabel footerLabel = new JLabel("目前只支持mp3转wav，后续会支持更多格式", SwingConstants.CENTER);
        footerLabel.setFont(font);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return footerLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AudioConversionGUI().setVisible(true));
    }
}
