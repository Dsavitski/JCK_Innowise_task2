package com.savitskiy.task2.reader.impl;

import com.savitskiy.task2.exception.CustomTextException;
import com.savitskiy.task2.reader.CustomTextReader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class CustomTextReaderImpl implements CustomTextReader {
    private static final Logger log = LogManager.getLogger(CustomTextReaderImpl.class);

    @Override
    public String read(String filepath) throws CustomTextException {
        if (filepath == null){
            log.log(Level.WARN, "CustomTextReaderImpl: filepath is null");
            throw new CustomTextException("filepath is null");
        }
        Path path = Paths.get(filepath);
        if (Files.notExists(path)){
            log.log(Level.WARN,"CustomTextReaderImpl: filepath does not exist");
            throw new CustomTextException("filepath does not exist");
        }

        try {
            String content = Files.readString(path);
            log.log(Level.INFO, "File successfully read");
            return content;
        } catch (IOException e) {
            log.log(Level.WARN, "Error reading file: {}",filepath);
            throw new CustomTextException("Error reading file: ", e);
        }

    }
}
