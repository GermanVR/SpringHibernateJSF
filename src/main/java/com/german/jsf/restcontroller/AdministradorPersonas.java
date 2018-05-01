package com.german.jsf.restcontroller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.german.jsf.entidad.Persona;
import com.german.jsf.service.IPersonaService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class AdministradorPersonas {

	@Autowired
	private IPersonaService personaService;

	@RequestMapping(value = "/todo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String obtienePersonas() {
		log.info("Entre");
		List<Persona> p = personaService.obtieneListaPersonas();
		Map<String, List<Persona>> lp = new LinkedHashMap<>();
		lp.put("personas", p);
		return new JSONObject(lp) + "";
	}

	@RequestMapping(value = "/id", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String obtienePorId(long id) {
		Persona o = personaService.obtienePersonaPorId(id);
		log.info("El resultado es: {}", o);
		if (o == null) {
			return "No existe la Persona";
		}

		// log.info(o.toString());
		return new JSONObject(o) + "";
		// return "";
	}

}
