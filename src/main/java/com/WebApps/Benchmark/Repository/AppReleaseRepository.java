package com.WebApps.Benchmark.Repository;

import com.WebApps.Benchmark.Model.AppRelease;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppReleaseRepository extends JpaRepository<AppRelease, Integer> {

    public AppRelease getReferenceById(int id);

    List<AppRelease> findByApplication_Id(@NotNull int applicationId);
}
