package com.ppanticona.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ppanticona.domain.Caja;
import com.ppanticona.domain.AsignacionCaja;
import com.ppanticona.domain.Empleados;
import com.ppanticona.repository.CajaRepository;
import com.ppanticona.repository.AsignacionCajaRepository;
import com.ppanticona.service.CajaService;
import java.time.ZonedDateTime;
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
        try {
            this.log.debug("caja =  ");
            this.log.debug(String.valueOf(datosMap.get("caja")));
            String usuCrea = String.valueOf(datosMap.get("usuCrea"));
            ZonedDateTime fecCrea = ZonedDateTime.parse(String.valueOf(datosMap.get("fecCrea")));
            final ObjectMapper mapper = new ObjectMapper(); // jackson's objectmapper
            mapper.registerModule(new JSR310Module());
            Map<String, Object> cajaMap = (Map<String, Object>) datosMap.get("caja");
            Caja cajaBean = mapper.convertValue(cajaMap, Caja.class);

            cajaBean.setEstado("02");
            cajaBean.setUsuModif(usuCrea);
            cajaBean.setFecModif(fecCrea);

            Caja res = this.cajaRepository.save(cajaBean);

            AsignacionCaja asignacionCajaBean = new AsignacionCaja();
            
            Empleados empleadoBean = new Empleados();

            empleadoBean.setIndDel(false);
            empleadoBean.setEstado("01");
            empleadoBean.setUsuCrea(login);  
            empleadoBean.setVersion(1);
            
            asignacionCajaBean.setEstado("01"); //   01 activa ;  02 historica 
            asignacionCajaBean.setMontoMaximoSoles(Double.valueOf(String.valueOf(datosMap.get("montoMaximoSoles"))));
            asignacionCajaBean.setCaja(cajaBean);
            asignacionCajaBean.setUsuCrea(usuCrea);
            asignacionCajaBean.setFecCrea(fecCrea);
            asignacionCajaBean.setVersion(1);
            asignacionCajaBean.setIndDel(false);
            asignacionCajaBean.ipCrea("0.0.0.0");
            asignacionCajaBean.setUserId(empleadoBean);

            this.asignacionCajaRepository.save(asignacionCajaBean);

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
