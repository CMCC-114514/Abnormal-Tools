package kk3twt.abnormal.tools;

import kk3twt.abnormal.tools.Pages.MainFrame;

import javax.swing.*;

public class App {

    // 程序入口
    public static void main(String[] args) {
        try {
            // 设置系统外观
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(MainFrame::new);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"错误" + e.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
        }
    }
}
