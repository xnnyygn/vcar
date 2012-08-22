package in.xnnyygn.vcar.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Implementation based on {@code ImageMagick}.
 * 
 * @author xnnyygn
 */
public class ImageMagickConverter implements ImageConverter {

  private static final Log logger = LogFactory
      .getLog(ImageMagickConverter.class);
  private final String convertCommand;

  /**
   * Create with convert command {@code convert}.
   */
  public ImageMagickConverter() {
    this("convert");
  }

  /**
   * Create with convert command.
   * 
   * @param convertCommand convert command
   * @see #executeCommand(String)
   */
  public ImageMagickConverter(String convertCommand) {
    logger.info("convert command [" + convertCommand + "]");
    this.convertCommand = convertCommand;
  }

  /**
   * Execute the command
   * {@code convert $sourceImagePath -crop $widthx$height+$left+$top $destinationImagePath}
   * .
   * 
   * @see in.xnnyygn.vcar.servlet.ImageConverter#crop(String, int, int, int,
   *      int)
   */
  @Override
  public InputStream crop(String vcarId, int left, int top, int width,
      int height) {
    File destImage = ImageLocator.getInstance().getOutputFile();
    executeCommand(String.format("%s %s -crop %dx%d+%d+%d %s", convertCommand,
        ImageLocator.getInstance().getInputPath(vcarId), width, height, left,
        top, destImage.getAbsoluteFile()));
    return openFile(destImage);
  }

  /**
   * Open file and return the input stream of the file. The main purpose of this
   * method is to convert checked exception {@link FileNotFoundException} to
   * runtime exception {@link NoSuchFileException}.
   * 
   * @param file file
   * @return input stream of the file
   * @throws NoSuchFileException if file not found
   * @see FileInputStream#FileInputStream(File)
   */
  private InputStream openFile(File file) {
    try {
      return new FileInputStream(file);
    } catch (FileNotFoundException e) {
      throw new NoSuchFileException("no such file [" + file + "]");
    }
  }

  /**
   * Execute command. If failed to execute or the exit value of process is not
   * zero which indicates some error occurred, throw
   * {@link ExecuteCommandException}.
   * 
   * @param command command
   * @throws ExecuteCommandException if failed to excute or the exit value of
   *         process is not zero
   * @see Runtime#exec(String)
   * @see Process#waitFor()
   * @see Process#getErrorStream()
   */
  private void executeCommand(String command) {
    try {
      Process process = Runtime.getRuntime().exec(command);
      int exitValue = process.waitFor();
      if (exitValue != 0) {
        throw new ExecuteCommandException("failed to execute command ["
            + command + "], error report ["
            + IOUtils.toString(process.getErrorStream()) + "]");
      }
    } catch (Exception e) {
      throw new ExecuteCommandException("failed to execute command [" + command
          + "]", e);
    }
  }

}
