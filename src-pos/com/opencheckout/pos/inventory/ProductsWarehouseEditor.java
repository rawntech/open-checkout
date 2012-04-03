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
//    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-130

package com.opencheckout.pos.inventory;

import java.awt.Component;
import com.opencheckout.basic.BasicException;
import com.opencheckout.data.user.DirtyManager;
import com.opencheckout.data.user.EditorRecord;
import com.opencheckout.format.Formats;
import com.opencheckout.pos.forms.AppLocal;

/**
 *
 * @author adrianromero
 */
public class ProductsWarehouseEditor extends javax.swing.JPanel implements EditorRecord {

    public Object id;
    public Object prodid;
    public Object prodref;
    public Object prodname;
    public Object location;
    
    /** Creates new form ProductsWarehouseEditor */
    public ProductsWarehouseEditor(DirtyManager dirty) {
        initComponents();
        
        m_jMinimum.getDocument().addDocumentListener(dirty);
        m_jMaximum.getDocument().addDocumentListener(dirty);
    }
    
    public void writeValueEOF() {
        m_jTitle.setText(AppLocal.getIntString("label.recordeof"));
        id = null;
        prodid = null;
        prodref = null;
        prodname = null;
        location = null;
        m_jQuantity.setText(null);
        m_jMinimum.setText(null);
        m_jMaximum.setText(null);
        m_jMinimum.setEnabled(false);
        m_jMaximum.setEnabled(false);
    }
    public void writeValueInsert() {
        m_jTitle.setText(AppLocal.getIntString("label.recordnew"));
        id = null;
        prodid = null;
        prodref = null;
        prodname = null;
        location = null;
        m_jQuantity.setText(null);
        m_jMinimum.setText(null);
        m_jMaximum.setText(null);
        m_jMinimum.setEnabled(true);
        m_jMaximum.setEnabled(true);
    }
    public void writeValueEdit(Object value) {
        Object[] myprod = (Object[]) value;
        id = myprod[0];
        prodid = myprod[1];
        prodref = myprod[2];
        prodname = myprod[3];
        location = myprod[4];
        m_jTitle.setText(Formats.STRING.formatValue(myprod[2]) + " - " + Formats.STRING.formatValue(myprod[3]));
        m_jQuantity.setText(Formats.DOUBLE.formatValue(myprod[7]));
        m_jMinimum.setText(Formats.DOUBLE.formatValue(myprod[5]));
        m_jMaximum.setText(Formats.DOUBLE.formatValue(myprod[6]));
        m_jMinimum.setEnabled(true);
        m_jMaximum.setEnabled(true);
     }
    public void writeValueDelete(Object value) {
        Object[] myprod = (Object[]) value;
        id = myprod[0];
        prodid = myprod[1];
        prodref = myprod[2];
        prodname = myprod[3];
        location = myprod[4];
        m_jTitle.setText(Formats.STRING.formatValue(myprod[2]) + " - " + Formats.STRING.formatValue(myprod[3]));
        m_jQuantity.setText(Formats.DOUBLE.formatValue(myprod[7]));
        m_jMinimum.setText(Formats.DOUBLE.formatValue(myprod[5]));
        m_jMaximum.setText(Formats.DOUBLE.formatValue(myprod[6]));
        m_jMinimum.setEnabled(false);
        m_jMaximum.setEnabled(false);
    }
    public Object createValue() throws BasicException {
        return new Object[] {
            id,
            prodid,
            prodref,
            prodname,
            location,
            Formats.DOUBLE.parseValue(m_jMinimum.getText()),
            Formats.DOUBLE.parseValue(m_jMaximum.getText()),
            Formats.DOUBLE.parseValue(m_jQuantity.getText())
        };
    }
    
    public Component getComponent() {
        return this;
    }
    
    public void refresh() {
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        m_jTitle = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        m_jQuantity = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        m_jMinimum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        m_jMaximum = new javax.swing.JTextField();

        setLayout(null);

        m_jTitle.setFont(new java.awt.Font("SansSerif", 3, 18));
        add(m_jTitle);
        m_jTitle.setBounds(10, 10, 320, 30);

        jLabel3.setText(AppLocal.getIntString("label.units"));
        add(jLabel3);
        jLabel3.setBounds(10, 50, 150, 15);

        m_jQuantity.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        m_jQuantity.setEnabled(false);
        add(m_jQuantity);
        m_jQuantity.setBounds(160, 50, 80, 19);

        jLabel4.setText(AppLocal.getIntString("label.minimum"));
        add(jLabel4);
        jLabel4.setBounds(10, 80, 150, 15);

        m_jMinimum.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(m_jMinimum);
        m_jMinimum.setBounds(160, 80, 80, 19);

        jLabel5.setText(AppLocal.getIntString("label.maximum"));
        add(jLabel5);
        jLabel5.setBounds(10, 110, 150, 15);

        m_jMaximum.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        add(m_jMaximum);
        m_jMaximum.setBounds(160, 110, 80, 19);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField m_jMaximum;
    private javax.swing.JTextField m_jMinimum;
    private javax.swing.JTextField m_jQuantity;
    private javax.swing.JLabel m_jTitle;
    // End of variables declaration//GEN-END:variables
    
}
