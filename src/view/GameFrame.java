package view;
import Begin.InputGame;
import Begin.InputPlayer;
import Begin.Intro;
import controller.GameController;
import util.ScreenUtils;

import javax.swing.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


public class GameFrame {
    public static GameController controller;
    private ChessBoardPanel chessBoardPanel;
    private StatusPanel statusPanel;
    private ArrayList<String> fPlayerNames;

    int windowWidth = (int)(ScreenUtils.getScreenWidth()*0.95);
    int windowHeight = (int)(ScreenUtils.getScreenHight()*0.95);
    final int Width = (int)(ScreenUtils.getScreenWidth()*0.95);
    final int Height = (int)(ScreenUtils.getScreenHight()*0.95);

    final int WIDTH = 800;
    final int HEIGHT = 800;

    public GameFrame(ArrayList<String> fPlayerNames, int BlackIndex, int WhiteIndex) throws IOException {
        JFrame gameFrame1 = new JFrame();
        this.fPlayerNames=fPlayerNames;

        //basic setting
        gameFrame1.setSize(windowWidth,windowHeight);
        gameFrame1.setLocation((int)(windowWidth*0.03),(int)(windowHeight*0.03));
        gameFrame1.setResizable(true);
        gameFrame1.setLayout(null);

        //set logo
        gameFrame1.setIconImage(ImageIO.read(new File("src/image/logo 馒头.png")));
        gameFrame1.setTitle("黑白棋大挑战");
        gameFrame1.setFont(new Font("华文行楷", Font.BOLD, 36));
        gameFrame1.setLocationRelativeTo(null);


//查看是否设置图标，图标字体


        ImageIcon backgroundIcon = new ImageIcon("src/image/背景.png");
        JLabel backgroundLabel = new JLabel((backgroundIcon));
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setVerticalAlignment(SwingConstants.TOP);

        //设置窗口内容


        // 设置chessBoardPanel
        chessBoardPanel = new ChessBoardPanel((int) (WIDTH* 0.8), (int) (HEIGHT * 0.7));
        chessBoardPanel.setOpaque(false);



        // 设置statusPanel
        statusPanel = new StatusPanel((int) (WIDTH * 0.8), (int) (HEIGHT * 0.1),fPlayerNames, BlackIndex, WhiteIndex);
        statusPanel.setOpaque(false);

        // 设置controller
        controller = new GameController(chessBoardPanel, statusPanel);
        controller.setGamePanel(chessBoardPanel);

        gameFrame1.add(statusPanel);
        gameFrame1.add(chessBoardPanel);


        JButton restartBtn = new JButton("Restart");
        restartBtn.setSize(120, 50);
        gameFrame1.add(restartBtn);
        restartBtn.addActionListener(e -> {
            System.out.println("click restart Btn");
            onRestartClicked();
        });

        JButton loadGameBtn = new JButton("Load");
        loadGameBtn.setSize(120, 50);
        gameFrame1.add(loadGameBtn);
        loadGameBtn.addActionListener(e -> {
            System.out.println("clicked Load Btn");
            String filePath = JOptionPane.showInputDialog(gameFrame1, "input the path here");
            controller.readFileData(filePath);

            if(controller.getBooleans()[1]){
                JOptionPane.showMessageDialog(null, "101：棋盘并非8*8");
            }
            if(controller.getBooleans()[2]){
                JOptionPane.showMessageDialog(null, "102：棋盘内棋子并非包含黑白空三种");
            }
            if(controller.getBooleans()[3]){
                JOptionPane.showMessageDialog(null, "103：无下一步行祺方的提示");
            }
            if(controller.getBooleans()[4]){
                JOptionPane.showMessageDialog(null, "104：文件格式错误");
            }
            if(controller.getBooleans()[5]){
                JOptionPane.showMessageDialog(null, "105：非法落子");
            }
            if(controller.getBooleans()[6]){
                JOptionPane.showMessageDialog(null, "106：其他错误");
            }
            if(controller.getBooleans()[7]){
                JOptionPane.showMessageDialog(null, "107：其他错误");
            }
        });


        JButton saveGameBtn = new JButton("Save");
        saveGameBtn.setSize(120, 50);
        gameFrame1.add(saveGameBtn);
        saveGameBtn.addActionListener(e -> {
            System.out.println("clicked Save Btn");
            String filePath = JOptionPane.showInputDialog(gameFrame1, "input the path here");
            try {
                controller.writeDataToFile(filePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JButton Back = new JButton("Back");
        Back.setSize(120, 50);
        gameFrame1.add(Back);
        Back.addActionListener(e -> {
            System.out.println("click Back Btn");
            onBackClicked();
        });

        JButton prompt = new JButton("p"+"\nr"+"\no"+"\nm"+"\np"+"\nt");
        prompt.setSize(150, 80);
        gameFrame1.add(prompt);
        prompt.addActionListener(e -> {
            System.out.println("click prompt Btn");
            GameFrame.controller.prompt();
        });

        JButton intro = new JButton("游"+"\n戏"+"\n起"+"\n源");
        intro.setSize(150, 80);
        gameFrame1.add(intro);
        intro.addActionListener(e -> {
            System.out.println("click prompt Btn");
            Intro intro1 = new Intro();
            try {
                intro1.init();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });

        JButton newGame = new JButton("N"+"\ne"+"\nw"+"\n "+"\nG"+"\na"+"\nm"+"\ne");
        newGame.setSize(150, 80);
        gameFrame1.add(newGame);
        newGame.addActionListener(e -> {
            System.out.println("click newGame Btn");
            gameFrame1.dispose();
            InputGame inputGame = new InputGame();
            try {
                inputGame.init(fPlayerNames.size()-1,fPlayerNames);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });



        //填加背景
        //组装计分板
        Box scoreBoard = Box.createVerticalBox();

        //组装标题
        Box tBox1 = Box.createHorizontalBox();
        JLabel title = new JLabel("黑白棋大挑战");
        title.setFont(new Font("华文行楷", Font.BOLD, 42));
        tBox1.add(title);
        tBox1.setAlignmentX(Component.CENTER_ALIGNMENT);


        //组装“玩家序号”  “玩家姓名”  “分数”
        Box col1 = Box.createVerticalBox();
        Box c1tBox1 = Box.createHorizontalBox();
        JLabel title2 = new JLabel("玩家序号");
        title2.setFont(new Font("华文行楷", Font.BOLD, 30));
        c1tBox1.add(title2);
        col1.add(c1tBox1);
        col1.add(Box.createVerticalStrut(30));

        for(int i=0;i<fPlayerNames.size();i++){
            Box iBox = Box.createHorizontalBox();
            JLabel iLabel = new JLabel(String.format("%d",i));
            iLabel.setFont(new Font("宋体", Font.BOLD, 18));
            iBox.add(iLabel);
            col1.add(iBox);
            col1.add(Box.createVerticalStrut(30));
        }


        //组装“玩家序号”  “玩家姓名”  “分数”
        Box col2 = Box.createVerticalBox();
        Box c2tBox1 = Box.createHorizontalBox();
        JLabel title3 = new JLabel("玩家名称");
        title3.setFont(new Font("华文行楷", Font.BOLD, 30));
        c2tBox1.add(title3);
        col2.add(c2tBox1);
        col2.add(Box.createVerticalStrut(30));

        for(int i=0;i<fPlayerNames.size();i++){
            Box iBox = Box.createHorizontalBox();
            JLabel iLabel = new JLabel(String.format("%s",fPlayerNames.get(i)));
            iLabel.setFont(new Font("宋体", Font.BOLD, 18));
            iBox.add(iLabel);
            col2.add(iBox);
            col2.add(Box.createVerticalStrut(30));
        }


//        score = ChessGridComponent.getScore();
        //组装“玩家序号”  “玩家姓名”  “分数”
        Box col3 = Box.createVerticalBox();
        Box c3tBox1 = Box.createHorizontalBox();
        JLabel title4 = new JLabel("获胜次数");
        title4.setFont(new Font("华文行楷", Font.BOLD, 30));
        c3tBox1.add(title4);
        col3.add(c3tBox1);
        col3.add(Box.createVerticalStrut(30));

        for(int i=0;i<fPlayerNames.size();i++){
            Box iBox = Box.createHorizontalBox();

            JLabel iLabel = new JLabel(String.format("%d", InputPlayer.score[i]));
            iLabel.setFont(new Font("宋体", Font.BOLD, 18));
            iBox.add(iLabel);
            col3.add(iBox);
            col3.add(Box.createVerticalStrut(30));
        }

        Box col = Box.createHorizontalBox();
        col.add(col1);
        col.add(Box.createHorizontalStrut(30));
        col.add(col2);
        col.add(Box.createHorizontalStrut(30));
        col.add(col3);

        scoreBoard.add(tBox1);
        scoreBoard.add(Box.createVerticalStrut(70));
        scoreBoard.add(col);
        scoreBoard.setSize(700, 600);
        scoreBoard.setVisible(true);
        scoreBoard.setAlignmentY(Component.TOP_ALIGNMENT);
        scoreBoard.setAlignmentX(Component.LEFT_ALIGNMENT);
        scoreBoard.setLocation((int)(windowWidth*0.03),
                ((windowHeight - chessBoardPanel.getHeight()) / 3));
        gameFrame1.add(scoreBoard);

        gameFrame1.add(backgroundLabel);

        gameFrame1.setVisible(true);
        gameFrame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        gameFrame1.addComponentListener(new ComponentAdapter() {//拖动窗口监听
            public void componentResized(ComponentEvent e) {
                windowWidth=gameFrame1.getWidth();//获取窗口宽度
                windowHeight=gameFrame1.getHeight();//获取窗口高度  你也可以设置高度居中
                double ratioWidth = (double)(windowWidth/Width);
                double ratioHeight = (double)(windowHeight/Height);

                scoreBoard.setLocation((int)(windowWidth*0.03*ratioWidth),
                        (int)( ((windowHeight - chessBoardPanel.getHeight()) / 3) *ratioHeight));

                chessBoardPanel.setLocation((windowWidth - chessBoardPanel.getWidth()) / 7*5,
                        (windowHeight - chessBoardPanel.getHeight()) / 3);
                statusPanel.setLocation((windowWidth - chessBoardPanel.getWidth()) / 7*5, 0);
                restartBtn.setLocation((windowWidth - chessBoardPanel.getWidth()) / 7*5+30,
                        (windowHeight + chessBoardPanel.getHeight()) / 2);
                loadGameBtn.setLocation(restartBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
                saveGameBtn.setLocation(loadGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
                Back.setLocation(saveGameBtn.getX()+restartBtn.getWidth()+30, restartBtn.getY());
                prompt.setLocation((windowWidth - chessBoardPanel.getWidth()) / 7*5 + chessBoardPanel.getWidth() +40,
                        (windowHeight - prompt.getHeight()) / 2);
                newGame.setLocation((windowWidth - chessBoardPanel.getWidth()) / 7*5 + chessBoardPanel.getWidth() +40,
                        (windowHeight - prompt.getHeight()) / 2+100);
                intro.setLocation((windowWidth - chessBoardPanel.getWidth()) / 7*5 + chessBoardPanel.getWidth() +40,
                        (windowHeight - prompt.getHeight()) / 2-350);


                //设置背景
                backgroundIcon.setImage(backgroundIcon.getImage().getScaledInstance(windowWidth, windowHeight,Image.SCALE_DEFAULT));
                backgroundLabel.setSize(windowWidth,windowHeight);
                backgroundLabel.setLocation(0,0);

            }

        });


    }

    public void onRestartClicked(){
        GameFrame.controller.setGamePanel(chessBoardPanel);
        GameFrame.controller.Restart();
    }


    public void onBackClicked(){
        GameFrame.controller.Back();
    }


}


