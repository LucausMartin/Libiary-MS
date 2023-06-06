package com.PreviewLibrary;

import com.Book.BorrowBook;
import com.Book.PreviewBorrowBook;
import com.Database.DB;
import com.SignInPackage.SignIn;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Arrays;

import static com.Database.DB.executeQuery;

public class PreviewLibraryInterface extends JFrame {
    public PreviewLibraryInterface() throws SQLException {
        super("Library");

        var connection = DB.getConnection();
        ResultSet resultSetLatestFive;
        var sqlLatestFive = "SELECT BookName FROM books ORDER BY BookNumber DESC LIMIT 5";
        assert connection != null;
        resultSetLatestFive = executeQuery(connection, sqlLatestFive);
        String[] a = new String[6];
        int i;
        i = 1;
        while (resultSetLatestFive.next()) {
            a[i] = resultSetLatestFive.getString("BookName");
            i++;
            if (i > 5) {
                break;
            }
        }

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
        JLabel signOut = new JLabel("Sign In", SwingConstants.RIGHT);
        JLabel newBooks = new JLabel("New Books", SwingConstants.LEFT);
        JLabel bookName = new JLabel("book name", SwingConstants.CENTER);
        JLabel bookType = new JLabel("book type", SwingConstants.CENTER);
        JLabel bookPress = new JLabel("book press", SwingConstants.CENTER);
        JLabel line = new JLabel("————————————————————————————————————————————————————————————————",
                SwingConstants.LEFT);
        JLabel back = new JLabel("back", SwingConstants.RIGHT);
        JLabel next = new JLabel("next", SwingConstants.CENTER);
        JLabel previous = new JLabel("previous", SwingConstants.CENTER);
        JLabel page = new JLabel("0/0", SwingConstants.CENTER);
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
        book1.setOpaque(true);
        book2.setOpaque(true);
        book3.setOpaque(true);
        book4.setOpaque(true);
        book5.setOpaque(true);
        bookName.setOpaque(true);
        line.setOpaque(false);
//        borrowedBooksDays1.setOpaque(true);
        titleLabel.setFont(new Font("Algerian", Font.PLAIN, 40));
        signOut.setFont(new Font("微软雅黑", Font.PLAIN, 15));
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
        bookName.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        bookPress.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        bookType.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        line.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        back.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        next.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        previous.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        page.setFont(new Font("微软雅黑", Font.PLAIN, 17));
        back.setForeground(new Color(0xc7a4ff));
        signOut.setForeground(new Color(0xc7a4ff));
        titleLabel.setForeground(Color.WHITE);
        newBooks.setForeground(new Color(0x303f9f));
        next.setForeground(new Color(0xc7a4ff));
        previous.setForeground(new Color(0xc7a4ff));
        page.setForeground(Color.BLACK);
        titleLabel.setSize(700, 100);
        panelBlue.add(titleLabel);
        panelBlue.add(signOut);
        panelYellow.add(book1);
        panelYellow.add(book2);
        panelYellow.add(book3);
        panelYellow.add(book4);
        panelYellow.add(book5);
        panelYellow.add(newBooks);
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

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                var windowWidth1 = PreviewLibraryInterface.super.getWidth();
                var windowHeight1 = PreviewLibraryInterface.super.getHeight();
                panelBlue.setBounds(0, 0, PreviewLibraryInterface.super.getWidth(),
                        PreviewLibraryInterface.super.getHeight() / 6);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(),
                        6 * PreviewLibraryInterface.super.getHeight() / 7);

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
                newBooks.setBounds(10, windowHeight1 - 330, 23 * windowWidth1 / 50, 32);
                book1.setBounds(10, windowHeight1 - 300, 23 * windowWidth1 / 50, 50);
                book2.setBounds(10, windowHeight1 - 250, 23 * windowWidth1 / 50, 50);
                book3.setBounds(10, windowHeight1 - 200, 23 * windowWidth1 / 50, 50);
                book4.setBounds(10, windowHeight1 - 150, 23 * windowWidth1 / 50, 50);
                book5.setBounds(10, windowHeight1 - 100, 23 * windowWidth1 / 50, 50);
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
                setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
                add(panelYellow);
                panelYellow.add(book1);
                panelYellow.add(book2);
                panelYellow.add(book3);
                panelYellow.add(book4);
                panelYellow.add(book5);
                panelYellow.add(newBooks);
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
                    setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                    setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                    panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                            PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
                    add(panelYellow);
                    panelYellow.remove(book1);
                    panelYellow.remove(book2);
                    panelYellow.remove(book3);
                    panelYellow.remove(book4);
                    panelYellow.remove(book5);
                    panelYellow.remove(newBooks);
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
                    setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                    setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                    panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                            PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
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
                setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
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
                setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
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
                setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
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
                setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
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
                setSize(PreviewLibraryInterface.super.getWidth() + 1, PreviewLibraryInterface.super.getHeight() + 1);
                setSize(PreviewLibraryInterface.super.getWidth() - 1, PreviewLibraryInterface.super.getHeight() - 1);
                panelYellow.setBounds(0, PreviewLibraryInterface.super.getHeight() / 7,
                        PreviewLibraryInterface.super.getWidth(), 6 * PreviewLibraryInterface.super.getHeight() / 7);
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
                        new PreviewLibraryInterface();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    new PreviewBorrowBook(a[1]);
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
                    new PreviewBorrowBook(a[2]);
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
                    new PreviewBorrowBook(a[3]);
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
                    new PreviewBorrowBook(a[4]);
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
                    new PreviewBorrowBook(a[5]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 1]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 2]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 3]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 4]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 5]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 6]);
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
                    new PreviewBorrowBook(BooksName[0][(p[0] - 1) * 7 + 7]);
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
