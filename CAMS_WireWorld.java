/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package cams;

import javax.swing.JPanel;

/**
 *
 * @author darek1849
 */
public class CAMS_WireWorld extends javax.swing.JFrame {

    CAMS_Mainframe main;
    
    private JPanel plane;
    
    public Cell[][] map;
    
    public CAMS_WireWorld(CAMS_Mainframe m) {
        initComponents();
        main = m;
        plane = new Plane(map.length, map[0].length, map, this);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wwHomeBtn.setText("HOME");
        wwHomeBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wwHomeBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wwHomeBtn)
                .addContainerGap(322, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(wwHomeBtn)
                .addContainerGap(271, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wwHomeBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wwHomeBtnActionPerformed

        main.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_wwHomeBtnActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton wwHomeBtn;
    // End of variables declaration//GEN-END:variables
}
