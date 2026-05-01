package kk3twt.abnormal.tools.Pages;

import kk3twt.abnormal.tools.otherFunctions.base64.Bas64GUI;
import kk3twt.abnormal.tools.otherFunctions.md5.Md5GUI;
import kk3twt.abnormal.tools.otherFunctions.randomGenerator.RandomGUI;
import kk3twt.abnormal.tools.otherFunctions.scoreBoard.ScoreBoard;

import javax.swing.*;
import java.awt.*;

import static kk3twt.abnormal.tools.Pages.MainFrame.BUTTON_SIZE;

public class OtherFunctionPage extends JPanel {
    public OtherFunctionPage(Font font) {
        // 其他功能
        this.setLayout(new GridLayout(2, 3, 20, 20));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton md5 = new JButton("MD5摘要");
        JButton base64 = new JButton("Base64编解码");
        JButton randomNum = new JButton("随机数生成");
        JButton scoreBoard = new JButton("计分板");

        // 设置按钮字体
        md5.setFont(font);
        base64.setFont(font);
        randomNum.setFont(font);
        scoreBoard.setFont(font);

        // 设置按钮大小
        md5.setPreferredSize(BUTTON_SIZE);
        base64.setPreferredSize(BUTTON_SIZE);
        randomNum.setPreferredSize(BUTTON_SIZE);
        scoreBoard.setPreferredSize(BUTTON_SIZE);

        // 按钮监听
        md5.addActionListener(e -> new Md5GUI());
        base64.addActionListener(e -> new Bas64GUI());
        randomNum.addActionListener(e -> new RandomGUI());
        scoreBoard.addActionListener(e -> new ScoreBoard());

        // 添加按钮
        this.add(md5);
        this.add(base64);
        this.add(randomNum);
        this.add(scoreBoard);
    }
}
