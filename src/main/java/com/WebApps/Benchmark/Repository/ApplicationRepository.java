package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    public Application getReferenceById(int id);

//    @Query("SELECT a FROM Application AS a WHERE a.app_name= ?1")
//    public List<Application> findMovieByName(String appInfo);

}
