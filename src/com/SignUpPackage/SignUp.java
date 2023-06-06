package com.SignUpPackage;

import com.Database.DB;
import com.SignInPackage.MyRoundJPasswordField;
import com.SignInPackage.MyRoundJTextField;
import com.SignInPackage.SignIn;

import java.awt.*;
import java.awt.Font;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SignUp extends JFrame {
    JLabel wrongFormatUserNameLabel = new JLabel("11 digits required and it cannot start with 0");
    JLabel wrongFormatChoosePasswordLabel = new JLabel("Only numbers and letters of 6 to 11 are allowed");
    JLabel wrongPasswordLabel = new JLabel("Inconsistent passwords");
    JLabel correctPasswordLabel = new JLabel("Password is correct");
    JLabel correctFormatUsernameLabel = new JLabel("User name available");
    JLabel correctFormatPasswordLabel = new JLabel("Password available");

    JLabel backLabel = new JLabel("<<back");
    JLabel userNameExistLabel = new JLabel("The user name already exists");

    public SignUp() {
        super("Sign up");


        // colors
        Color labelColor = new Color(0x7E74CB);
        Color TextFieldBackground = new Color(0x24134D);


        //window's width and height
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = 460, windowHeight = 600;
        //window exit
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        //location of window
        this.setBounds(dimension.width / 2 - windowWidth / 2, dimension.height / 2 - windowHeight / 2,
                windowWidth, windowHeight);


        //panel's Layout is null
        this.getContentPane().setLayout(null);
        JPanel imPanel = (JPanel) this.getContentPane();
        imPanel.setOpaque(false);
//        JPanel panel = new JPanel();
//        panel.setLayout(null);
//        this.add(panel)
        this.setBackground(new Color(0x530EA6));


        //userName and Password and confirmPassword and questions and answers
        int userNameLabelSizeWidth = 250, userNameLabelSizeHeight = 35;
        int userNameTextFieldSizeWidth = 300, userNameTextFieldSizeHeight = 50;
        //object of user and password and sign up and skip and forgotPassword
        JLabel titleLabel = new JLabel("Sign Up");
        JLabel userNameLabel = new JLabel("User Name");
        JLabel choosePasswordLabel = new JLabel("Choose Password");
        JLabel confirmPasswordLabel = new JLabel("Confirm Password");


        //Font and foreGround
        userNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        choosePasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        confirmPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        wrongFormatUserNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        wrongFormatChoosePasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        correctFormatUsernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        correctFormatPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        wrongPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        correctPasswordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        backLabel.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        userNameExistLabel.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        titleLabel.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        titleLabel.setForeground(Color.WHITE);
        userNameLabel.setForeground(labelColor);
        choosePasswordLabel.setForeground(labelColor);
        confirmPasswordLabel.setForeground(labelColor);
        wrongFormatUserNameLabel.setForeground(Color.RED);
        wrongFormatChoosePasswordLabel.setForeground(Color.RED);
        wrongPasswordLabel.setForeground(Color.RED);
        correctFormatUsernameLabel.setForeground(Color.GREEN);
        correctFormatPasswordLabel.setForeground(Color.GREEN);
        correctPasswordLabel.setForeground(Color.GREEN);
        backLabel.setForeground(Color.WHITE);
        userNameExistLabel.setForeground(Color.RED);
        //location of label
        titleLabel.setBounds(windowWidth / 2 - 60 - 30, windowHeight / 2 - 270,
                200, 100);
        userNameLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30, windowHeight / 2 - 160,
                userNameLabelSizeWidth, userNameLabelSizeHeight);
        choosePasswordLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30,
                windowHeight / 2 - 125 + userNameTextFieldSizeHeight + userNameLabelSizeHeight - 30,
                userNameLabelSizeWidth + 100, userNameLabelSizeHeight);
        confirmPasswordLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30,
                windowHeight / 2 - 135 + 2 * userNameTextFieldSizeHeight + 2 * userNameLabelSizeHeight - 15,
                userNameLabelSizeWidth + 100, userNameLabelSizeHeight);
        wrongFormatUserNameLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 125 + userNameTextFieldSizeHeight - 15,
                userNameTextFieldSizeWidth, userNameLabelSizeHeight);
        correctFormatUsernameLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 125 + userNameTextFieldSizeHeight - 15,
                userNameTextFieldSizeWidth - 150, userNameLabelSizeHeight);
        wrongFormatChoosePasswordLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 135 + 2 * userNameTextFieldSizeHeight + userNameLabelSizeHeight,
                userNameTextFieldSizeWidth + 80, userNameLabelSizeHeight);
        correctFormatPasswordLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 135 + 2 * userNameTextFieldSizeHeight + userNameLabelSizeHeight,
                userNameTextFieldSizeWidth + 80, userNameLabelSizeHeight);
        wrongPasswordLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 125 + 3 * userNameTextFieldSizeHeight + 2 * userNameLabelSizeHeight,
                userNameTextFieldSizeWidth, userNameLabelSizeHeight);
        correctPasswordLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 125 + 3 * userNameTextFieldSizeHeight + 2 * userNameLabelSizeHeight,
                userNameTextFieldSizeWidth, userNameLabelSizeHeight);
        backLabel.setBounds(0, 0, 80, 20);
        userNameExistLabel.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 + 5,
                windowHeight / 2 - 125 + userNameTextFieldSizeHeight - 15,
                userNameTextFieldSizeWidth - 100, userNameLabelSizeHeight);
        this.getContentPane().add(backLabel);
        this.getContentPane().add(userNameLabel);
        this.getContentPane().add(choosePasswordLabel);
        this.getContentPane().add(confirmPasswordLabel);
        this.getContentPane().add(titleLabel);


        //TextField
        //object of TextField
        MyRoundJTextField userNameTextField = new MyRoundJTextField();
        MyRoundJPasswordField choosePasswordTextField = new MyRoundJPasswordField();
        MyRoundJPasswordField confirmPasswordTextField = new MyRoundJPasswordField();
        //set background, columns, border, font and color
        userNameTextField.setBackground(TextFieldBackground);
        userNameTextField.setCaretColor(Color.WHITE);
        choosePasswordTextField.setBackground(TextFieldBackground);
        choosePasswordTextField.setCaretColor(Color.WHITE);
        confirmPasswordTextField.setBackground(TextFieldBackground);
        confirmPasswordTextField.setCaretColor(Color.WHITE);
        userNameTextField.setColumns(11);
        choosePasswordTextField.setColumns(11);
        confirmPasswordTextField.setColumns(11);
        userNameTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        choosePasswordTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        confirmPasswordTextField.setBorder(new EmptyBorder(0, 0, 0, 0));
        userNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        choosePasswordTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        confirmPasswordTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        userNameTextField.setForeground(Color.WHITE);
        choosePasswordTextField.setForeground(Color.WHITE);
        confirmPasswordTextField.setForeground(Color.WHITE);
        //location of TextField
        userNameTextField.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30, windowHeight / 2 - 130,
                userNameTextFieldSizeWidth, userNameTextFieldSizeHeight);
        choosePasswordTextField.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30,
                windowHeight / 2 - 125 + userNameTextFieldSizeHeight + userNameLabelSizeHeight,
                userNameTextFieldSizeWidth, userNameTextFieldSizeHeight);
        confirmPasswordTextField.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30,
                windowHeight / 2 - 135 + 2 * userNameTextFieldSizeHeight + 3 * userNameLabelSizeHeight - 15,
                userNameTextFieldSizeWidth, userNameTextFieldSizeHeight);
        this.getContentPane().add(userNameTextField);
        this.getContentPane().add(choosePasswordTextField);
        this.getContentPane().add(confirmPasswordTextField);


        //
        int firstSubmitButtonWidth = 160, firstSubmitButtonHeight = 40;
        SignUpMyButton firstSubmitButton = new SignUpMyButton("Submit");
        firstSubmitButton.setBounds(windowWidth / 2 - userNameLabelSizeWidth / 2 - 30 +
                        (userNameTextFieldSizeWidth / 2 - firstSubmitButtonWidth / 2), windowHeight / 2 + 160,
                firstSubmitButtonWidth, firstSubmitButtonHeight);
        this.getContentPane().add(firstSubmitButton);
        firstSubmitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //regular expression determines the character format of the input
                boolean flag01 = false, flag02, flag03;
                String getUserNameString = userNameTextField.getText();
                String getChoosePasswordString = new String(choosePasswordTextField.getPassword());
                String getWrongPasswordString = new String(confirmPasswordTextField.getPassword());
                String verifyGetUserNameString1 = "^[1-9]+\\d{7}$";
                String verifyGetUserNameString2 = "^[1-9]+\\d{10}$";
                String verifyGetPasswordNumberLetter = "^[a-z0-9A-Z]+$";
                String verifyGetPasswordNumber = ".*[0-9]+.*";
                String verifyGetPasswordLetter = ".*[a-zA-Z]+.*";
                Pattern verifyGetUserNameString1Pattern = Pattern.compile(verifyGetUserNameString1);
                Pattern verifyGetUserNameString2Pattern = Pattern.compile(verifyGetUserNameString2);
                Pattern verifyGetPasswordNumberLetterCompiled = Pattern.compile(verifyGetPasswordNumberLetter);
                Pattern verifyGetPasswordNumberCompiled = Pattern.compile(verifyGetPasswordNumber);
                Pattern verifyGetPasswordLetterCompiled = Pattern.compile(verifyGetPasswordLetter);
                Matcher verifyGetUserNameString1Matcher =
                        verifyGetUserNameString1Pattern.matcher(getUserNameString);
                Matcher verifyGetUserNameString2Matcher =
                        verifyGetUserNameString2Pattern.matcher(getUserNameString);
                Matcher verifyGetPasswordNumberLetterMatcher =
                        verifyGetPasswordNumberLetterCompiled.matcher(getChoosePasswordString);
                Matcher verifyGetPasswordNumberMatcher =
                        verifyGetPasswordNumberCompiled.matcher(getChoosePasswordString);
                Matcher verifyGetPasswordLetterMatcher =
                        verifyGetPasswordLetterCompiled.matcher(getChoosePasswordString);
                //
                var connection = DB.getConnection();
                ResultSet resultSet;
                if (verifyGetUserNameString1Matcher.matches() || verifyGetUserNameString2Matcher.matches()) {
                    removeUserNameExistLabel();
                    removeWrongFormatUserNameLabelDisplay();
                    String sqlFindName = "select Number from user where Number = " + userNameTextField.getText();
                    try {
                        assert connection != null;
                        resultSet = DB.executeQuery(connection, sqlFindName);
                        int count = 0;
                        while (resultSet.next()) {
                            count++;
                        }
                        removeWrongFormatUserNameLabelDisplay();
                        if (count > 0) {
                            userNameExistLabelDisplay();
                        } else {
                            removeUserNameExistLabel();
                            correctFormatUsernameLabelDisplay();
                            flag01 = true;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                } else if (verifyGetUserNameString2Matcher.matches()) {
                    removeUserNameExistLabel();
                    removeWrongFormatUserNameLabelDisplay();
                    correctFormatUsernameLabelDisplay();
                    flag01 = true;
                } else {
                    removeUserNameExistLabel();
                    removeCorrectFormatUsernameLabelDisplay();
                    wrongFormatUserNameLabelDisplay();
                }
                if (verifyGetPasswordNumberLetterMatcher.matches() &&
                        verifyGetPasswordNumberMatcher.matches() &&
                        verifyGetPasswordLetterMatcher.matches() &&
                        (getChoosePasswordString.length() <= 11 && getChoosePasswordString.length() >= 6)) {
                    removeWrongFormatChoosePasswordLabelDisplay();
                    correctFormatPasswordLabelDisplay();
                    flag02 = true;
                } else {
                    removeCorrectFormatPasswordLabelDisplay();
                    WrongFormatChoosePasswordLabelDisplay();
                    flag02 = false;
                }
                if (getChoosePasswordString.equals(getWrongPasswordString)) {
                    removePasswordLabelDisplay();
                    correctPasswordLabelDisplay();
                    flag03 = true;
                } else {
                    removeCorrectPasswordLabelDisplay();
                    wrongPasswordLabelDisplay();
                    flag03 = false;
                }

                if (flag01 && flag02 && flag03) {
                    try {
                        ThreeQuestionsWindow(userNameTextField.getText(), getChoosePasswordString);
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
                signInWindow();
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

    public void wrongFormatUserNameLabelDisplay() {
        this.getContentPane().add(wrongFormatUserNameLabel);
        this.repaint();
    }

    public void removeWrongFormatUserNameLabelDisplay() {
        this.remove(wrongFormatUserNameLabel);
        this.repaint();
    }

    public void correctFormatUsernameLabelDisplay() {
        this.getContentPane().add(correctFormatUsernameLabel);
        this.repaint();
    }

    public void removeCorrectFormatUsernameLabelDisplay() {
        this.remove(correctFormatUsernameLabel);
        this.repaint();
    }

    public void WrongFormatChoosePasswordLabelDisplay() {
        this.getContentPane().add(wrongFormatChoosePasswordLabel);
        this.repaint();
    }

    public void removeWrongFormatChoosePasswordLabelDisplay() {
        this.remove(wrongFormatChoosePasswordLabel);
        this.repaint();
    }

    public void correctFormatPasswordLabelDisplay() {
        this.getContentPane().add(correctFormatPasswordLabel);
        this.repaint();
    }

    public void removeCorrectFormatPasswordLabelDisplay() {
        this.remove(correctFormatPasswordLabel);
        this.repaint();
    }

    public void wrongPasswordLabelDisplay() {
        this.getContentPane().add(wrongPasswordLabel);
        this.repaint();
    }

    public void removePasswordLabelDisplay() {
        this.remove(wrongPasswordLabel);
        this.repaint();
    }

    public void correctPasswordLabelDisplay() {
        this.getContentPane().add(correctPasswordLabel);
        this.repaint();
    }

    public void removeCorrectPasswordLabelDisplay() {
        this.remove(correctPasswordLabel);
        this.repaint();
    }

    public void ThreeQuestionsWindow(String number, String password) throws SQLException {
        new ThreeQuestions(number, password);
        this.dispose();
    }

    public void signInWindow() {
        new SignIn();
        this.dispose();
    }

    public void userNameExistLabelDisplay() {
        this.getContentPane().add(userNameExistLabel);
        this.repaint();
    }

    public void removeUserNameExistLabel() {
        this.remove(userNameExistLabel);
        this.repaint();
    }
}
