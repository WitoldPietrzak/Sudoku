package sudoku;

import java.util.Locale;
import java.util.ResourceBundle;

public enum DifficultyLevel {
    Easy, Normal, Hard;


    private int fieldsToRemove;
    private String lang="pl";

    static {
        Easy.fieldsToRemove = 49;
        Normal.fieldsToRemove = 53;
        Hard.fieldsToRemove = 55;
    }

    public int getFieldsToRemove() {
        return fieldsToRemove;
    }

    public void setLang(String lang){
        this.lang=lang;
    }

    public String getLocaleText() {
        Locale locale = new Locale(lang);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang",locale);
        return resourceBundle.getString(this.name());
    }
    @Override
    public String toString() {
        return this.getLocaleText();
    }

}
