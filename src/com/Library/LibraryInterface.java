package com.Library;

import com.Book.BorrowBook;
import com.Book.ReturnBook;
import com.Database.DB;
import com.PreviewLibrary.SkipMyRoundJTextField;
import com.SignInPackage.SignIn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import static com.Database.DB.executeQuery;

public class LibraryInterface extends JFrame {
    public LibraryInterface(String name) throws SQLException {
        super("Library");

        var connection = DB.getConnection();
        ResultSet resultSetLatestFive;
        ResultSet resultSetBorrowedBooks;
        ResultSet resultSetBorrowedDays;
        var sqlLatestFive = "SELECT BookName FROM books ORDER BY BookNumber DESC LIMIT 5";
        var sqlBorrowedBooks = "SELECT BookName FROM borrow_books WHERE userNumber = " + name;
        var sqlBorrowedBooksDays = "SELECT days FROM borrow_books WHERE userNumber = " + name;
        assert connection != null;
        resultSetLatestFive = executeQuery(connection, sqlLatestFive);
        resultSetBorrowedBooks = DB.executeQuery(connection, sqlBorrowedBooks);
        resultSetBorrowedDays = DB.executeQuery(connection, sqlBorrowedBooksDays);
        String[] a = new String[6];
        String[] b = new String[6];
        int[] c = new int[6];
        int i = 1;
        while (resultSetBorrowedBooks.next()) {
            b[i] = resultSetBorrowedBooks.getString("BookName");
            i++;
            if (i > 5) {
                break;
            }
        }
        i = 1;
        while (resultSetLatestFive.next()) {
            a[i] = resultSetLatestFive.getString("BookName");
            i++;
            if (i > 5) {
                break;
            }
        }
        i = 1;
        while (resultSetBorrowedDays.next()) {
            c[i] = resultSetBorrowedDays.getInt("days");
            i++;
            if (i > 5) {
                break;
            }
        }

        int intData = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date(System.currentTimeMillis());
        String as = dateFormat.format(date);
        try {
            intData = Integer.parseInt(as);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        int count = 0;
        for (int k = 1; k < 6; k++) {
            if (c[k] == 0) {
                break;
            } else {
                int i1 = 30 - (((intData % 10) + (((intData / 10) % 10) * 10) + (((intData / 100) % 10) * 30) +
                        (((intData / 1000) % 10) * 300) + (((intData / 10000) % 10) * 365) +
                        (((intData / 100000) % 10) * 3650) + (((intData / 1000000) % 10) * 36500) +
                        (((intData / 10000000) % 10) * 365000)) -
                        ((c[k] % 10) + (((c[k] / 10) % 10) * 10) + (((c[k] / 100) % 10) * 30) +
                                (((c[k] / 1000) % 10) * 300) + (((c[k] / 10000) % 10) * 365) +
                                (((c[k] / 100000) % 10) * 3650) + (((c[k] / 1000000) % 10) * 36500) +
                                (((c[k] / 10000000) % 10) * 365000)));
                if (i1 <= 30 && i1 > 0) {
                    c[k] = i1;
                } else {
                    c[k] = -1;
                }
                count++;
            }
        }
        int count01 = 0;
        for (int j = 1; j < 6; j++) {
            if (c[j] == -1) {
                count01++;
            }
        }
        var sqlUpdateOverdue = String.format("UPDATE user SET Overdue = \"%d\"" +
                " WHERE Number = '" + name + "'", count01);
        DB.executeUpdate(connection, sqlUpdateOverdue);


        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int windowWidth = dimension.width, windowHeight = dimension.height;
        int minSizeWidth = 800, minSizeHeight = 540;
        int initSizeWidth = 900, initSizeHeight = 600;
        this.setBounds(windowWidth / 2 - initSizeWidth / 2, windowHeight / 2 - initSizeHeight / 2,
                initSizeWidth, initSizeHeight);
        this.setMinimumSize(new Dimension(minSizeWidth, minSizeHeight));


        JPanel panelBlue = new JPanel(null);
        JPanel panelYellow = new JPanel(null);
        JPanel panelYellow2 = new JPanel(null);
        panelBlue.setBackground(new Color(0x1966A2));
        panelYellow.setBackground(new Color(0xFAFFE3));
        panelYellow2.setBackground(new Color(0xFAFFE3));
        this.add(panelBlue);
        this.add(panelYellow);


        ImageIcon book = new ImageIcon("./recourse/book(1).png");
        ImageIcon search = new ImageIcon("./recourse/search(1).png");
        JLabel bookLabel = new JLabel(book);
        JLabel searchLabel = new JLabel(search);
        searchLabel.setSize(search.getIconWidth(), search.getIconHeight());
        bookLabel.setSize(100, 100);
        panelBlue.add(bookLabel);
        panelYellow.add(searchLabel);


        JLabel titleLabel = new JLabel("Pretend to have a library", SwingConstants.CENTER);
        JLabel signOut = new JLabel("Sign Out", SwingConstants.RIGHT);
        JLabel nameLabel = new JLabel(name, SwingConstants.RIGHT);
        JLabel newBooks = new JLabel("New Books", SwingConstants.LEFT);
        JLabel borrowedBooksLabel = new JLabel("Borrowed", SwingConstants.LEFT);
        JLabel bookName = new JLabel("book name", SwingConstants.CENTER);
        JLabel bookType = new JLabel("book type", SwingConstants.CENTER);
        JLabel bookPress = new JLabel("book press", SwingConstants.CENTER);
        JLabel line = new JLabel("————————————————————————————————————————————————————————————————",
                SwingConstants.LEFT);
        JLabel back = new JLabel("back", SwingConstants.RIGHT);
        JLabel next = new JLabel("next", SwingConstants.CENTER);
        JLabel previous = new JLabel("previous", SwingConstants.CENTER);
        JLabel page = new JLabel("0/0", SwingConstants.CENTER);
        JLabel borrowedBooksDays1;
        JLabel borrowedBooksDays2;
        JLabel borrowedBooksDays3;
        JLabel borrowedBooksDays4;
        JLabel borrowedBooksDays5;

        boolean c1, c2, c3, c4, c5;
        if (c[1] == -1) {
            borrowedBooksDays1 = new JLabel("overdue", SwingConstants.RIGHT);
            c1 = true;
        } else {
            borrowedBooksDays1 = new JLabel(Integer.toString(c[1]), SwingConstants.RIGHT);
            c1 = false;
        }
        if (c[2] == -1) {
            borrowedBooksDays2 = new JLabel("overdue", SwingConstants.RIGHT);
            c2 = true;
        } else {
            borrowedBooksDays2 = new JLabel(Integer.toString(c[2]), SwingConstants.RIGHT);
            c2 = false;
        }
        if (c[3] == -1) {
            borrowedBooksDays3 = new JLabel("overdue", SwingConstants.RIGHT);
            c3 = true;
        } else {
            borrowedBooksDays3 = new JLabel(Integer.toString(c[3]), SwingConstants.RIGHT);
            c3 = false;
        }
        if (c[4] == -1) {
            borrowedBooksDays4 = new JLabel("overdue", SwingConstants.RIGHT);
            c4 = true;
        } else {
            borrowedBooksDays4 = new JLabel(Integer.toString(c[4]), SwingConstants.RIGHT);
            c4 = false;
        }
        if (c[5] == -1) {
            borrowedBooksDays5 = new JLabel("overdue", SwingConstants.RIGHT);
            c5 = true;
        } else {
            borrowedBooksDays5 = new JLabel(Integer.toString(c[5]), SwingConstants.RIGHT);
            c5 = false;
        }
        JLabel bookFind1 = new JLabel("", SwingConstants.LEFT);
        JLabel bookFind2 = new JLabel("", SwingConstants.LEFT);
        JLabel bookFind3 = new JLabel("", SwingConstants.LEFT);
        JLabel bookFind4 = new JLabel("", SwingConstants.LEFT);
        JLabel bookFind5 = new JLabel("", SwingConstants.LEFT);
        JLabel bookFind6 = new JLabel("", SwingConstants.LEFT);
        JLabel bookFind7 = new JLabel("", SwingConstants.LEFT);
        JLabel book1 = new JLabel(a[1], SwingConstants.LEFT);
        JLabel book2 = new JLabel(a[2], SwingConstants.LEFT);
        JLabel book3 = new JLabel(a[3], SwingConstants.LEFT);
        JLabel book4 = new JLabel(a[4], SwingConstants.LEFT);
        JLabel book5 = new JLabel(a[5], SwingConstants.LEFT);
        JLabel borrowedBook1 = new JLabel(b[1], SwingConstants.LEFT);
        JLabel borrowedBook2 = new JLabel(b[2], SwingConstants.LEFT);
        JLabel borrowedBook3 = new JLabel(b[3], SwingConstants.LEFT);
        JLabel borrowedBook4 = new JLabel(b[4], SwingConstants.LEFT);
        JLabel borrowedBook5 = new JLabel(b[5], SwingConstants.LEFT);
        book1.setBackground(new Color(0xEEE9DC));
        book2.setBackground(new Color(0xEEE9DC));
        book3.setBackground(new Color(0xEEE9DC));
        book4.setBackground(new Color(0xEEE9DC));
        book5.setBackground(new Color(0xEEE9DC));
        bookName.setBackground(new Color(0xEEE9DC));
        bookPress.setBackground(new Color(0xEEE9DC));
        bookType.setBackground(new Color(0xEEE9DC));
        line.setBackground(Color.BLUE);
//        borrowedBooksDays1.setBackground(Color.RED);
        borrowedBook1.setBackground(new Color(0xEEE9DC));
        borrowedBook2.setBackground(new Color(0xEEE9DC));
        borrowedBook3.setBackground(new Color(0xEEE9DC));
        borrowedBook4.setBackground(new Color(0xEEE9DC));
        borrowedBook5.setBackground(new Color(0xEEE9DC));
        book1.setOpaque(true);
        book2.setOpaque(true);
        book3.setOpaque(true);
        book4.setOpaque(true);
        book5.setOpaque(true);
        bookName.setOpaque(true);
        line.setOpaque(false);
//        borrowedBooksDays1.setOpaque(true);
        borrowedBook1.setOpaque(true);
        borrowedBook2.setOpaque(true);
        borrowedBook3.setOpaque(true);
        borrowedBook4.setOpaque(true);
        borrowedBook5.setOpaque(true);
        titleLabel.setFont(new Font("Algerian", Font.PLAIN, 40));
        signOut.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        nameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBooksDays1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBooksDays2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBooksDays3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBooksDays4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBooksDays5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookFind1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        bookFind2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        bookFind3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        bookFind4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        bookFind5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        bookFind6.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        bookFind7.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        book1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        book2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        book3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        book4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        book5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBook1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBook2.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBook3.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBook4.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        borrowedBook5.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookName.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        bookPress.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        bookType.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        line.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        back.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        next.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        previous.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        page.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        back.setForeground(new Color(0xc7a4ff));
        nameLabel.setForeground(Color.WHITE);
        signOut.setForeground(new Color(0xc7a4ff));
        titleLabel.setForeground(Color.WHITE);
        newBooks.setForeground(new Color(0x303f9f));
        borrowedBooksLabel.setForeground(new Color(0x303f9f));
        next.setForeground(new Color(0xc7a4ff));
        previous.setForeground(new Color(0xc7a4ff));
        page.setForeground(Color.BLACK);
        titleLabel.setSize(700, 100);
        panelBlue.add(titleLabel);
        panelBlue.add(signOut);
        panelYellow.add(book1);
        panelYellow.add(borrowedBooksDays1);
        panelYellow.add(borrowedBooksDays2);
        panelYellow.add(borrowedBooksDays3);
        panelYellow.add(borrowedBooksDays4);
        panelYellow.add(borrowedBooksDays5);
        panelYellow.add(book2);
        panelYellow.add(book3);
        panelYellow.add(book4);
        panelYellow.add(book5);
        panelYellow.add(borrowedBook1);
        panelYellow.add(borrowedBook2);
        panelYellow.add(borrowedBook3);
        panelYellow.add(borrowedBook4);
        panelYellow.add(borrowedBook5);
        panelYellow.add(newBooks);
        panelYellow.add(borrowedBooksLabel);
        panelBlue.add(nameLabel);
        panelYellow2.add(bookFind1);
        panelYellow2.add(bookFind2);
        panelYellow2.add(bookFind3);
        panelYellow2.add(bookFind4);
        panelYellow2.add(bookFind5);
        panelYellow2.add(bookFind6);
        panelYellow2.add(bookFind7);


        SkipMyRoundJTextField searchTextField = new SkipMyRoundJTextField();
        LibraryTextField search0TextField = new LibraryTextField();
        searchTextField.setBackground(new Color(0xEEE9DC));
        search0TextField.setBackground(new Color(0xEEE9DC));
        searchTextField.setFont(new Font("Sans", Font.PLAIN, 20));
        search0TextField.setFont(new Font("Sans", Font.PLAIN, 20));
        panelYellow.add(searchTextField);

        int finalCount = count;
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                var windowWidth1 = LibraryInterface.super.getWidth();
                var windowHeight1 = LibraryInterface.super.getHeight();
                panelBlue.setBounds(0, 0, LibraryInterface.super.getWidth(),
                        LibraryInterface.super.getHeight() / 6);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(),
                        6 * LibraryInterface.super.getHeight() / 7);

                titleLabel.setBounds(windowWidth1 / 2 - titleLabel.getWidth() / 2 + 25,
                        panelBlue.getHeight() / 2 - titleLabel.getHeight() / 2,
                        titleLabel.getWidth(), titleLabel.getHeight());


                bookLabel.setBounds(windowWidth1 / 2 - titleLabel.getWidth() / 2 - 25,
                        panelBlue.getHeight() / 2 - bookLabel.getHeight() / 2,
                        bookLabel.getWidth(), bookLabel.getHeight());

                var searchHeight = panelYellow.getHeight() / 9;
                searchTextField.setBounds(panelYellow.getWidth() / 2 - 550 / 2,
                        panelYellow.getHeight() / 2 - 3 * searchHeight / 2,
                        550, 60);
                search0TextField.setBounds(panelYellow.getWidth() / 120,
                        panelYellow.getHeight() / 25,
                        550, 60);
                searchLabel.setBounds(panelYellow.getWidth() / 2 - 550 / 2 + 8,
                        panelYellow.getHeight() / 2 - 3 * searchHeight / 2 + 10,
                        search.getIconWidth(), search.getIconHeight());
                bookName.setBounds(panelYellow.getWidth() / 120, panelYellow.getHeight() / 25 + 70,
                        115, 35);
                bookPress.setBounds(panelYellow.getWidth() / 120 + 115, panelYellow.getHeight() / 25 + 70,
                        115, 35);
                bookType.setBounds(panelYellow.getWidth() / 120 + 230, panelYellow.getHeight() / 25 + 70,
                        115, 35);
                line.setBounds(panelYellow.getWidth() / 120, panelYellow.getHeight() / 25 + 99,
                        750, 10);
                next.setBounds(panelYellow.getWidth() / 120 + 550, 86 * panelYellow.getHeight() / 100,
                        50, 20);
                previous.setBounds(panelYellow.getWidth() / 120 + 150, 86 * panelYellow.getHeight() / 100,
                        100, 20);
                page.setBounds(panelYellow.getWidth() / 120 + 350, 86 * panelYellow.getHeight() / 100,
                        60, 20);
                panelYellow2.setBounds(line.getX(), 7 * windowHeight1 / 13 - 4 * windowHeight1 / 15 + 55,
                        750, windowHeight1 / 2);
                bookFind1.setBounds(panelYellow2.getX() + 10, panelYellow2.getHeight() / 8 - panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                bookFind2.setBounds(panelYellow2.getX() + 10, panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                bookFind3.setBounds(panelYellow2.getX() + 10, 2 * panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                bookFind4.setBounds(panelYellow2.getX() + 10, 3 * panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                bookFind5.setBounds(panelYellow2.getX() + 10, 4 * panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                bookFind6.setBounds(panelYellow2.getX() + 10, 5 * panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                bookFind7.setBounds(panelYellow2.getX() + 10, 6 * panelYellow2.getHeight() / 8 + 20,
                        1000, panelYellow2.getHeight() / 7);
                signOut.setSize(windowWidth1 / 16 + 20, panelBlue.getHeight() / 5);
                back.setBounds(panelYellow.getWidth() / 120 + 700, panelYellow.getHeight() / 25 + 80,
                        signOut.getWidth(), signOut.getHeight());
                signOut.setBounds(windowWidth1 - windowHeight1 / 6 - 30, 7 * panelBlue.getHeight() / 9,
                        signOut.getWidth(), signOut.getHeight());
                newBooks.setFont(new Font("Algerian", Font.PLAIN, windowHeight1 / 35));
                borrowedBooksLabel.setFont(new Font("Algerian", Font.PLAIN, windowHeight1 / 35));
                newBooks.setBounds(10, windowHeight1 - 330, 23 * windowWidth1 / 50, 32);
                borrowedBooksLabel.setBounds(windowWidth1 - (23 * windowWidth1 / 50 + 25), windowHeight1 - 330,
                        23 * windowWidth1 / 50, 32);
                book1.setBounds(10, windowHeight1 - 300, 23 * windowWidth1 / 50, 50);
                book2.setBounds(10, windowHeight1 - 250, 23 * windowWidth1 / 50, 50);
                book3.setBounds(10, windowHeight1 - 200, 23 * windowWidth1 / 50, 50);
                book4.setBounds(10, windowHeight1 - 150, 23 * windowWidth1 / 50, 50);
                book5.setBounds(10, windowHeight1 - 100, 23 * windowWidth1 / 50, 50);
                for (int x = 1; x <= finalCount; x++) {

                    if (x == 1) {
                        borrowedBooksDays1.setBounds(windowWidth1 - 100, windowHeight1 - 300,
                                70, 50);
                    }
                    if (x == 2) {
                        borrowedBooksDays2.setBounds(windowWidth1 - 100, windowHeight1 - 250,
                                70, 50);
                    }
                    if (x == 3) {
                        borrowedBooksDays3.setBounds(windowWidth1 - 100, windowHeight1 - 200,
                                70, 50);
                    }
                    if (x == 4) {
                        borrowedBooksDays4.setBounds(windowWidth1 - 100, windowHeight1 - 150,
                                70, 50);
                    }
                    if (x == 5) {
                        borrowedBooksDays5.setBounds(windowWidth1 - 100, windowHeight1 - 100,
                                70, 50);
                    }
                }
                borrowedBook1.setBounds(windowWidth1 - (23 * windowWidth1 / 50 + 25), windowHeight1 - 300,
                        23 * windowWidth1 / 50, 50);
                borrowedBook2.setBounds(windowWidth1 - (23 * windowWidth1 / 50 + 25), windowHeight1 - 250,
                        23 * windowWidth1 / 50, 50);
                borrowedBook3.setBounds(windowWidth1 - (23 * windowWidth1 / 50 + 25), windowHeight1 - 200,
                        23 * windowWidth1 / 50, 50);
                borrowedBook4.setBounds(windowWidth1 - (23 * windowWidth1 / 50 + 25), windowHeight1 - 150,
                        23 * windowWidth1 / 50, 50);
                borrowedBook5.setBounds(windowWidth1 - (23 * windowWidth1 / 50 + 25), windowHeight1 - 100,
                        23 * windowWidth1 / 50, 50);
                nameLabel.setSize(windowWidth1 / 16 + 50, panelBlue.getHeight() / 5);
                nameLabel.setBounds(windowWidth1 - windowHeight1 / 6 - 35, 7 * panelBlue.getHeight() / 9 - 20,
                        nameLabel.getWidth(), nameLabel.getHeight());
            }
        });
        final boolean[] flag01 = new boolean[1];
        final boolean[] flag02 = new boolean[1];
        final boolean[] flag03 = new boolean[1];
        final int[] p = {1};
        int[] pages = new int[1];
        final String[][] BooksName = {new String[pages[0]]};
        signOut.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                signOut.setForeground(Color.WHITE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                signOut.setForeground(new Color(0xc7a4ff));
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
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                back.setForeground(Color.BLUE);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                remove(panelYellow2);
                remove(panelYellow);
                setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                panelYellow.add(book1);
                panelYellow.add(book2);
                panelYellow.add(book3);
                panelYellow.add(book4);
                panelYellow.add(book5);
                panelYellow.add(borrowedBook1);
                panelYellow.add(borrowedBook2);
                panelYellow.add(borrowedBook3);
                panelYellow.add(borrowedBook4);
                panelYellow.add(borrowedBook5);
                panelYellow.add(borrowedBooksLabel);
                panelYellow.add(newBooks);
                panelYellow.add(borrowedBooksDays1);
                panelYellow.add(borrowedBooksDays2);
                panelYellow.add(borrowedBooksDays3);
                panelYellow.add(borrowedBooksDays4);
                panelYellow.add(borrowedBooksDays5);
                panelYellow.add(searchLabel);
                panelYellow.add(searchTextField);
                panelYellow.remove(search0TextField);
                panelYellow.remove(bookName);
                panelYellow.remove(bookPress);
                panelYellow.remove(bookType);
                panelYellow.remove(line);
                panelYellow.remove(back);
                panelYellow.remove(previous);
                panelYellow.remove(next);
                panelYellow.remove(page);
                back.setForeground(new Color(0xc7a4ff));
                repaint();
                p[0] = 1;
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    remove(panelBlue);
                    remove(panelYellow);
                    remove(panelYellow2);
                    add(panelYellow2);
                    add(panelBlue);
                    setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                    setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                    panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                            LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                    add(panelYellow);
                    panelYellow.remove(book1);
                    panelYellow.remove(book2);
                    panelYellow.remove(book3);
                    panelYellow.remove(book4);
                    panelYellow.remove(book5);
                    panelYellow.remove(borrowedBook1);
                    panelYellow.remove(borrowedBook2);
                    panelYellow.remove(borrowedBook3);
                    panelYellow.remove(borrowedBook4);
                    panelYellow.remove(borrowedBook5);
                    panelYellow.remove(borrowedBooksLabel);
                    panelYellow.remove(newBooks);
                    panelYellow.remove(borrowedBooksDays1);
                    panelYellow.remove(borrowedBooksDays2);
                    panelYellow.remove(borrowedBooksDays3);
                    panelYellow.remove(borrowedBooksDays4);
                    panelYellow.remove(borrowedBooksDays5);
                    panelYellow.remove(searchTextField);
                    panelYellow.remove(searchLabel);
                    String a = searchTextField.getText();
                    search0TextField.setText(a);
                    panelYellow.add(search0TextField);
                    bookName.setOpaque(true);
                    bookPress.setOpaque(false);
                    bookType.setOpaque(false);
                    flag01[0] = true;
                    flag02[0] = false;
                    flag03[0] = false;
                    panelYellow.add(bookName);
                    panelYellow.add(bookPress);
                    panelYellow.add(bookType);
                    panelYellow.add(line);
                    panelYellow.add(back);
//                    panelYellow.add(next);
                    panelYellow.add(page);
//                    panelYellow.add(previous);
                    Arrays.fill(BooksName[0], null);
                    Connection connection1 = DB.getConnection();
                    ResultSet resultSetPages = null;
                    var sqlPages = "SELECT COUNT(BookName) FROM books WHERE BookName LIKE '%"
                            + searchTextField.getText() + "%'";

                    try {
                        resultSetPages = DB.executeQuery(connection1, sqlPages);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    while (true) {
                        try {
                            if (!resultSetPages.next()) break;
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            pages[0] = resultSetPages.getInt("COUNT(BookName)");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    String[] booksName = new String[pages[0] + 8];
                    BooksName[0] = booksName;
                    if (pages[0] / 7 == 0) {
                        pages[0] = 1;
                    } else {
                        if (pages[0] % 7 == 0) {
                            pages[0] = pages[0] / 7;
                        } else {
                            pages[0] = pages[0] / 7 + 1;
                        }
                    }
                    if (pages[0] != 1) {
                        panelYellow.add(next);
                    }
                    page.setText(p[0] + "/" + pages[0]);
                    panelYellow.remove(page);
                    panelYellow.add(page);
                    repaint();
                    var sqlSearchTenBooks = "SELECT BookName FROM books WHERE BookName LIKE '%" +
                            searchTextField.getText() + "%'";
                    try {
                        ResultSet resultSetSearchTenBooks = DB.executeQuery(connection, sqlSearchTenBooks);
                        int i = 1;
                        while (resultSetSearchTenBooks.next()) {
                            booksName[i] = resultSetSearchTenBooks.getString("BookName");
                            i++;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    bookFind1.setText(booksName[1]);
                    bookFind2.setText(booksName[2]);
                    bookFind3.setText(booksName[3]);
                    bookFind4.setText(booksName[4]);
                    bookFind5.setText(booksName[5]);
                    bookFind6.setText(booksName[6]);
                    bookFind7.setText(booksName[7]);
                    repaint();
                }
            }
        });

        search0TextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    remove(panelBlue);
                    remove(panelYellow);
                    remove(panelYellow2);
                    add(panelYellow2);
                    add(panelBlue);
                    setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                    setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                    panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                            LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                    add(panelYellow);
                    panelYellow.remove(bookName);
                    panelYellow.remove(bookPress);
                    panelYellow.remove(bookType);
                    bookName.setOpaque(true);
                    bookPress.setOpaque(false);
                    bookType.setOpaque(false);
                    flag01[0] = true;
                    flag02[0] = false;
                    flag03[0] = false;
                    panelYellow.add(bookName);
                    panelYellow.add(bookPress);
                    panelYellow.add(bookType);
                    Arrays.fill(BooksName[0], null);
                    p[0] = 1;
                    Connection connection1 = DB.getConnection();
                    ResultSet resultSetPages = null;
                    var sqlPages = "SELECT COUNT(BookName) FROM books WHERE BookName LIKE '%"
                            + search0TextField.getText() + "%'";

                    try {
                        resultSetPages = DB.executeQuery(connection1, sqlPages);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    while (true) {
                        try {
                            if (!resultSetPages.next()) break;
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        try {
                            pages[0] = resultSetPages.getInt("COUNT(BookName)");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    String[] booksName = new String[pages[0] + 8];
                    BooksName[0] = booksName;
                    if (pages[0] / 7 == 0) {
                        pages[0] = 1;
                    } else {
                        if (pages[0] % 7 == 0) {
                            pages[0] = pages[0] / 7;
                        } else {
                            pages[0] = pages[0] / 7 + 1;
                        }
                    }
                    if (pages[0] != 1) {
                        panelYellow.add(next);
                    }
                    else {
                        panelYellow.remove(next);
                    }
                    page.setText(p[0] + "/" + pages[0]);
                    panelYellow.remove(page);
                    panelYellow.add(page);
                    repaint();
                    var sqlSearchTenBooks = "SELECT BookName FROM books WHERE BookName LIKE '%" +
                            search0TextField.getText() + "%'";
                    try {
                        ResultSet resultSetSearchTenBooks = DB.executeQuery(connection, sqlSearchTenBooks);
                        int i = 1;
                        while (resultSetSearchTenBooks.next()) {
                            booksName[i] = resultSetSearchTenBooks.getString("BookName");
                            i++;
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    bookFind1.setText(booksName[1]);
                    bookFind2.setText(booksName[2]);
                    bookFind3.setText(booksName[3]);
                    bookFind4.setText(booksName[4]);
                    bookFind5.setText(booksName[5]);
                    bookFind6.setText(booksName[6]);
                    bookFind7.setText(booksName[7]);
                    repaint();
                }
            }
        });

        next.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                next.setForeground(Color.BLUE);
                p[0]++;
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                remove(panelYellow);
                setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                bookFind1.setText(BooksName[0][(p[0] - 1) * 7 + 1]);
                bookFind2.setText(BooksName[0][(p[0] - 1) * 7 + 2]);
                bookFind3.setText(BooksName[0][(p[0] - 1) * 7 + 3]);
                bookFind4.setText(BooksName[0][(p[0] - 1) * 7 + 4]);
                bookFind5.setText(BooksName[0][(p[0] - 1) * 7 + 5]);
                bookFind6.setText(BooksName[0][(p[0] - 1) * 7 + 6]);
                bookFind7.setText(BooksName[0][(p[0] - 1) * 7 + 7]);
                next.setForeground(new Color(0xc7a4ff));
                if (p[0] == pages[0]) {
                    panelYellow.remove(next);
                    panelYellow.add(previous);
                }
                page.setText(p[0] + "/" + pages[0]);
                panelYellow.remove(page);
                panelYellow.add(page);
                repaint();
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        previous.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                p[0]--;
                previous.setForeground(Color.BLUE);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                remove(panelYellow);
                setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                previous.setForeground(new Color(0xc7a4ff));
                bookFind1.setText(BooksName[0][((p[0] - 1) * 7 + 1) - (p[0] - 1) * 7]);
                bookFind2.setText(BooksName[0][((p[0] - 1) * 7 + 2) - (p[0] - 1) * 7]);
                bookFind3.setText(BooksName[0][((p[0] - 1) * 7 + 3) - (p[0] - 1) * 7]);
                bookFind4.setText(BooksName[0][((p[0] - 1) * 7 + 4) - (p[0] - 1) * 7]);
                bookFind5.setText(BooksName[0][((p[0] - 1) * 7 + 5) - (p[0] - 1) * 7]);
                bookFind6.setText(BooksName[0][((p[0] - 1) * 7 + 6) - (p[0] - 1) * 7]);
                bookFind7.setText(BooksName[0][((p[0] - 1) * 7 + 7) - (p[0] - 1) * 7]);
                if (p[0] == 1) {
                    panelYellow.remove(previous);
                    panelYellow.add(next);
                }
                page.setText(p[0] + "/" + pages[0]);
                panelYellow.remove(page);
                panelYellow.add(page);
                repaint();
                repaint();

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookName.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag01[0] = true;
                flag02[0] = false;
                flag03[0] = false;
                bookPress.setOpaque(false);
                bookType.setOpaque(false);
                bookName.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bookName.setOpaque(flag01[0]);
                if (flag02[0]) {
                    bookName.setOpaque(false);
                }
                if (flag03[0]) {
                    bookName.setOpaque(false);
                }
                repaint();
                remove(panelBlue);
                remove(panelYellow);
                remove(panelYellow2);
                add(panelYellow2);
                add(panelBlue);
                setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                panelYellow.remove(bookName);
                panelYellow.remove(bookPress);
                panelYellow.remove(bookType);
                panelYellow.add(bookName);
                panelYellow.add(bookPress);
                panelYellow.add(bookType);
                Arrays.fill(BooksName[0], null);
                p[0] = 1;
                Connection connection1 = DB.getConnection();
                ResultSet resultSetPages = null;
                var sqlPages = "SELECT COUNT(BookName) FROM books WHERE BookName LIKE '%"
                        + search0TextField.getText() + "%'";

                try {
                    resultSetPages = DB.executeQuery(connection1, sqlPages);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        if (!resultSetPages.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        pages[0] = resultSetPages.getInt("COUNT(BookName)");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                String[] booksName = new String[pages[0] + 8];
                BooksName[0] = booksName;
                if (pages[0] / 7 == 0) {
                    pages[0] = 1;
                } else {
                    if (pages[0] % 7 == 0) {
                        pages[0] = pages[0] / 7;
                    } else {
                        pages[0] = pages[0] / 7 + 1;
                    }
                }
                if (pages[0] != 1) {
                    panelYellow.add(next);
                }
                else {
                    panelYellow.remove(next);
                }
                page.setText(p[0] + "/" + pages[0]);
                panelYellow.remove(page);
                panelYellow.add(page);
                repaint();
                var sqlSearchTenBooks = "SELECT BookName FROM books WHERE BookName LIKE '%" +
                        search0TextField.getText() + "%'";
                try {
                    ResultSet resultSetSearchTenBooks = DB.executeQuery(connection, sqlSearchTenBooks);
                    int i = 1;
                    while (resultSetSearchTenBooks.next()) {
                        booksName[i] = resultSetSearchTenBooks.getString("BookName");
                        i++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind1.setText(booksName[1]);
                bookFind2.setText(booksName[2]);
                bookFind3.setText(booksName[3]);
                bookFind4.setText(booksName[4]);
                bookFind5.setText(booksName[5]);
                bookFind6.setText(booksName[6]);
                bookFind7.setText(booksName[7]);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bookName.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bookName.setOpaque(flag01[0]);
                if (flag02[0]) {
                    bookName.setOpaque(false);
                }
                if (flag03[0]) {
                    bookName.setOpaque(false);
                }
                repaint();
            }
        });

        bookPress.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag02[0] = true;
                flag01[0] = false;
                flag03[0] = false;
                bookName.setOpaque(false);
                bookType.setOpaque(false);
                bookPress.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bookPress.setOpaque(flag02[0]);
                if (flag01[0]) {
                    bookPress.setOpaque(false);
                }
                if (flag03[0]) {
                    bookPress.setOpaque(false);
                }
                repaint();

                remove(panelBlue);
                remove(panelYellow);
                remove(panelYellow2);
                add(panelYellow2);
                add(panelBlue);
                setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                panelYellow.remove(bookName);
                panelYellow.remove(bookPress);
                panelYellow.remove(bookType);
                panelYellow.add(bookName);
                panelYellow.add(bookPress);
                panelYellow.add(bookType);
                Arrays.fill(BooksName[0], null);
                p[0] = 1;
                Connection connection1 = DB.getConnection();
                ResultSet resultSetPages = null;
                var sqlPages = "SELECT COUNT(BookName) FROM books WHERE BookPress LIKE '%"
                        + search0TextField.getText() + "%'";

                try {
                    resultSetPages = DB.executeQuery(connection1, sqlPages);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        if (!resultSetPages.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        pages[0] = resultSetPages.getInt("COUNT(BookName)");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                String[] booksName = new String[pages[0] + 8];
                BooksName[0] = booksName;
                if (pages[0] / 7 == 0) {
                    pages[0] = 1;
                } else {
                    if (pages[0] % 7 == 0) {
                        pages[0] = pages[0] / 7;
                    } else {
                        pages[0] = pages[0] / 7 + 1;
                    }
                }
                if (pages[0] != 1) {
                    panelYellow.add(next);
                }
                else {
                    panelYellow.remove(next);
                }
                page.setText(p[0] + "/" + pages[0]);
                panelYellow.remove(page);
                panelYellow.add(page);
                repaint();
                var sqlSearchTenBooks = "SELECT BookName FROM books WHERE BookPress LIKE '%" +
                        search0TextField.getText() + "%'";
                try {
                    ResultSet resultSetSearchTenBooks = DB.executeQuery(connection, sqlSearchTenBooks);
                    int i = 1;
                    while (resultSetSearchTenBooks.next()) {
                        booksName[i] = resultSetSearchTenBooks.getString("BookName");
                        i++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind1.setText(booksName[1]);
                bookFind2.setText(booksName[2]);
                bookFind3.setText(booksName[3]);
                bookFind4.setText(booksName[4]);
                bookFind5.setText(booksName[5]);
                bookFind6.setText(booksName[6]);
                bookFind7.setText(booksName[7]);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bookPress.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bookPress.setOpaque(flag02[0]);
                if (flag01[0]) {
                    bookPress.setOpaque(false);
                }
                if (flag03[0]) {
                    bookPress.setOpaque(false);
                }
                repaint();
            }
        });

        bookType.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag03[0] = true;
                flag01[0] = false;
                flag02[0] = false;
                bookPress.setOpaque(false);
                bookName.setOpaque(false);
                bookType.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                bookType.setOpaque(flag03[0]);
                if (flag01[0]) {
                    bookType.setOpaque(false);
                }
                if (flag02[0]) {
                    bookType.setOpaque(false);
                }
                repaint();

                remove(panelBlue);
                remove(panelYellow);
                remove(panelYellow2);
                add(panelYellow2);
                add(panelBlue);
                setSize(LibraryInterface.super.getWidth() + 1, LibraryInterface.super.getHeight() + 1);
                setSize(LibraryInterface.super.getWidth() - 1, LibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, LibraryInterface.super.getHeight() / 7,
                        LibraryInterface.super.getWidth(), 6 * LibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                panelYellow.remove(bookName);
                panelYellow.remove(bookPress);
                panelYellow.remove(bookType);
                panelYellow.add(bookName);
                panelYellow.add(bookPress);
                panelYellow.add(bookType);
                Arrays.fill(BooksName[0], null);
                p[0] = 1;
                Connection connection1 = DB.getConnection();
                ResultSet resultSetPages = null;
                var sqlPages = "SELECT COUNT(BookName) FROM books WHERE BookType1 LIKE '%"
                        + search0TextField.getText() + "%' OR BookType2 LIKE '%" + search0TextField.getText() +
                        "%' OR BookType3 LIKE '%" + search0TextField.getText() + "%'";

                try {
                    resultSetPages = DB.executeQuery(connection1, sqlPages);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                while (true) {
                    try {
                        if (!resultSetPages.next()) break;
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    try {
                        pages[0] = resultSetPages.getInt("COUNT(BookName)");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                String[] booksName = new String[pages[0] + 8];
                BooksName[0] = booksName;
                if (pages[0] / 7 == 0) {
                    pages[0] = 1;
                } else {
                    if (pages[0] % 7 == 0) {
                        pages[0] = pages[0] / 7;
                    } else {
                        pages[0] = pages[0] / 7 + 1;
                    }
                }
                if (pages[0] != 1) {
                    panelYellow.add(next);
                }
                else {
                    panelYellow.remove(next);
                }
                page.setText(p[0] + "/" + pages[0]);
                panelYellow.remove(page);
                panelYellow.add(page);
                repaint();
                var sqlSearchTenBooks = "SELECT BookName FROM books WHERE BookType1 LIKE '%"
                        + search0TextField.getText() + "%' OR BookType2 LIKE '%" + search0TextField.getText() +
                        "%' OR BookType3 LIKE '%" + search0TextField.getText() + "%'";
                try {
                    ResultSet resultSetSearchTenBooks = DB.executeQuery(connection, sqlSearchTenBooks);
                    int i = 1;
                    while (resultSetSearchTenBooks.next()) {
                        booksName[i] = resultSetSearchTenBooks.getString("BookName");
                        i++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind1.setText(booksName[1]);
                bookFind2.setText(booksName[2]);
                bookFind3.setText(booksName[3]);
                bookFind4.setText(booksName[4]);
                bookFind5.setText(booksName[5]);
                bookFind6.setText(booksName[6]);
                bookFind7.setText(booksName[7]);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bookType.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bookType.setOpaque(flag03[0]);
                if (flag01[0]) {
                    bookType.setOpaque(false);
                }
                if (flag02[0]) {
                    bookType.setOpaque(false);
                }
                repaint();
            }
        });

        int finalIntData4 = intData;
        book1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                book1.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                book1.setForeground(Color.BLACK);
                if (BorrowBook.DISPOSE_ON_CLOSE == 1) {
                    dispose();
                    try {
                        new LibraryInterface(name);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    new BorrowBook(a[1], name, finalIntData4);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        int finalIntData3 = intData;
        book2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                book2.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                book2.setForeground(Color.BLACK);
                try {
                    new BorrowBook(a[2], name, finalIntData3);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }


            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        int finalIntData2 = intData;
        book3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                book3.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                book3.setForeground(Color.BLACK);
                try {
                    new BorrowBook(a[3], name, finalIntData2);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        int finalIntData1 = intData;
        book4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                book4.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                book4.setForeground(Color.BLACK);
                try {
                    new BorrowBook(a[4], name, finalIntData1);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        int finalIntData = intData;
        book5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                book5.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                book5.setForeground(Color.BLACK);
                try {
                    new BorrowBook(a[5], name, finalIntData);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        int finalIntData5 = intData;
        borrowedBook1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                borrowedBook1.setForeground(new Color(0xc7a4ff));
                borrowedBooksDays1.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                borrowedBook1.setForeground(Color.BLACK);
                borrowedBooksDays1.repaint();
                try {
                    new ReturnBook(b[1], name, finalIntData5, c1);
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

            }
        });
        borrowedBook2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                borrowedBook2.setForeground(new Color(0xc7a4ff));
                borrowedBooksDays2.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                borrowedBook2.setForeground(Color.BLACK);
                borrowedBooksDays2.repaint();
                try {
                    new ReturnBook(b[2], name, finalIntData5, c2);
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

            }
        });
        borrowedBook3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                borrowedBook3.setForeground(new Color(0xc7a4ff));
                borrowedBooksDays3.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                borrowedBook3.setForeground(Color.BLACK);
                borrowedBooksDays3.repaint();
                try {
                    new ReturnBook(b[3], name, finalIntData5, c3);
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

            }
        });
        borrowedBook4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                borrowedBook4.setForeground(new Color(0xc7a4ff));
                borrowedBooksDays4.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                borrowedBook4.setForeground(Color.BLACK);
                borrowedBooksDays4.repaint();
                try {
                    new ReturnBook(b[4], name, finalIntData5, c4);
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

            }
        });
        borrowedBook5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                borrowedBook5.setForeground(new Color(0xc7a4ff));
                borrowedBooksDays5.repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                borrowedBook5.setForeground(Color.BLACK);
                borrowedBooksDays5.repaint();
                try {
                    new ReturnBook(b[5], name, finalIntData5, c5);
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

            }
        });

        int finalIntData6 = intData;
        bookFind1.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind1.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 1],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind1.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookFind2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind2.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 2],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind2.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookFind3.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind3.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 3],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind3.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookFind4.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind4.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 4],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind4.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookFind5.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind5.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 5],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind5.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookFind6.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind6.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 6],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind6.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        bookFind7.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                bookFind7.setForeground(new Color(0xc7a4ff));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    new BorrowBook(BooksName[0][(p[0] - 1) * 7 + 7],name, finalIntData6);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                bookFind7.setForeground(Color.BLACK);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(true);
        this.setExtendedState(JFrame.MAXIMIZED_HORIZ);
    }
}
