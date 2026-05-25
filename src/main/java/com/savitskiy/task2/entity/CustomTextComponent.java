package com.savitskiy.task2.entity;



import java.util.List;

public interface CustomTextComponent {
    String reconstruct();
    ComponentType getType();
    List<CustomTextComponent> getChildren();
    void add(CustomTextComponent component);
    void remove(CustomTextComponent component);
    int countLetters();
    int countSymbols();
}
