import java.awt.*;
import java.awt.image.BufferedImage;

class ImageOperations {

    /**
     * Removes the red channel from the colors of the image.
     *
     * @param img The image that we are removing the red channel from.
     * @return A new image that is the original image with the red channel removed.
     */
    static BufferedImage zeroRed(BufferedImage img) {
        // TODO.
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int newRGB = (0) | (green << 8) | blue;
                newImg.setRGB(x, y, newRGB);
            }
        }
        return newImg;
    }

    /**
     * Converts an image into greyscale.
     *
     * @param img The given image we are converting to greyscale.
     * @return A new image that is the original image converted to greyscale.
     */
    static BufferedImage grayscale(BufferedImage img) {
        // TODO.
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int gray = (red + green + blue) / 3;
                newImg.setRGB(x, y, (gray << 16) | (gray << 8) | gray);
            }
        }
        return newImg;
    }

    /**
     * Takes an image and inverts the pixel data.
     *
     * @param img The given image that we are inverting the pixel data of.
     * @return A new image with the pixel data from the original image inverted.
     */
    static BufferedImage invert(BufferedImage img) {
        // TODO.
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int rgb = img.getRGB(x, y);
                int red = (rgb >> 16) & 0xFF;
                int green = (rgb >> 8) & 0xFF;
                int blue = rgb & 0xFF;
                int newRGB = 255-red << 16 | 255-green << 8 | 255-blue;
                newImg.setRGB(x, y, newRGB);
            }
        }
        return newImg;
    }

    /**
     * Mirrors an image either vertically or horizontally.
     *
     * @param img The given image that we will be mirroring.
     * @param dir A mirror menu object that determines which direction the image will be mirrored in.
     * @return A new image that is the original image mirrored either vertically or horizontally.
     */
    static BufferedImage mirror(BufferedImage img, MirrorMenuItem.MirrorDirection dir) {
        // TODO instantiate newImg with the *correct* dimensions.
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        if (dir == MirrorMenuItem.MirrorDirection.VERTICAL) {
            // TODO mirror the image vertically.
            for (int y = 0; y < img.getHeight(); y++) {
                for (int lx = 0, rx = img.getWidth()-1; lx < img.getWidth(); lx++, rx--) {
                    int p = img.getRGB(rx, y);
                    newImg.setRGB(lx, y, p);
                    newImg.setRGB(rx, y, p);
                }
            }
        } else {
            // TODO mirror the image horizontally.
            for (int x = 0; x < img.getWidth(); x++) {
                for (int lx = 0, rx = img.getHeight()-1; lx < img.getHeight(); lx++, rx--) {
                    int p = img.getRGB(x,rx);
                    newImg.setRGB(x, lx, p);
                    newImg.setRGB(x, rx, p);
                }
            }
        }
        return newImg;
    }

    /**
     * Rotates an image 90 degrees either clockwise or counter-clockwise.
     *
     * @param img The given image the method will be rotating.
     * @param dir The menu item that determines if the image will be rotated clockwise or counter-clockwise.
     * @return A new image that is the original image rotated.
     */
    static BufferedImage rotate(BufferedImage img, RotateMenuItem.RotateDirection dir) {
        // TODO instantiate newImg with the *correct* dimensions.
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        if (dir == RotateMenuItem.RotateDirection.CLOCKWISE) {
            // TODO rotate the image clockwise.
            for (int i = 0; i < img.getHeight(); i++) {
                for (int lx = 0, rx = img.getWidth()-1; lx < img.getWidth(); lx++, rx--) {
                    int p = img.getRGB(i, lx);
                    newImg.setRGB(rx, i, p);
                }
            }
        } else {
            // TODO rotate the image counter-clockwise.
            for (int i = 0; i < img.getHeight(); i++) {
                for (int lx = 0, rx = img.getWidth()-1; lx < img.getWidth(); lx++, rx--) {
                    int p = img.getRGB(rx, i);
                    newImg.setRGB(i, lx, p);
                }
            }
        }
        return newImg;
    }

    /**
     * Creates an image with the image repeated either side-by-side or top-to-bottom, depending on the given argument, a number of times.
     *
     * @param img The given image that we are repeating.
     * @param n   The number of times the image is repeated as an integer.
     * @param dir A menu item that determines if the image will be repeated side-by-side or top-to-bottom.
     * @return A new image that has repeated the original image side-by-side or top-to-bottom a number of times.
     */
    static BufferedImage repeat(BufferedImage img, int n, RepeatMenuItem.RepeatDirection dir) {
        BufferedImage newImg;
        // newImg must be instantiated in both branches with the correct dimensions.
        if (dir == RepeatMenuItem.RepeatDirection.HORIZONTAL) {
            // TODO repeat the image horizontally.
            newImg = new BufferedImage(img.getWidth() * n, img.getHeight(), BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {
                    Color c = new Color(img.getRGB(x, y));
                    Color newC = new Color(c.getRed(), c.getGreen(), c.getBlue());
                    for (int k = 0; k < n; k++) {
                        newImg.setRGB(x + (img.getWidth() * k), y, newC.getRGB());
                    }
                }
            }
        }
        else {
            // TODO repeat the image vertically.
            newImg = new BufferedImage(img.getWidth(), img.getHeight() * n, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < img.getWidth(); x++) {
                for (int y = 0; y < img.getHeight(); y++) {
                    Color c2 = new Color(img.getRGB(x, y));
                    Color newC2 = new Color(c2.getRed(), c2.getGreen(), c2.getBlue());
                    for (int k = 0; k < n; k++) {
                        newImg.setRGB(x, y + (img.getHeight() * k), newC2.getRGB());
                    }
                }
            }
        }
        return newImg;
    }

    /**
     * Pixelates an image a number of times.
     *
     * @param img The given image that is going to be pixelated.
     * @param n The number of times the image will be pixelated as an integer.
     * @return A new image that pixelates the original image a number of times.
     */
    static BufferedImage pixelate(BufferedImage img, int n) {
        BufferedImage newImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < img.getHeight(); y+=n) {
            for (int x = 0; x < img.getWidth(); x+=n) {
                int red = 0, green = 0, blue = 0;
                int acc = 0;
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        int rgb = img.getRGB(x+a, y+b);
                        red += rgb >> 16 & 0xFF;
                        green += rgb >> 8 & 0xFF;
                        blue += rgb & 0xFF;
                        acc++;
                    }
                }
                red /= acc;
                green /= acc;
                blue /= acc;
                int avgRGB = (red << 16) | (green << 8) | blue;
                for (int a = 0; a < n; a++) {
                    for (int b = 0; b < n; b++) {
                        newImg.setRGB(x+a, y+b, avgRGB);
                    }
                }
            }
        }
        return newImg;
    }

    /**
     * Zooms in on the image. The zoom factor increases in multiplicative of 10% and
     * decreases in multiplicative of 10%.
     *
     * @param img        the original image to zoom in on. The image cannot be already zoomed in
     *                   or out because then the image will be distorted.
     * @param zoomFactor The factor to zoom in by.
     * @return the zoomed in image.
     */
    static BufferedImage zoom(BufferedImage img, double zoomFactor) {
        int newImageWidth = (int) (img.getWidth() * zoomFactor);
        int newImageHeight = (int) (img.getHeight() * zoomFactor);
        BufferedImage newImg = new BufferedImage(newImageWidth, newImageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = newImg.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(img, 0, 0, newImageWidth, newImageHeight, null);
        g2d.dispose();
        return newImg;
    }
}
