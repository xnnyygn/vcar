package in.xnnyygn.vcar.servlet;

import static org.junit.Assert.assertEquals;

import javax.servlet.http.HttpServletRequest;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Test for {@link ImageCropAndResizeServlet}.
 * 
 * @author xnnyygn
 */
@RunWith(JMock.class)
public class ImageCropAndResizeServletTest {

  private Mockery mockContext = new JUnit4Mockery();
  private HttpServletRequest mockHttpServletRequest;
  private ImageCropAndResizeServlet servlet = new ImageCropAndResizeServlet();

  @Before
  public void setUp() throws Exception {
    mockHttpServletRequest = mockContext.mock(HttpServletRequest.class);
  }
  
  @Test
  @Ignore
  public void testExecuteCommand() throws Exception {
    servlet.executeCommand("ls");
  }

  @Test
  @Ignore
  public void testParseBean() throws Exception {
    mockContext.checking(new Expectations() {
      {
        oneOf(mockHttpServletRequest).getParameter("vcarId");
        will(returnValue("c5eda2c6-6f94-4630-bc19-5ce9cf68de4e"));
        oneOf(mockHttpServletRequest).getParameter("left");
        will(returnValue("1"));
        oneOf(mockHttpServletRequest).getParameter("top");
        will(returnValue("25"));
        oneOf(mockHttpServletRequest).getParameter("width");
        will(returnValue("134"));
        oneOf(mockHttpServletRequest).getParameter("height");
        will(returnValue("444"));
      }
    });
    ImageCropAndResizeBean bean = servlet.parseBean(mockHttpServletRequest);
    assertEquals(1, bean.getLeft());
    assertEquals(25, bean.getTop());
    assertEquals(134, bean.getWidth());
    assertEquals(444, bean.getHeight());
  }

}
