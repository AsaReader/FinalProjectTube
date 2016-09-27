<%@ page import="java.util.GregorianCalendar, java.util.Calendar"%>
<%
	GregorianCalendar currentDate = new GregorianCalendar();
	int currentYear = currentDate.get(Calendar.YEAR);
%>
<p>
	&copy; Copyright
	<%=currentYear%>
	Georgi Chochov, Hristo Angelov &amp; Kaloyan Tsvetkov
</p>
</body>
</html>