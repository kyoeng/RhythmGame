package rhythm_15;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Objects;

public class RhythmGame extends JFrame {

    public static final String IMAGE_ROOT = "../images/";     // 이미지 경로


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

    // 곡 선택 좌우 버튼 이미지
    private final ImageIcon leftBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "leftButtonBasic.png")));
    private final ImageIcon leftBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "leftButtonEntered.png")));
    private final ImageIcon rightBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "rightButtonBasic.png")));
    private final ImageIcon rightBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "rightButtonEntered.png")));

    // 곡 난이도에 대한 버튼 이미지
    private final ImageIcon easyBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "easyButtonBasic.png")));
    private final ImageIcon easyBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "easyButtonEntered.png")));
    private final ImageIcon hardBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "hardButtonBasic.png")));
    private final ImageIcon hardBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "hardButtonEntered.png")));

    // 게임 화면에서 뒤로가기 버튼 이미지
    private final ImageIcon backBtnBasic = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "backButtonBasic.png")));
    private final ImageIcon backBtnEntered = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "backButtonEntered.png")));


    // 버튼에 대한 변수
    private JButton exitBtn = new JButton(basicExit);           // 상단 메뉴바의 X버튼
    private JButton startBtn = new JButton(startBtnBasic);      // 메인 인트로 화면에서의 시작 버튼
    private JButton quitBtn = new JButton(quitBtnBasic);        // 메인 인트로 화면에서의 종료 버튼
    private JButton leftBtn = new JButton(leftBtnBasic);        // 곡 선택 화면에서의 왼쪽으로 버튼
    private JButton rightBtn = new JButton(rightBtnBasic);      // 곡 선택 화면에서의 오른쪽으로 버튼
    private JButton easyBtn = new JButton(easyBtnBasic);        // 쉬움 난이도 버튼
    private JButton hardBtn = new JButton(hardBtnBasic);        // 어려움 난이도 버튼
    private JButton backBtn = new JButton(backBtnBasic);        // 뒤로가기 버튼


    // 마우스 이벤트를 위한 변수
    private int mouseX, mouseY;

    private boolean isMainScreen = false;
    private boolean isGameScreen = false;


    // 곡들의 정보에 대한 객체를 저장할 배열
    ArrayList<Track> trackList = new ArrayList<>();

    private Image titleImage;           // 곡 제목 이미지
    private Image selectedImage;        // 곡 선택 이미지
    private Music selectedMusic;        // 곡 선택 하이라이트 음악
    private int nowSelected = 0;        // trackList 의 인덱스


    // 인트로 음악에 대한 인스턴스
    private Music introMusic = new Music("intro.mp3", true);

    // Game 에 대한 인스턴스
    public static Game game;


    // 메서드 부분 -----------------------------------------------------------
    /**
     * 생성자
     */
    public RhythmGame() {
        // 곡에 대한 정보들을 trackList 에 저장
        trackList.add(new Track("mightyTitle.png", "mightyStartImage.png", "mightyGameImage.jpg", "mightySelected.mp3", "joakim-mighty_love.mp3", "Joakim - Might Love"));
        trackList.add(new Track("wildTitle.png", "flowerStartImage.png", "flowerGameImage.jpg", "wildSelected.mp3", "joakim-wild_flower.mp3", "Joakim - Wild Flower"));
        trackList.add(new Track("energyTitle.png", "energyStartImage.png", "energyGameImage.jpg", "energySelected.mp3", "bensound-energy.mp3", "BenSound - Energy"));


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

        addKeyListener(new KeyListener());

        // intro 화면에 나올 음악에 대한 인스턴스 생성 후 실행 ==========
        introMusic.start();


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
                enterMouse(startBtn, startBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(startBtn, startBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                enterMain();
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
                enterMouse(quitBtn, quitBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(quitBtn, quitBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                Music btnEnteredMusic = new Music("enteredSound.mp3", false);
                btnEnteredMusic.start();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

                System.exit(0);
            }
        });

        // 종료버튼 추가
        add(quitBtn);


        // 곡 선택 부분의 왼쪽 버튼 =====
        leftBtn.setVisible(false);
        leftBtn.setBounds(140, 310, 60, 60);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        leftBtn.setBorderPainted(false);
        leftBtn.setContentAreaFilled(false);
        leftBtn.setFocusPainted(false);

        leftBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterMouse(leftBtn, leftBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(leftBtn, leftBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectLeft();
            }
        });

        // 왼쪽 버튼 추가
        add(leftBtn);


        // 곡 선택 부분의 오른쪽 버튼 =====
        rightBtn.setVisible(false);
        rightBtn.setBounds(1080, 310, 60, 60);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        rightBtn.setBorderPainted(false);
        rightBtn.setContentAreaFilled(false);
        rightBtn.setFocusPainted(false);

        rightBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterMouse(rightBtn, rightBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(rightBtn, rightBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                selectRight();
            }
        });

        // 오른쪽 버튼 추가
        add(rightBtn);


        // 곡 난이도 버튼 (easy) =====
        easyBtn.setVisible(false);
        easyBtn.setBounds(375, 580, 250, 88);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        easyBtn.setBorderPainted(false);
        easyBtn.setContentAreaFilled(false);
        easyBtn.setFocusPainted(false);

        easyBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterMouse(easyBtn, easyBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(easyBtn, easyBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameStart(nowSelected, "easy");
            }
        });

        // easy 버튼 추가
        add(easyBtn);


        // 곡 난이도 버튼 (hard) =====
        hardBtn.setVisible(false);
        hardBtn.setBounds(655, 580, 250, 88);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        hardBtn.setBorderPainted(false);
        hardBtn.setContentAreaFilled(false);
        hardBtn.setFocusPainted(false);

        hardBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterMouse(hardBtn, hardBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(hardBtn, hardBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                gameStart(nowSelected, "hard");
            }
        });

        // hard 버튼 추가
        add(hardBtn);


        // 뒤로가기 버튼 =====
        backBtn.setVisible(false);
        backBtn.setBounds(20, 50, 60, 60);      // 위치 및 크기 설정

        // 기본 제공하는 버튼의 디자인 제거
        backBtn.setBorderPainted(false);
        backBtn.setContentAreaFilled(false);
        backBtn.setFocusPainted(false);

        backBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                enterMouse(backBtn, backBtnEntered);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                exitMouse(backBtn, backBtnBasic);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                backMain();
            }
        });

        // 뒤로가기 버튼 추가
        add(backBtn);
    }


    /**
     * 배경을 그리기 위한 메서드
     * @param g Graphics
     */
    public void paint(Graphics g) {
        screenImage = createImage(Main.SCREEN_WIDTH, Main.SCREEN_HEIGHT);   // 프로그램 화면의 크기만큼 이미지를 생성
        screenGraphic = screenImage.getGraphics();                          // screenImage 에서 생성한 그래픽 가져오기
        screenDraw((Graphics2D)screenGraphic);                                          // screenDraw 메서드 호출
        g.drawImage(screenImage, 0, 0, null);                  // 0, 0 위치에 이미지 그리기
    }


    /**
     * 배경이미지를 설정하고 paint 메서드를 재시작하기 위한 메서드
     * @param g Graphics
     */
    public void screenDraw(Graphics2D g) {
        g.drawImage(background, 0, 0, null);               // 0, 0 위치에 이미지 그리기

        if (isMainScreen) {
            g.drawImage(selectedImage, 340, 100, null);
            g.drawImage(titleImage, 340, 100, null);
        }

        if (isGameScreen) {
            game.screenDraw(g);
        }

        paintComponents(g);

        try {
            Thread.sleep(5);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        this.repaint();                                                       // paint 메서드 재실행
    }


    /**
     * nowSelected 변수를 가지고 trackList 에서 해당 Track 정보를 가져오는 메서드
     * @param nowSelected trackList 인덱스
     */
    public void selectTrack(int nowSelected) {
        if (selectedMusic != null) selectedMusic.close();

        titleImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + trackList.get(nowSelected).getTitleImage()))).getImage();
        selectedImage = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + trackList.get(nowSelected).getStartImage()))).getImage();
        selectedMusic = new Music(trackList.get(nowSelected).getStartMusic(), true);
        selectedMusic.start();
    }


    /**
     * 곡 선택 왼쪽 버튼의 로직 처리
     */
    public void selectLeft() {
        if (nowSelected == 0) {
            nowSelected = trackList.size() - 1;
        } else {
            nowSelected--;
        }

        selectTrack(nowSelected);
    }


    /**
     * 곡 선택 오른쪽 버튼의 로직 처리
     */
    public void selectRight() {
        if (nowSelected == trackList.size() - 1) {
            nowSelected = 0;
        } else {
            nowSelected++;
        }

        selectTrack(nowSelected);
    }


    /**
     * 게임 화면으로 전환을 위한 메서드
     * @param nowSelected 선택한 trackList 의 인덱스 값
     * @param difficulty
     */
    public void gameStart(int nowSelected, String difficulty) {
        if (selectedMusic != null) selectedMusic.close();

        isMainScreen = false;
        leftBtn.setVisible(false);
        rightBtn.setVisible(false);
        easyBtn.setVisible(false);
        hardBtn.setVisible(false);

        background = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + trackList.get(nowSelected).getGameImage()))).getImage();

        backBtn.setVisible(true);
        isGameScreen = true;

        game = new Game(trackList.get(nowSelected).getTitleName(), difficulty, trackList.get(nowSelected).getGameMusic());
        game.start();

        setFocusable(true);
    }


    /**
     * 게임 중 곡 선택 화면으로 넘어가기 위한 뒤로가기 메서드
     */
    public void backMain() {
        isGameScreen = false;
        isMainScreen = true;

        leftBtn.setVisible(true);
        rightBtn.setVisible(true);
        easyBtn.setVisible(true);
        hardBtn.setVisible(true);
        background = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "mainBackground.jpg"))).getImage();

        backBtn.setVisible(false);
        selectTrack(nowSelected);
        game.close();
    }


    /**
     * 메인 화면 (곡 선택 화면 )으로 가기 위한 메서드
     */
    public void enterMain() {
        startBtn.setVisible(false);
        quitBtn.setVisible(false);

        introMusic.close();

        Music btnEnteredMusic = new Music("enteredSound.mp3", false);
        btnEnteredMusic.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        selectTrack(0);

        background = new ImageIcon(Objects.requireNonNull(Main.class.getResource(IMAGE_ROOT + "mainBackground.jpg"))).getImage();
        isMainScreen = true;
        
        leftBtn.setVisible(true);
        rightBtn.setVisible(true);
        easyBtn.setVisible(true);
        hardBtn.setVisible(true);
    }


    /**
     *마우스를 올렸을 때의 이벤트
     * @param btn JButton (해당 버튼)
     * @param img ImageIcon (바꿀 이미지)
     */
    public void enterMouse(JButton btn, ImageIcon img) {
        btn.setIcon(img);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }


    /**
     * 마우스를 땔때의 이벤트
     * @param btn JButton (해당 버튼)
     * @param img ImageIcon (바꿀 이미지)
     */
    public void exitMouse(JButton btn, ImageIcon img) {
        btn.setIcon(img);
        btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }
}
