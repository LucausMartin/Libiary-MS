package com.SignUpPackage;

import com.Database.DB;
import com.SignInPackage.MyRoundJTextField;
import com.SignInPackage.SignIn;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

public class ThreeQuestions extends JFrame {
    public ThreeQuestions(String number, String password) {
        super("Three questions");


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


        JLabel titleLabel = new JLabel("Questions Reset the password if you forget it", JLabel.CENTER);
        JLabel titleLabel1 = new JLabel("The answer cannot be blank", JLabel.CENTER);
        JLabel question1 = new JLabel("name of your school");
        JLabel question2 = new JLabel("name of your father");
        JLabel question3 = new JLabel("name of your best friend");

        MyRoundJTextField question1Text = new MyRoundJTextField();
        MyRoundJTextField question2Text = new MyRoundJTextField();
        MyRoundJTextField question3Text = new MyRoundJTextField();


        SignUpMyButton secondSubmitButton = new SignUpMyButton("Submit");
        int secondSubmitButtonWidth = 160, secondSubmitButtonHeight = 40;
        secondSubmitButton.setBounds(windowWidth / 2 - secondSubmitButtonWidth / 2, 400,
                secondSubmitButtonWidth, secondSubmitButtonHeight);
        panel.add(secondSubmitButton);

        titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        titleLabel1.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        question1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        question1.setForeground(labelColor);
        question2.setForeground(labelColor);
        question3.setForeground(labelColor);
        titleLabel.setForeground(Color.WHITE);
        titleLabel1.setForeground(Color.RED);


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
        question1Text.setCaretColor(Color.WHITE);
        question2Text.setCaretColor(Color.WHITE);
        question3Text.setCaretColor(Color.WHITE);
        question1Text.setBackground(TextFieldBackground);
        question2Text.setBackground(TextFieldBackground);
        question3Text.setBackground(TextFieldBackground);


        int labelWidth = 200, labelHeight = 30;
        int textWidth = 300, textHeight = 50;
        titleLabel.setBounds(0, 20, windowWidth, labelHeight);
        titleLabel1.setBounds(0, 20, windowWidth, labelHeight);
        question1.setBounds(windowWidth / 2 - textWidth / 2 + 5, labelHeight + 50, labelWidth, labelHeight);
        question2.setBounds(windowWidth / 2 - textWidth / 2 + 5, 2 * labelHeight + 65 + textHeight,
                labelWidth, labelHeight);
        question3.setBounds(windowWidth / 2 - textWidth / 2 + 5, 3 * labelHeight + 80 + 2 * textHeight,
                labelWidth, labelHeight);
        panel.add(titleLabel);
        panel.add(question1);
        panel.add(question2);
        panel.add(question3);


        question1Text.setBounds(windowWidth / 2 - textWidth / 2, 2 * labelHeight + 50, textWidth, textHeight);
        question2Text.setBounds(windowWidth / 2 - textWidth / 2, 3 * labelHeight + 65 + textHeight,
                textWidth, textHeight);
        question3Text.setBounds(windowWidth / 2 - textWidth / 2, 4 * labelHeight + 80 + 2 * textHeight,
                textWidth, textHeight);
        panel.add(question1Text);
        panel.add(question2Text);
        panel.add(question3Text);


        secondSubmitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if ((question1Text.getText().trim().equals("") || question1Text.getText().length() == 0)
                        && (question2Text.getText().trim().equals("") || question2Text.getText().length() == 0)
                        && (question3Text.getText().length() == 0) || question3Text.getText().trim().equals("")) {
                    panel.remove(titleLabel);
                    panel.add(titleLabel1);
                    repaint();
                }
                else {
                    var connection = DB.getConnection();
                    var sql = String.format(
                            "INSERT INTO user (Number, Password, Question1, Question2, Question3) " +
                                    "VALUES (\"%s\", \"%s\", \"%S\", \"%s\", \"%s\")",
                            number,
                            password,
                            question1Text.getText(),
                            question2Text.getText(),
                            question3Text.getText()
                    );
                    try {
                        assert connection != null;
                        DB.executeUpdate(connection, sql);
                        new SignIn();
                        dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    DB.closeConnection(connection);
                }
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
