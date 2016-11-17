<%-- 
    Document   : addEdit
    Created on : Oct 12, 2016, 5:42:26 PM
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
        <title>Add or Edit Author</title>
    </head>
    <body>

        <h1>Add or Edit Author</h1>

        <form method="POST" action="AuthorController">
            <c:choose>
                <c:when test="${not empty author}">

                    <label for="id">ID</label>
                    <input type="text" name="authorId" value="${author.authorId}" readonly/>

                </c:when>
            </c:choose>
            <label for="name">Name</label>
            <input type="text" name="authorName" value="${author.authorName}"> 

            <c:choose>
                <c:when test="${not empty author}">

                    <label for="name">Date Added</label>
                    <input type="text" name="dateAdded" value="${author.dateAdded}" readonly/>  

                </c:when>

            </c:choose>
            <c:choose>
                <c:when test="${not empty author.bookCollection}">

                    <c:forEach var="book" items="${author.bookCollection}">
                        <select id="bookDropDown" name="bookId">
                            <label for="bookTitle">Book Title</label>
                            <option value="${book.bookId}">${book.title}</option>
                        </select>
                    </c:forEach>
                </c:when>

            </c:choose>

            <c:choose>
                <c:when test="${empty author}">
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
