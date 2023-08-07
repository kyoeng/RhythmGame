package rhythm_14;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Note extends Thread {

    private final Image noteBasicImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteBasic.png"))).getImage();
    private int x;
    private int y = 580 - (1000 / Main.SLEEP_TIME * Main.NOTE_SPEED) * Main.REACH_TIME;
    private String noteType;


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
    }


    @Override
    public void run() {
        try {
            while (true) {
                drop();
                Thread.sleep(Main.SLEEP_TIME);
            }
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
        }
    }

}
