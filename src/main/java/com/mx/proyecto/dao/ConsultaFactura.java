/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.proyecto.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author erick
 */
public class ConsultaFactura {
    private DataSource dataSource;
    
    public List<Map<String,Object>> selectInfoFacturas(){System.out.println("entro al dao");
        String query = "select\n" +
        "	f.fc_factura as 'Folio_Fiscal',\n" +
        "	f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',\n" +
        "	c.cl_r_f_c as 'RFC del cliente',\n" +
        "	c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',\n" +
        "	f.fc_serie as 'Serie de facturacion',\n" +
        "	f.fecha_alta as 'Fecha y hora de Emision',\n" +
        "	sum(f.fc_precio_neto) as Total,\n" +
        "	f.es_cve_estado as Estado\n" +
        "from factura f\n" +
        "inner join cliente c\n" +
        "on f.cl_cve_cliente = c.cl_cve_cliente\n" +
        "and f.Es_Cve_Estado = 'AC'\n" +
        "group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie\n" +
        "order by f.fc_factura asc";
        System.out.println(query);
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String, Object>> facturas = jdbcTemplate.queryForList(query);
        return facturas;        
    }
    
    public List<Map<String,Object>> selectConsultaUnoFactura(String fc_factura){
        String query = "select\n" +
        "e.Em_Razon_Social,\n" +
        "e.Em_R_F_C,\n" +
        "e.Em_Cve_Regimen_Fiscal,\n" +
        "s.Sc_Descripcion,\n" +
        "s.Sc_Direccion_1,\n" +
        "s.Sc_Direccion_2,\n" +
        "s.Sc_Direccion_3,\n" +
        "s.Sc_Telefono_1,\n" +
        "s.Sc_Telefono_2,\n" +
        "s.sc_telefono_3,\n" +
        "cd.Cd_Documento,\n" +
        "c.Cl_Razon_Social,\n" +
        "c.Cl_R_F_C,\n" +
        "c.Cl_Direccion_1,\n" +
        "c.Cl_Direccion_2,\n" +
        "c.cl_ciudad,\n" +
        "c.cl_estado,\n" +
        "c.Cl_Pais,\n" +
        "c.cl_direccion_3,\n" +
        "v.Vn_Descripcion,\n" +
        "cd.Cd_Numero_Serie_Certificado,\n" +
        "cd.Cd_Timbre_Fecha,\n" +
        "fe.Fc_Factura,\n" +
        "fe.Mn_Cve_Moneda,\n" +
        "f.Fc_Condicion_Venta,\n" +
        "fe.Fc_Folio,\n" +
        "FORMAT(fe.Fc_Tipo_Cambio,'N2')as Fc_Tipo_Cambio,\n" +
        "fe.Fc_Fecha\n" +
        "from Empresa e\n" +
        "inner join Sucursal s\n" +
        "on e.Em_Cve_Empresa = s.Em_Cve_Empresa\n" +
        "inner join factura f\n" +
        "on f.Sc_Cve_Sucursal = s.Sc_Cve_Sucursal\n" +
        "inner join Factura_Encabezado fe\n" +
        "on f.Fc_Factura = fe.Fc_Factura\n" +
        "inner join Vendedor v\n" +
        "on f.Vn_Cve_Vendedor = v.Vn_Cve_Vendedor\n" +
        "inner join Comprobante_Digital cd\n" +
        "on f.Fc_Folio = cd.Cd_Documento\n" +
        "and cd.Cd_Tabla = 'FACTURA'\n" +
        "inner join cliente c\n" +
        "on f.Cl_Cve_Cliente = c.Cl_Cve_Cliente\n" +
        "and f.Fc_Factura = '" + fc_factura + "';";
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String,Object>> parteUnoFactura = jdbcTemplate.queryForList(query);
        return parteUnoFactura;        
    }
    
