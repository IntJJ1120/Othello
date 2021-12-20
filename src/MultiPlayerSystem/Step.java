package MultiPlayerSystem;

public class Step {
    private int sid;
    private int rowIndex;
    private int columnIndex;
    private int color;
    private static int stepCnt=1;
    public Step(int rowInxex, int columnIndex, int color){
        sid=stepCnt;
        stepCnt++;
        this.rowIndex=rowInxex;
        this.columnIndex=columnIndex;
        this.color=color;
    }
    public int getSid(){
        return this.sid;
    }
    public int getColor(){
        return this.color;
    }
    public void setColor(int color){
        this.color=color;
    }
    public int getRowIndex(){
        return this.rowIndex;
    }
    public void setRowIndex(int rowIndex){
        this.rowIndex=rowIndex;
    }
    public int getColumnIndex(){
        return this.columnIndex;
    }
    public void setColumnIndex(int columnIndex){
        this.columnIndex=columnIndex;
    }
    public static int getStepCnt(){
        return stepCnt;
    }
    public String toString(){
        return String.format("sid: %d, rowIndex: %d, columnIndex: %d, color: %d",this.sid,this.rowIndex,this.columnIndex,this.color);
    }
}