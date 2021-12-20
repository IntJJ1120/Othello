package view;

import model.ChessPiece;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class StatusPanel extends JPanel {
    private JLabel playerLabel;
    private JLabel scoreLabel;
    private JLabel winner;
    private JComboBox pattern;
    private String BlackPlayer;
    private String WhitePlayer;

    public String getBlackPlayer(){
        return this.BlackPlayer;
    }
    public String getWhitePlayer(){
        return this.WhitePlayer;
    }


    public int k;
    public StatusPanel(int width, int height, ArrayList<String> fPlayerNames, int BlackIndex, int WhiteIndex) {
        BlackPlayer=fPlayerNames.get(BlackIndex);
        WhitePlayer=fPlayerNames.get(WhiteIndex);

        this.setSize(width, height);
        this.setLayout(null);
        this.setVisible(true);

        this.playerLabel = new JLabel();
        this.playerLabel.setLocation(0, 10);
        this.playerLabel.setSize((int) (width * 0.4), height);
        this.playerLabel.setFont(new Font("方正卡通简体", Font.BOLD, 30));
        this.setPlayerText(BlackPlayer);
        add(playerLabel);

        this.scoreLabel = new JLabel();
        this.scoreLabel.setLocation((int) (width * 0.35), 18);
        this.scoreLabel.setSize((int) (width * 0.5), height);
        this.scoreLabel.setFont(new Font("宋体", Font.ITALIC, 20));
        this.setScoreText(2,2);
        add(scoreLabel);

        this.winner=new JLabel();
        this.winner.setLocation((int) (width * 0.40), 0);
        this.winner.setSize((int) (width * 0.5), height);
        this.winner.setFont(new Font("宋体", Font.BOLD, 20));
        this.setWinnerText("Running");
        add(winner);

        this.pattern=new JComboBox();
        this.pattern.setLocation((int) (width * 0.78), 25);
        this.pattern.setSize((int) (width * 0.2), (int)(height*0.4));
        pattern.addItem("正常模式");
        pattern.addItem("任性模式");
        pattern.addItem("人机模式简单");
        pattern.addItem("人机模式正常");
        pattern.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent event)
            {
                switch (event.getStateChange())
                {case ItemEvent.SELECTED:
                    System.out.println("选中" + event.getItem());
                    if(event.getItem().equals("正常模式"))
                        k=0;
                    else if(event.getItem().equals("任性模式"))
                        k=1;
                    else if(event.getItem().equals("人机模式简单"))
                        k=2;
                    else if(event.getItem().equals("人机模式正常"))
                        k=3;
                    break;
                    case ItemEvent.DESELECTED:
                        System.out.println("取消选中"+event.getItem());
                        break;
                }
            }
        });
        add(pattern);
    }
    public void setScoreText(int black, int white) {
        this.scoreLabel.setText(String.format("BLACK: %d\t   WHITE: %d", black, white));
    }
    public void setPlayerText(String playerText) {
        this.playerLabel.setText(playerText + "'s turn");
    }
    public void setWinnerText(String winner) {
        this.winner.setText(winner);
    }
}
