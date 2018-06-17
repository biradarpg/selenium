package testcases;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses(
{FirstTestCase.class,
UnderStandingAssertion.class,
SecondTestcase.class,
ParameterisedTestCase.class})
public class MytestSuiteRunner {

}
