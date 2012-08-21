package in.xnnyygn.vcar.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImageEditServlet extends HttpServlet {

  private static final long serialVersionUID = 7183672062609436478L;
  private static final Log logger = LogFactory.getLog(ImageEditServlet.class);

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String vcarId = request.getParameter("vcarId");
    if (existImage(vcarId)) {
      request.setAttribute("vcarId", vcarId);
      getServletContext().getRequestDispatcher("/image-edit.jsp").forward(
          request, response);
    } else {
      getServletContext().getRequestDispatcher("/index.jsp").forward(request,
          response);
    }
  }

  /**
   * Test if image exists.
   * 
   * @param vcarId VCAR id
   * @return {@code true} if exist, otherwise false
   * @see ImageLocator#exists(String)
   */
  private boolean existImage(String vcarId) {
    if (logger.isDebugEnabled()) {
      logger.debug("vcarId = [" + vcarId + "]");
    }
    return ImageLocator.getInstance().exists(vcarId);
  }

}
