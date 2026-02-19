package fileFunctions.fileDownloader;

import utils.Downloader;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileDownloaderGUI extends JFrame {

    private final JProgressBar progressBar = new JProgressBar(0, 100);
    private final JTextField urlField = new JTextField();
    private final JTextField fileNameField = new JTextField();
    private Downloader downloader;

    public FileDownloaderGUI() {
        setTitle("文件下载器");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10,10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15,15,10,15));

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        inputPanel.setBorder(new TitledBorder("输入下载链接和文件名"));

        inputPanel.add(new JLabel("下载链接："));
        inputPanel.add(urlField);
        inputPanel.add(new JLabel("文件名："));
        inputPanel.add(fileNameField);

        JButton downloadButton = new JButton("开始");
        inputPanel.add(downloadButton);

        JButton pauseButton = new JButton("暂停");
        inputPanel.add(pauseButton);

        downloadButton.addActionListener(e -> startDownload());
        pauseButton.addActionListener(e -> {
            pause();
            downloadButton.setText("继续");
        });

        progressBar.setStringPainted(true);
        JLabel footerLabel = new JLabel("默认下载到 C:\\Users\\user-name\\Downloads", SwingConstants.CENTER);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        setLayout(new BorderLayout(10, 10));
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(footerLabel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void startDownload() {
        String fileName = fileNameField.getText();
        Path target = Paths.get(System.getProperty("user.home"), "Downloads").resolve(fileName);
        String url = urlField.getText();

        downloader = new Downloader(target, url);
        downloader.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        downloader.execute();
    }

    private void pause() {
        if (downloader != null && !downloader.isDone()) {
            downloader.cancel(true);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FileDownloaderGUI().setVisible(true));
    }
}
