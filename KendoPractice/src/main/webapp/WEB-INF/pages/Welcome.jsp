<%@ page isELIgnored="false"%>

<%@page session="true"%>
<html>
<head>
    <title>Title</title>

</head>
<body>

<c:if test="${pageContext.request.userPrincipal.name != null}">
    <h2>Welcome : ${pageContext.request.userPrincipal.name}</h2>
    <p>Click <a href="./index">here</a> to see a CoconetDemo Application.</p>

    <a href ="./logout" /> Logout</a>
</c:if>
</body>
</html>
