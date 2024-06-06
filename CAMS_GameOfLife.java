/*
Dawson Fedor Janura
Game of Life frame
 */
package cams;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author darek1849
 */
public class CAMS_GameOfLife extends javax.swing.JFrame {

    // reference main cuz this is second window
    CAMS_Mainframe main;
    private JPanel plane;

    public Cell[][] map;

    public CAMS_GameOfLife(CAMS_Mainframe m) throws IOException {
        initComponents();
        main = m;
        Cursor cursor;
        cursor = Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(getClass().getResource("/cams/pointer_scifi_b.png")).getImage(), new Point(), "Custom cursor");
        setCursor(cursor);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() throws IOException {

        map = new Cell[100][100];

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        plane = new GameOfLifePlane(map.length, map[0].length, map, this);
        //need to add toolbar to game of life as well, will implement later today
        //does not work properly
        JToolBar toolbar = new JToolBar();

        

        golHomeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        golHomeBtn.setText("HOME");
        

        playAndPauseBtn = new javax.swing.JButton();

        BufferedImage playAndPauseIcon = ImageIO.read(new File("src/cams/PlayAndPause.png"));
        Image playAndPauseImage = playAndPauseIcon.getScaledInstance(50, 50, Image.SCALE_DEFAULT);

        playAndPauseBtn.setIcon(new ImageIcon(playAndPauseImage));
        playAndPauseBtn.setText("PLAY/PAUSE");
        playAndPauseBtn.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        playAndPauseBtn.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        playAndPauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ((Plane) plane).playOrPause(evt);
            }
        });
        

        add(plane, BorderLayout.CENTER);
        
        golHomeBtn.addActionListener((java.awt.event.ActionEvent evt) -> {
            golHomeBtnActionPerformed(evt);
        });
        
        

        toolbar.add(golHomeBtn);
        toolbar.addSeparator(new Dimension(50, 50)); //Creates a seperation line between the button and the toolbar

        toolbar.add(playAndPauseBtn);
        toolbar.setRollover(true);
        toolbar.setFocusable(rootPaneCheckingEnabled);

        add(toolbar, BorderLayout.PAGE_START);

        pack();

        setSize(1000, 800);
        setTitle("GAME OF LIFE");

        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
// send back to first window

    private void golHomeBtnActionPerformed(java.awt.event.ActionEvent evt) {
        main.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_golHomeBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton golHomeBtn;
    private javax.swing.JButton playAndPauseBtn;

    // End of variables declaration//GEN-END:variables
}


