<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="row">
	<div class="col">
		<div class="card mt-4">
			<div class="card-header">
				<h3>
					<spring:message code='shop.header.products'/>
				</h3>
			</div>
			<ul class="list-group list-group-flush">
				<c:forEach var="item" items="${userProducts}" varStatus="loopCounter">
					<li class="list-group-item">
						<div class="row">
							<div class="col">${item.book.title}</div>
							<a class="btn btn-primary btn" href="${pagesBook}/${item.book.id}/edit">
								<spring:message code='form.button.edit'/>
							</a>
						</div>
					</li>
				</c:forEach>
			</ul>
			<a class="waves-effect waves-light btn right" href="${pagesBook}/add">
				<i class="btn btn-success btn">
					<spring:message code='form.button.add'/>
				</i>
			</a>
		</div>
	</div>
</div>
<div class="row">
	<div class="col">
		<div class="card mt-4">
			<div class="card-header">
				<h3>
					<spring:message code='shop.header.account'/>
				</h3>
			</div>
			<div class="m-2">
				<c:if test="${not empty error}">
					<div class="alert alert-warning" role="alert">${error}</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="alert alert-success" role="alert">${message}</div>
				</c:if>
			</div>

			<form:form method="POST" action="${pagesUserShop}" modelAttribute="userAccount">
				<form:input path="userId" type="hidden"/>
				<div class="row m-2 mt-4">

					<div class="form-group col-auto form-inline">
						<form:input path="withdrawalAmount" value="${userAccount.balance}" class="form-control" type="text" disabled="${readonly}"/>
						<form:errors path="withdrawalAmount" cssClass="red-text"/>
					</div>

					<div class="col-md-6 float-letf">
						<button type="submit" class="btn btn-primary mb-2  mr-1">
							<spring:message code='form.button.withdraw'/>
						</button>
					</div>

				</div>
			</form:form>

		</div>
	</div>
</div>

<div class="row">

	<div class="col-sm-6">
		<div class="card mt-4">
			<div class="card-header">
				<h3>
					<spring:message code='shop.header.sales'/>
				</h3>
			</div>
			<ul class="list-group list-group-flush">
				<c:forEach var="orderItem" items="${userSales}" varStatus="loopCounter">
					<li class="list-group-item">
						<div class="row">
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
			<div class="card-header">
				<h3>
					<spring:message code='shop.header.transactions'/>
				</h3>
			</div>
			<ul class="list-group list-group-flush">
				<c:forEach var="transaction" items="${userTransactions}" varStatus="loopCounter">
					<li class="list-group-item">
						<div class="row">
							<div class="col">
								<fmt:formatDate value="${transaction.created}" pattern="yyyy-MM-dd HH:mm"/>
							</div>
							<div class="col">${transaction.amount}</div>
						</div>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>

</div>