package by.nyurush.music.tag;

import by.nyurush.music.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class HelloTag extends TagSupport {
    private User user;

    @Override
    public int doStartTag() throws JspException {
        String message = "Hello, " + user.getFirstName() + user.getLastName() +
                ", your role is " +user.getRole();
        try {
            pageContext.getOut().write("<div>" + message + "<div");
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return super.doEndTag();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
