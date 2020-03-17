<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h4 class="header">Users</h4>
<table class="bordered highlight">
	<caption>List of users</caption>
	<tbody>
		<tr>
			<th scope="col">id</th>
			<th scope="col">username</th>
			<th scope="col">email</th>
			<th scope="col">password</th>
			<th scope="col"></th>
		</tr>
		<c:forEach var="userAccount" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td>
					<c:out value="${userAccount.id}" />
				</td>
				<td>
					<c:out value="${userAccount.name}" />
				</td>
				<td>
					<c:out value="${userAccount.email}" />
				</td>
				<td>
					<c:out value="${userAccount.password}" />
				</td>
				<td class="right">
					<a class="btn-floating" href="${pagesUserAccount}/${userAccount.id}"><i class="material-icons" aria-hidden="true">info</i></a>
					<a class="btn-floating" href="${pagesUserAccount}/${userAccount.id}/edit"><i class="material-icons" aria-hidden="true">edit</i></a>
					<a class="btn-floating red" href="${pagesUserAccount}/${userAccount.id}/delete"><i class="material-icons" aria-hidden="true">delete</i></a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesUser}/add"><i class="material-icons" aria-hidden="true">add</i></a>
