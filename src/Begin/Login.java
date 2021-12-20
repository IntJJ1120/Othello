package Begin;

import util.ScreenUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import Begin.*;


public class Login {
    protected int PlayerNum;

    JFrame jf0 = new JFrame();
    final int WIDTH = 1000;
    final int HEIGHT = 600;

    //组装试图
    public void init() throws IOException {

        //设置窗口相关属性
        jf0.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf0.setResizable(false);
        jf0.setIconImage(ImageIO.read(new File("src/image/logo 馒头.png")));
        jf0.setTitle("黑白棋大挑战");
        //设置窗口内容
        //绘制背景
        BackGroundPanel bgPanel= new BackGroundPanel(ImageIO.read(new File("src/image/背景.png")));

        //组装登录相关元素
        Box vBox = Box.createVerticalBox();

        //标题
        Box tBox = Box.createHorizontalBox();
        JLabel tlable = new JLabel("黑白棋大挑战");
        tlable.setFont(new Font("方正卡通简体", Font.BOLD, 36));

        tBox.add(tlable);
        tBox.add(Box.createHorizontalStrut(20));


        //组装玩家数量
        Box uBox = Box.createHorizontalBox();
        JLabel ulable = new JLabel("请输入玩家数量：");
        ulable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        JTextField uField = new JTextField(15);

        uBox.add(ulable);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);


        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton enterBtn = new JButton("输入玩家姓名");
        JButton exitBtn = new JButton("退出游戏");




        enterBtn.addActionListener(e -> {
            //获取用户输入的玩家数量
            if (!uField.getText().trim().equals("1") && !uField.getText().trim().equals("2") &&
                    !uField.getText().trim().equals("3") && !uField.getText().trim().equals("4") &&
                    !uField.getText().trim().equals("5")) {
                JOptionPane.showMessageDialog(null, "请输入1-5的数字信息!");
            } else {
                PlayerNum = Integer.parseInt(uField.getText().trim());
                //销毁当前页面
                jf0.dispose();
                //打开一个新的页面
                InputPlayer inputPlayer= new InputPlayer();
                try {
                    inputPlayer.init(PlayerNum);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                //访问登录接口
            }
        });


        exitBtn.addActionListener(ae -> System.exit(0));




        btnBox.add(enterBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(exitBtn);

        vBox.add(Box.createVerticalStrut(50));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(100));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(btnBox);


        bgPanel.add(vBox);
        jf0.add(bgPanel);
        jf0.setVisible(true);
        jf0.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口时退出程序



    }

}
