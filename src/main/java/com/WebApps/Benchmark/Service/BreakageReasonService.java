package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.BreakageReasonDTO;
import com.WebApps.Benchmark.Mapper.BreakageReasonMapper;
import com.WebApps.Benchmark.Model.BreakageReason;
import com.WebApps.Benchmark.Repository.BreakageReasonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BreakageReasonService {

    private final BreakageReasonRepository breakageReasonRepository;
    public BreakageReasonService(BreakageReasonRepository breakageReasonRepository) {
        this.breakageReasonRepository = breakageReasonRepository;
    }

    public List<BreakageReasonDTO> findAll(){
        List<BreakageReason> breakageReasons = breakageReasonRepository.findAll();
        return breakageReasons.stream()
                .map(BreakageReasonMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BreakageReasonDTO findById(int id){
        BreakageReason breakageReason = breakageReasonRepository.getReferenceById(id);
        return BreakageReasonMapper.toDTO(breakageReason);
    }

    public BreakageReasonDTO save(BreakageReasonDTO breakageReasonDTO) {
        BreakageReason breakageReason = new BreakageReason();
        breakageReason.setReasonName(breakageReasonDTO.getReasonName());
        breakageReasonRepository.save(breakageReason);

        breakageReasonDTO.setId(breakageReason.getId());
        return breakageReasonDTO;
    }

}
