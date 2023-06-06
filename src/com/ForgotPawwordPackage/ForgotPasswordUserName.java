package com.ForgotPawwordPackage;

import com.Database.DB;
import com.SignInPackage.MyRoundJTextField;
import com.SignInPackage.SignIn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordUserName extends JFrame {
    public ForgotPasswordUserName() {
        super("user name");


        Color labelColor = new Color(0x7E74CB);
        Color panelColor = new Color(0x530EA6);
        Color TextFieldBackground = new Color(0x24134D);

        //window's width and height
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = 460, windowHeight = 350;
        //window exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //location of window
        this.setBounds(dimension.width / 2 - windowWidth / 2, dimension.height / 2 - windowHeight / 2,
                windowWidth, windowHeight);


        //panel's Layout is null
        JPanel panel = new JPanel();
        panel.setBackground(panelColor);
        panel.setLayout(null);
        this.add(panel);


        JLabel titleLabel = new JLabel("Enter the account that forgot the password", JLabel.CENTER);
        JLabel titleLabel1 = new JLabel("Account does not exist", JLabel.CENTER);
        JLabel backLabel = new JLabel("<<back");


        MyRoundJTextField question1Text = new MyRoundJTextField();

        SignUpMyButton secondSubmitButton = new SignUpMyButton("Submit");
        int secondSubmitButtonWidth = 160, secondSubmitButtonHeight = 40;
        secondSubmitButton.setBounds(windowWidth / 2 - secondSubmitButtonWidth / 2, 200,
                secondSubmitButtonWidth, secondSubmitButtonHeight);
        panel.add(secondSubmitButton);

        titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        titleLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        backLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        backLabel.setForeground(labelColor);
        titleLabel.setForeground(Color.WHITE);
        titleLabel1.setForeground(Color.RED);
        backLabel.setForeground(Color.WHITE);


        question1Text.setColumns(20);
        question1Text.setCaretColor(Color.WHITE);
        question1Text.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question1Text.setBorder(new EmptyBorder(0, 0, 0, 0));
        question1Text.setForeground(Color.WHITE);
        question1Text.setBackground(TextFieldBackground);


        int labelHeight = 30;
        int textWidth = 300, textHeight = 50;
        titleLabel.setBounds(0, 38, windowWidth, labelHeight);
        titleLabel1.setBounds(0, 38, windowWidth, labelHeight);
        backLabel.setBounds(0, 0, 80, 20);
        panel.add(titleLabel);
        panel.add(backLabel);

        question1Text.setBounds(windowWidth / 2 - textWidth / 2, 2 * labelHeight + 50, textWidth, textHeight);
        panel.add(question1Text);


        backLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                backLabel.setForeground(new Color(0x7E74CB));
                backLabel.setBounds(0, 0, 80, 20);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                backLabel.setForeground(Color.WHITE);
                backLabel.setBounds(0, 0, 80, 20);
                new SignIn();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        secondSubmitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                var connection = DB.getConnection();
                ResultSet resultSet;
                var sqlAccount = "select Number from user where Number =" + question1Text.getText();
                try {
                    assert connection != null;
                    resultSet = DB.executeQuery(connection, sqlAccount);
                    int count = 0;
                    while (resultSet.next()) {
                        count++;
                    }
                    if (count > 0) {
                        new ForgotPassword(question1Text.getText());
                        dispose();
                    }
                    else {
                        panel.remove(titleLabel);
                        panel.add(titleLabel1);
                        repaint();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                DB.closeConnection(connection);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        this.setVisible(true);
        this.setResizable(false);
    }
}

