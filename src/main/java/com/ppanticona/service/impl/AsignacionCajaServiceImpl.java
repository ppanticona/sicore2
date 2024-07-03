package com.ppanticona.service.impl;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
//import com.teresitastudio.academy.component.ServicioDetalleConverter;
import com.ppanticona.domain.AsignacionCaja;
import com.ppanticona.domain.Caja;
import com.ppanticona.domain.User;
import com.ppanticona.domain.Empleados;
//import com.teresitastudio.academy.domain.DetCierreAsignacionCaja; 
import com.ppanticona.repository.AsignacionCajaRepository;
import com.ppanticona.repository.CajaRepository; 
import com.ppanticona.service.AsignacionCajaService;
import com.ppanticona.service.CajaService;
import com.ppanticona.repository.CajaRepository; 
import com.ppanticona.repository.MovimientoCajaRepository;

@Service
public class AsignacionCajaServiceImpl implements AsignacionCajaService {
	ZoneId zonaLima = ZoneId.of("America/Lima");
	ZonedDateTime fechaActual = ZonedDateTime.now(zonaLima);
	Gson gson = new GsonBuilder().setPrettyPrinting().create();

	private final CajaRepository cajaRepository; 
	private final AsignacionCajaRepository asignacionCajaRepository;
	private final MovimientoCajaRepository movimientoCajaRepository; 

    public AsignacionCajaServiceImpl(
		CajaRepository cajaRepository,  
		AsignacionCajaRepository asignacionCajaRepository, 
		MovimientoCajaRepository movimientoCajaRepository
	) {
        this.cajaRepository = cajaRepository; 
		this.asignacionCajaRepository = asignacionCajaRepository; 
		this.movimientoCajaRepository = movimientoCajaRepository; 
    }


	private static final Log LOG = LogFactory.getLog(AsignacionCajaServiceImpl.class);

	@Override
	public String listarCajasAsignadasPorEstado(String cod_estado) {

		List<AsignacionCaja> asignaciones = asignacionCajaRepository.findAllByEstado(cod_estado);


		try {
			String listadisplayString = gson.toJson(asignaciones);
			String jsonData = "{\"Result\":\"OK\",\"listaCajasAsignadas\":" + listadisplayString + "}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;
		}

	}

	@Override
	@Transactional
	public String aperturarCaja(Map datos, String login) {

		AsignacionCaja bean = new AsignacionCaja();
		
		Caja cajaBean = new Caja(); 
        User userBean = new User();
        userBean.setId(String.valueOf(((Map<String, Object>) datos.get("empleado")).get("id")));  
		
		Double MontoInicialSoles = null;
		if (datos.get("MontoInicialSoles") instanceof Double) {
			MontoInicialSoles = (double) datos.get("MontoInicialSoles");
		} else if (datos.get("MontoInicialSoles") instanceof Integer) {
			Integer asdasd = (Integer) datos.get("MontoInicialSoles");
			MontoInicialSoles = (double) asdasd;
		}
		



		Double MontoInicialDolares = null;
		if (datos.get("MontoInicialDolares") instanceof Double) {
			MontoInicialDolares = (double) datos.get("MontoInicialDolares");
		} else if (datos.get("MontoInicialDolares") instanceof Integer) {
			Integer asdasd = (Integer) datos.get("MontoInicialDolares");
			MontoInicialDolares = (double) asdasd;
		}
	 
		bean.setMntoInicialSoles(MontoInicialSoles);
		bean.setMntoInicialDolares(MontoInicialDolares);
		cajaBean.setId((String) datos.get("NroCaja"));
		bean.setCaja(cajaBean);
		bean.setObservacionApertura((String) datos.get("Observaciones"));

		bean.setIndDel(false);
		bean.setEstado("01");
		bean.setUsuCrea(login);
		bean.codAsignacion("01");  // está mal, en su lugar estoy usando el ID
		bean.setFecCrea(fechaActual);
		bean.setVersion(1);
		bean.setUser(userBean);
		  
   
		try {
			

			AsignacionCaja resApertura = asignacionCajaRepository.save(bean);   //se registra la asignacion con estado 01 asignada 
			Optional<Caja> optionalCaja = cajaRepository.findById((String)datos.get("NroCaja"));

			if (optionalCaja.isPresent()) {
				Caja caja = optionalCaja.get();
				caja.setEstado("02");
				cajaRepository.save(caja);
				this.LOG.debug("grabamos caja ------------ " + caja.toString());
			} 

			//cajaRepository.updateEstadoById("02", (String)datos.get("NroCaja")); // se cambia estado de la caja a 02 aperturada

			String resAperturaString = gson.toJson(resApertura);

			String jsonData = "{\"Result\":\"OK\",\"resApertura\":" + resAperturaString + "}";
			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;
		}

	}

