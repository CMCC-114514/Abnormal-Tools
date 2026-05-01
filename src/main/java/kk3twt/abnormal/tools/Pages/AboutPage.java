package kk3twt.abnormal.tools.Pages;

import javax.swing.*;
import java.awt.*;

public class AboutPage extends JPanel {
    public AboutPage(Font font) {
        this.setLayout(new GridLayout(1, 3, 20, 20));
        this.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JButton aboutButton = new JButton("关于本程序");
        aboutButton.setFont(font);
        aboutButton.addActionListener(e -> JOptionPane.showMessageDialog(
                null,
                """
                某科学的工具箱
                爱来自kk3TWT
                我喜欢你
                """,
                "关于本程序",
                JOptionPane.INFORMATION_MESSAGE
        ));

        JButton updateButton = new JButton("更新日志");
        updateButton.setFont(font);
        updateButton.addActionListener(e -> JOptionPane.showMessageDialog(
                null,
                """
                    Abnormal-Tools v1.8.2 (26.04.19)
                    
                    修正：
                    1. 手动重新编译了ffmpeg，现在ffmpeg资源包变得很小了
                    2. 修复了“视频格式转换”转换失败的问题
                    3. 修复了使用ffmpeg有关功能时，ffmpeg没有配置环境变量的情况下无法运行的问题
                    4. 删除了“音频格式转换”中“AMR”格式的转换
                    5. 修复了“视频混淆”会卡死的问题
                    6. 为“pixiv图片搜索”缩略图界面添加了“再来一张”的按钮
                    7. 添加了“我喜欢你”（在关于界面）
                    """,
                "更新日志",
                JOptionPane.INFORMATION_MESSAGE
        ));

        JButton qwq = new JButton("QWQ");
        qwq.setFont(font);
        qwq.addActionListener(e -> JOptionPane.showMessageDialog(
                null,
                """
                        QWQ TAT AWA TWT
                        
                        都看到这里了，来Q群：904976878 玩呗
                        
                        [某群聊的空间移动]是由<某科学管理组>和 Test 为中心并基于<某康康的聊天群>的神奇群聊.
                        以Tencent QQ 为据点为群友提供安全稳定的涩图环境，
                        在以邀请制为核心的制度下没有阴间人和广告等困扰的优良涩图氛围，
                        管理组积极负责24小时全天在线解决问题.
                        群内巨佬云集, 不乏有「嘉心糖」,「二刺螈」,「音游人」,「程序猿」,「男酮」,「原农粥撸幻批」等等重量级选手.
                        """,
                "QWQ",
                JOptionPane.QUESTION_MESSAGE
        ));

        this.add(aboutButton);
        this.add(updateButton);
        this.add(qwq);
    }
}
