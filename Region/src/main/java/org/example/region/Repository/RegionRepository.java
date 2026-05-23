package org.example.region.Repository;

import org.example.region.Model.RegionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<RegionModel, Integer> {
}
