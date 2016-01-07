package main.resources.perfume;

import java.io.IOException;

import main.recources.perfume.metric.statistics.*;
import main.resources.perfume.codesmell.*;
import main.resources.perfume.util.TrainingSetResultUtil;
import test.metric.*;

public class Entrance {
	public static void main(String[] args) {
//		new CYCLOMethodTest("D:\\Qualitas Corpus\\001-apache-ant-1.9.6-src");
//		new NOC2Test("D:\\Qualitas Corpus\\112-xmojosrc_5.0.0");
//		AbstractCodeSmell acs = new LazyClass() ;
//		acs.makeTrainingSet("D:\\Qualitas Corpus\\", "001-apache-ant-1.9.6-src");
//		TrainingSetUtil util = new TrainingSetUtil();
//		util.makeTrainningSet("D:\\Qualitas Corpus\\", new LazyClass());
//		
//		try {
//			TrainingSetResultUtil.collectToCSV("D:\\perfume\\lazy class", 
//					"E:\\lazy-class-training-set.tsv");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		Statistics s = new Statistics();
		StatisticsMetric sMetric = new StatisticsMetric();
		s.makeStatistics("g:\\", sMetric);
	}
}
