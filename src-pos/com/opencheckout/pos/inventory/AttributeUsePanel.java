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
import com.opencheckout.data.loader.Datas;
import com.opencheckout.data.model.Column;
import com.opencheckout.data.model.Field;
import com.opencheckout.data.model.PrimaryKey;
import com.opencheckout.data.model.Row;
import com.opencheckout.data.model.Table;
import com.opencheckout.data.user.EditorRecord;
import com.opencheckout.format.Formats;
import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.pos.panels.JPanelTable2;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author adrianromero
 */
public class AttributeUsePanel extends JPanelTable2 {

    private AttributeUseEditor editor;
    private AttributeSetFilter filter;

    protected void init() {

        filter = new AttributeSetFilter();
        filter.init(app);
        filter.addActionListener(new ReloadActionListener());

        row = new Row(
                new Field("ID", Datas.STRING, Formats.STRING),
                new Field("ATRIBUTESET_ID", Datas.STRING, Formats.STRING),
                new Field("ATTRIBUTE_ID", Datas.STRING, Formats.STRING),
                new Field(AppLocal.getIntString("label.order"), Datas.INT, Formats.INT, false, true, true),
                new Field(AppLocal.getIntString("label.name"), Datas.STRING, Formats.STRING, true, true, true)
        );

        Table table = new Table(
                "ATTRIBUTEUSE",
                new PrimaryKey("ID"),
                new Column("ATTRIBUTESET_ID"),
                new Column("ATTRIBUTE_ID"),
                new Column("LINENO"));

        lpr = row.getListProvider(app.getSession(),
                "SELECT ATTUSE.ID, ATTUSE.ATTRIBUTESET_ID, ATTUSE.ATTRIBUTE_ID, ATTUSE.LINENO, ATT.NAME " +
                "FROM ATTRIBUTEUSE ATTUSE, ATTRIBUTE ATT " +
                "WHERE ATTUSE.ATTRIBUTE_ID = ATT.ID AND ATTUSE.ATTRIBUTESET_ID = ? ORDER BY LINENO", filter);
        spr = row.getSaveProvider(app.getSession(), table);

        editor = new AttributeUseEditor(app, dirty);
    }

    @Override
    public void activate() throws BasicException {
        filter.activate();
        editor.activate();

        //super.activate();
        startNavigation();
        reload();
    }

    @Override
    public Component getFilter(){
        return filter.getComponent();
    }

    public EditorRecord getEditor() {
        return editor;
    }

    private void reload() throws BasicException {

        String attsetid = (String) filter.createValue();
        editor.setInsertId(attsetid); // must be set before load
        bd.setEditable(attsetid != null);
        bd.actionLoad();
    }

    public String getTitle() {
        return AppLocal.getIntString("Menu.AttributeUse");
    }

    private class ReloadActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                reload();
            } catch (BasicException w) {
            }
        }
    }
}
