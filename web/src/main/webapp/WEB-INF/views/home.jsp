<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<h1 class="header">Self publishing books marketplace</h1>


<div class="row">
	<c:forEach var="item" items="${gridItems}" varStatus="loopCounter">
		<div class="col s4">

			<div class="card">
				<div class="card-image book-cover">
					<img src="${item.cover}">
					<h3>
						<a href="${pagesBook}/${item.id}" class="card-title">${item.title}</a>
					</h3>
				</div>
			</div>
		</div>
	</c:forEach>
</div>
