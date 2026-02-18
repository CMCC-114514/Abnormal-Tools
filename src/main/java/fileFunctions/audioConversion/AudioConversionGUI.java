package fileFunctions.audioConversion;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class AudioConversionGUI extends JFrame {
    private final JTextField inputField;
    private final JTextField outputField;

    public AudioConversionGUI() {

        // 窗口基本参数
        setTitle("音频格式转换");
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
        JButton inputBtn = new JButton("选择音频文件");
        inputBtn.addActionListener(e -> chooseInputFile());
        inputPanel.add(inputField);
        inputPanel.add(inputBtn);

        // 选择输出位置
        JPanel outputPanel = new JPanel(new GridLayout(1,2,10,10));
        outputField = new JTextField(30);
        outputPanel.add(outputField);

        // 选择目标格式
        JComboBox<String> formatCombo = new JComboBox<>(Converter.AUDIO_FORMATS);
        outputPanel.add(formatCombo);

        // 转换按钮
        JButton convertBtn = new JButton("开始转换");
        convertBtn.addActionListener(e -> {
            int formatIndex = formatCombo.getSelectedIndex();
            convertFile(formatIndex);
        });

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
                for (String audioFormat : Converter.AUDIO_FORMATS) {
                    if (f.getName().endsWith("." + audioFormat) || f.getName().endsWith("." + audioFormat.toLowerCase())) {
                        return true;
                    }
                }
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "音频文件（" + Converter.getSupportedFormat() + ")";
            }
        });

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputField.setText(chooser.getSelectedFile().getAbsolutePath());

            String inputPath = inputField.getText();
            int dot = inputPath.lastIndexOf('.');
            String base = (dot > 0) ? inputPath.substring(0, dot + 1) : inputPath + ".";
            outputField.setText(base);
        }
    }

    private void convertFile(int formatIndex) {
        String format = Converter.AUDIO_FORMATS[formatIndex];
        String input = inputField.getText();
        String output = outputField.getText() + format;

        if (input.isEmpty() || output.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请选择输入和输出文件");
            return;
        }

        try {
            Converter.convert(input, output);
            JOptionPane.showMessageDialog(this, "转换成功！");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "转换失败: " + e.getMessage());
        }
    }

    private static JLabel getFooterLabel(Font font) {
        JLabel footerLabel = new JLabel("基于FFmpeg二次开发", SwingConstants.CENTER);
        footerLabel.setFont(font);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return footerLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AudioConversionGUI().setVisible(true));
    }
}
