<%--
  Created by IntelliJ IDEA.
  User: sayef
  Date: 4/11/17
  Time: 3:30 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" tagdir="../resources/tag/form" %>
<html>
<head>
    <title>Title</title>
    <script src="resources/js/jquery-3.2.0.js" type="text/javascript"/>
    <script src="resources/js/script.js" type="text/javascript"/>
</head>
<body>
    <c:url var="action" value="/timeField/create.html"/>
    <form:form id="TimeFieldForm" modelAttribute="timeField" method="post" cssClass="" formUrl="${action}">
        <jsp:include page="timeFieldForm.jsp"/>
    </form:form>
</body>
</html>
