<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<div class="card flex-md-row mb-4 box-shadow h-md-250">
	<div class="row">
		<div class="col-md-4">
			<img class="w-100" src="/${formModel.cover}" alt="Card image cap">
		</div>
		<div class="col-md-8 card-body d-flex flex-column align-items-start">
			<h1>${formModel.title}</h1>by<a href="${contextPath}/?authorId=${formModel.authorId}"><h2>${formModel.author}</h2></a>
			<p class="card-text mb-auto">${formModel.description}</p>
			<div class="">
				<h3>
					<a href="${pagesUserCart}/${formModel.id}/add">
						<i class="fas fa-cart-plus"> $${formModel.price} </i>
					</a>
				</h3>
			</div>
		</div>
	</div>
</div>
