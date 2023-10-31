import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Timer;

public class gamePanel extends JPanel {
    private final int maxScreenCol = 16;  // จำนวนแถวสูงสุด
    private final int maxScreenRow = 13;   // จำนวนคอลัมน์สูงสุด
    private final int tileSize = 60;      // ขนาดของแต่ละช่องในกริด
    private final int charSize = 50;      // ขนาดของตัวละคร
    private boolean[][] walkableTiles;  //map
    private Character character;
    private Character character2;
    private Item boostItem;
    private gate Gate;
    //การแบ่งรอบเดิน
    private boolean isRedTurn = true;  // เริ่มต้นด้วยตัวละครสีแดง
    private int redMoves = 0;
    private int blueMoves = 0;
    private int maxredMoves = 2;
    private int maxblueMoves = 4;
    private int winB = 0;
    private String info = "DUCK remaining rounds: " + (maxredMoves-redMoves);
    private Timer gameTimer;
    private int gameTime = 0;
    private int minutegameTime = 0;

    private int selectedMap=1;


    public gamePanel(int selectedMap) {
        this.setPreferredSize(new Dimension(tileSize * maxScreenCol, tileSize * maxScreenRow));
        this.setFocusable(true);
        this.requestFocus();
        this.selectedMap=selectedMap;
        initializeMap();
        character = new Character(tileSize, charSize, maxScreenCol, maxScreenRow, walkableTiles, Color.RED,"Duck.png");
        character2 = new Character(tileSize, charSize, maxScreenCol, maxScreenRow, walkableTiles, Color.BLUE,"P_B.png");
        character2.setPosition(15*tileSize+(tileSize / 2 - charSize / 2), 10*tileSize+(tileSize / 2 - charSize / 2));
        //Item boostItem = new Item(tileSize, charSize, walkableTiles, "Duck.png");
        boostItem = new Item(tileSize, charSize,walkableTiles,"egg.png");
        Gate = new gate(tileSize, charSize,walkableTiles,"gate_2d.png");

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (isRedTurn) {
                    if (key == KeyEvent.VK_LEFT && redMoves < maxredMoves) {
                        character.moveLeft();
                        if(character.chack){
                            redMoves++;
                        }
                        if(Gate.isPickedUp(character)){
                            Gate.spawn(character);
                        }
                        if(boostItem.isPickedUp(character)){
                            maxredMoves++;
                            boostItem.spawn();
                        }
                    } if (key == KeyEvent.VK_RIGHT && redMoves < maxredMoves) {
                        character.moveRight();
                        if(character.chack){
                            redMoves++;
                        }
                        if(Gate.isPickedUp(character)){
                            Gate.spawn(character);
                        }
                        if(boostItem.isPickedUp(character)){
                            maxredMoves++;
                            boostItem.spawn();
                        }
                    } if (key == KeyEvent.VK_UP && redMoves < maxredMoves) {
                        character.moveUp();
                        if(character.chack){
                            redMoves++;
                        }
                        if(Gate.isPickedUp(character)){
                            Gate.spawn(character);
                        }
                        if(boostItem.isPickedUp(character)){
                            maxredMoves++;
                            boostItem.spawn();
                        }
                    } if (key == KeyEvent.VK_DOWN && redMoves < maxredMoves) {
                        character.moveDown();
                        if(character.chack){
                            redMoves++;
                        }
                        if(Gate.isPickedUp(character)){
                            Gate.spawn(character);
                        }
                        if(boostItem.isPickedUp(character)){
                            if(character.chack){
                                redMoves++;
                            }
                            boostItem.spawn();
                        }

                    }
                    if(maxredMoves-redMoves!=0){
                        info = "DUCK remaining rounds: "  + (maxredMoves-redMoves);
                    }
                    else{
                        info = "B TURN";
                    }

                    System.out.println("รอบของสีแดง จำนวนก้าวเหลือ " + (maxredMoves-redMoves));
                    if (redMoves == maxredMoves) {
                        isRedTurn = false;
                        redMoves = 0;
                    }
                }
                else {
                    // คีย์คอนโทรลสำหรับตัวละครฺB


                    if (key == KeyEvent.VK_W && blueMoves < maxblueMoves) {
                        character2.moveUp();
                        if(character2.chack){
                            blueMoves++;
                        }
                        if(Gate.isPickedUp(character2)){
                            Gate.spawn(character2);
                        }
                        if(boostItem.isPickedUp(character2)){
                            winB++;
                            boostItem.spawn();
                        }
                    }
                    if (key == KeyEvent.VK_S && blueMoves < maxblueMoves) {
                        character2.moveDown();
                        if(character2.chack){
                            blueMoves++;
                        }
                        if(Gate.isPickedUp(character2)){
                            Gate.spawn(character2);
                        }
                        if(boostItem.isPickedUp(character2)){
                            winB++;
                            boostItem.spawn();
                        }
                    }
                    if (key == KeyEvent.VK_A && blueMoves < maxblueMoves) {
                        character2.moveLeft();
                        if(character2.chack){
                            blueMoves++;
                        }
                        if(Gate.isPickedUp(character2)){
                            Gate.spawn(character2);
                        }
                        if(boostItem.isPickedUp(character2)){
                            winB++;
                            boostItem.spawn();
                        }
                    }
                    if (key == KeyEvent.VK_D && blueMoves < maxblueMoves) {
                        character2.moveRight();
                        if(character2.chack){
                            blueMoves++;
                        }
                        if(Gate.isPickedUp(character2)){
                            Gate.spawn(character2);
                        }
                        if(boostItem.isPickedUp(character2)){
                            winB++;
                            boostItem.spawn();
                        }
                    }
                    if(winB==3){
                        maxblueMoves=5;
                    }
                    if(maxblueMoves-blueMoves!=0){
                        info = "B remaining rounds: "  + (maxblueMoves-blueMoves);
                    }
                    else{
                        info = "DUCK TURN";
                    }

                    System.out.println("รอบของสีฟ้า จำนวนก้าวเหลือ " + (maxblueMoves-blueMoves));
                    if (blueMoves == maxblueMoves) {
                        isRedTurn = true;
                        blueMoves = 0;
                        //Gate.spawn();
                    }
                }
                // ตรวจสอบการชน
                if (character.collidesWith(character2)) {
                    // ตัวละครสีแดงชนะ
                    boostItem.spawn();
                    showWinnerDialog("DUCK");
                }

                if(winB==5)
                {
                    winB=0;
                    showWinnerDialog("B");
                }
                repaint();
            }
        });
        int delay = 1000; // กำหนดเวลาในหน่วยมิลลิวินาที (1000 คือ 1 วินาที)
        gameTimer = new Timer(delay, event -> {
            // ใส่โค้ดที่คุณต้องการให้ทำงานทุกครั้งเมื่อเวลาผ่านไป
            updateGame();
        });
        gameTimer.start(); // เริ่มต้นตัวจับเวลา
    }
    private void updateGame() {
        gameTime++;
        if (gameTime==60)
        {
            minutegameTime++;
            gameTime=0;
        }
        repaint(); // รีเพนต์ panel เพื่อแสดงการเปลี่ยนแปลง
    }

    public void stopTimer() {
        if (gameTimer != null) {
            gameTimer.stop();
        }
    }
    private void initializeMap() {
        if(selectedMap==0) {
            walkableTiles = new boolean[][]{
                    // true คือเดินผ่านได้, false คือเดินผ่านไม่ได้
                    /*0*/{true, true, true, false, false, true, false, true, true, true, false, true, true, true, false, true,},
                    /*1*/{true, false, true, true, false, true, true, true, false, true, true, false, true, true, true, true,},
                    /*2*/{true, false, true, true, true, true, true, true, false, true, true, true, true, false, true, false,},
                    /*3*/{true, true, false, false, true, false, true, true, true, true, false, true, true, true, true, true,},
                    /*4*/{false, true, true, true, true, true, true, true, false, true, true, false, true, true, false, false,},
                    /*5*/{true, true, true, true, false, true, false, false, true, false, true, true, true, true, true, true,},
                    /*6*/{false, true, true, true, true, true, true, true, true, false, true, false, true, false, true, false,},
                    /*7*/{true, true, false, true, false, true, false, false, true, false, true, false, true, true, true, true,},
                    /*8*/{true, true, false, true, true, true, true, true, false, true, true, true, false, true, false, true,},
                    /*9*/{false, true, true, true, false, false, true, true, true, true, false, true, true, true, true, false,},
                    /*10*/{true, true, false, true, false, true, false, true, true, true, true, false, true, true, false, true,},
                    /*11*/{true, true, false, true, true, true, false, true, false, true, false, true, true, true, true, true,},
                    /*12*/{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},

            };
        } else if(selectedMap==1){
            walkableTiles = new boolean[][] {
                    // true คือเดินผ่านได้, false คือเดินผ่านไม่ได้
                    /*0*/{true, true, true, false,false, true, true, false,true, false, true, true,true, false, true, true},
                    /*1*/{true, false, true, true,true, false, true, true,true, true, true, true,false, false, true, false,},
                    /*2*/{true, true, true, true,false, true, false, false,true, true, false, true,true, true, true, true,},
                    /*3*/{true, true, false, true,false, true, true, false,true, true, false, true,true, false, false, true,},
                    /*4*/{true, false, true, true,true, true, false, false,true, true, true, true,true, true, true, false,},
                    /*5*/{true, true, true, true,false, true, true, true,true, true, false, true,true, true, false, true,},
                    /*6*/ {true, false, true, true,true, false, false, false,false, false, true, true,true, true, true, true,},
                    /*7*/ {true, true, false, true,true, true, false, false,false, true, true, true,false, false, true, false,},
                    /*8*/{true, false, true, true,true, true, true, true,true, true, true, true,true, true, true, true,},
                    /*9*/{true, true, true, true,false, false, true, true,false, true, false, false,true, true, false, true,},
                    /*10*/{false, false, false, true,true, false, true, false,false, true, true, true,true, true, false, true,},
                    /*11*/{true, true, true, true,false, true, true, true,false, true, false, true,true, true, false, false,},
                    /*12*/{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},

            };
        }
        else if(selectedMap==2){
            walkableTiles = new boolean[][] {
                    // true คือเดินผ่านได้, false คือเดินผ่านไม่ได้
                    /*0*/{true, false, true, false,false, true, true, false,true, true, false, true,true, true, false, true},
                    /*1*/{true, false, true, false,true, true, true, true,true, true, false, false,true, false, true, true},
                    /*2*/{true, true, true, true,true, false, true, false,true, false, true, true,true, true, true, false},
                    /*3*/{true, false, false, true,true, false, true, true,true, true, true, true,false, true, true, true},
                    /*4*/{true, true, true, true,true, true, true, true,true, false, false, false,true, false, true, true},
                    /*5*/{false, false, true, false,false, true, true, false,true, true, true, false,true, true, true, false},
                    /*6*/{true, true, true, false,true, true, false, true,true, false, true, false,true, true, false, true},
                    /*7*/{false, false, true, true,true, true, true, true,true, false, true, false,true, true, true, true},
                    /*8*/{true, true, true, true,true, false, true, false,true, true, false, true,true, false, true, false},
                    /*9*/{true, true, false, true,true, true, true, true,true, true, true, false,true, true, true, true},
                    /*10*/{false, true, false, true,true, false, false, true,false, true, true, true,true, false, true, true},
                    /*11*/{true, true, true, true,true, true, true, true,true, false, true, false,true, false, false, false},
                    /*12*/{false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},

            };
        }
    }
    private void showWinnerDialog(String winnerName) {
        int option = JOptionPane.showOptionDialog(this,
                winnerName + " WIN!",
                "ENG GAME",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new String[] {"New Game", "EXIT"},
                null);
        stopTimer();
        if (option == JOptionPane.YES_OPTION) {
            resetGame(); // เริ่มเกมใหม่
        } else {
            System.exit(0); // ออกจากเกม
        }
    }
    public void resetGame() {
        // ตั้งค่าเริ่มต้นใหม่สำหรับตัวละครและเกม
        isRedTurn = true;
        blueMoves=0;
        redMoves=0;
        maxredMoves=2;
        maxblueMoves=4;
        winB=0;
        character.setPosition(0*tileSize+(tileSize / 2 - charSize / 2), 0*tileSize+(tileSize / 2 - charSize / 2));
        character2.setPosition(15*tileSize+(tileSize / 2 - charSize / 2), 10*tileSize+(tileSize / 2 - charSize / 2));
        Gate.spawn();
        repaint(); // วาดจอใหม่
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // วาดกริด
        for (int row = 0; row < maxScreenRow; row++) {
            for (int col = 0; col < maxScreenCol; col++) {
                if(row<maxScreenRow-1){
                    if (walkableTiles[row][col]) {
                        g.setColor(Color.CYAN);
                    } else {
                        g.setColor(Color.BLACK);
                    }
                }
                else{
                    g.setColor(Color.BLACK);
                }
                g.fillRect(col * tileSize, row * tileSize, tileSize, tileSize);
            }
        }
        // ปรับเปลี่ยนฟ้อนท์สำหรับการวาดข้อความ
        g.setFont(new Font("Serif", Font.PLAIN, 30)); // เปลี่ยนชื่อฟ้อนท์, รูปแบบ, และขนาดตามต้องการ
        g.setColor(Color.WHITE); // ปรับเปลี่ยนสีเป็นสีขาว
        g.drawString(info, 1*tileSize-40, 12*tileSize+40); // ตำแหน่ง (12*tileSize, 15*tileSize) ควรเปลี่ยนเป็นตามที่คุณต้องการ
        g.drawString("TIME "+minutegameTime+":"+gameTime, 8*tileSize, 12*tileSize+40);
        g.drawString("B egg : "+winB, 13*tileSize, 12*tileSize+40);
        character.draw(g);
        character2.draw(g);
        boostItem.draw(g);
        Gate.draw(g);
    }
}
