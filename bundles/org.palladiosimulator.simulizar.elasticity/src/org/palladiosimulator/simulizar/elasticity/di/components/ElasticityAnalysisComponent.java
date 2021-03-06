package org.palladiosimulator.simulizar.elasticity.di.components;

import org.palladiosimulator.simulizar.di.modules.stateless.configuration.SimuLizarConfigurationModule;
import org.palladiosimulator.simulizar.elasticity.aggregator.ReconfigurationTimeAggregatorWithConfidence;
import org.palladiosimulator.simulizar.elasticity.di.modules.ElasticityAnalysisModule;
import org.palladiosimulator.simulizar.elasticity.jobs.SimuLizarElasticityAnalysisCompositeJob;
import org.palladiosimulator.simulizar.runconfig.SimuLizarWorkflowConfiguration;
import org.palladiosimulator.simulizar.scopes.AnalysisRootScope;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = { ElasticityAnalysisModule.class, SimuLizarConfigurationModule.class })
@AnalysisRootScope
public interface ElasticityAnalysisComponent {
    
    SimuLizarElasticityAnalysisCompositeJob rootJob();
    
    ReconfigurationTimeAggregatorWithConfidence aggregatorWithConfidence();
    
    @Component.Factory
    interface Factory {
        ElasticityAnalysisComponent create(@BindsInstance SimuLizarWorkflowConfiguration configuration);
    }

}
