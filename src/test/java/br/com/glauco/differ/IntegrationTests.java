package br.com.glauco.differ;

import br.com.glauco.differ.model.BinaryData;
import br.com.glauco.differ.model.ResponseDTO;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class IntegrationTests {

    @Test
    public void testFirstLeftEndPoint(){
        String URL = "http://localhost:8080/v1/diff/10/left";
        RestTemplate restTemplate = new RestTemplate();
        BinaryData binaryData = new BinaryData();
        binaryData.setData("+VmmTsFDYg5oyg4pbyuaHy6olZ1CY4rqMVrqNHAEYoN1LMlJlKmKADRgaIRQiuOFgRRuKSBo1LYbDk0TOa" +
                "E9KLTJgsNQggVwoaNhsHNFNGFdigFsJijAUcLQ4rhbCbaMKHFARXWcDnNDiig4oQaIQCKL0o55otAAGa7NBihC1x1AjmjYo" +
                "BRga4J3bFCOldQCikcGrs0Whrjjs0Ga6uxQCDXV2aCuOo7NBXGgpQWDXYoOaMBQOOFGAoBxRxRTAF20UrSuK7bTHCG2u20vtx" +
                "RdvNcASA5o2KUKcUXaaITgKMBXAEV1ckcCBRsVwOBRS3NMggk0Uih6igzXM4AjNEZKPmgJpWFCRSkytLnpmk2NKcIkUWjtRDRABmgNDXGgcB");
        ResponseEntity responseEntity = restTemplate.postForEntity(URL,binaryData, ResponseEntity.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testFirstRightEndPoint(){
        String URL = "http://localhost:8080/v1/diff/10/right";
        RestTemplate restTemplate = new RestTemplate();
        BinaryData binaryData = new BinaryData();
        binaryData.setData("+VmmTsFDYg5oyg4pbyuaHy6olZ1CY4rqMVrqNHAEYoN1LMlJlKmKADRgaIRQiuOFgRRuKSBo1LYbDk0TOa" +
                "E9KLTJgsNQggVwoaNhsHNFNGFdigFsJijAUcLQ4rhbCbaMKHFARXWcDnNDiig4oQaIQCKL0o55otAAGa7NBihC1x1AjmjYo" +
                "BRga4J3bFCOldQCikcGrs0Whrjjs0Ga6uxQCDXV2aCuOo7NBXGgpQWDXYoOaMBQOOFGAoBxRxRTAF20UrSuK7bTHCG2u20vtx" +
                "RdvNcASA5o2KUKcUXaaITgKMBXAEV1ckcCBRsVwOBRS3NMggk0Uih6igzXM4AjNEZKPmgJpW");
        ResponseEntity responseEntity = restTemplate.postForEntity(URL,binaryData, ResponseEntity.class);
        Assert.assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
    }

    @Test
    public void testLastDiffEndPoint(){
        String URL = "http://localhost:8080/v1/diff/10";
        RestTemplate restTemplate = new RestTemplate();
        Object response = restTemplate.getForObject(URL, Object.class);
        Assert.assertEquals("The files do not have the same size", ((LinkedHashMap) response).get("result"));
    }




}
