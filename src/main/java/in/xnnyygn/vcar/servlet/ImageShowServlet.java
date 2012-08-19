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
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Show image.
 * 
 * @author xnnyygn
 */
public class ImageShowServlet extends HttpServlet {

  private static final Log logger = LogFactory.getLog("imageShow");
  private static final long serialVersionUID = 5891788997820173233L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    File vcarFile = findVcarFile(request.getParameter("vcarId"));
    if (vcarFile != null) {
      IOUtils.copy(new FileInputStream(vcarFile), response.getOutputStream());
    } else {
      response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
  }

  /**
   * Find VCAR file.
   * 
   * @param vcarId VCAR id, may be {@code null}
   * @return file or {@code null} if {@code vcarId} is blank, file not exists,
   *         not a file, cannot read
   */
  private File findVcarFile(String vcarId) {
    if (logger.isDebugEnabled()) {
      logger.debug("vcarId = " + vcarId);
    }
    if (StringUtils.isNotBlank(vcarId)) {
      File file = new File("/tmp/vcar-" + vcarId);
      if (file.exists() && file.isFile() && file.canRead()) return file;
    }
    return null;
  }

}
