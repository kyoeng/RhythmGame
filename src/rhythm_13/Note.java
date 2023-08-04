package rhythm_13;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Note extends Thread {

    private final Image noteBasicImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(RhythmGame.IMAGE_ROOT + "noteBasic.png"))).getImage();
    private int x;
    private int y = 580 - 1000 / Main.SLEEP_TIME * Main.NOTE_SPEED;
    private String noteType;


    public Note(int x, String noteType) {
        this.x = x;
        this.noteType = noteType;
    }

    public void screenDraw(Graphics2D g) {
        if (noteType.equals("short")) {
            g.drawImage(noteBasicImage, x, y, null);
        } else if (noteType.equals("long")) {
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
            System.out.println(e.getMessage());
        }
    }

}
