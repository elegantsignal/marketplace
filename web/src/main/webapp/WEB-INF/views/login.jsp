<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">Aa</div>
						</div>
						<input name="name" type="text" class="form-control" placeholder="<spring:message code='login.form.input.name'/>" aria-describedby="nameHelp"/>
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="email">Email address</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">@</div>
						</div>
						<input name="email" type='email' class="form-control" placeholder="<spring:message code='login.form.input.email'/>" aria-describedby="emailHelp"/>
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="password">password</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">✱</div>
						</div>
						<input type='password' class="form-control" name='password' placeholder="<spring:message code='login.form.input.password'/>"/>
					</div>
				</div>

				<div class="form-group">
					<label class="sr-only" for="password">repeat password</label>
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">✱</div>
						</div>
						<input type='password' class="form-control" name='repeat' placeholder="<spring:message code='login.form.input.passwordRepeat'/>"/>
					</div>
				</div>

				<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code='login.form.button.singUp'/></button>
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
						<input type='email' class="form-control" name='username' value='' placeholder="<spring:message code='login.form.input.email'/>" aria-describedby="emailHelp">
					</div>
				</div>

				<div class="form-group">
					<div class="input-group mb-2 mr-sm-2">
						<div class="input-group-prepend">
							<div class="input-group-text">✱</div>
						</div>
						<input type='password' class="form-control" name='password' placeholder="<spring:message code='login.form.input.password'/>"/>
					</div>
				</div>

				<button class="btn btn-lg btn-primary btn-block" type="submit">
					<spring:message code='login.form.button.singIn'/></button>
			</form>
		</div>
	</div>


</div>