package Tests;

import PhotoViewer.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * User: scottat
 * Date: 11/3/12
 * Time: 1:49 PM
 */
public class ControllerTest {

    private final IController controller;
    private final IPhotoViewerView view;
    private final IAlbumModel model;


    public ControllerTest() throws IOException {
        this.view = new CustomView();
        File f = new File("test.alb");
        if (!f.exists()) {
            f.createNewFile();
        }

        this.model = new Album(f);
        this.controller = new Controller(this.model, this.view);
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testOnNewAlbum() throws Exception {
//        controller.OnNewAlbum();
    }

    @Test
    public void testOnOpenAlbum() throws Exception {
//                     controller.OnOpenAlbum();
    }

    @Test
    public void testOnSaveAlbum() throws Exception {
        controller.OnSaveAlbum();
    }

    @Test
    public void testOnAddPhoto() throws Exception {

    }

    @Test
    public void testOnDeletePhoto() throws Exception {

    }

    @Test
    public void testToggleSlideshow() throws Exception {

    }

    @Test
    public void testStopSlideshow() throws Exception {

    }

    @Test
    public void testStartSlideshow() throws Exception {

    }

    @Test
    public void testShowImage() throws Exception {

    }

    @Test
    public void testOnTimeChange() throws Exception {

    }

    @Test
    public void testOnOrderSelection() throws Exception {

    }


    /**
     * Allows us to test the controller methods by creating a "fake" view that implements the interface and sets
     * attributes to indicate what methods were called.
     */
    class CustomView implements IPhotoViewerView {

        IController addListenerCalledWith = null;

        String displayAlbumNameCalledWith = null;

        File addPhotoCalledWith = null;

        String showErrorMessageCalledWith = null;

        boolean clearEverythingCalled = false;

        File showImageCalledawith = null;

        boolean setEnabledCalled = false;
        boolean setEndabledCalledWith = false;

        File removePhotoCalledWith = null;

        CustomView() {
        }

        @Override
        public void AddListener(IController controller) {
            this.addListenerCalledWith = controller;
        }

        @Override
        public void DisplayAlbumName(String name) {
            this.displayAlbumNameCalledWith = name;
        }

        @Override
        public void AddPhoto(File picture) {
            this.addPhotoCalledWith = picture;
        }

        @Override
        public void ShowErrorMessage(String message) {
            this.showErrorMessageCalledWith = message;
        }

        @Override
        public void ClearEverything() {
            this.clearEverythingCalled = true;
        }

        @Override
        public void ShowImage(File file) {
            this.showImageCalledawith = file;
        }

        @Override
        public void SetEnabled(boolean enabled) {
            this.setEnabledCalled = true;
            this.setEndabledCalledWith = enabled;
        }

        @Override
        public void RemovePhoto(File photo) {
            this.removePhotoCalledWith = photo;
        }
    }

}
