package util;

import java.awt.*;

public class ScreenUtils {

    //获取当前屏幕宽度
    public static int getScreenWidth(){
        return Toolkit.getDefaultToolkit().getScreenSize().width;
    }

    //获取当前屏幕搞度
    public static int getScreenHight(){
        return Toolkit.getDefaultToolkit().getScreenSize().height;
    }


}
