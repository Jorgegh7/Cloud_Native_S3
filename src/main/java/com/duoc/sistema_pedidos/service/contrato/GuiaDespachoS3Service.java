package com.duoc.sistema_pedidos.service.contrato;

import com.duoc.sistema_pedidos.model.GuiaDespacho;

public interface GuiaDespachoS3Service {

    byte[] generarGuia(Long guiaId);
    void subirGuia(Long guiaId);
    byte[] descargarGuia(Long guiaId);
    void modificarGuia(Long guiaId, GuiaDespacho guiaDespacho);
    void borrarGuia(Long guiaId);
}
