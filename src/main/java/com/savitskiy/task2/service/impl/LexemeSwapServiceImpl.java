package com.savitskiy.task2.service.impl;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.service.LexemeSwapService;
import com.savitskiy.task2.util.ComponentUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class LexemeSwapServiceImpl implements LexemeSwapService {
    private static final Logger log = LogManager.getLogger(LexemeSwapServiceImpl.class);

    @Override
    public void swapLexemes(CustomTextComponent component) {
        List<CustomTextComponent> sentences = ComponentUtil.getComponentsByType(component, ComponentType.SENTENCE);

        for (CustomTextComponent sentence : sentences) {
            List<CustomTextComponent> lexemes = new ArrayList<>();
            for (CustomTextComponent child : sentence.getChildren()) {
                if (child.getType() == ComponentType.LEXEME) {
                    lexemes.add(child);
                }
            }

            if (lexemes.size() >= 2) {
                CustomTextComponent firstLexeme = lexemes.getFirst();
                CustomTextComponent lastLexeme = lexemes.getLast();

                List<CustomTextComponent> originalChildren = sentence.getChildren();
                for (CustomTextComponent child : originalChildren) {
                    sentence.remove(child);
                }

                for (CustomTextComponent child : originalChildren) {
                    if (child.equals(firstLexeme)) {
                        sentence.add(lastLexeme);
                    } else if (child.equals(lastLexeme)) {
                        sentence.add(firstLexeme);
                    } else {
                        sentence.add(child);
                    }
                }
            }
        }

        log.log(Level.INFO,"The first and last lexemes have been successfully swapped in all sentences");
    }
}

