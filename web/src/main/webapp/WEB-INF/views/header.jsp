<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header>
	<nav>
		<div class="nav-wrapper container">
			<div class="row">
				<ul class=" col s6 hide-on-med-and-down">
					<li><a href="/">home</a></li>
					<li><a href="${pagesBook}">books</a></li>
					<li><a href="${pagesUser}">users</a></li>
					<li><a href="${pagesGenre}">genre</a></li>
					<li><a href="${pagesCar}">cars</a></li>
					<li><a href="?lang=ru">RU</a></li>
					<li><a href="?lang=en">EN</a></li>
					<sec:authorize access="!isAnonymous()">
						<a class="right" href="${contextPath}/execute_logout" title="logout">
							<i class="material-icons">arrow_forward</i>
						</a>
					</sec:authorize>

				</ul>

				<form>
					<div class="col s6 input-field">
						<input id="search" type="search" required> <label class="label-icon" for="search"> <i class="material-icons">search</i></label> <i class="material-icons">close</i>
					</div>
				</form>
			</div>
		</div>
	</nav>
</header>
