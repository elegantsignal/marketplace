<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>

<div class="container">
	<div class="row">
		<div class="col-3"></div>
		<div class="col-9">
			<div class="row">
				<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
					<div class="col-sm-4 d-flex align-items-stretch">
						<div class="card border-primary mb-3" style="max-width: 18rem;">
							<img class="card-img-top book-cover " src="${item.cover}" alt="Card image cap">
							<div class="card-header mt-auto"><a href="${pagesBook}/${item.id}" class="card-title">${item.title}</a><br>by ${item.author}</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<jspFragments:paging/>
		</div>
	</div>
</div>
