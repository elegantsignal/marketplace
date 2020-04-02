
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit Book</h4>
<div class="row">

	<form:form
		class="col s12"
		method="POST"
		action="${pagesBook}"
		modelAttribute="formModel">
		<form:input
			path="id"
			type="hidden" />

		<div class="row">
			<div class="input-field col s12">
				<form:input
					path="title"
					type="text"
					disabled="${readonly}" />
				<form:errors
					path="title"
					cssClass="red-text" />
				<label for="title">title</label>
			</div>

			<div class="input-field col s12">
				<form:input
					path="price"
					type="text"
					disabled="${readonly}" />
				<form:errors
					path="price"
					cssClass="red-text" />
				<label for="price">price</label>
			</div>

			<div class="input-field col s12">
				<form:input
					path="cover"
					type="text"
					disabled="${readonly}" />
				<form:errors
					path="cover"
					cssClass="red-text" />
				<label for="cover">cover</label>
			</div>

			<div class="input-field col s12">
				<form:input
					path="published"
					type="text"
					disabled="${readonly}"
					class="datepicker" />
				<form:errors
					path="published"
					cssClass="red-text" />
				<label for="published">published</label>
			</div>

			<div class="input-field col s12">
				<form:textarea
					path="description"
					rows="3"
					type="text"
					disabled="${readonly}"
					class="materialize-textarea" />
				<form:errors
					path="description"
					cssClass="red-text" />
				<label for="description">description</label>
			</div>

		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button
						class="btn waves-effect waves-light right"
						type="submit">Save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a
					class="btn waves-effect waves-light right"
					href="${pagesBook}">Cancel</a>
			</div>
		</div>
	</form:form>
</div>
