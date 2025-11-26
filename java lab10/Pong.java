cat Pong.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Pong extends JFrame {
    private int leftScore = 0;
    private int rightScore = 0;
    private int ballX = 450, ballY = 250;
    private int ballDX = 3, ballDY = 3;
    private int leftY = 200, rightY = 200;
    private final int PADDLE_HEIGHT = 300;
    private final int PADDLE_WIDTH = 10;
    private final int BALL_SIZE = 50;
    private Timer timer;

    Pong(String title) {
        super(title);
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        GamePanel gamePanel = new GamePanel();
        add(gamePanel);

        // Game loop: updates every 10ms
        timer = new Timer(10, e -> {
            updateGame();
            gamePanel.repaint();
        });
        timer.start();

        // Keyboard controls
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> leftY -= 30;
                    case KeyEvent.VK_S -> leftY += 30;
                    case KeyEvent.VK_UP -> rightY -= 30;
                    case KeyEvent.VK_DOWN -> rightY += 30;
                }
            }
        });

        setVisible(true);
    }

    private void updateGame() {
        // Move ball
        ballX += ballDX;
        ballY += ballDY;

        // Bounce off top/bottom
        if (ballY <= 0 || ballY >= getHeight() - BALL_SIZE/2) {
            ballDY = -ballDY;
        }

        // Paddle collisions
        Rectangle leftPaddle = new Rectangle(50, leftY, PADDLE_WIDTH, PADDLE_HEIGHT);
        Rectangle rightPaddle = new Rectangle(getWidth() - 70, rightY, PADDLE_WIDTH, PADDLE_HEIGHT);
        Rectangle ballRect = new Rectangle(ballX, ballY, BALL_SIZE, BALL_SIZE);

        if (ballRect.intersects(leftPaddle) || ballRect.intersects(rightPaddle)) {
            ballDX = -ballDX;
        }

        // Scoring
        if (ballX - BALL_SIZE/2< 0) {
            rightScore++;
            resetBall();
        } else if (ballX + BALL_SIZE/2 > getWidth()) {
            leftScore++;
            resetBall();
        }

        // Game over
        if (leftScore == 5 || rightScore == 5) {
            timer.stop();
            JOptionPane.showMessageDialog(this,
                (leftScore == 5 ? "Left Player Wins!" : "Right Player Wins!"),
                "Game Over", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }

    private void resetBall() {
        ballX = getWidth() / 2;
        ballY = getHeight() / 2;
        ballDX = -ballDX; // send ball toward scorer
    }

    class GamePanel extends JPanel {
        public GamePanel() {
            setBackground(Color.BLACK);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);

            // Draw paddles
            g.fillRect(50, leftY, PADDLE_WIDTH, PADDLE_HEIGHT);
            g.fillRect(getWidth() - 70, rightY, PADDLE_WIDTH, PADDLE_HEIGHT);

            // Draw ball
            g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);

            // Draw score
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.drawString(leftScore + " : " + rightScore, getWidth() / 2 - 40, 40);
        }
    }

    public static void main(String[] args) {
        new Pong("Pong Game");
    }
}
