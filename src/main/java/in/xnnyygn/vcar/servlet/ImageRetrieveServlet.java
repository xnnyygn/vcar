/**
 * 
 */
package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * SERVLET to retrieve image.
 * 
 * @author xnnyygn
 */
public class ImageRetrieveServlet extends HttpServlet {

  private static final Log logger = LogFactory.getLog("main");
  private static final long serialVersionUID = -6912621737271443097L;
  private final ServletFileUpload servletFileUpload = new ServletFileUpload(
      new DiskFileItemFactory());

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    String vcarId = ImageLocator.getInstance().newVcarId();
    try {
      copyImage(vcarId, request);
      response.sendRedirect(request.getContextPath() + "/editImage?vcarId="
          + vcarId);
    } catch (VcarException e) {
      sendError(e.getMessage(), request, response);
    } catch (VcarRuntimeException e) {
      logger.error("failed to retrieve image", e);
      sendError("system error", request, response);
    }
  }

  /**
   * Send error message to client.
   * 
   * @param message message
   * @param request HTTP request
   * @param response HTTP response
   * @throws ServletException if SERVLET error occurred
   * @throws IOException if IO error occurred
   * @see RequestDispatcher#forward(javax.servlet.ServletRequest,
   *      javax.servlet.ServletResponse)
   */
  private void sendError(String message, HttpServletRequest request,
      HttpServletResponse response) throws ServletException, IOException {
    request.setAttribute("errorMessage", message);
    getServletContext().getRequestDispatcher("/index.jsp").forward(request,
        response);
  }

  /**
   * Copy image to local disk.
   * 
   * @param vcarId VCAR id
   * @param request HTTP request
   * @throws IllegalImageUrlException if {@code imageUrl} is not a legal URL
   * @throws NoImageException if {@code imageUrl} and {@code image} is blank
   * @throws CopyImageException if failed to copy image to local disk
   * @see IOUtils#copy(InputStream, java.io.OutputStream)
   * @see #getImage(Map)
   * @see #toMap(List)
   * @see #parseFiles(HttpServletRequest)
   * @see ImageLocator#getInputPath(String)
   */
  private void copyImage(String vcarId, HttpServletRequest request)
      throws IllegalImageUrlException, NoImageException {
    File tmp = new File(ImageLocator.getInstance().getInputPath(vcarId));
    logger.info("retrieve image to [" + tmp.getAbsolutePath() + "]");
    try {
      IOUtils.copy(getImage(toMap(parseFiles(request))), new FileOutputStream(
          tmp));
    } catch (IOException e) {
      throw new CopyImageException("failed to copy image", e);
    }
  }

  /**
   * Get image from form fields. Form contains two fields, text field
   * {@code imageUrl} and file field {@code image}. If the content of
   * {@code imageUrl} is not blank, try to use the URL of image to open online
   * image. If {@code imageUrl} is blank and {@code image} is not empty, try to
   * open the image user uploaded. If {@code imageUrl} is blank and
   * {@code image} is empty, which means both fields are blank, will throw
   * {@link NoImageException}.
   * 
   * @param fileMap a map use field name as key, file item as value
   * @return input stream
   * @throws IllegalImageUrlException if {@code imageUrl} is not a legal URL
   * @throws NoImageException if both {@code imageUrl} and {@code image} are
   *         blank
   */
  private InputStream getImage(Map<String, FileItem> fileMap)
      throws IllegalImageUrlException, NoImageException {
    String imageUrl = fileMap.get("imageUrl").getString();
    if (StringUtils.isNotBlank(imageUrl)) return openOnlineImage(imageUrl);
    FileItem image = fileMap.get("image");
    if (image.getSize() > 0) return openUploadedImage(image);
    throw new NoImageException("no image");
  }

  /**
   * Open uploaded image.
   * 
   * @param item file item
   * @return input stream
   * @throws ImageOpenFailureException if failed to open image
   * @see FileItem#getInputStream()
   */
  private InputStream openUploadedImage(FileItem item) {
    logger.info("open uploaded image size = [" + item.getSize() + "]");
    try {
      return item.getInputStream();
    } catch (IOException e) {
      throw new ImageOpenFailureException("failed to open image", e);
    }
  }

  /**
   * Open online image.
   * 
   * @param imageUrl image URL
   * @return input stream
   * @throws IllegalImageUrlException if {@code imageUrl} is not a legal URL
   * @throws ImageOpenFailureException if failed to open online image
   * @see URL#openStream()
   */
  private InputStream openOnlineImage(String imageUrl)
      throws IllegalImageUrlException, ImageOpenFailureException {
    logger.info("open online image [" + imageUrl + "]");
    try {
      return new URL(imageUrl).openStream();
    } catch (MalformedURLException e) {
      throw new IllegalImageUrlException(
          "illegal image url [" + imageUrl + "]", e);
    } catch (IOException e) {
      throw new ImageOpenFailureException("failed to open online image ["
          + imageUrl + "]", e);
    }
  }

  /**
   * Convert file item list to a map use field name as key and file item as
   * value.
   * 
   * @param files file item list
   * @return a map use field name as key and file item as value
   */
  private Map<String, FileItem> toMap(List<FileItem> files) {
    Map<String, FileItem> fileMap = new HashMap<String, FileItem>();
    for (FileItem file : files) {
      fileMap.put(file.getFieldName(), file);
    }
    return fileMap;
  }

  /**
   * Parse files from HTTP request. If failed to parse, throw
   * {@link ParseFileException}.
   * 
   * @param request HTTP request
   * @return files
   * @see ServletFileUpload#parseRequest(HttpServletRequest)
   */
  @SuppressWarnings("unchecked")
  private List<FileItem> parseFiles(HttpServletRequest request) {
    try {
      return servletFileUpload.parseRequest(request);
    } catch (FileUploadException e) {
      throw new ParseFileException("failed to parse file", e);
    }
  }

}
