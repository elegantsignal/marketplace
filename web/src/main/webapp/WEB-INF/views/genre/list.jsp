<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="mytaglib" uri="my-custom-tags-uri"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<h4 class="header">Genres</h4>
<table class="bordered highlight">
	<caption>List of genres</caption>
	<tbody>
		<tr>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesGenre}" column="id">id</mytaglib:sort-link></th>
			<th scope="col"><mytaglib:sort-link pageUrl="${pagesGenre}" column="name">genre</mytaglib:sort-link></th>
			<th scope="col"></th>
		</tr>
		<c:forEach var="genre" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td>
					<c:out value="${genre.id}" />
				</td>
				<td>
					<c:out value="${genre.name}" />
				</td>
				<td class="right">
					<a class="btn-floating" href="${pagesGenre}/${genre.id}">
						<i class="material-icons" aria-hidden="true">info</i>
					</a>
					<a class="btn-floating" href="${pagesGenre}/${genre.id}/edit">
						<i class="material-icons" aria-hidden="true">edit</i>
					</a>
					<a class="btn-floating red" href="${pagesGenre}/${genre.id}/delete">
						<i class="material-icons" aria-hidden="true">delete</i>
					</a>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<jspFragments:paging />

<a class="waves-effect waves-light btn right" href="${pagesGenre}/add">
	<i class="material-icons" aria-hidden="true">add</i>
</a>
