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

package com.opencheckout.pos.mant;

import java.awt.Component;
import java.util.UUID;
import javax.swing.*;

import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.format.Formats;
import com.opencheckout.basic.BasicException;
import com.opencheckout.data.gui.ComboBoxValModel;
import com.opencheckout.data.loader.SentenceList;
import com.opencheckout.data.user.EditorRecord;
import com.opencheckout.data.user.DirtyManager;
import com.opencheckout.pos.forms.DataLogicSales;

/**
 *
 * @author adrianromero
 */
public class PlacesEditor extends JPanel implements EditorRecord {
    
    private SentenceList m_sentfloor;
    private ComboBoxValModel m_FloorModel;
    
    private String m_sID;
    
    /** Creates new form PlacesEditor */
    public PlacesEditor(DataLogicSales dlSales, DirtyManager dirty) {
        initComponents();
        
        m_sentfloor = dlSales.getFloorsList();
        m_FloorModel = new ComboBoxValModel();
        
        m_jName.getDocument().addDocumentListener(dirty);
        m_jFloor.addActionListener(dirty);
        m_jX.getDocument().addDocumentListener(dirty);
        m_jY.getDocument().addDocumentListener(dirty);
        
        writeValueEOF();
    }
    
    public void activate() throws BasicException {
        
        m_FloorModel = new ComboBoxValModel(m_sentfloor.list());
        m_jFloor.setModel(m_FloorModel);
    }
    
    public void refresh() {
    }   
    
    public void writeValueEOF() {
        
        m_sID = null;
        m_jName.setText(null);
        m_FloorModel.setSelectedKey(null);
        m_jX.setText(null);
        m_jY.setText(null);

        m_jName.setEnabled(false);
        m_jFloor.setEnabled(false);
        m_jX.setEnabled(false);
        m_jY.setEnabled(false);
    }
    public void writeValueInsert() {

        m_sID = UUID.randomUUID().toString(); 
        m_jName.setText(null);
        m_FloorModel.setSelectedKey(null);
        m_jX.setText(null);
        m_jY.setText(null);
        
        m_jName.setEnabled(true);
        m_jFloor.setEnabled(true);
        m_jX.setEnabled(true);
        m_jY.setEnabled(true);
    }
    public void writeValueDelete(Object value) {
        
        Object[] place = (Object[]) value;
        m_sID = Formats.STRING.formatValue(place[0]);
        m_jName.setText(Formats.STRING.formatValue(place[1]));
        m_jX.setText(Formats.INT.formatValue(place[2]));
        m_jY.setText(Formats.INT.formatValue(place[3]));
        m_FloorModel.setSelectedKey(place[4]);

        m_jName.setEnabled(false);
        m_jFloor.setEnabled(false);
        m_jX.setEnabled(false);
        m_jY.setEnabled(false);
    }
    public void writeValueEdit(Object value) {
        
        Object[] place = (Object[]) value;
        m_sID = Formats.STRING.formatValue(place[0]);
        m_jName.setText(Formats.STRING.formatValue(place[1]));
        m_jX.setText(Formats.INT.formatValue(place[2]));
        m_jY.setText(Formats.INT.formatValue(place[3]));
        m_FloorModel.setSelectedKey(place[4]);

        m_jName.setEnabled(true);
        m_jFloor.setEnabled(true);
        m_jX.setEnabled(true);
        m_jY.setEnabled(true);
    }
    public Object createValue() throws BasicException {
        Object[] place = new Object[5];
        place[0] = m_sID;
        place[1] = m_jName.getText();
        place[2] = Formats.INT.parseValue(m_jX.getText());
        place[3] = Formats.INT.parseValue(m_jY.getText());
        place[4] = m_FloorModel.getSelectedKey();
        return place;
    }
    
    public Component getComponent() {
        return this;
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jLabel2 = new javax.swing.JLabel();
        m_jName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        m_jX = new javax.swing.JTextField();
        m_jY = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        m_jFloor = new javax.swing.JComboBox();

        setLayout(null);

        jLabel2.setText(AppLocal.getIntString("Label.Name"));
        add(jLabel2);
        jLabel2.setBounds(20, 20, 90, 15);

        add(m_jName);
        m_jName.setBounds(110, 20, 180, 19);

        jLabel3.setText(AppLocal.getIntString("label.placeposition"));
        add(jLabel3);
        jLabel3.setBounds(20, 80, 90, 15);

        add(m_jX);
        m_jX.setBounds(110, 80, 50, 19);

        add(m_jY);
        m_jY.setBounds(170, 80, 50, 19);

        jLabel1.setText(AppLocal.getIntString("label.placefloor"));
        add(jLabel1);
        jLabel1.setBounds(20, 50, 90, 15);

        add(m_jFloor);
        m_jFloor.setBounds(110, 50, 170, 20);

    }// </editor-fold>//GEN-END:initComponents
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox m_jFloor;
    private javax.swing.JTextField m_jName;
    private javax.swing.JTextField m_jX;
    private javax.swing.JTextField m_jY;
    // End of variables declaration//GEN-END:variables
    
}
