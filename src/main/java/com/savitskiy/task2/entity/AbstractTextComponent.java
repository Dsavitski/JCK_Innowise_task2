package com.savitskiy.task2.entity;


public abstract class AbstractTextComponent implements CustomTextComponent{
    protected final ComponentType type;

    AbstractTextComponent(ComponentType type) {
        this.type = type;
    }

    @Override
    public ComponentType getType() {
        return type;
    }
}
