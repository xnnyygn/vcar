package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

/**
 * A class to locate image. The base directory of VCAR is system temporary
 * directory, on Unix, the directory is {@code /tmp}. The name of image file is
 * {@code vcar-$vcarId}. Now the {@code vcarId} is generated by UUID.
 * 
 * @author xnnyygn
 * @see UUID#randomUUID()
 */
public class ImageLocator {

  public static final ImageLocator instance = new ImageLocator();

  /**
   * Get instance.
   * 
   * @return instance
   */
  public static ImageLocator getInstance() {
    return instance;
  }

  private final String base;

  /**
   * Constructor. Initialize the base with system temporary directory.
   * 
   * @see System#getProperty(String)
   */
  public ImageLocator() {
    String tmpDir = System.getProperty("java.io.tmpdir");
    if (tmpDir.endsWith(File.separator)) {
      base = tmpDir + "vcar-";
    } else {
      base = tmpDir + File.separator + "vcar-";
    }
  }

  /**
   * Get path of inputed image.
   * 
   * @return path
   * @see #getInputPath(String)
   * @see #newVcarId()
   */
  public String getInputPath() {
    return getInputPath(newVcarId());
  }

  /**
   * Generate new {@code vcarId}.
   * 
   * @return new {@code vcarId}
   * @see UUID#randomUUID()
   */
  public String newVcarId() {
    return UUID.randomUUID().toString();
  }

  /**
   * Get path of inputed image with {@code vcarId}.
   * 
   * @param vcarId VCAR id
   * @return path
   */
  public String getInputPath(String vcarId) {
    return base + vcarId;
  }

  /**
   * Get output image with {@code vcarId}.
   * 
   * @return file
   */
  public File getOutputFile() {
    return new File(base + newVcarId());
  }

  /**
   * Locate the file of image by {@code vcarId}. This method does NOT check if
   * file exists or not.
   * 
   * @param vcarId VCAR id
   * @return file
   */
  public File locate(String vcarId) {
    return new File(getInputPath(vcarId));
  }

  /**
   * Test if image file exists. If {@code vcarId} is not blank, and image
   * exists, is a regular file, readable, return {@code true}.
   * 
   * @param vcarId VCAR id
   * @return {@code true} if OK, otherwise {@code false}
   * @see #locate(String)
   * @see File#exists()
   * @see File#isFile()
   * @see File#canRead()
   */
  public boolean exists(String vcarId) {
    if (StringUtils.isNotBlank(vcarId)) {
      File image = locate(vcarId);
      if (image.exists() && image.isFile() && image.canRead()) {
        return true;
      }
    }
    return false;
  }

}
