package com.dubic.farm;

import com.dubic.farm.api.requests.PlantRequest;
import com.dubic.farm.models.CropType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FarmApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void submitPlanted() throws Exception {
		PlantRequest pl = new PlantRequest(CropType.CORN, "Farm A", 5, 2023);
		this.mockMvc.perform(post("/api/v1/farm")
						.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(pl)))
				.andDo(print())
				.andExpect(status().isOk());
	}
}
