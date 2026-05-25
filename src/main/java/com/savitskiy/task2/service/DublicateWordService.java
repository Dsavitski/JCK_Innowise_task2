package com.savitskiy.task2.service;

import com.savitskiy.task2.entity.CustomTextComponent;

public interface DublicateWordService {
    int findmaxSentencesWithSameWord(CustomTextComponent component);
}
