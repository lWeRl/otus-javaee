<header class="header">
    <span>Проект курса JavaEE на обучающем портале <a href="http:\\otus.ru">OTUS</a></span>
    <div><img src="https://otus.ru/media/18/fa/18fa32eefb974c638e16be5cb2e7bd11.png"></div>
</header>
<nav class="nav">
    <a class="nav-element <%=request.getRequestURI().equals("/jsp/main") ? "selected" : ""%>"
       href="${pageContext.request.contextPath}/jsp/main">
        Главная
    </a>
    <a class="nav-element <%=request.getRequestURI().equals("/jsp/login") ? "selected" : ""%>"
       href="${pageContext.request.contextPath}/jsp/login">Логин</a>
    <a class="nav-element <%=request.getRequestURI().equals("/jsp/news") ? "selected" : ""%>"
       href="${pageContext.request.contextPath}/jsp/news">Новости</a>
    <a class="nav-element <%=request.getRequestURI().equals("/jsp/script") ? "selected" : ""%>"
       href="${pageContext.request.contextPath}/jsp/script">Скрипт</a>
    <a class="nav-element <%=request.getRequestURI().equals("/jsp/other") ? "selected" : ""%>"
       href="${pageContext.request.contextPath}/jsp/other">Другое</a>
    <form action="http://www.google.com/search" class="search-form" method="get" name="searchform" target="_blank">
        <input class="form-controls search" name="q" placeholder="Поиск ..." required="required" type="text">
        <button class="button" type="submit">Поиск</button>
    </form>
</nav>