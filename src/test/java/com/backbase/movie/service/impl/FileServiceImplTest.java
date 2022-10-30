package com.backbase.movie.service.impl;


import com.backbase.movies.model.file.RecordModel;
import com.backbase.movies.service.impl.FileServiceImpl;
import com.backbase.movies.utils.Constants;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileServiceImplTest {

    private FileServiceImpl fileService = new FileServiceImpl();

    @Test
    void readFile() throws IOException, URISyntaxException {
        List<RecordModel> recordModelList = fileService.readFile(Constants.INPUT_FILEPATH);
        assertEquals(true, recordModelList.size() > 0);
    }
}