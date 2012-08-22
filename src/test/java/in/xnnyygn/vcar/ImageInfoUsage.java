package in.xnnyygn.vcar;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.devlib.schmidt.imageinfo.ImageInfo;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class ImageInfoUsage {

  @Test
  public void test() throws MalformedURLException, IOException {
    ImageInfo imageInfo = new ImageInfo();
    imageInfo
        .setInput(new URL(
            "http://www.deviantart.com/download/116229059/Sponge_bob_PNG_by_gio0989.png")
            .openStream());
    imageInfo.setDetermineImageNumber(true);
    if (imageInfo.check()) {
      System.out.println("formatName: " + imageInfo.getFormatName());
      System.out.println("mimeType: " + imageInfo.getMimeType());
    }
  }

}
