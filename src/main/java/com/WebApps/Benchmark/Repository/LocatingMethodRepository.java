package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.LocatingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocatingMethodRepository extends JpaRepository<LocatingMethod, Integer> {

    public LocatingMethod getReferenceById(int id);

}
