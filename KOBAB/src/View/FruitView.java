package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FruitView extends JFrame {
    private String[] fruits;
    private JLabel[] labels;
    private JPanel pnl1;

    public FruitView() {
        init();
        setDisplay();
        addListeners();
        showFrime();
    }
    public String[] getFruits() {
        return fruits;
    }
    private void init() {
        fruits = new String[] { "apple", "asparagus", "banana", "broccoli", "cantaloupe", "carrot", "corn", "grapefruit",
                "grapes", "kiwi", "onion", "peach", "pear", "pepper", "pickle", "pineapple", "raspberry", "strawberry",
                "tomato", "watermelon" };
    }
    private void setDisplay() {
        pnl1 = new JPanel(new GridLayout(5, 4));
        labels = new JLabel[fruits.length];

        for (int idx = 0; idx < fruits.length; idx++) {
            ImageIcon icon = new ImageIcon(fruits[idx] + ".jpg");
            labels[idx] = new JLabel(icon);
            labels[idx].setToolTipText("Click the " + fruits[idx]);
            pnl1.add(labels[idx]);
        }

        add(pnl1, BorderLayout.CENTER);

    }
    private void addListeners() {
        MouseListener mouseListener = new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                for(int idx = 0; idx < labels.length; idx++) {
                    if((JLabel) e.getSource() == labels[idx]) {
                        setVisible(false);
                        new Fruit(FruitView.this, idx);
                    }
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel select = (JLabel)e.getSource();

                for(JLabel lable : labels) {
                    if(select == lable) {
                        lable.setBorder(new LineBorder(Color.GREEN, 2));
                    }
                }
                pnl1.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                for(JLabel lable : labels) {
                    lable.setBorder(null);
                }
            }
        };
        for(JLabel lable : labels) {
            lable.addMouseListener(mouseListener);
        }
    }
    public ImageIcon getImage(int idx) {
        Image img = ((ImageIcon)labels[idx].getIcon()).getImage();
        Image newImage = img.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);

    }
    private void showFrime() {
        setTitle("Viewer");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        new FruitView();
    }
}