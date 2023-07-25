package rhythm_5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Objects;

public class RhythmGame extends JFrame {

    private final String IMAGE_ROOT = "../images/";     // 이미지 경로


    // 더블 버퍼링을 위해 전체화면을 담기 위한 두개의 변수 선언
    private Image screenImage;
    private Graphics screenGraphic;


    // 외부의 이미지를 저장하기 위한 변수 선언 (Main.class 위치를 기준으로 해당경로의 파일 리소스 가져오기)
    private Image background = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "introBackground.jpg"))).getImage();

    // 메뉴바에 대한 이미지
    private final JLabel MENU_BAR = new JLabel(new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "menuBar.png"))));

    // 종료버튼에 대한 이미지 (메뉴바)
    private final ImageIcon basicExit = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "exitBtn1.png")));
    private final ImageIcon enteredExit = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "exitBtn2.png")));

    // 시작 종료 버튼 이미지 (intro 화면)
    private final ImageIcon startBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "startBtnBasic.png")));
    private final ImageIcon startBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "startBtnEntered.png")));
    private final ImageIcon quitBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "quitBtnBasic.png")));
    private final ImageIcon quitBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "quitBtnEntered.png")));


    // 종료버튼에 대한 변수
    private JButton exitBtn = new JButton(basicExit);
    private JButton startBtn = new JButton(startBtnBasic);
    private JButton quitBtn = new JButton(quitBtnBasic);


    // 마우스 이벤트를 위한 변수
    private int mouseX, mouseY;


    // 메서드 부분 -----------------------------------------------------------
    /**
     * 생성자
     */
    public RhythmGame() {
        // 기본 설정 ==========
        setUndecorated(true);                                   // 기본 제공되는 메뉴바가 안보이도록 설정
        setTitle("Rhythm Game");                                // 프로그램의 제목 설정
        setSize(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);         // 프로그램의 해상도 설정 (가로, 세로)
        setResizable(false);                                    // 사용자가 해상도 변경을 불가하도록 설정 (true = 가능)
        setLocationRelativeTo(null);                            // 실행 시 프로그램이 화면 정중앙에 오도록 설정
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         // 프로그램 종료 시 전체 종료 (백그라운드에서 실행이 안되도록)
        setVisible(true);                                       // 프로그램이 눈에 보이도록 설정 (false = 백그라운드 실행)
        setBackground(new Color(0, 0, 0, 0));       // 기본 배경을 투명하게 설정 (지정한 이미지가 보일 수 있도록)
        setLayout(null);


        // 종료버튼 setting (메뉴바) ==========
        exitBtn.setBounds(1232, 0, 48, 28);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        exitBtn.setBorderPainted(false);
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusPainted(false);

        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                exitBtn.setIcon(enteredExit);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitBtn.setIcon(basicExit);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        // 종료버튼 추가 (메뉴바)
        add(exitBtn);


        // 메뉴바 setting ==========
        MENU_BAR.setBounds(0, 0, 1280, 28);     // 위치 및 크기 설정

        // 마우스 이벤트 설정
        MENU_BAR.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mouseX = e.getX();
                mouseY = e.getY();
            }
        });
        MENU_BAR.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                setLocation(x - mouseX, y - mouseY);
            }
        });

        // 메뉴바 추가
        add(MENU_BAR);


        // 시작 버튼 setting ===========
        startBtn.setBounds(980, 510, 270, 80);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        startBtn.setBorderPainted(false);
        startBtn.setContentAreaFilled(false);
        startBtn.setFocusPainted(false);

        startBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                startBtn.setIcon(startBtnEntered);
                startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                startBtn.setIcon(startBtnBasic);
                startBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startBtn.setVisible(false);
                quitBtn.setVisible(false);
                background = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "mainBackground.jpg"))).getImage();
            }
        });

        // 시작버튼 추가
        add(startBtn);



        // 종료버튼 setting ==========
        quitBtn.setBounds(980, 610, 270, 80);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        quitBtn.setBorderPainted(false);
        quitBtn.setContentAreaFilled(false);
        quitBtn.setFocusPainted(false);

        quitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                quitBtn.setIcon(quitBtnEntered);
                quitBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quitBtn.setIcon(quitBtnBasic);
                quitBtn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.exit(0);
            }
        });

        // 종료버튼 추가
        add(quitBtn);



        // intro 화면에 나올 음악에 대한 인스턴스 생성 후 실행 ==========
        Music introMusic = new Music("intro.mp3", true);
        introMusic.start();
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
        g.drawImage(background, 0, 0, null);               // 0, 0 위치에 이미지 그리기
        paintComponents(g);
        this.repaint();                                                       // paint 메서드 재실행
    }

}
