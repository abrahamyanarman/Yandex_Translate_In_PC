package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class TranslateButton extends JFrame {
    private JFrame frame = new JFrame();
    private JPanel panel = new JPanel();
    private JButton button = new JButton();
    private static Point initialClick;

    public TranslateButton() throws HeadlessException {

        /*Configure Frame*/
        setTitle("");
        setContentPane(panel);
        setLocationRelativeTo(null);
        setSize(65,35);
        setResizable(false);

        /*Configure Panel*/

        panel.setLayout(new GridLayout());
        panel.setPreferredSize(new Dimension(65,35));
        panel.setBorder(BorderFactory.createEmptyBorder());
        panel.add(button);

        /*Configure Button*/

        button.setIcon(new ImageIcon(getClass().getClassLoader().getResource("icon.png")));
        button.setPreferredSize(new Dimension(65,35));
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setMargin(new Insets(0,0,0,0));
        button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    Translate.setKey("trnsl.1.1.20181202T135724Z.e8e12c6fcbaddce8.cd3abb64b03eb067adb5b6e539b3265e6486a9d5");

                    // Create a Clipboard object using getSystemClipboard() method
                    Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();

                    // Get data stored in the clipboard that is in the form of a string (text)
                    try {
                        String text = String.valueOf(c.getData(DataFlavor.stringFlavor));
                        String translatedText = Translate.execute(text, "en", "ru");
                        JOptionPane.showMessageDialog(null, translatedText);
                    } catch (UnsupportedFlavorException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    initialClick = e.getPoint();
                    System.out.println(initialClick);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    // get location of Window
                    int thisX = getLocation().x;
                    int thisY = getLocation().y;

                    // Determine how much the mouse moved since the initial click
                    int xMoved = e.getX() - initialClick.x;
                    int yMoved = e.getY() - initialClick.y;

                    // Move window to this position
                    int X = thisX + xMoved;
                    int Y = thisY + yMoved;
                    setLocation(X, Y);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setUndecorated(true);
        setAlwaysOnTop(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);

    }
}
