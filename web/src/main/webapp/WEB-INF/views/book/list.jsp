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
				<div class="card-image">
					<img src="https://picsum.photos/200/300?random=${item.id}">
					<span class="card-title">Card Title</span>
				</div>
				<div class="card-content">
					<p>I am a very simple card. I am good at containing small bits of information.
						I am convenient because I require little markup to use effectively.</p>
				</div>
				<div class="card-action">
					<a href="#">This is a link</a>
				</div>
			</div>
		</div>
	</c:forEach>
</div>

<a class="waves-effect waves-light btn right" href="${pagesBook}/add">
	<i class="material-icons">add</i>
</a>
