package Board;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.IOException;

public class Board extends JFrame {
    private JTextField titleField;
    private JTextArea contentArea;
    private DefaultListModel<String> postListModel;
    private JList<String> postList;
    private List<String> contents;

    public Board() {
        setTitle("KOBAB");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);

        contents = new ArrayList<>();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(900, 30));
        contentArea = new JTextArea();
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentArea);
        contentScrollPane.setMaximumSize(new Dimension(900, 400));

        
        
        JButton addButton = new JButton("게시글 작성");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPost();
            }
        });

        JButton rouletteButton = new JButton("오늘의 메뉴 룰렛 돌리기");
        rouletteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                spinRoulette();
            }
        });

        postListModel = new DefaultListModel<>();
        postList = new JList<>(postListModel);
        postList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        postList.addListSelectionListener(e -> {
            int index = postList.getSelectedIndex();
            if (index >= 0) {
                String content = contents.get(index);
                JOptionPane.showMessageDialog(null, content, "게시글 내용", JOptionPane.PLAIN_MESSAGE);
            }
        });
        JScrollPane listScrollPane = new JScrollPane(postList);
        listScrollPane.setMaximumSize(new Dimension(900, 350));
        
        
        
        panel.add(new JLabel("제목:"));
        panel.add(titleField);
        panel.add(new JLabel("내용:"));
        panel.add(contentScrollPane);
        panel.add(addButton);
        panel.add(new JLabel("게시글 목록:"));
        panel.add(listScrollPane);
        panel.add(rouletteButton);

        add(panel);
        setVisible(true);

        Font customFont = new Font("CookieRun Regular", Font.PLAIN, 14);

        // 컴포넌트에 적용할 폰트 설정
        titleField.setFont(customFont);
        contentArea.setFont(customFont);
        postList.setFont(customFont);
        addButton.setFont(customFont);
        rouletteButton.setFont(customFont);
        panel.setFont(customFont);
    }

    private void addPost() {
        String title = titleField.getText();
        String content = contentArea.getText();
        if (!title.isEmpty() && !content.isEmpty()) {
            postListModel.addElement(title);
            contents.add(content);
            titleField.setText("");
            contentArea.setText("");
        }
    }

    private void spinRoulette() {
        String[] menu = {"김치찌개", "돈까스", "마라탕", "햄버거", "치킨", "제육덮밥", "칼국수", "삼겹살", "갈비", "자장면"};
        Random random = new Random();
        int selected = random.nextInt(menu.length);

        JOptionPane.showMessageDialog(null, "오늘의 메뉴는 " + menu[selected] + "입니다!", "오늘의 메뉴", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Board().setVisible(true);
        });
    }
}