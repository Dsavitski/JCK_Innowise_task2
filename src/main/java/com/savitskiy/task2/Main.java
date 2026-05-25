package com.savitskiy.task2;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.entity.TextComposite;
import com.savitskiy.task2.parser.ParagraphParser;
import com.savitskiy.task2.reader.impl.CustomTextReaderImpl;
import com.savitskiy.task2.service.impl.DublicateWordServiceImpl;
import com.savitskiy.task2.service.impl.LexemeSwapServiceImpl;
import com.savitskiy.task2.service.impl.SentenceSortServiceImpl;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Main {
    private static final Logger log = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        log.log(Level.INFO,"Application started.");

        CustomTextReaderImpl reader = new CustomTextReaderImpl();
        String filePath = "data/text.txt";

        try {
            log.log(Level.INFO,"Reading file from path: {}", filePath);
            String rawText = reader.read(filePath);

            log.log(Level.INFO,"Starting text parsing process");
            CustomTextComponent root = new TextComposite(ComponentType.TEXT);
            ParagraphParser.getInstance().parse(root, rawText);
            log.log(Level.INFO,"Parsing completed successfully.");

            log.log(Level.INFO,"Start text before modifications:");
            log.log(Level.INFO,"\n{}", root.reconstruct());

            int letters = root.countLetters();
            int totalSymbols = root.countSymbols();
            log.log(Level.INFO,"Text Statistics -> Letters: {}, Total Symbols: {}", letters, totalSymbols);

            DublicateWordServiceImpl dublicateService = new DublicateWordServiceImpl();
            int maxSentences = dublicateService.findmaxSentencesWithSameWord(root);
            log.log(Level.INFO,"Task 1: Max sentences with duplicate words found: {}", maxSentences);

            SentenceSortServiceImpl sortService = new SentenceSortServiceImpl();
            char targetChar = 'a';
            List<CustomTextComponent> sortedSentences = sortService.sortByLetterCount(root, targetChar);
            log.log(Level.INFO,"Task 2: Sentences sorted by occurrence of '{}':", targetChar);
            for (CustomTextComponent sentence : sortedSentences) {
                log.log(Level.INFO,"-> {}", sentence.reconstruct());
            }

            LexemeSwapServiceImpl swapService = new LexemeSwapServiceImpl();
            log.log(Level.INFO,"Task 3: Swapping first and last lexemes in every sentence...");
            swapService.swapLexemes(root);

            log.log(Level.INFO,"Final reconstructed text after modifications:");
            log.log(Level.INFO,"\n{}", root.reconstruct());


        } catch (Exception e) {
            log.log(Level.ERROR,"Error: ", e);
        }

        log.log(Level.INFO,"Application finished.");
    }
}
