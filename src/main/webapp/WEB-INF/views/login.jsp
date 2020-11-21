<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <!--<link rel="stylesheet" href="./../../css/styles.css">-->
    

    <title>Document</title>
</head>

<body>
    <header class="main-header">
        <div class="left">
            <h1 class="h1">PORTAL FACTURACION</h1>
        </div>
        <div class="right">
        </div>
    </header>

    <div class="pseudo-body" id="pseudo-body">
        <div class="section-div" id="contenido">
            <h1 class="f-title">Acceso al sistema</h1>
            <form action="home.do" class="login-form" id="login-form">
                <div class="up">
                    <h2 class="f-subtitle">INICIAR SESION</h2>
                    <label class="f-normal" for="input-rfc">USUARIO</label>
                    <input type="text" class="text-field" id="input-user" placeholder="Ingrese su usuario">
                    <label class="f-normal" for="input-pass">CONSTRASEÑA</label>
                    <input type="password" class="text-field" id="input-pass" placeholder="Ingrese su contraseña">
                    <h3 class="f-link">¿Olvidó su contraseña?</h3>
                </div>
                <div class="down">
                    <input type="submit" class="principal-button" id="principal-button" value="ENVIAR">
                </div>
            </form>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/js/form.js"></script>
</body>

</html>