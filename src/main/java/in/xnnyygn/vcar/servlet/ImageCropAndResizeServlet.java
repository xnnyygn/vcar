package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * The SERVLET to crop and resize image.
 * 
 * @author xnnyygn
 */
public class ImageCropAndResizeServlet extends HttpServlet {

  private static final Log logger = LogFactory
      .getLog(ImageCropAndResizeServlet.class);
  private static final long serialVersionUID = 2580954493193650852L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      ImageCropAndResizeBean bean = parseBean(request);
      logger.info("parameters: " + bean);
      String destImagePath = "/tmp/output";
      executeCommand(bean.toConvertCommand(destImagePath));
      IOUtils.copy(new FileInputStream(destImagePath),
          response.getOutputStream());
    } catch (VcarException e) {
    }
  }

  void executeCommand(String command) {
    try {
      Process process = Runtime.getRuntime().exec(command);
      int exitValue = process.waitFor();
      if (exitValue != 0) {
        throw new ExecuteCommandException("failed to execute command ["
            + command + "], error report ["
            + IOUtils.toString(process.getErrorStream()) + "]");
      }
    } catch (Exception e) {
      throw new ExecuteCommandException("failed to execute command [" + command
          + "]", e);
    }
  }

  /**
   * Parse parameters to crop and resize image from HTTP request.
   * 
   * @param request HTTP request
   * @return parameters to crop and resize image
   * @throws SourceImageNotFoundException if source image not found
   * @throws ParseIntegerException if one of {@code left}, {@code top},
   *         {@code width} and {@code height} not a legal number
   * @throws NotANonnegativeException if one of {@code left}, {@code top},
   *         {@code width} and {@code height} is a negative number
   */
  ImageCropAndResizeBean parseBean(HttpServletRequest request)
      throws SourceImageNotFoundException, ParseIntegerException,
      NotANonnegativeException {
    return new ImageCropAndResizeBean(
        getSourceImagePath(request.getParameter("vcarId")),
        parseNonnegative(request.getParameter("left")),
        parseNonnegative(request.getParameter("top")),
        parseNonnegative(request.getParameter("width")),
        parseNonnegative(request.getParameter("height")));
  }

  private int parseNonnegative(String number) throws ParseIntegerException,
      NotANonnegativeException {
    int integer = parseInt(number);
    if (integer < 0) {
      throw new NotANonnegativeException("not a nonnegateive [" + integer + "]");
    }
    return integer;
  }

  private int parseInt(String number) throws ParseIntegerException {
    try {
      return Integer.parseInt(number);
    } catch (NumberFormatException e) {
      throw new ParseIntegerException("illegal integer [" + number + "]");
    }
  }

  private String getSourceImagePath(String vcarId)
      throws SourceImageNotFoundException {
    if (StringUtils.isNotBlank(vcarId)) {
      File image = new File("/tmp/vcar-" + vcarId);
      if (image.exists() && image.isFile() && image.canRead()) {
        return image.getAbsolutePath();
      }
    }
    throw new SourceImageNotFoundException("source image vcarId = [" + vcarId
        + "] not found");
  }

}
