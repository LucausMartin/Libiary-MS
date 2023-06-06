package com.SignInPackage;

import com.Admin.AdminInterface;
import com.Database.DB;
import com.ForgotPawwordPackage.ForgotPasswordUserName;
import com.Library.LibraryInterface;
import com.PreviewLibrary.PreviewLibraryInterface;
import com.SignUpPackage.SignUp;
import java.awt.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.awt.Font.PLAIN;

public class SignIn extends JFrame {
    public SignIn() {
        super("Sign in");


        // colors
        Color primaryColor = new Color(0x9575cd);
        Color primaryLight = new Color(0xc7a4ff);
        Color secondaryDark = new Color(0x1a237e);
        Color secondaryColor = new Color(0xec407a);
        Color others = new Color(0x3f51b5);


        //window's width and height
        int windowWidth = 500, windowHeight = 650;
        //window exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //window‘s location
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds(dimension.width / 2 - windowWidth / 2, dimension.height / 2 - windowHeight / 2,
                windowWidth, windowHeight);


        //panel’s layout is null
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(primaryColor);
        this.add(panel);


        //picture
        //size of picture
        int picture01Width = 200, picture01Height = 200;
        //object of picture
        ImageIcon book = new ImageIcon("./recourse/book.png");
        ImageIcon trueImage = new ImageIcon("./recourse/true.png");
        ImageIcon falseImage = new ImageIcon("./recourse/false.png");
        JLabel bookLabel = new JLabel(book);
        JLabel trueImageLabel = new JLabel(trueImage);
        JLabel falseImageLabel = new JLabel(falseImage);
        JLabel falseImageLabel1 = new JLabel(falseImage);
        //location of picture
        bookLabel.setBounds(windowWidth / 2 - picture01Width / 2, 70, picture01Width, picture01Height);
        panel.add(bookLabel);


        //user and password
        int userLabelSizeWidth = 150, userLabelSizeHeight = 50,
                passwordLabelSizeWidth = 150, passwordLabelSizeHeight = 50,
                userTextFieldSizeWidth = 300, userTextFieldSizeHeight = 40,
                passwordTextFieldSizeWidth = 300, passwordTextFieldSizeHeight = 40,
                othersWidth = 150, othersHeight = 50;
        //object of user and password and sign up and skip and forgotPassword
        JLabel userNameLabel = new JLabel("User name");
        JLabel passwordLabel = new JLabel("Password");
        JLabel signUpLabel = new JLabel("Sign up");
        JLabel skipLabel = new JLabel("Skip>>", SwingConstants.RIGHT);
        JLabel forgotPasswordLabel = new JLabel("Forgot Password?", SwingConstants.RIGHT);
        //Font and foreGround
        userNameLabel.setFont(new Font("微软雅黑", PLAIN, 20));
        userNameLabel.setForeground(secondaryDark);
        passwordLabel.setFont(new Font("微软雅黑", PLAIN, 20));
        passwordLabel.setForeground(secondaryDark);
        signUpLabel.setFont(new Font("微软雅黑", PLAIN, 13));
        signUpLabel.setForeground(others);
        skipLabel.setFont(new Font("微软雅黑", PLAIN, 13));
        skipLabel.setForeground(others);
        forgotPasswordLabel.setFont(new Font("微软雅黑", PLAIN, 13));
        forgotPasswordLabel.setForeground(others);
        //location of userName, password and others
        userNameLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2, windowHeight * 2 / 5,
                userLabelSizeWidth, userLabelSizeHeight);
        passwordLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2,
                windowHeight * 2 / 5 + userLabelSizeHeight + userTextFieldSizeHeight,
                passwordLabelSizeWidth, passwordLabelSizeHeight);
        int y = windowHeight * 2 / 5 + userLabelSizeHeight + userTextFieldSizeHeight + passwordTextFieldSizeHeight +
                passwordLabelSizeHeight;
        signUpLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2, y
                , othersWidth - 100, othersHeight - 15);
        forgotPasswordLabel.setBounds(windowWidth / 2 + passwordTextFieldSizeWidth / 2 - othersWidth + 40, y,
                othersWidth - 40, othersHeight - 15);
        skipLabel.setBounds(275 + 90, 570, othersWidth - 90, othersHeight - 15);
        //add signUpLabel, forgotPasswordLabel, skipLabel, userNameLabel, passwordLabel;
        //sign up redirects to realize
        panel.add(signUpLabel);
        signUpLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                signUpLabel.setForeground(secondaryColor);
                signUpLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2, y
                        , othersWidth - 100, othersHeight - 15);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                signUpLabel.setForeground(others);
                signUpLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2, y
                        , othersWidth - 100, othersHeight - 15);
                new SignUp();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                signUpLabel.setForeground(others);
                signUpLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2, y
                        , othersWidth - 100, othersHeight - 15);
            }

        });
        //forgotPassword redirects to realize
        panel.add(forgotPasswordLabel);
        forgotPasswordLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                forgotPasswordLabel.setForeground(secondaryColor);
                forgotPasswordLabel.setBounds(windowWidth / 2 + passwordTextFieldSizeWidth / 2 - othersWidth + 40, y,
                        othersWidth - 40, othersHeight - 15);

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                forgotPasswordLabel.setForeground(others);
                forgotPasswordLabel.setBounds(windowWidth / 2 + passwordTextFieldSizeWidth / 2 - othersWidth + 40, y,
                        othersWidth - 40, othersHeight - 15);
                new ForgotPasswordUserName();
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                forgotPasswordLabel.setForeground(others);
                forgotPasswordLabel.setBounds(windowWidth / 2 + passwordTextFieldSizeWidth / 2 - othersWidth + 40, y,
                        othersWidth - 40, othersHeight - 15);
            }
        });
        panel.add(skipLabel);
        skipLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                skipLabel.setForeground(secondaryColor);
                skipLabel.setBounds(275 + 90, 570, othersWidth - 90, othersHeight - 15);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                skipLabel.setForeground(others);
                skipLabel.setBounds(275 + 90, 570, othersWidth - 90, othersHeight - 15);
                try {
                    new PreviewLibraryInterface();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
                skipLabel.setForeground(others);
                skipLabel.setBounds(275 + 90, 570, othersWidth - 90, othersHeight - 15);
            }
        });
        panel.add(userNameLabel);
        panel.add(passwordLabel);


        //TextField of user and password
        //object of userTextField and
        MyRoundJTextField userTextField = new MyRoundJTextField();
        MyRoundJPasswordField passwordTextField = new MyRoundJPasswordField();
        //TextField Font
        userTextField.setFont(new Font("微软雅黑", PLAIN, 20));
        passwordTextField.setFont(new Font("微软雅黑", PLAIN, 20));
        //passwordTextField size and echo
        passwordTextField.setColumns(11);
        //Cancel the border
        userTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        passwordTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        //TextField color
        userTextField.setBackground(primaryLight);
        userTextField.setForeground(Color.WHITE);
        passwordTextField.setBackground(primaryLight);
        passwordTextField.setForeground(Color.WHITE);
        //location of userTextField and passwordTextField
        userTextField.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2,
                windowHeight * 2 / 5 + userLabelSizeHeight,
                userTextFieldSizeWidth, userTextFieldSizeHeight);
        passwordTextField.setBounds(windowWidth / 2 - passwordTextFieldSizeWidth / 2,
                windowHeight * 2 / 5 + userLabelSizeHeight + userTextFieldSizeHeight + passwordLabelSizeHeight,
                passwordTextFieldSizeWidth, passwordTextFieldSizeHeight);
        //add userTextField, passwordTextField
        panel.add(userTextField);
        panel.add(passwordTextField);


        //sign in button
        int signInButtonWidth = 160, signInButtonHeight = 40;
        SignInMyButton signInButton = new SignInMyButton("sign in");
        signInButton.setBounds(windowWidth / 2 - signInButtonWidth / 2,
                windowHeight * 2 / 5 + userLabelSizeHeight + userTextFieldSizeHeight + passwordLabelSizeHeight +
                        passwordTextFieldSizeHeight + 60,
                signInButtonWidth, signInButtonHeight);
        //add signInButton
        panel.add(signInButton);


        signInButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean flag;
                String password = new String(passwordTextField.getPassword());
                var connection = DB.getConnection();
                ResultSet resultSet;
                var sqlFindName = "select Number from user where Number = " + userTextField.getText();
                var sqlFindPassword = "select Password from user where Number = " + userTextField.getText();
                try {
                    String getUserNameString = userTextField.getText();
                    String verifyGetUserNameString1 = "^(([1-9][0-9]*)|0)$";
                    Pattern verifyGetUserNameString1Pattern = Pattern.compile(verifyGetUserNameString1);
                    Matcher verifyGetUserNameString1Matcher =
                            verifyGetUserNameString1Pattern.matcher(getUserNameString);
                    if (verifyGetUserNameString1Matcher.matches()) {
                        assert connection != null;
                        resultSet = DB.executeQuery(connection, sqlFindName);
                        int count = 0;
                        while (resultSet.next()) {
                            count++;
                        }
                        if (count > 0) {
                            if(getUserNameString.length() == 4) {
                                flag = true;
                            }
                            panel.remove(falseImageLabel);
                            trueImageLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                    windowHeight * 2 / 5 + userLabelSizeHeight - 5, 50, 50);
                            panel.add(trueImageLabel);
                            repaint();
                            flag = true;
                        } else {
                            panel.remove(trueImageLabel);
                            falseImageLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                    windowHeight * 2 / 5 + userLabelSizeHeight - 5, 50, 50);
                            panel.add(falseImageLabel);
                            repaint();
                            flag = false;
                        }

                    } else {
                        panel.remove(trueImageLabel);
                        falseImageLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                windowHeight * 2 / 5 + userLabelSizeHeight - 5, 50, 50);
                        panel.add(falseImageLabel);
                        repaint();
                        flag = false;
                    }

                    if (flag) {
                        resultSet = DB.executeQuery(connection, sqlFindPassword);
                        while (resultSet.next()) {
                            if (password.equals(resultSet.getString("Password"))) {
                                if(getUserNameString.length() == 4) {
                                    panel.remove(falseImageLabel1);
                                    new AdminInterface();
                                    dispose();
                                }
                                else {
                                    panel.remove(falseImageLabel1);
                                    new LibraryInterface(userTextField.getText());
                                    dispose();
                                }
                            } else {
                                falseImageLabel1.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                        windowHeight * 2 / 5 + userLabelSizeHeight + userTextFieldSizeHeight + passwordLabelSizeHeight - 5,
                                        50, 50);
                                panel.add(falseImageLabel1);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (Exception exception) {
                    exception.printStackTrace();
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
        passwordTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if(e.getKeyChar() == KeyEvent.VK_ENTER) {
                    boolean flag;
                    String password = new String(passwordTextField.getPassword());
                    var connection = DB.getConnection();
                    ResultSet resultSet;
                    var sqlFindName = "select Number from user where Number = " + userTextField.getText();
                    var sqlFindPassword = "select Password from user where Number = " + userTextField.getText();
                    try {
                        String getUserNameString = userTextField.getText();
                        String verifyGetUserNameString1 = "^(([1-9][0-9]*)|0)$";
                        Pattern verifyGetUserNameString1Pattern = Pattern.compile(verifyGetUserNameString1);
                        Matcher verifyGetUserNameString1Matcher =
                                verifyGetUserNameString1Pattern.matcher(getUserNameString);
                        if (verifyGetUserNameString1Matcher.matches()) {
                            assert connection != null;
                            resultSet = DB.executeQuery(connection, sqlFindName);
                            int count = 0;
                            while (resultSet.next()) {
                                count++;
                            }
                            if (count > 0) {
                                panel.remove(falseImageLabel);
                                trueImageLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                        windowHeight * 2 / 5 + userLabelSizeHeight - 5, 50, 50);
                                panel.add(trueImageLabel);
                                repaint();
                                flag = true;
                            } else {
                                panel.remove(trueImageLabel);
                                falseImageLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                        windowHeight * 2 / 5 + userLabelSizeHeight - 5, 50, 50);
                                panel.add(falseImageLabel);
                                repaint();
                                flag = false;
                            }

                        } else {
                            panel.remove(trueImageLabel);
                            falseImageLabel.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                    windowHeight * 2 / 5 + userLabelSizeHeight - 5, 50, 50);
                            panel.add(falseImageLabel);
                            repaint();
                            flag = false;
                        }

                        if (flag) {
                            resultSet = DB.executeQuery(connection, sqlFindPassword);
                            while (resultSet.next()) {
                                if (password.equals(resultSet.getString("Password"))) {
                                    panel.remove(falseImageLabel1);
                                    new LibraryInterface(userTextField.getText());
                                    dispose();
                                } else {
                                    falseImageLabel1.setBounds(windowWidth / 2 - userTextFieldSizeWidth / 2 + userTextFieldSizeWidth,
                                            windowHeight * 2 / 5 + userLabelSizeHeight + userTextFieldSizeHeight + passwordLabelSizeHeight - 5,
                                            50, 50);
                                    panel.add(falseImageLabel1);
                                }
                            }
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        this.setResizable(false);
        this.setVisible(true);
    }
}