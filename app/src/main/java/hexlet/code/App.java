package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.repository.BaseRepository;
import io.javalin.Javalin;
import gg.jte.ContentType;
import gg.jte.TemplateEngine;
import gg.jte.resolve.ResourceCodeResolver;


import io.javalin.rendering.template.JavalinJte;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

@Slf4j

public class App {
    public static Javalin getApp() throws SQLException {
        //создали бд в памяти машины
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(getDataBaseUrl());
        var dataSource = new HikariDataSource(hikariConfig);

        // Получаем путь до файла с базовыми sql-запросами по настройке таблиц в src/main/resources
        var url = App.class.getClassLoader().getResourceAsStream("schema.sql");
        var sql = new BufferedReader(new InputStreamReader(url))
                .lines().collect(Collectors.joining("\n"));
        // Получаем соединение, создаем стейтмент и выполняем запрос
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }

        //объект - источник данных для будущего исп в классах-контролерах, где будут выполняться разные запрсы в бд
        BaseRepository.dataSource = dataSource;

        //создать инстанс соединения и указать путь к jte-файлам для отрисовки
        var app = Javalin.create(config -> {
            config.fileRenderer(new JavalinJte(createTemplateEngine()));
        });

        //указываем запросы
        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static int getPort() {
        String port = System.getenv().getOrDefault("PORT", "7070");
        return Integer.valueOf(port);
    }

    public static String getDataBaseUrl() {
        String url = System.getenv().getOrDefault("JDBC_DATABASE_URL",
                "jdbc:h2:mem:project");
        return url;
    }

    private static TemplateEngine createTemplateEngine() {
        ClassLoader classLoader = App.class.getClassLoader();
        ResourceCodeResolver codeResolver = new ResourceCodeResolver("templates", classLoader);
        TemplateEngine templateEngine = TemplateEngine.create(codeResolver, ContentType.Html);
        return templateEngine;
    }

    public static void main(String[] args) throws IOException, SQLException, Exception {
        Javalin app = getApp();
        app.start(getPort());
    }
}
