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

public class ForgotPassword extends JFrame {
    public ForgotPassword(String userName) {
        super("Forgot password");


        Color labelColor = new Color(0x7E74CB);
        Color panelColor = new Color(0x530EA6);
        Color TextFieldBackground = new Color(0x24134D);

        //window's width and height
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = 460, windowHeight = 500;
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


        JLabel titleLabel = new JLabel("Enter answers to questions for reset your password", JLabel.CENTER);
        JLabel question1 = new JLabel("name of your school");
        JLabel question2 = new JLabel("name of your father");
        JLabel question3 = new JLabel("name of your best friend");
        JLabel wrongAnswers = new JLabel("Have the wrong answer", JLabel.CENTER);
        JLabel backLabel = new JLabel("<<back");


        MyRoundJTextField question1Text = new MyRoundJTextField();
        MyRoundJTextField question2Text = new MyRoundJTextField();
        MyRoundJTextField question3Text = new MyRoundJTextField();


        SignUpMyButton secondSubmitButton = new SignUpMyButton("Submit");
        int secondSubmitButtonWidth = 160, secondSubmitButtonHeight = 40;
        secondSubmitButton.setBounds(windowWidth / 2 - secondSubmitButtonWidth / 2, 400,
                secondSubmitButtonWidth, secondSubmitButtonHeight);
        panel.add(secondSubmitButton);

        titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        backLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        wrongAnswers.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        question1.setForeground(labelColor);
        question2.setForeground(labelColor);
        question3.setForeground(labelColor);
        backLabel.setForeground(labelColor);
        titleLabel.setForeground(Color.WHITE);
        backLabel.setForeground(Color.WHITE);
        wrongAnswers.setForeground(Color.RED);


        question1Text.setColumns(20);
        question2Text.setColumns(20);
        question3Text.setColumns(20);
        question1Text.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question2Text.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question3Text.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question1Text.setBorder(new EmptyBorder(0, 0, 0, 0));
        question2Text.setBorder(new EmptyBorder(0, 0, 0, 0));
        question3Text.setBorder(new EmptyBorder(0, 0, 0, 0));
        question1Text.setForeground(Color.WHITE);
        question2Text.setForeground(Color.WHITE);
        question3Text.setForeground(Color.WHITE);
        question1Text.setBackground(TextFieldBackground);
        question2Text.setBackground(TextFieldBackground);
        question3Text.setBackground(TextFieldBackground);
        question1Text.setCaretColor(Color.WHITE);
        question2Text.setCaretColor(Color.WHITE);
        question3Text.setCaretColor(Color.WHITE);


        int labelWidth = 200, labelHeight = 30;
        int textWidth = 300, textHeight = 50;
        titleLabel.setBounds(0, 38, windowWidth, labelHeight);
        wrongAnswers.setBounds(0, 38, windowWidth, labelHeight);
        question1.setBounds(windowWidth / 2 - textWidth / 2 + 5, labelHeight + 50, labelWidth, labelHeight);
        question2.setBounds(windowWidth / 2 - textWidth / 2 + 5, 2 * labelHeight + 65 + textHeight,
                labelWidth, labelHeight);
        question3.setBounds(windowWidth / 2 - textWidth / 2 + 5, 3 * labelHeight + 80 + 2 * textHeight,
                labelWidth, labelHeight);
        backLabel.setBounds(0, 0, 80, 20);
        panel.add(titleLabel);
        panel.add(question1);
        panel.add(question2);
        panel.add(question3);
        panel.add(backLabel);


        question1Text.setBounds(windowWidth / 2 - textWidth / 2, 2 * labelHeight + 50, textWidth, textHeight);
        question2Text.setBounds(windowWidth / 2 - textWidth / 2, 3 * labelHeight + 65 + textHeight,
                textWidth, textHeight);
        question3Text.setBounds(windowWidth / 2 - textWidth / 2, 4 * labelHeight + 80 + 2 * textHeight,
                textWidth, textHeight);
        panel.add(question1Text);
        panel.add(question2Text);
        panel.add(question3Text);


        secondSubmitButton.addMouseListener(new MouseListener() {
            boolean flag1 = false,  flag2 = false, flag3 = false;
            @Override
            public void mouseClicked(MouseEvent e) {
                var connection = DB.getConnection();
                var sqlQuestion1 = "select Question1 from user where Number = " + userName;
                var sqlQuestion2 = "select Question2 from user where Number = " + userName;
                var sqlQuestion3 = "select Question3 from user where Number = " + userName;
                ResultSet resultSet;
                try {
                    assert connection != null;
                    resultSet = DB.executeQuery(connection, sqlQuestion1);
                    while (resultSet.next()) {
                        flag1 = question1Text.getText().equals(resultSet.getString("Question1"));
                    }
                    resultSet = DB.executeQuery(connection, sqlQuestion2);
                    while (resultSet.next()) {
                        flag2 = question2Text.getText().equals(resultSet.getString("Question2"));
                    }
                    resultSet = DB.executeQuery(connection, sqlQuestion3);
                    while (resultSet.next()) {
                        flag3 = question3Text.getText().equals(resultSet.getString("Question3"));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                if(flag1 && flag2 && flag3) {
                    new Password(userName);
                    dispose();
                }
                else {
                    panel.remove(titleLabel);
                    panel.add(wrongAnswers);
                    repaint();
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
        this.setVisible(true);
        this.setResizable(false);
    }
}

