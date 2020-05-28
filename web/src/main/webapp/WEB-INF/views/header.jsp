<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<header>
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="/">Self publishing books marketplace</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<!--				<li class="nav-item"><a class="nav-link" href="${pagesBook}">books</a></li>-->
				<!--				<li class="nav-item"><a class="nav-link" href="${pagesUser}">users</a></li>-->
				<!--				<li class="nav-item"><a class="nav-link" href="${pagesGenre}">genre</a></li>-->
				<!--				<li class="nav-item"><a class="nav-link" href="${pagesCar}">cars</a></li>-->
				<!--				<li class="nav-item"><a class="nav-link" href="?lang=ru">RU</a></li>-->
				<!--				<li class="nav-item"><a class="nav-link" href="?lang=en">EN</a></li>-->

				<sec:authorize access="isAuthenticated()">
					<li class="nav-item dropdown float-right">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<sec:authentication property="name" />
						</a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="${pagesUserCart}">Shopping cart</a>
							<a class="dropdown-item" href="${pagesUserShop}">My shop</a>
							<a class="dropdown-item" href="${logout}">logout</a>
						</div>
					</li>
				</sec:authorize>
				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="${login}">login</a></li>
				</sec:authorize>
			</ul>

		</div>

		<div class="container">
			<div class="dropdown">
				<form class="form-inline my-2 my-lg-0">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2">
							<div class="input-group-prepend">
								<div class="input-group-text"><i class="fas fa-search"></i></div>
							</div>
							<input id="search" class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
						</div>
					</div>
				</form>
				<div id="result" class="dropdown-menu" aria-labelledby="dropdownMenuButton">
					<ul class="list-group"></ul>
				</div>
			</div>
		</div>
	</nav>
</header>
