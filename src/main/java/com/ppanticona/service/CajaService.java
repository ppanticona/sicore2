package com.ppanticona.service;

import java.util.Map;

public interface CajaService {
    public String aperturarCaja(Map datosMap, String login);

    public String cerrarCaja(Map datosMap, String login);
    
	public abstract String listarCajasParaAsignar();
    
	public abstract String listarCajasPorEstado(String cod_estado);
}
