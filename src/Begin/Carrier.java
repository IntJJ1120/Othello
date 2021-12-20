package Begin;

import java.util.Scanner;
import java.util.Arrays;

public class Carrier{
    public static int[][] ChessBoard2(int [][]chessboard1,int J){
        int [][] chessboard2=new int[8][8];
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(chessboard1[i][j]==(-J)){
                    if(0<i&i<7&0<j&j<7){
                        for(int p=i-1;p<=i+1;p++){
                            for(int q=j-1;q<=j+1;q++){
                                if (chessboard1[2*i-p][2*j-q]==0) {
                                    if(chessboard1[p][q]==J){
                                        chessboard2[2*i-p][2*j-q]=1;
                                    }
                                    if(chessboard1[p][q]==-J){
                                        for(int k=1;p+(p-i)*k<=7&p+(p-i)*k>=0&q+(q-j)*k<=7&q+(q-j)*k>=0;k++){
                                            if(chessboard1[p+(p-i)*k][q+(q-j)*k]==0){
                                                break;
                                            }
                                            if(chessboard1[p+(p-i)*k][q+(q-j)*k]==J){
                                                chessboard2[2*i-p][2*j-q]=1;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }


                    if(i==0&j!=7&j!=0){
                        for(int q=j-1;q<=j+1;q++){
                            if(chessboard1[0][2*j-q]==0){
                                if(chessboard1[0][q]==J){
                                    chessboard2[0][2*j-q]=1;
                                }
                                if(chessboard1[0][q]==-J){
                                    for(int k=1;0<=q+(q-j)*k&q+(q-j)*k<=7;k++){
                                        if(chessboard1[0][q+(q-j)*k]==0){
                                            break;
                                        }
                                        if(chessboard1[0][q+(q-j)*k]==J){
                                            chessboard2[0][2*j-q]=1;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }

                    if(i==7&j!=7&j!=0){
                        for(int q=j-1;q<=j+1;q++){
                            if(chessboard1[7][2*j-q]==0){
                                if(chessboard1[7][q]==J){
                                    chessboard2[7][2*j-q]=1;
                                }
                                if(chessboard1[7][q]==-J){
                                    for(int k=1;0<=q+(q-j)*k&q+(q-j)*k<=7;k++){
                                        if(chessboard1[7][q+(q-j)*k]==0){
                                            break;
                                        }
                                        if(chessboard1[7][q+(q-j)*k]==J){
                                            chessboard2[7][2*j-q]=1;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(i!=7&i!=0&j==0){
                        for(int p=i-1;p<=i+1;p++){
                            if(chessboard1[2*i-p][0]==0){
                                if(chessboard1[p][0]==J){
                                    chessboard2[2*i-p][0]=1;
                                }
                                if(chessboard1[p][0]==-J){
                                    for(int k=1;0<=p+(p-i)*k&p+(p-i)*k<=7;k++){
                                        if(chessboard1[p+(p-i)*k][0]==0){
                                            break;
                                        }
                                        if(chessboard1[p+(p-i)*k][0]==J){
                                            chessboard2[2*i-p][0]=1;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if(i!=7&i!=0&j==7){
                        for(int p=i-1;p<=i+1;p++){
                            if(chessboard1[2*i-p][7]==0){
                                if(chessboard1[p][7]==J){
                                    chessboard2[2*i-p][7]=1;
                                }
                                if(chessboard1[p][7]==-J){
                                    for(int k=1;0<=p+(p-i)*k&p+(p-i)*k<=7;k++){
                                        if(chessboard1[p+(p-i)*k][7]==0){
                                            break;
                                        }
                                        if(chessboard1[p+(p-i)*k][7]==J){
                                            chessboard2[2*i-p][7]=1;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return chessboard2;
    }
}