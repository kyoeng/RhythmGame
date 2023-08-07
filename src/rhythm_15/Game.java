package rhythm_15;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class Game extends Thread {
    
    // 게임 화면에 필요한 이미지
    private final Image gameInfoImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource( RhythmGame.IMAGE_ROOT+ "gameInfo.png"))).getImage();
    private final Image judgementLineImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "judgementLine.png"))).getImage();
    private final Image noteRouteLineImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRouteLine.png"))).getImage();

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


    ArrayList<Note> noteList = new ArrayList<>();



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

        g.drawImage(gameInfoImage, 0, 660, null);
        g.drawImage(judgementLineImage, 0, 580, null);

        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);
            if (!note.isProceeded()) {
                noteList.remove(i);
                i--;
            } else {
                note.screenDraw(g);
            }
        }

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
        judge("S");
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
        judge("D");
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
        judge("F");
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
        judge("Space");
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
        judge("J");
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
        judge("K");
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
        judge("L");
        Music smallS = new Music("smallSound.mp3", false);
        smallS.start();
    }

    public void releaseL() {
        noteRouteLImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteRoute.png"))).getImage();
    }


    @Override
    public void run() {
        dropNotes();
    }

    public void close() {
        gameMusic.close();
        this.interrupt();
    }


    public void dropNotes() {
        Beat[] beats = null;

        if (titleName.equals("Joakim - Might Love")) {
            int startTime = 4460 - Main.REACH_TIME * 1000;
            int gap = 125;

            beats = new Beat[]{
                    new Beat(startTime, "S"),
                    new Beat(startTime + gap * 2, "S"),
                    new Beat(startTime + gap * 4, "D"),
                    new Beat(startTime + gap * 6, "F"),
                    new Beat(startTime + gap * 8, "D"),
                    new Beat(startTime + gap * 10, "Space"),
                    new Beat(startTime + gap * 12, "J"),
                    new Beat(startTime + gap * 14, "K"),
                    new Beat(startTime + gap * 16, "J"),
                    new Beat(startTime + gap * 18, "L"),
                    new Beat(startTime + gap * 20, "D"),
                    new Beat(startTime + gap * 22, "F"),
                    new Beat(startTime + gap * 24, "Space"),
                    new Beat(startTime + gap * 26, "K"),
                    new Beat(startTime + gap * 28, "J"),
                    new Beat(startTime + gap * 30, "L"),
                    new Beat(startTime + gap * 32, "J"),
                    new Beat(startTime + gap * 34, "F"),
                    new Beat(startTime + gap * 36, "F"),
                    new Beat(startTime + gap * 38, "Space"),
                    new Beat(startTime + gap * 40, "J"),
                    new Beat(startTime + gap * 42, "K"),
                    new Beat(startTime + gap * 44, "S"),
                    new Beat(startTime + gap * 46, "L"),
                    new Beat(startTime + gap * 48, "D"),
                    new Beat(startTime + gap * 50, "K"),
                    new Beat(startTime + gap * 52, "L"),
                    new Beat(startTime + gap * 54, "S"),
            };
        } else if (titleName.equals("Joakim - Wild Flower")) {
            int startTime = 1000;
            beats = new Beat[]{
                    new Beat(startTime, "Space")
            };
        } else if (titleName.equals("BenSound - Energy")) {
            int startTime = 1000;
            beats = new Beat[]{
                    new Beat(startTime, "Space")
            };
        }

        gameMusic.start();

        int i = 0;
        while(i < beats.length && !isInterrupted()) {
            boolean dropped = false;

            if (beats[i].getTime() <= gameMusic.getTime()) {
                Note note = new Note(beats[i].getNoteName());
                note.start();
                noteList.add(note);
                i++;
                dropped = true;
            }

            if (!dropped) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }


    public void judge(String input) {
        for (int i = 0; i < noteList.size(); i++) {
            Note note = noteList.get(i);

            if (input.equals(note.getNoteType())) {
                note.judge();
                break;
            }
        }
    }
}
