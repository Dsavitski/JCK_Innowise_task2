package com.savitskiy.task2.service;

import com.savitskiy.task2.entity.CustomTextComponent;

import java.util.List;

public interface SentenceSortService {
    List<CustomTextComponent> sortByLetterCount(CustomTextComponent customTextComponent,char letter);
}
