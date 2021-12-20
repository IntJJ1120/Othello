package components;

import Begin.*;
import view.ChessBoardPanel;
import Music.CanClickMusic;
import Music.CannotClickMusic;
import model.*;
import view.GameFrame;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;


public class ChessGridComponent extends BasicComponent {
    public static int chessSize;
    public static int gridSize;
    public static Color gridColor = new Color(182, 253, 117);
    public static ArrayList<String> Step;
    private ChessPiece chessPiece;
    private int row;
    private int col;
    public int time= 30; //60秒倒计时
    public boolean running=true;//是否一直运行
    public boolean running2=true;
    public boolean running3=true;
    public Thread athread;
    public static int i=0;
    public int w=0;
    public String winnerName;

    public ChessGridComponent(int row, int col) {
        this.setSize(gridSize, gridSize);
        this.row = row;
        this.col = col;

    }
    public void startTimer(){
        athread=new Thread(() -> {
            time=31;
            int j=ChessBoardPanel.Row.size();
            int w=0;
            while(running&w==0){
                try{
                    Thread.sleep(1000);
                }catch(Exception e){}
                w=j-i;
                time--;
                if(w==0)
                    GameFrame.controller.getStatusPanel().setWinnerText("       "+String.valueOf(time));
                if(time<=0){//倒计时到零，满足条件
                    GameFrame.controller.removePrompt();
                    running=false;//记得置成false否则不退出
                    try{
                        Thread.sleep(1000);
                    }catch(Exception e){}
                    GameFrame.controller.getStatusPanel().setWinnerText("0");
                    GameFrame.controller.swap();
                    time=31;
                    while(running2&w==0) {
                        try {
                            Thread.sleep(1000);
                        } catch (Exception e) {
                        }
                        time--;
                        w=j-i;
                        if(w==0)
                            GameFrame.controller.getStatusPanel().setWinnerText("       "+String.valueOf(time));
                        if(time<=0) {//倒计时到零，满足条件
                            GameFrame.controller.removePrompt();
                            running2 = false;//记得置成false否则不退出}
                            GameFrame.controller.getStatusPanel().setWinnerText("0");
                            GameFrame.controller.swap();
                            time=31;
                            while(running3&w==0) {
                                try {
                                    Thread.sleep(1000);
                                } catch (Exception e) {
                                }
                                time--;
                                w=j-i;
                                if(w==0)
                                    GameFrame.controller.getStatusPanel().setWinnerText("       "+String.valueOf(time));
                                if (time <= 0) {//倒计时到零，满足条件
                                    GameFrame.controller.removePrompt();
                                    running3= false;//记得置成false否则不退出}
                                    GameFrame.controller.getStatusPanel().setWinnerText("0");
                                    GameFrame.controller.swap();
                                }
                            }
                        }
                    }
                }
            }
        });
        athread.start();
    }

    @Override
    public void onMouseClicked() {

        System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);

