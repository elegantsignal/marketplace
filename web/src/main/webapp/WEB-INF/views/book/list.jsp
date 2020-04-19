<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h4 class="header">books</h4>
<table class="bordered highlight">
	<tbody>
		<tr>
			<th>id</th>
			<th>title</th>
			<th>price</th>
			<th>created</th>
			<th>updated</th>
			<th></th>
		</tr>

		<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
			<tr>
				<td>
					<c:out value="${item.id}" />
				</td>
				<td>
					<c:out value="${item.title}" />
				</td>
				<td>
					<c:out value="${item.price}" />
				</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${item.created}" />
				</td>
				<td>
					<fmt:formatDate pattern="yyyy-MM-dd" value="${item.updated}" />
				</td>
				<td class="right">
					<a class="btn-floating" href="${pagesBook}/${item.id}">
						<i class="material-icons">info</i>
					</a>
					<a class="btn-floating" href="${pagesBook}/${item.id}/edit">
						<i class="material-icons">edit</i>
					</a>
					<a class="btn-floating red" href="${pagesBook}/${item.id}/delete">
						<i class="material-icons">delete</i>
					</a>
				</td>
			</tr>
		</c:forEach>

	</tbody>
</table>



<div class="row">
	<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
		<div class="col s4">

			<div class="card">
				<div class="card-image book-cover">
					<img src="${item.cover}">
					<h3>
						<a href="${pagesBook}/${item.id}" class="card-title">${item.title}</a>
					</h3>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<a class="waves-effect waves-light btn right" href="${pagesBook}/add">
	<i class="material-icons">add</i>
</a>
