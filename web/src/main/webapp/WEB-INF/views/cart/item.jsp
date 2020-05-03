<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h2>Shopping cart</h2>

<div class="container">
	<c:forEach var="item" items="${cartItems}" varStatus="loopCounter">
		<div class="row">
			<div class="col-sm">${item.productTitle}</div>
			<div class="col-sm-2">$${item.amount}</div>
			<div class="col-sm-1">
				<a href="${pagesCart}/${item.id}/delete">
					<i class="fas fa-trash"></i>
				</a>
			</div>
		</div>
	</c:forEach>

	<div class="row float-right">
		<a href="${pagesCart}/checkout" class="btn btn-info" role="button">Checkout</a>
	</div>
</div>

<h2>Orders</h2>
<c:forEach var="item" items="${ordersItems}" varStatus="loopCounter">
	<div class="row">
		<div class="col-sm">${item.productTitle}</div>
		<div class="col-sm-2">$${item.amount}</div>
	</div>
</c:forEach>