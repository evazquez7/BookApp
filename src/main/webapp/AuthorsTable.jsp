<%-- 
    Document   : AuthorsTable
    Created on : Sep 19, 2016, 6:24:05 PM
    Author     : Emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
   Object obj = request.getAttribute("authors");
   if(obj == null) {
       response.sendRedirect("AuthorController");
   }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Authors</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="index.css">
    </head>
    <body>
        <div class="container">
        <h1>List of Authors</h1>
        <table class="table table-hover table-responsive table-bordered">
            <thead>
            <th>Author Id</th>
                <th>Author Name</th>
                <th>Date</th>
            </thead>
            
            <c:forEach var="author" items="${authors}">
                <tr>
                    <td>${author.authorId}</td>
                    <td>${author.authorName}</td>
                    <td>${author.dateAdded}</td>
                </tr>
            </c:forEach>
            
        </table>
        <a href="index.jsp">Home</a>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   
    </body>
</html>
