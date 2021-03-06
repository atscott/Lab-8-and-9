package Tests;

import PhotoViewer.Album;
import PhotoViewer.Controller;
import PhotoViewer.IController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;

/**
 * User: scottat
 * Date: 11/3/12
 * Time: 1:49 PM
 */
public class ControllerTest {

    private static IController controller;
    private static CustomView view;
    private static File testAlbumFile = null;
    private long lastTimeShowImageWasCalled;

    /**
     * make sure the view, model and controller are fresh before every test
     */
    @Before
    public void setUpBeforeTest() throws IOException {
        testAlbumFile = new File("test" + Math.random() + ".alb");
        if (!testAlbumFile.exists()) {
            testAlbumFile.createNewFile();
        } else {
            testAlbumFile.delete();
            testAlbumFile.createNewFile();
        }
        Album model = new Album(testAlbumFile);
        view = new CustomView();
        controller = new Controller(model, view);
        view.setEnabledCalled = false;

    }

    @After
    public void tearDown() throws Exception {
        if (testAlbumFile.exists()) {
            testAlbumFile.delete();
        }
    }

    /**
     * author: atscott
     */
    @Test
    public void testOnNewAlbumWithNullFile() throws Exception {
        controller.OnNewAlbum(null);
        Assert.assertEquals(view.clearEverythingCalled, false);
        Assert.assertEquals(view.displayAlbumNameCalledWith, null);
    }

    /**
     * author: atscott
     */
    @Test
    public void testOnNewAlbumWithFileThatCannotBeCreated() throws Exception {
        controller.OnNewAlbum(new File("D:/%#$#(&*($%&#$%&#)$*$_@#$@#"));
        Assert.assertEquals(view.clearEverythingCalled, false);
        Assert.assertEquals(view.displayAlbumNameCalledWith, null);
        Assert.assertNotNull(view.showErrorMessageCalledWith);
    }

    /**
     * author: atscott
     */
    @Test
    public void testOnNewAlbumWithExistingFile() throws Exception {
        controller.OnNewAlbum(testAlbumFile);
        Assert.assertEquals(view.clearEverythingCalled, true);
        Assert.assertNotNull(view.displayAlbumNameCalledWith);
        Assert.assertEquals(view.showErrorMessageCalledWith, null);
    }

    /**
     * author: atscott
     *
     * @throws Exception
     */
    @Test
    public void testOpenAlbumWithNullFile() throws Exception {
        controller.OnOpenAlbum(null);
        Assert.assertEquals(view.setEnabledCalled, false);
        Assert.assertEquals(view.clearEverythingCalled, false);
        Assert.assertEquals(view.displayAlbumNameCalledWith, null);
    }

    /**
     * author: atscott
     *
     * @throws Exception
     */
    @Test
    public void testOpenAlbumWithInvalidPath() throws Exception {
        controller.OnOpenAlbum(new File("\\\\\\\\\\\\\\\\\\\\"));
        Assert.assertNotNull(view.showErrorMessageCalledWith);
        Assert.assertEquals(view.setEnabledCalled, false);
        Assert.assertEquals(view.clearEverythingCalled, false);
        Assert.assertEquals(view.displayAlbumNameCalledWith, null);
    }

    /**
     * author: atscott
     */
    @Test
    public void testOpenAlbumWithExistingFile() {
        controller.OnOpenAlbum(testAlbumFile);
        Assert.assertEquals(view.setEnabledCalled, true);
        Assert.assertEquals(view.clearEverythingCalled, true);
        Assert.assertNotNull(view.displayAlbumNameCalledWith);
    }

