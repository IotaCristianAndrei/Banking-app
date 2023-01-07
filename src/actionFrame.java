import javax.swing.*;
import java.awt.*;

public class actionFrame {
    private static JPanel panel;
    private static JFrame actionsFrame;
    private static JLabel welcomeText;
    private static JLabel chooseAction;
    private static JButton checkSold;
    private static JButton deposit;
    private static JButton withdraw;
    private static JButton returnButton;
    public static void createActionsFrame(){

        // JFrame config
        panel=new JPanel();
        actionsFrame=new JFrame();
        actionsFrame.setTitle("Choose Action");
        actionsFrame.setSize(600,600);
        actionsFrame.setResizable(false);
        actionsFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        actionsFrame.add(panel);

        //JPanel config
        panel.setLayout(null);
        panel.setBackground(Color.lightGray);

        //JLabel welcomeText config
        welcomeText = new JLabel("Welcome, "+ initialFrame.getName());
        welcomeText.setBounds(0,30,300,50);
        welcomeText.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeText.setFont(new Font("Arial",Font.BOLD,20));
        panel.add(welcomeText);

        //JLabel chooseAction config
        chooseAction = new JLabel("Please choose one of the following actions:");
        chooseAction.setBounds(15,120,400,50);
        chooseAction.setHorizontalAlignment(SwingConstants.CENTER);
        chooseAction.setFont(new Font("Arial",Font.BOLD,18));
        panel.add(chooseAction);

        //JButton checkSold config
        checkSold = new JButton("1. Check my balance");
        checkSold.setBounds(5,210,250,40);
        checkSold.setFont(new Font("Arial",Font.BOLD,20));
        checkSold.setFocusPainted(false);
        checkSold.setMargin(new Insets(0, 0, 0, 0));
        checkSold.setContentAreaFilled(false);
        checkSold.setBorderPainted(false);
        checkSold.setOpaque(false);
        checkSold.setCursor(new Cursor(Cursor.HAND_CURSOR));
        checkSold.addActionListener(e -> {
            actionsFrame.dispose();
            checkFrame.createCheckFrame();
        });
        panel.add(checkSold);

        //JButton deposit config
        deposit = new JButton("2. Deposit into account");
        deposit.setBounds(15,290,250,40);
        deposit.setFont(new Font("Arial",Font.BOLD,20));
        deposit.setFocusPainted(false);
        deposit.setMargin(new Insets(0, 0, 0, 0));
        deposit.setContentAreaFilled(false);
        deposit.setBorderPainted(false);
        deposit.setOpaque(false);
        deposit.setCursor(new Cursor(Cursor.HAND_CURSOR));
        deposit.addActionListener(e -> {
            actionsFrame.dispose();
            depositFrame.createDepositFrame();
        });
        panel.add(deposit);

        //JButton withdraw config
        withdraw = new JButton("3. Withdraw from account");
        withdraw.setBounds(25,370,250,40);
        withdraw.setFont(new Font("Arial",Font.BOLD,20));
        withdraw.setFocusPainted(false);
        withdraw.setMargin(new Insets(0, 0, 0, 0));
        withdraw.setContentAreaFilled(false);
        withdraw.setBorderPainted(false);
        withdraw.setOpaque(false);
        withdraw.setCursor(new Cursor(Cursor.HAND_CURSOR));
        withdraw.addActionListener(e -> {
            actionsFrame.dispose();
            withdrawFrame.createWithdrawFrame();
        });
        panel.add(withdraw);

        // JButton returnButton config
        returnButton = new JButton("Return");
        returnButton.setBounds(255,450,90,40);
        returnButton.setFont(new Font("Arial",Font.BOLD,15));
        returnButton.setBorder(BorderFactory.createLineBorder(Color.black));
        returnButton.setFocusPainted(false);
        returnButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        returnButton.addActionListener(e -> {
            actionsFrame.dispose();
            initialFrame.createLoginFrame();
        });
        panel.add(returnButton);

        actionsFrame.setVisible(true);
    }

}
