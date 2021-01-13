package by.nyurush.music.controller.command.impl.admin;

import by.nyurush.music.controller.command.Command;
import by.nyurush.music.controller.command.CommandResult;
import by.nyurush.music.dao.DaoHelperFactory;
import by.nyurush.music.entity.Artist;
import by.nyurush.music.service.exception.ServiceException;
import by.nyurush.music.service.impl.ArtistService;
import by.nyurush.music.util.constant.ConstantAttributes;
import by.nyurush.music.util.constant.ConstantMessages;
import by.nyurush.music.util.constant.ConstantPathPages;
import by.nyurush.music.util.language.ResourceBundleUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.ResourceBundle;

public class AddArtistCommandImpl implements Command {
    @Override
    public CommandResult execute(HttpServletRequest req, HttpServletResponse resp) {
/*

        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            //todo
        }

        // Создаём класс фабрику
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024 * 1024);

        // устанавливаем временную директорию
        File tempDir = (File) req.getSession().getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List<FileItem> formItems = upload.parseRequest(req);

            if (formItems != null && formItems.size() > 0) {
                */
        /*обрабатываем поля формы*//*


                for (FileItem item : formItems) {
                    */
        /*обработка тех полей, которые содержат файл*//*

                    if (!item.isFormField()) {

						*/
/*itemField - создаем объект, поле которого
                        будет хранить имя файла
						* nameField - возращает имя файла или каталога
						* filePath - получаем полный абсолютный путь до загружаемого файла,
						* во временный каталог на сервере
						* isVoidFileName - проверяем загружен ли файл
						* field - получаем название поля загружаемого файла*//*

                        String field = item.getFieldName();
                        File itemField = new File(item.getName());
                        String fileName = itemField.getName();
                        String filePath = uploadPath + File.separator + fileName;

                        FileUpload.isVoidFileName(fileName,field, filePath, session);


                        */
        /*создаем объект для хранения полного пути файла*//*

                        File storeFile = new File(filePath);


                        */
        /*записываем файл на диск во временный каталог*//*

                        item.write(storeFile);
                        request.setAttribute("message", "Загрузка успешна!");
                    } else if (item.isFormField()) {


                        */
        /*если обрабатываемая часть данных - это поле формы
         * тогда получаем значение данного поля и имя этого поля формы
         * при получении значения обязательно указываем кодировку*//*

                        String one = new String(item.get(), "UTF-8");
                        String nameField = item.getFieldName();
                        session.setAttribute(nameField, one);

                    }
                }
            }

        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            //e.printStackTrace();
            request.setAttribute("message", "При обработке запроса, произошла" +
                    "ошибка: " + e.getMessage());
        }

*/


/*
        boolean isMultipart = ServletFileUpload.isMultipartContent(req);
        if (!isMultipart) {
            //todo
        }

        // Создаём класс фабрику
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024 * 1024);

        // устанавливаем временную директорию
        File tempDir = (File) req.getSession().getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(req);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы
                    //processFormField(item);
                } else {
                    //в противном случае рассматриваем как файл
                    File uploadetFile = null;
                    //выбираем файлу имя пока не найдём свободное
                    do {
                        String path = req.getSession().getServletContext().getRealPath("/resource/img/artists/" + item.getName());
                        uploadetFile = new File(path);
                    } while (uploadetFile.exists());

                    //создаём файл
                    uploadetFile.mkdirs();
                    uploadetFile.createNewFile();
                    //записываем в него данные
                    item.write(uploadetFile);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
*/




/*

        String uploadPath = req.getSession().getServletContext().getRealPath("") + File.separator + UPLOAD__DIRECTORY;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();


        for (Part part : req.getPart()) {
            fileName = getFileName(part);
            part.write(uploadPath + File.separator + fileName);
        }
        fileName = part.getSubmittedFileName();
*/

///////////////////////////////////////////////////////////////
        String artistName = null;// = req.getParameter(ARTIST_NAME);
        String artistImg = null;// = req.getParameter(ARTIST_IMAGE);


        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).
                    artistName = item.getString();
                   /* String fieldValue = item.getString();
                    artistName = fieldValue;*/
                    // ... (do your job here)
                } else {
                    // Process form file field (input type="file").


                    String fileName = new File(item.getName()).getName();
                    //TODO: вынести в константу
                    artistImg = fileName;
                    String filePath = "D:\\JAVA_ST_2020\\final_project\\music\\web\\resource\\img\\artists\\" + fileName;

                    File storeFile = new File(filePath);
                    item.write(storeFile);



                    //InputStream fileContent = item.getInputStream();//.getInputStream();



                    //String path = getClass().getResource("/").getPath() + "web/resource/img/artists/" + fileName;




/*
                    ServletContext sc = req.getSession().getServletContext();
                    File savedFile = new File(sc.getRealPath("resource/img/artists/"), fileName);
                    String Path = req.getSession().getServletContext().getInitParameter("resource/img/artists/") + fileName;
                    savedFile.mkdirs();
*/

                }
            }
        } catch(Exception e) {
            e.printStackTrace();
            //TODO
        }



        Artist artist = new Artist(null, artistName, artistImg);
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(req);

        try {
            ArtistService artistService = new ArtistService(new DaoHelperFactory());
            artistService.save(artist);

            req.setAttribute(ConstantAttributes.SAVE_RESULT, rb.getString(ConstantMessages.SUCCESSFUL_SAVE_RESULT));


        } catch (ServiceException e) {
            req.setAttribute(ConstantAttributes.SAVE_RESULT, rb.getString(ConstantMessages.INVALID_SAVE_RESULT));
            e.printStackTrace();//TODO
            return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
        }


        return CommandResult.forward(ConstantPathPages.PATH_PAGE_ADD_ARTIST);
    }
}

      //////////////////////////////////////////////////////


