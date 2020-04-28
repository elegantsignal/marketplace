<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>



<div class="card flex-md-row mb-4 box-shadow h-md-250">
	<div class="row">
		<div class="col-md-4">
			<img class="w-100" src="/${formModel.cover}" alt="Card image cap">
		</div>
		<div class="card-body d-flex flex-column align-items-start">
			<h3 class="mb-0">
				$${formModel.price} - ${formModel.title} by
			</h3>
			<p class="card-text mb-auto">This is a wider card with supporting text below as a natural lead-in to additional content.</p>
		</div>
	</div>
</div>
