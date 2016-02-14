<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Football Team</title>
</head>
<h1>New Football Team</h1>
<body>
	<form action="../FootballTeamEx/rest/FootBallService/footballteams" method="post">
		<table>
			<tr>
				<td><label for="name">Name</label></td>
				<td><input type=text required name="name"  /> </td>
			</tr>
			<tr>
				<td><label for="city">City</label></td>
				<td><input type=text  name="city" /> </td>
			</tr>
			<tr>
				<td><label for="owner">Owner</label></td>
				<td><input type=text name="owner" /> </td>
			</tr>
			<tr>
				<td><label for="competition">Competition</label></td>
				<td><input type=text name="competition" />  </td>
			</tr>
			<tr>
				<td><label for="no_of_players">No of players</label></td>
				<td><input type=number name="no_of_players" />  </td>
			</tr>
			<tr> 
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</table>
		
		
	</form>
</body>
</html>