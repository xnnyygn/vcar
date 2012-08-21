/**
 * 
 */
package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SERVLET for showing image.
 * 
 * @author xnnyygn
 */
public class ImageShowServlet extends HttpServlet {

  private static final Log logger = LogFactory.getLog("imageShow");
  private static final long serialVersionUID = 5891788997820173233L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String vcarId = request.getParameter("vcarId");
    if (!ImageLocator.getInstance().exists(vcarId)
        || !copyImage(ImageLocator.getInstance().locate(vcarId), response)) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  /**
   * Copy image to HTTP response. If failed to copy, log the cause.
   * 
   * @param image image file
   * @param response HTTP response
   * @return {@code true} if OK, otherwise {@code false}
   * @see IOUtils#copy(java.io.InputStream, java.io.OutputStream)
   * @see HttpServletResponse#getOutputStream()
   */
  private boolean copyImage(File image, HttpServletResponse response) {
    try {
      IOUtils.copy(new FileInputStream(image), response.getOutputStream());
      return true;
    } catch (IOException e) {
      logger.error("failed to copy image [" + image.getAbsolutePath()
          + "] to response", e);
    }
    return false;
  }

}
