import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class withdrawFrame {
    private static JPanel panel;
    private static JFrame withdrawFrame;
    private static JLabel withdrawText;
    private static JTextField withdrawAmount;
    private static JButton withdrawButton;
    private static JLabel notification1;
    private static JLabel notification2;
    private static JButton returnButton;

    public static void createWithdrawFrame(){

        // JFrame config
        panel=new JPanel();
        withdrawFrame=new JFrame();
        withdrawFrame.setTitle("Check balance");
        withdrawFrame.setSize(600,600);
        withdrawFrame.setResizable(false);
        withdrawFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        withdrawFrame.add(panel);

        //JPanel config
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        // JLabel withdrawText config
        withdrawText =new JLabel("Please specify the amount you want to withdraw:");
        withdrawText.setBounds(40,60,500,50);
        withdrawText.setHorizontalAlignment(SwingConstants.CENTER);
        withdrawText.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(withdrawText);

        // JTextField withdrawAmount config
        withdrawAmount = new JTextField();
        withdrawAmount.setFont(new Font("Arial",Font.PLAIN,15));
        withdrawAmount.setBounds(200,160,200,50);
        withdrawAmount.setBorder(BorderFactory.createLineBorder(Color.black));
        withdrawAmount.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(withdrawAmount);

        // JButton withdrawButton config
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setBounds(255,260,90,40);
        withdrawButton.setFont(new Font("Arial",Font.BOLD,15));
        withdrawButton.setBorder(BorderFactory.createLineBorder(Color.black));
        withdrawButton.setFocusPainted(false);
        withdrawButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        withdrawButton.addActionListener(e -> {
            Pattern pattern = Pattern.compile("^[1-9]\\d*$", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(withdrawAmount.getText());
            boolean matchFound = matcher.find();
            if (matchFound){
                int newAmount =checkFrame.getBalance()-Integer.parseInt(withdrawAmount.getText());
                if (newAmount>=0){
                    try {
                        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine", "root", "SteauAA2ooo");
                        Statement statement = connection.createStatement();
                        int update = statement.executeUpdate("UPDATE user SET Amount="+newAmount+" WHERE UserID='"+initialFrame.getClientID()+"'");
                        connection.close();
                    }catch (Exception g){
                        g.printStackTrace();
                    }
                    notification1.setText("<html><nobr>You successfully withdrew <font color='#90EE90'>"+withdrawAmount.getText()+" €</font></nobr></html>");
                    notification2.setText("<html><nobr>Your new balance is <font color='#90EE90'>"+newAmount+" €</font></nobr></html>");
                    withdrawAmount.setText("");
                }else {
                    notification1.setText("<html><nobr><font color='#FF0000'>Insufficient funds</font></nobr></html>");
                    notification2.setText("");
                    withdrawAmount.setText("");
                }
            }else {
                notification1.setText("<html><nobr><font color='#FF0000'>Invalid input. Please type a valid deposit amount.</font></nobr></html>");
                notification2.setText("");
                withdrawAmount.setText("");
            }
        });
        panel.add(withdrawButton);

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
            withdrawFrame.dispose();
            actionFrame.createActionsFrame();
        });
        panel.add(returnButton);

        withdrawFrame.setVisible(true);
    }
}
