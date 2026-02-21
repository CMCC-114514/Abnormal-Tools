package fileFunctions.imageScramble;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ScramblerGUI extends JFrame {
    private final JTextField inputField;
    private final JTextField passwordField;
    private String outputPath;

    public ScramblerGUI() {
        setTitle("图片混淆");
        setSize(450,225);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,15,15));

        // 选择文件面板
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        inputField = new JTextField(30);
        JButton inputBtn = new JButton("选择图片文件");
        inputBtn.addActionListener(e -> chooseInputFile());
        inputPanel.add(inputField);
        inputPanel.add(inputBtn);

        // 选择混淆类型
        JPanel typePanel = new JPanel(new GridLayout(2, 2, 10, 10));

        String[] type = {"密码混淆", "小番茄算法混淆"};
        JComboBox<String> typeBox = new JComboBox<>(type);
        passwordField = new JTextField(30);
        typePanel.add(new JLabel("选择混淆类型："));
        typePanel.add(typeBox);
        JLabel passwordLabel = new JLabel("输入密码：");
        typePanel.add(passwordLabel);
        typePanel.add(passwordField);

        // 混淆按钮与解混淆按钮
        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 10, 10));

        JButton scrambleButton = new JButton("混淆");
        JButton descrambleButton = new JButton("解混淆");
        buttonPanel.add(scrambleButton);
        buttonPanel.add(descrambleButton);

        // 事件监听
        typeBox.addActionListener(e -> {
            if (typeBox.getSelectedIndex() == 0) {
                passwordLabel.setText("输入密码：");
                passwordField.setEditable(true);
            } else {
                passwordLabel.setText("该算法不需要输入密码");
                passwordField.setEditable(false);
            }
        });

        scrambleButton.addActionListener(e -> {
            try {
                scramble(typeBox.getSelectedIndex());
                JOptionPane.showMessageDialog(this, "完成");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "错误：" + ex.getMessage());
            }
        });

        descrambleButton.addActionListener(e -> {
            try {
                descramble(typeBox.getSelectedIndex());
                JOptionPane.showMessageDialog(this, "完成");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "错误：" + ex.getMessage());
            }
        });

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(typePanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel, BorderLayout.CENTER);

        JLabel footerLabel = new JLabel("目前仅支持png格式，解混淆后的图片质量取决于原图质量", SwingUtilities.CENTER);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        add(footerLabel, BorderLayout.SOUTH);
    }

    private void chooseInputFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".png") || f.getName().endsWith(".PNG");
            }

            @Override
            public String getDescription() {
                return "图片文件（*.png）";
            }
        });

        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            inputField.setText(chooser.getSelectedFile().getAbsolutePath());

            String inputPath = inputField.getText();
            int dot = inputPath.lastIndexOf('.');
            String base = (dot > 0) ? inputPath.substring(0, dot) : "none";
            outputPath = base + "_processed.png";
        }
    }

    private void scramble(int type) throws IOException {
        String imgPath = inputField.getText();
        BufferedImage src = ImageIO.read(new File(imgPath));

        BufferedImage out;
        if (type == 0) {
            String password = passwordField.getText().trim();
            long seed = PasswordScrambler.passwordToSeed(password);

            out = PasswordScrambler.scramble(src, seed);
        } else {
            out = IEADScrambler.encrypt(src);
        }

        ImageIO.write(out, "PNG", new File(outputPath));
    }

    private void descramble(int type) throws IOException {
        String imgPath = inputField.getText();
        BufferedImage src = ImageIO.read(new File(imgPath));

        BufferedImage out;
        if (type == 0) {
            String password = passwordField.getText().trim();
            long seed = PasswordScrambler.passwordToSeed(password);

            out = PasswordScrambler.descramble(src, seed);
        } else {
            out = IEADScrambler.decrypt(src);
        }

        ImageIO.write(out, "PNG", new File(outputPath));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ScramblerGUI::new);
    }
}
