package by.nyurush.music.controller.command;

import by.nyurush.music.controller.command.impl.common.ChangeLanguageCommandImpl;
import by.nyurush.music.controller.command.impl.user.LoginCommandImpl;
import by.nyurush.music.controller.command.impl.user.RegisterCommandImpl;
import by.nyurush.music.controller.command.impl.user.SignUpCommandImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Logger LOGGER = LogManager.getLogger(CommandFactory.class);


    private static final Map<String, Command> commands = new HashMap<>();

    static {
        commands.put("login", new LoginCommandImpl());
        commands.put("signup", new SignUpCommandImpl());
        commands.put("register", new RegisterCommandImpl());
        commands.put("changeLanguage", new ChangeLanguageCommandImpl());
    }

    public static Command getCommand(String commandName) {
        Command command = null;
        try {
            command = commands.get(commandName);
        } catch (IllegalArgumentException e) {
            LOGGER.info("Could not receive command by name: " + commandName);
        }
        return command;
    }


    /*
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
    }*/
}