    /**
     * author: atscott
     *
     * @throws Exception
     */
    @Test
    public void testOnSaveAlbumWithAFewFiles() throws Exception {
        controller.OnOpenAlbum(testAlbumFile);
        //get some photos and add them to the album
        ArrayList<File> somePhotos = this.getSomePhotos();
        controller.AddPhoto(somePhotos);
        //save the album
        controller.OnSaveAlbum();


        //now check the file to make sure that it has all the photo paths
        //List<String> lines = Files.readAllLines(Paths.get(testAlbumFile.getPath()), ENCODING);
        BufferedReader in = new BufferedReader(new FileReader(testAlbumFile));
        String line;
        List<String> lines = new LinkedList<String>();
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }
        if (lines.size() != somePhotos.size()) {
            Assert.fail("Number of lines in file (" + lines.size() + ") is not equal to number of photos added (" + somePhotos.size() + ")");
        }
        for (File file : somePhotos) {
            if (!lines.contains(file.getPath())) {
                Assert.fail("Saved album does not contain expected file: " + file.getPath());
            }
        }
    }

    /**
     * author: scottat
     *
     * @throws Exception
     */
    @Test
    public void testOnSaveAlbumWithNoOpenAlbum() throws Exception {
        controller.OnSaveAlbum();
        //error message should have been displayed
        Assert.assertNotNull(view.showErrorMessageCalledWith);
    }

    /**
     * author: scottat
     *
     * @throws Exception
     */
    @Test
    public void testOnSaveAlbumWithEmptyAlbum() throws Exception {
        controller.OnOpenAlbum(testAlbumFile);
        controller.OnSaveAlbum();
        //List<String> lines = Files.readAllLines(Paths.get(testAlbumFile.getPath()), ENCODING);
        BufferedReader in = new BufferedReader(new FileReader(testAlbumFile));
        String line;
        List<String> lines = new LinkedList<String>();
        while ((line = in.readLine()) != null) {
            lines.add(line);
        }
        //file should be empty
        Assert.assertEquals(0, lines.size());
        //no error message should have been displayed
        Assert.assertNull(view.showErrorMessageCalledWith);
    }


    /**
     * Tests on add photo
     * author: Chris Hougard
     * @throws Exception
     */
    @Test
    public void testOnAddPhoto() throws Exception {
        controller.OnOpenAlbum(testAlbumFile);

        // verify a photo can be added
        view.addPhotoCalledWith = null;
        ArrayList<File> somePhotos = new ArrayList<File>();
        String path = getClass().getResource("/testAssets/Chrysanthemum.jpg").getPath();
        path = path.replace("%20", " ");
        File f = new File(path);
        somePhotos.add(f);
        controller.AddPhoto(somePhotos);
        Assert.assertEquals(f.getAbsolutePath(), view.addPhotoCalledWith.getAbsolutePath());

        // try to add the photo again
        view.addPhotoCalledWith = null;
        view.showErrorMessageCalledWith = null;
        controller.AddPhoto(somePhotos);
        Assert.assertNull(view.addPhotoCalledWith);
        Assert.assertNotNull(view.showErrorMessageCalledWith);

        // give it an empty list
        view.addPhotoCalledWith = null;
        view.showErrorMessageCalledWith = null;
        controller.AddPhoto(new LinkedList<File>());
        Assert.assertNull(view.addPhotoCalledWith);

        // try to add a bad photo
        view.addPhotoCalledWith = null;
        view.showErrorMessageCalledWith = null;
        somePhotos = new ArrayList<File>();
        path = getClass().getResource("/testAssets/bad.jpg").getPath();
        path = path.replace("%20", " ");
        f = new File(path);
        somePhotos.add(f);
        controller.AddPhoto(somePhotos);
        Assert.assertNull(view.addPhotoCalledWith);
    }

    /**
     * Tests on delete photo
     * author: Chris Hougard
     * @throws Exception
     */
    @Test
    public void testOnDeletePhoto() throws Exception {
        controller.OnOpenAlbum(testAlbumFile);
        ArrayList<File> somePhotos = this.getSomePhotos();
        controller.AddPhoto(somePhotos);

        // try to delete a photo
        view.removePhotoCalledWith = null;
        view.showErrorMessageCalledWith = null;
        controller.OnDeletePhoto(somePhotos.get(0));
        Assert.assertEquals(somePhotos.get(0).getAbsolutePath(), view.removePhotoCalledWith.getAbsolutePath());

        // try to delete a photo not in the list
        view.removePhotoCalledWith = null;
        view.showErrorMessageCalledWith = null;
        controller.OnDeletePhoto(somePhotos.get(0));
        Assert.assertNull(view.removePhotoCalledWith);
        Assert.assertNotNull(view.showErrorMessageCalledWith);
    }

    /**
     * author: tohtzk
     * @throws Exception
     */
    @Test
    public void testToggleSlideshow() throws Exception {
        ArrayList<File> somePhotos = this.getSomePhotos();
        controller.OnOpenAlbum(testAlbumFile);
        controller.AddPhoto(somePhotos);
        Assert.assertTrue(controller.StartSlideshow());
        Assert.assertFalse(controller.StartSlideshow());
    }

    /**
     * author: tohtzk
     * @throws Exception
     */
    @Test
    public void testToggleSlideshowWithNoAlbum() throws Exception {
        Assert.assertFalse(controller.StartSlideshow());
    }

    /**
     * author: tohtzk
     * @throws Exception
     */
    @Test
    public void testStopSlideshow() throws Exception {
        ArrayList<File> somePhotos = this.getSomePhotos();
        controller.OnOpenAlbum(testAlbumFile);
        controller.AddPhoto(somePhotos);
        Assert.assertTrue(controller.StartSlideshow());
        Assert.assertTrue(controller.StopSlideshow());
    }

    /**
     * author: tohtzk
     * @throws Exception
     */
    @Test
    public void testStopSlideshowWithNoAlbum() throws Exception {
        Assert.assertFalse(controller.StopSlideshow());
    }

    /**
     * author: tohtzk
     * @throws Exception
     */
    @Test
    public void testStartSlideshow() throws Exception {
        ArrayList<File> somePhotos = this.getSomePhotos();
        controller.OnOpenAlbum(testAlbumFile);
        controller.AddPhoto(somePhotos);
        Assert.assertTrue(controller.StartSlideshow());
    }

    /**
     * author: scottat
     * @throws Exception
     */
    @Test
    public void testSlideshowFunction() throws Exception {
        final int interval = 1000;

        view.listener = new CustomView.ShowImageCalledListener() {
            @Override
            public void handleShowImageCalled(EventObject e) {
                long currentTime = System.currentTimeMillis();
                if (lastTimeShowImageWasCalled != 0) {
                    if(Math.abs(currentTime - lastTimeShowImageWasCalled) > interval + 1000){
                        Assert.fail("slideshow next image not called in a reasonable amount of time");
                    }
                }
                lastTimeShowImageWasCalled = currentTime;
            }
        };

        controller.OnOpenAlbum(testAlbumFile);
        //get some photos and add them to the album
        ArrayList<File> somePhotos = this.getSomePhotos();
        controller.AddPhoto(somePhotos);
        controller.OnTimeChange(interval/1000);

        if(!controller.StartSlideshow()){
            Assert.fail("Could not start slideshow");
        }

        Thread.sleep(5000);
        if(view.numberOfTimesShowImageCalled < 5){
            Assert.fail("show image not called 5 times in 5 seconds with slideshow running at 5 second interval");
        }
    }

    /**
     * @author moorea
     * @throws Exception
     */
    @Test
    public void testShowImage() throws Exception {
    	controller.OnOpenAlbum(testAlbumFile);
    	File f = new File("testAssets/Chrysanthemum.jpg");
        controller.ShowImage(f);
    	Assert.assertTrue(f.getCanonicalPath().equals(view.showImageCalledawith.getCanonicalPath()));
    }
    
    /**
     * @author moorea
     * @throws Exception
     */
    @Test
    public void testOnTimeChange() throws Exception {
    	controller.OnTimeChange(10);
    	Assert.assertTrue(((Controller) controller).getTimeBetweenImages() == 10);
    }

    private ArrayList<File> getSomePhotos() {
        ArrayList<File> temp = new ArrayList<File>();
        String path = getClass().getResource("/testAssets/Chrysanthemum.jpg").getPath();
        path = path.replace("%20", " ");
        File f = new File(path);
        temp.add(f);
        path = getClass().getResource("/testAssets/Desert.jpg").getPath();
        path = path.replace("%20", " ");
        f = new File(path);
        temp.add(f);
        path = getClass().getResource("/testAssets/Hydrangeas.jpg").getPath();
        path = path.replace("%20", " ");
        f = new File(path);
        temp.add(f);
        path = getClass().getResource("/testAssets/Jellyfish.jpg").getPath();
        path = path.replace("%20", " ");
        f = new File(path);
        temp.add(f);
        path = getClass().getResource("/testAssets/Koala.jpg").getPath();
        path = path.replace("%20", " ");
        f = new File(path);
        temp.add(f);
        return temp;
    }


}
