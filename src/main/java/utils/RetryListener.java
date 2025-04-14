package utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

	public class RetryListener implements IAnnotationTransformer {

		public void transform (final ITestAnnotation annotation, 
				  final Class testClass, final Constructor testConstructor,
				  final Method testMethod) {
				  annotation.setRetryAnalyzer (FailRetry.class);
				  }
	}


