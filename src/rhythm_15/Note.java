package rhythm_15;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Note extends Thread {

    private final Image noteBasicImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteBasic.png"))).getImage();
    private int x;
    private int y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
    private String noteType;
    private boolean proceeded = true;

    public String getNoteType() {
        return noteType;
    }

    public boolean isProceeded() {
        return proceeded;
    }


    /**
     * 노트가 더이상 움직이지 않게 하지 위한 메서드
     */
    public void close() {

    }

    public Note(String noteType) {
        if (noteType.equals("S")) {
            x = 228;
        } else if (noteType.equals("D")) {
            x = 332;
        } else if (noteType.equals("F")) {
            x = 436;
        } else if (noteType.equals("Space")) {
            x = 540;
        } else if (noteType.equals("J")) {
            x = 744;
        } else if (noteType.equals("K")) {
            x = 848;
        } else if (noteType.equals("L")) {
            x = 952;
        }

        this.noteType = noteType;
    }

    public void screenDraw(Graphics2D g) {
        if (!noteType.equals("Space")) {
            g.drawImage(noteBasicImage, x, y, null);
        } else {
            g.drawImage(noteBasicImage, x, y, null);
            g.drawImage(noteBasicImage, x + 100, y, null);
        }
    }


    public void drop() {
        y += Main.NOTE_SPEED;

        if (y > 620) {
            System.out.println("Miss");
            proceeded = false;
            close();
        }
    }


    @Override
    public void run() {
        try {
            while (true) {
                drop();

                if (proceeded) {
                    Thread.sleep(Main.SLEEP_TIME);
                } else {
                    interrupt();
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }


    public void judge() {
        if (y >= 613) {
            System.out.println("Late");
            proceeded = false;
            close();
        } else if (y >= 600) {
            System.out.println("Good");
            proceeded = false;
            close();
        } else if (y >= 587) {
            System.out.println("Great");
            proceeded = false;
            close();
        } else if (y >= 573) {
            System.out.println("Perfect");
            proceeded = false;
            close();
        } else if (y >= 565) {
            System.out.println("Great");
            proceeded = false;
            close();
        } else if (y >= 550) {
            System.out.println("Good");
            proceeded = false;
            close();
        } else if (y >= 535) {
            System.out.println("Early");
            proceeded = false;
            close();
        }
    }

}
