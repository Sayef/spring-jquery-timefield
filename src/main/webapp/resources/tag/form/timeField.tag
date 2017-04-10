<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="html" uri="/WEB-INF/inputField.tld" %>
<%@ tag dynamic-attributes="dynAttrs" %>
<%@ attribute name="id" required="false" rtexprvalue="true" %>
<%@ attribute name="path" required="true" rtexprvalue="true" %>
<%@ attribute name="label" required="true" rtexprvalue="true" %>
<%@ attribute name="inline" required="false" rtexprvalue="true" type="java.lang.Boolean" %>
<%@ attribute name="cssClass" required="false" rtexprvalue="true" %>
<%@ attribute name="wrapperId" required="false" rtexprvalue="true" %>
<%@ attribute name="wrapperCssClass" required="false" rtexprvalue="true" %>
<%@ attribute name="wrapperCssStyle" required="false" rtexprvalue="true" %>
<%@ attribute name="gridClass" required="false" rtexprvalue="true" %>
<%@ attribute name="timeFormat" required="true" rtexprvalue="true" %>

<c:set var="inline" value="${(inline == null) ? false : inline}" />

<spring:bind path="${path}" htmlEscape="true">
    <div <c:if test="${not empty wrapperId}">id="${wrapperId}"</c:if> class="form-group <c:if test="${not empty gridClass}">${gridClass}</c:if>  <c:if test="${inline}">inline</c:if> <c:if test="${not empty wrapperCssClass}">${wrapperCssClass}</c:if>" <c:if test="${not empty wrapperCssStyle}">style="${wrapperCssStyle}"</c:if>>
        <label for="${id}" class="data-label">
            <spring:message code='${label}'/>:
        </label>


        <style>
            #unified-time-inputs.time-input-group { width: 100%; }
            #unified-time-inputs.time-input-group input { width: 28% !important; }
            /*#unified-time-inputs.time-input-group input:last-of-type { border-left: 0;  width: 35% !important;}*/
        </style>

        &nbsp; &nbsp; &nbsp; <spring:message code='Hour'/> &nbsp; &nbsp; &nbsp; &nbsp; <spring:message code='Min'/> <c:if test="${timeFormat == '12'}"> &nbsp; &nbsp; &nbsp; &nbsp; <spring:message code='AMPM'/> </c:if>

        <div class="time-input-group" id="unified-time-inputs">
            <input type="text" maxlength="2" pattern="^([0-9]|0[0-9]|1[0-9]|2[0-3])$" name="${id}HourText" class="time-input-control" id="${id}HourText"  placeholder="<spring:message code='Hour'/>" />
            :
            <input type="text" maxlength="2" pattern="^([0-9]|[0-5][0-9])$" type="text" name="${id}MinText" class="time-input-control" id="${id}MinText" placeholder="<spring:message code='Min'/>" />
            <c:if test="${timeFormat == '12'}">
                <input maxlength="2" pattern="^(am|pm|AM|PM)$" type="text" name="${id}AMPMText" class="time-input-control" id="${id}AMPMText" placeholder="<spring:message code='AMPM'/>" />
            </c:if>
            <html:input type="hidden" id="${id}" style="visibility: hidden; height: 0;" name="${id}" path="${path}" dynAttrs="${dynAttrs}" cssClass="${cssClass} time-input" cssErrorClass="${cssClass} error" autocomplete="off" htmlEscape="true"/>
            <form:errors id="${id}-error" path="${path}" cssClass="error" element="div"/>
        </div>

            <%--<div class="data-value" style="padding:0;">
                <html:input type="text" pattern="^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$" id="${id}" style="visibility: hidden; height: 0;" name="${id}" path="${path}" dynAttrs="${dynAttrs}" cssClass="${cssClass} time-input" cssErrorClass="${cssClass} error" autocomplete="off" htmlEscape="true"/>
                <form:errors id="${id}-error" path="${path}" cssClass="error" element="div"/>
            </div>--%>


        <div class="data-value" style="padding:0; display:none;">
            <select id="${id}Hour" style="width: 50px;"><option value="Hour" disabled selected><spring:message code='Hour'/></option></select>
            <select id="${id}Min" style="width: 50px; inline: true;"><option value="Minute" disabled selected><spring:message code='Min'/></option></select>
            <c:if test="${timeFormat == '12'}">
                <select id="${id}AMPM" style="width: 50px; inline: true;"><option value="AMPM" disabled selected><spring:message code='AMPM'/></option></select>
            </c:if>
        </div>
    </div>
</spring:bind>