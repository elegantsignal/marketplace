<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h1>Please sign in</h1>

<div class="row">

	<div class="container">
		<c:if test="${not empty error}">
			<div class="alert alert-warning alert-dismissible fade show" role="alert">
				<h4 class="alert-headin">${error}</h4>
			</div>
		</c:if>

		<c:if test="${not empty msg}">
			<div class="row">
				<div class="col s12 center">
					<div class="msg">${msg}</div>
				</div>
			</div>
		</c:if>
	</div>

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
				<small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			</div>

			<div class="form-group">
				<label class="sr-only" for="password">Password</label>
				<div class="input-group mb-2 mr-sm-2">
					<div class="input-group-prepend">
						<div class="input-group-text">✱</div>
					</div>
					<input type='password' class="form-control" name='password' placeholder="password" />
				</div>
			</div>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
		</form>
	</div>

</div>