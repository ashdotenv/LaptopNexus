<%@page import="model.ProductModel"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products</title>
</head>
<body>
	<%@ include file="./partials/header.jsp"%>
	<%
	ArrayList<ProductModel> products = (ArrayList<ProductModel>) request.getAttribute("Product");
	if (products != null) {
		for (ProductModel product : products) {
	%>
	<div class="flex h-screen mt-10">
		<div class="flex h-screen 	">
			<img style="height:600px" class="object-cover object-fill w-full h-full z-[1]" alt=""
				src="https://itechstore.com.np/_ipx/f_webp/img/product/7f489ded-7ccb-45d1-b4bf-d9a698f4b2a2/hp_victus_15_laptop.png">
		</div>
		<div class="flex flex-col text-xl">
			<div class="mb-4">
				<p class="text-2xl font-semibold"><%=product.getName()%></p>
				<p class="text-gray-600"><%=product.getDescription()%></p>
				<p>
					$<%=product.getPrice()%></p>
				<p class="text-gray-700">
					Stock:
					<%=product.getStock()%></p>
				<p class="text-gray-700">
					Category
					<%=product.getCategory()%></p>
			</div>
			<div class="flex mt-[10%]  gap-2">
				<button
					class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Add
					To Cart</button>
				<button
					class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded">Buy
					Now</button>
			</div>
		</div>


	</div>
	<%
	}
	} else if (products.isEmpty()) {
	%>
	<div class="flex items-center justify-center">
		<h1 class="text-6xl text- Black">NO Products Found</h1>
	</div>
	<%
	}
	%>
	<%@ include file="./partials/footer.jsp"%>
</body>
</html>