package rhythm_12;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Game extends Thread {
    
    // 게임 화면에 필요한 이미지
    private final Image gameInfoImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource( RhythmGame.IMAGE_ROOT+ "gameInfo.png"))).getImage();
    private final Image judgementLineImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "judgementLine.png"))).getImage();
    private final Image noteRouteLineImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRouteLine.png"))).getImage();
    private final Image noteBasicImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteBasic.png"))).getImage();

    private Image noteRouteSImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteDImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteFImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteSpace1Image = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteSpace2Image = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteJImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteKImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    private Image noteRouteLImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();

    private String titleName;
    private String difficulty;
    private String musicTitle;
    private Music gameMusic;



    /**
     * 생성자
     * @param titleName 곡 이름
     * @param difficulty 난이도
     */
    public Game(String titleName, String difficulty, String musicTitle) {
        this.titleName = titleName;
        this.difficulty = difficulty;
        this.musicTitle = musicTitle;
        gameMusic = new Music(this.musicTitle, false);
        gameMusic.start();
    }
    

    /**
     * 게임 화면을 그려주는 메서드
     * @param g Graphics2D
     */
    public void screenDraw(Graphics2D g) {
        g.drawImage(noteRouteSImage, 228, 30, null);
        g.drawImage(noteRouteDImage, 332, 30, null);
        g.drawImage(noteRouteFImage, 436, 30, null);
        g.drawImage(noteRouteSpace1Image, 540, 30, null);
        g.drawImage(noteRouteSpace2Image, 640, 30, null);
        g.drawImage(noteRouteJImage, 744, 30, null);
        g.drawImage(noteRouteKImage, 848, 30, null);
        g.drawImage(noteRouteLImage, 952, 30, null);

        g.drawImage(noteRouteLineImage, 224, 30, null);
        g.drawImage(noteRouteLineImage, 328, 30, null);
        g.drawImage(noteRouteLineImage, 432, 30, null);
        g.drawImage(noteRouteLineImage, 536, 30, null);
        g.drawImage(noteRouteLineImage, 740, 30, null);
        g.drawImage(noteRouteLineImage, 844, 30, null);
        g.drawImage(noteRouteLineImage, 948, 30, null);
        g.drawImage(noteRouteLineImage, 1052, 30, null);

        g.drawImage(noteBasicImage, 228, 120, null);
        g.drawImage(noteBasicImage, 332, 120, null);
        g.drawImage(noteBasicImage, 436, 120, null);
        g.drawImage(noteBasicImage, 540, 120, null);
        g.drawImage(noteBasicImage, 640, 120, null);
        g.drawImage(noteBasicImage, 744, 120, null);
        g.drawImage(noteBasicImage, 848, 120, null);
        g.drawImage(noteBasicImage, 952, 120, null);

        g.drawImage(gameInfoImage, 0, 660, null);
        g.drawImage(judgementLineImage, 0, 580, null);

        g.setColor(Color.white);
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString(titleName, 20, 702);
        g.drawString(difficulty, 1190, 702);

        g.setFont(new Font("Arial", Font.PLAIN, 26));
        g.setColor(Color.DARK_GRAY);
        g.drawString("S", 270, 609);
        g.drawString("D", 374, 609);
        g.drawString("F", 478, 609);
        g.drawString("Space Bar", 580, 609);
        g.drawString("J", 784, 609);
        g.drawString("K", 889, 609);
        g.drawString("L", 993, 609);

        g.setColor(Color.LIGHT_GRAY);
        g.setFont(new Font("Elephant", Font.BOLD, 30));
        g.drawString("000000", 565, 702);
    }


    /**
     * S 키의 이벤트
     */
    public void pressS() {
        noteRouteSImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseS() {
        noteRouteSImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    /**
     * D 키의 이벤트
     */
    public void pressD() {
        noteRouteDImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseD() {
        noteRouteDImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    /**
     * F 키의 이벤트
     */
    public void pressF() {
        noteRouteFImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseF() {
        noteRouteFImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    /**
     * Space 키의 이벤트
     */
    public void pressSpace() {
        noteRouteSpace1Image = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        noteRouteSpace2Image = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music bigS = new Music("bigSound.mp3", false);
        bigS.start();
    }

    public void releaseSpace() {
        noteRouteSpace1Image = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
        noteRouteSpace2Image = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    /**
     * J 키의 이벤트
     */
    public void pressJ() {
        noteRouteJImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseJ() {
        noteRouteJImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    /**
     * K 키의 이벤트
     */
    public void pressK() {
        noteRouteKImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseK() {
        noteRouteKImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    /**
     * L 키의 이벤트
     */
    public void pressL() {
        noteRouteLImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoutePressed.png"))).getImage();
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseL() {
        noteRouteLImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    @Override
    public void run() {

    }

    public void close() {
        gameMusic.close();
        this.interrupt();
    }
}
