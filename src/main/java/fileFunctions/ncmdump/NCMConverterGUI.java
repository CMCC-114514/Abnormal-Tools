package fileFunctions.ncmdump;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class NCMConverterGUI extends JFrame {
    private final JTextField inputField;
    private final JTextField outputField;

    public NCMConverterGUI() {
        setTitle("NCMDump");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 主面板
        JPanel ncmDumpPanel = new JPanel(new GridLayout(3, 1, 10,10));
        ncmDumpPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // 选择文件面板
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        inputField = new JTextField(30);
        JButton inputBtn = new JButton("选择NCM文件");
        inputBtn.addActionListener(e -> chooseInputFile());
        inputPanel.add(inputField);
        inputPanel.add(inputBtn);

        // 选择输出位置面板
        JPanel outputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        outputField = new JTextField(30);
        JButton outputBtn = new JButton("选择输出位置");
        outputBtn.addActionListener(e -> chooseOutputFile());
        outputPanel.add(outputField);
        outputPanel.add(outputBtn);

        // 转换按钮
        JButton convertBtn = new JButton("开始转换");
        convertBtn.addActionListener(e -> convertFile());

        ncmDumpPanel.add(inputPanel, BorderLayout.NORTH);
        ncmDumpPanel.add(outputPanel, BorderLayout.CENTER);
        ncmDumpPanel.add(convertBtn, BorderLayout.SOUTH);
        add(ncmDumpPanel, BorderLayout.CENTER);

        JLabel footerLabel = getFooterLabel(new Font("Microsoft YaHei", Font.PLAIN, 12));
        add(footerLabel, BorderLayout.SOUTH);
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
        String input = inputField.getText();
        String output = outputField.getText();

        if (input.isEmpty() || output.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请选择输入和输出文件");
            return;
        }

        try(NeteaseCrypt crypt = new NeteaseCrypt(input)) {
            crypt.dump("");
            crypt.fixMetadata();
            JOptionPane.showMessageDialog(this, "转换成功!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "转换失败: " + e.getMessage());
        }
    }

    private static JLabel getFooterLabel(Font font) {
        JLabel footerLabel = new JLabel("选择ncm文件，程序将自动生成输出位置，目前路径中不能包含中文", SwingConstants.CENTER);
        footerLabel.setFont(font);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        return footerLabel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new NCMConverterGUI().setVisible(true));
    }
}