//	Open Checkout is an extensible point of sale software.
//	Copyleft 2012 Open Checkout Team
//	
//		This file is part of Open Checkout.
//		
//    Open Checkout is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Open Checkout is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Open Checkout.  If not, see <http://www.gnu.org/licenses/>.
//
//	Open Checkout is derived from Openbravo POS, unmodified notice below.
//
////////////////////////////////////////////////////////////////////////////////
//    Openbravo POS is a point of sales application designed for touch screens.
//    Copyright (C) 2007-2009 Openbravo, S.L.
//    http://www.openbravo.com/product/pos
//
//    This file is part of Openbravo POS.
//
//    Openbravo POS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//    Openbravo POS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with Openbravo POS.  If not, see <http://www.gnu.org/licenses/>.

package com.opencheckout.pos.printer.screen;

import javax.swing.JComponent;
import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.pos.printer.DeviceDisplay;

/**
 *
 * @author  adrian
 */
public class DeviceDisplayWindow extends javax.swing.JFrame implements DeviceDisplay {
    
    private String m_sName;
    private DeviceDisplayPanel m_display;
    
    /** Creates new form DeviceDisplayWindow */
    public DeviceDisplayWindow() {
        initComponents();
        
        m_sName = AppLocal.getIntString("Display.Window");
        m_display = new DeviceDisplayPanel(3.0);
        
        m_jContainer.add(m_display.getDisplayComponent());
        
        setVisible(true);
    }
    
    public String getDisplayName() {
        return m_sName;
    }    
    public String getDisplayDescription() {
        return null;
    }        
    public JComponent getDisplayComponent() {
        return null;
    }
    
    public void writeVisor(int animation, String sLine1, String sLine2) {
        m_display.writeVisor(animation, sLine1, sLine2);
    }    
    
    public void writeVisor(String sLine1, String sLine2) {
        m_display.writeVisor(sLine1, sLine2);
    }

    public void clearVisor() {
        m_display.clearVisor();
    }
    
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        m_jContainer = new javax.swing.JPanel();

        setTitle(AppLocal.getIntString("Display.Window"));
        m_jContainer.setLayout(new java.awt.BorderLayout());

        m_jContainer.setBorder(new javax.swing.border.EmptyBorder(new java.awt.Insets(5, 5, 5, 5)));
        getContentPane().add(m_jContainer, java.awt.BorderLayout.CENTER);

        setSize(new java.awt.Dimension(767, 245));
    }
    // </editor-fold>//GEN-END:initComponents
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel m_jContainer;
    // End of variables declaration//GEN-END:variables
    
}
