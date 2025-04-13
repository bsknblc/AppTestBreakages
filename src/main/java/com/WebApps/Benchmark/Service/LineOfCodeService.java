package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.LineOfCodeDTO;
import com.WebApps.Benchmark.Mapper.LineOfCodeMapper;
import com.WebApps.Benchmark.Model.LineOfCode;
import com.WebApps.Benchmark.Repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LineOfCodeService {

    private final LineOfCodeRepository lineOfCodeRepository;
    private final AppPageRepository appPageRepository;
    private final TestCaseVersionRepository testCaseVersionRepository;
    public LineOfCodeService(LineOfCodeRepository lineOfCodeRepository, AppPageRepository appPageRepository, TestCaseVersionRepository testCaseVersionRepository) {
        this.lineOfCodeRepository = lineOfCodeRepository;
        this.appPageRepository = appPageRepository;
        this.testCaseVersionRepository = testCaseVersionRepository;
    }

    public List<LineOfCodeDTO> findAll(){
        List<LineOfCode> lineOfCodes = lineOfCodeRepository.findAll();
        return lineOfCodes.stream()
                .map(LineOfCodeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LineOfCodeDTO findById(int id){
        LineOfCode lineOfCode = lineOfCodeRepository.getReferenceById(id);
        return LineOfCodeMapper.toDTO(lineOfCode);
    }

    public LineOfCodeDTO save(LineOfCodeDTO lineOfCodeDTO) {
        LineOfCode lineOfCode = new LineOfCode();
        lineOfCode.setAppPage(appPageRepository.getReferenceById(lineOfCodeDTO.getAppPageID()));
        lineOfCode.setTestCaseVersion(testCaseVersionRepository.getReferenceById(lineOfCodeDTO.getTestCaseVersionID()));
        lineOfCodeRepository.save(lineOfCode);

        lineOfCodeDTO.setId(lineOfCode.getId());
        return lineOfCodeDTO;
    }

}

