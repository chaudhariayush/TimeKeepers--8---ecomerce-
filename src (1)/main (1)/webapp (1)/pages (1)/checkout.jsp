<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ page import="java.util.List" %>
<%@ page import="model.ProductModel" %>
<%@ page import="model.CartModel. *" %>
<%@ page import="model.CartModel" %>
<%@ page import="model.Cart" %>
<%@ page import="controller.database.DatabaseController" %>
<%@ page import="controller.servlets.*" %>
    
<%
if(session.getAttribute("current_user") != null){
    int isAdmin = (int) session.getAttribute("current_user");
    if (isAdmin == 1){
        session.setAttribute("credential", "You are Admin!! No access to this page");
        response.sendRedirect("admin.jsp");
        return;
    }
}
Cart cart = (Cart) session.getAttribute("cart"); // Retrieve the cart object from the session

%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Check Out</title>

    <!--css link-->
    <link rel="stylesheet" href="../stylesheets/checkout.css">
</head>
<body>
    <div class="heading">
        <h3>checkout</h3>
        <p><a href="index.jsp">home</a>/checkout</p>
    </div>

    <section class="display-order">
    <%
        if (cart != null && !cart.getItems().isEmpty()) { // Check if cart is not null and has items
            List<CartModel> cartModel = cart.getItems();
            for (CartModel cartItem : cartModel) {
                ProductModel product = cartItem.getProduct();
                
        
        %>
        <p><%=product.getProduct_name() %><span>(Rs <%=product.getUnit_price() %> x <%=cartItem.getProduct_quantity() %>)</span></p>
    <%
            }
        }
     %>
    <c:set var="grandTotal" value="${cart.getTotalPrice()}" />
     <div class="grand-total">grand total : <span>Rs ${grandTotal} /-</span></div>
    </section>

    <section class="checkout">
        <form action="" method="post">
            <h3>place your order</h3>
            <div class="flex">
                <div class="input-box">
                    <span>your name:</span>
                    <input type="text" name="name" placeholder="enter your name">
                </div>
                <div class="input-box">
                    <span>your number:</span>
                    <input type="text" name="number" placeholder="enter your number">
                </div>
                <div class="input-box">
                    <span>your address:</span>
                    <input type="text" name="address" placeholder="enter your address">
                </div>
                <div class="input-box">
                    <span>your email:</span>
                    <input type="email" name="email" placeholder="enter your email">
                </div>
                <div class="input-box">
                    <span>city:</span>
                    <input type="email" name="city" placeholder="Eg :- Biratnagar">
                </div>
                <div class="input-box">
                    <span>pin code:</span>
                    <input type="number" name="pin code" placeholder="Eg :- 123124">
                </div>
                <div class="input-box">
                    <span>province:</span>
                    <select name="province">
                        <option value="Koshi Province">koshi Province</option>
                        <option value="Madhesh Province">Madhesh Province</option>
                        <option value="Bagmati Province">Bagmati Province</option>
                        <option value="Gandaki Province">Gandaki Province</option>
                        <option value="Lumbini Province">Lumbini Province</option>
                        <option value="Karnali Province">Karnali Province</option>
                        <option value="Sudurpashchim Province">Sudurpashchim Province</option>
                    </select>
                </div>
                <div class="input-box">
                    <span>payment method:</span>
                    <select name="method">
                        <option value="cash on delivery">cash on delivery</option>
                        <option value="credit card">credit card</option>
                        <option value="paypal">paypal</option>
                    </select>
                </div>
            </div>
            <a href="/TimeKeepers/pages/cart.jsp" class="option-btn">go back</a>
            <input type="submit" value="order now" class="btn" name="order-btn">
        </form>
    </section>
</body>
</html>
