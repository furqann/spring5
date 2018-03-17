<%-- 
    Document   : index
    Created on : Mar 17, 2018, 4:46:24 PM
    Author     : Furqan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Api Home Page</title>
    </head>
    <body>
        <h1>Rest Api</h1>
        <p>
            ${pageContext.request.servletContext.contextPath}/domain
        </p>
    </body>
</html>
