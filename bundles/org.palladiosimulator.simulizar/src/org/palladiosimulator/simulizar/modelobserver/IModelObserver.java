package org.palladiosimulator.simulizar.modelobserver;

import org.palladiosimulator.simulizar.di.extension.Extension;

public interface IModelObserver extends Extension {
    
    default public void initialize() {
        
    }

    default void unregister() {
    }
    
}