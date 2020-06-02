<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="row">
	<div class="container mt-4">
		<c:if test="${not empty error}">
			<div class="alert alert-danger" role="alert">${error}</div>
		</c:if>
		<c:if test="${not empty msg}">
			<div class="alert alert-success" role="alert">${msg}</div>
		</c:if>
	</div>

	<div class="col-6">
		<div class="container">
			<form:form method='POST' action="singup"  modelAttribute="formModel"
			           oninput='password.setCustomValidity(passwordValidation(password.value , repeat.value))'>
				<form:input path="id" type="hidden"/>

				<div class="form-group">
					<label class="sr-only" for="name">name</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">Aa</div>
						</div>
						<form:input path="name" type="text" class="form-control" placeholder="name" aria-describedby="nameHelp"/>
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="email">Email address</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">@</div>
						</div>
						<form:input path="email" type='email' class="form-control" placeholder="email" aria-describedby="emailHelp"/>
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="password">password</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">✱</div>
						</div>
						<input type='password' class="form-control" name='password' placeholder="password"/>
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="password">repeat password</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">✱</div>
						</div>
						<input type='password' class="form-control" name='repeat' placeholder="password"/>
					</div>
				</div>

				<button class="btn btn-lg btn-primary btn-block" type="submit">sign up</button>
			</form:form>
		</div>
	</div>


	<div class="col-6">

		<div class="container">
			<form name='loginForm' action="<c:url value='login' />" method='POST'>

				<div class="form-group">
					<label class="sr-only" for="username">Email address</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">@</div>
						</div>
						<input type='email' class="form-control" name='username' value='' placeholder="email" aria-describedby="emailHelp">
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="password">Password</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">✱</div>
						</div>
						<input type='password' class="form-control" name='password' placeholder="password"/>
					</div>
				</div>

				<button class="btn btn-lg btn-primary btn-block" type="submit">sign in</button>
			</form>
		</div>
	</div>


</div>