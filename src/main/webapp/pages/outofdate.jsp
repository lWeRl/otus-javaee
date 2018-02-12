
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Browser out of date</title>
</head>
<body>
    You browser <%=request.getAttribute("BrowserName")%> out of date in version <%=request.getAttribute("BrowserVersion")%>!
</body>
</html>
