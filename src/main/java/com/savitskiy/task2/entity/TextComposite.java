package com.savitskiy.task2.entity;

import java.util.ArrayList;
import java.util.List;

public abstract class TextComposite extends AbstractTextComponent {
    private final List<CustomTextComponent> components = new ArrayList<>();
    private static final String Str = "\n";
    private static final String Tab = "    ";
    private static final String Space = " ";

    public TextComposite(ComponentType type) {
        super(type);
    }

    @Override
    public String reconstruct() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < components.size(); i++) {
            CustomTextComponent component = components.get(i);
            if(component.getType() == ComponentType.PARAGRAPH) {
                if(i !=0){
                    sb.append(Str);
                }
                sb.append(Tab);
            }
            sb.append(component.reconstruct());
            if(i == components.size()-1){
                continue;
            }

            switch(component.getType()) {
                case LEXEME:
                case SENTENCE:
                    sb.append(Space);
                    break;
            }
        }
        return sb.toString();
    }

    @Override
    public List<CustomTextComponent> getChildren() {
        return new ArrayList<>(components);
    }

    @Override
    public void add(CustomTextComponent component) {
        components.add(component);
    }

    @Override
    public void remove(CustomTextComponent component) {
        components.remove(component);
    }

    @Override
    public int countLetters() {
        return components.stream().mapToInt(CustomTextComponent::countLetters).sum();
    }

    @Override
    public int countSymbols() {
        return components.stream().mapToInt(CustomTextComponent::countSymbols).sum();
    }
}
