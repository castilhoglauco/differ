package br.com.glauco.differ;

import br.com.glauco.differ.model.BinaryData;
import br.com.glauco.differ.model.BinarySet;
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
        Assert.assertEquals("The files do not have the same size. " +
                "The left one has 382 characters and the right one has 346 characters.", ((LinkedHashMap) response).get("result"));
    }

    @Test
    public void testV2EndPoint(){
        String URL = "http://localhost:8080/v2/diff";
        RestTemplate restTemplate = new RestTemplate();
        BinarySet binarySet = new BinarySet();
        binarySet.setData1("+VmmTsFDYg5oyg4pbyuaHy6olZ1CY4rqMVrqNHAEYoN1LMlJlKmKADRgaIRQiuOFgRRuKSBo1LYbDk0TOa" +
                "E9KLTJgsNQggVwoaNhsHNFNGFdigFsJijAUcLQ4rhbCbaMKHFARXWcDnNDiig4oQaIQCKL0o55otAAGa7NBihC1x1AjmjYo" +
                "BRga4J3bFCOldQCikcGrs0Whrjjs0Ga6uxQCDXV2aCuOo7NBXGgpQWDXYoOaMBQOOFGAoBxRxRTAF20UrSuK7bTHCG2u20vtx" +
                "RdvNcASA5o2KUKcUXaaITgKMBXAEV1ckcCBRsVwOBRS3NMggk0Uih6igzXM4AjNEZKPmgJpW");

        binarySet.setData2("+VmmTsFDYg5oyg4pbyuaHy6olZ1CY4rqMVrqNHAEYoN1LMlJlKmKADRgaIRQiuOFgRRuKSBo1LYbDk0TOa" +
                "E9KLTJgsNQggVwoaNhsHNFNGFdigFsJijAUcLQ4rhbCbaMKHFARXWcDnNDiig4oQaIQCKL0o55otAAGa7NBihC1x1AjmjYo" +
                "BRga4J3bFCOldQCikcGrs0Whrjjs0Ga6uxQCDXV2aCuOo7NBXGgpQWDXYoOaMBQOOFGAoBxRxRTAF20UrSuK7bTHCG2u20vtx" +
                "RdvNcASA5o2KUKcUXaaITgKMBXAEV1ckcCBRsVwOBRS3NMggk0Uih6igzXM4AjNEZKPmgJpW");

        Object response = restTemplate.postForObject(URL,binarySet, Object.class);
        String expectedResponse = "[Diff(EQUAL,\"+VmmTsFDYg5oyg4pbyuaHy6olZ1CY4rqMVrqNHAEYoN1LMlJlKmKADRgaIRQiuOFgRRuKSBo1LYbDk0TOaE9KLTJgsNQggVwoaNhsHNFNGFdigFsJijAUcLQ4rhbCbaMKHFARXWcDnNDiig4oQaIQCKL0o55otAAGa7NBihC1x1AjmjYoBRga4J3bFCOldQCikcGrs0Whrjjs0Ga6uxQCDXV2aCuOo7NBXGgpQWDXYoOaMBQOOFGAoBxRxRTAF20UrSuK7bTHCG2u20vtxRdvNcASA5o2KUKcUXaaITgKMBXAEV1ckcCBRsVwOBRS3NMggk0Uih6igzXM4AjNEZKPmgJpW\")]";
        Assert.assertEquals(expectedResponse, ((LinkedHashMap) response).get("result"));
        //todo ajustar este retorno
    }




}
