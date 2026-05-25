package com.savitskiy.task2.service.impl;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.service.DublicateWordService;
import com.savitskiy.task2.util.ComponentUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class DublicateWordServiceImpl implements DublicateWordService {
    private static final Logger log = LogManager.getLogger(DublicateWordServiceImpl.class);

    @Override
    public int findmaxSentencesWithSameWord(CustomTextComponent component) {
        Map<String, Set<CustomTextComponent>> wordToSentencesMap = new HashMap<>();
        List<CustomTextComponent> sentences = ComponentUtil.getComponentsByType(component, ComponentType.SENTENCE);

        for (CustomTextComponent sentence : sentences) {
            List<CustomTextComponent> words = ComponentUtil.getComponentsByType(sentence, ComponentType.WORD);
            for (CustomTextComponent word : words) {
                String wordStr = word.reconstruct().toLowerCase();
                wordToSentencesMap.computeIfAbsent(wordStr,k-> new HashSet<>()).add(sentence);
            }
        }

        int max = 0;
        for (Set<CustomTextComponent> set : wordToSentencesMap.values()) {
            if (set.size() > max) {
                max = set.size();
            }
        }
        log.log(Level.INFO,"The maximum number of sentences containing the same word: {}", max);
        return max;
    }
    }
