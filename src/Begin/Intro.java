package Begin;

import util.ScreenUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import Begin.*;


public class Intro {
    protected int PlayerNum;

    JFrame jf0 = new JFrame();
    final int WIDTH = 1500;
    final int HEIGHT = 900;

    //组装试图
    public void init() throws IOException {

        //设置窗口相关属性
        jf0.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf0.setResizable(false);
        jf0.setIconImage(ImageIO.read(new File("src/image/logo 馒头.png")));
        jf0.setTitle("游戏背景");
        //设置窗口内容
        //绘制背景
        BackGroundPanel bgPanel= new BackGroundPanel(ImageIO.read(new File("src/image/背景.png")));

        //组装登录相关元素
        Box vBox = Box.createVerticalBox();

        //标题
        Box tBox = Box.createHorizontalBox();
        JLabel tlable = new JLabel("游戏背景");
        tlable.setFont(new Font("方正卡通简体", Font.BOLD, 36));

        tBox.add(tlable);
        tBox.add(Box.createHorizontalStrut(20));
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(tBox);

        vBox.add(Box.createVerticalStrut(100));
        //组装玩家数量
        Box u1Box = Box.createHorizontalBox();
        JLabel u1lable = new JLabel("黑白棋是19世纪末英国人发明的。直到上个世纪70年代日本人长谷川五郎将其进行发展和推广，");
        u1lable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        u1Box.add(u1lable);
        u1Box.add(Box.createHorizontalStrut(20));
        vBox.add(u1Box);
        vBox.add(Box.createVerticalStrut(10));

        Box u2Box = Box.createHorizontalBox();
        JLabel u2lable = new JLabel("借用莎士比亚名剧奥赛罗（othello)为这个游戏重新命名(日语“オセロ”），也就是大家玩的黑白棋。");
        u2lable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        u2Box.add(u2lable);
        u2Box.add(Box.createHorizontalStrut(20));
        vBox.add(u2Box);
        vBox.add(Box.createVerticalStrut(10));


        Box u3Box = Box.createHorizontalBox();
        JLabel u3lable = new JLabel("为何借用莎士比亚名剧呢?是因为奥赛罗是莎士比亚一个名剧的男主角。");
        u3lable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        u3Box.add(u3lable);
        u3Box.add(Box.createHorizontalStrut(20));
        vBox.add(u3Box);
        vBox.add(Box.createVerticalStrut(10));


        Box u4Box = Box.createHorizontalBox();
        JLabel u4lable = new JLabel("他是一个黑人，妻子是白人，因受小人挑拨，怀疑妻子不忠一直情海翻波，最终亲手把妻子杀死。");
        u4lable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        u4Box.add(u4lable);
        u4Box.add(Box.createHorizontalStrut(20));
        vBox.add(u4Box);
        vBox.add(Box.createVerticalStrut(10));

        Box u5Box = Box.createHorizontalBox();
        JLabel u5lable = new JLabel("后来真相大白，奥赛罗懊悔不已，自杀而死。黑白棋就是借用这个黑人白人斗争的故事而命名。");
        u5lable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        u5Box.add(u5lable);
        u5Box.add(Box.createHorizontalStrut(20));
        vBox.add(u5Box);
        vBox.add(Box.createVerticalStrut(10));

        bgPanel.add(vBox);
        jf0.add(bgPanel);
        jf0.setVisible(true);
        jf0.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //关闭窗口时退出程序



    }

}
