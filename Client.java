package com.java.chatapp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends JFrame implements ActionListener {
    JPanel panel1;
    JTextField messageBox;
    JButton sendButton;
    static JTextArea message;

    static Socket s;
    static DataInputStream dataIn;
    static DataOutputStream dataOut;


    Client() {

        panel1 = new JPanel();
        panel1.setLayout(null);
        panel1.setBackground(new Color(7, 94, 84));
        panel1.setBounds(0, 0, 450, 70);
        add(panel1);

        // adding arrow icon
        ImageIcon arrowIcon = new ImageIcon(ClassLoader.getSystemResource("com/java/chatapp/icons/3.png"));
        Image arrowImage = arrowIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon arrowIcon2 = new ImageIcon(arrowImage);
        JLabel arrowLabel = new JLabel(arrowIcon2);
        arrowLabel.setBounds(5, 17, 30, 30);
        panel1.add(arrowLabel);

        // arrow close event
        arrowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        // adding user icon
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource("com/java/chatapp/icons/man.png"));
        Image userImage = userIcon.getImage().getScaledInstance(60, 60, Image.SCALE_DEFAULT);
        ImageIcon userIcon2 = new ImageIcon(userImage);
        JLabel userLabel = new JLabel(userIcon2);
        userLabel.setBounds(40, 5, 60, 60);
        panel1.add(userLabel);

        // adding user Label
        JLabel userName = new JLabel("Client");
        userName.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        userName.setForeground(Color.WHITE);
        userName.setBounds(110, 15, 100, 18);
        panel1.add(userName);

        //adding user status
        JLabel userStatus = new JLabel("Active Now");
        userStatus.setFont(new Font("SAN_SERIF", Font.PLAIN, 10));
        userStatus.setForeground(Color.WHITE);
        userStatus.setBounds(112, 33, 100, 20);
        panel1.add(userStatus);

        // adding video icon
        ImageIcon videoIcon = new ImageIcon(ClassLoader.getSystemResource("com/java/chatapp/icons/video.png"));
        Image videoImage = videoIcon.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon videoIcon2 = new ImageIcon(videoImage);
        JLabel videoLabel = new JLabel(videoIcon2);
        videoLabel.setBounds(290, 20, 35, 30);
        panel1.add(videoLabel);

        // adding phone icon
        ImageIcon phoneIcon = new ImageIcon(ClassLoader.getSystemResource("com/java/chatapp/icons/phone.png"));
        Image phoneImage = phoneIcon.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon phoneIcon2 = new ImageIcon(phoneImage);
        JLabel phoneLabel = new JLabel(phoneIcon2);
        phoneLabel.setBounds(350, 20, 35, 30);
        panel1.add(phoneLabel);

        // more option aka 3 dots icon
        ImageIcon moreIcon = new ImageIcon(ClassLoader.getSystemResource("com/java/chatapp/icons/3icon.png"));
        Image moreImage = moreIcon.getImage().getScaledInstance(13, 25, Image.SCALE_DEFAULT);
        ImageIcon moreIcon2 = new ImageIcon(moreImage);
        JLabel moreLabel = new JLabel(moreIcon2);
        moreLabel.setBounds(410, 20, 13, 25);
        panel1.add(moreLabel);

        // creating message inside body
        message = new JTextArea();
        message.setBounds(5, 75, 440, 575);
        message.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        message.setEditable(false);
        message.setLineWrap(true);
        message.setWrapStyleWord(true);
        add(message);

        // creating message box
        messageBox = new JTextField();
        messageBox.setBounds(5, 655, 310, 40);
        messageBox.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        add(messageBox);

        // creating send button
        sendButton = new JButton("Send");
        sendButton.setBounds(320, 655, 125, 40);
        sendButton.setBackground(new Color(7, 94, 84));
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        sendButton.addActionListener(this);
        add(sendButton);

        getContentPane().setBackground(new Color(236, 229, 221));
        setLayout(null);
        setSize(450, 700);
        setLocation(700, 100);
        setUndecorated(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            String messageOutput = messageBox.getText();
            message.setText(message.getText() + "\n\t\t\t" + messageOutput);
            dataOut.writeUTF(messageOutput);
            messageBox.setText("");
        } catch(Exception er) {

        }

    }

    public static void main(String[] args) {
        new Client().setVisible(true);

        try{
            s = new Socket("127.0.0.1", 6001);
            dataIn = new DataInputStream(s.getInputStream());
            dataOut = new DataOutputStream(s.getOutputStream());

            String messageInput = "";
            messageInput = dataIn.readUTF();
            message.setText(message.getText() + "\n" + messageInput);

        }catch(Exception e){

        }
    }

}
