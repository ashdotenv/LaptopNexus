
	<div id="header">
		<header class="header">
			<h1 class="logo">
				<a href="">
					<img src="${pageContext.request.contextPath}/resources/images/logo.png" />
				</a>
			</h1>
			<ul class="main-nav">
				<li><a href="${pageContext.request.contextPath}/pages/home.jsp">Home</a>
				<li><a href="${pageContext.request.contextPath}/pages/students.jsp">Students</a>
				<li>
					<form action="${pageContext.request.contextPath}/LogoutServlet" method="post">
						<button type="submit" class="btn btn-danger">
							Logout
						</button>
					</form>
				</li>
			</ul>
		</header>
	</div>
