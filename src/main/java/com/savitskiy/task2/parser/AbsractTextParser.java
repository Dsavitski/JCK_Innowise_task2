package com.savitskiy.task2.parser;

public abstract class AbsractTextParser implements TextParser {
    protected TextParser textParser;

    public AbsractTextParser(TextParser textParser) {
        this.textParser = textParser;
    }
}
