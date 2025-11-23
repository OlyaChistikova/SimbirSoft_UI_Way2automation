package helpers;

import lombok.extern.slf4j.Slf4j;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Slf4j
public class RunTestAgain implements IRetryAnalyzer {
    private int nowCount=0;
    private int maxCount=2;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (nowCount<maxCount) {
            nowCount++;
            return true; //пока истина перезапускаем
        }
        log.info("Тест перезапущен еще дважды после первого провала!");
        nowCount=0;
        return false;
    }
}