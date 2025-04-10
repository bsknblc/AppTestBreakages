package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.AppPage;
import com.WebApps.Benchmark.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppPageRepository extends JpaRepository<AppPage, Integer> {

    public AppPage getReferenceById(int id);

}
