<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello page</title>
    <link rel="stylesheet" type="text/css" href="/css/main.css">
</head>
<body>

<%@include file="pieces/header.jsp" %>

<main class="main-wrapper">
    <section class="left-side">
        <%@include file="pieces/login.jsp"%>
    </section>
    <aside class="right-side">
        ${pageContext.request.getAttribute("currencyRatesHolder").getHtmlView()}
    </aside>
</main>

<%@include file="pieces/footer.jsp" %>
</body>
</html>
