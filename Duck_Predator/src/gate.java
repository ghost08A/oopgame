import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
public class gate {
    private int x, y;
    private final int size;
    private final int tileSize;
    private final boolean[][] walkableTiles;
    private BufferedImage characterImage;

    public gate(int tileSize, int size, boolean[][] walkableTiles, String imagePath){
        this.tileSize = tileSize;
        this.size = size;
        this.walkableTiles = walkableTiles;
        try {
            this.characterImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        spawn();
    }

    public void draw(Graphics g) {
        g.drawImage(characterImage, x, y, size, size, null);
    }

    public boolean isPickedUp(Character character) {
        return this.x == character.x && this.y == character.y;
    }
    public void spawn(Character character) {
        Random rand = new Random();
        int row, col;

        do {
            row = rand.nextInt(walkableTiles.length);
            col = rand.nextInt(walkableTiles[0].length);
        } while (!walkableTiles[row][col]);

        character.x = col *tileSize+(tileSize / 2 - size / 2);
        character.y = row *tileSize+(tileSize / 2 - size / 2);
    }

    public void spawn() {
        Random rand = new Random();
        int row, col;
        do {
            row = rand.nextInt(walkableTiles.length);
            col = rand.nextInt(walkableTiles[0].length);
        } while (!walkableTiles[row][col]);
        this.x = col *tileSize+(tileSize / 2 - size / 2);
        this.y = row *tileSize+(tileSize / 2 - size / 2);
    }
}