        if (GameFrame.controller.canClick(row, col)) {
            GameFrame.controller.removePrompt();

        }
        if(GameFrame.controller.canClick(row, col)){
            new CanClickMusic().start();
        }
        else{
            new CannotClickMusic().start();
        }
        if(!GameFrame.controller.canContinue()){
            GameFrame.controller.getStatusPanel().setWinnerText("换人");
            GameFrame.controller.swap();
            if(!GameFrame.controller.canContinue()){
                ChessGridComponent.i=0;
                GameFrame.controller.getStatusPanel().setWinnerText(GameFrame.controller.compareNumberGetWinner()+"  WIN");
                winnerName=GameFrame.controller.compareNumberGetWinner();
                for(int i=0;i<InputPlayer.fPlayerNames.size();i++){
                    if(InputPlayer.fPlayerNames.get(i)==winnerName){
                        InputPlayer.score[i]++;
                        System.out.println("0");
                    }
                }


            }
        }
        if (GameFrame.controller.canClick(row, col)) {
            Step=new ArrayList<>();
            if (this.chessPiece == null||this.chessPiece ==ChessPiece.PROMPT) {
                System.out.printf("%s clicked (%d, %d)\n", GameFrame.controller.getCurrentPlayer(), row, col);
                if (GameFrame.controller.getStatusPanel().k == 0) {

                    if (GameFrame.controller.canClick(row, col)) {
                        ChessBoardPanel.Row.add(String.valueOf(row));
                        ChessBoardPanel.Col.add(String.valueOf(col));
                        i++;
                    }
                    this.startTimer();
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();
                    for (int q = 1; q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q].getChessPiece() ==
                                ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = col; e < col + q + 1; e++) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                                    Step.add(String.valueOf(row)+" "+String.valueOf(e));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = row; e < row + q + 1; e++) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                                    Step.add(String.valueOf(e)+" "+String.valueOf(col));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < row; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = row; e > row - q - 1; e--) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                                    Step.add(String.valueOf(e)+" "+String.valueOf(col));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < col; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q - 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = col; e > col - q - 1; e--) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                                    Step.add(String.valueOf(row)+" "+String.valueOf(e));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < col & q < row; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col - q - 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = col;
                                int r = row;
                                while (e > col - q - 1 & r > row - q - 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[r][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(r)+" "+String.valueOf(e));
                                    e--;
                                    r--;
                                    GameFrame.controller.getGamePanel().getChessGrids()[r][e].repaint();

                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e < q + row + 1 & r < q + col + 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(e)+" "+String.valueOf(r));
                                    e++;
                                    r++;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();

                                }
                                repaint();
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1 & q < col; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col - 1 - q].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e < q + 1 + row & r > col - q - 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(e)+" "+String.valueOf(r));
                                    e++;
                                    r--;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();

                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < row & q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e > row - q - 1 & r < q + 1 + col) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(e)+" "+String.valueOf(r));
                                    e--;
                                    r++;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();

                                }
                            }
                        } else {
                            break;
                        }
                    }
                    GameFrame.controller.swapPlayer();



                    if(GameFrame.controller.sum()==64){
                        GameFrame.controller.getStatusPanel().setWinnerText(GameFrame.controller.compareNumberGetWinner()+"  WIN!");
                        GameFrame.controller.getStatusPanel().setPlayerText("NO ONE");
                        ChessGridComponent.i=0;

                        for(int i=0;i<InputPlayer.fPlayerNames.size();i++){
                            if(InputPlayer.fPlayerNames.get(i)==winnerName){
                                InputPlayer.score[i]++;
                                System.out.println("1");
                            }
                        }
                    }
                }
            }
        }
        if (this.chessPiece == null||this.chessPiece ==ChessPiece.PROMPT) {
            if (GameFrame.controller.getStatusPanel().k == 1) {
                ChessBoardPanel.Row.add(String.valueOf(row));
                ChessBoardPanel.Col.add(String.valueOf(col));
                i++;
                this.startTimer();
                this.chessPiece = GameFrame.controller.getCurrentPlayer();
                for (int q = 1; q < 8 - col - 1; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q].getChessPiece() ==
                            ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q + 1].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            for (int e = col; e < col + q + 1; e++) {
                                GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < 8 - row - 1; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            for (int e = row; e < row + q + 1; e++) {
                                GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < row; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            for (int e = row; e > row - q - 1; e--) {
                                GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < col; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q - 1].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            for (int e = col; e > col - q - 1; e--) {
                                GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < col & q < row; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col - q - 1].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            int e = col;
                            int r = row;
                            while (e > col - q - 1 & r > row - q - 1) {
                                GameFrame.controller.getGamePanel().getChessGrids()[r][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                e--;
                                r--;
                                GameFrame.controller.getGamePanel().getChessGrids()[r][e].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col + q + 1].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            int e = row;
                            int r = col;
                            while (e < q + row + 1 & r < q + col + 1) {
                                GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                e++;
                                r++;
                                GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                            }

                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < 8 - row - 1 & q < col; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col - 1 - q].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            int e = row;
                            int r = col;
                            while (e < q + 1 + row & r > col - q - 1) {

                                GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                e++;
                                r--;
                                GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }
                for (int q = 1; q < row & q < 8 - col - 1; q++) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col + q + 1].getChessPiece()
                                == GameFrame.controller.getCurrentPlayer()) {
                            int e = row;
                            int r = col;
                            while (e > row - q - 1 & r < q + 1 + col) {
                                GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                e--;
                                r++;
                                GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                            }
                        }
                    } else {
                        break;
                    }
                }

                GameFrame.controller.swapPlayer();



                if(GameFrame.controller.sum()==64){
                    GameFrame.controller.getStatusPanel().setWinnerText(GameFrame.controller.compareNumberGetWinner()+"  WIN!");
                    GameFrame.controller.getStatusPanel().setPlayerText("NO ONE");
                    ChessGridComponent.i=0;
                    for(int i=0;i<InputPlayer.fPlayerNames.size();i++){
                        if(InputPlayer.fPlayerNames.get(i)==winnerName){
                            InputPlayer.score[i]++;
                            System.out.println("2");
                        }
                    }
                }
            }}
        repaint();



        //Maininterface.mainFrame.controller.canContinue()

        if (GameFrame.controller.canClick(row, col)) {
            if (this.chessPiece == null || this.chessPiece == ChessPiece.PROMPT) {
                if (GameFrame.controller.getStatusPanel().k == 2) {

//                this.startTimer();

                    if (GameFrame.controller.canClick(row, col)) {
                        ChessBoardPanel.Row.add(String.valueOf(row));
                        ChessBoardPanel.Col.add(String.valueOf(col));
//                    i++;
                    }
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();
                    for (int q = 1; q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q].getChessPiece() ==
                                ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = col; e < col + q + 1; e++) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = row; e < row + q + 1; e++) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < row; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = row; e > row - q - 1; e--) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < col; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q - 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = col; e > col - q - 1; e--) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < col & q < row; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col - q - 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = col;
                                int r = row;
                                while (e > col - q - 1 & r > row - q - 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[r][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    e--;
                                    r--;
                                    GameFrame.controller.getGamePanel().getChessGrids()[r][e].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e < q + row + 1 & r < q + col + 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    e++;
                                    r++;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                                }

                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1 & q < col; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col - 1 - q].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e < q + 1 + row & r > col - q - 1) {

                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    e++;
                                    r--;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < row & q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e > row - q - 1 & r < q + 1 + col) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    e--;
                                    r++;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    GameFrame.controller.swap();


                    int rowa = GameFrame.controller.promptRow().get(1);
                    int colb = GameFrame.controller.promptCol().get(1);
                    GameFrame.controller.Do(rowa, colb);
                    GameFrame.controller.getGamePanel().repaint();

                    if (GameFrame.controller.sum() == 64) {
                        i = 0;
                        GameFrame.controller.getStatusPanel().setWinnerText(GameFrame.controller.compareNumberGetWinner() + "  WIN!");
                        GameFrame.controller.getStatusPanel().setPlayerText("NO ONE");
                        for(int i=0;i<InputPlayer.fPlayerNames.size();i++){
                            if(InputPlayer.fPlayerNames.get(i)==winnerName){
                                InputPlayer.score[i]++;
                                System.out.println("3");
                            }
                        }
                    }
                    GameFrame.controller.swapPlayer();

                }
            }
        }
        //Maininterface.mainFrame.controller.canContinue()
        if (GameFrame.controller.canClick(row, col)) {
            if (this.chessPiece == null || this.chessPiece == ChessPiece.PROMPT) {
                if (GameFrame.controller.getStatusPanel().k == 3) {
                    Step = new ArrayList<>();
                    this.chessPiece = GameFrame.controller.getCurrentPlayer();
                    for (int q = 1; q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q].getChessPiece() ==
                                ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = col; e < col + q + 1; e++) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                                    Step.add(String.valueOf(row) + " " + String.valueOf(e));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = row; e < row + q + 1; e++) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                                    Step.add(String.valueOf(e) + " " + String.valueOf(col));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < row; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = row; e > row - q - 1; e--) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][col].repaint();
                                    Step.add(String.valueOf(e) + " " + String.valueOf(col));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < col; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row][col - q - 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                for (int e = col; e > col - q - 1; e--) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    GameFrame.controller.getGamePanel().getChessGrids()[row][e].repaint();
                                    Step.add(String.valueOf(row) + " " + String.valueOf(e));
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < col & q < row; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col - q - 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = col;
                                int r = row;
                                while (e > col - q - 1 & r > row - q - 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[r][e].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(r) + " " + String.valueOf(e));
                                    e--;
                                    r--;
                                    GameFrame.controller.getGamePanel().getChessGrids()[r][e].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e < q + row + 1 & r < q + col + 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(e) + " " + String.valueOf(r));
                                    e++;
                                    r++;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                                }

                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < 8 - row - 1 & q < col; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row + q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row + q + 1][col - 1 - q].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e < q + 1 + row & r > col - q - 1) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(e) + " " + String.valueOf(r));
                                    e++;
                                    r--;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    for (int q = 1; q < row & q < 8 - col - 1; q++) {
                        if (GameFrame.controller.getGamePanel().getChessGrids()[row - q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                            if (GameFrame.controller.getGamePanel().getChessGrids()[row - q - 1][col + q + 1].getChessPiece()
                                    == GameFrame.controller.getCurrentPlayer()) {
                                int e = row;
                                int r = col;
                                while (e > row - q - 1 & r < q + 1 + col) {
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].chessPiece = GameFrame.controller.getCurrentPlayer();
                                    Step.add(String.valueOf(e) + " " + String.valueOf(r));
                                    e--;
                                    r++;
                                    GameFrame.controller.getGamePanel().getChessGrids()[e][r].repaint();
                                }
                            }
                        } else {
                            break;
                        }
                    }

                    GameFrame.controller.swapPlayer();
                    ArrayList<Integer>promptRow=GameFrame.controller.promptRow();
                    ArrayList<Integer>promptCol=GameFrame.controller.promptCol();
                    int rowa=promptRow.get(GameFrame.controller.BestTimes());
                    int colb=promptCol.get(GameFrame.controller.BestTimes());
                    GameFrame.controller.Do(rowa,colb);
                    GameFrame.controller.getGamePanel().repaint();
                    if (GameFrame.controller.sum() == 64) {
                        GameFrame.controller.getStatusPanel().setWinnerText(GameFrame.controller.compareNumberGetWinner() + "  WIN!");
                        GameFrame.controller.getStatusPanel().setPlayerText("NO ONE");
                    }
                    GameFrame.controller.swapPlayer();
                    for(int i=0;i<InputPlayer.fPlayerNames.size();i++){
                        if(InputPlayer.fPlayerNames.get(i)==winnerName){
                            InputPlayer.score[i]++;
                            System.out.println("4");
                        }
                    }

                }
            }
        }
    }





    public ChessPiece getChessPiece() {
        return chessPiece;
    }

    public void setChessPiece(ChessPiece chessPiece) {
        this.chessPiece = chessPiece;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void drawPiece(Graphics g) {
        g.setColor(gridColor);
        g.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        if (this.chessPiece ==ChessPiece.BLACK||this.chessPiece ==ChessPiece.WHITE) {
            g.setColor(chessPiece.getColor());
            g.fillOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
        else if(this.chessPiece==ChessPiece.PROMPT){
            g.setColor(Color.BLACK);
            g.drawOval((gridSize - chessSize) / 2, (gridSize - chessSize) / 2, chessSize, chessSize);
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.printComponents(g);
        drawPiece(g);
    }

//    public static int[] getScore(){
//        return score;
//    }

}
