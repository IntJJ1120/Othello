package controller;

import Begin.*;
import components.ChessGridComponent;
import model.ChessPiece;
import view.*;


import javax.swing.*;
import javax.swing.plaf.synth.SynthEditorPaneUI;
import javax.swing.plaf.synth.SynthRadioButtonMenuItemUI;
import java.io.*;
import java.util.ArrayList;
import java.util.List;



public class GameController {
    private ChessBoardPanel gamePanel;
    private StatusPanel statusPanel;
    private ChessPiece currentPlayer;
    private String currentPlayerName;
    private int blackScore;
    private int whiteScore;
    public Thread aThread;
    private boolean [] booleans=new boolean[10];
    private final String filePath="E:\\南科大\\课程 专业 导师\\大二上 21秋\\JAVA\\黑白棋4.0\\";
    public int [][] board2 = new int[8][8];
//    public int readTime=0;


    public boolean[] getBooleans() {
        return booleans;
    }

    public ChessPiece getcurrentPlayer(){
        return this.currentPlayer;
    }
    public int sum(){
        return blackScore+whiteScore;
    }
    public GameController(ChessBoardPanel gamePanel, StatusPanel statusPanel) {
        this.gamePanel = gamePanel;
        this.statusPanel = statusPanel;
        this.currentPlayer = ChessPiece.BLACK;
        blackScore = 2;
        whiteScore = 2;

    }

    public void swapPlayer() {
        blackScore =0;
        whiteScore =0;
        countScore();
        currentPlayer = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        currentPlayerName=(currentPlayer == ChessPiece.BLACK) ? statusPanel.getBlackPlayer() : statusPanel.getWhitePlayer();
        statusPanel.setPlayerText(currentPlayerName);
        statusPanel.setScoreText(blackScore, whiteScore);
    }

