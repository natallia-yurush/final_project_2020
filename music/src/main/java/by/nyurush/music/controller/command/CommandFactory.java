package by.nyurush.music.controller.command;

import by.nyurush.music.controller.command.impl.common.ChangeLanguageCommandImpl;
import by.nyurush.music.controller.command.impl.user.LoginCommandImpl;
import by.nyurush.music.controller.command.impl.user.RegisterCommandImpl;
import by.nyurush.music.controller.command.impl.user.SignUpCommandImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public static Command create(String command) {
        switch (command) {
            case "login":
                return new LoginCommandImpl();
            case "signup":
                return new SignUpCommandImpl();
            case "register":
                return new RegisterCommandImpl();
            case "changeLanguage":
                return new ChangeLanguageCommandImpl();



            default:
                LOGGER.error("Unknown command: " + command);
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
