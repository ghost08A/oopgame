import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JComboBox<String> mapSelector;
    public Main() {
        setTitle("SDuck Predator");
        setSize(500, 300);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // ตั้งค่าพื้นหลัง
        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Duck.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        setContentPane(background);
        setLayout(new BorderLayout());

        // ตั้งค่าฟอนต์
        Font retroFont;
        try {
            retroFont = Font.createFont(Font.TRUETYPE_FONT, getClass().getResource("/OldSchoolAdventures-42j9.ttf").openStream());
            GraphicsEnvironment genv = GraphicsEnvironment.getLocalGraphicsEnvironment();
            genv.registerFont(retroFont);
        } catch (Exception e) {
            e.printStackTrace();
            retroFont = new Font("Serif", Font.PLAIN, 24);
        }

        JLabel titleLabel = new JLabel("Duck Predator");
        titleLabel.setFont(retroFont.deriveFont(35f));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mapSelector = new JComboBox<>(new String[]{"Map 1", "Map 2", "Map 3"});
        mapSelector.setPreferredSize(new Dimension(150, 30));
        mapSelector.setFont(retroFont.deriveFont(20f));
        JButton startGameBtn = new JButton("Start Game");
        startGameBtn.setPreferredSize(new Dimension(200, 50));
        startGameBtn.setFont(retroFont.deriveFont(20f));
        startGameBtn.setBackground(Color.DARK_GRAY);
        startGameBtn.setForeground(Color.WHITE);
        startGameBtn.setBorder(BorderFactory.createRaisedBevelBorder());

        startGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedMap = mapSelector.getSelectedIndex();
                JFrame gameWindow = new JFrame("Duck Predator");
                gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                gameWindow.setResizable(false);
                gamePanel GamePanel = new gamePanel(selectedMap);
                gameWindow.add(GamePanel);
                gameWindow.pack();
                gameWindow.setLocationRelativeTo(null);
                gameWindow.setVisible(true);
                dispose();
            }
        });

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.add(mapSelector);
        centerPanel.add(startGameBtn);

        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}