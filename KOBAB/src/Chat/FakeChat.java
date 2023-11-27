package Chat;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FakeChat extends JFrame {
	private JTextArea taOutput;
	private JTextField tfInput;
	
	public FakeChat() {
		taOutput = new JTextArea();
		taOutput.setEditable(false);
		
		tfInput = new JTextField(30);
		
		add(new JScrollPane(taOutput), BorderLayout.CENTER);
		add(tfInput, BorderLayout.SOUTH);
		
		tfInput.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				String text = ((JTextField)ae.getSource()).getText();
				//taOutput.setText(taOutput.getText() + text + "\n");
				taOutput.append(text + "\n");
				tfInput.setText("");
			}
		});
		
		setTitle("Fake Chat");
		setSize(500,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new FakeChat();
	}
}