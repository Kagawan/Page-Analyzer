package hexlet.code;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import hexlet.code.model.Url;
import hexlet.code.repository.BaseRepository;
import hexlet.code.repository.UrlCheckRepository;
import hexlet.code.repository.UrlRepository;
import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;


import static hexlet.code.App.readResourceFile;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AppTest {
    private static MockWebServer mockServer;
    Javalin app;

    @BeforeEach
    public final void setUp() throws IOException, SQLException {
        app = App.getApp();
    }

    @BeforeAll
    public static void beforeAll() throws IOException, SQLException {
        var hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl("jdbc:h2:mem:project;DB_CLOSE_DELAY=-1;");
        var dataSource = new HikariDataSource(hikariConfig);
        var sql = readResourceFile("schema.sql");
        try (var connection = dataSource.getConnection();
             var statement = connection.createStatement()) {
            statement.execute(sql);
        }
        BaseRepository.dataSource = dataSource;
    }

    @Test
    void testRoot() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains("Анализатор страниц");
        });
    }
    @Test
    void testUrlsPath() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get(NamedRoutes.urlsPath());
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testAddUrls() {
        JavalinTest.test(app, (server, client) -> {
            var url = "https://www.example.com";
            var response = client.post(NamedRoutes.urlsPath(), "url=" + url);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string()).contains(url);
            assertThat(UrlRepository.find(url).get().getName()).isEqualTo(url);
        });
    }

    @Test
    public void testAddWrongUrl() {
        JavalinTest.test(app, (server, client) -> {
            var url = "  https:/www.example.com";
            var response2 = client.post(NamedRoutes.urlsPath(), "url=" + url);
            assertThat(response2.body().string()).contains("Некорректный URL");
        });
    }

    @Test
    void testCheckUrl() {
        var url = mockServer.url("/").toString();
        Url urlForCheck = new Url(url);
        UrlRepository.save(urlForCheck);
        JavalinTest.test(app, (server, client) -> {
            var response = client.post(NamedRoutes.urlsChecksPath(urlForCheck.getId()));
            assertThat(response.code()).isEqualTo(200);
            var lastCheck = UrlCheckRepository.find(urlForCheck.getId()).orElseThrow();
            assertThat(lastCheck.getTitle()).isEqualTo("Example Title");
            assertThat(lastCheck.getH1()).isEqualTo("Example Domain");
            assertThat(lastCheck.getDescription()).isEqualTo("");

            var afterPost = client.get(NamedRoutes.urlsPath());
            assertThat(afterPost.code()).isEqualTo(200);
            assertThat(afterPost.body().string()).contains(lastCheck.getCreatedAt().toLocalDateTime()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        });
    }
}
