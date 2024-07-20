package gg.jte.generated.ondemand;
import gg.jte.Content;
public final class JteindexGenerated {
	public static final String JTE_NAME = "index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,4,4,8,8,20,20,20,20,24,24,24,24};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <h1>Заголовок моего локал-сайта!</h1>\n    <h2>Подзаголовок! Если ты читаешь - пожалуйста, пользуйся</h2>\n\n    <h4>Тут ты увидишь:</h4>\n    <ul>\n        <li><a href=\"/\">Главную страницу</a></li>\n        <li><a href=\"/\">Список всех курсов(из прошлого курса)</a></li>\n        <li><a href=\"/\">Список всех сайтов</a></li>\n        <li>А вообще будем анализировать страницы других сайтов</li>\n\n    </ul>\n");
			}
		}, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n    <div class=\"footer\">\n        <a href=\"https://github.com/Kagawan\">профиль на GitHub</a>\n    </div>\n");
			}
		});
		jteOutput.writeContent(" ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, content);
	}
}
