<%@page import="model.ProductModel"%>
<%@page import="java.util.ArrayList"%>
<%@ include file="./partials/header.jsp"%>
<div class="w-[82%] h-[70vh] ml-auto grid grid-cols-3 gap-4">
	<%
	ArrayList<ProductModel> products = (ArrayList<ProductModel>) request.getAttribute("Product");
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
			<button class="border rounded-xl p-2 bg-blue-500">Buy Now</button>
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
<%@ include file="./partials/footer.jsp"%>