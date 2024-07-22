<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- link css  -->
    <link rel="stylesheet" href="../stylesheets/main.css">
    <link rel="stylesheet" href="../stylesheets/style.css">
    <link rel="stylesheet" href="../stylesheets/header.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/3.5.0/remixicon.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />

    <!-- link font  -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
        integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm" crossorigin="anonymous">

    <title>TimeKeepers Landing Page</title>
</head>
<!--This is a header-->
<div class="main-header">
    <header class="header" id="header">
        <nav class="nav container">
           <a href="Time keepers.png" class="logo">
                 <img src="<%=contextPath%>/resources/images/adminLogo\logo.png" alt="Time Keepers Logo">
               <span style="margin-top: 25px; margin-left: 0px;">TIME   KEEPERS</span>
            </a>

           <div class="nav__menu" id="nav-menu">
              <ul class="nav__list">
                 <li class="nav__item">
                    <a href="#" class="nav__link">Home</a>
                 </li>

                 <li class="nav__item">
                    <a href="/TimeKeepers/ProductServlet" class="nav__link">Products</a>
                 </li>

                 <li class="nav__item">
                    <a href="<%=contextPath%>/pages/cart.jsp" class="nav__link">Cart</a>
                 </li>
                 <li class="nav__item">
                    <a href="<%=contextPath%>/pages/user-profile.jsp" class="nav__link">Profile</a>
                 </li>             

                 <li class="nav__item">
                    <a href="#" class="nav__link">About Us</a>
                 </li>
              </ul>

              <!-- Close button -->
              <div class="nav__close" id="nav-close">
                 <i class="ri-close-line"></i>
              </div>
           </div>

           <div class="nav__actions">
              <!-- Search button -->
              <i class="ri-search-line nav__search" id="search-btn"></i>

              <!-- Login button -->
              <i class="ri-user-line nav__login" id="login-btn"></i>

              <!-- Toggle button -->
              <div class="nav__toggle" id="nav-toggle">
                 <i class="ri-menu-line"></i>
              </div>
           </div>
        </nav>
     </header>

     <!--==================== SEARCH ====================-->
          <!--==================== SEARCH ====================-->
     <div class="search" id="search">
        <form action="../findProductServlet" class="search__form", method = "post">
           <i class="ri-search-line search__icon"></i>
           <input type="text" name = "search" placeholder="How can i help you?" class="search__input">
           <input type="submit" name="submit" value="search" class="btn">
        </form>

        <i class="ri-close-line search__close" id="search-close"></i>
     </div>

     <!--==================== LOGIN ====================-->
     <div class="login" id="login">
        <form action="" class="login__form">
           <h2 class="login__title">Log In</h2>
           
           <!--This is a login page from landing page  -->
           <div class="login__group">
              <div>
                 <label for="email" class="login__label">Email</label>
                 <input type="email" placeholder="Write your email" id="email" class="login__input">
              </div>
              
              <div>
                 <label for="password" class="login__label">Password</label>
                 <input type="password" placeholder="Enter your password" id="password" class="login__input">
              </div>
           </div>

           <div>
              <p class="login__signup">
                 You do not have an account? <a href="#">Sign up</a>
              </p>
  
              <a href="#" class="login__forgot">
                 You forgot your password
              </a>
  
              <button type="submit" class="login__button">Log In</button>
           </div>
        </form>

        <i class="ri-close-line login__close" id="login-close"></i>
     </div>
    </div>

<body>
                
            <!-- >>>>>>>>>>>>>>> M A I N <<<<<<<<<<<<<<<   -->

            <main class="flex-r container">
                <div class="flex-c main-text">
                    <h1 class="lg-head">Where every tick</h1>
                    <h2 class="m-head"> tells a story... </h2>
                    <p class="para">
                        Lorem ipsum dolor sit amet, conseur
                        adi elit, sed do tempor ut lorem torem
                        aliquip ex ea commodo consequat. Lorem 
                        ipsum dolor sit amet.
                    </p>
                    <a href="/TimeKeepers/ProductServlet" class="btn">discover more</a>
                </div>

                <div class="flex-r main-img">
                    <img src="<%=contextPath%>/resources/images/adminLogo/Wristwatch-bro.png" alt="watch image">
                </div> 
                
            </main>
<!-- This is a footer container -->
            <footer>
                <div class="footerContainer">
                    <div class="socialIcons">
                        <a href=""><i class="fa-brands fa-facebook"></i></a>
                        <a href=""><i class="fa-brands fa-instagram"></i></a>
                        <a href=""><i class="fa-brands fa-twitter"></i></a>
                        <a href=""><i class="fa-brands fa-google-plus"></i></a>
                        <a href=""><i class="fa-brands fa-youtube"></i></a>
                    </div>
                    <div class="footerNav">
                        <ul><li><a href="">Home</a></li>
                            <li><a href="">News</a></li>
                            <li><a href="">About</a></li>
                            <li><a href="">Contact Us</a></li>
                            <li><a href="">our Team</a></li>
                        </ul>
                    </div>
                   
                    
                </div>
                <div class="footerBottom">
                    <p>Copyright &copy;2024; Designed by <span class="designer">TimeKeepers</span></p>
                </div>
            </footer>
             <script src="../stylesheets/main.js"></script>
            

</body>

</html>
