package org.palladiosimulator.simulizar.di.modules.component.eclipse;

import javax.inject.Named;

import org.palladiosimulator.probeframework.calculator.ExtensibleCalculatorFactoryDelegatingFactory;
import org.palladiosimulator.probeframework.calculator.IGenericCalculatorFactory;
import org.palladiosimulator.probeframework.calculator.RegisterCalculatorFactoryDecorator;
import org.palladiosimulator.recorderframework.config.IRecorderConfigurationFactory;
import org.palladiosimulator.recorderframework.utils.RecorderExtensionHelper;
import org.palladiosimulator.simulizar.di.modules.component.core.QUALModule;
import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimulationConfigBindingModule;
import org.palladiosimulator.simulizar.scopes.AnalysisDependencyScope;

import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.simucomframework.calculator.RecorderAttachingCalculatorFactoryDecorator;

@Module(includes = { QUALModule.class })
public interface EclipseQUALModule {

    @Provides
    @AnalysisDependencyScope
    static IGenericCalculatorFactory provideCalculatorFactory(
            @Named(SimulationConfigBindingModule.RECORDERFRAMEWORK_RECORDERNAME) String recorderName,
            IRecorderConfigurationFactory recorderConfigurationFactory) {
        return new RegisterCalculatorFactoryDecorator(
                    new RecorderAttachingCalculatorFactoryDecorator(
                            new ExtensibleCalculatorFactoryDelegatingFactory(),
                            recorderName, recorderConfigurationFactory));
    }
    
    @Provides
    @AnalysisDependencyScope
    static IRecorderConfigurationFactory provideRecorderConfigurationFactory(
            @Named(SimulationConfigBindingModule.RECORDERFRAMEWORK_RECORDERNAME) String recorderName) {
        return RecorderExtensionHelper.getRecorderConfigurationFactoryForName(recorderName);
    }
}
