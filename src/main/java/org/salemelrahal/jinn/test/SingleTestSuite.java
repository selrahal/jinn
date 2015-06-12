package org.salemelrahal.jinn.test;

import java.util.Collections;
import java.util.List;

public class SingleTestSuite extends TrainingSuite {
	@Override
	public List<TrainingSuite> randomSplit() {
		return Collections.singletonList((TrainingSuite)this);
	}
}
