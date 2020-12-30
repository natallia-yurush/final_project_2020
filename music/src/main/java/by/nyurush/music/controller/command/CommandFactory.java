package by.nyurush.music.controller.command;

import by.nyurush.music.controller.command.impl.user.LoginCommandImpl;
import by.nyurush.music.controller.command.impl.user.SignUpCommandImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {
    private static Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    public static Command create(String command) {
        switch (command) {
            case "login":
                return new LoginCommandImpl();
            case "sign up":
                return new SignUpCommandImpl();


            default:
                LOGGER.error("Unknown command: " + command);
                throw new UnsupportedOperationException("Unknown command: " + command);
        }
    }
}
