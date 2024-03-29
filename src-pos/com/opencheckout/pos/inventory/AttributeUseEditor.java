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
import com.opencheckout.data.gui.ComboBoxValModel;
import com.opencheckout.data.loader.DataRead;
import com.opencheckout.data.loader.SentenceList;
import com.opencheckout.data.loader.SerializerRead;
import com.opencheckout.data.loader.StaticSentence;
import com.opencheckout.data.user.DirtyManager;
import com.opencheckout.data.user.EditorRecord;
import com.opencheckout.format.Formats;
import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.pos.forms.AppView;
import java.awt.Component;
import java.util.UUID;

/**
 *
 * @author adrianromero
 */
public class AttributeUseEditor extends javax.swing.JPanel implements EditorRecord {

    private SentenceList attributesent;
    private ComboBoxValModel attributemodel;

    private Object id;
    private Object attuseid;

    private Object insertid;

    /** Creates new form AttributeSetEditor */
    public AttributeUseEditor(AppView app, DirtyManager dirty) {

        attributesent = new StaticSentence(app.getSession()
            , "SELECT ID, NAME FROM ATTRIBUTE ORDER BY NAME"
            , null
            , new SerializerRead() { public Object readValues(DataRead dr) throws BasicException {
                return new AttributeInfo(dr.getString(1), dr.getString(2));
            }}
        );
        attributemodel = new ComboBoxValModel();

        initComponents();

        jLineno.getDocument().addDocumentListener(dirty);
        jAttribute.addActionListener(dirty);
    }

    public void setInsertId(String insertid) {

        this.insertid = insertid;
    }

    public void activate() throws BasicException {

        attributemodel = new ComboBoxValModel(attributesent.list());
        jAttribute.setModel(attributemodel);
    }

    public void refresh() {
    }

    public void writeValueEOF() {

        id = null;
        attuseid = null;
        attributemodel.setSelectedKey(null);
        jLineno.setText(null);

        jAttribute.setEnabled(false);
        jLineno.setEnabled(false);
    }

    public void writeValueInsert() {

        id = UUID.randomUUID().toString();
        attuseid = insertid;
        attributemodel.setSelectedKey(null);
        jLineno.setText(null);

        jAttribute.setEnabled(true);
        jLineno.setEnabled(true);
    }

    public void writeValueEdit(Object value) {

        Object[] obj = (Object[]) value;

        id = obj[0];
        attuseid = obj[1];
        attributemodel.setSelectedKey(obj[2]);
        jLineno.setText(Formats.INT.formatValue(obj[3]));

        jAttribute.setEnabled(true);
        jLineno.setEnabled(true);
    }

    public void writeValueDelete(Object value) {

        Object[] obj = (Object[]) value;

        id = obj[0];
        attuseid = obj[1];
        attributemodel.setSelectedKey(obj[2]);
        jLineno.setText(Formats.INT.formatValue(obj[3]));

        jAttribute.setEnabled(false);
        jLineno.setEnabled(false);
    }

    public Component getComponent() {
        return this;
    }

    public Object createValue() throws BasicException {
        Object[] value = new Object[5];

        value[0] = id;
        value[1] = attuseid;
        value[2] = attributemodel.getSelectedKey();
        value[3] = Formats.INT.parseValue(jLineno.getText());
        value[4] = attributemodel.getSelectedText();

        return value;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jLineno = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jAttribute = new javax.swing.JComboBox();

        jLabel3.setText(AppLocal.getIntString("label.order")); // NOI18N

        jLabel4.setText(AppLocal.getIntString("label.attribute")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLineno, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLineno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jAttribute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(273, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox jAttribute;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jLineno;
    // End of variables declaration//GEN-END:variables


}
