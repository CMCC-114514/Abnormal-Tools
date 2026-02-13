package utils.fileDownloader;

import utils.dependencyManager.AppPath;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;

public class Download extends JFrame {

    private final JProgressBar progressBar = new JProgressBar(0, 100);

    public Download() {
        setTitle("文件下载");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        progressBar.setStringPainted(true);
        JLabel footerLabel = new JLabel("初次启动，需要下载相关依赖，请耐心等待", SwingConstants.CENTER);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


        setLayout(new BorderLayout(10, 10));
        add(footerLabel, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.CENTER);

        startDownload();
    }

    private void startDownload() {
        Path target = AppPath.appHome().resolve("lib.zip");
        String url = "https://github.com/CMCC-114514/Abnormal_Dependencies/releases/download/lib/lib.zip";

        Worker worker = new Worker(target, url);
        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Download frame = new Download();
            frame.setVisible(true);
        });
    }
}

