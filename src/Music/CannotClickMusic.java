package Music;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class CannotClickMusic extends Thread{
    private File f;
    private URI uri;
    private URL url;


    public void run() { // 注意，java只能播放无损音质，如.wav这种格式
        try {

            f = new File("src/Music/游戏失败音效.wav"); // 绝对路径
            uri = f.toURI();
            url = uri.toURL(); // 解析路径
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.play(); // 播放
            System.out.println("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}