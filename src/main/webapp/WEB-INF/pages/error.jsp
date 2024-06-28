<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
table td{
vertical-align:top;
border:solid 1px #888;
padding:10px;
}
table{
border: 1px solid #000;
}
 div#custom-error {
      	width: 100%;
      	height:500px;
      	overflow: auto;
      }
</style>
<div class="row">
<fieldset>
<legend>Page d'erreur</legend>
<div id ="custom-error" >
<%-- <%Enumeration<String> enums =  request.getAttributeNames();
while(enums.hasMoreElements())
{
	Object o = enums.nextElement();
	%><%=o.toString() %><br><%
}
%> --%>
<%-- <%= request.getAttribute("status")%><br>
<%= request.getAttribute("timestamp")%><br>
<%= request.getAttribute("error")%><br>
<%= request.getAttribute("path")%><br>
<%= request.getAttribute("message")%><br>
<%= request.getAttribute("exception")%><br>
<%= request.getAttribute("trace")%><br> --%>
    <table>
        <tr>
            <td>Date</td>
            <td><fmt:formatDate value="${timestamp}"  dateStyle="full"/></td>
        </tr>
        <tr>
            <td>adresse</td>
            <td>${addresse}</td>
        </tr>
        <tr>
            <td>Error</td>
            <td>${error}</td>
        </tr>
        <tr>
            <td>Status</td>
            <td>${status}</td>
        </tr>
        <tr>
            <td>Message</td>
            <td>${message}</td>
        </tr>
        <tr>
            <td>Exception</td>
            <td>${exception}</td>
        </tr>
        <tr>
            <td>Trace</td>
            <td>
                <pre>${trace}</pre>
            </td>
        </tr>
    </table>
    </div>
    </fieldset>
    </div>