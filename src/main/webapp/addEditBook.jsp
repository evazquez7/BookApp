<%-- 
    Document   : addEditBook
    Created on : Nov 14, 2016, 4:11:04 PM
    Author     : Emilio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add or Edit Book</title>
    </head>
    <body>

        <h1>Add or Edit Book</h1>

        <form method="POST" action="BookController">
            <c:choose>
                <c:when test="${not empty book}">

                    <label for="id">ID</label>
                    <input type="text" name="bookId" value="${book.bookId}" readonly/><br>

                </c:when>
            </c:choose>
            <label for="name">Name</label>
            <input type="text" name="bookTitle" value="${book.title}"> <br>

            <label for="name">ISBN</label>
            <input type="text" name="isbn" value="${book.isbn}">  <br>
            <label for="author">Author</label>
            <select id="authorDropDown" name="authorId">
                <c:choose>
                    <c:when test="${not empty book.authorId}">
                        <option value="">None</option>
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.authorId}" name="authorId">${author.authorName}</option>
                        </c:forEach>

                    </c:when>
                    <c:otherwise>
                        <option value="">None</option>
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.authorId}" name="authorId">${author.authorName}</option>
                        </c:forEach>        
                    </c:otherwise>

                </c:choose>
            </select>
            <c:choose>
                <c:when test="${empty book}">
                    <input type="submit" value="Add" name="action">
                </c:when>
                <c:otherwise>
                    <input type="submit" value="Update" name="action">
                </c:otherwise>
            </c:choose>



            <input type="submit" value="Cancel" name="action">  

        </form>
    </body>
</html>