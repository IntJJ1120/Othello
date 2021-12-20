package Begin;

import components.ChessGridComponent;
import util.ScreenUtils;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class InputPlayer {
    ArrayList<Box> Players= new ArrayList<>(0);
    ArrayList<JTextField> iPlayerNames = new ArrayList<>(0);
    public static ArrayList<String> fPlayerNames = new ArrayList<>(0);
    public static int[] score;

    JFrame jf = new JFrame();
    final int WIDTH = 1000;
    final int HEIGHT = 600;


    //组装试图
    public void init(int playerNum) throws IOException {
        score = new int[playerNum+100];
        fPlayerNames.add("电脑");
        //设置窗口相关属性
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf.setResizable(false);
        jf.setIconImage(ImageIO.read(new File("src/image/logo 馒头.png")));
        jf.setTitle("黑白棋大挑战");
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


        //组装用户名
        for(int i=0;i<playerNum;i++){
            Box uBox = Box.createHorizontalBox();
            JLabel ulable = new JLabel(String.format("玩家%s姓名：",i+1));
            ulable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
            JTextField uField = new JTextField(15);

            uBox.add(ulable);
            uBox.add(Box.createHorizontalStrut(20));
            uBox.add(uField);
            Players.add(uBox);
            iPlayerNames.add(uField);
        }


        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton enterBtn = new JButton("选择比赛双方与模式");
        JButton exitBtn = new JButton("退出游戏");
        JButton backBtn = new JButton("返回上一级");


        enterBtn.addActionListener(e -> {


            //获取用户输入的数据
            for (int i = 0; i < playerNum; i++) {
                if (0 < iPlayerNames.get(i).getText().trim().length() && iPlayerNames.get(i).getText().trim().length() < 10)
                {
                    fPlayerNames.add(iPlayerNames.get(i).getText().trim());

                } else {
                    JOptionPane.showMessageDialog(null, String.format("%s号玩家的名字长度须在0-10之间！",i+1));
                    break;
                }
                if(i==playerNum-1){
                    if (0 < iPlayerNames.get(playerNum-1).getText().trim().length() && iPlayerNames.get(playerNum-1).getText().trim().length() < 10)
                        //销毁当前页面
                        jf.dispose();
                    //进入游戏
                    InputGame inputGame = new InputGame();
                    try {
                        inputGame.init(playerNum,fPlayerNames);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }


        });

        backBtn.addActionListener(ae -> {
            Login login = new Login();
            try {
                login.init();
            } catch (IOException e) {
                e.printStackTrace();
            }
            jf.dispose();
        });

        exitBtn.addActionListener(ae -> System.exit(0));



        btnBox.add(enterBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(backBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(exitBtn);

//        ArrayList<Box> Players= new ArrayList<>(0);
//        ArrayList<JTextField> iPlayerNames = new ArrayList<>(0);
//        ArrayList<String> fPlayerNames = new ArrayList<>(0);

        for(int i=0;i<playerNum;i++){
            vBox.add(Box.createVerticalStrut(50));
            vBox.add(Players.get(i));
        }

        vBox.add(Box.createVerticalStrut(70));
        vBox.add(btnBox);

        bgPanel.add(vBox);
        jf.add(bgPanel);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口时退出程序

    }

//    private int [] score;
}
