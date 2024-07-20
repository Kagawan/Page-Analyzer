package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
public final class JtepageGenerated {
	public static final String JTE_NAME = "layout/page.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,38,38,38,38,43,43,43,48};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, Content footer) {
		jteOutput.writeContent("\n<html lang=\"en\">\n<head>\n    <meta charset=\"utf-8\" />\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css\"\n          rel=\"stylesheet\"\n          integrity=\"sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We\"\n          crossorigin=\"anonymous\">\n    <title>Hello Hexlet!</title>\n</head>\n<body>\n<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js\"\n        integrity=\"sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz\"\n        crossorigin=\"anonymous\"></script>\n\n<nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n    <div class=\"collapse navbar-collapse\" id=\"navbarNavDropdown\">\n        <ul class=\"navbar-nav\">\n            <li class=\"nav-item active\">\n                <a class=\"nav-link\" href=\"/\">Главная</a>\n            </li>\n\n            <li class=\"nav-item\">\n                <a class=\"nav-link\" href=\"/\">Ещё одна ссыль в хедере</a>\n            </li>\n\n\n        </ul>\n    </div>\n</nav>\n\n<main>\n\n    ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n\n</main>\n<footer class=\"text-center text-lg-start bg-light text-muted\">\n    <div class=\"text-center p-4\" style=\"background-color: rgba(0, 0, 0, 0.05);\">\n        ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(footer);
		jteOutput.writeContent("\n    </div>\n</footer>\n</body>\n</html>\n");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.getOrDefault("content", null);
		Content footer = (Content)params.getOrDefault("footer", null);
		render(jteOutput, jteHtmlInterceptor, content, footer);
	}
}
