package com.sa1zer.simpleanalyzer.metricsconsumer.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sa1zer.simpleanalyzer.metricsconsumer.entity.Report;
import com.sa1zer.simpleanalyzer.metricsconsumer.repo.ReportRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportControllerTest {

    @Autowired
    private ReportRepo repo;

    @Autowired
    private MockMvc mockMvc;

    private static final ObjectMapper mapper = JsonMapper.builder()
            .findAndAddModules()
            .build();

    @BeforeEach
    void before() {
        repo.deleteAll();
    }

    @Test
    void report() throws Exception {
        Report save = repo.save(Report.builder()
                .jvmMax(1.0)
                .jvmUsed(1.0)
                .uptime(1.0)
                .totalSpace(1.0)
                .usableSpace(1.0)
                .dateTime(LocalDateTime.now())
                .build());

        String contentAsString = mockMvc.perform(get("/api/metrics/" + save.getId()))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Report reportDto = mapper.readValue(contentAsString, Report.class);

        assertAll(() -> {
           assertEquals(save.getId(), reportDto.getId());
           assertEquals(save.getJvmMax(), reportDto.getJvmMax());
           assertEquals(save.getTotalSpace(), reportDto.getTotalSpace());
        });
    }

    @Test
    void findAll() throws Exception {
        Report save = repo.save(Report.builder()
                .jvmMax(1.0)
                .jvmUsed(1.0)
                .uptime(1.0)
                .totalSpace(1.0)
                .usableSpace(1.0)
                .dateTime(LocalDateTime.now())
                .build());

        String contentAsString = mockMvc.perform(get("/api/metrics/all"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn().getResponse().getContentAsString();

        Report[] list = mapper.readValue(contentAsString, Report[].class);

        assertEquals(1, list.length);
    }
}