<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <!-- bootstrap jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>  
    
    <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>        
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.css">
    <!--<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">-->
    
    <script src="${pageContext.request.contextPath}/js/utileria.js"></script>
    <!--PDF-->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.min.js"></script>
    <!--<script src="${pageContext.request.contextPath}/js/pdfGenerator.js" type="module"></script>-->

    <!--modal-->
    <!--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>-->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/styles.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/consultaFacturasStyle.css">

    <title>Document</title>
</head>

<body>
    <header class="main-header">
        <div class="left">
            <i class="icon-menu" id="btn-menu"></i>
            <a href="home.do">
                <h1>PORTAL FACTURACION</h1>
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
            <h1 class="f-title">Consulta de facturas</h1>
            <div class="filtros">
                <fieldset>
                    <legend>Filtros</legend>
                    <div class="up">
                        <div class="anio">
                            Año:
                            <input type="number" value="2020" min="2000" max="2020" step="1">
                        </div>
                        <div class="mes">
                            Mes:
                            <select name="anio-mes" id="anio-mes" class="select"></select>
                        </div>
                        <div class="dia">
                            Día:
                            <input type="number" value="1" min="1" max="31" step="1">
                        </div>
                        <div class="hora-inicial">
                            Hora inicial:
                            <div class="selects">
                                <select name="hora-inicial-hora" id="hora-inicial-hora" class="select"></select>
                                <select name="hora-inicial-minuto" id="hora-inicial-minuto" class="select"></select>
                                <select name="hora-inicial-segundo" id="hora-inicial-segundo" class="select"></select>
                            </div>
                        </div>
                        <div class="hora-final">
                            Hora final:
                            <div class="selects">
                                <select name="hora-final-hora" id="hora-final-hora" class="select"></select>
                                <select name="hora-final-minuto" id="hora-final-minuto" class="select"></select>
                                <select name="hora-final-segundo" id="hora-final-segundo" class="select"></select>
                            </div>
                        </div>

                    </div>
                    <div class="down">
                        <a href="#open-modal-uno">
                            <button class="boton-generico" id="btn-search-filtros">Buscar
                            </button>
                        </a>
                    </div>
                </fieldset>
            </div>
            <!--Fin filtros -->
            
            <button id="btn_guardar" class="boton-generico" OnClick="get()"> Ver </button>   
            <button id="btn_generate_pdf" class="boton-generico" OnClick="generatePdf()"> Generar pdf</button> 
            <div class="table">
                <table id="tabla-facturas" class="table-bordered table-hover">
                    <thead>
                        <tr class="encabezado">

                            <th id="seleccionar">
                                <div class="seleccionar">
                                    <input type="checkbox">
                                </div>
                            </th>
                            <th id="folio-fiscal">
                                <div class="folio-fiscal">
                                    <span>Folio fiscal</span>
                                    <input type="text">
                                </div>
                            </th>
                            <th id="rfc-emisor">
                                <div class="rfc-emisor">
                                    <span>RFC emisor</span>
                                    <input type="text">
                                </div>
                            </th>
                            <th id="rfc-cliente">
                                <div class="rfc-cliente">
                                    <span>RFC del cliente</span>
                                    <input type="text">
                                </div>
                            </th>
                            <th id="nombre-cliente">
                                <div class="nombre-cliente">
                                    <span>Nombre del cliente</span>
                                    <input type="text">
                                </div>
                            </th>
                            <th id="serie-facturacion">
                                <div class="serie-facturacion">
                                    <span>Serie de facturacion</span>
                                    <input type="text">
                                </div>
                            </th>
                            <th id="fecha-hora-emision">
                                <div class="fecha-hora-emision">
                                    <span>Fecha y hora de Emisión</span>
                                </div>
                            </th>
                            <th id="total">
                                <div class="total">
                                    <span>Total</span>
                                </div>
                            </th>
                            <th id="status">
                                <div class="status">
                                    <span>Estado</span>
                                    <select name="status" id="status"></select>
                                </div>
                            </th>
                            <th id="visualizar">
                                <div class="visualizar">
                                    <span>Visualizar</span>
                                </div>
                            </th>
                            <th id="descargar">
                                <div class="descargar">
                                    <span>Descargar</span>
                                </div>
                            </th>
                        </tr>                        
                    </thead>
                </table>
            </div>
            <!--fin tabla-->
            <div class="facturas-seleccionadas">
                <button class="boton-generico">Enviar facturas
                </button>
                <button class="boton-generico">PDF
                </button>
                <button class="boton-generico">XML
                </button>
            </div>
            
            <!-- Button to Open the Modal -->
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
              Open modal
            </button>

            <!-- The Modal -->
            <div class="modal" id="myModal">
              <div class="modal-dialog modal-lg">
                <div class="modal-content">

                  <!-- Modal Header -->
                  <div class="modal-header">
                    <h4 class="modal-title">Pdf generado</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                  </div>

                  <!-- Modal body -->
                  <div class="d-flex flex-column align-items-center" id="modal-body">
                      <div class="d-flex flex-column align-items-center" id="pdf-container">
                    <object data="${pageContext.request.contextPath}/pdfs/pdf.pdf" type="application/pdf" width="100%" height="400">
                        alt : <a href="test.pdf">pdf.pdf</a>
                    </object>
                    </div>  

                    <div id="botones-factura">
                        <a href="${pageContext.request.contextPath}/pdfs/pdf.pdf" download rel="noopener noreferrer" target="_blank">
                            <button  class="boton-generico" > PDF </button>   
                        </a>
                        <button  class="boton-generico" > XML </button>  
                    </div>
                  </div>

                  <!-- Modal footer -->
                  <div class="modal-footer">
                      <button type="button" class="btn btn-danger" data-dismiss="modal" id="close-modal">Cerrar</button>
                  </div>

                </div>
              </div>
            </div>
        <!--fin contentenido-->
    </div>
    <!--fin pseudo body-->
            
    <script src="${pageContext.request.contextPath}/js/menu.js"></script>
</body>

</html>