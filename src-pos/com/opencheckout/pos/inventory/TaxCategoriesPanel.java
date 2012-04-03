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

import com.opencheckout.pos.panels.*;
import javax.swing.ListCellRenderer;
import com.opencheckout.data.gui.ListCellRendererBasic;
import com.opencheckout.data.loader.ComparatorCreator;
import com.opencheckout.pos.forms.AppLocal;
import com.opencheckout.data.loader.TableDefinition;
import com.opencheckout.data.loader.Vectorer;
import com.opencheckout.data.user.EditorRecord;
import com.opencheckout.data.user.SaveProvider;
import com.opencheckout.data.user.ListProvider;
import com.opencheckout.data.user.ListProviderCreator;
import com.opencheckout.pos.forms.DataLogicSales;

/**
 *
 * @author adrianromero
 */
public class TaxCategoriesPanel extends JPanelTable {

    private TableDefinition ttaxcategories;
    private TaxCustCategoriesEditor jeditor;
    
    /** Creates a new instance of JPanelDuty */
    public TaxCategoriesPanel() {
    }
    
    protected void init() {
        DataLogicSales dlSales = (DataLogicSales) app.getBean("com.opencheckout.pos.forms.DataLogicSales");        
        ttaxcategories = dlSales.getTableTaxCategories();
        jeditor = new TaxCustCategoriesEditor(dirty);
    }
    
    public ListProvider getListProvider() {
        return new ListProviderCreator(ttaxcategories);
    }
    
    public SaveProvider getSaveProvider() {
        return new SaveProvider(ttaxcategories);      
    }
    
    @Override
    public Vectorer getVectorer() {
        return ttaxcategories.getVectorerBasic(new int[]{1});
    }
    
    @Override
    public ComparatorCreator getComparatorCreator() {
        return ttaxcategories.getComparatorCreator(new int[] {1});
    }
    
    @Override
    public ListCellRenderer getListCellRenderer() {
        return new ListCellRendererBasic(ttaxcategories.getRenderStringBasic(new int[]{1}));
    }
    
    public EditorRecord getEditor() {
        return jeditor;
    }
        
    public String getTitle() {
        return AppLocal.getIntString("Menu.TaxCategories");
    }     
}