    public void swap() {System.out.println("改变了棋子");
        this.currentPlayer = (this.currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        currentPlayerName=(currentPlayer == ChessPiece.BLACK) ? statusPanel.getBlackPlayer() : statusPanel.getWhitePlayer();
        this.statusPanel.setPlayerText(currentPlayerName);
    }


    public void Restart(){
        ChessBoardPanel.Row=new ArrayList<String>();
        ChessBoardPanel.Col=new ArrayList<String>();
        this.currentPlayer = ChessPiece.BLACK;
        ChessGridComponent.i=0;
        blackScore = 2;
        whiteScore = 2;
        statusPanel.setPlayerText(currentPlayer.name());
        statusPanel.setScoreText(blackScore, whiteScore);
        statusPanel.setWinnerText("Running");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.gamePanel.getChessGridComponent()[i][j].setChessPiece(null);
                this.gamePanel.getChessGridComponent()[i][j].repaint();
            }
        }
        this.gamePanel.getChessGridComponent()[3][3].setChessPiece(ChessPiece.WHITE);
        this.gamePanel.getChessGridComponent()[3][4].setChessPiece(ChessPiece.BLACK);
        this.gamePanel.getChessGridComponent()[4][3].setChessPiece(ChessPiece.BLACK);
        this.gamePanel.getChessGridComponent()[4][4].setChessPiece(ChessPiece.WHITE);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.gamePanel.getChessGridComponent()[i][j].repaint();
            }
        }
    }

    public void countScore() {
        for(int i=0;i<8;i++) {
            for (int j = 0; j < 8; j++) {
                if(this.getGamePanel().getChessGridComponent()[i][j].getChessPiece()==ChessPiece.BLACK){
                    blackScore++;
                }
                else if(this.getGamePanel().getChessGridComponent()[i][j].getChessPiece()==ChessPiece.WHITE){
                    whiteScore++;
                }
            }
        }
    }

    public String compareNumberGetWinner(){
        String winnerName;
        if(this.blackScore>this.whiteScore){
            winnerName = statusPanel.getBlackPlayer();
            for(int i = 0; i< InputPlayer.fPlayerNames.size(); i++){
                if(InputPlayer.fPlayerNames.get(i)==statusPanel.getBlackPlayer()){
                    InputPlayer.score[i]++;
                    System.out.println("Black win");
                }
            }
            JOptionPane.showMessageDialog(null,(winnerName+"   win!"));
            return(statusPanel.getBlackPlayer());
        }
        else if(this.blackScore<this.whiteScore){
            for(int i = 0; i< InputPlayer.fPlayerNames.size(); i++){
                if(InputPlayer.fPlayerNames.get(i)==statusPanel.getWhitePlayer()){
                    InputPlayer.score[i]++;
                    System.out.println("White win");
                }
            }
            winnerName = statusPanel.getWhitePlayer();
            JOptionPane.showMessageDialog(null,(winnerName+"   win!"));
            return(statusPanel.getWhitePlayer());
        }
        else{
            winnerName = "EQUAL ";
            JOptionPane.showMessageDialog(null,(winnerName+"   win!"));
            return("EQUAL ");
        }

    }

    public ChessPiece getCurrentPlayer() {
        return currentPlayer;
    }

    public ChessBoardPanel getGamePanel() {
        return gamePanel;
    }
    public StatusPanel getStatusPanel() {
        return statusPanel;
    }


    public void setGamePanel(ChessBoardPanel gamePanel) {
        this.gamePanel = gamePanel;
    }


    public void readFileData(String fileName) {
        //todo: read data from file
        boolean boo1=false;
        boolean boo2=false;
        boolean boo3=false;
        boolean boo4=false;
        boolean boo5=false;
        boolean boo6=false;
        boolean boo7=false;

        ChessGridComponent.i=0;
        if(!fileName.endsWith("txt")){
            boo4=true;
            booleans [4]=boo4;
            System.out.println(fileName);
        }

        int[][] Board0 = new int[8][8];
        ArrayList<Integer> StepRow = new ArrayList<>();
        ArrayList<Integer> StepCol = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(filePath+fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String line2 = bufferedReader.readLine();
//            readTime=Integer.parseInt(line2);

        int Row1 = 0;
        int Col1 = 0;
        String lastMove = null;
        ChessPiece lastPlayer=ChessPiece.BLACK;
        ArrayList<String []> allStep=new ArrayList<>(0);
            for (int i = 1; i < Integer.parseInt(line2) + 12; i++) {
                if(boo4){
                    bufferedReader.close();
                    break;
                 }
                boolean boo21=false;
                boolean boo22=false;
                boolean boo23=false;
                if (i < 9) {
                    line = bufferedReader.readLine();
                    String[] Board = line.split(" ");
                    if (Board.length!=8){
                        boo1=true;
                        bufferedReader.close();
                        break;
                    }

//                    for(String a : Board){
//                        if(a.equals("0")){
//                            boo21=true;
//                            break;
//                        }
//                    }
//                    for(String a : Board){
//                        if(a.equals("1")){
//                            boo22=true;
//                            break;
//                        }
//                    }
//                    for(String a : Board){
//                        if(a.equals("-1")){
//                            boo23=true;
//                            break;
//                        }
//                    }
                    for(String a : Board){
                        if(!a.equals("0")&&!a.equals("1")&&!a.equals("-1")){
                            boo2=true;
                            break;
                        }
                    }
                }

                if(i==8) {
//                    if (!boo21 && !boo22 && !boo23) {
//                        System.out.println(boo21);
//                        System.out.println(boo21);
//                        System.out.println(boo21);
//                        boo2 = true;
//                    }
                    if (boo2) {
                        bufferedReader.close();
                        break;
                    }
                }
                if (i == 9) {
                    line = bufferedReader.readLine();
                    if(!line.equals("BLACK")&&!line.equals("WHITE")){
                        boo3=true;
                        bufferedReader.close();
                        break;
                    }
                    currentPlayer = ChessPiece.valueOf(line);
                    statusPanel.setPlayerText(line);
                }
                if(i==10){
                    line = bufferedReader.readLine();
                    if(line.equals("WHITE")){
                        lastPlayer=ChessPiece.WHITE;
                    }
                    if(line.equals("BLACK")){
                        lastPlayer=ChessPiece.BLACK;
                    }
                }
                if(i==11){
                    line=bufferedReader.readLine();
                    statusPanel.k=Integer.parseInt(line);
                }
                if(i==12){
                    line = bufferedReader.readLine();
                    lastMove=line;
                    String[] Board = line.split(" ");
                    Row1=Integer.parseInt(Board[0]);
                    Col1=Integer.parseInt(Board[1]);
                }


                if (i >= 12 & i <= 11 + Integer.parseInt(line2)) {
                    line = bufferedReader.readLine();
                    String[] Step = line.split(" ");
                    allStep.add(Step);
                    ChessBoardPanel.Row.add(Step[0]);
                    ChessBoardPanel.Col.add(Step[1]);
                    StepRow.add(Integer.parseInt(Step[0]));
                    StepCol.add(Integer.parseInt(Step[1]));
                }


                if (i == 11  + Integer.parseInt(line2)) {
                    if(statusPanel.k==1){
                        System.out.println("测试样例5.2");
                        System.out.println(Row1+" "+Col1);
                        System.out.println(Board0[Row1][Col1]);
                        if(Board0[Row1][Col1]!=0){
                            System.out.println("任性模式 error 5");
                            boo5=true;
                            bufferedReader.close();
                            break;
                        }
                    }
                    if(statusPanel.k==0){
                        boolean boo51=false;
                        int [][]lastBoard=LastBoard.LastBoard(allStep);
                        for(int p=0;p<8;p++) {
                            for (int q = 0; q < 8; q++) {
                                System.out.print(lastBoard[p][q] + " ");
                            }
                            System.out.println();
                        }
                        System.out.println();


                        int chess = (lastPlayer==ChessPiece.WHITE)? 1:-1;
                        System.out.println(chess);
                        System.out.println();

                        if(Carrier.ChessBoard2(lastBoard,chess)[Row1][Col1]==0){
                            boo51=true;
                        }
                        for(int p=0;p<8;p++){
                            for(int q=0;q<8;q++){
                                System.out.print(Carrier.ChessBoard2(lastBoard,chess)[p][q]+" ");
                            }
                            System.out.println();
                        }
                        if(boo51){
                            System.out.println("正常模式 error 5");
                            boo5=true;
                            bufferedReader.close();
                            Restart();
                            break;
                        }
                    }
                }
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            boo6=true;
        }
        booleans [1]=boo1;
        booleans [2]=boo2;
        booleans [3]=boo3;
        booleans [4]=boo4;
        booleans [5]=boo5;
        booleans [6]=boo6;
        booleans [7]=boo7;
        Restart();
        //不能下有任意模式的

        aThread=new Thread(() -> {
            for (int k = 0; k < StepRow.size(); k++) {
                int K = k;
                GameFrame.controller.gamePanel.getChessGridComponent()[StepRow.get(K)][StepCol.get(K)].setChessPiece(GameFrame.controller.getCurrentPlayer());
                int row = StepRow.get(K);
                int col = StepCol.get(K);
                ChessGridComponent.i++;
                GameFrame.controller.Do(row, col);
                GameFrame.controller.swap();
                try{
                    Thread.sleep(500);
                }catch(Exception e){
                    e.printStackTrace();
                }
                GameFrame.controller.getGamePanel().repaint( );
            }
        });
        if(!(boo1||boo2||boo3||boo4||boo5||boo6||boo7)){
            aThread.start();
        }
        Restart();
    }

    //            for(int i=0;i<8;i++){
//                for(int j=0;j<8;j++){
//                    if(board[i][j]==1){
//                        this.gamePanel.getChessGrids()[i][j].setChessPiece(ChessPiece.WHITE);
//                        this.gamePanel.getChessGrids()[i][j].repaint();
//                    }
//                    else if(board[i][j]==-1){
//                        this.gamePanel.getChessGrids()[i][j].setChessPiece(ChessPiece.BLACK);
//                        this.gamePanel.getChessGrids()[i][j].repaint();
//                    }
//                    else if(board[i][j]==0){
//                        this.gamePanel.getChessGrids()[i][j].setChessPiece(null);
//                        this.gamePanel.getChessGrids()[i][j].repaint();
//                    }
//
//                }
//






    public void writeDataToFile(String fileName) throws IOException {
        List<String> fileData = new ArrayList<>();
        int [][] board = new int[8][8];
        fileData.add(String.valueOf(gamePanel.Row.size()));
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(gamePanel.getChessGrids()[i][j].getChessPiece()==ChessPiece.WHITE){
                    board[i][j]=1;
                }
                else if(gamePanel.getChessGrids()[i][j].getChessPiece()==ChessPiece.BLACK){
                    board[i][j]=-1;
                }
                else{
                    board[i][j]=0;
                }
            }
            fileData.add(String.format("%d %d %d %d %d %d %d %d ",board[i][0],board[i][1],board[i][2],board[i][3],
                    board[i][4],board[i][5],board[i][6],board[i][7]));
        }
        // line 9 is the present player
        fileData.add(currentPlayer.name());
        // line 10 is the former move's player
        fileData.add((this.currentPlayer == ChessPiece.BLACK) ? "WHITE" : "BLACK");
        // line 11 is the mode "正常" or "任性"
        fileData.add(String.valueOf(statusPanel.k));
        // line 12 is the former move
        String str=String.format("%s %s",gamePanel.Row.get(gamePanel.Row.size()-1),gamePanel.Col.get(gamePanel.Col.size()-1));
        fileData.add(str);

        //
        ArrayList<String> Step=new ArrayList<>(0);
        for(int i=0;i<ChessBoardPanel.Row.size();i++) {
            fileData.add(ChessBoardPanel.Row.get(i)+" "+ChessBoardPanel.Col.get(i));
            Step.add(ChessBoardPanel.Row.get(i)+" "+ChessBoardPanel.Col.get(i));
        }
        FileWriter filewriter = new FileWriter (filePath+fileName);
        BufferedWriter bufferedWriter = new BufferedWriter(filewriter);

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(gamePanel.getChessGrids()[i][j].getChessPiece()==ChessPiece.WHITE){
                    board2[i][j]=1;
                }
                else if(gamePanel.getChessGrids()[i][j].getChessPiece()==ChessPiece.BLACK){
                    board2[i][j]=-1;
                }
                else{
                    board2[i][j]=0;
                }
            }
            fileData.add(String.format("%d %d %d %d %d %d %d %d ",board2[i][0],board2[i][1],board2[i][2],board2[i][3],
                    board2[i][4],board2[i][5],board2[i][6],board2[i][7]));
        }


        for(String a:fileData){
            bufferedWriter.write(a+"\r\n");
        }
        fileData.forEach(System.out::println);
        bufferedWriter.close();

    }

    public boolean canClick(int row, int col) {
        return gamePanel.canClickGrid(row, col, currentPlayer);
    }
    public boolean canContinue(){
        boolean c=true;
        int f=0;
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                if(this.getGamePanel().getChessGrids()[a][b].getChessPiece()==null||
                        this.getGamePanel().getChessGrids()[a][b].getChessPiece()==ChessPiece.PROMPT){
                    c=GameFrame.controller.canClick(a, b) ;
                    if(c){
                        f=1;
                        break;
                    }
                }
            } if(f==1){
                break;
            }
        }
        return c;
    }
    public void prompt(){
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                if(this.getGamePanel().getChessGrids()[a][b].getChessPiece()==null){
                    if( GameFrame.controller.canClick(a, b) ){
                        this.getGamePanel().getChessGrids()[a][b].setChessPiece(ChessPiece.PROMPT);
                        System.out.printf("%s %s\n",a, b);
                        GameFrame.controller.getGamePanel().getChessGrids()[a][b].repaint();
                    }
                }}
        }
    }
    public void removePrompt(){
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                if(this.getGamePanel().getChessGrids()[a][b].getChessPiece()==ChessPiece.PROMPT){
                    this.getGamePanel().getChessGrids()[a][b].setChessPiece(null);
                    GameFrame.controller.getGamePanel().getChessGrids()[a][b].repaint();
                }}
        }



    }


    public void Back(){
        GameFrame.controller.removePrompt();
        ChessPiece Now=GameFrame.controller.getGamePanel().getChessGrids()
                [Integer.parseInt(ChessBoardPanel.Row.get(ChessBoardPanel.Row.size()-1))]
                [Integer.parseInt(ChessBoardPanel.Col.get(ChessBoardPanel.Col.size()-1))].getChessPiece();
        ChessPiece Back=(Now==ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        if(GameFrame.controller.getGamePanel().getChessGrids()
                [Integer.parseInt(ChessBoardPanel.Row.get(ChessBoardPanel.Row.size()-1))]
                [Integer.parseInt(ChessBoardPanel.Col.get(ChessBoardPanel.Col.size()-1))].getChessPiece()
                !=null){
            for(String i: ChessGridComponent.Step) {
                String []tokens=i.split(" ");
                GameFrame.controller.getGamePanel().getChessGrids()
                        [Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])].setChessPiece(Back);
                GameFrame.controller.getGamePanel().getChessGrids()
                        [Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])].repaint();
            }
            this.currentPlayer=Now;
            GameFrame.controller.getStatusPanel().setPlayerText(Now.name());
            GameFrame.controller.getGamePanel().getChessGrids()
                    [Integer.parseInt(ChessBoardPanel.Row.get(ChessBoardPanel.Row.size()-1))]
                    [Integer.parseInt(ChessBoardPanel.Col.get(ChessBoardPanel.Col.size()-1))].setChessPiece(null);
            GameFrame.controller.getGamePanel().getChessGrids()
                    [Integer.parseInt(ChessBoardPanel.Row.get(ChessBoardPanel.Row.size()-1))]
                    [Integer.parseInt(ChessBoardPanel.Col.get(ChessBoardPanel.Col.size()-1))].repaint();
        }
        else{
            for(String i: ChessGridComponent.Step) {
                String []tokens=i.split(" ");
                GameFrame.controller.getGamePanel().getChessGrids()
                        [Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])].setChessPiece(Back);
                GameFrame.controller.getGamePanel().getChessGrids()
                        [Integer.parseInt(tokens[0])][Integer.parseInt(tokens[1])].repaint();
            }
            this.currentPlayer=Now;
            GameFrame.controller.getStatusPanel().setPlayerText(Now.name());
            GameFrame.controller.getGamePanel().getChessGrids()
                    [Integer.parseInt(ChessBoardPanel.Row.get(ChessBoardPanel.Row.size()-1))]
                    [Integer.parseInt(ChessBoardPanel.Col.get(ChessBoardPanel.Col.size()-1))].setChessPiece(null);
            GameFrame.controller.getGamePanel().getChessGrids()
                    [Integer.parseInt(ChessBoardPanel.Row.get(ChessBoardPanel.Row.size()-1))]
                    [Integer.parseInt(ChessBoardPanel.Col.get(ChessBoardPanel.Col.size()-1))].repaint();

        }
        if(ChessBoardPanel.Row.size()!=0){
            ChessBoardPanel.Row.remove(ChessBoardPanel.Row.size()-1);
            ChessBoardPanel.Col.remove(ChessBoardPanel.Col.size()-1);
            ChessGridComponent.i=ChessGridComponent.i-1;
            GameFrame.controller.getStatusPanel().setWinnerText("Running");
        }

    }


    public void Do(int row,int col){
        ChessBoardPanel.Row.add(String.valueOf(row));
        ChessBoardPanel.Col.add(String.valueOf(col));
        for (int q = 1; q < 8 - col - 1; q++) {
            if (this.gamePanel.getChessGridComponent()[row][col + q].getChessPiece() ==
                    ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row][col + q + 1].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    for (int e = col; e < col + q + 1; e++) {
                        this.gamePanel.getChessGridComponent()[row][e].setChessPiece(GameFrame.controller.getCurrentPlayer());
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < 8 - row - 1; q++) {
            if (this.gamePanel.getChessGridComponent()[row + q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row + q + 1][col].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    for (int e = row; e < row + q + 1; e++) {
                        this.gamePanel.getChessGridComponent()[e][col].setChessPiece(GameFrame.controller.getCurrentPlayer());
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < row; q++) {
            if (this.gamePanel.getChessGridComponent()[row - q][col].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row - q - 1][col].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    for (int e = row; e > row - q - 1; e--) {
                        this.gamePanel.getChessGridComponent()[e][col].setChessPiece(GameFrame.controller.getCurrentPlayer());
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < col; q++) {
            if (this.gamePanel.getChessGridComponent()[row][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row][col - q - 1].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    for (int e = col; e > col - q - 1; e--) {
                        this.gamePanel.getChessGridComponent()[row][e].setChessPiece(GameFrame.controller.getCurrentPlayer());
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < col & q < row; q++) {
            if (this.gamePanel.getChessGridComponent()[row - q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row - q - 1][col - q - 1].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    int e = col;
                    int r = row;
                    while (e > col - q - 1 & r > row - q - 1) {
                        this.gamePanel.getChessGridComponent()[r][e].setChessPiece(GameFrame.controller.getCurrentPlayer());
                        e--;
                        r--;
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
            if (this.gamePanel.getChessGridComponent()[row + q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row + q + 1][col + q + 1].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    int e = row;
                    int r = col;
                    while (e < q + row + 1 & r < q + col + 1) {
                        this.gamePanel.getChessGridComponent()[e][r].setChessPiece(GameFrame.controller.getCurrentPlayer());
                        e++;
                        r++;
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < 8 - row - 1 & q < col; q++) {
            if (this.gamePanel.getChessGridComponent()[row + q][col - q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row + q + 1][col - 1 - q].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    int e = row;
                    int r = col;
                    while (e < q + 1 + row & r > col - q - 1) {
                        this.gamePanel.getChessGridComponent()[e][r].setChessPiece(GameFrame.controller.getCurrentPlayer());
                        e++;
                        r--;
                    }
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < row & q < 8 - col - 1; q++) {
            if (this.gamePanel.getChessGridComponent()[row - q][col + q].getChessPiece() == ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                if (this.gamePanel.getChessGridComponent()[row - q - 1][col + q + 1].getChessPiece()
                        == GameFrame.controller.getCurrentPlayer()) {
                    int e = row;
                    int r = col;
                    while (e > row - q - 1 & r < q + 1 + col) {
                        this.gamePanel.getChessGridComponent()[e][r].setChessPiece(GameFrame.controller.getCurrentPlayer());
                        e--;
                        r++;
                    }
                }
            } else {
                break;
            }
        }

    }



    public int BestTimes() {
        int p;
        ArrayList<Integer>promptRow=GameFrame.controller.promptRow();
        ArrayList<Integer>promptCol=GameFrame.controller.promptCol();
        ArrayList<Integer>allNumbers=new ArrayList<>();
        int bestclick=0;
        for ( p=0;p<promptRow.size();p++){
            int number=0;
            int row=promptRow.get(p);int col=promptCol.get(p);
            for (int q = 1; q < 8 - col - 1; q++) {
                if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q].getChessPiece() ==
                        ((GameFrame.controller.getCurrentPlayer() == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK)) {
                    if (GameFrame.controller.getGamePanel().getChessGrids()[row][col + q + 1].getChessPiece()
                            == GameFrame.controller.getCurrentPlayer()) {
                        for (int e = col; e < col + q + 1; e++) {
                            number++;
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
                            number++;
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
                            number++;
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
                            number++;
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
                            number++;
                            e--;
                            r--;

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
                            number++;
                            e++;
                            r++;

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
                            number++;

                            e++;
                            r--;

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
                            number++;
                            e--;
                            r++;

                        }
                    }
                } else {
                    break;
                }
            }
            allNumbers.add(number);
        }
        for(int i:allNumbers){
            if(bestclick<i){
                bestclick=i;
            }
        }
        int o=allNumbers.indexOf(bestclick);
        return o;
    }
    public ArrayList<Integer> promptRow(){
        ArrayList<Integer>row=new ArrayList<>();
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                if(this.getGamePanel().getChessGrids()[a][b].getChessPiece()==null||this.getGamePanel().getChessGrids()[a][b].getChessPiece()==ChessPiece.PROMPT){
                    if( GameFrame.controller.canClick(a, b) ){
                        row.add(a);
                    }
                }}
        }
        return row;
    }
    public ArrayList<Integer> promptCol(){
        ArrayList<Integer>col=new ArrayList<>();
        for(int a=0;a<8;a++){
            for(int b=0;b<8;b++){
                if(this.getGamePanel().getChessGrids()[a][b].getChessPiece()==null||this.getGamePanel().getChessGrids()[a][b].getChessPiece()==ChessPiece.PROMPT){
                    if( GameFrame.controller.canClick(a, b) ){
                        col.add(b);
                    }
                }}
        }
        return col;
    }




}

