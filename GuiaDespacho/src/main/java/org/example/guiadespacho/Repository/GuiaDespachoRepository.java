package org.example.guiadespacho.Repository;

import org.example.guiadespacho.Model.GuiaDespachoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuiaDespachoRepository extends JpaRepository<GuiaDespachoModel, Integer> {
}
