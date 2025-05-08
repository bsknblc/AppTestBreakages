package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.LocatingMethodDTO;
import com.WebApps.Benchmark.Mapper.LocatingMethodMapper;
import com.WebApps.Benchmark.Model.LocatingMethod;
import com.WebApps.Benchmark.Repository.LocatingMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocatingMethodService {

    private final LocatingMethodRepository locatingMethodRepository;
    public LocatingMethodService(LocatingMethodRepository locatingMethodRepository) {
        this.locatingMethodRepository = locatingMethodRepository;
    }

    public List<LocatingMethodDTO> findAll(){
        List<LocatingMethod> locatingMethods = locatingMethodRepository.findAll();
        return locatingMethods.stream()
                .map(LocatingMethodMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LocatingMethodDTO findById(int id){
        LocatingMethod locatingMethod = locatingMethodRepository.getReferenceById(id);
        return LocatingMethodMapper.toDTO(locatingMethod);
    }

    public LocatingMethodDTO save(LocatingMethodDTO locatingMethodDTO) {
        LocatingMethod locatingMethod = new LocatingMethod();
        locatingMethod.setLocatingMethodName(locatingMethodDTO.getLocatingMethodName());
        locatingMethodRepository.save(locatingMethod);

        locatingMethodDTO.setId(locatingMethod.getId());
        return locatingMethodDTO;
    }

}
