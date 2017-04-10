<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ attribute name="modelAttribute" required="true" rtexprvalue="true" %>
<%@ attribute name="id" required="true" rtexprvalue="true" %>
<%@ attribute name="formUrl" required="true" rtexprvalue="true" %>
<%@ attribute name="method" required="true" rtexprvalue="true" %>
<%@ attribute name="cssClass" required="false" rtexprvalue="true" %>

<form:form autocomplete="off" modelAttribute="${modelAttribute}" id="${id}" name="${id}" action="${formUrl}" method="${method}" htmlEscape="true" cssClass="${cssClass}">
    <jsp:doBody/>
</form:form>