<style>
::-webkit-scrollbar {
	width: 5px;
	background-color: #3B82F6;
}

::-webkit-scrollbar-thumb {
	background-color: white;
}
</style>

<div  class="flex text-2xl pb-[80px]  justify-center  " id="header">
	<div
		
		class="fixed w-full  flex items-center border-b border-black justify-between bg-gray-300">
		<div >
			<a href="${pageContext.request.contextPath}/ProductServlet">LOGO</a>
		</div>
		<div  class="flex flex-col justify-center">
			<form action="${pageContext.request.contextPath}/ProductServlet"
				method="get">
				<input type="search" name="name"
					class="p-2 border border-black border-2 rounded-xl bg-transparent w-[500px]">
				<button>&#128269;</button>
			</form>
		</div>
		<div class="text-xl font-bold *:p-2 ">
			<a href="${pageContext.request.contextPath}/ProductServlet">Home</a>
			<%
			if (session.getAttribute("user") != null) {
			%>
			<a href="/userDashboard">&#128100; </a> <a
				href="${pageContext.request.contextPath}/AddProductServlet">AddProduct</a>
			<a class="text-blue-500" href="/Cart">&#128722;<sup
				class="text-xl">0</sup></a> <a
				href="${pageContext.request.contextPath}/Logout">Logout</a>
			<%
			} else {
			%>
			<a href="${pageContext.request.contextPath}/Login">Login</a> <a
				href="${pageContext.request.contextPath}/RegisterServlet">Signup</a>
			<%
			}
			%>
		</div>
	</div>
</div>
<script src="https://cdn.tailwindcss.com"></script>
<script type="text/javascript"
	src="https://cdn.jsdelivr.net/npm/toastify-js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/gsap/3.12.5/gsap.min.js"></script>


