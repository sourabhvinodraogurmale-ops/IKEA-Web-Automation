package report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import util.MeetingPlaceConfig;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static util.WebOperations.mConfigProps;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    static LocalDateTime myDateObj = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss");
    static String formattedTime = myDateObj.format(formatter);


    public synchronized static ExtentReports createExtentReports() {
        ExtentSparkReporter reporter = new ExtentSparkReporter( "./extent-reports/extent-report-"+ formattedTime +".html");
        reporter.config().setReportName("Extent Report for " + MeetingPlaceConfig.getMeetingPlaceLanguage());
        reporter.config().thumbnailForBase64(true);
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("User Name", "Livat");
        return extentReports;
    }
}
