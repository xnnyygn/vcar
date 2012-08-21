package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.util.UUID;

public class ImageLocator {

  public static final ImageLocator instance = new ImageLocator();

  public static ImageLocator getInstance() {
    return instance;
  }

  private final String base;

  public ImageLocator() {
    String tmpDir = System.getProperty("java.io.tmpdir");
    if (tmpDir.endsWith(File.separator)) {
      base = tmpDir + "vcar-";
    } else {
      base = tmpDir + File.separator + "vcar-";
    }
  }

  public String getInputPath() {
    return getInputPath(newVcarId());
  }

  public String newVcarId() {
    return UUID.randomUUID().toString();
  }

  public String getInputPath(String vcarId) {
    return base + vcarId;
  }

  public String getOutputPath() {
    return base + newVcarId();
  }

}
