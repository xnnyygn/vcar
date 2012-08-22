package in.xnnyygn.vcar.servlet;

import java.io.InputStream;

/**
 * Image converter.
 * 
 * @author xnnyygn
 */
public interface ImageConverter {

  /**
   * Crop image and return the input stream of output image.
   * 
   * @param vcarId VCAR id
   * @param left left
   * @param top top
   * @param width width
   * @param height height
   * 
   * @return the input stream of output image
   */
  public InputStream crop(String vcarId, int left, int top, int width,
      int height);

}
