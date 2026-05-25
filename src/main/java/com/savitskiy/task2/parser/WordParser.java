package com.savitskiy.task2.parser;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.SymbolLeaf;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.entity.TextComposite;

public class WordParser extends AbsractTextParser{
    private static final String WORD_PUNCTUATION_REGEX = "(?<=\\b)(?=\\p{Punct})|(?<=\\p{Punct})(?=\\b)";
    private static final WordParser WORD_PARSER = new WordParser(null);

    public WordParser(TextParser textParser) {
        super(textParser);
    }

    public static WordParser getInstance() {
        return WORD_PARSER;
    }

    @Override
    public void parse(CustomTextComponent component, String text) {
        String[] parts = text.split(WORD_PUNCTUATION_REGEX);
        for(String part : parts){
            ComponentType type = part.matches("\\p{Punct}+") ? ComponentType.PUNCTUATION : ComponentType.WORD;
            CustomTextComponent partComponent = new TextComposite(type);
            component.add(partComponent);

            for (char ch : part.toCharArray()) {
                partComponent.add(new SymbolLeaf(ComponentType.SYMBOL, ch));
            }
        }
    }
}
