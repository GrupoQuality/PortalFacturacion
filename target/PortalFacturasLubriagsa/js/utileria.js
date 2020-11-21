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
                generatePdf(vfolio);      
            }
        });                
    }
    
   
    function generatePdf(fFiscal){
        let fc_factura = "esto es un ejemplo para hacer el pdf"
        fc_factura = fFiscal
        $.ajax({
            url : 'generarPdf2.do',
            type : "GET",
            data: {fc_factura:fc_factura},
            beforeSend : function(xhr) {
            },
            success : function(jsonResponse, textStatus, jqXHR) {
                let pdf;
                console.log("ya regreso del controlador")
                const loadPdf =async(name)=>{
                    pdf = await jsonResponse;
                }
                loadPdf(pdf).then(()=>{    
                alert("antes de abrir la ventana");
                window.open(pdf, "_blank");
//                    let pdfWindow = window.open("")
//                    pdfWindow.document.write(
//                        "<iframe width='100%' height='100%' src='data:application/pdf;base64, " +
//                        encodeURI(pdf) + "'></iframe>"
//                    )
//                window.open(pdf);
//                window.open('generarPdf2.do?query=' + "'" + encodeURIComponent(pdf) + "'&flag=1", "_blank");
//                var pdfAsDataUri = "data:application/pdf;base64,"+pdf;
//                window.open(pdfAsDataUri);
//                downloadFile(new Blob([pdf]), {type: "application/pdf"});
//                console.log(pdf);
//                var bytes = new Uint8Array(pdf);
//                var file = new Blob([bytes], { type: 'application/pdf' });
//                var link=document.createElement('a');
//                link.href=window.URL.createObjectURL(file);
//                link.download="myFileName.pdf";
//                link.click();
//                    addOpenModalBody(pdfName);
//                    borrarPdf(pdfName);
                })
            },
            error : function(jqXHR, textStatus, errorThrown) {
            }
        });
    }
    const downloadFile = (blob, fileName) => {
    const link = document.createElement('a');
    // create a blobURI pointing to our Blob
    link.href = URL.createObjectURL(blob);
    link.download = "fileName.pdf";
    // some browser needs the anchor to be in the doc
    document.body.append(link);
    link.click();
    link.remove();
    // in case the Blob uses a lot of memory
    setTimeout(() => URL.revokeObjectURL(link.href), 7000);
  };


    function addOpenModalBody(pdfName){  
//        alert("abrira el modal con: " + pdfName)
        const openModal = document.getElementById("open-modal-pdf");
        //        document.getElementById("modal-body").innerHTML = "";
//        document.getElementById("modal-body").innerHTML  += ""+
//        alert("2");
        window.open('/PortalFacturasLubriagsa/pdfs/'+pdfName+'.pdf','_blank');
//            $("#dialog").html("<a href='/PortalFacturasLubriagsa/pdfs/+pdfName+".pdf'" target='_blank' id='pdf'>dsf</a>"+
//                    "<div>"+
//            "<iframe src='/PortalFacturasLubriagsa/pdfs/"+pdfName+".pdf'></iframe> " + 
//            "</div>");
//        $("#modal-body").html(
//        `<object data='/PortalFacturasLubriagsa/pdfs/${pdfName}.pdf' type='application/pdf' width='100%' height='450px' id='object-pdf-container'>`+
////             `<a href='/PortalFacturasLubriagsa/pdfs/${pdfName}.pdf'>pdf.pdf</a>` +
//             `<p>error</p>`+
//        `</object>` +
//        `<div id="botones-factura">`+
//            `<a href="/PortalFacturasLubriagsa/pdfs/${pdfName}.pdf" download rel="noopener noreferrer" target="_blank">`+
//                `<button  class="btn boton-generico"  style="margin: 0.5rem"> PDF </button>`+
//            `</a>`+
//            `<button  class="btn boton-generico"  style="margin: 0.5rem"> XML </button>  `+
//        `</div>`);
 
//        openModal.click();
        $("#dialog").show();
//        $("#modal-body").show();
//        alert($("#modal-body").val());
        
//        return 0;
    }
    function borrarPdf(filePath){
        let name = "esto es un ejemplo para hacer el pdf";
        name = filePath;
        alert(name)
        $.ajax({
            url : 'borrarPdf.do',
            type : "POST",
            data: {fileName:name},
            beforeSend : function(xhr) {
            },
            success : function(jsonResponse, textStatus, jqXHR) {
            },
            error : function(jqXHR, textStatus, errorThrown) {
            }
        });
    }
    function populateMonth(){
        const mes = document.getElementById("mes");
        const anio = document.getElementById("anio");
        const dia = document.getElementById("dia");
        
        const horaInicialHora = document.getElementById("hora-inicial-hora");
        const horaInicialMinuto = document.getElementById("hora-inicial-minuto");
        const horaInicialSegundo = document.getElementById("hora-inicial-segundo");
        
        const horaFinalHora = document.getElementById("hora-final-hora");
        const horaFinalMinuto = document.getElementById("hora-final-minuto");
        const horaFinalSegundo = document.getElementById("hora-final-segundo");
        
//        MESES
        mes.innerHTML += `
        <option value = "0">Selecciona mes</option>
        <option value = "1">Enero</option>
        <option value = "2">Febrero</option>
        <option value = "3">Marzo</option>
        <option value = "4">Abril</option>
        <option value = "5">Mayo</option>
        <option value = "6">Junio</option>
        <option value = "7">Julio</option>
        <option value = "8">Agosto</option>
        <option value = "9">Septiembre</option>
        <option value = "10">Octubre</option>
        <option value = "11">Noviembre</option>
        <option value = "12">Diciembre</option>
        `;
        //HORAS
        for (let i = -1; i < 23; i++) {
            horaInicialHora.innerHTML += `<option value="${agregarCero(i)}">${agregarCero(i)}</option>`;
            horaFinalHora.innerHTML += `<option value="${agregarCero(i)}">${agregarCero(i)}</option>`;
        }
        for (let i = -1; i < 59; i++) {
            horaInicialMinuto.innerHTML += `<option value="${agregarCero(i)}">${agregarCero(i)}</option>`;
            horaFinalMinuto.innerHTML += `<option value="${agregarCero(i)}">${agregarCero(i)}</option>`;
        }
        for (let i = -1; i < 59; i++) {
            horaInicialSegundo.innerHTML += `<option value="${agregarCero(i)}">${agregarCero(i)}</option>`;
            horaFinalSegundo.innerHTML += `<option value="${agregarCero(i)}">${agregarCero(i)}</option>`;
        }
        
    }
    function agregarCero(n){
        let string = ""+(n+1);
        let length = string.length;
        if(length == 1)
            return "0"+(n+1);
        else
            return (n+1)
    }
    populateMonth();
    
    
