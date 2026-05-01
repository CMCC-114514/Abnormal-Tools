package kk3twt.abnormal.tools.Pages;

import kk3twt.abnormal.tools.fileFunctions.fileDownloader.FileDownloaderGUI;
import kk3twt.abnormal.tools.fileFunctions.formatConversion.FormatConversionGUI;
import kk3twt.abnormal.tools.fileFunctions.imageScramble.ScramblerGUI;
import kk3twt.abnormal.tools.fileFunctions.loliconImage.LoliconGUI;
import kk3twt.abnormal.tools.fileFunctions.musicUnlocker.MusicUnlockerGUI;

import javax.swing.*;
import java.awt.*;

import static kk3twt.abnormal.tools.Pages.MainFrame.BUTTON_SIZE;

public class FileFunctionPage extends JPanel {
    public FileFunctionPage(Font font) {
        // 文件功能
        this.setLayout(new GridLayout(2, 3, 20, 20));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton musicUnlock = new JButton("音乐解锁");
        JButton formatConversion = new JButton("音视频格式转换");
        JButton fileDownload = new JButton("文件下载器");
        JButton imageScramble = new JButton("图片与视频混淆");
        JButton loliconImage = new JButton("Pixiv图片搜索");

        // 设置按钮字体
        musicUnlock.setFont(font);
        formatConversion.setFont(font);
        fileDownload.setFont(font);
        imageScramble.setFont(font);
        loliconImage.setFont(font);

        // 设置按钮大小
        musicUnlock.setPreferredSize(BUTTON_SIZE);
        formatConversion.setPreferredSize(BUTTON_SIZE);
        fileDownload.setPreferredSize(BUTTON_SIZE);
        formatConversion.setPreferredSize(BUTTON_SIZE);
        loliconImage.setPreferredSize(BUTTON_SIZE);

        // 按钮监听
        musicUnlock.addActionListener(e -> new MusicUnlockerGUI());
        formatConversion.addActionListener(e -> new FormatConversionGUI());
        fileDownload.addActionListener(e -> new FileDownloaderGUI());
        imageScramble.addActionListener(e -> new ScramblerGUI());
        loliconImage.addActionListener(e -> new LoliconGUI());

        // 添加按钮
        this.add(musicUnlock);
        this.add(formatConversion);
        this.add(fileDownload);
        this.add(imageScramble);
        this.add(loliconImage);
    }
}
