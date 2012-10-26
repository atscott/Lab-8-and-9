/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PhotoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author moorea
 */
public class PhotoViewerGUI extends javax.swing.JFrame implements IPhotoViewerView {
    IController controller;

    private static enum slideshowState {SLIDESHOW_RUNNING, SLIDESHOW_STOPPED;}

    private slideshowState state = slideshowState.SLIDESHOW_STOPPED;

    /**
     * Creates new form PhotoViewerGUI
     */
    public PhotoViewerGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        fileInfoLabel = new javax.swing.JLabel();
        previousButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();
        startAndStopToggleButton = new javax.swing.JToggleButton();
        delaySpinner = new javax.swing.JSpinner();
        delayLabel = new javax.swing.JLabel();
        secondsLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        photoScrollPane = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        fileList = new javax.swing.JList();
        jMenuBar2 = new javax.swing.JMenuBar();
        newMenu = new javax.swing.JMenu();
        openMenuItem = new javax.swing.JMenuItem();
        newMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        startSlideMenu = new javax.swing.JMenu();
        startSlideMenuItem = new javax.swing.JMenuItem();
        stopSlideMenuItem = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        sequentialRadio = new javax.swing.JRadioButtonMenuItem();
        randomRadio = new javax.swing.JRadioButtonMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("\"Album Name\"  -  Photo Viewer");

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/addPic.png"))); // NOI18N
        addButton.setToolTipText("Add file to album");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/deletePic.png"))); // NOI18N
        deleteButton.setToolTipText("Remove selected file from album");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        fileInfoLabel.setText("Picture filename and resolution");

        previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/leftArrow.png"))); // NOI18N
        previousButton.setToolTipText("View previous picture");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed(evt);
            }
        });

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/rightArrow.png"))); // NOI18N
        nextButton.setToolTipText("View next picture");
        nextButton.setOpaque(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/start.png"))); // NOI18N
        startAndStopToggleButton.setToolTipText("Start/Stop Slideshow");
        startAndStopToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAndStopToggleButtonActionPerformed(evt);
            }
        });

        delaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                delaySpinnerStateChanged(evt);
            }
        });

        delayLabel.setText("Slide Delay ");

        secondsLabel.setText("seconds");

        photoScrollPane.setText("");
        jScrollPane1.setViewportView(photoScrollPane);

        fileList.setModel(new DefaultListModel());
        fileList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fileListValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(fileList);

        newMenu.setText("File");
        newMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuActionPerformed(evt);
            }
        });

        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open Album");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(openMenuItem);

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setText("New Album");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(newMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save Album");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        newMenu.add(saveMenuItem);

        jMenuBar2.add(newMenu);

        startSlideMenu.setMnemonic('S');
        startSlideMenu.setText("Slideshow");

        startSlideMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.SHIFT_MASK));
        startSlideMenuItem.setText("Start Slideshow");
        //startSlideMenuItem.setMnemonic(KeyEvent.VK_S);
        startSlideMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startSlideMenuItemActionPerformed(evt);
            }
        });
        startSlideMenu.add(startSlideMenuItem);

        stopSlideMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        stopSlideMenuItem.setText("Stop Slideshow");
        stopSlideMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSlideMenuItemActionPerformed(evt);
            }
        });
        startSlideMenu.add(stopSlideMenuItem);

        jMenu5.setText("Options");

        sequentialRadio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK));
        sequentialRadio.setSelected(true);
        sequentialRadio.setText("Sequential");
        sequentialRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sequentialRadioActionPerformed(evt);
            }
        });
        jMenu5.add(sequentialRadio);

        randomRadio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
        randomRadio.setText("Random");
        randomRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomRadioActionPerformed(evt);
            }
        });
        jMenu5.add(randomRadio);

        startSlideMenu.add(jMenu5);

        jMenuBar2.add(startSlideMenu);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(8, 8, 8)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(2, 2, 2)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addComponent(delayLabel)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(secondsLabel))
                                                        .addComponent(fileInfoLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 326, Short.MAX_VALUE)
                                                .addComponent(previousButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(startAndStopToggleButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(nextButton))
                                        .addComponent(jScrollPane1))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(fileInfoLabel)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(delaySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(delayLabel)
                                                        .addComponent(secondsLabel)))
                                        .addComponent(previousButton)
                                        .addComponent(nextButton)
                                        .addComponent(startAndStopToggleButton))
                                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fileListValueChanged(ListSelectionEvent evt) {

        //To change body of created methods use File | Settings | File Templates.
        System.out.println("selection changed");
        if(this.fileList.getSelectedIndex() >= 0){
            BufferedImage myPicture = null;
            try {
                photoScrollPane.setIcon(new ImageIcon(ImageIO.read(this.controller.getImageAtIndex(this.fileList.getSelectedIndex()))));
            } catch (Exception e) {
                photoScrollPane.setIcon(null);
                photoScrollPane.setText("Could not read file");
            }
        }
    }

    private void newMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuActionPerformed
        // TODO add your handling code here:
        System.out.println("New Album");

    }//GEN-LAST:event_newMenuActionPerformed

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        // TODO add your handling code here:
        System.out.println("Open Album");
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        // TODO add your handling code here:
        System.out.println("Save Album");
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void startSlideMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startSlideMenuItemActionPerformed
        if (state == slideshowState.SLIDESHOW_STOPPED) {
            if (this.controller.ToggleSlideshow() == true) {
                state = slideshowState.SLIDESHOW_RUNNING;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/pause.png")));
            }

        }
    }//GEN-LAST:event_startSlideMenuItemActionPerformed

    private void stopSlideMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopSlideMenuItemActionPerformed
        if (state == slideshowState.SLIDESHOW_RUNNING) {
            if (this.controller.ToggleSlideshow() == true) {
                state = slideshowState.SLIDESHOW_STOPPED;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/start.png")));
            }
        }
    }//GEN-LAST:event_stopSlideMenuItemActionPerformed

    private void sequentialRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sequentialRadioActionPerformed
        // TODO add your handling code here:
        System.out.println("Sequential order");
    }//GEN-LAST:event_sequentialRadioActionPerformed

    private void randomRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomRadioActionPerformed
        // TODO add your handling code here:
        System.out.println("Random order");
        System.out.println("make sure to uncheck the other radio button ");
    }//GEN-LAST:event_randomRadioActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Add photo");
        this.controller.OnAddPhoto();
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Delete photo");
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void previousButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_previousButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Previous photo");
    }//GEN-LAST:event_previousButtonActionPerformed

    private void startAndStopToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startAndStopToggleButtonActionPerformed
        if (this.controller.ToggleSlideshow() == true) {
            if (state == slideshowState.SLIDESHOW_STOPPED) {
                state = slideshowState.SLIDESHOW_RUNNING;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/pause.png")));
            } else {
                state = slideshowState.SLIDESHOW_STOPPED;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/start.png")));
            }
        }
    }//GEN-LAST:event_startAndStopToggleButtonActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        // TODO add your handling code here:
        System.out.println("Next photo");
    }//GEN-LAST:event_nextButtonActionPerformed

    private void newMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newMenuItemActionPerformed
        // TODO add your handling code here:
        System.out.println("New album");
        controller.OnNewAlbum();
    }//GEN-LAST:event_newMenuItemActionPerformed

    private void delaySpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_delaySpinnerStateChanged
        // TODO add your handling code here:
        JSpinner js = (JSpinner) evt.getSource();
        System.out.println(js.getValue().toString());
    }//GEN-LAST:event_delaySpinnerStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhotoViewerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhotoViewerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhotoViewerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhotoViewerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhotoViewerGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JLabel delayLabel;
    private javax.swing.JSpinner delaySpinner;
    private javax.swing.JButton deleteButton;
    private javax.swing.JLabel fileInfoLabel;
    private javax.swing.JList fileList;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenu newMenu;
    private javax.swing.JMenuItem newMenuItem;
    private javax.swing.JButton nextButton;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JLabel photoScrollPane;
    private javax.swing.JButton previousButton;
    private javax.swing.JRadioButtonMenuItem randomRadio;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JLabel secondsLabel;
    private javax.swing.JRadioButtonMenuItem sequentialRadio;
    private javax.swing.JToggleButton startAndStopToggleButton;
    private javax.swing.JMenu startSlideMenu;
    private javax.swing.JMenuItem startSlideMenuItem;
    private javax.swing.JMenuItem stopSlideMenuItem;
    // End of variables declaration//GEN-END:variables

    @Override
    public void AddListener(IController controller) {
        this.controller = controller;
    }

    @Override
    public File RetrieveNewAlbumName() {
        JFileChooser fc = new JFileChooser("C:\\");
        fc.setFileFilter(new FileNameExtensionFilter("Album File (*.alb)", "alb"));
        fc.showSaveDialog(this);
        return fc.getSelectedFile();
    }

    @Override
    public void DisplayAlbumName(String name) {
        this.setTitle(name + "  -  Photo Viewer");
    }

    @Override
    public void AddPhoto(File picture) {

        DefaultListModel model = (DefaultListModel)this.fileList.getModel();
        model.addElement(picture.getName());
        this.fileList.setModel(model);
    }

    @Override
    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void ClearEverything() {
        this.fileList.setModel(new DefaultListModel());
        this.setTitle("Photo Viewer");
    }

    @Override
    public void showImage(File file) {
        int index = 0;
        boolean found = false;
        while (file != null && !found && index < this.controller.getAlbumSize()) {
            if (file.equals(this.controller.getImageAtIndex(index))) {
                changeListIndex(index);
                found = true;
            }
            index++;
        }
    }

    private void changeListIndex(int index) {
        if (index >= 0 && index < fileList.getVisibleRowCount()) {
            this.fileList.setSelectedIndex(index);
        }
    }

}
