package javadoctest.engine.fixture;

import javadoctest.internal.DocTestExtractor;
import org.assertj.core.api.Assertions;


/**
 * Doc tests with dependencies:
 *
 * <h3>Main project classpath</h3>
 *
 * <pre class="doctest">
 *   {@code
 *     // From javadoc-test-core
 *     new DocTestExtractor();
 *   }
 * </pre>
 *
 * <h3>Test project classpath</h3>
 *
 * <pre class="doctest">
 *   {@code
 *     // From AssertJ
 *     Assertions.entry("key", "value");
 *   }
 * </pre>
 */
public class FixtureDocTestWithDeps {

  // Keep imports around
  static {
    @SuppressWarnings("unused") Class<?> assertionsClass = Assertions.class;
    @SuppressWarnings("unused") Class<?> docTestClass = DocTestExtractor.class;
  }
}
