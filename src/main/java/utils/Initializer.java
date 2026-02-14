package utils;

import utils.dependencyManager.AppPath;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Initializer extends JFrame {

    private final JProgressBar progressBar = new JProgressBar(0, 100);

    public static boolean isInitialized(String[] args) {
        Path initFile = AppPath.resourcePath("isInitialized");
        Path libZip = AppPath.appHome().resolve("lib.zip");

        if (Files.exists(initFile)) {
            return true;
        } else {
            try {
                if (!Files.exists(libZip)){
                    Initializer.main(args);
                }
                ZipExtractor.unzip(libZip, AppPath.appHome());
                Files.write(initFile, "1".getBytes(), StandardOpenOption.CREATE_NEW);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return false;
    }

    public Initializer() {
        setTitle("下载依赖");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        progressBar.setStringPainted(true);
        JLabel footerLabel = new JLabel("初次启动，需要下载相关依赖，下载完成后请重新启动程序", SwingConstants.CENTER);
        footerLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));


        setLayout(new BorderLayout(10, 10));
        add(footerLabel, BorderLayout.SOUTH);
        add(progressBar, BorderLayout.CENTER);

        startDownload();
    }

    private void startDownload() {
        Path target = AppPath.appHome().resolve("lib.zip");
        String url = "https://github.com/CMCC-114514/Abnormal_Dependencies/releases/download/lib/lib.zip";

        Downloader Downloader = new Downloader(target, url);
        Downloader.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((Integer) evt.getNewValue());
            }
        });

        Downloader.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Initializer initializer = new Initializer();
            initializer.setVisible(true);
        });
    }
}

