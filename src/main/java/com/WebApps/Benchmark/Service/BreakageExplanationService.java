package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageExplanationDTO;
import com.WebApps.Benchmark.Mapper.BreakageExplanationMapper;
import com.WebApps.Benchmark.Model.BreakageExplanation;
import com.WebApps.Benchmark.Repository.BreakageExplanationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreakageExplanationService {
    private final BreakageExplanationRepository breakageExplanationRepository;
    public BreakageExplanationService(BreakageExplanationRepository breakageExplanationRepository) {
        this.breakageExplanationRepository = breakageExplanationRepository;
    }

    public List<BreakageExplanationDTO> findAll(){
        List<BreakageExplanation> breakageExplanations = breakageExplanationRepository.findAll();
        return breakageExplanations.stream()
                .map(BreakageExplanationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BreakageExplanationDTO findById(int id){
        BreakageExplanation breakageExplanation = breakageExplanationRepository.getReferenceById(id);
        return BreakageExplanationMapper.toDTO(breakageExplanation);
    }

    public BreakageExplanationDTO save(BreakageExplanationDTO breakageExplanationDTO) {
        BreakageExplanation breakageExplanation = new BreakageExplanation();
        breakageExplanation.setExplanation(breakageExplanationDTO.getExplanation());
        breakageExplanationRepository.save(breakageExplanation);

        breakageExplanationDTO.setId(breakageExplanation.getId());
        return breakageExplanationDTO;
    }
}
