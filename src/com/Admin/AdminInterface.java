package com.Admin;

import com.Database.DB;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminInterface extends JFrame {
    public AdminInterface() throws Exception {
        super("Admin");
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(null);
        int minSizeWidth = 800, minSizeHeight = 540;
        int initSizeWidth = 900, initSizeHeight = 700;
        this.setBounds(dimension.width / 2 - initSizeWidth / 2, dimension.height / 2 - initSizeHeight / 2,
                initSizeWidth, initSizeHeight);
        this.setMinimumSize(new Dimension(minSizeWidth, minSizeHeight));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


        JPanel panelYellow = new JPanel(null);
        panelYellow.setBackground(new Color(0xFAFFE3));
        panelYellow.setBounds(0, 0, initSizeWidth, initSizeHeight);
        JPanel panelDarkYellow = new JPanel(null);
        panelDarkYellow.setBackground(new Color(0xE7E494));
        panelDarkYellow.setBounds(0, 0, initSizeWidth / 6, initSizeHeight);
        JPanel addPanel = new JPanel(null);
        JPanel deletePanel = new JPanel(null);
        JPanel changPanel  = new JPanel(null);
        JPanel searchPanel = new JPanel(null);
        addPanel.setBackground(new Color(0xFFFFE3));
        deletePanel.setBackground(new Color(0xFFFFE3));
        changPanel.setBackground(new Color(0xFAFAF3));
        searchPanel.setBackground(new Color(0xFFFFE3));
        addPanel.setBounds(panelDarkYellow.getWidth(), 0,
                initSizeWidth - panelDarkYellow.getWidth(), initSizeHeight);
        deletePanel.setBounds(panelDarkYellow.getWidth(), 0,
                initSizeWidth - panelDarkYellow.getWidth(), initSizeHeight);
        changPanel.setBounds(panelDarkYellow.getWidth(), 0,
                initSizeWidth - panelDarkYellow.getWidth(), initSizeHeight);
        searchPanel.setBounds(panelDarkYellow.getWidth(), 0,
                initSizeWidth - panelDarkYellow.getWidth(), initSizeHeight);
        this.add(panelDarkYellow);
        this.add(addPanel);
        this.add(panelYellow);

        JButton confirmModification = new JButton("confirm modification");
        JButton deleteBookButton = new JButton("submit");
        JButton submit = new JButton("submit");
        JLabel addBooks = new JLabel(" Add books", SwingConstants.CENTER);
        addBooks.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        addBooks.setBackground(new Color(0xFAFFE3));
        addBooks.setOpaque(true);
        addBooks.setBounds(0,panelDarkYellow.getHeight() / 9,
                panelDarkYellow.getWidth(), 50);
        JLabel deleteBooks = new JLabel(" Delete books", SwingConstants.CENTER);
        deleteBooks.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        deleteBooks.setBackground(new Color(0xFAFFE3));
        deleteBooks.setOpaque(false);
        deleteBooks.setBounds(0, 3 * panelDarkYellow.getHeight() / 9,
                panelDarkYellow.getWidth(), 50);
        JLabel changBooks = new JLabel(" Chang books", SwingConstants.CENTER);
        changBooks.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        changBooks.setBackground(new Color(0xFAFFE3));
        changBooks.setOpaque(false);
        changBooks.setBounds(0, 5 * panelDarkYellow.getHeight() / 9,
                panelDarkYellow.getWidth(), 50);
        JLabel searchBooks = new JLabel("", SwingConstants.CENTER);
        searchBooks.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        searchBooks.setBackground(new Color(0xFAFFE3));
        searchBooks.setOpaque(false);
        searchBooks.setBounds(0, 7 * panelDarkYellow.getHeight() / 9,
                panelDarkYellow.getWidth(), 50);
        JLabel bookNameLabel = new JLabel("Book name : ", SwingConstants.RIGHT);
        JLabel bookType1Label = new JLabel("Book type1 : ", SwingConstants.RIGHT);
        JLabel bookType2Label = new JLabel("Book type2 : ", SwingConstants.RIGHT);
        JLabel bookType3Label = new JLabel("Book type3 : ", SwingConstants.RIGHT);
        JLabel bookPriceLabel = new JLabel("Book price : ", SwingConstants.RIGHT);
        JLabel bookPressLabel = new JLabel("Book press : ", SwingConstants.RIGHT);
        JLabel bookQuantity = new JLabel("Book quantity : ", SwingConstants.RIGHT);
        JLabel bookPriceSearch = new JLabel("Book price : ", SwingConstants.RIGHT);
        JLabel bookNumberSearch = new JLabel("Book number : ", SwingConstants.RIGHT);
        JLabel bookNameSearch = new JLabel("Book name : ", SwingConstants.RIGHT);
        JLabel bookPressSearch = new JLabel("Book press : ", SwingConstants.RIGHT);
        JLabel bookQuantitySearch = new JLabel("Book quantity : ", SwingConstants.RIGHT);
        JLabel bookType1Search = new JLabel("Book type1 : ", SwingConstants.RIGHT);
        JLabel bookNumberDelete = new JLabel("Book Number : ", SwingConstants.RIGHT);
        JLabel deleteBook = new JLabel("Delete book : ", SwingConstants.RIGHT);
        JLabel wrongPrice = new JLabel("The input can only be integers or decimals", SwingConstants.LEFT);
        JLabel wrongQuantity = new JLabel("Only positive integers can be entered", SwingConstants.LEFT);
        JLabel addSucceed = new JLabel("successful", SwingConstants.LEFT);
        JLabel wrongBookName = new JLabel("The book name already exists", SwingConstants.LEFT);
        JLabel wrongBookNumber = new JLabel("Please enter the correct book number", SwingConstants.LEFT);
        JLabel deleteSucceed = new JLabel("successful", SwingConstants.LEFT);
        JLabel wrongChang = new JLabel("The input can only be integers or decimals", SwingConstants.LEFT);
        wrongChang.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        wrongChang.setForeground(Color.RED);
        deleteSucceed.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        deleteSucceed.setForeground(Color.RED);
        wrongBookNumber.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        wrongBookNumber.setForeground(Color.RED);
        wrongBookName.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        wrongBookName.setForeground(Color.RED);
        wrongPrice.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        wrongPrice.setForeground(Color.RED);
        wrongQuantity.setFont(new Font("微软雅黑", Font.PLAIN, 12));
        wrongQuantity.setForeground(Color.RED);
        addSucceed.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        addSucceed.setForeground(Color.GREEN);
        deleteBook.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        submit.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        confirmModification.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        deleteBookButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNumberDelete.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPriceSearch.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNumberSearch.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNameSearch.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPressSearch.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType1Search.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookQuantitySearch.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType1Label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType2Label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType3Label.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPressLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPriceLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookQuantity.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JTextField bookNameTextField = new JTextField();
        JTextField bookType1TextField = new JTextField();
        JTextField bookType2TextField = new JTextField();
        JTextField bookType3TextField = new JTextField();
        JTextField bookPriceTextField = new JTextField();
        JTextField bookPressTextField = new JTextField();
        JTextField bookQuantityTextField = new JTextField();
        JTextField bookNumberSearchText = new JTextField();
        JTextField bookNameSearchText = new JTextField();
        JTextField bookPressSearchText = new JTextField();
        JTextField bookPriceSearchText = new JTextField();
        JTextField bookQuantitySearchText = new JTextField();
        JTextField bookType1SearchText = new JTextField();
        JTextField deleteBookTextField = new JTextField();
        bookNumberSearchText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNumberSearchText.setEnabled(false);
        bookNameSearchText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPressSearchText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPriceSearchText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookQuantitySearchText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType1SearchText.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNameTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType1TextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType2TextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookType3TextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPriceTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookPressTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookQuantityTextField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        bookNameTextField.setBounds(170, addPanel.getHeight() / 16 + 10, 200, 30);
        bookType1TextField.setBounds(170, 3 * addPanel.getHeight() / 16 + 10, 200, 30);
        bookType2TextField.setBounds(170, 5 * addPanel.getHeight() / 16 + 10, 200, 30);
        bookType3TextField.setBounds(170, 7 * addPanel.getHeight() / 16 + 10, 200, 30);
        bookPressTextField.setBounds(170, 9 * addPanel.getHeight() / 16 + 10, 200, 30);
        bookPriceTextField.setBounds(170, 11 * addPanel.getHeight() / 16 + 10, 200, 30);
        bookQuantityTextField.setBounds(170, 13 * addPanel.getHeight() / 16 + 10, 200, 30);
        bookNameLabel.setBounds(50, addPanel.getHeight() / 16,
                120, 50);
        wrongBookName.setBounds(100, addPanel.getHeight() / 16 + 50, 300, 20);
        bookType1Label.setBounds(50, 3 * addPanel.getHeight() / 16,
                120, 50);
        bookType2Label.setBounds(50, 5 * addPanel.getHeight() / 16,
                120, 50);
        bookType3Label.setBounds(50, 7 * addPanel.getHeight() / 16,
                120, 50);
        bookPressLabel.setBounds(50, 9 * addPanel.getHeight() / 16,
                120, 50);
        bookPriceLabel.setBounds(50, 11 * addPanel.getHeight() / 16,
                120, 50);
        wrongPrice.setBounds(100, 11 * addPanel.getHeight() / 16 + 50, 300, 20);
        bookQuantity.setBounds(50, 13 * addPanel.getHeight() / 16,
                120, 50);
        wrongQuantity.setBounds(100, 13 * addPanel.getHeight() / 16 + 50, 300, 20);
        addSucceed.setBounds(100, 13 * addPanel.getHeight() / 16 + 70, 200, 20);
        submit.setBounds(500, 13 * addPanel.getHeight() / 16 + 40,
                120, 50);
        deleteBook.setBounds(0, 40, 150, 50);
        Vector head = null;
        Vector rowData = null;
        head = new BookDao().bookHead();
        rowData = new BookDao().bookRows();
        JTable BookTable = new JTable(rowData, head);
        BookTable.getTableHeader().setReorderingAllowed(false);
        BookTable.setRowHeight(30);
        final JScrollPane[] jScrollPane = {new JScrollPane(BookTable)};
        jScrollPane[0].setBounds(0, 0, changPanel.getWidth() - 10, 3 * changPanel.getHeight() / 5);
        BookTable.setBounds(0, 0, jScrollPane[0].getWidth(), jScrollPane[0].getHeight());
        bookNumberSearch.setBounds(jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 30,
                120, 50);
        bookNameSearch.setBounds(5 * jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 30,
                120, 50);
        bookPressSearch.setBounds( jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 80,
                120, 50);
        bookPriceSearch.setBounds(5 * jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 80,
                120, 50);
        bookType1Search.setBounds( jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 130,
                120, 50);
        bookQuantitySearch.setBounds( 5 * jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 130,
                120, 50);
        bookNumberSearchText.setBounds(jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 40,
                150, 30);
        bookNameSearchText.setBounds(5 * jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 40,
                150, 30);
        bookPressSearchText.setBounds(jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 90,
                150, 30);
        bookPriceSearchText.setBounds(5 * jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 90,
                150, 30);
        bookType1SearchText.setBounds(jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 140,
                150, 30);
        bookQuantitySearchText.setBounds(5 * jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 140,
                150, 30);
        confirmModification.setBounds(jScrollPane[0].getWidth() / 2 - 100, jScrollPane[0].getHeight() + 200,
                200, 30);
        wrongChang.setBounds(jScrollPane[0].getWidth() / 2 - 120, jScrollPane[0].getHeight() + 170,
                300, 30);
        deleteBookTextField.setBounds(150, 48, 200, 30);
        wrongBookNumber.setBounds(350, 48, 300, 20);
        deleteSucceed.setBounds(350, 48, 300, 20);
        deleteBookButton.setBounds(250, 80, 100, 40);
        deletePanel.add(deleteBookButton);
        deletePanel.add(deleteBookTextField);
        deletePanel.add(deleteBook);
        changPanel.add(confirmModification);
        changPanel.add(bookNameSearchText);
        changPanel.add(bookPressSearchText);
        changPanel.add(bookPriceSearchText);
        changPanel.add(bookNumberSearchText);
        changPanel.add(bookPressSearch);
        changPanel.add(bookPriceSearch);
        changPanel.add(bookNumberSearch);
        changPanel.add(bookNameSearch);
        changPanel.add(bookQuantitySearch);
        changPanel.add(bookType1Search);
        changPanel.add(bookQuantitySearchText);
        changPanel.add(bookType1SearchText);
        changPanel.add(jScrollPane[0]);
        addPanel.add(submit);
        addPanel.add(bookNameTextField);
        addPanel.add(bookType1TextField);
        addPanel.add(bookType2TextField);
        addPanel.add(bookType3TextField);
        addPanel.add(bookPriceTextField);
        addPanel.add(bookPressTextField);
        addPanel.add(bookQuantityTextField);
        addPanel.add(bookNameLabel);
        addPanel.add(bookType1Label);
        addPanel.add(bookType2Label);
        addPanel.add(bookType3Label);
        addPanel.add(bookPressLabel);
        addPanel.add(bookPriceLabel);
        addPanel.add(bookQuantity);
        panelDarkYellow.add(addBooks);
        panelDarkYellow.add(deleteBooks);
        panelDarkYellow.add(changBooks);
        panelDarkYellow.add(searchBooks);

        Connection connection = DB.getConnection();


        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                var windowWidth = AdminInterface.super.getWidth();
                var windowHeight = AdminInterface.super.getHeight();
                panelYellow.setBounds(0, 0, windowWidth, windowHeight);
                panelDarkYellow.setBounds(0, 0, windowWidth / 6, windowHeight);
                addBooks.setBounds(0,panelDarkYellow.getHeight() / 9,
                        panelDarkYellow.getWidth(), 50);
                deleteBooks.setBounds(0,3 *  panelDarkYellow.getHeight() / 9,
                        panelDarkYellow.getWidth(), 50);
                changBooks.setBounds(0, 5 * panelDarkYellow.getHeight() / 9,
                        panelDarkYellow.getWidth(), 50);
                searchBooks.setBounds(0, 7 * panelDarkYellow.getHeight() / 9,
                        panelDarkYellow.getWidth(), 50);
                addPanel.setBounds(panelDarkYellow.getWidth(), 0,
                        windowWidth - panelDarkYellow.getWidth(), windowHeight);
                deletePanel.setBounds(panelDarkYellow.getWidth(), 0,
                        windowWidth - panelDarkYellow.getWidth(), windowHeight);
                changPanel.setBounds(panelDarkYellow.getWidth(), 0,
                        windowWidth - panelDarkYellow.getWidth(), windowHeight);
                searchPanel.setBounds(panelDarkYellow.getWidth(), 0,
                        windowWidth - panelDarkYellow.getWidth(), windowHeight);
                jScrollPane[0].setBounds(0, 0,
                        changPanel.getWidth() - 15, 3 * changPanel.getHeight() / 5);
                BookTable.setBounds(0, 0, jScrollPane[0].getWidth(), jScrollPane[0].getHeight() - 15);
                bookNumberSearch.setBounds(jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 30,
                        120, 50);
                bookNameSearch.setBounds(5 * jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 30,
                        120, 50);
                bookPressSearch.setBounds( jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 80,
                        120, 50);
                bookPriceSearch.setBounds(5 * jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 80,
                        120, 50);
                bookType1Search.setBounds( jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 130,
                        120, 50);
                bookQuantitySearch.setBounds( 5 * jScrollPane[0].getWidth() / 9, jScrollPane[0].getHeight() + 130,
                        120, 50);
                bookNumberSearchText.setBounds(jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 40,
                        150, 30);
                bookNameSearchText.setBounds(5 * jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 40,
                        150, 30);
                bookPressSearchText.setBounds(jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 90,
                        150, 30);
                bookPriceSearchText.setBounds(5 * jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 90,
                        150, 30);
                confirmModification.setBounds(jScrollPane[0].getWidth() / 2 - 100, jScrollPane[0].getHeight() + 200,
                        200, 30);
                wrongChang.setBounds(jScrollPane[0].getWidth() / 2 - 120, jScrollPane[0].getHeight() + 170,
                        300, 30);
                bookQuantitySearchText.setBounds(5 * jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 140,
                        150, 30);
                bookType1SearchText.setBounds(jScrollPane[0].getWidth() / 9 + 120, jScrollPane[0].getHeight() + 140,
                        150, 30);
            }
        });
        final boolean[] flag01 = new boolean[1];
        final boolean[] flag02 = new boolean[1];
        final boolean[] flag03 = new boolean[1];
        final boolean[] flag04 = new boolean[1];
        addBooks.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag01[0] = true;
                flag02[0] = false;
                flag03[0] = false;
                flag04[0] = false;
                searchBooks.setOpaque(false);
                changBooks.setOpaque(false);
                deleteBooks.setOpaque(false);
                addBooks.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                addBooks.setOpaque(flag01[0]);
                if (flag02[0]) {
                    addBooks.setOpaque(false);
                }
                if (flag03[0]) {
                    addBooks.setOpaque(false);
                }
                if (flag04[0]) {
                    addBooks.setOpaque(false);
                }
                remove(panelDarkYellow);
                remove(panelYellow);
                remove(addPanel);
                remove(deletePanel);
                remove(changPanel);
                remove(searchPanel);
                add(addPanel);
                add(panelDarkYellow);
                add(panelYellow);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                addBooks.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                addBooks.setOpaque(flag01[0]);
                if (flag02[0]) {
                    addBooks.setOpaque(false);
                }
                if (flag03[0]) {
                    addBooks.setOpaque(false);
                }
                if (flag04[0]) {
                    addBooks.setOpaque(false);
                }
                repaint();
            }
        });
        deleteBooks.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag01[0] = false;
                flag02[0] = true;
                flag03[0] = false;
                flag04[0] = false;
                searchBooks.setOpaque(false);
                changBooks.setOpaque(false);
                deleteBooks.setOpaque(true);
                addBooks.setOpaque(false);
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                deleteBooks.setOpaque(flag02[0]);
                if (flag01[0]) {
                    deleteBooks.setOpaque(false);
                }
                if (flag03[0]) {
                    deleteBooks.setOpaque(false);
                }
                if (flag04[0]) {
                    deleteBooks.setOpaque(false);
                }
                remove(panelDarkYellow);
                remove(panelYellow);
                remove(addPanel);
                remove(deletePanel);
                remove(changPanel);
                remove(searchPanel);
                add(deletePanel);
                add(panelDarkYellow);
                add(panelYellow);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                deleteBooks.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                deleteBooks.setOpaque(flag02[0]);
                if (flag01[0]) {
                    deleteBooks.setOpaque(false);
                }
                if (flag03[0]) {
                    deleteBooks.setOpaque(false);
                }
                if (flag04[0]) {
                    deleteBooks.setOpaque(false);
                }
                repaint();
            }
        });
        changBooks.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag01[0] = false;
                flag02[0] = false;
                flag03[0] = true;
                flag04[0] = false;
                searchBooks.setOpaque(false);
                changBooks.setOpaque(true);
                deleteBooks.setOpaque(false);
                addBooks.setOpaque(false);
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                changBooks.setOpaque(flag03[0]);
                if (flag02[0]) {
                    changBooks.setOpaque(false);
                }
                if (flag01[0]) {
                    changBooks.setOpaque(false);
                }
                if (flag04[0]) {
                    changBooks.setOpaque(false);
                }
                remove(panelDarkYellow);
                remove(panelYellow);
                remove(addPanel);
                remove(deletePanel);
                remove(changPanel);
                remove(searchPanel);
                add(changPanel);
                add(panelDarkYellow);
                add(panelYellow);
                repaint();
                Vector head1 = null;
                Vector rowData1 = null;
                try {
                    head1 = new BookDao().bookHead();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                try {
                    rowData1 = new BookDao().bookRows();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                TableModel tableModel = new DefaultTableModel(rowData1, head1);
                BookTable.setModel(tableModel);
                changPanel.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                changBooks.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                changBooks.setOpaque(flag03[0]);
                if (flag02[0]) {
                    changBooks.setOpaque(false);
                }
                if (flag01[0]) {
                    changBooks.setOpaque(false);
                }
                if (flag04[0]) {
                    changBooks.setOpaque(false);
                }
                repaint();
            }
        });
        searchBooks.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                flag01[0] = false;
                flag02[0] = false;
                flag03[0] = false;
                flag04[0] = true;
                searchBooks.setOpaque(true);
                changBooks.setOpaque(false);
                deleteBooks.setOpaque(false);
                addBooks.setOpaque(false);
                repaint();

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                searchBooks.setOpaque(flag04[0]);
                if (flag02[0]) {
                    searchBooks.setOpaque(false);
                }
                if (flag03[0]) {
                    searchBooks.setOpaque(false);
                }
                if (flag01[0]) {
                    searchBooks.setOpaque(false);
                }
                remove(panelDarkYellow);
                remove(panelYellow);
                remove(addPanel);
                remove(deletePanel);
                remove(changPanel);
                remove(searchPanel);
                add(searchPanel);
                add(panelDarkYellow);
                add(panelYellow);
                repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                searchBooks.setOpaque(true);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                searchBooks.setOpaque(flag04[0]);
                if (flag02[0]) {
                    searchBooks.setOpaque(false);
                }
                if (flag03[0]) {
                    searchBooks.setOpaque(false);
                }
                if (flag01[0]) {
                    searchBooks.setOpaque(false);
                }
                repaint();
            }
        });
        submit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                boolean flag1 = false, flag2 = false, flag3 = false;
                String name = bookNameTextField.getText();
                String type1 = bookType1TextField.getText();
                String type2 = bookType2TextField.getText();
                String type3 = bookType3TextField.getText();
                String press = bookPressTextField.getText();
                String price = bookPriceTextField.getText();
                String quantity = bookQuantityTextField.getText();
                int Quantity = 0;
                int count = 0;
                String priceVerify = "([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+)";
                String QuantityVerify = "^[1-9]\\d*$";
                Pattern VerifyQuantityPattern = Pattern.compile(QuantityVerify);
                Pattern VerifyPricePattern = Pattern.compile(priceVerify);
                Matcher VerifyQuantityMatcher = VerifyQuantityPattern.matcher(quantity);
                Matcher VerifyPriceMatcher = VerifyPricePattern.matcher(price);
                ResultSet resultSetFindBookName;
                var sqlFindBookName = "SELECT BookName FROM books WHERE BookName = '" + bookNameTextField.getText() + "'";
                try {
                    resultSetFindBookName = DB.executeQuery(connection, sqlFindBookName);
                    while(resultSetFindBookName.next()) {
                        count++;
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if(count > 0) {
                    addPanel.add(wrongBookName);
                    addPanel.repaint();
                }
                else {
                    addPanel.remove(wrongBookName);
                    addPanel.repaint();
                    flag3 = true;
                }
                if(VerifyPriceMatcher.matches()) {
                    flag1 = true;
                    addPanel.remove(wrongPrice);
                }
                else {
                    addPanel.add(wrongPrice);
                }
                addPanel.repaint();
                if(VerifyQuantityMatcher.matches()) {
                    flag2 = true;
                    Quantity = Integer.parseInt(quantity);
                    addPanel.remove(wrongQuantity);
                }
                else {
                    addPanel.add(wrongQuantity);
                }
                addPanel.repaint();
                if(flag1 && flag2 && flag3) {
                    addPanel.remove(wrongQuantity);
                    addPanel.remove(wrongPrice);
                    addPanel.remove(wrongBookName);
                    addPanel.add(addSucceed);
                    addPanel.repaint();
                    var sqlAddBook = String.format(
                            "INSERT INTO books (BookName, BookPress, BookPrice, BookType1, BookType2, BookType3, BookQuantity) " +
                                    "VALUES (\"%S\", \"%S\", \"%S\", \"%S\", \"%S\", \"%S\", \"%d\")",
                            name, press, price, type1, type2, type3, Quantity);
                    try {
                        assert connection != null;
                        DB.executeUpdate(connection, sqlAddBook);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
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
        deleteBookButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String deleteBook = deleteBookTextField.getText();
                String deleteBookVerify = "^[1-9]\\d*$";
                Pattern deleteBookVerifyPattern = Pattern.compile(deleteBookVerify);
                Matcher deleteBookVerifyMatcher = deleteBookVerifyPattern.matcher(deleteBook);
                if(deleteBookVerifyMatcher.matches()) {
                    var sqlFindBookNumber = "SELECT BookNumber FROM books WHERE BookNumber = " + deleteBook;
                    ResultSet resultSet = null;
                    try {
                        resultSet = DB.executeQuery(connection, sqlFindBookNumber);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    int count = 0, number = -1;
                    while(true) {
                        try {
                            if (!resultSet.next()) break;
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        count++;
                        try {
                            number = resultSet.getInt("BookNumber");
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                    if(count > 0) {
                        deletePanel.remove(wrongBookName);
                        deletePanel.add(deleteSucceed);
                        deletePanel.repaint();
                        try {
                            var sqlDeleteBook = "DELETE FROM books WHERE BookNumber = "
                                    + number;
                            DB.executeUpdate(connection, sqlDeleteBook);
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                        deletePanel.remove(wrongBookNumber);
                    }
                    deletePanel.add(wrongBookNumber);
                    deletePanel.repaint();
                }
                else {
                    deletePanel.add(wrongBookNumber);
                    deletePanel.repaint();
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
        BookTable.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = BookTable.getSelectedRow();
                bookNumberSearchText.setText((String) BookTable.getValueAt(row, 0));
                bookNameSearchText.setText((String) BookTable.getValueAt(row, 1));
                bookPressSearchText.setText((String) BookTable.getValueAt(row, 2));
                bookPriceSearchText.setText((String) BookTable.getValueAt(row, 3));
                bookType1SearchText.setText((String) BookTable.getValueAt(row, 4));
                bookQuantitySearchText.setText((String) BookTable.getValueAt(row, 7));
                changPanel.repaint();
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
        confirmModification.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                boolean flag1 = false, flag2 = false;
                String number = bookNumberSearchText.getText();
                String type1 = bookType1SearchText.getText();
                String name = bookNameSearchText.getText();
                String press = bookPressSearchText.getText();
                String price = bookPriceSearchText.getText();
                String quantity = bookQuantitySearchText.getText();
                int Quantity = 0;
                String priceVerify = "([1-9]\\d*\\.?\\d+)|(0\\.\\d*[1-9])|(\\d+)";
                String QuantityVerify = "^[1-9]\\d*$";
                Pattern VerifyQuantityPattern = Pattern.compile(QuantityVerify);
                Pattern VerifyPricePattern = Pattern.compile(priceVerify);
                Matcher VerifyQuantityMatcher = VerifyQuantityPattern.matcher(quantity);
                Matcher VerifyPriceMatcher = VerifyPricePattern.matcher(price);
                if(VerifyPriceMatcher.matches()) {
                    flag1 = true;
                    changPanel.remove(wrongChang);
                }
                else {
                    changPanel.add(wrongChang);
                }
                changPanel.repaint();
                if(VerifyQuantityMatcher.matches()) {
                    flag2 = true;
                    changPanel.remove(wrongChang);
                }
                else {
                    changPanel.add(wrongChang);
                }
                changPanel.repaint();
                if(flag1 && flag2) {
                    wrongChang.setText("Successful");
                    wrongChang.setForeground(Color.GREEN);
                    changPanel.add(wrongChang);
                    changPanel.repaint();
                    var sqlAddBook = String.format("UPDATE books SET BookPrice = '" + price + "',  BookType1 = '" + type1 + "' , BookName = '" + name + "' , BookPress = '" + press + "', BookQuantity = '" + quantity + "' WHERE BookNumber = '" + number + "'");
                    try {
                        assert connection != null;
                        DB.executeUpdate(connection, sqlAddBook);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    Vector head1 = null;
                    Vector rowData1 = null;
                    try {
                        head1 = new BookDao().bookHead();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    try {
                        rowData1 = new BookDao().bookRows();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    TableModel tableModel = new DefaultTableModel(rowData1, head1);
                    BookTable.setModel(tableModel);
                    changPanel.repaint();
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
    }
}
