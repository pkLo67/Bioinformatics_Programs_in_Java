import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class NameFrameGUI extends JFrame {
    private JLabel firstNameLabel;
    private JLabel lastNameLabel;
    private JLabel authorLabel;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField fullNameTextField;
    private JButton nameButton;
    private JButton clearButton;

    public NameFrameGUI() {

        super("Full Name Generator");
        setLayout(new FlowLayout());

        firstNameLabel = new JLabel("First Name:");
        add(firstNameLabel);

        firstNameTextField = new JTextField(13);
        add(firstNameTextField);

        lastNameLabel = new JLabel("Last Name:");
        add(lastNameLabel);

        lastNameTextField = new JTextField(13);
        add(lastNameTextField);

        fullNameTextField = new JTextField(26);
        add(fullNameTextField);

        nameButton = new JButton("Name");
        add(nameButton);

        clearButton = new JButton("Clear");
        add(clearButton);

        authorLabel = new JLabel("GUI Author: Pang-Kuo Lo");
        add(authorLabel);

        ButtonHandler handler = new ButtonHandler();
        nameButton.addActionListener(handler);
        clearButton.addActionListener(handler);
    }

    private class ButtonHandler implements ActionListener {

        public void actionPerformed(ActionEvent event) {

            if (event.getSource() == nameButton) {
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String fullName = firstName + " " + lastName;
                fullNameTextField.setText("The Full Name is: " + fullName);
            } else if (event.getSource() == clearButton) {
                firstNameTextField.setText("");
                lastNameTextField.setText("");
                fullNameTextField.setText("");
            }
        }
    }

    
    public static void main(String[] args) {
        NameFrameGUI gui = new NameFrameGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(450, 150);
        gui.setVisible(true);
    }

}
