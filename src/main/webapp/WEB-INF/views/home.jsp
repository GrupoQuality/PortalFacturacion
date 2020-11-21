<!DOCTYPE html>
<html lang="en">
<!-- sdf -->

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    <!-- bootstrap -->
    <!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->


    <title>Document</title>
</head>

<body>
        <header class="main-header">
        <div class="left">
            <i class="icon-menu" id="btn-menu"></i>
            <a href="home.do">  
                <h1 style="h1">PORTAL FACTURACION</h1>
            </a>
        </div>
        <div class="right">
            <i class="icon-user-circle-o"></i>
            <i class="icon-down-dir" id="btn-user"></i>
        </div>
    </header>

    <div class="user-window" id="user-window">
        <p>Erick Aguilar</p>
        <p>aguilar505088@gmail.com</p>
    </div>

    <div class="container-menu" id="container-menu">
        <div class="cont-menu" id="cont-menu">
            <nav>
                <a href="consulta.do">
                    <i class="icon-doc-text"></i><span>Consulta formularios</span>
                </a>
            </nav>
        </div>
    </div>

    <div class="pseudo-body" id="pseudo-body">
        <div class="section-div" id="contenido">
            <h1 class="f-title">Bienvenido!!</h1>
            <img id="logo-lubriagsa" src="${pageContext.request.contextPath}/img/logo-lubriagsa.png" alt="">
        </div>
        <!--fin contentenido-->
    </div>
    <!--fin pseudo body-->

    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
    <!-- bootstrp -->
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

    <!-- <script src="./src/bootstrap-input-spinner.js"></script> -->

</body>

</html>