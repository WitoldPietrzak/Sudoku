package pl.comp.gui.authors;

import java.util.ListResourceBundle;

public class authors_pl extends ListResourceBundle {

    private static final Object[][] contents = {
            {"createdBy","Stworzone Przez:"},
            {"author1","Witold Pietrzak"},
            {"author2","Karol Domanski"}
    };


    @Override
    protected Object[][] getContents() {
        return contents;
    }
}
