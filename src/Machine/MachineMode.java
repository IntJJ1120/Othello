package Machine;
import java.util.Random;


public class MachineMode {
    private int r;
    private int c;

    public MachineMode(){
        Random rand = new Random();
        r=rand.nextInt(8);
        c=rand.nextInt(8);
//        while(!canClickGrid(r,c,)){
//            r=rand.nextInt(8);
//            c=rand.nextInt(8);
//        }

    }

    public int getC() {
        return c;
    }

    public int getR() {
        return r;
    }
}
