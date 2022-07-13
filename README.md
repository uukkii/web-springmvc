## Домашнее задание к занятию «2.3. Spring Web MVC»

### Задание Migration.

**v. 1.1**

Решение исправлено.

- Метод `public Post save(Post savePost)` в классе `PostRepository` изменен:

```
    public Post save(Post savePost) {
        long checkId = savePost.getId();
        if (checkId == 0) {
            long id = counter.incrementAndGet();
            while (allPosts.containsKey(id)) {
                id = counter.incrementAndGet();
            }
            savePost.setId(id);
            allPosts.put(id, savePost);
        } else if (!allPosts.containsKey(checkId)) {
            allPosts.put(checkId, savePost);
        } else {
            allPosts.replace(checkId, savePost);
        }
        return savePost;
    }
```

- Исправлен маппинг удаления поста (добавленая аннотация _@PathVariable_) в классе `PostController`:

```
  @DeleteMapping("/{id}")
  public void removeById(@PathVariable long id) {
    service.removeById(id);
  }
```

- В класс, описывающий исключение `NotFoundException` добавлена аннотация статуса:
```
@ResponseStatus(value = HttpStatus.NOT_FOUND)
```

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