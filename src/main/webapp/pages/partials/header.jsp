<style>
::-webkit-scrollbar {
	width: 5px;
	background-color: #3B82F6;
}

::-webkit-scrollbar-thumb {
	background-color: white;
}

* {
	font-family: "General Sans";
}
</style>

<div id="navLinks"
	class="navLinks  flex text-2xl pb-[76px] h-[40px] justify-center">
	<div
		class="p-2 fixed w-full h-[75px] flex items-center border-b border-black justify-between bg-gray-300">
		<div>
			<a href="${pageContext.request.contextPath}/ProductServlet"><i
				class="fa-solid fa-laptop"></i>Nexus</a>
		</div>
		<div>
			<form action="${pageContext.request.contextPath}/ProductServlet"
				method="get" class="flex items-center">
				<input type="search" name="name"
					class="p-2 border border-black border-2 rounded-xl bg-transparent w-[500px]">
				<button class="ml-2">
					<i class="fa-solid fa-magnifying-glass"></i>
				</button>
			</form>
		</div>
		<div class="text-[18px] font-bold *:p-2 ">
			<a href="${pageContext.request.contextPath}/ProductServlet"><i
				class="fa-solid fa-house"></i> Home</a>
			<%
			if (session.getAttribute("user") != null) {
			%>

			<a href="${pageContext.request.contextPath}/Cart"><sup
				class="text-sm">0</sup><i class="fa-solid fa-cart-shopping"></i>
				Cart</a> <a href="${pageContext.request.contextPath}/Cart"><i
				class="fa-solid fa-user"></i> Dashboard</a> <a
				href="${pageContext.request.contextPath}/Logout">Logout <i
				class="fa-solid fa-right-from-bracket"></i></a>

			<%
			} else {
			%>
			<a href="${pageContext.request.contextPath}/Login">Login <i
				class="fa-solid fa-right-to-bracket"></i></a> <a
				href="${pageContext.request.contextPath}/RegisterServlet">
				Signup <i class="fa-solid fa-user-plus"></i>
			</a> <a href="${pageContext.request.contextPath}/AboutUs"> About Us</a>
			<%
			}
			%>
		</div>
	</div>
</div>
<script src="https://cdn.tailwindcss.com"></script>
<script src="https://kit.fontawesome.com/78d9ee46c5.js"
	crossorigin="anonymous"></script>


