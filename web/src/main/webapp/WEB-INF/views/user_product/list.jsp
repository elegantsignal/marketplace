<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="card mt-4">
	<div class="card-header"><h3>My Products</h3></div>
	<ul class="list-group list-group-flush">
		<c:forEach var="item" items="${userProducts}" varStatus="loopCounter">
			<li class="list-group-item">
				<div class="row">
					<div class="col-sm-11">${item.book.title}</div>
					<div class="col-sm">
						<a class="btn btn-primary btn" href="${pagesBook}/${item.book.id}/edit">edit</a>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>

	<a class="waves-effect waves-light btn right" href="${pagesBook}/add">
		<i class="material-icons">add</i>
	</a>

</div>

<div class="card mt-4">
	<div class="card-header"><h3>My sales</h3></div>
	<ul class="list-group list-group-flush">
		<c:forEach var="orderItem" items="${userSales}" varStatus="loopCounter">
			<li class="list-group-item">
				<div class="row">
					<div class="col-sm">${orderItem.productTitle}</div>
					<div class="col-sm">${orderItem.amount}</div>
				</div>
			</li>
		</c:forEach>
	</ul>
</div>