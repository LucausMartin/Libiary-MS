package com.PreviewLibrary;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LibraryTextField extends JTextField{
        private Shape shape;
        public LibraryTextField() {
//        super(size);
            setOpaque(false); // As suggested by @AVD in comment.
        }

        @Override
        public void setBounds(int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            super.setBounds(x, y, width, height);
        }

        protected void paintComponent(Graphics g) {
            g.setColor(getBackground());
            g.fillRoundRect(0, 0, getWidth()-2, getHeight()-2, 24, 24);
            super.paintComponent(g);
        }
        protected void paintBorder(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            //圆角抗锯齿
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
//		g2d.setColor(new Color(221,221,221));

            g2d.drawRoundRect(0, 0, getWidth()-2, getHeight()-2, 24, 24);
            this.setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 10));
        }

        public boolean contains(int x, int y) {

            if (shape == null || !shape.getBounds().equals(getBounds())) {
                shape = new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
            }
            return shape.contains(x, y);
        }
}