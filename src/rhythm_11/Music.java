package rhythm_11;

import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Objects;

public class Music extends Thread {

    private Player player;
    private boolean isLoop;
    private File file;
    private FileInputStream fis;
    private BufferedInputStream bis;


    // 메서드 부분 -----------------------------------------------------------
    /**
     * 생성자
     * @param name 음악 리소스 파일 이름
     * @param isLoop 반복 여부 (boolean)
     */
    public Music(String name, boolean isLoop) {
        try {
            this.isLoop = isLoop;
            file = new File(Objects.requireNonNull(Main.class.getResource("../musics/" + name)).toURI());
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            player = new Player(bis);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public int getTime() {
        if (player == null) return 0;
        return player.getPosition();
    }


    public void close() {
        isLoop = false;
        player.close();
        this.interrupt();       // Thread 종료
    }


    /**
     * Thread 상속 시 필수 오버라이딩 메서드
     */
    @Override
    public void run() {
        try {
            do {
                player.play();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                player = new Player(bis);
            } while(isLoop);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
