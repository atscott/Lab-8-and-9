package Tests;

import PhotoViewer.*;
import org.junit.*;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: scottat
 * Date: 11/3/12
 * Time: 1:49 PM
 */
public class ControllerTest {

    private static IController controller;
    private static CustomView view;
    private static File testAlbumFile = null;


    public ControllerTest() throws IOException {

    }

    @Before
    public void setUp() throws Exception {

    }

    /**
     * make sure the view, model and controller are fresh before every test
     */
    @BeforeClass
    public static void setUpBeforeClass() throws IOException {
        testAlbumFile = new File("test.alb");
        if (!testAlbumFile.exists()) {
            testAlbumFile.createNewFile();
        }else{
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

    }

    /**
     * author: atscott
     */
    @Test
    public void testOnNewAlbumWithNullFile() throws Exception {
        controller.OnNewAlbum(null);
        Assert.assertEquals(this.view.clearEverythingCalled, false);
        Assert.assertEquals(this.view.displayAlbumNameCalledWith, null);
    }

    /**
     * author: atscott
     */
    @Test
    public void testOnNewAlbumWithFileThatCannotBeCreated() throws Exception {
        controller.OnNewAlbum(new File("#$%^&*("));
        Assert.assertEquals(this.view.clearEverythingCalled, false);
        Assert.assertEquals(this.view.displayAlbumNameCalledWith, null);
        Assert.assertNotNull(this.view.showErrorMessageCalledWith);
    }

    /**
     * author: atscott
     */
    @Test
    public void testOnNewAlbumWithExistingFile() throws Exception {
        controller.OnNewAlbum(testAlbumFile);
        Assert.assertEquals(this.view.clearEverythingCalled, true);
        Assert.assertNotNull(this.view.displayAlbumNameCalledWith);
        Assert.assertEquals(this.view.showErrorMessageCalledWith, null);
    }

    /**
     * author: atscott
     * @throws Exception
     */
    @Test
    public void testOpenAlbumWithNullFile() throws Exception {
        controller.OnOpenAlbum(null);
        Assert.assertEquals(this.view.setEnabledCalled, false);
        Assert.assertEquals(this.view.clearEverythingCalled, false);
        Assert.assertEquals(this.view.displayAlbumNameCalledWith, null);
    }

    /**
     * author: atscott
     * @throws Exception
     */
    @Test
    public void testOpenAlbumWithInvalidPath() throws Exception {
        controller.OnOpenAlbum(new File("lkajsdflkj@#$%^&*("));
        Assert.assertNotNull(this.view.showErrorMessageCalledWith);
        Assert.assertEquals(this.view.setEnabledCalled, false);
        Assert.assertEquals(this.view.clearEverythingCalled, false);
        Assert.assertEquals(this.view.displayAlbumNameCalledWith, null);
    }

    /**
     * author: atscott
     */
    @Test
    public void testOpenAlbumWithExistingFile(){
        controller.OnOpenAlbum(this.testAlbumFile);
        Assert.assertEquals(this.view.setEnabledCalled, true);
        Assert.assertEquals(this.view.clearEverythingCalled, true);
        Assert.assertNotNull(this.view.displayAlbumNameCalledWith);
    }

    /**
     * author: atscott
     *
     * @throws Exception
     */
    @Test
    public void testOnSaveAlbum() throws Exception {
        controller.OnOpenAlbum(this.testAlbumFile);

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

    private void addSomePics(){
        ArrayList<File> temp = new ArrayList<File>();
        File f = new File(getClass().getResource("/testAssets/Chrysanthemum.jpg").getPath());
        temp.add(f);
        f = new File(getClass().getResource("/testAssets/Desert.jpg").getPath());
        temp.add(f);
        f = new File(getClass().getResource("/testAssets/Hydrangeas.jpg").getPath());
        temp.add(f);
        f = new File(getClass().getResource("/testAssets/Jellyfish.jpg").getPath());
        temp.add(f);
        f = new File(getClass().getResource("/testAssets/Koala.jpg").getPath());
        temp.add(f);
    }


}
