package com.backbase.movies.service;


import com.backbase.movies.model.file.RecordModel;

import java.io.IOException;
import java.util.List;

public interface FileService {
    List<RecordModel> readFile(String fileName) throws IOException;


}
