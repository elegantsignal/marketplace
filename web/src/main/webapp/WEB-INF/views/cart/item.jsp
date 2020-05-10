<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="card mt-4">
	<div class="card-header">
		<h3>Shopping cart</h3>
	</div>
	<ul class="list-group list-group-flush">
		<c:forEach var="item" items="${cartItems}" varStatus="loopCounter">
			<li class="list-group-item">
				<div class="row">
					<div class="col-sm">${item.productTitle}</div>
					<div class="col-sm-2">$${item.amount}</div>
					<div class="col-sm-1">
						<a href="${pagesCart}/${item.id}/delete">
							<i class="fas fa-trash"></i>
						</a>
					</div>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div class="card-body">
		<div class=" float-right">
			<a href="${pagesCart}/checkout" class="btn btn-info" role="button">Checkout</a>
		</div>
	</div>
</div>

<div class="card mt-4">
	<div class="card-header">
		<h3>My purchase</h3>
	</div>
	<ul class="list-group list-group-flush">
		<c:forEach var="order" items="${user_orders}" varStatus="loopCounter">
			<li class="list-group-item">
				<div class="row">
					<div class="col-sm"><h4>${order.created}</h4>
						<ul>
							<c:forEach var="orderItem" items="${order.orderItems}" varStatus="loopCounter">
								<li>
									<div class="row">
										<div class="col-sm">${orderItem.productTitle}</div>
										<div class="col-sm-2">${orderItem.amount}</div>
									</div>
								</li>
							</c:forEach>
						</ul>
					</div>
			</li>
		</c:forEach>
	</ul>
</div>
