package org.salemelrahal.jinn.test;

import java.util.Iterator;
import java.util.List;

public interface TrainingSuite {
	public void addTest(TrainingTest test);
	public Iterator<TrainingTest> iterator();
	public int size();
	public List<TrainingSuite> split(int batchSize);
}