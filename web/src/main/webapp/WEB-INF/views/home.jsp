<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="jspFragments" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container" xmlns:c="http://www.w3.org/1999/html">

	<div class="row">

		<div class="col-3">
			<div class="card">
				<div class="card-header"><spring:message code="page.home.filterHeader"/>:</div>
				<div class="card-body">
					<div class="form-check">
						<form method='GET' action="/">
							<spring:message code="page.home.filterGenre"/>
							<ul class="list-group list-group-flush">
								<c:forEach var="genre" items="${genreList}" varStatus="loopCounter">
									<li class="list-group-item">
										<div class="form-check">
											<input class="form-check-input" type="checkbox" id="genre-${genre.id}" name="genre[]" value="${genre.name}">
											<label class="form-check-label" for="genre-${genre.id}">${genre.name}</label>
										</div>
									</li>
								</c:forEach>
							</ul>
							<button type="submit" class="btn btn-primary mt-2"><spring:message code="page.home.filterButton"/></button>
						</form>
					</div>
				</div>
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
