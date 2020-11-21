create or alter proc dbo.consultarFacturas
(@anio int,
@mes int,
@dia int,
@hora_inicial varchar(8),
@hora_final varchar(8)) 
as 
    if @hora_inicial = '00:00:00' and @hora_final = '00:00:00'
    BEGIN
            if @mes != 0
            BEGIN
                if @dia = 0--consulta anio y mes
                BEGIN 
                    select
                    f.fc_factura as 'Folio_Fiscal',
                    f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',
                    c.cl_r_f_c as 'RFC del cliente',
                    c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',
                    f.fc_serie as 'Serie de facturacion',
                    f.fecha_alta as 'Fecha y hora de Emision',
                    sum(f.fc_precio_neto) as Total,
                    f.es_cve_estado as Estado
                    from factura f
                    inner join cliente c
                    on f.cl_cve_cliente = c.cl_cve_cliente
                    and f.Es_Cve_Estado = 'AC'
                    --
                    and DATEPART(YEAR, f.fecha_alta) = @anio
                    and DATEPART(MONTH, f.fecha_alta) = @mes
                    --
                    group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie
                    order by f.fc_factura asc;
                END

                else--consulta anio, mes y dia
                BEGIN
                    select
                    f.fc_factura as 'Folio_Fiscal',
                    f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',
                    c.cl_r_f_c as 'RFC del cliente',
                    c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',
                    f.fc_serie as 'Serie de facturacion',
                    f.fecha_alta as 'Fecha y hora de Emision',
                    sum(f.fc_precio_neto) as Total,
                    f.es_cve_estado as Estado
                    from factura f
                    inner join cliente c
                    on f.cl_cve_cliente = c.cl_cve_cliente
                    and f.Es_Cve_Estado = 'AC'
                    --
                    and DATEPART(YEAR, f.fecha_alta) = @anio
                    and DATEPART(MONTH, f.fecha_alta) = @mes
                    and DATEPART(DAY, f.fecha_alta) = @dia
                    --
                    group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie
                    order by f.fc_factura asc;
                END
            END
            else--consulta puro anio
            BEGIN
                select
                f.fc_factura as 'Folio_Fiscal',
                f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',
                c.cl_r_f_c as 'RFC del cliente',
                c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',
                f.fc_serie as 'Serie de facturacion',
                f.fecha_alta as 'Fecha y hora de Emision',
                sum(f.fc_precio_neto) as Total,
                f.es_cve_estado as Estado
                from factura f
                inner join cliente c
                on f.cl_cve_cliente = c.cl_cve_cliente
                and f.Es_Cve_Estado = 'AC'
                --
                and DATEPART(YEAR, f.fecha_alta) = @anio
                --
                group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie
                order by f.fc_factura asc;

            END
    END   

    else--si se tomara en cuanta las horas
    BEGIN
            if @mes != 0
            BEGIN
                if @dia = 0--consulta anio y mes
                BEGIN 
                    select
                    f.fc_factura as 'Folio_Fiscal',
                    f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',
                    c.cl_r_f_c as 'RFC del cliente',
                    c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',
                    f.fc_serie as 'Serie de facturacion',
                    f.fecha_alta as 'Fecha y hora de Emision',
                    sum(f.fc_precio_neto) as Total,
                    f.es_cve_estado as Estado
                    from factura f
                    inner join cliente c
                    on f.cl_cve_cliente = c.cl_cve_cliente
                    and f.Es_Cve_Estado = 'AC'
                    --
                    and DATEPART(YEAR, f.fecha_alta) = @anio
                    and DATEPART(MONTH, f.fecha_alta) = @mes
                    and CAST(f.fecha_alta as time) between @hora_inicial and @hora_final
                    --
                    group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie
                    order by f.fc_factura asc;
                END

                else--consulta anio, mes y dia
                BEGIN
                    select
                    f.fc_factura as 'Folio_Fiscal',
                    f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',
                    c.cl_r_f_c as 'RFC del cliente',
                    c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',
                    f.fc_serie as 'Serie de facturacion',
                    f.fecha_alta as 'Fecha y hora de Emision',
                    sum(f.fc_precio_neto) as Total,
                    f.es_cve_estado as Estado
                    from factura f
                    inner join cliente c
                    on f.cl_cve_cliente = c.cl_cve_cliente
                    and f.Es_Cve_Estado = 'AC'
                    --
                    and DATEPART(YEAR, f.fecha_alta) = @anio
                    and DATEPART(MONTH, f.fecha_alta) = @mes
                    and DATEPART(DAY, f.fecha_alta) = @dia
                    and CAST(f.fecha_alta as time) between @hora_inicial and @hora_final
                    --
                    group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie
                    order by f.fc_factura asc;
                END
            END
            else--consulta puro anio
            BEGIN
                select
                f.fc_factura as 'Folio_Fiscal',
                f.fc_documento+c.cl_cve_cliente as 'RFC Emisor',
                c.cl_r_f_c as 'RFC del cliente',
                c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres as 'Nombre del cliente',
                f.fc_serie as 'Serie de facturacion',
                f.fecha_alta as 'Fecha y hora de Emision',
                sum(f.fc_precio_neto) as Total,
                f.es_cve_estado as Estado
                from factura f
                inner join cliente c
                on f.cl_cve_cliente = c.cl_cve_cliente
                and f.Es_Cve_Estado = 'AC'
                --
                and DATEPART(YEAR, f.fecha_alta) = @anio
                and CAST(f.fecha_alta as time) between @hora_inicial and @hora_final
                --
                group by f.fc_factura, (f.fc_documento+c.cl_cve_cliente), c.cl_r_f_c,(c.cl_apellido_paterno+c.cl_apellido_materno+c.cl_nombres),f.fecha_alta,f.es_cve_estado,f.fc_serie
                order by f.fc_factura asc;

            END
    END    
go