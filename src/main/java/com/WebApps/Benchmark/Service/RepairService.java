package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.RepairDTO;
import com.WebApps.Benchmark.Mapper.RepairMapper;
import com.WebApps.Benchmark.Model.Repair;
import com.WebApps.Benchmark.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairService {

    private final RepairRepository repairRepository;
    private final BreakageRepository breakageRepository;
    public RepairService(RepairRepository repairRepository, BreakageRepository breakageRepository) {
        this.repairRepository = repairRepository;
        this.breakageRepository = breakageRepository;
    }

    public List<RepairDTO> findAll(){
        List<Repair> repairs = repairRepository.findAll();
        return repairs.stream()
                .map(RepairMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RepairDTO findById(int id){
        Repair repair = repairRepository.getReferenceById(id);
        return RepairMapper.toDTO(repair);
    }

    public RepairDTO save(RepairDTO repairDTO) {
        Repair repair = new Repair();
        repair.setBreakage(breakageRepository.getReferenceById(repairDTO.getBreakageID()));
        repairRepository.save(repair);

        repairDTO.setId(repair.getId());
        return repairDTO;
    }

}


