package org.example.ingrediente;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.example.ingrediente.Model.IngredienteModel;
import org.example.ingrediente.Service.IngredienteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class IngredienteApplicationTests {

	@Autowired
	private IngredienteService ingredienteService;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Revisando")
	void test1() {
		IngredienteModel ingredienteModel = ingredienteService.getIngredienteById(1);
		log.info(ingredienteModel.toString());

	}
}
