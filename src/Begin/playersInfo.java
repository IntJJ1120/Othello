package Begin;

import java.util.ArrayList;

public class playersInfo {
    private int playersNum;
    private ArrayList<String> playersName= new ArrayList<>(0);
    private int [] score= new int[playersNum];

    public playersInfo(int playersNum, ArrayList<String> playersName){
        this.playersNum=playersNum;
        this.playersName=playersName;
    }

    public int getPlayersNum(){
        return this.playersNum;
    }

    public void setPlayersNum(int playersNum){
        this.playersNum=playersNum;
    }

    public ArrayList<String> getPlayersName(){
        return this.playersName;
    }

    public void setPlayersName(ArrayList<String> playersName){
        this.playersName=playersName;
    }
    public int[] getScore() {
        return this.score;
    }

    public void setScore(int[] score) {
        this.score = score;
    }

}
