/**
 * 
 */
package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Servlet to retrieve image.
 * 
 * @author xnnyygn
 */
public class ImageRetrieveServlet extends HttpServlet {

  private static final Log logger = LogFactory.getLog("main");
  private static final long serialVersionUID = -6912621737271443097L;
  private ImageRetriever retriever = new ImageRetriever();

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String vcarId = UUID.randomUUID().toString();
    if (retrieve(request, vcarId)) {
      response.sendRedirect(request.getContextPath() + "/vcar.jsp?vcarId="
          + vcarId);
    } else {
      request.setAttribute("error", true);
      getServletContext().getRequestDispatcher("/index.jsp").forward(request,
          response);
    }
  }

  /**
   * Retrieve image from HTTP request and copy to specified file with VCAR id.
   * 
   * @param request HTTP request
   * @param vcarId VCAR id
   * @return true if success, otherwise false
   * @see ImageRetriever#apply(HttpServletRequest, OutputStream)
   */
  private boolean retrieve(HttpServletRequest request, String vcarId) {
    File tmp = new File("/tmp/vcar-" + vcarId);
    try {
      retriever.apply(request, new FileOutputStream(tmp));
      return true;
    } catch (IllegalImageUrlException e) {
      logger.error(e);
    } catch (FileNotFoundException e) {
      logger.error(e);
    }
    return false;
  }

}
