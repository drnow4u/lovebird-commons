package nl.ing.lovebird.postgres.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.test.context.ContextCustomizer;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisabledIfEnvironmentVariable(named = "GITLAB_CI", matches = "true", disabledReason = "Test containers are not supported in CI")
class PostgresTestContainerContextCustomizerTest {

    @Test
    void shouldLaunchPostgresTestContainer() {
        new ApplicationContextRunner()
                .withInitializer(applicationContext -> {
                    ContextCustomizer contextCustomizer = new PostgresTestContainerContextCustomizer();
                    contextCustomizer.customizeContext(applicationContext, null);
                })
                .withConfiguration(AutoConfigurations.of(
                        DataSourceAutoConfiguration.class
                ))
                .run(context -> assertThat(context)
                        .hasSingleBean(DataSource.class));
    }
}
