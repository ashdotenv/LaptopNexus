<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
</head>
<body>
	<%@ include file="./partials/header.jsp"%>
	<h1 class="text-center text-3xl mt-8 font-bold">Add Product</h1>
	<div class="max-w-md mx-auto h-[600px] mt-20 z-90">
		<form action="${pageContext.request.contextPath}/AddProductServlet"
			method="post">
			<div class="mb-4">
				<label for="name" class="block text-gray-700 font-bold mb-2">Product
					Name:</label> <input type="text" id="name" name="name"
					class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
			</div>
			<div class="mb-4">
				<label for="description" class="block text-gray-700 font-bold mb-2">Description:</label>
				<textarea type="text" id="description" name="description" rows="2"
					class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"></textarea>
			</div>
			<div class="mb-4">
				<label for="price" class="block text-gray-700 font-bold mb-2">Price:</label>
				<input type="number" id="price" name="price" step="0.01"
					class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
			</div>
			<div class="mb-4">
				<label for="stock" class="block text-gray-700 font-bold mb-2">Stock:</label>
				<input type="number" id="stock" name="stock"
					class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
			</div>

			<label for="category" class="block text-gray-700 font-bold mb-2">Category:</label>
			<select id="category" name="category"
				class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
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
			<div class="flex items-center justify-between mt-5">
				<button type="submit"
					class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
					Add Product</button>
			</div>
	</div>
	</form>
	</div>
	<%@ include file="./partials/footer.jsp"%>

</body>
</html>