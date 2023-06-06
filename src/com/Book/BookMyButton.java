package com.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;


public class BookMyButton extends JButton {
    //判断类的serialVersionUID来验证版本一致性的
    private static final long serialVersionUID = 39082560987930759L;


    //默认颜色 两种渐变色
    private Color BUTTON_COLOR1 = new Color(0x9575cd);
    private Color BUTTON_COLOR2 = new Color(0xc7a4ff);

    /*public static final Color BUTTON_COLOR1 = new Color(125, 161, 237);
    public static final Color BUTTON_COLOR2 = new Color(91, 118, 173);  */
    //按下按钮时字体的默认颜色
    private Color BUTTON_FOREGROUND_COLOR1 = new Color(0xB05A84);
    private Color BUTTON_FOREGROUND_COLOR2 = Color.WHITE;
    //默认字体
    private Font font = new Font("system", Font.PLAIN, 20);
    //判断是否按下
    private boolean hover;
    private float clickTran = 0.6F, exitTran = 1F;
    //修改按下后透明度
    public void setClickTran(float tran) {
        clickTran = tran;
    }
    //修改按下前透明度
    public void setExitTran(float tran) {
        exitTran = tran;
    }
    //上半段渐变颜色
    public void setBUTTON_COLOR1(Color bUTTON_COLOR1) {
        BUTTON_COLOR1 = bUTTON_COLOR1;
    }
    //下半段渐变颜色
    public void setBUTTON_COLOR2(Color bUTTON_COLOR2) {
        BUTTON_COLOR2 = bUTTON_COLOR2;
    }
    //按下前字体颜色
    public void setBUTTON_FOREGROUND_COLOR1(Color bUTTON_FOREGROUND_COLOR1) {
        BUTTON_FOREGROUND_COLOR1 = bUTTON_FOREGROUND_COLOR1;
    }
    //按下后字体颜色
    public void setBUTTON_FOREGROUND_COLOR2(Color bUTTON_FOREGROUND_COLOR2) {
        BUTTON_FOREGROUND_COLOR2 = bUTTON_FOREGROUND_COLOR2;
    }

    public BookMyButton(ImageIcon icon) {
        setIcon(icon);
        Init();
    }
    public BookMyButton(String name) {
        setText(name);
        Init();
    }

    public void Init() {
        setFont(font);
        setBorderPainted(false);
        setForeground(BUTTON_FOREGROUND_COLOR1);
        setFocusPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {  //鼠标移动到上面时
                setForeground(BUTTON_FOREGROUND_COLOR2);
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {  //鼠标移开时
                setForeground(BUTTON_FOREGROUND_COLOR1);
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int h = getHeight();
        int w = getWidth();
        float tran = clickTran;
        if (!hover) {
//	            tran = 0.3F;  
//	        	tran = 0.6F;  
            tran = exitTran;
        }

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint p1;
        GradientPaint p2;

        if (getModel().isPressed()) {
            p1 = new GradientPaint(0, 0, new Color(0, 0, 0), 0, h - 1,
                    new Color(100, 100, 100));
            p2 = new GradientPaint(0, 1, new Color(0, 0, 0, 50), 0, h - 3,
                    new Color(255, 255, 255, 100));
        } else {
            p1 = new GradientPaint(0, 0, new Color(100, 100, 100), 0, h - 1,
                    new Color(0, 0, 0));
            p2 = new GradientPaint(0, 1, new Color(255, 255, 255, 100), 0,
                    h - 3, new Color(0, 0, 0, 50));
        }
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
                tran));
        RoundRectangle2D.Float r2d = new RoundRectangle2D.Float(0, 0, w - 1,
                h - 1, 20, 20);
        Shape clip = g2d.getClip();
        g2d.clip(r2d);

        GradientPaint gp = new GradientPaint(0.0F, 0.0F, BUTTON_COLOR1, 0.0F,
                h, BUTTON_COLOR2, true);

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        g2d.setClip(clip);
        g2d.setPaint(p1);
        g2d.drawRoundRect(0, 0, w - 1, h - 1, 20, 20);
        g2d.setPaint(p2);
        g2d.drawRoundRect(1, 1, w - 3, h - 3, 18, 18);
        g2d.dispose();
        super.paintComponent(g);
    }

    public void setBorder(int i, int i1, int i2, int i3) {
    }
}