package MultiPlayerSystem;

public class Player {
    private int pid;        // Player ID. Each player’s player ID is unique and is automatically generated by the class Player. Starting from 1, each new player’s ID will increase by 1.
    private String name;        // Player name.
    private static int playerCnt=1;     // Initialized to 1, increased by 1 every time a Player object is created.
    public Player(String name){
        // Constructor, automatically generate player ID and player object according to the name provided by the user.
        this.name=name;
        pid=playerCnt;
        playerCnt++;
    }
    public int getPid(){
        return this.pid;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public static int getPlayerCnt(){
        return playerCnt;
    }
    public String toString(){
        return String.format("Player: %s, pid: %d",this.name,this.pid);
    }
}
