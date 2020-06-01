<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h4 class="header">Edit Book</h4>

<form:form method="POST" enctype="multipart/form-data" action="${pagesBook}" modelAttribute="formModel">
	<div class="form-row">
		<form:input path="id" type="hidden"/>

		<div class="form-group col-md-6">
			<label for="title">title</label>
			<form:input path="title" class="form-control" type="text" disabled="${readonly}"/>
			<form:errors path="title" cssClass="red-text"/>
		</div>

		<div class="form-group col-md-6">
			<label for="title">author</label>
			<form:input path="author" class="form-control" type="text" disabled="${readonly}"/>
			<form:errors path="author" cssClass="red-text"/>
		</div>
	</div>

	<div class="form-row">

		<div class="form-group col-md-3">
			<label for="price">price</label>
			<form:input path="price" class="form-control" type="text" disabled="${readonly}"/>
			<form:errors path="price" cssClass="red-text"/>
		</div>

		<div class="form-group col-md-3">
			<label for="published">published</label>
			<form:input path="published" class="form-control datepicker" type="text" data-date-format="yyyy-mm-dd" data-provide="datepicker" disabled="${readonly}"/>
			<form:errors path="published" cssClass="red-text"/>
		</div>
	</div>

	<div class="form-row">
		<div class="form-group col-md-4">
			<label for="genreIds">genres</label>
			<form:select class="form-control" path="genreIds" disabled="${readonly}" multiple="true">
				<form:options items="${genreChoices}"/>
			</form:select>
			<form:errors path="genreIds" cssClass="red-text"/>
		</div>

		<div class="form-group col">
			<label for="price">cover</label>
			<div class="row">
				<div class="col-auto">
					<img class="img-thumbnail rounded float-left" src="/${formModel.cover}" alt="Card image cap">
				</div>
				<div class="col">
					<form:input path="cover" class="form-control-file" type="file" disabled="${readonly}"/>
					<form:input path="cover" type="hidden"/>
					<form:errors path="cover" cssClass="red-text"/>
				</div>
			</div>
		</div>
	</div>

	<div class="form-row">
		<div class="form-group col-12">
			<label for="price">book file</label>
			<div class="col">
				<div class="row">
					<form:input path="pdf" type="text"/>
					<form:input path="pdf" class="form-control-file col-6" type="file" disabled="${readonly}"/>
					<form:errors path="pdf" class="col" cssClass="red-text"/>
				</div>
			</div>
		</div>
	</div>

	<div class="form-row">
		<div class="form-group col-md-12">
			<label for="description">description</label>
			<form:textarea class="form-control" path="description" rows="3" type="text" disabled="${readonly}"/>
			<form:errors path="description" cssClass="red-text"/>
		</div>
	</div>

	<div class="form-row">
		<c:if test="${!readonly}">
			<button type="submit" class="btn btn-primary   mr-1">Save</button>
		</c:if>
		<button type="reset" class="btn btn-warning mr-1">Cancel</button>
		<c:if test="${id != null}">
			<div>
				<a href="${pagesBook}/${id}/delete" class="btn btn-danger" role="button">Delete</a>
			</div>
		</c:if>
	</div>
</form:form>
