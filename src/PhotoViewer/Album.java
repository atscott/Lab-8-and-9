package PhotoViewer;

import java.io.File;
import java.io.IOException;

/**
 * User: atscott
 * Date: 10/24/12
 * Time: 7:43 AM
 */
public class Album implements IAlbumModel {
    final File me;

    public Album(String name) throws IOException{
        me = new File(name);
        if (me.exists()) {
            me.delete();
        }
        me.createNewFile();
    }

}
