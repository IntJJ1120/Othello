package MultiPlayerSystem;

import java.util.ArrayList;
public class GameSystem {
    private ArrayList<Player> playerList=new ArrayList<>(0);       //the list of players.
    private ArrayList<Game> gameList=new ArrayList<>(0);       //the list of games.
    public GameSystem(){
        // Constructor. Initialize playerList and gameList. For a list with no elements in it, its size should be 0 and its reference should not be null.
    }
    public ArrayList<Game> getGameList(){
        return this.gameList;
    }
    public ArrayList<Player> getPlayerList(){
        return this.playerList;
    }
    public boolean checkPlayer(int pid){
        // If the player with pid is not in the player list, return false.
        // Otherwise return true.
        boolean boo=false;
        for (Player player : playerList) {
            if (player.getPid() == pid) {
                boo = true;
                break;
            }
        }
        return boo;
    }

    public boolean checkGame(int gid){
        // If the game with gid is not in the game list, return false.
        // Otherwise return true.
        boolean boo=false;
        for (Game game : gameList) {
            if (game.getGid() == gid) {
                boo = true;
                break;
            }
        }
        return boo;
    }

    public boolean addPlayer(Player player){
        // If the player is in the player list (i.e. his/her pid is in the player list), return false.
        // Otherwise add the player into the player list and return true.
        boolean boo;
        if(playerList.contains(player)){
            boo=false;
        }
        else{
            playerList.add(player);
            boo=true;
        }
        return boo;
    }

    public boolean addGame(Game game)
    // If the game is in the game list (i.e. its gid is in the game list), return false.
    // Else if the corresponding players of the game are not in playerList, return false.
    // Otherwise add the game into the game list and return true.
    {
        boolean boo;
        if(gameList.contains(game)){
            boo=false;
        }
        else if(!playerList.contains(game.getWhitePlayer())||!playerList.contains(game.getBlackPlayer())){
            boo=false;
        }
        else{
            gameList.add(game);
            boo=true;
        }
        return boo;
    }

    public ArrayList<Game> listPlayerGame(int pid) {
        // Return an ArrayList<Game> of the player with pid, the order of the games is following the game order in the gameList. Skip games the player didn't participate in.
        ArrayList<Game> newGameList = new ArrayList<>(0);
        for (Game game : gameList) {
            if (game.getWhitePlayer().getPid() == pid || game.getBlackPlayer().getPid() == pid) {
                newGameList.add(game);
            }
        }
        return  newGameList;
    }

    public float calculatePlayerWinRate(int pid) {
        float rate;
        if (listPlayerGame(pid).size()!=0) {
            // Return the win rate of the player with pid. The win rate is the ratio of total wins to total games the player has participated in.
            // If the player has not participated in any games, return 0.
            int total = listPlayerGame(pid).size();
            int win = 0;
            int black = 0;
            int white = 0;
            int blackwin = 0;
            int whitewin = 0;
            for (Game game : listPlayerGame(pid)) {
                int[][] board = game.getBoard();
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (board[i][j] == -1) {
                            black++;
                        }
                        if (board[i][j] == 1) {
                            white++;
                        }
                    }
                }
                if (black > white) {
                    blackwin = 1;
                }
                if (black < white) {
                    whitewin = 1;
                }
                if (game.getWhitePlayer().getPid() == pid) {
                    if (whitewin == 1) {
                        win++;
                    }
                }
                if (game.getBlackPlayer().getPid() == pid) {
                    if (blackwin == 1) {
                        win++;
                    }
                }
            }
            rate = (float) win / total;
        }
        else{
            rate=0;
        }
        return rate;
    }
}
