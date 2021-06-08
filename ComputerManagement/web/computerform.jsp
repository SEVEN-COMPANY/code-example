<%-- 
    Document   : computerform
    Created on : Jun 8, 2021, 11:21:46 PM
    Author     : heaty566
--%>

<%@page import="dtos.RoomDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	ArrayList<RoomDTO> lstRooms = new ArrayList();
	lstRooms = (ArrayList<RoomDTO>) request.getAttribute("listRooms");
	System.out.println(lstRooms);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
	</head>
	<body>
		<h1>Add New Computer Information</h1>
		<form action="" method="POST">
			<table>
				<tr>
					<td>ID</td>
					<td>: <input type="text" name="txtID" value=""> </td>
				</tr>
				<tr>
					<td>CPU</td>
					<td>: <input type="text" name="txtCPU" value=""> </td>
				</tr>
				<tr>
					<td>Hard Disk</td>
					<td>: <input type="text" name="txtHardDisk" value=""> </td>
				</tr>
				<tr>
					<td>Ram</td>
					<td>: <input type="text" name="txtRAM" value=""> </td>
				</tr>
				<tr>
					<td>VGA</td>
					<td>: <input type="text" name="txtVGA" value=""> </td>
				</tr>
				<tr>
					<td>Monitor</td>
					<td>: <input type="text" name="txtMonitor" value=""> </td>
				</tr>
				<tr>
					<td>Room</td>
					<td>: <select name="cboRoom">
							<% for (int i = 0; i < lstRooms.size(); i++) { %>
							<option><%= lstRooms.get(i).getId() %>-<%= lstRooms.get(i).getName() %></option>
							<% }%>
						</select>
					</td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Create"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>
