package com.savitskiy.task2.parser;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.entity.TextComposite;

public class LexemeParser extends AbsractTextParser {
    private static final String LEXEME_REGEX = "\\s+";
    private static final LexemeParser LEXEME_PARSER = new LexemeParser(WordParser.getInstance());

    private LexemeParser(TextParser nextParser) {
        super(nextParser);
    }

    public static LexemeParser getInstance() {
        return LEXEME_PARSER;
    }

    @Override
    public void parse(CustomTextComponent component, String text) {
        String[] lexemes = text.split(LEXEME_REGEX);

        for (String lexeme : lexemes) {
            CustomTextComponent lexemeComponent = new TextComposite(ComponentType.LEXEME);
            component.add(lexemeComponent);

            if (textParser != null) {
                textParser.parse(lexemeComponent, lexeme);
            }
        }
    }
}
