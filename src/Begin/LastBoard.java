package Begin;

import model.ChessPiece;
import java.util.ArrayList;

public class LastBoard {

    public int back(int current){
        int back = (current==1)?-1:1;
        return back;
    }
    public static int[][] LastBoard(ArrayList<String[]> Step){
        int[][] iBoard=new int[8][8];
        iBoard[3][3]=1;
        iBoard[3][4]=-1;
        iBoard[4][3]=-1;
        iBoard[4][4]=1;

        ArrayList<int [][]> middleBoards=new ArrayList<>(0);
        middleBoards.add(iBoard);
        int J=-1;
        for(int j=0;j<Step.size()-1;j++) {
            int[] Move = new int[2];
            int[][] middle = new int[8][8];
            Move[0] = Integer.parseInt(Step.get(j)[0]);
            Move[1] = Integer.parseInt(Step.get(j)[1]);
            middle=MoveBoard.ChessBoardMove(middleBoards.get(j), J, Move);
            middleBoards.add(middle);
            J=-J;
            for(int p=0;p<8;p++){
                for(int q=0;q<8;q++){
                    System.out.print(middle[p][q]+" ");
                }
                System.out.println();
            }
            System.out.println(J);
            System.out.println();
        }
        return middleBoards.get(middleBoards.size()-1);
    }

}
