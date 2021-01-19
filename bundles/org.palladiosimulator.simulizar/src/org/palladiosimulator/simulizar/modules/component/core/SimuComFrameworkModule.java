package org.palladiosimulator.simulizar.modules.component.core;

import org.palladiosimulator.pcm.resourceenvironment.ResourceContainer;
import org.palladiosimulator.simulizar.runtimestate.SimuComModelFactory;
import org.palladiosimulator.simulizar.scopes.SimuComFrameworkScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import de.uka.ipd.sdq.scheduler.resources.active.IResourceTableManager;
import de.uka.ipd.sdq.scheduler.resources.active.ResourceTableManager;
import de.uka.ipd.sdq.simucomframework.ResourceRegistry;
import de.uka.ipd.sdq.simucomframework.model.SimuComModel;
import de.uka.ipd.sdq.simucomframework.resources.AbstractSimulatedResourceContainer;
import de.uka.ipd.sdq.simucomframework.resources.ISimulatedModelEntityAccess;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;
import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationTimeProvider;

@Module
public interface SimuComFrameworkModule {
    
    @Provides
    @SimuComFrameworkScope
    static SimuComModel provideSimuComModel(SimuComModelFactory impl) {
        return impl.get();
    }

    @Provides
    @SimuComFrameworkScope
    static IResourceTableManager provideResourceTableManager() {
        return new ResourceTableManager();
    }
    
    
    @Provides
    @SimuComFrameworkScope
    static ResourceRegistry provideResourceRegistry(SimuComModel simuComModel) {
        return new ResourceRegistry(simuComModel);
    }
    
    @Provides
    @SimuComFrameworkScope
    static ISimulationControl provideSimulationControl(SimuComModel simuComModel) {
        return simuComModel.getSimulationControl();
    }
    
    @Binds
    @SimuComFrameworkScope
    ISimulationTimeProvider bindTimeProvider(ISimulationControl simuComModel);
    
    @Provides
    static ISimulatedModelEntityAccess<ResourceContainer, AbstractSimulatedResourceContainer> bindRCAccess(ResourceRegistry registry) {
        return registry::getResourceContainer;
    }

}
