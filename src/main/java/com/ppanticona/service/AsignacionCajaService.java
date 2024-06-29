package com.ppanticona.service;

import java.util.Map;

public interface AsignacionCajaService {

    public abstract String listarCajasAsignadasPorEstado(String cod_estado);
	public abstract String aperturarCaja(Map datos, String login);
	public abstract String cerrarCaja(Map datos);
	public abstract String obtenerDatosXLogin(String login);
	public abstract String obtenerDatosCajaAsignada(String nroCaja);
	public abstract String findByUsuarioAsignadoAndAndEstado(String login, String cod_estado);
	
}
