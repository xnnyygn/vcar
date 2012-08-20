/**
 * 
 */
package in.xnnyygn.vcar.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

/**
 * A class to parse image from HTTP request and copy image to output. This class
 * will parse text field {@code imageUrl} and file field {@code image} to open
 * the input stream of image. When the value of {@code imageUrl} is not blank,
 * open the online image, otherwise parse the image file field. If
 * {@code imageUrl} is not a legal URL, throw {@link IllegalImageUrlException}.
 * If failed to parse files, throw {@link ParseFileException}; if file field
 * {@code image} not found, throw {@link NoImageException}; if failed to
 * open the input of image, throw {@link ImageOpenFailureException}.
 * 
 * @author xnnyygn
 * @see #apply(HttpServletRequest, OutputStream)
 */
public class ImageRetriever {

  private final ServletFileUpload servletFileUpload = new ServletFileUpload(
      new DiskFileItemFactory());

  /**
   * Parse image from HTTP request, copy image to output and close the output.
   * 
   * @param request HTTP request
   * @param output output
   * @throws IllegalImageUrlException if user input {@code imageUrl} is not a
   *         legal URL
   * @see #parseSource(HttpServletRequest)
   * @see IOUtils#copy(InputStream, OutputStream)
   * @see IOUtils#closeQuietly(OutputStream)
   */
  public void apply(HttpServletRequest request, OutputStream output)
      throws IllegalImageUrlException {
    InputStream input = null;
    try {
      input = parseSource(request);
      IOUtils.copy(input, output);
    } catch (IOException e) {
      throw new CopyImageException("failed to copy image", e);
    } finally {
      IOUtils.closeQuietly(input);
      IOUtils.closeQuietly(output);
    }
  }

  /**
   * Parse image from HTTP request. The field name of online image is
   * {@code imageUrl}. If the value of {@code imageUrl} is not blank, return the
   * input stream of online image, otherwise parse the image file field.
   * 
   * @param request HTTP request
   * @return stream of image
   * @throws IllegalImageUrlException if {@code imageUrl} is not a legal URL
   * @see #parseFiles(HttpServletRequest)
   */
  private InputStream parseSource(HttpServletRequest request)
      throws IllegalImageUrlException {
    return new ImageRetrieveForm(parseFiles(request)).openStream();
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
