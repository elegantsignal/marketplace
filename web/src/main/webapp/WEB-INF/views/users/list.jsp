<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Users</h4>
<table class="bordered highlight">
	<caption>List of users</caption>
	<tbody>
		<tr>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesUser}" column="id">id</mytaglib:sort-link></th>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesUser}" column="username">username</mytaglib:sort-link></th>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesUser}" column="email">email</mytaglib:sort-link></th>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesUser}" column="password">password</mytaglib:sort-link></th>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesUser}" column="created">created</mytaglib:sort-link></th>
			<th scope="col"></th>
		</tr>
		<c:forEach var="user" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td>
					<c:out value="${user.id}" />
				</td>
				<td>
					<c:out value="${user.name}" />
				</td>
				<td>
					<c:out value="${user.email}" />
				</td>
				<td>
					<c:out value="${user.password}" />
				</td>
				<td>
					<c:out value="${user.created}" />
				</td>
				<td class="right">
					<a class="btn-floating" href="${pagesUser}/${user.id}">
						<i class="material-icons" aria-hidden="true">info</i>
					</a>
					<a class="btn-floating" href="${pagesUser}/${user.id}/edit">
						<i class="material-icons" aria-hidden="true">edit</i>
					</a>
					<a class="btn-floating red" href="${pagesUser}/${user.id}/delete">
						<i class="material-icons" aria-hidden="true">delete</i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />

<a class="waves-effect waves-light btn right" href="${pagesUser}/add">
	<i class="material-icons" aria-hidden="true">add</i>
</a>