	@Override
	@Transactional
	public String cerrarCaja(Map datos) {
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			
		String jsonString = objectMapper.writeValueAsString(datos);
    
		 
		JsonNode rootNode = objectMapper.readTree(jsonString);

		// Obtener el valor de "id" de "caja"
		String idCaja = rootNode.get("resasignacionCaja").get("caja").get("id").asText();
		
		this.LOG.debug("-----------------------------id caja " + idCaja);
		
			Double saldoActualSoles = null;
			if (datos.get("saldoActualSoles") instanceof Double) {
				saldoActualSoles = (double) datos.get("saldoActualSoles");
			} else if (datos.get("saldoActualSoles") instanceof Integer) {
				Integer valorEntero = (Integer) datos.get("saldoActualSoles");
				saldoActualSoles = (double) valorEntero;
			}
			Double saldoActualDolares = null;
			if (datos.get("saldoActualDolares") instanceof Double) {
				saldoActualDolares = (double) datos.get("saldoActualDolares");
			} else if (datos.get("saldoActualDolares") instanceof Integer) {
				Integer valorEntero = (Integer) datos.get("saldoActualDolares");
				saldoActualDolares = (double) valorEntero;
			}
			Double diferencia_soles = null;
			if (datos.get("diferencia_soles") instanceof Double) {
				diferencia_soles = (double) datos.get("diferencia_soles");
			} else if (datos.get("diferencia_soles") instanceof Integer) {
				Integer valorEntero = (Integer) datos.get("diferencia_soles");
				diferencia_soles = (double) valorEntero;
			}
			Double diferencia_dolares = null;
			if (datos.get("diferencia_dolares") instanceof Double) {
				diferencia_dolares = (double) datos.get("diferencia_dolares");
			} else if (datos.get("diferencia_dolares") instanceof Integer) {
				Integer valorEntero = (Integer) datos.get("diferencia_dolares");
				diferencia_dolares = (double) valorEntero;
			}
 
			String obs_cierre = (String) datos.get("obsCierre"); 
			String idResasignacionCaja = (String) ((Map) datos.get("resasignacionCaja")).get("id");

 
			Optional<AsignacionCaja> optionalAsignacionCaja = asignacionCajaRepository.findById(idResasignacionCaja);
 
			if (optionalAsignacionCaja.isPresent()) {
				AsignacionCaja asignacionCaja = optionalAsignacionCaja.get();
			 
 
				if (asignacionCaja.getEstado().equals("01")) { // asignacion activa
															// proceder a cerrarla

					this.LOG.debug("entramos al if" + asignacionCaja.getEstado());
					asignacionCaja.setMntoFinalSoles(saldoActualSoles);
					asignacionCaja.setMntoFinalDolares(saldoActualDolares);
					asignacionCaja.setDiferenciaSoles(diferencia_soles);
					asignacionCaja.setDiferenciaDolares(diferencia_dolares);
					asignacionCaja.setObservacionCierre(obs_cierre);
					asignacionCaja.setFecModif(fechaActual);
					asignacionCaja.setEstado("02"); //asignacion caja cerrada
					asignacionCaja.setUsuModif("usuarioACtualiza");
 					asignacionCajaRepository.save(asignacionCaja);   // cerramos la asignacion 
					
					Optional<Caja> optionalCaja = cajaRepository.findById(idCaja);

					if (optionalCaja.isPresent()) {
						Caja caja = optionalCaja.get();
						caja.setEstado("01");  //liberamos la caja cambiandole a un estado 01 - activa
						cajaRepository.save(caja);
						this.LOG.debug("grabamos caja ------------ " + caja.toString());
					} 
					String jsonData = "{\"Result\":\"OK\"}";
					return jsonData;

				} else {
					this.LOG.debug("entramos al else" + asignacionCaja.getEstado());
					String jsonData = "{\"Result\":\"ERROR\",\"Message\":\"El estado de asignacion es distinta a Activa :"
							+ asignacionCaja.getEstado() + "\"}";
					return jsonData;
				}
			}  else {
					this.LOG.debug("no se encuentra la asignacion" + idResasignacionCaja);
					String jsonData = "{\"Result\":\"ERROR\",\"Message\":\"No se encuentra la asignacion de caja  :"
							+ idResasignacionCaja + "\"}";
					return jsonData;
				}
		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + e + "\"}";
			return error;
		}

	}

	@Override
	public String obtenerDatosCajaAsignada(String cajaId) {
		// Map Resultado;
		// Map datosAsignacion = new HashMap();

		Caja beanCaja= new Caja();
 
		AsignacionCaja asignacionCaja = asignacionCajaRepository.findByCajaAndEstado(cajaId, "01"); //buscamos la caja con estado 01 asignada

		String codigoAsignacion = asignacionCaja.getId(); 
		Double ingresosSoles = movimientoCajaRepository.sumMontoByAsignacionCajaAndTipMovimientoAndTipMoneda(codigoAsignacion, "I", "S");
		ingresosSoles = (ingresosSoles != null) ? ingresosSoles : 0.0;
		Double salidaSoles = movimientoCajaRepository.sumMontoByAsignacionCajaAndTipMovimientoAndTipMoneda(codigoAsignacion, "S", "S");
		salidaSoles = (salidaSoles != null) ? salidaSoles : 0.0;
		Double ingresosDolares = movimientoCajaRepository.sumMontoByAsignacionCajaAndTipMovimientoAndTipMoneda(codigoAsignacion, "I", "D");
		ingresosDolares = (ingresosDolares != null) ? ingresosDolares : 0.0;
		Double salidaDolares = movimientoCajaRepository.sumMontoByAsignacionCajaAndTipMovimientoAndTipMoneda(codigoAsignacion, "S", "D");
		salidaDolares = (salidaDolares != null) ? salidaDolares : 0.0;


		Double saldoActualSoles = (asignacionCaja.getMntoInicialSoles()+ingresosSoles ) - salidaSoles;
		Double saldoActualDolares = (asignacionCaja.getMntoInicialDolares()+ingresosDolares ) - salidaDolares;

		Double montoTotalSoles = asignacionCaja.getMntoInicialSoles()+ingresosSoles;
		
			String resasignacionCaja = gson.toJson(asignacionCaja);

		try {
		
		
			// String listadisplayString = gson.toJson(datosAsignacion);
			String jsonData = "{\"Result\":\"OK\",\"saldoActualSoles\":" 
			+ saldoActualSoles + ",\"saldoActualDolares\":" 
			+ saldoActualDolares +",\"ingresosDolares\":" 
			+ ingresosDolares +",\"salidaDolares\":" 
			+ salidaDolares +",\"montoTotalSoles\":" 
			+ montoTotalSoles +",\"resasignacionCaja\":" 
			+ resasignacionCaja +
			"}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;

		}
	}

	@Override
	public String obtenerDatosXLogin(String userId) {
		
        User userBean = new User();
        userBean.setId(userId);  
 
		String jsonData="";
		 
		
		AsignacionCaja datosAsignacion = asignacionCajaRepository.findByUserAndEstado(userBean, "01");
		
		if(datosAsignacion==null){
			jsonData = "{\"Result\":\"ERROR\",\"codmsg\":\"02\",\"msg\":\"Empleado no tiene caja asignada\"}";

			return jsonData;
		}		
		
		
		try {
			String listadisplayString = gson.toJson(datosAsignacion);
			jsonData = "{\"Result\":\"OK\",\"datosAsignacion\":" + listadisplayString + "}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;

		}
	}

	@Override
	public String findByUsuarioAsignadoAndAndEstado(String userId,String cod_estado) {

		try {
			User userBean = new User();
			userBean.setId(userId);  
	 
			String jsonData="";
		AsignacionCaja asignacionCaja = asignacionCajaRepository.findByUserAndEstado(userBean,cod_estado);

			String listadisplayString = gson.toJson(asignacionCaja);
			jsonData = "{\"Result\":\"OK\",\"datosAsignacion\":" + listadisplayString + "}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;

		}
		// TODO Auto-generated method stub 
	}

}
