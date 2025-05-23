package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.RepairExplanationDTO;
import com.WebApps.Benchmark.Mapper.RepairExplanationMapper;
import com.WebApps.Benchmark.Model.RepairExplanation;
import com.WebApps.Benchmark.Repository.RepairExplanationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RepairExplanationService {
    
    private final RepairExplanationRepository repairExplanationRepository;
    public RepairExplanationService(RepairExplanationRepository repairExplanationRepository) {
        this.repairExplanationRepository = repairExplanationRepository;
    }

    public List<RepairExplanationDTO> findAll(){
        List<RepairExplanation> repairExplanations = repairExplanationRepository.findAll();
        return repairExplanations.stream()
                .map(RepairExplanationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public RepairExplanationDTO findById(int id){
        RepairExplanation repairExplanation = repairExplanationRepository.getReferenceById(id);
        return RepairExplanationMapper.toDTO(repairExplanation);
    }

    public RepairExplanationDTO save(RepairExplanationDTO repairExplanationDTO) {
        RepairExplanation repairExplanation = new RepairExplanation();
        repairExplanation.setExplanation(repairExplanationDTO.getExplanation());
        repairExplanationRepository.save(repairExplanation);

        repairExplanationDTO.setId(repairExplanation.getId());
        return repairExplanationDTO;
    }
}
