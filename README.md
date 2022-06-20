## Домашнее задание к занятию «2.3. Spring Web MVC»

### Задание Migration.

**v. 1.0**

Задание реализовано.
- Добавлен WebConfig в пакет _ru.netology.config_;
- Изменен PostController в пакете _ru.netology.controller_;
- Добавлен ApplicationInitializer в пакет _ru.netology.initializer_;
- В PostRepository и PostService добавлены аннотации @Repository и @Service, соответственно;
- Вместо MainServlet добавлен класс Application с точкой входа в программу:
```
public class Application {
    public static final int PORT = 8080;
    public static final String TOMCAT = "tomcat";

    public static void main(String[] args) throws IOException, LifecycleException {
        final var tomcat = new Tomcat();
        final var baseDir = Files.createTempDirectory(TOMCAT);
        baseDir.toFile().deleteOnExit();
        tomcat.setBaseDir(baseDir.toAbsolutePath().toString());

        final var connector = new Connector();
        connector.setPort(PORT);
        tomcat.setConnector(connector);
        tomcat.getHost().setAppBase(".");
        tomcat.addWebapp("", ".");
        tomcat.start();
        tomcat.getServer().await();
    }
}
```