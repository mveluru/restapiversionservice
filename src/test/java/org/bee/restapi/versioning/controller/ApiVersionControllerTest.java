package org.bee.restapi.versioning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiVersionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void uriPathVersioning() throws Exception {
        mockMvc.perform(get("/apiversion/v1/api")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v1.0"));
        mockMvc.perform(get("/apiversion/v2/api")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v2.0"));
    }

    @Test
    void requestParamVersioning() throws Exception {
        mockMvc.perform(get("/apiversion/api").param("v1", "")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v1.0"));
        mockMvc.perform(get("/apiversion/api").param("v2", "")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v2.0"));
    }

    @Test
    void headerVersioning() throws Exception {
        mockMvc.perform(get("/apiversion/api").header("X-API-VERSION", "1")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v1.0"));
        mockMvc.perform(get("/apiversion/api").header("X-API-VERSION", "2")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v2.0"));
    }

    @Test
    void contentNegotiationVersioning() throws Exception {
        mockMvc.perform(get("/apiversion/api").accept("application/vnd.company.app-v1+json")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v1.0"));
        mockMvc.perform(get("/apiversion/api").accept("application/vnd.company.app-v2+json")).andExpect(status().isOk()).andExpect(jsonPath("$.apiversion").value("v2.0"));
    }
}
