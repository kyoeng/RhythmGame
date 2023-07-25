package rhythm_1;

import javax.swing.*;
import java.awt.*;

public class RhythmGame extends JFrame {

    // 더블 버퍼링을 위해 전체화면을 담기 위한 두개의 변수 선언
    private Image screenImage;
    private Graphics screenGraphic;
    // 외부의 이미지를 저장하기 위한 변수 선언
    private Image introBackground;

    /**
     * 생성자
     */
    public RhythmGame() {
        setTitle("Rhythm Game");                                // 프로그램의 제목 설정
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);         // 프로그램의 해상도 설정 (가로, 세로)
        setResizable(false);                                    // 사용자가 해상도 변경을 불가하도록 설정 (true = 가능)
        setLocationRelativeTo(null);                            // 실행 시 프로그램이 화면 정중앙에 오도록 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         // 프로그램 종료 시 전체 종료 (백그라운드에서 실행이 안되도록)
        setVisible(true);                                       // 프로그램이 눈에 보이도록 설정 (false = 백그라운드 실행)

        // Main.class 위치를 기준으로 해당경로의 파일 리소스 가져오기
        introBackground = new ImageIcon(Main.class.getResource("../images/introBackground.jpg")).getImage();
    }


    /**
     * 배경을 그리기 위한 메서드
     * @param g Graphics
     */
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);   // 프로그램 화면의 크기만큼 이미지를 생성
        screenGraphic = screenImage.getGraphics();                          // screenImage 에서 생성한 그래픽 가져오기
        screenDraw(screenGraphic);                                          // screenDraw 메서드 호출
        g.drawImage(screenImage, 0, 0, null);                  // 0, 0 위치에 이미지 그리기
    }


    /**
     * 배경이미지를 설정하고 paint 메서드를 재시작하기 위한 메서드
     * @param g Graphics
     */
    public void screenDraw(Graphics g) {
        g.drawImage(introBackground, 0, 0, null);               // 0, 0 위치에 이미지 그리기
        this.repaint();                                                       // paint 메서드 재실행
    }

}
