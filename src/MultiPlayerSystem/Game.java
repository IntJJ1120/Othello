package MultiPlayerSystem;

import java.util.Arrays;
import java.util.ArrayList;

public class Game {
    private int gid;
    private String name;
    private static int gameCnt=1;
    private Player whitePlayer;
    private Player blackPlayer;
    private ArrayList<Step> stepList=new ArrayList<>(0);
    private int[][] board=new int[8][8];
    public Game(String name, Player whitePlayer, Player blackPlayer){
        // Constructor, automatically generate game ID and Game object according to the name provided by the user. Initialize white player, black player, stepList, and board.
        // For a list with no elements in it, its size should be 0 and its reference should not be null.
        // For a board that has not to be set:
        // 	1. Its size should be 8*8.
        //  2. board[3][3] and board[4][4] should be white(1). board[3][4] and board[4][3] should be black(-1)
        //  3. Its reference should not be null.
        this.whitePlayer=whitePlayer;
        this.blackPlayer=blackPlayer;
        this.name=name;
        gid=gameCnt;
        gameCnt++;
        this.board=new int[8][8];
        board[3][3]=1;
        board[4][4]=1;
        board[3][4]=-1;
        board[4][3]=-1;
    }

    public int getGid(){
        return this.gid;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public static int getGameCnt(){
        return gameCnt;
    }
    public Player getWhitePlayer(){
        return this.whitePlayer;
    }
    public Player getBlackPlayer(){
        return this.blackPlayer;
    }
    public ArrayList<Step> getStepList(){
        return this.stepList;
    }
    public boolean checkStep(int sid){
        // If a step is not in the step list, return false.
        // Otherwise return true.
        boolean boo=false;
        for (Step step : stepList) {
            if (step.getSid() == sid) {
                boo = true;
                break;
            }
        }
        return boo;
    }

    public boolean addStep(Step step){
        // If a step is in the step list (i.e. The sid is in the step list), return false.
        // Otherwise add the step into the step list and return true.
        // You are not required to change the board in this method.
        boolean Boolean=false;
        if(stepList.contains(step)){
            Boolean=false;
        }
        else{
            stepList.add(step);
            Boolean=true;
        }
        return Boolean;
    }
    public int[][] getBoard(){
        return this.board;
    }
    public void setBoard(int[][] board){
        for(int i=0;i<8;i++){
            System.arraycopy(board[i], 0, this.board[i], 0, 8);
        }
    }

    public String toString(){
        // When print an object of this class, follow the format: "Game: %s, gid: %d, whitePlayerId: %d, blackPlayerId: %d, stepList: %s, board: %s"
        // 1. The stepList String should be in the format: "[sid: x, rowIndex: x, columnIndex: x, color: x, sid: x, rowIndex: x, columnIndex: x, color: x, ...]"
        // 2. The board string should be in the format: "[[x, x, x, x, x, x, x, x], [x, x, x, x, x, x, x, x], ...]"
        // 3. Without quotes
        return String.format("Game: %s, gid: %d, whitePlayerId: %d, blackPlayerId: %d, stepList: %s, board: %s"
                ,this.name,this.gid,whitePlayer.getPid(),blackPlayer.getPid(),
                stepList,Arrays.deepToString(board));
    }
}