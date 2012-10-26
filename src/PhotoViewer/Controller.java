package PhotoViewer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Controller implements IController {

    /**
     * This controller's album
     */
    IAlbumModel albumModel;

    /**
     * This controller's view
     */
    IPhotoViewerView view;

    /**
     * Controller state. Album open or closed
     */
    private enum ControllerState {ALBUM_OPENED, ALBUM_CLOSED;}

    /**
     * The controller's initial state is album closed
     */
    private ControllerState state = ControllerState.ALBUM_CLOSED;

    /**
     * Creates the controller with the given view and model. Adds this controller as a listener to the view
     * @param model the model for this controller
     * @param view The view for this controller
     */
    public Controller(IAlbumModel model, IPhotoViewerView view) {
        this.view = view;
        this.albumModel = model;
        this.view.AddListener(this);
    }

    @Override
    /**
     * Gets a file name for the new album, creates the file, and opens the album. Tells view to display an error message
     * if the album couldn't be created.
     */
    public void OnNewAlbum() {
        JFileChooser fc = new JFileChooser("C:\\");
        fc.setFileFilter(new FileNameExtensionFilter("Album File (*.alb)", "alb"));
        fc.showSaveDialog(null);
        File file =  fc.getSelectedFile();
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
            try {
                String name = file.getPath();
                if (!name.endsWith(".alb")) {
                    file = new File(name + ".alb");
                }
                file.createNewFile();
                this.OnOpenAlbum(file);
            } catch (IOException e) {
                view.showErrorMessage("Error creating album: " + e.getMessage());
            }
        }
    }

    @Override
    /**
     * Given the album file, attempts to create an albumModel and add this controller as the listener. Updates the state
     * to indicate an album is open. Also calls tellViewToShowAlbumInfo so the view shows the album information.
     */
    public void OnOpenAlbum(File file) {
        if (file == null)
            throw new NullPointerException("File cannot be null");

        this.albumModel = new Album(file);
        this.albumModel.AddListener(this);
        this.state = ControllerState.ALBUM_OPENED;
        this.tellViewToShowAlbumInfo();
    }

    @Override
    /**
     * Handles a request to add a photo. Adds the photo to the album and tells the view to also display the added photo
     */
    public void OnAddPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(null);
        File photo = fileChooser.getSelectedFile();
        albumModel.AddPhoto(photo);
        this.tellViewToAddPhoto(photo);
    }

    @Override
    /**
     * If controller has an album open (state), tells the AlbumModel to toggle its slideshow. otherwise tells the view
     * to show an error message.
     */
    public boolean ToggleSlideshow() {
        boolean toggled = false;
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.albumModel.ToggleSlideshow();
            toggled = true;
        } else {
            view.showErrorMessage("Cannot toggle slideshow because no album is open.");
        }

        return toggled;
    }

    @Override
    /**
     * Tells the view to display the specified image file.
     */
    public void ShowImage(File file) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.showImage(file);
        }
    }

    /**
     * Tells the view to display all the album information
     */
    private void tellViewToShowAlbumInfo() {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.ClearEverything();
            this.view.DisplayAlbumName(this.albumModel.GetName());
            for (File picture : this.albumModel.getPictures()) {
                this.view.AddPhoto(picture);
            }
        }
    }

    /**
     * Tells the view that a new photo was added to the album and requests that it be displayed
     * @param photo The photo that was added to the album
     */
    private void tellViewToAddPhoto(File photo) {
        this.view.AddPhoto(photo);
    }


}
