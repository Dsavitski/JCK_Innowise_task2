package com.savitskiy.task2.parser;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.entity.TextComposite;

public class SentenceParser extends AbsractTextParser {

    private static final String SENTENCE_REGEX = "(?<=[.!?])\\s+";
    private static final SentenceParser SENTENCE_PARSER = new SentenceParser(LexemeParser.getInstance());

    private SentenceParser(TextParser nextParser) {
        super(nextParser);
    }

    public static SentenceParser getInstance() {
        return SENTENCE_PARSER;
    }

    @Override
    public void parse(CustomTextComponent component, String text) {
        String[] sentences = text.split(SENTENCE_REGEX);

        for (String sentence : sentences) {
            CustomTextComponent sentenceComponent = new TextComposite(ComponentType.SENTENCE);
            component.add(sentenceComponent);

            if (textParser != null) {
                textParser.parse(sentenceComponent, sentence);
            }
        }
    }
}