    public List<Map<String,Object>> selectConsultaDosFactura(String fc_factura){
        String query = "select\n" +
        "p.Pr_Codigo_SAT,\n" +
        "p.Pr_Descripcion_Corta,\n" +
        "FORMAT(f.Fc_Cantidad_Control_1,'N2')as Fc_Cantidad_Control_1,\n" +
        "u.Un_Cve_Unidad,\n" +
        "u.Un_Descripcion,\n" +
        "p.Pr_Descripcion,\n" +
        "FORMAT(f.Fc_Precio_Lista,'N2') as Fc_Precio_Lista,\n" +
        "FORMAT(f.Fc_Descuento_Importe,'N2') as Fc_Descuento_Importe,\n" +
        "FORMAT(f.Fc_Precio_Lista_Importe,'N2') as Fc_Precio_Lista_Importe\n" +
        "from producto p\n" +
        "inner join factura f\n" +
        "on p.Pr_Cve_Producto = f.Pr_Cve_Producto\n" +
        "inner join unidad u\n" +
        "on u.Un_Cve_Unidad = f.Fc_Unidad_1\n" +
        "and f.Fc_Factura = '"+ fc_factura + "';";
//        System.out.println(query);
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String,Object>> parteDosFactura = jdbcTemplate.queryForList(query);
        return parteDosFactura;        
    }

    public List<Map<String,Object>> selectConsultaTresFactura(String fc_factura){
        String query = "select\n" +
        "(dbo.ImporteConLetras(sum(f.fc_precio_neto_importe), f.mn_cve_moneda)) as totalConLetra,\n" +
        "(mp.Mp_Descripcion + '    ' + mp.Mp_Descripcion_CFDI) as metodo_pago,\n" +
        "(f.Fc_Condicion_Venta + '    ' + cv.Cv_Descripcion) as formaPago,\n" +
        "cd.Cd_Numero_Cuenta_Pago,\n" +
        "FORMAT(sum(f.Fc_Precio_Lista_Importe),'N2') as suma,\n" +
        "FORMAT(sum(f.fc_descuento_importe),'N2')as descuento,\n" +
        "FORMAT(sum(f.Fc_Precio_Descontado_Importe),'N2') as subtotal,\n" +
        "FORMAT(sum(f.Fc_Impuesto_Importe),'N2') as iva,\n" +
        "FORMAT(sum(f.fc_precio_neto_importe),'N2') as total\n" +
        "from factura f\n" +
        "inner join Comprobante_Digital cd\n" +
        "on f.Fc_Folio = cd.Cd_Documento\n" +
        "and cd.Cd_Tabla = 'FACTURA'\n" +
        "inner join Metodo_Pago mp\n" +
        "on cd.Cd_Metodo_Pago = mp.Mp_Cve_Metodo_Pago\n" +
        "inner join Condicion_Venta cv \n" +
        "ON cv.Cv_cve_condicion_venta = f.fc_condicion_venta\n" +
        "and fc_factura = '" + fc_factura + "'\n" +
        "group by f.Fc_Factura, mp.Mp_Descripcion, mp.Mp_Descripcion_CFDI, f.Fc_Condicion_Venta, cv.Cv_Descripcion, cd.Cd_Numero_Cuenta_Pago, f.mn_cve_moneda;";
//        System.out.println(query);
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String,Object>> parteTresFactura = jdbcTemplate.queryForList(query);
        return parteTresFactura;        
    }
    
    public List<Map<String,Object>> nConceptos(String fc_factura){
        String query = "select \n" +
        "count(Fc_Factura) as n\n" +
        "from factura\n" +
        "where Fc_Factura = '"+ fc_factura + "';";
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String,Object>> nConceptos = jdbcTemplate.queryForList(query);
        return nConceptos;        
    }
    
    public List<Map<String,Object>> selectConsultaCuatroFactura(String fc_factura){
        String query = "select \n" +
        "cd.Cd_Timbre_Sello_CFDI as selloDigital,\n" +
        "cd.Cd_Timbre_Sello_SAT as selloSAT,\n" +
        "cd.Cd_Timbre_Cadena_Original as cadenaOriginal,\n" +
        "cd.Cd_Timbre_CertificadoSAT as certificado,\n" +
        "cd.Cd_Timbre_Fecha as fecha,\n" +
        "CAST(cd.Cd_Timbre_Fecha AS date) as 'date'" +
        "from factura f \n" +
        "inner join Comprobante_Digital cd\n" +
        "on f.Fc_Folio = cd.Cd_Documento\n" +
        "and cd.Cd_Tabla = 'FACTURA'\n" +
        "and Fc_Factura = '"+ fc_factura + "';";
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String,Object>> consultaCuatro = jdbcTemplate.queryForList(query);
        return consultaCuatro;   
    }
    

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
}
