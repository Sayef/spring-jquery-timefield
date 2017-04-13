<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="html" tagdir="../resources/tag/html" %>
<%@ taglib prefix="form" tagdir="../resources/tag/form" %>

<form:timeField id="crimeTime" path="crimeTimeText" label="crimeTime"
                maxlength="100" required="true" data-msg-required='${requiredMsg}'
                cssClass="comparable" readonly="false" gridClass="col-3"
                data-msg-pattern='${timeMsg}' timeFormat='${timeFormat}'
                pattern="^([0-9]|0[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9])$"/>
<%-- use this pattern for 12-hours format --%>  <%--pattern="^(0[1-9]|1[0-2]):[0-5][0-9] (AM|PM)$"--%>

<input id="saveBtn" type="submit" value="Submit" form="TimeFieldForm"/>