import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class checkFrame {
    private static JPanel panel;
    private static JFrame checkFrame;
    private static JLabel balance;

    private static JButton returnButton;
    public static int getBalance(){
        int amount=0;
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine", "root", "SteauAA2ooo");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT Amount FROM user WHERE UserID='"+initialFrame.getClientID()+"'");
            while (resultSet.next()){
                amount = ((Number)resultSet.getObject(1)).intValue();
            }
            connection.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return amount;
    }
    public static void createCheckFrame(){


        // JFrame config
        panel=new JPanel();
        checkFrame=new JFrame();
        checkFrame.setTitle("Check balance");
        checkFrame.setSize(600,600);
        checkFrame.setResizable(false);
        checkFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        checkFrame.add(panel);

        //JPanel config
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        // JLabel config
        balance = new JLabel("<html><nobr>Your current balance is <font color='#90EE90'>"+getBalance()+" €</font></nobr></html>");
        balance.setBounds(125,200,350,50);
        balance.setHorizontalAlignment(SwingConstants.CENTER);
        balance.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(balance);

        // JButton returnButton config
        returnButton = new JButton("Return");
        returnButton.setBounds(255,350,90,40);
        returnButton.setFont(new Font("Arial",Font.BOLD,15));
        returnButton.setBorder(BorderFactory.createLineBorder(Color.black));
        returnButton.setFocusPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.addActionListener(e -> {
            checkFrame.dispose();
            actionFrame.createActionsFrame();
        });
        panel.add(returnButton);

        checkFrame.setVisible(true);
    }
}
