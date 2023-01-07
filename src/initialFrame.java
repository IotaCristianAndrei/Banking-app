import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class initialFrame {
    private static JPanel panel;
    private static JFrame loginFrame;
    private static JLabel idText;
    private static JTextField idInput;
    private static JButton loginButton;
    private static JLabel invalidID;

    private static String name;

    public static String getName(){
        return name;
    }

    public static String getClientID(){
        return idInput.getText();
    }

    public static void createLoginFrame(){

        // JFrame config
        panel=new JPanel();
        loginFrame=new JFrame();
        loginFrame.setTitle("Bank Login");
        loginFrame.setSize(600,600);
        loginFrame.setResizable(false);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.add(panel);

        // JPanel config
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        // JLabel idText config
        idText=new JLabel("Client ID:");
        idText.setBounds(250,190,100,50);
        idText.setHorizontalAlignment(SwingConstants.CENTER);
        idText.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(idText);

        // JTextField idInput config
        idInput=new JTextField();
        idInput.setFont(new Font("Arial",Font.PLAIN,15));
        idInput.setBounds(200,240,200,50);
        idInput.setBorder(BorderFactory.createLineBorder(Color.black));
        idInput.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(idInput);

        //JLabel invalidID config
        invalidID=new JLabel();
        invalidID.setBounds(150,420,300,50);
        invalidID.setHorizontalAlignment(SwingConstants.CENTER);
        invalidID.setFont(new Font("Arial",Font.BOLD,15));
        panel.add(invalidID);

        // JButton loginButton config
        loginButton=new JButton("Access");
        loginButton.setBounds(255,350,90,40);
        loginButton.setFont(new Font("Arial",Font.BOLD,15));
        loginButton.setBorder(BorderFactory.createLineBorder(Color.black));
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addActionListener(e -> {
            try {
                String idValue=idInput.getText();
                name="";
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/atm_machine", "root", "SteauAA2ooo");
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT COUNT(UserID) FROM user WHERE UserID='"+idValue+"'");
                Statement statement1 = connection.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("SELECT FullName FROM user WHERE UserID='"+idValue+"'");

                int val=0;

                while (resultSet.next()){
                     val= ((Number) resultSet.getObject(1)).intValue();
                }
                while (resultSet1.next()){
                    name = resultSet1.getObject(1).toString();
                }
                if (val == 1){
                    loginFrame.dispose();
                    actionFrame.createActionsFrame();
                }else{
                    invalidID.setText("Invalid customer ID. Please try again.");
                    invalidID.setForeground(Color.red);
                    idInput.setText("");

                    resultSet.close();
                    resultSet1.close();
                    statement.close();
                    statement1.close();
                    connection.close();
                }

            } catch (Exception g){
                g.printStackTrace();
            }

        });
        panel.add(loginButton);

        loginFrame.setVisible(true);
    }

}


