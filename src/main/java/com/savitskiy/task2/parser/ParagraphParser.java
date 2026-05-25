package com.savitskiy.task2.parser;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.entity.TextComposite;

public class ParagraphParser extends AbsractTextParser {
    private static final String PARAGRAPH_REGEX = "(?m)(?=^(\\t| {4}))";
    private static final ParagraphParser PARAGRAPH_PARSER = new ParagraphParser(SentenceParser.getInstance());

    private ParagraphParser(TextParser nextParser) {
        super(nextParser);
    }

    public static ParagraphParser getInstance() {
        return PARAGRAPH_PARSER;
    }

    @Override
    public void parse(CustomTextComponent component, String text) {
        String[] paragraphs = text.split(PARAGRAPH_REGEX);

        for (String paragraph : paragraphs) {
            if (!paragraph.isBlank()) {
                CustomTextComponent paragraphComponent = new TextComposite(ComponentType.PARAGRAPH);
                component.add(paragraphComponent);

                if (textParser != null) {
                    textParser.parse(paragraphComponent, paragraph.strip());
                }
            }
        }
    }
}
