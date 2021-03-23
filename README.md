# Final Project — Music Time #

## О проекте
Music Time — веб-приложение для поиска и прослушивания музыкальных композиций. В приложении реализовано разграничение прав доступа по ролям: 
- администратор;
- пользователь;
- пользователь с подпиской.

Для зарегистрированных пользователей, не имеющих подписку, обеспечивается доступ к следующему функционалу:
- прослушивание музыкальных композиций;
- поиск композиций по артисту, альбому, жанру или названию трека;
- возможность изменений данных в личном кабинете.

Для пользователей, оформивших подписку, включая функционал обычных пользователей, доступны дополнительные функции:
- возможность создавать собственные плейлисты;
- добавление понравившейся музыки в избранное;
- прослушивание подборки от редакции, брендов, музыкантов, блогеров и т. д.

Для администратора обеспечивается доступ к следующему функционалу:
- добавление артистов, альбомов, музыкальных композиций;
- удаление и редактирование информации о музыкальных композициях;
- прослушивание музыкальных композиций;
- поиск композиций по артисту, альбому, жанру или названию трека;
- возможность создавать плейлисты (подборки).

Для входа с правами администратора используйте логин - "admin" и пароль - "admin", для пользователя - "user" и пароль - "QQww1122"

##### Схема БД
![Image alt](https://github.com/natallia-yurush/final_project_2020/blob/master/database%20music.jpg)


## Используемые технологии
- Java 13;
- В качестве контейнера сервлетов используется Apache Tomcat 9.40;
- База данных - MySql 8;
- Для соеденения с базой данных используется JDBC;
- JavaEE (Java Servlets, JSP);
- Для расширения JSP используется JSTL;
- Для логирования используется библиотека журналирования Log4j2;
- Для тестирования был использован TestNG;
- Maven — для автоматизации сборки проекта.
