package View;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class Fruit extends JDialog {

    int currentFruit;
    FruitView owner;
    int maxFruitNum;

    JLabel lblTitle;
    JLabel lblImage;
    JPopupMenu popMenu;
    JMenuItem menuBack;
    JMenuItem menuFront;
    public Fruit(FruitView owner, int idx) {
        super(owner, "Do u see ?", true);
        this.owner = owner;
        currentFruit = idx;
        init();
        setDisplay();
        addListeners();
        showFrame();
    }
    private void init() {
        maxFruitNum = owner.getFruits().length;
        lblTitle = new JLabel((currentFruit + 1) + " / " + maxFruitNum, JLabel.CENTER);
        lblImage = new JLabel(owner.getImage(currentFruit));

        popMenu = new JPopupMenu();
        menuBack = new JMenuItem("뒤로");
        menuFront = new JMenuItem("앞으로");
        popMenu.add(menuBack);
        popMenu.add(menuFront);


    }
    private void setDisplay() {

        add(lblImage, BorderLayout.CENTER);
        add(lblTitle, BorderLayout.NORTH);

    }

    private void addListeners () {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent we) {
                dispose();
                owner.setVisible(true);
            }
        });

        lblImage.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popMenu.show(lblImage, e.getX(), e.getY());
                } else {
                    int half = lblImage.getWidth() / 2;
                    if (e.getX() < half) {
                        back();
                    } else {
                        front();
                    }

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource() == lblImage) {
                    lblImage.setBorder(new LineBorder(Color.GREEN, 2));
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                lblImage.setBorder(null);
            }
        });

        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource() == menuFront) {
                    front();
                } else {
                    back();
                }
            }
        };
        menuFront.addActionListener(actionListener);
        menuBack.addActionListener(actionListener);
    }
    private void back() {
        currentFruit --;
        if(currentFruit < 0) {
            currentFruit += maxFruitNum;
        }
        setting();
    }
    private void front() {
        currentFruit++;
        if (currentFruit == maxFruitNum) {
            currentFruit -= maxFruitNum;
        }
        setting();
    }
    private void setting() {
        lblTitle.setText((currentFruit + 1) + " / " + maxFruitNum);
        lblImage.setIcon(owner.getImage(currentFruit));
    }
    private void showFrame () {
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

    }
}