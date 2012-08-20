package in.xnnyygn.vcar.servlet;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for {@link ImageCropAndResizeBean}.
 * 
 * @author xnnyygn
 */
public class ImageCropAndResizeBeanTest {

  @Test
  public void testToConvertCommand() {
    ImageCropAndResizeBean bean =
        new ImageCropAndResizeBean("/path/to/source/image", 1, 2, 3, 4);
    assertEquals(
        "convert /path/to/source/image -crop 3x4+1+2 /path/to/dest/image",
        bean.toConvertCommand("/path/to/dest/image"));
  }

}
