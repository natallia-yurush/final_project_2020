package by.nyurush.music.controller.command;

import javax.servlet.http.HttpSession;
import java.util.Locale;
import java.util.ResourceBundle;

//TODO: change name
public class ResourceBundleCommand {

    public static ResourceBundle getResourceBundle(HttpSession session) {
        Object localParameter = session.getAttribute("javax.servlet.jsp.jstl.fmt.locale.session");
        Locale currentLang;
        if (localParameter != null) {
            String string = String.valueOf(localParameter);
            String[] langParameters = string.split("_");
            currentLang = new Locale(langParameters[0], langParameters[1]);
        } else {
            currentLang = new Locale("");
        }
        return ResourceBundle.getBundle("pagecontent", currentLang);
    }
}
