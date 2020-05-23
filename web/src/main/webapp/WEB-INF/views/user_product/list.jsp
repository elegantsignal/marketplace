<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="row">
	<div class="col">
		<div class="card mt-4">
			<div class="card-header"><h3>My Products</h3></div>
			<ul class="list-group list-group-flush">
				<c:forEach var="item" items="${userProducts}" varStatus="loopCounter">
					<li class="list-group-item">
						<div class="row">
							<div class="col">${item.book.title}</div>
							<a class="btn btn-primary btn" href="${pagesBook}/${item.book.id}/edit">edit</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<a class="waves-effect waves-light btn right" href="${pagesBook}/add">
				<i class="btn btn-success btn">add</i>
			</a>
		</div>
	</div>
</div>
<div class="row">
	<div class="col">
		<div class="card mt-4">
			<div class="card-header"><h3>My Account</h3></div>
			<form:form method="POST" action="${pagesUserShop}" modelAttribute="userAccount">
				<form:input path="userId" type="hidden"/>
				<div class="row m-2 mt-4">
					<div class="form-group col-auto form-inline">
						<label for="withdrawalAmount">Withdraw: </label>
						<form:input path="withdrawalAmount" value="${userAccount.balance}" class="form-control" type="text" disabled="${readonly}"/>
						<form:errors path="withdrawalAmount" cssClass="red-text"/>
					</div>

					<div class="col-md-6 float-letf">
						<button type="submit" class="btn btn-primary mb-2  mr-1">process</button>
					</div>

				</div>
			</form:form>
		</div>
	</div>
</div>

<div class="row">

	<div class="col-sm-6">
		<div class="card mt-4">
			<div class="card-header"><h3>My sales</h3></div>
			<ul class="list-group list-group-flush">
				<c:forEach var="orderItem" items="${userSales}" varStatus="loopCounter">
					<li class="list-group-item">
						<div class="row">
							<div class="col">${orderItem.productTitle}</div>
							<div class="col">${orderItem.productTitle}</div>
							<div class="float-right">${orderItem.amount}</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

	<div class="col-sm-6">
		<div class="card mt-4">
			<div class="card-header"><h3>My transactions</h3></div>
			<ul class="list-group list-group-flush">
				<c:forEach var="transaction" items="${userTransactions}" varStatus="loopCounter">
					<li class="list-group-item">
						<div class="row">
							<div class="col">${transaction.created}</div>
							<div class="col">${transaction.amount}</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</div>