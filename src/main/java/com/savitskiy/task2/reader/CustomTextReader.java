package com.savitskiy.task2.reader;
import com.savitskiy.task2.exception.CustomTextException;

public interface CustomTextReader {
    String read(String filepath) throws CustomTextException;
}
