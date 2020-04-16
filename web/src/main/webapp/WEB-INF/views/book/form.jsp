<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit Book</h4>
<div class="row">

	<form:form class="col s12" method="POST" enctype="multipart/form-data" action="${pagesBook}" modelAttribute="formModel">
		<form:input path="id" type="hidden" />

		<div class="row">
			<div class="input-field col s9">
				<form:input path="title" type="text" disabled="${readonly}" />
				<form:errors path="title" cssClass="red-text" />
				<label for="title">title</label>
			</div>

			<div class="input-field col s3">
				<form:input path="price" type="text" disabled="${readonly}" />
				<form:errors path="price" cssClass="red-text" />
				<label for="price">price</label>
			</div>

			<div class="file-field input-field col s12">
				<div class="btn waves-effect waves-light">
					<span>Browse</span>
					<form:input path="cover" type="file" disabled="${readonly}" />
					<form:errors path="cover" cssClass="red-text" />
				</div>
				<div class="file-path-wrapper">
					<input class="file-path validate" type="text" placeholder="Cover image" />
				</div>
			</div>

			<div class="input-field col s6">
				<form:input path="published" type="text" disabled="${readonly}" class="datepicker" />
				<form:errors path="published" cssClass="red-text" />
				<label for="published">published</label>
			</div>

			<div class="row">
				<div class="input-field col s6">
					<form:select path="genreIds" disabled="${readonly}" multiple="true">
						<form:options items="${genreChoices}" />
					</form:select>
					<form:errors path="genreIds" cssClass="red-text" />
					<label for="genreIds" class="multiselect-label">genres</label>
				</div>
			</div>

			<div class="input-field col s12">
				<form:textarea path="description" rows="3" type="text" disabled="${readonly}" class="materialize-textarea" />
				<form:errors path="description" cssClass="red-text" />
				<label for="description">description</label>
			</div>

		</div>
		<div class="row">
			<div class="col s6"></div>
			<div class="col s3">
				<c:if test="${!readonly}">
					<button class="btn waves-effect waves-light right" type="submit">Save</button>
				</c:if>
			</div>
			<div class="col s3">
				<a class="btn waves-effect waves-light right" href="${pagesBook}">Cancel</a>
			</div>
		</div>
	</form:form>
</div>
