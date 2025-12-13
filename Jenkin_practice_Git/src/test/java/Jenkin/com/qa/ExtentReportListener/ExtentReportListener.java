package Jenkin.com.qa.ExtentReportListener;

import org.testng.ITestContext;
import org.testng.ITestResult;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestListener;

import Reports.ExtentManager;


public class ExtentReportListener implements ITestListener{
	 private static ExtentReports extent = ExtentManager.getInstance();
	    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    @Override
	    public void onTestStart(ITestResult result) {
	        ExtentTest extentTest =
	                extent.createTest(result.getMethod().getMethodName());
	        test.set(extentTest);
	    }

	    public void onTestSuccess(ITestResult result) {
	        test.get().pass("Test Passed");
	    }

	    public void onTestFailure(ITestResult result) {
	        test.get().fail(result.getThrowable());
	    }

	    @Override
	    public void onTestSkipped(ITestResult result) {
	        test.get().skip("Test Skipped");
	    }

	    @Override
	    public void onFinish(ITestContext context) {
	        extent.flush();
	    }}