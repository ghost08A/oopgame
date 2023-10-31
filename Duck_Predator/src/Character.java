import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.awt.image.BufferedImage;
public class Character{
    public int x, y;  // ตำแหน่งของตัวละคร
    private final int size;  // ขนาดของตัวละคร
    private final int tileSize;  // ขนาดของแต่ละช่องในกริด
    private final int maxScreenCol;
    private final int maxScreenRow;
    private final boolean[][] walkableTiles;
    private Color color;
    private BufferedImage characterImage;
    boolean chack;
    private audio walk;

    public Character(int tileSize, int size,int maxScreenCol,int maxScreenRow,boolean[][] walkableTiles, Color color,String imagePath) {
        this.tileSize = tileSize;
        this.size = size;
        this.maxScreenCol = maxScreenCol;
        this.maxScreenRow = maxScreenRow;
        this.walkableTiles = walkableTiles;
        // ตั้งค่าตำแหน่งเริ่มต้นให้กับตัวละคร
        this.x = tileSize / 2 - size / 2;
        this.y = tileSize / 2 - size / 2;
        this.color = color;
        try {
            this.characterImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
       // walk = new audio("walk (mp3cut.net).mp3"); // ย้ายการสร้างคลาส audio เข้ามาใน constructor
    }


    // วาดตัวละคร
    public void draw(Graphics g) {
        g.drawImage(characterImage, x, y, size, size, null);
    }

    public void setPosition(int x,int y){
        this.x=x;
        this.y=y;
    }

    public boolean collidesWith(Character other) {
        return this.x == other.x && this.y == other.y;
    }

    // เคลื่อนที่ตัวละครไปทางซ้าย
    public void moveLeft() {
        if (x - tileSize >= 0 ) {
            if(walkableTiles[y / tileSize][(x - tileSize) / tileSize])
            {
                x -= tileSize;
                chack = true;
            }
            else {
                chack = false;
            }
            //walk.startaudio();
        }
    }

    // เคลื่อนที่ตัวละครไปทางขวา
    public void moveRight() {
        if (x + tileSize <= (tileSize * maxScreenCol) - size) {
            if(walkableTiles[y / tileSize][(x + tileSize) / tileSize])
            {
                x += tileSize;
                chack = true;
            }
            else {
                chack = false;
            }

          //  walk.startaudio();
        }
    }

    // เคลื่อนที่ตัวละครขึ้นบน
    public void moveUp() {
        if (y - tileSize >= 0) {
            if(walkableTiles[(y - tileSize) / tileSize][ x / tileSize])
            {
                y -= tileSize;
                chack = true;
            }
            else {
                chack = false;
            }

          //  walk.startaudio();
        }
    }

    // เคลื่อนที่ตัวละครลงล่าง
    public void moveDown() {
        if (y + tileSize <= (tileSize * maxScreenRow) - size) {
            if(walkableTiles[(y + tileSize) / tileSize][ x / tileSize])
            {
                y += tileSize;
                chack = true;
            }
            else {
                chack = false;
            }
            //walk.startaudio();
        }
    }
}
