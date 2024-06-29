package com.ppanticona.service.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ppanticona.repository.EmpleadosRepository;
import com.ppanticona.domain.Empleados;
import org.slf4j.Logger;
import java.util.List;
import org.slf4j.LoggerFactory;
import com.ppanticona.service.EmpleadosService;

import org.springframework.stereotype.Service;
@Service
public class EmpleadosServiceImpl implements EmpleadosService{

    
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final EmpleadosRepository empleadosRepository; 

    public EmpleadosServiceImpl(EmpleadosRepository empleadosRepository ) {
        this.empleadosRepository = empleadosRepository; 
    }

    private final Logger log = LoggerFactory.getLogger(EmpleadosServiceImpl.class);
    @Override
    public String listarEmpleadosPorRolAndEstado(String cod_estado,String categoria) {
       
		List<Empleados> cajerosReg = empleadosRepository.findAllByEstadoAndCategoria(cod_estado,categoria);
        String listadisplayString = gson.toJson(cajerosReg);

		try {
			String jsonData = "{\"Result\":\"OK\",\"listaCajeros\":" + listadisplayString + "}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;
		}

    }

}
