    var tabla=null;
      function get()
    {        
        console.log("entro a get")
        if(tabla!=null){tabla.destroy();}
            tabla=$('#tabla-facturas').DataTable( {searching: false,
                "ajax": "consultarFacturas.do",
                "columns": [{"render": function () {
                           return '<input type="checkbox">'}},
                    {data: "Folio Fiscal" },
                    {data: "RFC Emisor"},
                    {data: "RFC del cliente"},
                    {data: "Nombre del cliente"},
                    {data: "Serie de facturacion"},
                    {data: "Fecha y hora de Emision"},
                    {data: "Total"},
                    {data: "Estado"},
                    {"render": function () {
                           return '<i class="icon-doc-inv"></i>'}},
                    {"render": function () {
                           return '<button class="boton-table">PDF</button>\n\
<button class="boton-table">XML</button>'}},
                ]
        } );
        const table = document.getElementById('tabla-facturas');
        table.style.setProperty('width',`${100}%`)
    }
    
    function generatePdf(){
        let fc_factura = "esto es un ejemplo para hacer el pdf"
        $.ajax({
            url : 'generarPdf.do',
            type : "POST",
            data: {fc_factura},
            beforeSend : function(xhr) {
            },
            success : function(jsonResponse, textStatus, jqXHR) {
                alert("listo");
            },
            error : function(jqXHR, textStatus, errorThrown) {
//                console.log(textStatus);
            }
        });
    }
    
    
    
