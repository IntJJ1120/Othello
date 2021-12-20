package view;

import components.ChessGridComponent;
import model.ChessPiece;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ChessBoardPanel extends JPanel {
    private final int CHESS_COUNT = 8;
    private ChessGridComponent[][] chessGrids;
    public static ArrayList<String> Row=new ArrayList<>(0);
    public static ArrayList<String> Col=new ArrayList<>(0);

    public ChessBoardPanel(int width, int height) {
        width=700;
        height=700;
        this.setVisible(true);
        this.setFocusable(true);
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        int length = Math.min(width, height);
        this.setSize(length, length);
        ChessGridComponent.gridSize = length / CHESS_COUNT;
        ChessGridComponent.chessSize = (int) (ChessGridComponent.gridSize * 0.8);
        System.out.printf("width = %d height = %d gridSize = %d chessSize = %d\n",
                width, height, ChessGridComponent.gridSize, ChessGridComponent.chessSize);

        initialChessGrids();//return empty chessboard
        initialGame();//add initial four chess

        repaint();
    }

    /**
     * set an empty chessboard
     */
    public ChessGridComponent[][] getChessGrids() {
        return chessGrids;
    }
    public ArrayList<String> getRow() {
        return  Row;
    }
    public ArrayList<String> getCol() {
        return  Col;
    }

    public ChessGridComponent[][] getChessGridComponent() {
        return chessGrids;
    }

    public void initialChessGrids() {
        chessGrids = new ChessGridComponent[CHESS_COUNT][CHESS_COUNT];
        //draw all chess grids
        for (int i = 0; i < CHESS_COUNT; i++) {
            for (int j = 0; j < CHESS_COUNT; j++) {
                ChessGridComponent gridComponent = new ChessGridComponent(i, j);
                gridComponent.setLocation(j * ChessGridComponent.gridSize, i * ChessGridComponent.gridSize);
                chessGrids[i][j] = gridComponent;
                this.add(chessGrids[i][j]);
            }
        }
    }

    /**
     * initial origin four chess
     */
    public void initialGame() {
        chessGrids[3][3].setChessPiece(ChessPiece.WHITE);
        chessGrids[3][4].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][3].setChessPiece(ChessPiece.BLACK);
        chessGrids[4][4].setChessPiece(ChessPiece.WHITE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
    }

    public ChessPiece changeChess(ChessPiece currentPlayer) {
        ChessPiece a = (currentPlayer == ChessPiece.BLACK) ? ChessPiece.WHITE : ChessPiece.BLACK;
        return a;
    }

    public boolean canClickGrid(int row, int col, ChessPiece currentPlayer) {
        boolean b = false;
        if(this.chessGrids[row][col].getChessPiece()==null||this.chessGrids[row][col].getChessPiece()==ChessPiece.PROMPT ){
        for (int q = 1; q < 8 - col - 1; q++) {
            if (this.chessGrids[row][col + q].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row][col + q + 1].getChessPiece() == currentPlayer) {
                    b = true;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < 8 - row - 1; q++) {
            if (this.chessGrids[row + q][col].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row + q + 1][col].getChessPiece() == currentPlayer) {
                    b = true;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < row; q++) {
            if (this.chessGrids[row - q][col].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row - q - 1][col].getChessPiece() == currentPlayer) {
                    b = true;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < col; q++) {
            if (this.chessGrids[row][col - q].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row][col - q - 1].getChessPiece() == currentPlayer) {
                    b = true;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < col & q < row; q++) {
            if (this.chessGrids[row - q][col - q].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row - q - 1][col - q - 1].getChessPiece() == currentPlayer) {
                    b = true;
                    int e = col;
                    int r = row;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
            if (this.chessGrids[row + q][col + q].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row + q + 1][col + q + 1].getChessPiece() == currentPlayer) {
                    int e = row;
                    int r = col;
                    b = true;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < 8 - row - 1 & q < col; q++) {
            if (this.chessGrids[row + q][col - q].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row + q + 1][col - 1 - q].getChessPiece() == currentPlayer) {
                    int e = row;
                    int r = col;
                    b = true;
                }
            } else {
                break;
            }
        }
        for (int q = 1; q < row & q < 8 - col - 1; q++) {
            if (this.chessGrids[row - q][col + q].getChessPiece() == this.changeChess(currentPlayer)) {
                if (this.chessGrids[row - q - 1][col + q + 1].getChessPiece() == currentPlayer) {
                    b = true;
                    int e = row;
                    int r = col;
                }
            } else {
                break;
            }
        }}
        return b;
    }

//    public boolean GameOver(ChessPiece currentPlayer) {
//        boolean b =true;
//            for (int row = 0; row < 8; row++) {
//                for (int col = 0; col < 8; col++) {
//                    if(this.chessGrids[row][col]==null) {
//                        for (int q = 1; q < 8 - col - 1; q++) {
//                            if (this.chessGrids[row][col + q].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row][col + q + 1].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < 8 - row - 1; q++) {
//                            if (this.chessGrids[row + q][col].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row + q + 1][col].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < row; q++) {
//                            if (this.chessGrids[row - q][col].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row - q - 1][col].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < col; q++) {
//                            if (this.chessGrids[row][col - q].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row][col - q - 1].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < col & q < row; q++) {
//                            if (this.chessGrids[row - q][col - q].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row - q - 1][col - q - 1].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < 8 - row - 1 & q < 8 - col - 1; q++) {
//                            if (this.chessGrids[row + q][col + q].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row + q + 1][col + q + 1].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < 8 - row - 1 & q < col; q++) {
//                            if (this.chessGrids[row + q][col - q].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row + q + 1][col - 1 - q].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                        for (int q = 1; q < row & q < 8 - col - 1; q++) {
//                            if (this.chessGrids[row - q][col + q].getChessPiece() == this.changeChess(currentPlayer)) {
//                                if (this.chessGrids[row - q - 1][col + q + 1].getChessPiece() == currentPlayer) {
//                                    b = false;
//                                }
//                            } else {
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        return b;
//    }
    }


