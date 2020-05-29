<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">
	<div class="row">

		<div class="col-3">
			<div class="form-check">
				<form method='GET' action="/">
					<c:forEach var="genre" items="${genreList}" varStatus="loopCounter">
						<div class="form-check">
							<input class="form-check-input" type="checkbox" id="genre-${genre.id}" name="genre[]" value="${genre.name}">
							<label class="form-check-label" for="genre-${genre.id}">${genre.name}</label>
						</div>
					</c:forEach>
					<input class="btn btn-primary" type="submit">
				</form>
			</div>

		</div>


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
