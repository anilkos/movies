package com.backbase.movies.service.impl;

import com.backbase.movies.model.file.RecordModel;
import com.backbase.movies.service.FileService;
import com.backbase.movies.utils.Constants;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<RecordModel> readFile(String fileName) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream resource = classLoader.getResourceAsStream(fileName);
        Reader reader =  new InputStreamReader(resource);
        CSVReader csvReader = new CSVReaderBuilder(reader)
                .withSkipLines(1)// Skip the  first line - header
                .build();
        return csvReader.readAll().stream().map(this::getModel)
                .collect(Collectors.toList());
    }

    private RecordModel getModel(final String[] data) {
        return RecordModel.builder()
                .year(data[0].substring(0, 4))
                .category(data[1])
                .nominee(data[2])
                .additionalInfo(data[3])
                .won(data[4].equals(Constants.YES))
                .build();
    }


}
