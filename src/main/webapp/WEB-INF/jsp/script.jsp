<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Hello page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/main.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/script.css">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8=" crossorigin="anonymous"></script>
</head>
<body>

<%@include file="pieces/header.jsp" %>

<main class="main-wrapper">
    <section class="left-side">
        <%@include file="pieces/script.jsp"%>
    </section>
    <aside class="right-side">
        ${pageContext.request.getAttribute("currencyRatesHolder").getHtmlView()}
    </aside>
</main>

<%@include file="pieces/footer.jsp" %>
</body>
<script src="${pageContext.request.contextPath}/js/script.js"></script>
</html>
