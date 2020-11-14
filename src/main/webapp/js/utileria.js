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
    let pdfNameToDelete = ""
    function validar(){
        let vfolio = ""
        var myTable = $('#tabla-facturas').DataTable();
        myTable.on('click','i',function() {
            var rowData = myTable.row($(this).parents('tr')).data();            
            vfolio = rowData.Folio_Fiscal
            
            if(vfolio != ""){
//                borrarPdf(pdfNameToDelete);
                generatePdf(vfolio);      
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
                let pdfName = "";
                console.log("ya regreso del controlador")
                const loadPdf =async(name)=>{
                    pdfNameToDelete = pdfName;
                    pdfName = await jsonResponse;
                    console.log("termino de conseguir el nombre")
                }
                loadPdf().then(()=>{
                    addOpenModalBody(pdfName);
                    console.log("carga el pdf al modal")
                })
            },
            error : function(jqXHR, textStatus, errorThrown) {
            }
        });
    }
    
    function addOpenModalBody(pdfName){  
        
        const openModal = document.getElementById("open-modal-pdf");
//        document.getElementById("modal-body").innerHTML = "";
        document.getElementById("modal-body").innerHTML  += ""+
        `<object data='/PortalFacturasLubriagsa/pdfs/${pdfName}.pdf' type='application/pdf' width='100%' height='400' id='object-pdf-container'>`+
//             `<a href='/PortalFacturasLubriagsa/pdfs/${pdfName}.pdf'>pdf.pdf</a>` +
             `<p>error</p>`+
        `</object>` +
        `<div id="botones-factura">`+
            `<a href="/PortalFacturasLubriagsa/pdfs/${pdfName}.pdf" download rel="noopener noreferrer" target="_blank">`+
                `<button  class="btn boton-generico" > PDF </button>`+
            `</a>`+
            `<button  class="btn boton-generico" > XML </button>  `+
        `</div>`;
        openModal.click() 
//        return 0;
    }
    function borrarPdf(filePath){
        let name = filePath
//        name = fileName;
        $.ajax({
            url : 'borrarPdf.do',
            type : "POST",
            data: {name},
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
    
    
    
