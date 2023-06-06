package com.Book;

import com.Database.DB;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreviewBorrowBook extends JFrame {
    public PreviewBorrowBook(String bookName) throws SQLException {
        super("The book information");
        var connection = DB.getConnection();
        ResultSet resultSetBookType1;
        ResultSet resultSetBookType2;
        ResultSet resultSetBookType3;
        ResultSet resultSetPrice;
        ResultSet resultSetQuantity;
        ResultSet resultSetMyTime;
        ResultSet resultSetPress;
        String[] type1 = new String[2];
        String[] type2 = new String[2];
        String[] type3 = new String[2];
        String[] price = new String[2];
        String[] press = new String[2];
        int[] quantity = new int[2];
        var sqlBookType1 = "SELECT BookType1 FROM books WHERE BookName = '" + bookName + "'";
        var sqlBookType2 = "SELECT BookType2 FROM books WHERE BookName = '" + bookName + "'";
        var sqlBookType3 = "SELECT BookType3 FROM books WHERE BookName = '" + bookName + "'";
        var sqlBookPrice = "SELECT BookPrice FROM books WHERE BookName = '" + bookName + "'";
        var sqlBookQuantity = "SELECT BookQuantity FROM books WHERE BookName = '" + bookName + "'";
        var sqlPress = "SELECT BookPress FROM books WHERE BookName = '" + bookName + "'";
        assert connection != null;
        resultSetBookType1 = DB.executeQuery(connection, sqlBookType1);
        resultSetBookType2 = DB.executeQuery(connection, sqlBookType2);
        resultSetBookType3 = DB.executeQuery(connection, sqlBookType3);
        resultSetPrice = DB.executeQuery(connection, sqlBookPrice);
        resultSetQuantity = DB.executeQuery(connection, sqlBookQuantity);
        resultSetPress = DB.executeQuery(connection, sqlPress);
        int i = 1;
        while(resultSetBookType1.next()) {
            type1[i] = resultSetBookType1.getString("BookType1");
        }
        while(resultSetBookType2.next()) {
            type2[i] = resultSetBookType2.getString("BookType2");
        }
        while(resultSetBookType3.next()) {
            type3[i] = resultSetBookType3.getString("BookType3");
        }
        while(resultSetPrice.next()) {
            price[i] = resultSetPrice.getString("BookPrice");
        }
        while(resultSetQuantity.next()) {
            quantity[i] = resultSetQuantity.getInt("BookQuantity");
        }
        while (resultSetPress.next()) {
            press[i] = resultSetPress.getString("BookPress");
        }
        var sqlUpdateBookQuantity = String.format("UPDATE books SET BookQuantity = \"%d\"" +
                " WHERE BookName = '" + bookName + "'", quantity[1] - 1);


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = dimension.width, screenHeight = dimension.height;
        int windowWidth = 560, windowHeight = 550;
        this.setBounds(dimension.width / 2 - screenWidth / 2, dimension.height / 2 - screenHeight / 2,
                windowWidth, windowHeight);
        this.setVisible(true);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        Color primaryColor = new Color(0x9575cd);

        JPanel panel = new JPanel(null);
        this.add(panel);

        panel.setBackground(primaryColor);

        JLabel title = new JLabel("The book information");
        title.setFont(new Font("Algerian",Font.PLAIN, 25));
        title.setBounds(windowWidth / 2 - 282 / 2, windowHeight / 24, 282, 50);
        JLabel bookNameLibrary = new JLabel("Book name : " + bookName);
        bookNameLibrary.setFont(new Font("微软雅黑",Font.PLAIN, 18));
        bookNameLibrary.setBounds(windowWidth / 15, windowHeight / 7, 500, 50);
        JLabel bookType = new JLabel("Book type : " + type1[1] + " , " + type2[1] + " , " + type3[1]);
        bookType.setFont(new Font("微软雅黑",Font.PLAIN, 18));
        bookType.setBounds(windowWidth / 15, 2 * windowHeight / 7, 500, 50);
        JLabel bookPrice = new JLabel("Book price : " + price[1]);
        bookPrice.setFont(new Font("微软雅黑",Font.PLAIN, 18));
        bookPrice.setBounds(windowWidth / 15, 3 * windowHeight / 7, 500, 50);
        final JLabel[] bookQuantity = {new JLabel("Book quantity : " + quantity[1])};
        bookQuantity[0].setFont(new Font("微软雅黑",Font.PLAIN, 18));
        bookQuantity[0].setBounds(windowWidth / 15, 4 * windowHeight / 7, 500, 50);
        JLabel bookPress = new JLabel("Book press : " + press[1]);
        bookPress.setFont(new Font("微软雅黑",Font.PLAIN, 18));
        bookPress.setBounds(windowWidth / 15, 5 * windowHeight / 7, 500, 50);
        JLabel wongLabel = new JLabel("The borrowing quantity has reached the upper limit");
        wongLabel.setForeground(Color.RED);
        wongLabel.setFont(new Font("微软雅黑",Font.PLAIN, 15));
        wongLabel.setBounds(windowWidth / 2 - 380 / 2, 5 * windowHeight / 7 + 30, 380, 50);
        JLabel correctLabel = new JLabel("Borrowing successful");
        correctLabel.setForeground(Color.GREEN);
        correctLabel.setFont(new Font("微软雅黑",Font.PLAIN, 15));
        correctLabel.setBounds(windowWidth / 2 - 160 / 2, 5 * windowHeight / 7 + 30, 160, 50);
        JLabel NothingLabel = new JLabel("The book is out of stock");
        NothingLabel.setForeground(Color.RED);
        NothingLabel.setFont(new Font("微软雅黑",Font.PLAIN, 15));
        NothingLabel.setBounds(windowWidth / 2 - 180 / 2, 5 * windowHeight / 7 + 30, 180, 50);
        panel.add(bookNameLibrary);
        panel.add(bookType);
        panel.add(bookPrice);
        panel.add(bookQuantity[0]);
        panel.add(title);
        panel.add(bookPress);
    }
}
