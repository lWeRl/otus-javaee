<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/news.css">
</head>
<body>

<%@include file="pieces/header.jsp" %>

<main class="main-wrapper">
    <section class="left-side">
        ${pageContext.request.getAttribute("newsHolder").getHtmlView()}
    </section>
    <aside class="right-side">
        ${pageContext.request.getAttribute("currencyRatesHolder").getHtmlView()}
    </aside>
</main>

<%@include file="pieces/footer.jsp" %>
</body>
</html>
