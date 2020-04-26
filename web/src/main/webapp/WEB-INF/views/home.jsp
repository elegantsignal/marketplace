<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="container">
	<div class="row">
		<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
			<div class="col-sm-4 d-flex align-items-stretch">


				<div class="card border-primary mb-3" style="max-width: 18rem;">
					<img class="card-img-top book-cover" src="${item.cover}" alt="Card image cap">

					<div class="card-header"><a href=" ${pagesBook}/${item.id}" class="card-title">${item.title}</a></div>

				</div>






			</div>
		</c:forEach>
	</div>
</div>
