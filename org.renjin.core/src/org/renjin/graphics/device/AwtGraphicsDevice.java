package org.renjin.graphics.device;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;

import org.renjin.graphics.GraphicParameters;
import org.renjin.graphics.GraphicsDeviceDriver;
import org.renjin.graphics.geom.Dimension;
import org.renjin.graphics.geom.Rectangle;


/**
 * Graphics device using the java.awt toolkit for
 * drawing
 */
public class AwtGraphicsDevice implements GraphicsDeviceDriver {

  private final Graphics2D g2d;
  private org.renjin.graphics.geom.Dimension size;

  public AwtGraphicsDevice(Graphics2D g2d, org.renjin.graphics.geom.Dimension sizeInches) {
    this.g2d = g2d;
    this.size = sizeInches;
  }
  
  public AwtGraphicsDevice(Graphics2D g2d) {
    this.g2d = g2d;
    java.awt.Rectangle bounds = g2d.getDeviceConfiguration().getBounds();    
    size = new org.renjin.graphics.geom.Dimension(bounds.getWidth() / 72d, bounds.getHeight() / 72d);
  }

  @Override
  public void drawRectangle(org.renjin.graphics.geom.Rectangle rect, 
                            org.renjin.graphics.Color fillColor, 
                            org.renjin.graphics.Color borderColor,
                            GraphicParameters parameters) {
    
    Rectangle2D shape = new Rectangle2D.Double(rect.getX1(), rect.getY1(), rect.getWidth(), rect.getHeight());

    if(!fillColor.isTransparent()) {
      g2d.setPaint(toAwtColor(fillColor));
      g2d.fill(shape);
    }

    if(!borderColor.isTransparent()) {
      g2d.setColor(toAwtColor(borderColor));
      g2d.setStroke(currentStroke(parameters));
      g2d.draw(shape);
    }
  }
  
  private Stroke currentStroke(GraphicParameters parameters) {
    double lwd = parameters.getLineWidth();
    return new BasicStroke((float) lwd);
  }

  private java.awt.Color toAwtColor(org.renjin.graphics.Color color) {
    return new java.awt.Color(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
  }

  @Override
  public Dimension getInchesPerPixel() {
    Rectangle bounds = getDeviceRegion();
    return new org.renjin.graphics.geom.Dimension(
        size.getWidth() / bounds.getWidth(),
        size.getHeight() / bounds.getHeight());
  }

  @Override
  public Dimension getCharacterSize() {
    return new Dimension(10.8, 14.4);
  }

  @Override
  public Rectangle getDeviceRegion() {
    java.awt.Rectangle bounds = g2d.getDeviceConfiguration().getBounds();
    return new Rectangle(bounds.getMinX(), bounds.getMaxX(), bounds.getMinY(), bounds.getMaxY());
  }
  
}
