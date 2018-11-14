package br.com.glauco.differ;

import br.com.glauco.differ.model.BinaryData;
import br.com.glauco.differ.model.FileId;
import br.com.glauco.differ.model.SideEnum;
import br.com.glauco.differ.repository.BinaryDataRepository;
import br.com.glauco.differ.service.DiffService;
import name.fraser.neil.plaintext.diff_match_patch;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UnitTests {

    @Autowired
    private DiffService diffService;

    @Autowired
    private BinaryDataRepository binaryDataRepository;


    @Test
    public void testSaveLeft(){
        BinaryData dataToSave = new BinaryData();
        dataToSave.setData("Data 1");
        diffService.saveLeftData(dataToSave, 1L);
        FileId fileId = new FileId(1L, SideEnum.LEFT);
        Optional<BinaryData> readData = binaryDataRepository.findById(fileId);
        Assert.assertEquals("Data 1", readData.get().getData());
    }

    @Test
    public void testSaveRight(){
        BinaryData dataToSave = new BinaryData();
        dataToSave.setData("Data 2");
        diffService.saveRightData(dataToSave, 1L);
        FileId fileId = new FileId(1L, SideEnum.RIGHT);
        Optional<BinaryData> readData = binaryDataRepository.findById(fileId);
        Assert.assertEquals("Data 2", readData.get().getData());
    }

    @Test
    public void testReadFiles(){
        BinaryData dataToSave1 = new BinaryData();
        dataToSave1.setData("Data 3");
        diffService.saveLeftData(dataToSave1, 2L);

        BinaryData dataToSave2 = new BinaryData();
        dataToSave2.setData("Data 4");
        diffService.saveRightData(dataToSave2, 2L);

        ArrayList<BinaryData> files = diffService.find(2L);
        BinaryData binaryData1 =  files.get(0);
        BinaryData binaryData2 =  files.get(1);
        Assert.assertEquals("Data 3", binaryData1.getData());
        Assert.assertEquals("Data 4", binaryData2.getData());
    }



    @Test
    public void testCheckSave(){
        BinaryData dataToSave1 = new BinaryData();
        dataToSave1.setData("Data 5");
        diffService.saveLeftData(dataToSave1, 3L);

        BinaryData dataToSave2 = new BinaryData();
        dataToSave2.setData("Data 6");
        HttpStatus status = diffService.saveLeftData(dataToSave2, 3L);
        Assert.assertEquals(HttpStatus.BAD_REQUEST, status);
    }

}
