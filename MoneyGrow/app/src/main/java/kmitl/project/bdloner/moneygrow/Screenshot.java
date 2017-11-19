package kmitl.project.bdloner.moneygrow;

import android.graphics.Bitmap;
import android.view.View;

/**
 * Created by BDLoneRZ on 9/13/2017.
 */

public class Screenshot {

    public static Bitmap takescreenshot(View v) {
        v.setDrawingCacheEnabled(true);
        v.buildDrawingCache(true);
        Bitmap b = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        return b;
    }

}
