import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class depositFrame {
    private static JPanel panel;
    private static JFrame depositFrame;
    private static JLabel depositText;
    private static JTextField depositAmount;
    private static JButton depositButton;

    private static JLabel notification1;
    private static JLabel notification2;
    private static JButton returnButton;
    public static void createDepositFrame(){

        // JFrame config
        panel=new JPanel();
        depositFrame=new JFrame();
        depositFrame.setTitle("Check balance");
        depositFrame.setSize(600,600);
        depositFrame.setResizable(false);
        depositFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        depositFrame.add(panel);

        //JPanel config
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        // JLabel depositText config
        depositText =new JLabel("Please specify the amount you want to deposit:");
        depositText.setBounds(40,60,450,50);
        depositText.setHorizontalAlignment(SwingConstants.CENTER);
        depositText.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(depositText);

        // JTextField depositAmount config
        depositAmount = new JTextField();
        depositAmount.setFont(new Font("Arial",Font.PLAIN,15));
        depositAmount.setBounds(200,160,200,50);
        depositAmount.setBorder(BorderFactory.createLineBorder(Color.black));
        depositAmount.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(depositAmount);

        // JButton depositButton config
        depositButton = new JButton("Deposit");
        depositButton.setBounds(255,260,90,40);
        depositButton.setFont(new Font("Arial",Font.BOLD,15));
        depositButton.setBorder(BorderFactory.createLineBorder(Color.black));
        depositButton.setFocusPainted(false);
        depositButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        depositButton.addActionListener(e -> {
            Pattern pattern = Pattern.compile("^[1-9]\\d*$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(depositAmount.getText());
            boolean matchFound = matcher.find();
            if (matchFound){
                int newAmount =checkFrame.getBalance()+Integer.parseInt(depositAmount.getText());
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine", "root", "SteauAA2ooo");
                    Statement statement = connection.createStatement();
                    int update = statement.executeUpdate("UPDATE user SET Amount="+newAmount+" WHERE UserID='"+initialFrame.getClientID()+"'");
                    connection.close();
                }catch (Exception g){
                    g.printStackTrace();
                }
                notification1.setText("<html><nobr>You successfully deposited <font color='#90EE90'>"+depositAmount.getText()+" €</font></nobr></html>");
                notification2.setText("<html><nobr>Your new balance is <font color='#90EE90'>"+newAmount+" €</font></nobr></html>");
                depositAmount.setText("");
            }else {
                notification1.setText("<html><nobr><font color='#FF0000'>Invalid input. Please type a valid deposit amount.</font></nobr></html>");
                notification2.setText("");
                depositAmount.setText("");
            }
        });
        panel.add(depositButton);

        // JLabel notification1 config
        notification1 = new JLabel("");
        notification1.setBounds(50,350,500,50);
        notification1.setHorizontalAlignment(SwingConstants.CENTER);
        notification1.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(notification1);

        // JLabel notification2 config
        notification2 = new JLabel("");
        notification2.setBounds(50,390,500,50);
        notification2.setHorizontalAlignment(SwingConstants.CENTER);
        notification2.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(notification2);

        // JButton returnButton config
        returnButton = new JButton("Return");
        returnButton.setBounds(255,450,90,40);
        returnButton.setFont(new Font("Arial",Font.BOLD,15));
        returnButton.setBorder(BorderFactory.createLineBorder(Color.black));
        returnButton.setFocusPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.addActionListener(e -> {
            depositFrame.dispose();
            actionFrame.createActionsFrame();
        });
        panel.add(returnButton);

        depositFrame.setVisible(true);
    }
}
