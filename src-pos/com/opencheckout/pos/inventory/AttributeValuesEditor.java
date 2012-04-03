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
//    Copyright (C) 2008-2009 Openbravo, S.L.
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

package com.opencheckout.pos.inventory;

import com.opencheckout.basic.BasicException;
import com.opencheckout.data.user.DirtyManager;
import com.opencheckout.data.user.EditorRecord;
import com.opencheckout.format.Formats;
import com.opencheckout.pos.forms.AppLocal;
import java.awt.Component;
import java.util.UUID;

/**
 *
 * @author adrian
 */
public class AttributeValuesEditor extends javax.swing.JPanel implements EditorRecord {

    private Object id;
    private Object attid;

    private Object insertid;

    /** Creates new form AttributesValuesEditor */
    public AttributeValuesEditor(DirtyManager dirty) {
        
        initComponents();

        jValue.getDocument().addDocumentListener(dirty);
    }

    public void setInsertId(String insertid) {

        this.insertid = insertid;
    }

    public void refresh() {
    }

    public void writeValueEOF() {

        id = null;
        attid = null;
        jValue.setText(null);

        jValue.setEnabled(false);
    }

    public void writeValueInsert() {

        id = UUID.randomUUID().toString();
        attid = insertid;
        jValue.setText(null);

        jValue.setEnabled(true);
    }

    public void writeValueEdit(Object value) {

        Object[] obj = (Object[]) value;

        id = obj[0];
        attid = obj[1];
        jValue.setText(Formats.STRING.formatValue(obj[2]));

        jValue.setEnabled(true);
    }

    public void writeValueDelete(Object value) {

        Object[] obj = (Object[]) value;

        id = obj[0];
        attid = obj[1];
        jValue.setText(Formats.STRING.formatValue(obj[2]));

        jValue.setEnabled(false);
    }

    public Component getComponent() {
        return this;
    }

    public Object createValue() throws BasicException {
        return new Object[] {
            id,
            attid,
            Formats.STRING.formatValue(jValue.getText())
        };
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jValue = new javax.swing.JTextField();

        jLabel2.setText(AppLocal.getIntString("label.value")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 316, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jValue, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 43, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField jValue;
    // End of variables declaration//GEN-END:variables


}
