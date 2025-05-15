package com.WebApps.Benchmark.Compare;

import com.WebApps.Benchmark.Model.File;
import com.WebApps.Benchmark.Repository.FileRepository;
//import com.github.difflib.DiffUtils;
//import com.github.difflib.patch.AbstractDelta;
//import com.github.difflib.patch.DeltaType;
//import com.github.difflib.patch.Patch;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Service
public class CompareService {

    private final FileRepository fileRepository;

    public CompareService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public String getDiffOutput(String content1, String content2) {
        List<String> original = Arrays.asList(content1.split("\n"));
        List<String> revised = Arrays.asList(content2.split("\n"));

        /*Patch<String> patch = DiffUtils.diff(original, revised);

        StringBuilder diffOutput = new StringBuilder();

        for (AbstractDelta<String> delta : patch.getDeltas()) {
            DeltaType type = delta.getType();
            diffOutput.append("[").append(type).append("]\n");

            diffOutput.append("- ").append(String.join("\n- ", delta.getSource().getLines())).append("\n");
            diffOutput.append("+ ").append(String.join("\n+ ", delta.getTarget().getLines())).append("\n\n");
        }*/

        //return diffOutput.toString();
        return "";
    }

    public String compareFiles(Long fileId1, Long fileId2) {
        File file1 = fileRepository.findById(fileId1).orElseThrow();
        File file2 = fileRepository.findById(fileId2).orElseThrow();

        String content1 = new String(file1.getData(), StandardCharsets.UTF_8);
        String content2 = new String(file2.getData(), StandardCharsets.UTF_8);

        return getDiffOutput(content1, content2);
    }

}
