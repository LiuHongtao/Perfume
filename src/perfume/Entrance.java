package perfume;

import perfume.codesmell.*;
import perfume.test.metric.*;

public class Entrance {
	public static void main(String[] args) {
//		new CYCLOMethodTest("D:\\Qualitas Corpus\\112-xmojosrc_5.0.0");
//		new LOC_CLASSTest("D:\\Qualitas Corpus\\112-xmojosrc_5.0.0");
//		AbstractCodeSmell acs = new LazyClass() ;
//		acs.makeTrainingSet("D:\\Qualitas Corpus\\", "112-xmojosrc_5.0.0");
//		TrainingSetUtil util = new TrainingSetUtil();
//		util.makeTrainningSet("D:\\Qualitas Corpus\\", new LazyClass());
		RemoveTestFileUtil util = new RemoveTestFileUtil();
		util.removeAllTestFile("D:\\Qualitas Corpus\\");
	}
}
