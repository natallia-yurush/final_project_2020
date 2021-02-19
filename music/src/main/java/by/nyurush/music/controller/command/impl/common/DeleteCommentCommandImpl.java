package by.nyurush.music.controller.command.impl.common;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.entity.Comment;
import by.nyurush.music.entity.Playlist;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.CommentService;
import by.nyurush.music.service.impl.TrackService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import by.nyurush.music.util.validation.StringUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;
import java.util.ResourceBundle;

import static by.nyurush.music.util.constant.ConstantAttributes.ERROR_MESSAGE;
import static by.nyurush.music.util.constant.ConstantAttributes.SUCCESS_MESSAGE;

public class DeleteCommentCommandImpl implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteCommentCommandImpl.class);
    private static final CommentService commentService = new CommentService();
    private final TrackService trackService = new TrackService();

    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) throws ServiceException {
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);
        String idStr = req.getParameter(ConstantAttributes.COMMENT_ID);
        if(StringUtil.isNullOrEmpty(idStr)) {
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_FIND_COMMENT));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_COMMENTS);
        }

        Integer id = Integer.parseInt(idStr);
        Integer trackId = null;
        Optional<Comment> comment = commentService.findById(id);
        if (comment.isPresent()) {
            commentService.delete(comment.get());
            trackId = comment.get().getTrack().getId();
        } else {
            LOGGER.warn("Comment was not found");
            req.setAttribute(ERROR_MESSAGE, rb.getString(ConstantMessages.INVALID_FIND_COMMENT));
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_COMMENTS);
        }

        req.setAttribute(ConstantAttributes.COMMENTS, commentService.findAllByTrack(trackId));
        req.setAttribute(ConstantAttributes.SONG, trackService.findById(trackId).orElseThrow());
        req.setAttribute(SUCCESS_MESSAGE, rb.getString(ConstantMessages.SUCCESSFUL_DELETE_COMMENT));
        return CommandResult.forward(ConstantPathPages.PATH_PAGE_COMMENTS);
    }
}
