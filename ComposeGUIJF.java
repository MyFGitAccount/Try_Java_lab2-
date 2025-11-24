import java.awt.*;
import javax.swing.*;

class ComposeGUIJF extends JFrame {
    private JButton[] btn = new JButton[6];
    private JLabel bottomLabel;
    private int ClickCounter=0;
    public void ListenBtnClick() {
        for (int i = 0; i < btn.length; i++) {
            btn[i].addActionListener(e -> {
                ClickCounter++;
                bottomLabel.setText("Clicked by: " + ((JButton) e.getSource()).getText()+" with times:"+ClickCounter);
                JOptionPane.showMessageDialog(this, "Top Button clicked with times:"+ClickCounter);
            });
        }
    }

    public ComposeGUIJF() {
        super("Compose GUI JFrame SubClass");
        setSize(600, 600);

        ImageIcon imageIcon = new ImageIcon("cc.jpg");
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        JLabel CClabel = new JLabel(imageIcon);

        JLabel LeftBLabel = new JLabel("LeftB", SwingConstants.CENTER);
        JLabel RightBLabel = new JLabel("RightB", SwingConstants.CENTER);
        LeftBLabel.setOpaque(true);
        RightBLabel.setOpaque(true);
        LeftBLabel.setBackground(new Color(255, 200, 100));
        RightBLabel.setBackground(new Color(255, 200, 100));

        JPanel topPanel = new JPanel(new FlowLayout());
        for (int i = 0; i < btn.length; i++) {
            btn[i] = new JButton("TopB-" + i);
            topPanel.add(btn[i]);
        }

        bottomLabel = new JLabel("No button clicked yet", SwingConstants.CENTER);
        bottomLabel.setOpaque(true);
        bottomLabel.setBackground(new Color(220, 220, 220));
        JScrollPane scrollPane = new JScrollPane(topPanel);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(LeftBLabel, BorderLayout.WEST);
        getContentPane().add(RightBLabel, BorderLayout.EAST);
        getContentPane().add(CClabel, BorderLayout.CENTER);
        getContentPane().add(bottomLabel, BorderLayout.SOUTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.GRAY);
        setVisible(true);
    }

    public static void main(String[] args) {
        ComposeGUIJF aGUI = new ComposeGUIJF();
        aGUI.setResizable(true);
        aGUI.ListenBtnClick();
    }
}
