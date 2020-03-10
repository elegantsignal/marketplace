<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">books</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>

		<c:forEach var="book" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td>
					<c:out value="${book.id}" />
				</td>
				<td>
					<c:out value="${book.title}" />
				</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${book.created}" />
				</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${book.updated}" />
				</td>
				<td class="right">
					<a class="btn-floating" href="${pagesBook}/${book.id}">
						<i class="material-icons">info</i>
					</a>
					<a class="btn-floating" href="${pagesBook}/${book.id}/edit">
						<i class="material-icons">edit</i>
					</a>
					<a class="btn-floating red" href="${pagesBook}/${book.id}/delete">
						<i class="material-icons">delete</i>
					</a>
				</td>
			</tr>
		</c:forEach>
		
	</tbody>
</table>
<a class="waves-effect waves-light btn right" href="${pagesBook}/add">
	<i class="material-icons">add</i>
</a>