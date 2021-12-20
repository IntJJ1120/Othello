package Machine;

import java.util.ArrayList;
import java.util.Random;

public class MachineMode1 {
    ArrayList<int[]> possibleMoves = new ArrayList<>(0);
    ArrayList<Integer> lengthToVertex= new ArrayList<>(0);
    public MachineMode1(){
        int r;
        int c;
        int k=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
//                if(canClick(i,j)){
//                    int[] move = new int[2];
//                    move[0]=i;
//                    move[1]=j;
//                    possibleMoves.add(move);
//                    lengthToVertex.add(i*i+j*j);
//                }
//            }
        }
        int a=lengthToVertex.get(0);
//        for(int i = 1;i < lengthToVertex.size(); i++){
//            if(a>=lengthToVertex.get(i)){
//                a=lengthToVertex.get(i);
//                k=i;
//            }
        }
        r=possibleMoves.get(k)[0];
        c=possibleMoves.get(k)[1];


    }
}
