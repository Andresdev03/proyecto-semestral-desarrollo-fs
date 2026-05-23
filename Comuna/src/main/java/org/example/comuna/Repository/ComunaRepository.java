package org.example.comuna.Repository;

import org.example.comuna.Model.ComunaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComunaRepository extends JpaRepository<ComunaModel, Integer> {
    List<ComunaModel> findByIdRegion(Integer idRegion);
}
