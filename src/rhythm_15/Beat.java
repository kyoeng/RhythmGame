package rhythm_15;

public class Beat {

    private int time;
    private String noteName;

    /**
     * 생성자
     * @param time int
     * @param noteName String
     */
    public Beat(int time, String noteName) {
        this.time = time;
        this.noteName = noteName;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getNoteName() {
        return noteName;
    }

    public void setNoteName(String noteName) {
        this.noteName = noteName;
    }
}
