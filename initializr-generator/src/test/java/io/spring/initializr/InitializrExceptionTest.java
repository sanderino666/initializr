package io.spring.initializr;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.isA;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InitializrExceptionTest {

  @Test
  public void testInvalidVersionException() {
    InitializrException initializrException = new InitializrException();
    assertThat(initializrException, notNullValue());
  }

  @Test
  public void testInvalidVersionExceptionMessage() {
    InitializrException initializrException = new InitializrException("Test");
    assertThat(initializrException.getMessage(), equalTo("Test"));
  }

  @Test
  public void testInvalidVersionExceptionThrowable() {
    InitializrException initializrException = new InitializrException(new IllegalStateException("CauseException"));
    assertThat(initializrException.getCause(), isA(Throwable.class));
  }

  @Test
  public void testInvalidVersionExceptionMessageAndThrowable() {
    InitializrException initializrException =
        new InitializrException("Test", new IllegalStateException("CauseException"));
    assertThat(initializrException.getMessage(), equalTo("Test"));
    assertThat(initializrException.getCause(), isA(Throwable.class));
  }
}
