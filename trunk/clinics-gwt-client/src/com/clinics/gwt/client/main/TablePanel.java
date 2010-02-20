package com.clinics.gwt.client.main;

import java.util.ArrayList;
import java.util.Iterator;

import com.google.gwt.gen2.table.client.AbstractColumnDefinition;
import com.google.gwt.gen2.table.client.CellRenderer;
import com.google.gwt.gen2.table.client.ColumnDefinition;
import com.google.gwt.gen2.table.client.DefaultTableDefinition;
import com.google.gwt.gen2.table.client.PagingScrollTable;
import com.google.gwt.gen2.table.client.TableModel;
import com.google.gwt.gen2.table.client.TableDefinition.AbstractCellView;
import com.google.gwt.gen2.table.client.TableModelHelper.Request;
import com.google.gwt.gen2.table.client.TableModelHelper.Response;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.widgetideas.graphics.client.Color;
import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

public class TablePanel extends Composite {

    public TablePanel() {

        FlowPanel p = new FlowPanel();
        PagingScrollTable table = createPagingTable();
        p.add(table);

        initWidget(p);
    }

    protected PagingScrollTable createPagingTable() {
        TableModel<TableRowData> tableModel = new TableModel<TableRowData>() {
            @Override
            public void requestRows(
                    Request request,
                    com.google.gwt.gen2.table.client.TableModel.Callback<TableRowData> callback) {

                Response<TableRowData> response = new Response<TableRowData>() {
                    @Override
                    public Iterator<TableRowData> getRowValues() {

                        ArrayList<TableRowData> ret = new ArrayList<TableRowData>();
                        ret.add(new TableRowData("aaaa", 2, 10));
                        ret.add(new TableRowData("bbbb", 1, 20));
                        ret.add(new TableRowData("cccc", 7, 100));
                        return ret.iterator();
                    }
                };
                callback.onRowsReady(request, response);
            }
        };
        AbstractColumnDefinition<TableRowData, String> colName = new AbstractColumnDefinition<TableRowData, String>() {

            @Override
            public void setCellValue(TableRowData rowValue, String cellValue) {

            }

            @Override
            public String getCellValue(TableRowData rowValue) {
                return rowValue.getName();
            }
        };
        colName.setHeader(0, "Name");

        AbstractColumnDefinition<TableRowData, Integer> colValue = new AbstractColumnDefinition<TableRowData, Integer>() {
            @Override
            public void setCellValue(TableRowData rowValue, Integer cellValue) {
            }

            @Override
            public Integer getCellValue(TableRowData rowValue) {
                return rowValue.getMaxValue();
            }
        };
        colValue.setCellRenderer(new CellRenderer<TableRowData, Integer>() {

            public void renderRowValue(TableRowData rowValue,
                    ColumnDefinition<TableRowData, Integer> columnDef,
                    AbstractCellView<TableRowData> view) {

                AAA aaa = new AAA(rowValue.getMaxValue());
                view.setWidget(aaa);
            }
        });
        colValue.setHeader(0, "Value");

        DefaultTableDefinition<TableRowData> tableDefinition = new DefaultTableDefinition<TableRowData>();

        tableDefinition.addColumnDefinition(colName);
        tableDefinition.addColumnDefinition(colValue);

        tableModel.setRowCount(4);
        PagingScrollTable<TableRowData> table = new PagingScrollTable<TableRowData>(
                tableModel, tableDefinition);

        table.setWidth("400px");
        table.setHeight("300px");

        table.reloadPage();

        return table;

    }

    public static class AAA extends GWTCanvas {
        public AAA(Integer val) {
            setWidth("100px");
            setHeight("20px");

            setBackgroundColor(Color.RED);
        }
    }

}
