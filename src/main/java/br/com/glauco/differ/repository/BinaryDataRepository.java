package br.com.glauco.differ.repository;

import br.com.glauco.differ.model.BinaryData;
import br.com.glauco.differ.model.FileId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;


public interface BinaryDataRepository extends JpaRepository<BinaryData, FileId> {

    ArrayList<BinaryData> findById_Id(Long id);

    int countById(FileId id);
}
