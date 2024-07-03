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
