package by.nyurush.music.util.language;

import by.nyurush.music.util.constant.ConstantAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GenreUtil {

    public static List<String> getGenres(List<String> genres, ResourceBundle rb) {
        List<String> newList = new ArrayList<>();
        ResourceBundle resourceBundle = ResourceBundle.getBundle(ConstantAttributes.RES_GENRE, rb.getLocale());
        for (String genre: genres) {
            newList.add(resourceBundle.getString(genre.toLowerCase()));
        }
        return newList;
    }
}
