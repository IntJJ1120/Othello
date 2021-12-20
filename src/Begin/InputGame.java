package Begin;

import util.ScreenUtils;
import view.GameFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class InputGame {
    JFrame jf1 = new JFrame();
    final int WIDTH = 1000;
    final int HEIGHT = 600;
    int BlackIndex;
    int WhiteIndex;
    public static GameFrame gameFrame;

    //组装试图
    public void init(int playerNum, ArrayList<String> fPlayerNames) throws IOException {

        //设置窗口相关属性
        jf1.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,(ScreenUtils.getScreenHight()-HEIGHT)/2,WIDTH,HEIGHT);
        jf1.setResizable(false);
        jf1.setIconImage(ImageIO.read(new File("src/image/logo 馒头.png")));
        jf1.setTitle("黑白棋大挑战");
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



        //组装模式选择（打算放到每一局的开始）


        Box sBox = Box.createHorizontalBox();
        JLabel slable = new JLabel("玩家序号选择规则：若人人对战，则正常填写；若人机对战，则机器一方玩家序号填写0");
        slable.setFont(new Font("方正卡通简体", Font.BOLD, 20));

        sBox.add(slable);
        sBox.add(Box.createHorizontalStrut(20));



        //组装玩家数量
        Box uBox = Box.createHorizontalBox();
        JLabel ulable = new JLabel("请输入本局黑棋玩家序号：");
        ulable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        JTextField uField = new JTextField(15);

        uBox.add(ulable);
        uBox.add(Box.createHorizontalStrut(20));
        uBox.add(uField);


        Box pBox = Box.createHorizontalBox();
        JLabel plable = new JLabel("请输入本局白棋玩家序号：");
        plable.setFont(new Font("方正卡通简体", Font.BOLD, 24));
        JTextField pField = new JTextField(15);

        pBox.add(plable);
        pBox.add(Box.createHorizontalStrut(20));
        pBox.add(pField);





        //组装按钮
        Box btnBox = Box.createHorizontalBox();
        JButton enterBtn = new JButton("开始游戏！");
        JButton backBtn = new JButton("返回上一级");
        JButton exitBtn = new JButton("退出游戏");




        enterBtn.addActionListener(e -> {

            //获取用户输入的玩家数量
            boolean boo1=false;
            boolean boo2=false;
            for (int i=0;i<playerNum+1;i++){
                if(uField.getText().trim().equals(String.format("%d",i))){
                    boo1=true;
                    break;
                }
            }
            for (int i=0;i<playerNum+1;i++){
                if(pField.getText().trim().equals(String.format("%d",i))){
                    boo2=true;
                    break;
                }
            }

            if (!boo1||!boo2) {
                JOptionPane.showMessageDialog(null, String.format("玩家序号请输入0-%d的数字信息!",playerNum));
            }
            else {
                if(pField.getText().trim().equals(uField.getText().trim())){
                    JOptionPane.showMessageDialog(null,"玩家1和玩家2不能相同");
                }else{
                    BlackIndex=Integer.parseInt(uField.getText().trim());
                    WhiteIndex=Integer.parseInt(pField.getText().trim());


                    //销毁当前页面
                    jf1.dispose();
                    //打开一个新的页面
                    try {
                        gameFrame = new GameFrame(fPlayerNames,BlackIndex,WhiteIndex);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    //访问登录接口
                }

            }
        });


        exitBtn.addActionListener(ae -> System.exit(0));

        backBtn.addActionListener(ae -> {
            InputPlayer inputPlayer= new InputPlayer();
            try {
                inputPlayer.init(playerNum);
            } catch (IOException e) {
                e.printStackTrace();
            }
            jf1.dispose();
        });


        btnBox.add(enterBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(backBtn);
        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(exitBtn);


        vBox.add(Box.createVerticalStrut(50));
        vBox.add(tBox);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(sBox);
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(25));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(60));
        vBox.add(btnBox);


        bgPanel.add(vBox);
        jf1.add(bgPanel);
        jf1.setVisible(true);
        jf1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //关闭窗口时退出程序



    }

}
