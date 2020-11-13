    var tabla=null;

      function get()
    {   
        console.log("entro a get")
        if(tabla!=null){tabla.destroy();}
            tabla=$('#tabla-facturas').DataTable( {searching: false,
                "ajax": "consultarFacturas.do",
                "columns": [{"render": function () {
                           return '<input type="checkbox">'}},
                    {data: "Folio_Fiscal"},
                    {data: "RFC Emisor"},
                    {data: "RFC del cliente"},
                    {data: "Nombre del cliente"},
                    {data: "Serie de facturacion"},
                    {data: "Fecha y hora de Emision"},
                    {data: "Total"},
                    {data: "Estado"},
                    {"render": function () {
                           return '<i class="icon-doc-inv" id = "" onclick = ""></i>'}},
                    {"render": function () {
                           return '<button class="boton-table" onclick = "">PDF</button>\n\
<button class="boton-table">XML</button>'}},
                ]
        } );
        const table = document.getElementById('tabla-facturas');
        table.style.setProperty('width',`${100}%`)
        validar()
    }
    
    function validar(){
        let vfolio = ""
        var myTable = $('#tabla-facturas').DataTable();
        myTable.on('click','i',function() {
            var rowData = myTable.row($(this).parents('tr')).data();            
            vfolio = rowData.Folio_Fiscal
//            alert(typeof vfolio)    
            if(vfolio == ""){

            }
            else {
//                alert("antes de pdf: " + vfolio)
                generatePdf(vfolio)
                const openModal = document.getElementById("open-modal-pdf");
                const pdfObjectContainer = document.getElementById("object-pdf-container");
//                pdfObjectContainer.setAttribute('data','${pageContext.request.contextPath}/pdfs/pdf.pdf')
                setTimeout(()=>{openModal.click()},2000)                
            }
        });                
    }
    
   
    function generatePdf(fFiscal){
        let fc_factura = "esto es un ejemplo para hacer el pdf"
        fc_factura = fFiscal
        $.ajax({
            url : 'generarPdf.do',
            type : "POST",
            data: {fc_factura},
            beforeSend : function(xhr) {
            },
            success : function(jsonResponse, textStatus, jqXHR) {
//                alert("listo");
            },
            error : function(jqXHR, textStatus, errorThrown) {
//                console.log(textStatus);
            }
        });
    }
    
    
    
