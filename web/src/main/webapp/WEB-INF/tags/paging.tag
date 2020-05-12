<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${currentPageGridState.totalCount>0}">
		<div class="row justify-content-center">
		<ul class="pagination pagination-lg">
			<c:choose>
				<c:when test="${currentPageGridState.firstPage}">
					<li class="page-item disabled">
						<a class="page-link">Previous</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="page-link" href="?page=${currentPageGridState.page-1}">Previous</a>
					</li>
				</c:otherwise>
			</c:choose>
			<c:forEach begin="1" end="${currentPageGridState.pageCount}" varStatus="loop">
				<c:choose>
					<c:when test="${loop.index == currentPageGridState.page}">
						<li class="page-item active">
							<a class="page-link">${loop.index}</a>
						</li>
					</c:when>
					<c:otherwise>
						<li class="page-item">
							<a class="page-link" href="?page=${loop.index}">${loop.index}</a>
						</li>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<c:choose>
				<c:when test="${currentPageGridState.lastPage}">
					<li class="disabled">
						<a class="page-link">Next</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
						<a class="page-link" href="?page=${currentPageGridState.page+1}">Next</a>
					</li>
				</c:otherwise>
			</c:choose>
		</ul>
		</div>
	</c:when>
</c:choose>
