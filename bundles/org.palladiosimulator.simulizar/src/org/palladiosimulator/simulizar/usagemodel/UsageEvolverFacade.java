package org.palladiosimulator.simulizar.usagemodel;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.entity.EntityReferenceFactory;
import org.palladiosimulator.simulizar.runtimestate.SimuLizarRuntimeState;
import org.palladiosimulator.simulizar.scopes.SimulationRuntimeScope;
import org.palladiosimulator.simulizar.utils.PCMPartitionManager;
import org.scaledl.usageevolution.Usage;
import org.scaledl.usageevolution.UsageEvolution;
import org.scaledl.usageevolution.UsageevolutionPackage;

import de.uka.ipd.sdq.simulation.abstractsimengine.ISimulationControl;

@SimulationRuntimeScope
public class UsageEvolverFacade {

    protected Map<Usage, PeriodicallyTriggeredUsageEvolver> usageEvolvers;
    
    /** Runtime state of the simulation. Required to start evolution(s). */
    private final PCMPartitionManager partitionManager;
    private final ISimulationControl simulationControl;

    private final LoopingUsageEvolverFactory loopingFactory;
    private final StretchedUsageEvolverFactory stretchedFactory;
    private final EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory;

    @Inject
    public UsageEvolverFacade(final PCMPartitionManager partitionManager, 
            final ISimulationControl simulationControl,
            final LoopingUsageEvolverFactory loopingFactory,
            final StretchedUsageEvolverFactory stretchedFactory,
            final EntityReferenceFactory<UsageScenario> usageScenarioReferenceFactory) {
        this.partitionManager = partitionManager;
        this.simulationControl = simulationControl;
        this.loopingFactory = loopingFactory;
        this.stretchedFactory = stretchedFactory;
        this.usageEvolvers = new HashMap<Usage, PeriodicallyTriggeredUsageEvolver>();
        this.usageScenarioReferenceFactory = usageScenarioReferenceFactory;
    }

    public void start() {
        // TODO: add check on duration of evolutions for work parameters.
        // For now, assume that the duration of these are the same as for the load evolution
    	UsageEvolution ueModel = partitionManager.findModel(UsageevolutionPackage.eINSTANCE.getUsageEvolution());
        ueModel.getUsages().forEach(usage -> startUsageEvolution(usage));
    }
    
    public void startUsageEvolution(Usage usage) {
        this.usageEvolvers.put(usage, createUsageEvolver(usage));
    }
    
    public void stopUsageEvolution(Usage usage) {
        this.usageEvolvers.remove(usage).stop();
    }
    
    protected PeriodicallyTriggeredUsageEvolver createUsageEvolver(Usage usage) {
        double timePerStep = 1d;
        if (usage.getEvolutionStepWidth() != 0d) {
            timePerStep = usage.getEvolutionStepWidth();
        }
        
        double simulationTimeOffset = 0d; 
        if (simulationControl.isRunning()) {
            simulationTimeOffset = simulationControl.getCurrentSimulationTime();
        }
        
        var ref = usageScenarioReferenceFactory.createCached(usage.getScenario());
        if (usage.isRepeatingPattern()) {
            return loopingFactory.create(0d, timePerStep, simulationTimeOffset, ref);
        } else {
            return stretchedFactory.create(0.0, timePerStep, ref);
        }
    }
}
