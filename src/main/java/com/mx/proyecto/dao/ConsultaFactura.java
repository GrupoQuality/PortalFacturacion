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
        "	f.fc_factura as 'Folio Fiscal',\n" +
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
//        System.out.println(query);
        JdbcTemplate jdbcTemplate = new JdbcTemplate (dataSource);
        List<Map<String, Object>> facturas = jdbcTemplate.queryForList(query);
        return facturas;
        
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    
}
