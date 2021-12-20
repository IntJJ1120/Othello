package Begin;


public class MoveBoard{
    public static int[][] ChessBoardMove(int [][]chessboard1,int J, int [] Move){
        int i=Move[0];
        int j=Move[1];
        if (0 < i & i < 7 & 0 < j & j < 7) {
            for (int p = i - 1; p <= i + 1; p++) {
                for (int q = j - 1; q <= j + 1; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(i==0&j!=7&j!=0) {// the top line without the corner
            for (int p = i; p <= i + 1; p++) {
                for (int q = j - 1; q <= j + 1; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(i==7&j!=7&j!=0) {//the bottom line without the corner
            for (int p = i-1; p <= i; p++) {
                for (int q = j - 1; q <= j + 1; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(j==7&i!=7&i!=0) {//the rightest line without the corner
            for (int p = i-1; p <= i+1; p++) {
                for (int q = j - 1; q <= j; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(j==0&i!=7&i!=0) {//the leftest line without the corner
            for (int p = i-1; p <= i+1; p++) {
                for (int q = j; q <= j+1; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(i==0&j==0) {//the leftest top corner
            for (int p = i; p <= i+1; p++) {
                for (int q = j; q <= j+1; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(i==0&j==7) {//the rightest top corner
            for (int p = i; p <= i+1; p++) {
                for (int q = j-1; q <= j; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(i==7&j==0) {//the leftest bottom corner
            for (int p = i-1; p <= i; p++) {
                for (int q = j; q <= j+1; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }

        if(i==7&j==7) {//the rightest bottom corner
            for (int p = i-1; p <= i; p++) {
                for (int q = j-1; q <= j; q++) {
                    if (chessboard1[p][q] == -J) {
                        for (int k = 1; p + (p - i) * k <=7 & p + (p - i) * k >= 0 & q + (q - j) * k <= 7 & q + (q - j) * k >= 0; k++) {
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == 0) {
                                break;
                            }
                            if (chessboard1[p + (p - i) * k][q + (q - j) * k] == J) {
                                chessboard1[i][j] = J;//落子为J
                                for(int x=1;x<=k;x++){//落子与找到棋子中间为J
                                    chessboard1[p + (p - i) * (k-x)][q + (q - j) * (k-x)]=J;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }
        return chessboard1;
    }
}