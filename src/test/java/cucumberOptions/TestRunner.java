package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= "src/test/java/features/Status.feature",
				glue="stepDefinitions"
				//,tags="@PublishStatus"
				)
public class TestRunner {

}
