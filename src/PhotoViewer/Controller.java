package PhotoViewer;

import PhotoViewer.Album.SlideshowOrder;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
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
    private enum ControllerState {
        ALBUM_OPENED, ALBUM_CLOSED
    }

    /**
     * The controller's initial state is album closed
     */
    private ControllerState state = ControllerState.ALBUM_CLOSED;

    /**
     * Creates the controller with the given view and model. Adds this controller as a listener to the view
     *
     * @param model the model for this controller
     * @param view  The view for this controller
     */
    public Controller(IAlbumModel model, IPhotoViewerView view) {
        this.view = view;
        this.albumModel = model;
        this.view.AddListener(this);
        this.view.DisableAllFunctions();
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
        File file = fc.getSelectedFile();
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

                this.albumModel = new Album(file);
                this.albumModel.AddListener(this);
                this.state = ControllerState.ALBUM_OPENED;
                this.tellViewToShowAlbumInfo();
                this.view.EnableAllFunctions();
            } catch (IOException e) {
                view.ShowErrorMessage("Error creating album: " + e.getMessage());
            }
        }
    }

    @Override
    /**
     * Given the album file, attempts to create an albumModel and add this controller as the listener. Updates the state
     * to indicate an album is open. Also calls tellViewToShowAlbumInfo so the view shows the album information.
     */
    public void OnOpenAlbum() {
        JFileChooser fc = new JFileChooser("C:\\");
        fc.setFileFilter(new FileNameExtensionFilter("Album File (*.alb)", "alb"));
        fc.showOpenDialog(null);
        File file = fc.getSelectedFile();
        if (file != null) {
            this.albumModel = new Album(file);
            this.albumModel.AddListener(this);
            this.state = ControllerState.ALBUM_OPENED;
            this.albumModel.Open();
            this.tellViewToShowAlbumInfo();
            this.view.EnableAllFunctions();
        }

    }

    @Override
    /**
     * Tells the model to save the album
     */
    public void OnSaveAlbum() {
        this.albumModel.Save();
    }

    @Override
    /**
     * Handles a request to add a photo. Adds the photo to the album and tells the view to also display the added photo
     */
    public void OnAddPhoto() {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Images", "jpg", "jpeg");
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File photo = fileChooser.getSelectedFile();
            try {
                if (albumModel.AddPhoto(photo)) {
                    this.tellViewToAddPhoto(photo);
                } else {
                    this.view.ShowErrorMessage("Unable to add photo. Photo is already in the album.");
                }
            } catch (IOException e) {
                this.view.ShowErrorMessage("Unable to add image:\n" + e.getMessage());
            }
        }
    }

    @Override
    /**
     * Deletes the photo from the album and calls the tellViewToRemovePhoto method
     */
    public void OnDeletePhoto(File photo) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.albumModel.RemovePhoto(photo);
            this.tellViewToRemovePhoto(photo);
        }
    }

    /**
     * Tells the view to remove the given photo
     * @param photo The photo File to remove from the view
     */
    private void tellViewToRemovePhoto(File photo) {
        this.view.RemovePhoto(photo);
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
            view.ShowErrorMessage("Cannot toggle slideshow because no album is open.");
        }

        return toggled;
    }

    @Override
    /**
     * Tells the view to display the specified image file.
     */
    public void ShowImage(File file) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            //this.view.ShowImage(file);
            BufferedImage myPicture = null;
            try {
                myPicture = ImageIO.read(file);
                this.view.SetPicture(myPicture);
                this.view.SetPictureLabel(file.getName() + " Width: " + myPicture.getWidth() + " Height: " + myPicture.getHeight());
            } catch (Exception e) {
                this.view.SetPicture(null);
                this.view.SetPictureLabel("Could not read file.");
            }
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
                this.tellViewToAddPhoto(picture);
            }
        }
    }

    /**
     * Tells the view that a new photo was added to the album and requests that it be displayed
     *
     * @param photo The photo that was added to the album
     */
    private void tellViewToAddPhoto(File photo) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            this.view.AddPhoto(photo);
        }
    }

    @Override
    /**
     * If an album is open, tells the album model to set the time between images
     */
    public void OnTimeChange(int newTime) {
        if (this.state == ControllerState.ALBUM_OPENED) {
            albumModel.SetTimeBetweenImages(newTime);
        }
    }

    @Override
    /**
     * tells the alum model to change slideshow order
     */
    public void OnOrderSelection(SlideshowOrder o) {
        albumModel.SetSlideshowOrder(o);

    }


}
