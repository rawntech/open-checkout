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

package com.opencheckout.pos.panels;

import com.opencheckout.basic.BasicException;
import com.opencheckout.data.gui.MessageInf;
import com.opencheckout.data.loader.SerializerWrite;
import com.opencheckout.data.loader.SerializerWriteString;
import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.pos.forms.AppView;
import com.opencheckout.pos.forms.DataLogicSales;
import com.opencheckout.pos.reports.ReportEditorCreator;
import com.opencheckout.pos.ticket.ProductInfoExt;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;
import javax.swing.event.EventListenerList;

/**
 *
 * @author jaroslawwozniak
 * @author adrianromero
 */
public class AuxiliarFilter extends javax.swing.JPanel implements ReportEditorCreator {

    private ProductInfoExt product;
    private DataLogicSales m_dlSales;
    
    protected EventListenerList listeners = new EventListenerList();

    /** Creates new form AuxiliarFilter */
    public AuxiliarFilter() {
        initComponents();
    }

    @Override
    public void init(AppView app) {   
         m_dlSales = (DataLogicSales) app.getBean("com.opencheckout.pos.forms.DataLogicSales");
    }

    @Override
    public void activate() throws BasicException {
        product = null;
        m_jSearch.setText(null);
        m_jBarcode1.setText(null);
        m_jReference1.setText(null);        
    }

    @Override
    public SerializerWrite getSerializerWrite() {
        return SerializerWriteString.INSTANCE;
    }

    public void addActionListener(ActionListener l){
        listeners.add(ActionListener.class, l);
    }
    
    public void removeActionListener(ActionListener l) {
        listeners.remove(ActionListener.class, l);
    }
    
    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public Object createValue() throws BasicException {
        return product == null ? null : product.getID();
    }
    
    public ProductInfoExt getProductInfoExt() {
        return product;
    }

    private void assignProduct(ProductInfoExt prod) {

        product = prod;
        if (product == null) {
            m_jSearch.setText(null);
            m_jBarcode1.setText(null);
            m_jReference1.setText(null);
        } else {
            m_jSearch.setText(product.getReference() + " - " + product.getName());
            m_jBarcode1.setText(product.getCode());
            m_jReference1.setText(product.getReference());
        } 
        
        fireSelectedProduct();
    }
    
    
    
    protected void fireSelectedProduct() {
        EventListener[] l = listeners.getListeners(ActionListener.class);
        ActionEvent e = null;
        for (int i = 0; i < l.length; i++) {
            if (e == null) {
                e = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "SELECTED");
            }
            ((ActionListener) l[i]).actionPerformed(e);	       
        }
    }     

    private void assignProductByCode() {
        try {
            ProductInfoExt prod = m_dlSales.getProductInfoByCode(m_jBarcode1.getText());
            if (prod == null) {
                Toolkit.getDefaultToolkit().beep();
            }
            assignProduct(prod);
        } catch (BasicException eData) {
            MessageInf msg = new MessageInf(eData);
            msg.show(this);
            assignProduct(null);
        }
    }

    private void assignProductByReference() {
        try {
            ProductInfoExt prod = m_dlSales.getProductInfoByReference(m_jReference1.getText());
            if (prod == null) {
                Toolkit.getDefaultToolkit().beep();
            }
            assignProduct(prod);
        } catch (BasicException eData) {
            MessageInf msg = new MessageInf(eData);
            msg.show(this);
            assignProduct(null);
        }
    }
   
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        m_jReference1 = new javax.swing.JTextField();
        Enter1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        m_jBarcode1 = new javax.swing.JTextField();
        Enter2 = new javax.swing.JButton();
        m_jSearch = new javax.swing.JTextField();
        search = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(AppLocal.getIntString("label.byproduct"))); // NOI18N

        jLabel6.setText(AppLocal.getIntString("label.prodref")); // NOI18N

        m_jReference1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jReference1ActionPerformed(evt);
            }
        });

        Enter1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/opencheckout/images/apply.png"))); // NOI18N
        Enter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter1ActionPerformed(evt);
            }
        });

        jLabel7.setText(AppLocal.getIntString("label.prodbarcode")); // NOI18N

        m_jBarcode1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                m_jBarcode1ActionPerformed(evt);
            }
        });

        Enter2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/opencheckout/images/apply.png"))); // NOI18N
        Enter2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Enter2ActionPerformed(evt);
            }
        });

        m_jSearch.setEditable(false);
        m_jSearch.setFocusable(false);
        m_jSearch.setRequestFocusEnabled(false);

        search.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/opencheckout/images/search.png"))); // NOI18N
        search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jReference1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Enter1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(m_jBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Enter2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(m_jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 378, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(search)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(m_jReference1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Enter1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(m_jBarcode1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(search)
                            .addComponent(m_jSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(Enter2))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleName("By product");
    }// </editor-fold>//GEN-END:initComponents

    private void m_jReference1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jReference1ActionPerformed
        this.assignProductByReference();
    }//GEN-LAST:event_m_jReference1ActionPerformed

    private void searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchActionPerformed
        
        assignProduct(JProductFinder.showMessage(this, m_dlSales, JProductFinder.PRODUCT_NORMAL));       
        
}//GEN-LAST:event_searchActionPerformed

    private void Enter2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter2ActionPerformed
        this.assignProductByCode();
    }//GEN-LAST:event_Enter2ActionPerformed

    private void Enter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Enter1ActionPerformed
        this.assignProductByReference();
    }//GEN-LAST:event_Enter1ActionPerformed

    private void m_jBarcode1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_m_jBarcode1ActionPerformed
        this.assignProductByCode();
    }//GEN-LAST:event_m_jBarcode1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Enter1;
    private javax.swing.JButton Enter2;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField m_jBarcode1;
    private javax.swing.JTextField m_jReference1;
    private javax.swing.JTextField m_jSearch;
    private javax.swing.JButton search;
    // End of variables declaration//GEN-END:variables

}
