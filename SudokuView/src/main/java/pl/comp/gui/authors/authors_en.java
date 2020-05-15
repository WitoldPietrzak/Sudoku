package pl.comp.gui.authors;

import java.util.ListResourceBundle;

public class authors_en extends ListResourceBundle {

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
