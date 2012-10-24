package PhotoViewer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Album implements IAlbumModel {
    final File me;
    private ArrayList<File> pictures = new ArrayList<File>();

    public Album(String name) throws IOException{
        me = new File(name);
        if (me.exists()) {
            me.delete();
        }
        me.createNewFile();
    }

    @Override
    public String GetName() {
        return me.getName();
    }

    @Override
    public void Save() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
