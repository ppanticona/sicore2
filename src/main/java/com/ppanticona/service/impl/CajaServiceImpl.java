package com.ppanticona.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ppanticona.domain.Caja;
import com.ppanticona.domain.Empleados;
import com.ppanticona.domain.AsignacionCaja;
import com.ppanticona.domain.User;
import com.ppanticona.repository.CajaRepository;
import com.ppanticona.repository.AsignacionCajaRepository;
import com.ppanticona.service.CajaService;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CajaServiceImpl implements CajaService {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final CajaRepository cajaRepository;
    private final AsignacionCajaRepository asignacionCajaRepository;

    public CajaServiceImpl(CajaRepository cajaRepository, AsignacionCajaRepository asignacionCajaRepository) {
        this.cajaRepository = cajaRepository;
        this.asignacionCajaRepository = asignacionCajaRepository;
    }

    private final Logger log = LoggerFactory.getLogger(CajaService.class);

	@Override
	public String listarCajasPorEstado(String cod_estado) {



		List<Caja> cajasReg = cajaRepository.findAllByEstado(cod_estado);
        String listadisplayString = gson.toJson(cajasReg);

		try {
			String jsonData = "{\"Result\":\"OK\",\"listaCajas\":" + listadisplayString + "}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;
		}

	}
    @Override
    public String aperturarCaja(Map datosMap, String login) {


		AsignacionCaja asignacionCajaBean = new AsignacionCaja();
		
		Caja cajaBean = new Caja(); 
        User userBean = new User();
        userBean.setId(String.valueOf(((Map<String, Object>) datosMap.get("empleado")).get("id"))); 
		
		
		Double MontoInicialSoles = null;
		if (datosMap.get("MontoInicialSoles") instanceof Double) {
			MontoInicialSoles = (double) datosMap.get("MontoInicialSoles");
		} else if (datosMap.get("MontoInicialSoles") instanceof Integer) {
			Integer entero = (Integer) datosMap.get("MontoInicialSoles");
			MontoInicialSoles = (double) entero;
		}
		



		Double MontoInicialDolares = null;
		if (datosMap.get("MontoInicialDolares") instanceof Double) {
			MontoInicialDolares = (double) datosMap.get("MontoInicialDolares");
		} else if (datosMap.get("MontoInicialDolares") instanceof Integer) {
			Integer entero = (Integer) datosMap.get("MontoInicialDolares");
			MontoInicialDolares = (double) entero;
		}
	 
		asignacionCajaBean.setMntoInicialSoles(MontoInicialSoles);
		asignacionCajaBean.setMntoInicialDolares(MontoInicialDolares);
		cajaBean.setId((String) datosMap.get("NroCaja"));
		asignacionCajaBean.setCaja(cajaBean);
		asignacionCajaBean.setObservacionApertura((String) datosMap.get("Observaciones"));

		asignacionCajaBean.setIndDel(false);
		asignacionCajaBean.setEstado("01");
		asignacionCajaBean.setUsuCrea(login);
		asignacionCajaBean.codAsignacion("01");  // está mal, en su lugar estoy usando el ID 
		asignacionCajaBean.setVersion(1);
        asignacionCajaBean.setUser(userBean);
		 
     
		try {
			

			AsignacionCaja resApertura = asignacionCajaRepository.save(asignacionCajaBean);   //se registra la asignacion con estado 01 asignada 
			Optional<Caja> optionalCaja = cajaRepository.findById((String)datosMap.get("NroCaja"));

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
    public String cerrarCaja(Map datosMap, String login) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JSR310Module());

            Map<String, Object> cajaMap = (Map<String, Object>) datosMap.get("caja");

            Caja cajaBean = objectMapper.convertValue(cajaMap, Caja.class);

            cajaBean.setEstado("01");

            Caja res = this.cajaRepository.save(cajaBean);

            String jsonData = "{\"Result\":\"OK\",\"caja\":\"" + res.toString() + "\"}";

            return jsonData;
        } catch (Exception e) {
            this.log.debug("Ocurrió un error durante la apertura de la caja ");
            String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";

            this.log.error("ERROR : " + e.getMessage(), e);
            return error;
        }
    }
    @Override
    public String listarCajasParaAsignar() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'listarCajasParaAsignar'");
    }
}
