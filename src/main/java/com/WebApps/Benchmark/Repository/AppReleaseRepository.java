package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.AppRelease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppReleaseRepository extends JpaRepository<AppRelease, Integer> {

    public AppRelease getReferenceById(int id);

}
