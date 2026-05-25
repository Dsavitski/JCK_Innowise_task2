package com.savitskiy.task2.service.impl;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.reader.impl.CustomTextReaderImpl;
import com.savitskiy.task2.service.SentenceSortService;
import com.savitskiy.task2.util.ComponentUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SentenceSortServiceImpl implements SentenceSortService {
    private static final Logger log = LogManager.getLogger(SentenceSortServiceImpl.class);

    @Override
    public List<CustomTextComponent> sortByLetterCount(CustomTextComponent customTextComponent, char letter) {

        List<CustomTextComponent> sentences = ComponentUtil.getComponentsByType(customTextComponent, ComponentType.SENTENCE);
        final char lowerTarget = Character.toLowerCase(letter);

        sentences.sort((s1, s2) -> {
            int count1 = countLetterInComponent(s1, lowerTarget);
            int count2 = countLetterInComponent(s2, lowerTarget);
            return Integer.compare(count1, count2);
        });

        log.log(Level.INFO,"The suggestions are sorted by letter: '{}'", letter);
        return sentences;
    }

    private int countLetterInComponent(CustomTextComponent component, char targetLetter) {
        int count = 0;
        String text = component.reconstruct().toLowerCase();
        for (char c : text.toCharArray()) {
            if (c == targetLetter) {
                count++;
            }
        }
        return count;
    }
}
