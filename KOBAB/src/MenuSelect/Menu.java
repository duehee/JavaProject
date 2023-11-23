package MenuSelect;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

class Menu extends JFrame {
    private FoodCheck[] fChecks;
    private JTextField tfTotal;
    private int totalPrice;

    public Menu(Food... foodList) {
        init(foodList);
        setDisplay();
        addListeners();
        showFrame();

    }
    private void init(Food...foodList) {
        // foodlist 즉, food를 내가 넣을 만큼 길이의 foodCheck의 배열을 만듬
        fChecks = new FoodCheck[foodList.length];
        for(int i = 0; i < fChecks.length; i++) {
            // food 하나하나를 foodCheck 배열에 담는다.
            fChecks[i] = new FoodCheck(foodList[i]);
        }
        tfTotal = new JTextField(20);
        tfTotal.setEditable(false);
    }
    private void setDisplay() {
        JPanel pnlCenter = new JPanel(new GridLayout(0, 1));
        for(FoodCheck btn : fChecks) {
            pnlCenter.add(btn);
        }
        pnlCenter.setBorder(new TitledBorder(
                new LineBorder(Color.GRAY, 1),
                "menu"
        ));

        JPanel pnlSouth = new JPanel();
        pnlSouth.add(new Label("가격"));
        pnlSouth.add(tfTotal);

        add(pnlCenter, BorderLayout.CENTER);
        add(pnlSouth, BorderLayout.SOUTH);
    }
    private void addListeners() {
        for(FoodCheck check : fChecks) {
            check.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {
                    FoodCheck btn = (FoodCheck)e.getSource();
                    int price = btn.getFood().getPrice();

                    // SELECTED 했을 때
                    if(e.getStateChange() == ItemEvent.SELECTED) {
                        totalPrice += price;
                        // DESELECTED 됐을 때
                    } else {
                        totalPrice -= price;
                    }
                    tfTotal.setText(String.valueOf(totalPrice));
                }
            });
        }
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(
                        Menu.this,
                        "종료합니다.",
                        "종료",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                if(choice == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
    private void showFrame() {
        setTitle("Menu");
        pack();
        setLocation(100, 0);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Menu(
                new Food("Pizza", 28000),
                new Food("Cola", 1500),
                new Food("Hamburger", 6300),
                new Food("Java", 3000)
        );
    }
}