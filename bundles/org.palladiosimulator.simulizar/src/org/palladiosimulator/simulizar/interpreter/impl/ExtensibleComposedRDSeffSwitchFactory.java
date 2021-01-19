package org.palladiosimulator.simulizar.interpreter.impl;

import java.util.Set;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.emf.ecore.util.Switch;
import org.palladiosimulator.simulizar.interpreter.AbstractRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ComposedRDSeffSwitchFactory;
import org.palladiosimulator.simulizar.interpreter.ExplicitDispatchComposedSwitch;
import org.palladiosimulator.simulizar.interpreter.InterpreterDefaultContext;

public class ExtensibleComposedRDSeffSwitchFactory implements ComposedRDSeffSwitchFactory {

    private final Provider<Set<AbstractRDSeffSwitchFactory>> elementFactoriesProvider;

    @Inject
    public ExtensibleComposedRDSeffSwitchFactory(Provider<Set<AbstractRDSeffSwitchFactory>> elementFactoriesProvider) {
        this.elementFactoriesProvider = elementFactoriesProvider;
    }

    @Override
    public Switch<Object> createRDSeffSwitch(InterpreterDefaultContext context) {
        final  ExplicitDispatchComposedSwitch<Object> interpreter = new ExplicitDispatchComposedSwitch<Object>();
        elementFactoriesProvider.get().stream().forEach(s -> interpreter.addSwitch(
                s.createRDSeffSwitch(context, interpreter)));
        
        return interpreter;
    }

}
