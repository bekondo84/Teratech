/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author DEV_4
 */
public class RoundButton extends JButton{
    
     public RoundButton(Icon icon) {
    super(icon);
    //setBorderPainted(false);
    //setFocusPainted(false);
    setContentAreaFilled(false);
    setRolloverEnabled(true);
    setRolloverIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/calendar.png")));
    setSelectedIcon(new ImageIcon(getClass().getResource("/com/megatim/tools/images/service.png")));
    this.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
  }
 
  /**
   * détermine si le point (x, y) est à l'intérieur de l'icône circulaire
   */
  public boolean contains(int x, int y) {
    Dimension size = getSize();
    float x0 = size.width / 2F;
    float y0 = size.height / 2F;
 
    Icon icon = getIcon();
    float w = icon.getIconWidth() / 2F;
    float h = icon.getIconHeight() / 2F;
 
    return (x - x0) * (x - x0) + (y - y0) * (y - y0) <= w * h;
  }
}
