package in.xnnyygn.vcar.servlet;

/**
 * A form contains parameters to crop and resize image.
 * 
 * @author chen.zhao
 */
public class ImageCropAndResizeBean {

  private String sourceImagePath;
  private int left;
  private int top;
  private int width;
  private int height;

  public ImageCropAndResizeBean(String sourceImagePath, int left, int top,
      int width, int height) {
    super();
    this.sourceImagePath = sourceImagePath;
    this.left = left;
    this.top = top;
    this.width = width;
    this.height = height;
  }

  public String getVcarId() {
    return sourceImagePath;
  }

  public int getLeft() {
    return left;
  }

  public int getTop() {
    return top;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  /**
   * Generate the command of convert(ImageMagick). The format of command is
   * {@code convert $sourceImagePath -crop $leftx$top+$width+$height $destImagePath}
   * .
   * 
   * @param destImagePath the path of destination image
   * @return command
   */
  public String toConvertCommand(String destImagePath) {
    StringBuilder builder = new StringBuilder("convert ");
    builder.append(sourceImagePath).append(" -crop ");
    builder.append(width).append('x').append(height);
    builder.append('+').append(left).append('+').append(top).append(' ');
    builder.append(destImagePath);
    return builder.toString();
  }

  @Override
  public String toString() {
    return String.format("ImageCropAndResizeBean [sourceImagePath=%s, "
        + "left=%s, top=%s, width=%s, height=%s]", sourceImagePath, left, top,
        width, height);
  }

}
