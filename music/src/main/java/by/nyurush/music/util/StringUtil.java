package by.nyurush.music.util;

public class StringUtil {

    public boolean areNotNullAndNotEmpty(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (isNullOrEmpty(string)) {
                return false;
            }
        }
        return true;
    }

    public boolean areNotNull(String... strings) {
        if (strings == null || strings.length == 0) {
            return false;
        }
        for (String string : strings) {
            if (string == null) {
                return false;
            }
        }
        return true;
    }

    public boolean isNullOrEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
