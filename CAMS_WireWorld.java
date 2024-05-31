/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cams;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

/**
 *
 * @author darek1849
 */
public class CAMS_WireWorld extends javax.swing.JFrame {

    CAMS_Mainframe main; //this does not work anymore 

    //Create a seperate panel for the buttons, only make the cellular automata thing editable tho
    private JPanel plane;

    public Cell[][] map;

    public CAMS_WireWorld(CAMS_Mainframe m) {
        initComponents();
        main = m;

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        wwHomeBtn = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        wwHomeBtn.setText("HOME");
        wwHomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wwHomeBtnActionPerformed(evt);
            }
        });

        JToolBar toolbar = new JToolBar();
        
        map = new Cell[100][100];

        plane = new WireworldPlane(map.length, map[0].length, map, this);

        plane.setVisible(true);
        add(plane, BorderLayout.CENTER);
        
        //there is a bug with the toolbar itself, the event listener for the key input won't work when the WireWorld plane originally appears with the toolbar, however, once you 
        //seperate it, the evenet listener works again as seperate entities and then you are able to put down a source wire once again.
        toolbar.setRollover(true);
        toolbar.add(wwHomeBtn);
        
        toolbar.addSeparator(new Dimension(50, 50)); //Creates a seperation line between the button and the toolbar
        
        
        add(toolbar, BorderLayout.PAGE_START);

        pack();

        setSize(1000, 800);
        setTitle("Application");
        

        setLocationRelativeTo(null);

    }// </editor-fold>//GEN-END:initComponents

    private void wwHomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wwHomeBtnActionPerformed

        main.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_wwHomeBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton wwHomeBtn;
    // End of variables declaration//GEN-END:variables
}
