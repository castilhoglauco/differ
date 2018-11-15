package br.com.glauco.differ.controller;

import br.com.glauco.differ.model.BinaryData;
import br.com.glauco.differ.model.BinarySet;
import br.com.glauco.differ.model.ResponseDTO;
import br.com.glauco.differ.service.DiffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DiffController {


    private DiffService diffService;

    @Autowired
    public DiffController(DiffService diffService) {
        this.diffService = diffService;
    }

    @PostMapping(value = "/v1/diff/{id}/left")
    public ResponseEntity receiveLeftData(@PathVariable("id") Long id, @RequestBody BinaryData leftData){
        HttpStatus status = diffService.saveLeftData(leftData, id);
        return new ResponseEntity(status);
    }

    @PostMapping(value = "/v1/diff/{id}/right")
    public ResponseEntity receiveRightData(@PathVariable("id") Long id, @RequestBody BinaryData rightData){
        HttpStatus status = diffService.saveRightData(rightData, id);
        return new ResponseEntity(status);
    }

    @GetMapping(value = "/v1/diff/{id}")
    public ResponseEntity<ResponseDTO> diffFiles(@PathVariable("id") Long id){
        return diffService.findDiff(id);
    }

    @PostMapping(value ="/v2/diff")
    public ResponseEntity<ResponseDTO> diffFiles(@RequestBody BinarySet binarySet){
        return diffService.receiveAndDiff(binarySet);
    }

}
