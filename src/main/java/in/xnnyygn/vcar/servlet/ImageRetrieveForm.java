package in.xnnyygn.vcar.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ImageRetrieveForm {

  private static final Log logger = LogFactory.getLog(ImageRetrieveForm.class);
  private InputStream onlineImage;
  private InputStream image;

  public ImageRetrieveForm(List<FileItem> items)
      throws IllegalImageUrlException {
    for (FileItem item : items) {
      String name = item.getFieldName();
      logger.debug("parsed field name [" + name + "]");
      if ("imageUrl".equals(name)) {
        String imageUrl = item.getString();
        if (StringUtils.isNotBlank(imageUrl))
          onlineImage = openOnlineImage(item.getString());
      } else if ("image".equals(name)) {
        image = openImage(item);
      }
    }
    if (onlineImage == null && image == null) {
      throw new ImageNotFoundException("image not found");
    }
  }

  private InputStream openImage(FileItem item) {
    try {
      return item.getInputStream();
    } catch (IOException e) {
      throw new ImageOpenFailureException("failed to open image", e);
    }
  }

  private InputStream openOnlineImage(String imageUrl)
      throws IllegalImageUrlException {
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

  public InputStream openStream() {
    return onlineImage != null ? onlineImage : image;
  }

}
