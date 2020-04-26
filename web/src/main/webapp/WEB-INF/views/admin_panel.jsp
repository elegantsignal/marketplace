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
				</ul>

				<div class="right">
					<ul>
						<li>
							<sec:authorize access="isAuthenticated()">
								<a href="/user">
									<sec:authentication property="name"/>
								</a>
							</sec:authorize>
						</li>
						<li>
							<sec:authorize access="!isAnonymous()">
								<a href="${contextPath}/execute_logout" title="logout">logout</a>
							</sec:authorize>
						</li>
					</ul>
				</div>

			</div>
		</div>
	</nav>
</header>
