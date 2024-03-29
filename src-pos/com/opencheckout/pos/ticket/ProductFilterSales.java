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

package com.opencheckout.pos.ticket;

import java.util.List;
import com.opencheckout.basic.BasicException;
import com.opencheckout.data.gui.ComboBoxValModel;
import com.opencheckout.data.gui.ListQBFModelNumber;
import com.opencheckout.data.loader.QBFCompareEnum;
import com.opencheckout.data.loader.SentenceList;
import com.opencheckout.data.user.EditorCreator;
import com.opencheckout.editor.JEditorKeys;
import com.opencheckout.editor.JEditorString;
import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.pos.forms.DataLogicSales;

public class ProductFilterSales extends javax.swing.JPanel implements EditorCreator {
    
    private SentenceList m_sentcat;
    private ComboBoxValModel m_CategoryModel;
    
    /** Creates new form ProductFilterSales */
    public ProductFilterSales(DataLogicSales dlSales, JEditorKeys jKeys) {
        initComponents();
        
        // El modelo de categorias
        m_sentcat = dlSales.getCategoriesList();
        m_CategoryModel = new ComboBoxValModel();           
        
        m_jCboPriceBuy.setModel(ListQBFModelNumber.getMandatoryNumber());
        m_jPriceBuy.addEditorKeys(jKeys);
        
        m_jCboPriceSell.setModel(ListQBFModelNumber.getMandatoryNumber());
        m_jPriceSell.addEditorKeys(jKeys);
        
        m_jtxtName.addEditorKeys(jKeys);
        
        m_jtxtBarCode.addEditorKeys(jKeys);
    }
    
    public void activate() {
        
        m_jtxtBarCode.reset();
        m_jtxtBarCode.setEditModeEnum(JEditorString.MODE_123);
        m_jtxtName.reset();
        m_jPriceBuy.reset();
        m_jPriceSell.reset();
        m_jtxtName.activate();
        
        try {
            List catlist = m_sentcat.list();
            catlist.add(0, null);
            m_CategoryModel = new ComboBoxValModel(catlist);
            m_jCategory.setModel(m_CategoryModel);
        } catch (BasicException eD) {
            // no hay validacion
        }
    }
    
    public Object createValue() throws BasicException {
        
        Object[] afilter = new Object[10];
        
        // Nombre
        if (m_jtxtName.getText() == null || m_jtxtName.getText().equals("")) {
            afilter[0] = QBFCompareEnum.COMP_NONE;
            afilter[1] = null;
        } else {
            afilter[0] = QBFCompareEnum.COMP_RE;
            afilter[1] = "%" + m_jtxtName.getText() + "%";
        }
        
        // Precio de compra
        afilter[3] = m_jPriceBuy.getDoubleValue();
        afilter[2] = afilter[3] == null ? QBFCompareEnum.COMP_NONE : m_jCboPriceBuy.getSelectedItem();

        // Precio de venta
        afilter[5] = m_jPriceSell.getDoubleValue();
        afilter[4] = afilter[5] == null ? QBFCompareEnum.COMP_NONE : m_jCboPriceSell.getSelectedItem();
        
        // Categoria
        if (m_CategoryModel.getSelectedKey() == null) {
            afilter[6] = QBFCompareEnum.COMP_NONE;
            afilter[7] = null;
        } else {
            afilter[6] = QBFCompareEnum.COMP_EQUALS;
            afilter[7] = m_CategoryModel.getSelectedKey();
        }
        
        // el codigo de barras
        if (m_jtxtBarCode.getText() == null || m_jtxtBarCode.getText().equals("")) {
            afilter[8] = QBFCompareEnum.COMP_NONE;
            afilter[9] = null;
        } else{
            afilter[8] = QBFCompareEnum.COMP_RE;
            afilter[9] = "%" + m_jtxtBarCode.getText() + "%";
        }
        
        return afilter;
    } 
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel5 = new javax.swing.JLabel();
        m_jtxtName = new com.opencheckout.editor.JEditorString();
        jLabel2 = new javax.swing.JLabel();
        m_jCategory = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        m_jCboPriceBuy = new javax.swing.JComboBox();
        m_jPriceBuy = new com.opencheckout.editor.JEditorCurrency();
        jLabel3 = new javax.swing.JLabel();
        m_jCboPriceSell = new javax.swing.JComboBox();
        m_jPriceSell = new com.opencheckout.editor.JEditorCurrency();
        m_jtxtBarCode = new com.opencheckout.editor.JEditorString();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(370, 170));
        setLayout(null);

        jLabel5.setText(AppLocal.getIntString("label.prodname")); // NOI18N
        add(jLabel5);
        jLabel5.setBounds(20, 40, 110, 15);
        add(m_jtxtName);
        m_jtxtName.setBounds(130, 40, 290, 25);

        jLabel2.setText(AppLocal.getIntString("label.prodcategory")); // NOI18N
        add(jLabel2);
        jLabel2.setBounds(20, 70, 110, 15);
        add(m_jCategory);
        m_jCategory.setBounds(130, 70, 260, 24);

        jLabel4.setText(AppLocal.getIntString("label.prodpricebuy")); // NOI18N
        add(jLabel4);
        jLabel4.setBounds(20, 100, 110, 15);
        add(m_jCboPriceBuy);
        m_jCboPriceBuy.setBounds(130, 100, 150, 24);
        add(m_jPriceBuy);
        m_jPriceBuy.setBounds(290, 100, 130, 25);

        jLabel3.setText(AppLocal.getIntString("label.prodpricesell")); // NOI18N
        add(jLabel3);
        jLabel3.setBounds(20, 130, 110, 15);
        add(m_jCboPriceSell);
        m_jCboPriceSell.setBounds(130, 130, 150, 24);
        add(m_jPriceSell);
        m_jPriceSell.setBounds(290, 130, 130, 25);
        add(m_jtxtBarCode);
        m_jtxtBarCode.setBounds(130, 10, 290, 25);

        jLabel1.setText(AppLocal.getIntString("label.prodbarcode")); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(20, 10, 110, 15);
    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JComboBox m_jCategory;
    private javax.swing.JComboBox m_jCboPriceBuy;
    private javax.swing.JComboBox m_jCboPriceSell;
    private com.opencheckout.editor.JEditorCurrency m_jPriceBuy;
    private com.opencheckout.editor.JEditorCurrency m_jPriceSell;
    private com.opencheckout.editor.JEditorString m_jtxtBarCode;
    private com.opencheckout.editor.JEditorString m_jtxtName;
    // End of variables declaration//GEN-END:variables
    
}
