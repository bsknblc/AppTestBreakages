package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.RepairALineOfCodeDTO;
import com.WebApps.Benchmark.Mapper.RepairALineOfCodeMapper;
import com.WebApps.Benchmark.Model.RepairALineOfCode;
import com.WebApps.Benchmark.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairALineOfCodeService {

    private final RepairALineOfCodeRepository repairALineOfCodeRepository;
    private final BreakageRepository breakageRepository;
    private final LineOfCodeRepository lineOfCodeRepository;
    public RepairALineOfCodeService(RepairALineOfCodeRepository repairALineOfCodeRepository, BreakageRepository breakageRepository, LineOfCodeRepository lineOfCodeRepository) {
        this.repairALineOfCodeRepository = repairALineOfCodeRepository;
        this.breakageRepository = breakageRepository;
        this.lineOfCodeRepository = lineOfCodeRepository;
    }

    public List<RepairALineOfCodeDTO> findAll(){
        List<RepairALineOfCode> repairALineOfCodes = repairALineOfCodeRepository.findAll();
        return repairALineOfCodes.stream()
                .map(RepairALineOfCodeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RepairALineOfCodeDTO findById(int id){
        RepairALineOfCode repairALineOfCode = repairALineOfCodeRepository.getReferenceById(id);
        return RepairALineOfCodeMapper.toDTO(repairALineOfCode);
    }

    public RepairALineOfCodeDTO save(RepairALineOfCodeDTO repairALineOfCodeDTO) {
        RepairALineOfCode repairALineOfCode = new RepairALineOfCode();
        repairALineOfCode.setBreakage(breakageRepository.getReferenceById(repairALineOfCodeDTO.getBreakageID()));
        repairALineOfCode.setLineOfCode(lineOfCodeRepository.getReferenceById(repairALineOfCodeDTO.getLineOfCodeID()));
        repairALineOfCodeRepository.save(repairALineOfCode);

        repairALineOfCodeDTO.setId(repairALineOfCode.getId());
        return repairALineOfCodeDTO;
    }

}


