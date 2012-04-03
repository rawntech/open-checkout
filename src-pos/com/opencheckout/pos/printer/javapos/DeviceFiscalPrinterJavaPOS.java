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

package com.opencheckout.pos.printer.javapos;

import javax.swing.JComponent;
import jpos.FiscalPrinter;
import jpos.JposException;
import com.opencheckout.pos.printer.DeviceFiscalPrinter;
import com.opencheckout.pos.printer.TicketPrinterException;
import com.opencheckout.pos.util.RoundUtils;

public class DeviceFiscalPrinterJavaPOS extends javax.swing.JPanel implements DeviceFiscalPrinter  {
    
    private String m_sName;
    
    private FiscalPrinter m_fiscal;
    
    /** Creates new form DeviceFiscalPrinterJavaPOSPanel */
    public DeviceFiscalPrinterJavaPOS(String sDeviceFiscalPrinterName) throws TicketPrinterException {
        m_sName = sDeviceFiscalPrinterName;
        
        
        m_fiscal = new FiscalPrinter();
        try {       
            m_fiscal.open(m_sName);
            m_fiscal.claim(10000);
            m_fiscal.setDeviceEnabled(true);
            // m_printer.setMapMode(POSPrinterConst.PTR_MM_METRIC);  // unit = 1/100 mm - i.e. 1 cm = 10 mm = 10 * 100 units

            m_fiscal.setCheckTotal(false);
            
        } catch (JposException e) {
            throw new TicketPrinterException(e.getMessage(), e);
        }
        
        initComponents();
        
        
    }
 
    public String getFiscalName() {
        return m_sName;
    }
    public JComponent getFiscalComponent() {
        return this;
    }
    
    public void beginReceipt() {
        try {
            m_fiscal.beginFiscalReceipt(true);
        } catch (JposException e) {
        }
    }
    public void endReceipt() {
        try {
            m_fiscal.endFiscalReceipt(false);
        } catch (JposException e) {
        }        
    }
    
    public void printLine(String sproduct, double dprice, double dunits, int taxinfo) {
        try {
            m_fiscal.printRecItem(sproduct, roundFiscal(dprice * dunits), (int)(dunits * 1000), taxinfo, roundFiscal(dprice), "");
        } catch (JposException e) {
        }             
    }
    
    public void printMessage(String smessage) {
        try {
            m_fiscal.printRecMessage(smessage);
        } catch (JposException e) {
        } 
    }
    
    public void printTotal(String sPayment, double dpaid) {
        try {
            // el primer valor es el total calculado por la aplicacion.
            // al poner 0 no se debe chequear: CAPCHECKTOTAL = false.
            m_fiscal.printRecTotal(0, roundFiscal(dpaid), sPayment);
        } catch (JposException e) {
        }          
    }
    
    public void printZReport() {
        try {
            m_fiscal.printZReport();
        } catch (JposException e) {
        }          
    }
    
    public void printXReport() {
        try {
            m_fiscal.printXReport();
        } catch (JposException e) {
        }     
    }
    
    public void finalize() throws Throwable {
    
        m_fiscal.setDeviceEnabled(false);
        m_fiscal.release();
        m_fiscal.close();
        
        super.finalize();       
    } 
    
    private int roundFiscal(double value) {
        return (int) Math.floor(RoundUtils.round(value) * 10000.0 + 0.5);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jCheckBox1 = new javax.swing.JCheckBox();
        jTextField2 = new javax.swing.JTextField();
        jCheckBox2 = new javax.swing.JCheckBox();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jCheckBox3 = new javax.swing.JCheckBox();
        jCheckBox4 = new javax.swing.JCheckBox();
        jCheckBox5 = new javax.swing.JCheckBox();
        jButton2 = new javax.swing.JButton();

        setLayout(null);

        jButton1.setText("*X Report");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        add(jButton1);
        jButton1.setBounds(30, 10, 130, 23);

        jPanel1.setLayout(null);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("*Receipt Title"));
        jTextField1.setText("jTextField1");
        jPanel1.add(jTextField1);
        jTextField1.setBounds(20, 30, 260, 19);

        jCheckBox1.setText("jCheckBox1");
        jCheckBox1.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox1.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jCheckBox1);
        jCheckBox1.setBounds(290, 30, 73, 15);

        jTextField2.setText("jTextField2");
        jPanel1.add(jTextField2);
        jTextField2.setBounds(20, 60, 260, 19);

        jCheckBox2.setText("jCheckBox2");
        jCheckBox2.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox2.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jCheckBox2);
        jCheckBox2.setBounds(290, 60, 73, 15);

        jTextField3.setText("jTextField3");
        jPanel1.add(jTextField3);
        jTextField3.setBounds(20, 90, 260, 19);

        jTextField4.setText("jTextField4");
        jPanel1.add(jTextField4);
        jTextField4.setBounds(20, 120, 260, 19);

        jTextField5.setText("jTextField5");
        jPanel1.add(jTextField5);
        jTextField5.setBounds(20, 150, 260, 19);

        jCheckBox3.setText("jCheckBox3");
        jCheckBox3.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox3.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jCheckBox3);
        jCheckBox3.setBounds(290, 90, 73, 15);

        jCheckBox4.setText("jCheckBox4");
        jCheckBox4.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox4.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jCheckBox4);
        jCheckBox4.setBounds(290, 120, 73, 15);

        jCheckBox5.setText("jCheckBox5");
        jCheckBox5.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        jCheckBox5.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jPanel1.add(jCheckBox5);
        jCheckBox5.setBounds(290, 150, 73, 15);

        jButton2.setText("*Z Report");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.add(jButton2);
        jButton2.setBounds(20, 220, 130, 23);

        add(jPanel1);
        jPanel1.setBounds(10, 60, 470, 260);

    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        printZReport();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        printXReport();
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox4;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
    
}
