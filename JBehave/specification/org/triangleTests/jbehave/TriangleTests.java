package org.triangleTests.jbehave;

import java.util.List;

import org.jbehave.core.junit.JUnitStory;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;

public class TriangleTests extends JUnitStory {

	@Override
	public List<CandidateSteps> candidateSteps() {
		return new InstanceStepsFactory(configuration(),
				new TriangleTestSteps()).createCandidateSteps();
	}
}
