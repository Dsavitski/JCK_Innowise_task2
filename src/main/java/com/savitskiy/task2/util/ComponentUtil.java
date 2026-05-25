package com.savitskiy.task2.util;

import com.savitskiy.task2.entity.ComponentType;
import com.savitskiy.task2.entity.CustomTextComponent;
import com.savitskiy.task2.entity.TextComposite;

import java.util.ArrayList;
import java.util.List;

public class ComponentUtil {
    private ComponentUtil () {}

    public static List<CustomTextComponent> getComponentsByType(CustomTextComponent component, ComponentType type) {
        List<CustomTextComponent> result = new ArrayList<>();
        if (component.getType() == type) {
            result.add(component);
        } else if (component instanceof TextComposite) {
            for (CustomTextComponent child : component.getChildren()) {
                result.addAll(getComponentsByType(child, type));
            }
        }
        return result;
    }
}
