<%@page import="util.StringUtils"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.ProductModel"%>
<%@ page import="model.ProductModel"%>

<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Product</title>
</head>
<body class="bg-gray-100">
	<%@ include file="./partials/header.jsp"%>
	<%
	String successMessage = (String) request.getAttribute(StringUtils.SUCCESS_MESSAGE);
	String errorMessage = (String) request.getAttribute(StringUtils.ERROR_MESSAGE);

	if (errorMessage != null && !errorMessage.isEmpty()) {
	%>
	<!-- Display error message -->
	<div class="alert alert-danger mt-2" role="alert">
		<%=errorMessage%>
	</div>
	<%
	}
	%>

	<%
	if (successMessage != null && !successMessage.isEmpty()) {
	%>
	<!-- Display success message -->
	<div class="alert alert-success mt-2" role="alert">
		<%=successMessage%>
	</div>
	<%
	}
	%>

	<div class="flex ">
		<div
			class="flex-[4] fixed h-screen text-center   bg-[#D1D5DB] w-[18%]">
			<div class="p-8">

				<h1 class="text-2xl text-center font-bold">Filter Products</h1>
				<form method="post"
					action="${pageContext.request.contextPath}/ProductServlet"
					class="max-w-sm mt-5">
					<h1 class="text-xl font-bold">Price</h1>
					<div class="mb-4 flex">
						<div class="mb-4 flex">
							<div class="mr-2 flex-grow">
								<label class="block text-gray-700 text-sm font-bold mb-2"
									for="minPrice">From:</label> <input type="number" id="minPrice"
									name="minPrice" min="299" step="10"
									class="w-full bg-white border border-gray-300 rounded px-2 py-1">
							</div>
							<div class="flex-grow">
								<label class="block text-gray-700 text-sm font-bold mb-2"
									for="maxPrice">To:</label> <input type="number" id="maxPrice"
									name="maxPrice" step="10"
									class="w-full bg-white border border-gray-300 rounded px-2 py-1">
							</div>
						</div>

					</div>
					<div class="mb-4">
						<label class="block text-gray-700 text-sm font-bold mb-2"
							for="category">Category:</label> <select id="category"
							name="category"
							class="w-full bg-white border border-gray-300 rounded px-4 py-2">
							<option value="" disabled selected>Select Category</option>
							<option value="1">Ultrabooks</option>
							<option value="2">Gaming</option>
							<option value="3">2-in-1</option>
							<option value="4">Business</option>
							<option value="5">Budget</option>
							<option value="6">Workstation</option>
							<option value="7">Chromebooks</option>
							<option value="8">Student</option>
							<option value="9">Multimedia</option>
							<option value="10">Thin and Light</option>

						</select>
					</div>
					<div class="flex justify-center">
						<button type="submit"
							class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">Filter</button>
					</div>
				</form>
			</div>

		</div>
		<div class="flex-[13] mr-auto ">

			<div id="carousel"
				class="w-[82%] flex items-center justify-center text-8xl bg-red-200 ml-auto h-[60vh]  ">
				<h1>Carousel</h1>
			</div>
			<div class="w-[82%] ml-auto grid grid-cols-3 gap-4">
				<%
				ArrayList<ProductModel> products = (ArrayList<ProductModel>) request.getAttribute("Products");
				if (products != null) {
					for (ProductModel product : products) {
				%>
				<div
					class="bg-white p-4 border flex flex-col justifu border-gray-300 shadow-lg rounded-xl">
					<div>
						<div class="flex gap-2">
							<a
								href='${pageContext.request.contextPath}/ProductServlet/<%=product.getProductId()%>'>
								<img alt=""
								src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcScPdsC0S6PwDKC2Vz3ILck4OE-Pun5uF_Lz_sWuAl4Lg&s">
							</a>
							<div class="*:mt-2 *:w-20">
								<img class="h-12" alt=""
									src="https://miro.medium.com/v2/resize:fit:1200/1*0HIdwBOPpG4d5sPqw6EA8Q.jpeg">

								<img class="h-12" alt=""
									src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQUOkcBkC9CvlcYbUYUNQ_Ni4iVyLgHTnhOCCVC9cJ6ID8HWB0OclQnrrT4EGHX_byS-7Q&usqp=CAU">
								<img class="h-12 object-cover" alt=""
									src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRkMu7tVxnmSP3uFylfug3vLGlAny5-iIgsCn1DEQFLBGtvmRV6T6H3uOTs76n8wkz3QRU&usqp=CAU">
							</div>
						</div>
						<h2 class="text-lg font-semibold mb-2"><%=product.getName()%></h2>
						<p class="text-gray-700 mb-2"><%=product.getDescription()%></p>
						<p class="text-gray-900 font-semibold mb-2">
							Price : $<%=product.getPrice()%></p>
						<p class="text-gray-700 font-semibold">
							Stock:
							<%=product.getStock()%></p>
						<p class="text-gray-700 font-semibold">
							Cateogry :
							<%=product.getCategoryName()%></p>
					</div>
					<div class="flex justify-between mt-2">
						<form class="flex justify-between mt-2 w-full" method="post"
							action="${pageContext.request.contextPath}/Cart">
							<button name="cart" value="<%=product.getProductId()%>"
								class="border rounded-xl p-2 bg-green-500">Add To Cart</button>
						</form>
							<button class="border rounded-xl p-2 bg-blue-500">Buy
								Now</button>
					</div>
				</div>
				<%
				}
				} else {
				%>
				<div class="flex items-center justify-center">
					<h1 class="text-6xl text- Black">NO Products Found</h1>
				</div>
				<%
				}
				%>
			</div>
		</div>
	</div>
	<div class="w-[82%] ml-auto">
		<%@ include file="./partials/footer.jsp"%>
	</div>
</body>
</html>
