/**
 * 
 */

function checkAddProduct() {
	var productId = document.getElementById("productId");
	var name = document.getElementById("name");
	var unitPrice = document.getElementById("unitPrice");
	var unitsInStock = document.getElementById("unitsInStock");
	
	var regExpProductId = / ^P[0-9]{4,11}/;  // 0~9까지 최소 4자에서 11자까지
	if(regExpProductId.test(productId.value)) {
		alert("[상품 코드]\n최소 4자에서 11자까지 입력하세요")
		productId.focus();
		
		return false;
	}
	
	if(name.value.length < 4 || name.value.length > 12) {
		alert("[상품명]\n최소 4자에서 최대 12자 까지 입력하세요");
		name.focus();
		return false;
	}
	
	if(unitPrice.value.length == 0 || isNaN(unitPrice.value)) {
		alert("[가격]\n숫자만 입력하세요")
		unitPrice.focus();
		return false;
	}
	if(isNaN(unitsInStock.value)) {
		alert("[재고]\n숫자만 입력하세요")
		unitsInStock.focus();
		return false;
	}
	
	document.newProduct.submit();
}