/*
                    try (FileOutputStream outputStream = new FileOutputStream(storeFile)) {
                        int read;
                        byte[] bytes = new byte[1024];

                        while ((read = fileContent.read(bytes)) != -1) {
                            outputStream.write(bytes, 0, read);
                        }
                    }
                    */
                    // saves the file on disk


                    /*
                    String fieldName = item.getFieldName();
                    String fileName = FilenameUtils.getName(item.getName());
                    InputStream fileContent = item.getInputStream();


                    item.write(new File("web/resource/img/artists/" + fileName));
                    // ... (do your job here)
                     */
        /*
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        /*catch (FileUploadException | IOException e) {
            e.printStackTrace();
            //TODO
        }*/

/*
        PrintWriter out = null;
        try {
            out = resp.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean isMultipartContent = ServletFileUpload.isMultipartContent(req);
        if (!isMultipartContent) {
            return null; //TODO
        }
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            List<FileItem> fields = upload.parseRequest(req);
            Iterator< FileItem > it = fields.iterator();
            if (!it.hasNext()) {
                return null; //todo
            }

            while (it.hasNext()) {
                FileItem fileItem = it.next();
                boolean isFormField = fileItem.isFormField();
                if (isFormField) {
                    if (artistImg == null) {
                        if (fileItem.getFieldName().equals("file_name")) {
                            artistImg = fileItem.getString();
                        }
                    }
                } else {
                    if (fileItem.getSize() > 0) {
                        //    fileItem.write(new File("E:\\uploaded_files\\" + fileItem.getName()));

                        String file_name2=fileItem.getName();
                        fileItem.write(new File("web/resource/img/artists/" + file_name2));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            /*out.println("<script type='text/javascript'>");
            out.println("window.location.href='index.jsp?filename="+file_name2+"'");
            out.println("</script>");
            out.close();
        }

*/

/*
        Artist artist = new Artist(null, artistName, artistImg);

        try {
            ArtistService artistService = new ArtistService(new DaoHelperFactory());
            artistService.save(artist);


        } catch (ServiceException e) {
            e.printStackTrace();
        }


        return null;
    }
}*/
