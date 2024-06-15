package com.ppanticona.service.impl;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ppanticona.domain.Mesas;
import com.ppanticona.repository.AsignacionCajaRepository;
import com.ppanticona.repository.CajaRepository;
import com.ppanticona.repository.MesasRepository;
import com.ppanticona.service.MesasService;

import java.util.List;

import org.springframework.stereotype.Service;


@Service
public class MesasServiceImpl implements MesasService {

	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final MesasRepository mesasRepository;

    public MesasServiceImpl(MesasRepository mesasRepository ) {
        this.mesasRepository = mesasRepository; 
    }

    @Override
    public String listarMesasPorEstado(String cod_estado) {


		List<Mesas> mesasReg = mesasRepository.findAllByEstado(cod_estado);
        String listadisplayString = gson.toJson(mesasReg);

		try {
			String jsonData = "{\"Result\":\"OK\",\"listaMesas\":" + listadisplayString + "}";

			return jsonData;

		} catch (Exception e) {
			String error = "{\"Result\":\"ERROR\",\"Message\":\"" + e.getMessage() + "\"}";
			return error;
		}
    }
 

}
