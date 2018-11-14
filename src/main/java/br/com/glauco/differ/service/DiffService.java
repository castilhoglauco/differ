package br.com.glauco.differ.service;

import br.com.glauco.differ.model.BinaryData;
import br.com.glauco.differ.model.FileId;
import br.com.glauco.differ.model.ResponseDTO;
import br.com.glauco.differ.model.SideEnum;
import br.com.glauco.differ.repository.BinaryDataRepository;
import name.fraser.neil.plaintext.diff_match_patch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;

@Service
public class DiffService {

    private BinaryDataRepository binaryDataRepository;

    @Autowired
    public DiffService(BinaryDataRepository binaryDataRepository) {
        this.binaryDataRepository = binaryDataRepository;
    }

    /**
     * Receives an object from the endpoint, adds an enum to it and saves
     * @param binaryData Binary data the user wants to diff
     * @param id a value selected by the user
     * @return An HttpStatus describing the situation
     */
    public HttpStatus saveLeftData(BinaryData binaryData, Long id){
        FileId fileId = new FileId(id, SideEnum.LEFT);
        binaryData.setId(fileId);
        return checkSave(binaryData);
    }

    /**
     * Receives an object from the endpoint, adds an enum to it and saves
     * @param binaryData Binary data the user wants to diff
     * @param id a value selected by the user
     * @return An HttpStatus describing the situation
     */
    public HttpStatus saveRightData(BinaryData binaryData, Long id){
        FileId fileId = new FileId(id, SideEnum.RIGHT);
        binaryData.setId(fileId);
        return checkSave(binaryData);
    }

    /**
     * Method to verify if there is already an id with the same value
     * @param binaryData Data send by the user
     * @return HttpStatus describing the situation
     */
    private HttpStatus checkSave(BinaryData binaryData){
        if(binaryDataRepository.countById(binaryData.getId()) > 0){
            return HttpStatus.BAD_REQUEST;
        }else{
            binaryDataRepository.save(binaryData);
            return HttpStatus.CREATED;
        }
    }

    /**
     * This method retrieves both files that have the same id
     * @param id Long value that identifies the files
     * @return A list with the two saved files
     */
    public ArrayList<BinaryData> find(Long id){
        return binaryDataRepository.findById_Id(id);
    }

    /**
     * This method retrieves 2 files from the database and verify their differences
     * @param id Long value that identifies the files
     * @return A ResponseEntity containing a ResponseDTO and a HttpStatus
     */
    public ResponseEntity<ResponseDTO> findDiff(Long id) {
        ResponseDTO responseDTO = new ResponseDTO();
        HttpStatus status = HttpStatus.OK;
        ArrayList<BinaryData> files = find(id);
        if(files.size() > 1){

            diff_match_patch differ = new diff_match_patch();
            LinkedList<diff_match_patch.Diff> result = differ.diff_main(files.get(0).getData(), files.get(1).getData());
            differ.diff_cleanupSemantic(result);

            if(files.get(0).getData().length() != files.get(1).getData().length()){
                responseDTO.setResult("The files do not have the same size");
            }else{

                if(result.size() == 1 && result.get(0).operation.equals(diff_match_patch.Operation.EQUAL) ){
                    responseDTO.setResult("Both files are equal");
                }else{

                    int insertChar = 0;
                    int deleteChar = 0;

                    for (diff_match_patch.Diff diff: result) {
                        if(diff_match_patch.Operation.INSERT.equals(diff.operation)){
                            insertChar +=diff.text.length();
                        }else if (diff_match_patch.Operation.DELETE.equals(diff.operation)){
                            deleteChar +=diff.text.length();
                        }
                    }

                    responseDTO.setResult("There are some differences in the files. " + insertChar + " Characters were inserted and "
                            + deleteChar + " characters were deleted");
                }
            }
        }else{
            responseDTO.setResult("File not found");
            status =  HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(responseDTO, status);

    }
}
