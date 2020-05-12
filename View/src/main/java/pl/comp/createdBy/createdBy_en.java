package pl.comp.createdBy;

import java.util.ListResourceBundle;

public class createdBy_en extends ListResourceBundle {

    private static final Object[][] contents = {
            {"createdBy","Created By:"},
            {"author1","Witold Pietrzak"},
            {"author2","Karol Domanski"}
    };


    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
