package org.salemelrahal.jinn.test;

import java.util.Iterator;
import java.util.List;

public interface TrainingSuite {
	public Iterator<TrainingTest> tests();
	public List<TrainingSuite> split(int batchSize);
}