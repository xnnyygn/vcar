package in.xnnyygn.vcar.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.devlib.schmidt.imageinfo.ImageInfo;

/**
 * The SERVLET to crop and resize image.
 * 
 * @author xnnyygn
 */
public class ImageCropAndResizeServlet extends HttpServlet {

  private static final Log logger = LogFactory
      .getLog(ImageCropAndResizeServlet.class);
  private static final long serialVersionUID = 2580954493193650852L;
  private ImageConverter converter = new ImageMagickConverter();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      cropAndCopyImage(request, response);
    } catch (VcarException e) {
      response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
    } catch (Exception e) {
      logger.error("failed to copy and download image", e);
      response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
          "system error");
    }
  }

  /**
   * Copy and copy image.
   * 
   * @param request HTTP request
   * @param response HTTP response
   * @throws ParseFloatException if {@code left}, {@code top}, {@code width} or
   *         {@code height} is not a legal integer
   * @throws NotANonnegativeException if {@code left}, {@code top},
   *         {@code width} or {@code height} is negative
   * @throws NoSuchImageException if image found by VCAR id not exist
   * @see ImageLocator#exists(String)
   * @see ImageConverter#crop(String, int, int, int, int)
   * @see #copyImage(InputStream, HttpServletResponse)
   */
  private void cropAndCopyImage(HttpServletRequest request,
      HttpServletResponse response) throws ParseFloatException,
      NotANonnegativeException, NoSuchImageException {
    String vcarId = request.getParameter("vcarId");
    if (!ImageLocator.getInstance().exists(vcarId)) {
      throw new NoSuchImageException("no such image with vcar id [" + vcarId
          + "]");
    }
    copyImage(converter.crop(vcarId,
        parseNonnegative(request.getParameter("left")),
        parseNonnegative(request.getParameter("top")),
        parseNonnegative(request.getParameter("width")),
        parseNonnegative(request.getParameter("height"))), response);
  }

  /**
   * Copy image to client.
   * 
   * @param outputImage output image
   * @param response HTTP response
   * @throws CopyImageException if failed to copy image
   * @see #getImageInfo(InputStream)
   * @see IOUtils#copy(InputStream, java.io.OutputStream)
   */
  private void copyImage(InputStream outputImage, HttpServletResponse response)
      throws CopyImageException {
    ImageInfo info = getImageInfo(outputImage);
    response.setContentType(info.getMimeType());
    response.setHeader("Content-Disposition", "attachment;filename=image."
        + info.getFormatName());
    try {
      IOUtils.copy(outputImage, response.getOutputStream());
    } catch (IOException e) {
      throw new CopyImageException("failed to copy image to client", e);
    }
  }

  /**
   * Get information of image.
   * 
   * @param image image
   * @return image information
   * @see ImageInfo#setDetermineImageNumber(boolean)
   * @see ImageInfo#setInput(InputStream)
   */
  private ImageInfo getImageInfo(InputStream image) {
    ImageInfo info = new ImageInfo();
    info.setDetermineImageNumber(true);
    info.setInput(image);
    return info;
  }

  /**
   * Parse nonnegative number from string.
   * 
   * @param string string, maybe {@code null}
   * @return integer
   * @throws ParseFloatException if failed to parse float from string
   * @throws NotANonnegativeException if number is negative
   * @see #parseInt(String)
   */
  private int parseNonnegative(String string) throws ParseFloatException,
      NotANonnegativeException {
    int integer = parseInt(string);
    if (integer < 0) {
      throw new NotANonnegativeException("not a nonnegateive [" + integer + "]");
    }
    return integer;
  }

  /**
   * Parse float from string and convert to integer.
   * 
   * @param string string, maybe {@code null}
   * @return integer
   * @throws ParseFloatException if failed to parse float from string
   * @see Float#valueOf(String)
   * @see Float#intValue()
   */
  private int parseInt(String string) throws ParseFloatException {
    try {
      return Float.valueOf(string).intValue();
    } catch (NumberFormatException e) {
      throw new ParseFloatException("illegal float [" + string + "]");
    }
  }

}
