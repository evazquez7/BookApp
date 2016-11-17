<%-- 
    Document   : BookTable
    Created on : Nov 7, 2016, 5:29:03 PM
    Author     : Emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
   Object obj = request.getAttribute("books");
   if(obj == null) {
       response.sendRedirect("BookController");
   }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Books</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="index.css">
    </head>
    <body>
        <div class="container">
        <h1>List of Books</h1>
        
        <form method="POST" action="BookController?action=addEditDelete">
            <input type="submit" value="AddEdit" name="submit">
            <input type="submit" value="Delete" name="submit">
            <table class="table table-hover table-responsive table-bordered">
                <thead>
                <th>Book Id</th>
                    <th>Book Title</th>
                    <th>Isbn</th>
                    <th>Author name</th>
                </thead>
            
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td><input type="checkbox" name="bookId" value="${book.bookId}"></td>
                        <td>${book.title}</td>
                        <td>${book.isbn}</td>
                        <td>${book.authorId.authorName}</td>
                    </tr>
                </c:forEach> 
            </table>
        </form>
        <a href="index.jsp">Home</a>
        </div>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>   
    </body>
</html>