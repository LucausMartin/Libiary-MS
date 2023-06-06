package com.ForgotPawwordPackage;

import com.Database.DB;
import com.SignInPackage.MyRoundJPasswordField;
import com.SignInPackage.SignIn;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password extends JFrame {
    public Password(String userName) {
        super("New password");


        Color labelColor = new Color(0x7E74CB);
        Color panelColor = new Color(0x530EA6);
        Color TextFieldBackground = new Color(0x24134D);

        //window's width and height
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = 460, windowHeight = 400;
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


        JLabel titleLabel = new JLabel("Please enter your new password", JLabel.CENTER);
        JLabel backLabel = new JLabel("<<back");
        JLabel question1 = new JLabel("New Password");
        JLabel question2 = new JLabel("Confirm Password");
        JLabel correctFormatPasswordLabel = new JLabel("Password available");
        JLabel wrongFormatChoosePasswordLabel = new JLabel("Only numbers and letters of 6 to 11 are allowed");
        JLabel wrongPasswordLabel = new JLabel("Inconsistent passwords");
        JLabel correctPasswordLabel = new JLabel("Password is correct");


        MyRoundJPasswordField question1Text = new MyRoundJPasswordField();
        MyRoundJPasswordField question2Text= new MyRoundJPasswordField();

        SignUpMyButton secondSubmitButton = new SignUpMyButton("Submit");
        int secondSubmitButtonWidth = 160, secondSubmitButtonHeight = 40;
        secondSubmitButton.setBounds(windowWidth / 2 - secondSubmitButtonWidth / 2, 300,
                secondSubmitButtonWidth, secondSubmitButtonHeight);
        panel.add(secondSubmitButton);

        titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        backLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        question2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        wrongPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        correctPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        correctFormatPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        wrongFormatChoosePasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        backLabel.setForeground(labelColor);
        question2.setForeground(labelColor);
        question1.setForeground(labelColor);
        titleLabel.setForeground(Color.WHITE);
        backLabel.setForeground(Color.WHITE);
        correctFormatPasswordLabel.setForeground(Color.GREEN);
        wrongFormatChoosePasswordLabel.setForeground(Color.RED);
        wrongPasswordLabel.setForeground(Color.RED);
        correctPasswordLabel.setForeground(Color.GREEN);


        question1Text.setColumns(20);
        question2Text.setColumns(20);
        question1Text.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question2Text.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question1Text.setBorder(new EmptyBorder(0, 0, 0, 0));
        question2Text.setBorder(new EmptyBorder(0, 0, 0, 0));
        question1Text.setForeground(Color.WHITE);
        question2Text.setForeground(Color.WHITE);
        question1Text.setBackground(TextFieldBackground);
        question2Text.setBackground(TextFieldBackground);
        question1Text.setCaretColor(Color.WHITE);
        question2Text.setCaretColor(Color.WHITE);



        int labelWidth = 200, labelHeight = 30;
        int textWidth = 300, textHeight = 50;
        titleLabel.setBounds(0, 38, windowWidth, labelHeight);
        backLabel.setBounds(0, 0, 80, 20);
        question2.setBounds(windowWidth / 2 - textWidth / 2 + 5, 2 * labelHeight + 65 + textHeight,
                labelWidth, labelHeight);
        question1.setBounds(windowWidth / 2 - textWidth / 2 + 5, labelHeight + 50, labelWidth, labelHeight);
        correctFormatPasswordLabel.setBounds(windowWidth / 2 - textWidth / 2 + 5,
                2 * labelHeight + 40 + textHeight,
                300 + 80, 35);
        wrongFormatChoosePasswordLabel.setBounds(windowWidth / 2 - textWidth / 2 + 5,
                2 * labelHeight + 40 + textHeight,
                300 + 80, 35);
        wrongPasswordLabel.setBounds(windowWidth / 2 - textWidth / 2 + 5, 2 * labelHeight + 85 + 2 * textHeight,
                380, 35);
        correctPasswordLabel.setBounds(windowWidth / 2 - textWidth / 2 + 5, 2 * labelHeight + 85 + 2 * textHeight,
                380, 35);
        panel.add(titleLabel);
        panel.add(backLabel);
        panel.add(question2);
        panel.add(question1);

        question1Text.setBounds(windowWidth / 2 - textWidth / 2, 2 * labelHeight + 50, textWidth, textHeight);
        panel.add(question1Text);
        question2Text.setBounds(windowWidth / 2 - textWidth / 2, 3 * labelHeight + 65 + textHeight,
                textWidth, textHeight);
        panel.add(question2Text);


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
                String getChoosePasswordString = new String(question1Text.getPassword());
                String getConfirmPasswordString = new String(question2Text.getPassword());
                String verifyGetPasswordNumberLetter = "^[a-z0-9A-Z]+$";
                String verifyGetPasswordNumber = ".*[0-9]+.*";
                String verifyGetPasswordLetter = ".*[a-zA-Z]+.*";
                Pattern verifyGetPasswordNumberLetterCompiled = Pattern.compile(verifyGetPasswordNumberLetter);
                Pattern verifyGetPasswordNumberCompiled = Pattern.compile(verifyGetPasswordNumber);
                Pattern verifyGetPasswordLetterCompiled = Pattern.compile(verifyGetPasswordLetter);
                Matcher verifyGetPasswordNumberLetterMatcher =
                        verifyGetPasswordNumberLetterCompiled.matcher(getChoosePasswordString);
                Matcher verifyGetPasswordNumberMatcher =
                        verifyGetPasswordNumberCompiled.matcher(getChoosePasswordString);
                Matcher verifyGetPasswordLetterMatcher =
                        verifyGetPasswordLetterCompiled.matcher(getChoosePasswordString);
                var connection = DB.getConnection();
                boolean flag02, flag03;
                if (verifyGetPasswordNumberLetterMatcher.matches() &&
                        verifyGetPasswordNumberMatcher.matches() &&
                        verifyGetPasswordLetterMatcher.matches() &&
                        (getChoosePasswordString.length() <= 11 && getChoosePasswordString.length() >= 6)) {
                    panel.remove(wrongFormatChoosePasswordLabel);
                    repaint();
                    panel.add(correctFormatPasswordLabel);
                    repaint();
                    flag02 = true;
                } else {
                    panel.remove(correctFormatPasswordLabel);
                    repaint();
                    panel.add(wrongFormatChoosePasswordLabel);
                    flag02 = false;
                }
                if (getChoosePasswordString.equals(getConfirmPasswordString)) {
                    panel.remove(wrongPasswordLabel);
                    repaint();
                    panel.add(correctPasswordLabel);
                    flag03 = true;
                } else {
                    panel.remove(correctPasswordLabel);
                    repaint();
                    panel.add(wrongPasswordLabel);
                    flag03 = false;
                }

                if (flag02 && flag03) {
                    var sqlUpdatePassword = "update user set Password = '" + getChoosePasswordString +
                            "' where Number = '" + userName + "'";
                    System.out.println(sqlUpdatePassword);
                    try {
                        assert connection != null;
                        DB.executeUpdate(connection, sqlUpdatePassword);
                        new SignIn();
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
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

