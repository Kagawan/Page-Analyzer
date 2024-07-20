@import gg.jte.Content

@param Content content

@template.layout.page(
content  = @`
    <h1>Заголовок моего локал-сайта!</h1>
    <h2>Подзаголовок! Если ты читаешь - пожалуйста, пользуйся</h2>

    <h4>Тут ты увидишь:</h4>
    <ul>
        <li><a href="/">Главную страницу</a></li>
        <li><a href="/">Список всех курсов(из прошлого курса)</a></li>
        <li><a href="/">Список всех сайтов</a></li>
        <li>А вообще будем анализировать страницы других сайтов</li>

    </ul>
`
,
footer = @`
    <div class="footer">
        <a href="https://github.com/Kagawan">профиль на GitHub</a>
    </div>
`
) 