<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-between pt-2 pb-3">
		<a class="navbar-brand" href="/">marketplace for self-publishers</a>

		<div class="container">
			<div class="dropdown w-100">
				<form class="form-inline">
					<div class="input-group w-100">
						<div class="input-group-prepend">
							<div class="input-group-text"><i class="fas fa-search"></i></div>
						</div>
						<input id="search" class="form-control mr-sm-6" type="search" placeholder="Search" aria-label="Search">
					</div>
				</form>
				<div id="result" class="dropdown-menu" aria-labelledby="dropdownMenuButton">
				</div>
			</div>
		</div>


		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav">
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item dropdown float-right">
						<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<sec:authentication property="name"/>
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

</nav>
