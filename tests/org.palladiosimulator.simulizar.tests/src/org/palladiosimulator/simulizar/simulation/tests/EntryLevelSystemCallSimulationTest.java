package org.palladiosimulator.simulizar.simulation.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.measure.unit.SI;

import org.junit.jupiter.api.Test;
import org.palladiosimulator.edp2.models.ExperimentData.ExperimentRun;
import org.palladiosimulator.metricspec.constants.MetricDescriptionConstants;
import org.palladiosimulator.pcm.usagemodel.UsageScenario;
import org.palladiosimulator.simulizar.test.commons.annotation.LoadPCMInstanceFromBundle;
import org.palladiosimulator.simulizar.test.commons.annotation.RunSimuLizar;
import org.palladiosimulator.simulizar.test.commons.util.MeasurementTestUtils;

import de.uka.ipd.sdq.workflow.jobs.JobFailedException;
import de.uka.ipd.sdq.workflow.jobs.UserCanceledException;
import tools.mdsd.junit5utils.annotations.PluginTestOnly;

@PluginTestOnly
public class EntryLevelSystemCallSimulationTest {
	@Test
	@LoadPCMInstanceFromBundle(bundleName = "org.palladiosimulator.simulizar.tests", basePath = "testmodel/entry-level-system-call-return-testmodel", modelFiles = {
			"default.allocation", "default.measuringpoint", "default.monitorrepository", "default.repository",
			"default.resourceenvironment", "default.slo", "default.system", "default.usagemodel" })
	@RunSimuLizar
	void testEntryLevelSystemCallResultSimulation(UsageScenario scenario, ExperimentRun expRun)
			throws JobFailedException, UserCanceledException {
		var metric = MetricDescriptionConstants.RESPONSE_TIME_METRIC_TUPLE;

		var measurement = MeasurementTestUtils.getMeasurementOfAt(expRun.getMeasurement(), metric, scenario);
		
		assertTrue(measurement.isPresent());

		MeasurementTestUtils.containsMoreThanZeroMeasurementsFor(measurement.get(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND);
		
		MeasurementTestUtils.allDoubleMeasurementValuesMatch(measurement.get(),
				MetricDescriptionConstants.RESPONSE_TIME_METRIC, SI.SECOND, is(equalTo(315.0)));

	}
}
