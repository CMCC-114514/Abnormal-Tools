package fileFunctions.imgConversion;

import java.awt.*;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class ImageConversionGUI extends JFrame {
    private final JTextField inputField;
    private final JTextField outputField;

    public ImageConversionGUI() {

        // 窗口基本参数
        setTitle("图片格式转换");
        setSize(500, 200);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 主面板
        JPanel mainPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // 选择文件
        JPanel inputPanel = new JPanel(new GridLayout(1,2,10,10));
        inputField = new JTextField(30);
        JButton inputButton = new JButton("选择图片");
        inputButton.addActionListener(e -> chooseInputFile());
        inputPanel.add(inputField);
        inputPanel.add(inputButton);

        // 选择输出位置
        JPanel outputPanel = new JPanel(new GridLayout(1,2,10,10));
        outputField = new JTextField(30);
        outputPanel.add(outputField);

        // 选择目标格式
        JComboBox<String> formatCombo = new JComboBox<>(ImageConverter.IMAGE_FORMATS);
        outputPanel.add(formatCombo);

        // 转换过程
        JButton convertButton = new JButton("开始转换");
        convertButton.addActionListener(e -> {
            int formatIndex = formatCombo.getSelectedIndex();
            convertFile(formatIndex);
        });

        mainPanel.add(inputPanel);
        mainPanel.add(outputPanel);
        mainPanel.add(convertButton);

        add(mainPanel, BorderLayout.CENTER);

        JLabel footerLabel = getFooterLabel(new Font("Microsoft YaHei", Font.PLAIN, 12));
        add(footerLabel, BorderLayout.SOUTH);
    }

    private void chooseInputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        for (String format : ImageConverter.IMAGE_FORMATS) {
            chooser.addChoosableFileFilter(
                    new FileNameExtensionFilter(format + " 文件（*."  + format.toLowerCase() + "）", format.toLowerCase()));
        }

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputField.setText(chooser.getSelectedFile().getAbsolutePath());

            String inputPath = inputField.getText();
            int dot = inputPath.lastIndexOf('.');
            String base = (dot > 0) ? inputPath.substring(0, dot + 1) : inputPath + ".";
            outputField.setText(base);
        }
    }

    private void convertFile(int formatIndex) {
        String format = ImageConverter.IMAGE_FORMATS[formatIndex];
        String input = inputField.getText();
        String output = outputField.getText() + format;

        if (input.isEmpty() || output.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请选择输入和输出文件");
            return;
        }

        try {
            ImageConverter.convert(input, output, format);
            JOptionPane.showMessageDialog(this, "转换成功!");
        }catch (Exception e) {
            JOptionPane.showMessageDialog(this, "转换失败：" + e.getMessage());
        }
    }

    private JLabel getFooterLabel(Font font) {
        JLabel footerLabel = new JLabel("支持的格式:" + ImageConverter.getSupportedFormat(), SwingConstants.CENTER);
        footerLabel.setFont(font);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return footerLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ImageConversionGUI().setVisible(true));
    }
}
