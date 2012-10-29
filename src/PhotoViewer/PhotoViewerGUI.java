/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PhotoViewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * @author moorea
 */
public class PhotoViewerGUI extends javax.swing.JFrame implements IPhotoViewerView {
    /**
     * THe controller for this view
     */
    private IController controller;
    private DropTarget dt;

    /**
     * The enum that indicates slideshow state
     */
    private static enum slideshowState {SLIDESHOW_RUNNING, SLIDESHOW_STOPPED}

    /**
     * The slideshow state for the view
     */
    private slideshowState state = slideshowState.SLIDESHOW_STOPPED;

    /**
     * The list of files that are in the album
     */
    private ArrayList<File> listFiles = new ArrayList<File>();

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
                addButtonActionPerformed();
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/deletePic.png"))); // NOI18N
        deleteButton.setToolTipText("Remove selected file from album");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed();
            }
        });

        fileInfoLabel.setText("Picture filename and resolution");

        previousButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/leftArrow.png"))); // NOI18N
        previousButton.setToolTipText("View previous picture");
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                previousButtonActionPerformed();
            }
        });

        nextButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/rightArrow.png"))); // NOI18N
        nextButton.setToolTipText("View next picture");
        nextButton.setOpaque(false);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed();
            }
        });

        startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/start.png"))); // NOI18N
        startAndStopToggleButton.setToolTipText("Start/Stop Slideshow");
        startAndStopToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startAndStopToggleButtonActionPerformed();
            }
        });

        SpinnerModel model = new SpinnerNumberModel(2,1,60,2);
        delaySpinner.setModel(model);
        delaySpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                delaySpinnerStateChanged();
            }
        });


        delayLabel.setText("Slide Delay ");

        secondsLabel.setText("seconds");

        photoScrollPane.setText("");
        jScrollPane1.setViewportView(photoScrollPane);
        jScrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        fileList.setModel(new DefaultListModel());
        fileList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                fileListValueChanged();
            }
        });
        dt = new DropTarget(fileList, new ListDropTargetListener());
        jScrollPane2.setViewportView(fileList);

        newMenu.setText("File");


        openMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        openMenuItem.setText("Open Album");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed();
            }
        });
        newMenu.add(openMenuItem);

        newMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newMenuItem.setText("New Album");
        newMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newMenuItemActionPerformed();
            }
        });
        newMenu.add(newMenuItem);

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setText("Save Album");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed();
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
                startSlideMenuItemActionPerformed();
            }
        });
        startSlideMenu.add(startSlideMenuItem);

        stopSlideMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK));
        stopSlideMenuItem.setText("Stop Slideshow");
        stopSlideMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSlideMenuItemActionPerformed();
            }
        });
        startSlideMenu.add(stopSlideMenuItem);

        jMenu5.setText("Options");

        sequentialRadio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.SHIFT_MASK));
        sequentialRadio.setSelected(true);
        sequentialRadio.setText("Sequential");
        sequentialRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sequentialRadioActionPerformed();
            }
        });
        jMenu5.add(sequentialRadio);

        randomRadio.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.SHIFT_MASK));
        randomRadio.setText("Random");
        randomRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomRadioActionPerformed();
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

    /**
     * When the JList selection changes, show the image that is selected
     */
    private void fileListValueChanged() {
        if (this.fileList.getSelectedIndex() >= 0) {
            File file = listFiles.get(this.fileList.getSelectedIndex());
            BufferedImage myPicture = null;
            try {
                myPicture = ImageIO.read(file);
                this.setPicture(myPicture);
                this.SetPictureLabel(file.getName() + " Width: " + myPicture.getWidth() + " Height: " + myPicture.getHeight());
            } catch (Exception e) {
                this.setPicture(null);
                this.SetPictureLabel("Could not read file.");
            }
            /*BufferedImage myPicture = null;
            try {
                File file = listFiles.get(this.fileList.getSelectedIndex());
                myPicture = ImageIO.read(file);
                Image scaled = myPicture.getScaledInstance(jScrollPane1.getWidth(), jScrollPane1.getHeight(), Image.SCALE_SMOOTH);
                photoScrollPane.setIcon(new ImageIcon(scaled));
                //photoScrollPane.setIcon(new ImageIcon(ImageIO.read(listFiles.get(this.fileList.getSelectedIndex()))));
                SetPictureLabel(file.getName() + " Width: " + myPicture.getWidth() + " Height: " + myPicture.getHeight());
            } catch (Exception e) {
                photoScrollPane.setIcon(null);
                photoScrollPane.setText("Could not read file");
            } */
        }
    }

    /**
     * Stops any running slideshow and asks the controller to open an album
     */
    private void openMenuItemActionPerformed() {//GEN-FIRST:event_openMenuItemActionPerformed
        stopSlideMenuItemActionPerformed();
        this.controller.OnOpenAlbum();
        controller.OnTimeChange(Integer.parseInt((delaySpinner.getValue().toString().trim())));
    }//GEN-LAST:event_openMenuItemActionPerformed

    /**
     * Asks the controller to save the album
     */
    private void saveMenuItemActionPerformed() {//GEN-FIRST:event_saveMenuItemActionPerformed
        this.controller.OnSaveAlbum();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    /**
     * If a slideshow is not running, asks the controller to start it
     */
    private void startSlideMenuItemActionPerformed() {//GEN-FIRST:event_startSlideMenuItemActionPerformed
        if (state == slideshowState.SLIDESHOW_STOPPED) {
            if (this.controller.ToggleSlideshow()) {
                state = slideshowState.SLIDESHOW_RUNNING;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/pause.png")));
            }

        }
    }//GEN-LAST:event_startSlideMenuItemActionPerformed

    /**
     * If a slideshow is running, asks the controller to stop it
     */
    private void stopSlideMenuItemActionPerformed() {//GEN-FIRST:event_stopSlideMenuItemActionPerformed
        if (state == slideshowState.SLIDESHOW_RUNNING) {
            if (this.controller.ToggleSlideshow()) {
                state = slideshowState.SLIDESHOW_STOPPED;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/start.png")));
            }
        }
    }//GEN-LAST:event_stopSlideMenuItemActionPerformed

    /**
     * Changes the order to sequential in the controller
     */
    private void sequentialRadioActionPerformed() {//GEN-FIRST:event_sequentialRadioActionPerformed
        randomRadio.setSelected(false);
        controller.OnOrderSelection(Album.SlideshowOrder.SEQUENTIAL);

    }//GEN-LAST:event_sequentialRadioActionPerformed

    /**
     * Tells the controller to change to random order for slideshows
     */
    private void randomRadioActionPerformed() {//GEN-FIRST:event_randomRadioActionPerformed
        sequentialRadio.setSelected(false);
        controller.OnOrderSelection(Album.SlideshowOrder.RANDOM);
    }//GEN-LAST:event_randomRadioActionPerformed

    /**
     * Asks the controller to add a photo to the album
     */
    private void addButtonActionPerformed() {//GEN-FIRST:event_addButtonActionPerformed
        this.controller.OnAddPhoto();
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * Asks the controller to delete the selected item. If no item is selected, shows an error.
     */
    private void deleteButtonActionPerformed() {//GEN-FIRST:event_deleteButtonActionPerformed
        if (this.fileList.getSelectedIndex() >= 0) {
            this.controller.OnDeletePhoto(listFiles.get(fileList.getSelectedIndex()));
        } else {
            this.ShowErrorMessage("No file selected to delete");
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    /**
     * gets the previous picture
     */
    private void previousButtonActionPerformed() {//GEN-FIRST:event_previousButtonActionPerformed
        getPreviousPicture();
    }//GEN-LAST:event_previousButtonActionPerformed

    /**
     * Toggles slideshow state
     */
    private void startAndStopToggleButtonActionPerformed() {//GEN-FIRST:event_startAndStopToggleButtonActionPerformed
        if (this.controller.ToggleSlideshow()) {
            if (state == slideshowState.SLIDESHOW_STOPPED) {
                state = slideshowState.SLIDESHOW_RUNNING;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/pause.png")));
            } else {
                state = slideshowState.SLIDESHOW_STOPPED;
                startAndStopToggleButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photoviewer/start.png")));
            }
        }
    }//GEN-LAST:event_startAndStopToggleButtonActionPerformed

    /**
     * Gets the next picture in the list
     */
    private void nextButtonActionPerformed() {//GEN-FIRST:event_nextButtonActionPerformed
        getNextPicture();

    }//GEN-LAST:event_nextButtonActionPerformed

    /**
     * Stops any running slideshow. Asks controller to create an album.
     */
    private void newMenuItemActionPerformed() {//GEN-FIRST:event_newMenuItemActionPerformed
        stopSlideMenuItemActionPerformed();
        controller.OnNewAlbum();
        controller.OnTimeChange(Integer.parseInt((delaySpinner.getValue().toString().trim())));
    }//GEN-LAST:event_newMenuItemActionPerformed

    /**
     * Tells the controller that the time interval between images has changed
     */
    private void delaySpinnerStateChanged() {//GEN-FIRST:event_delaySpinnerStateChanged
        controller.OnTimeChange(Integer.parseInt((delaySpinner.getValue().toString().trim())));
    }//GEN-LAST:event_delaySpinnerStateChanged

    /**
     * Listener used for the drop target
     */
    private class ListDropTargetListener implements DropTargetListener {

        /**
         * Called while a drag operation is ongoing, when the mouse pointer enters
         * the operable part of the drop site for the <code>DropTarget</code>
         * registered with this listener.
         *
         * @param dtde the <code>DropTargetDragEvent</code>
         */
        @Override
        public void dragEnter(DropTargetDragEvent dtde) {}

        /**
         * Called when a drag operation is ongoing, while the mouse pointer is still
         * over the operable part of the drop site for the <code>DropTarget</code>
         * registered with this listener.
         *
         * @param dtde the <code>DropTargetDragEvent</code>
         */
        @Override
        public void dragOver(DropTargetDragEvent dtde) {}

        /**
         * Called if the user has modified
         * the current drop gesture.
         * <p/>
         *
         * @param dtde the <code>DropTargetDragEvent</code>
         */
        @Override
        public void dropActionChanged(DropTargetDragEvent dtde) {}

        /**
         * Called while a drag operation is ongoing, when the mouse pointer has
         * exited the operable part of the drop site for the
         * <code>DropTarget</code> registered with this listener.
         *
         * @param dte the <code>DropTargetEvent</code>
         */
        @Override
        public void dragExit(DropTargetEvent dte) {}

        /**
         * Called when the drag operation has terminated with a drop on
         * the operable part of the drop site for the <code>DropTarget</code>
         * registered with this listener.
         * <p/>
         * This method is responsible for undertaking
         * the transfer of the data associated with the
         * gesture. The <code>DropTargetDropEvent</code>
         * provides a means to obtain a <code>Transferable</code>
         * object that represents the data object(s) to
         * be transfered.<P>
         * From this method, the <code>DropTargetListener</code>
         * shall accept or reject the drop via the
         * acceptDrop(int dropAction) or rejectDrop() methods of the
         * <code>DropTargetDropEvent</code> parameter.
         * <p/>
         * Subsequent to acceptDrop(), but not before,
         * <code>DropTargetDropEvent</code>'s getTransferable()
         * method may be invoked, and data transfer may be
         * performed via the returned <code>Transferable</code>'s
         * getTransferData() method.
         * <p/>
         * At the completion of a drop, an implementation
         * of this method is required to signal the success/failure
         * of the drop by passing an appropriate
         * <code>boolean</code> to the <code>DropTargetDropEvent</code>'s
         * dropComplete(boolean success) method.
         * <p/>
         * Note: The data transfer should be completed before the call  to the
         * <code>DropTargetDropEvent</code>'s dropComplete(boolean success) method.
         * After that, a call to the getTransferData() method of the
         * <code>Transferable</code> returned by
         * <code>DropTargetDropEvent.getTransferable()</code> is guaranteed to
         * succeed only if the data transfer is local; that is, only if
         * <code>DropTargetDropEvent.isLocalTransfer()</code> returns
         * <code>true</code>. Otherwise, the behavior of the call is
         * implementation-dependent.
         * <p/>
         *
         * @param dtde the <code>DropTargetDropEvent</code>
         */
        @Override
        public void drop(DropTargetDropEvent dtde) {
            onDropFile(dtde);
        }
    }

    public void onDropFile(DropTargetDropEvent dtde) {
        Transferable tr = dtde.getTransferable();
        DataFlavor[] flavors = tr.getTransferDataFlavors();
        for (DataFlavor flavor : flavors) {
            if (flavor.isFlavorJavaFileListType()) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);
                try {
                    this.controller.OnAddPhoto((java.util.List<File>) tr.getTransferData(flavor));
                } catch (Exception ignored) {}
            }
        }
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

    /**
     * Adds a controller as a listener
     */
    @Override
    public void AddListener(IController controller) {
        this.controller = controller;
    }


    /**
     * Displays the album name in the title
     */
    @Override
    public void DisplayAlbumName(String name) {
        this.setTitle(name + "  -  Photo Viewer");
    }

    @Override
    /**
     * Adds photo to the view
     */
    public void AddPhoto(File picture) {
        this.listFiles.add(picture);
        DefaultListModel model = (DefaultListModel) this.fileList.getModel();
        model.addElement(picture.getName());
        this.fileList.setModel(model);
    }

    @Override
    /**
     * Shows an error message with the given string
     */
    public void ShowErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    /**
     * Clears all information being displayed on the GUI
     */
    public void ClearEverything() {
        this.listFiles.clear();
        this.fileList.setModel(new DefaultListModel());
        this.setTitle("Photo Viewer");
        this.photoScrollPane.setIcon(null);
    }

    @Override
    /**
     * findds the given file in the list and changes to the index that it is located
     */
    public void ShowImage(File file) {
        int index = 0;
        boolean found = false;
        while (file != null && !found && index < this.listFiles.size()) {
            if (file.equals(this.listFiles.get(index))) {
                changeListIndex(index);
                found = true;
            }
            index++;
        }
    }

    /**
     * Gets the previous picutre
     * @return The File that is the previous picture in the list
     */
    private File getNextPicture() {
        File retVal = null;

        if (listFiles.size() > 0) {
            if (fileList.getSelectedIndex() == listFiles.size() - 1) {
                retVal = listFiles.get(0);
                fileList.setSelectedIndex(0);
            } else {
                retVal = listFiles.get(fileList.getSelectedIndex() + 1);
                fileList.setSelectedIndex(fileList.getSelectedIndex() + 1);
            }
        }

        return retVal;
    }

    /**
     * gets the next picture
     * @return The File that is the next picture in the list
     */
    private File getPreviousPicture() {
        File retVal = null;

        if (listFiles.size() > 0) {
            if (fileList.getSelectedIndex() <= 0) {
                retVal = listFiles.get(listFiles.size() - 1);
                fileList.setSelectedIndex(listFiles.size() - 1);
            } else {
                retVal = listFiles.get(fileList.getSelectedIndex() - 1);
                fileList.setSelectedIndex(fileList.getSelectedIndex() - 1);
            }
        }

        return retVal;
    }

    @Override
    /**
     * Disables all buttons and menu items
     */
    public void DisableAllFunctions() {
        this.addButton.setEnabled(false);
        this.deleteButton.setEnabled(false);
        this.nextButton.setEnabled(false);
        this.previousButton.setEnabled(false);
        this.startAndStopToggleButton.setEnabled(false);
        this.sequentialRadio.setEnabled(false);
        this.randomRadio.setEnabled(false);
        this.startSlideMenuItem.setEnabled(false);
        this.stopSlideMenuItem.setEnabled(false);
        this.delaySpinner.setEnabled(false);
        this.saveMenuItem.setEnabled(false);
    }

    @Override
    /**
     * enables all buttons and menu items
     */
    public void EnableAllFunctions() {
        this.addButton.setEnabled(true);
        this.deleteButton.setEnabled(true);
        this.nextButton.setEnabled(true);
        this.previousButton.setEnabled(true);
        this.startAndStopToggleButton.setEnabled(true);
        this.sequentialRadio.setEnabled(true);
        this.randomRadio.setEnabled(true);
        this.startSlideMenuItem.setEnabled(true);
        this.stopSlideMenuItem.setEnabled(true);
        this.delaySpinner.setEnabled(true);
        this.saveMenuItem.setEnabled(true);
    }

    @Override
    /**
     * removes a photo from the view's information
     */
    public void RemovePhoto(File photo) {
        int index = this.listFiles.indexOf(photo);
        this.listFiles.remove(index);
        DefaultListModel model = (DefaultListModel) fileList.getModel();
        model.remove(index);
        fileList.setModel(model);
        if(listFiles.size() > 0) {
            fileList.setSelectedIndex(0);
        } else {
            this.setPicture(null);
            this.SetPictureLabel("");
        }
    }

    /**
     * Sets picture that is displayed
     */
    private void setPicture(BufferedImage image) {
        if(image != null) {
            int newHeight;
            int newWidth;
            if(image.getWidth() > image.getHeight()) {
                newWidth = jScrollPane1.getWidth();
                newHeight = image.getHeight() * newWidth / image.getWidth();

                if(newHeight > jScrollPane1.getHeight()) {
                    int tmpWidth = newWidth;
                    int tmpHeight = newHeight;
                    newHeight = jScrollPane1.getHeight();
                    newWidth = tmpWidth * newHeight / tmpHeight;
                }
            } else {
                newHeight = jScrollPane1.getHeight();
                newWidth = image.getWidth() * newHeight / image.getHeight();

                if(newWidth > jScrollPane1.getWidth()) {
                    int tmpWidth = newWidth;
                    int tmpHeight = newHeight;
                    newWidth = jScrollPane1.getWidth();
                    newHeight = tmpHeight * newWidth / tmpWidth;
                }
            }
            Image scaled = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            photoScrollPane.setIcon(new ImageIcon(scaled));
        } else {
            photoScrollPane.setIcon(null);
        }
    }

    /**
     * Changes the JList index
     * @param index index to change to
     */
    private void changeListIndex(int index) {
        if (index >= 0 && index < fileList.getVisibleRowCount()) {
            this.fileList.setSelectedIndex(index);
        }
    }

    /**
     * Sets the picture label information
     */
    private void SetPictureLabel(String label) {
        fileInfoLabel.setText(label);
    }

}
