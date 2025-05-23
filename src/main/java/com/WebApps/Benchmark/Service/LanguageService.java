package com.WebApps.Benchmark.Service;

import com.WebApps.Benchmark.DTO.LanguageDTO;
import com.WebApps.Benchmark.DTO.LocatingMethodDTO;
import com.WebApps.Benchmark.Mapper.LanguageMapper;
import com.WebApps.Benchmark.Mapper.LocatingMethodMapper;
import com.WebApps.Benchmark.Model.Language;
import com.WebApps.Benchmark.Model.LocatingMethod;
import com.WebApps.Benchmark.Repository.LanguageRepository;
import com.WebApps.Benchmark.Repository.LocatingMethodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class LanguageService {
    private final LanguageRepository languageRepository;
    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<LanguageDTO> findAll(){
        List<Language> languages = languageRepository.findAll();
        return languages.stream()
                .map(LanguageMapper::toDTO)
                .collect(Collectors.toList());
    }

    public LanguageDTO findById(int id){
        Language language = languageRepository.getReferenceById(id);
        return LanguageMapper.toDTO(language);
    }

    public LanguageDTO save(LanguageDTO languageDTO) {
        Language language = new Language();
        language.setLanguageName(languageDTO.getLanguageName());
        languageRepository.save(language);

        languageDTO.setId(language.getId());
        return languageDTO;
    }
}
