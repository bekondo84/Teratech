/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.utilities;

/**
 *
 * @author DEV_4
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableColumnModelEvent;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.TableColumnModel;

public class FixedRow2Tables extends JFrame {
    private static final long serialVersionUID = 4676303089799270571L;
    Object[][] data;
    Object[] column;
    JTable footerTable, table;

    public FixedRow2Tables() {
        super("Fixed Row Example");

        Object[][] mainData = new Object[][] { { "a", "", "", "", "", "" },
                { "", "b", "", "", "", "" }, { "", "", "c", "", "", "" },
                { "", "", "", "d", "", "" }, { "", "", "", "", "e", "" },
                { "", "", "", "", "", "f" } };
        Object[][] summaryData = { { "fixed1", "", "", "", "", "" },
                { "fixed2", "", "", "", "", "" } };
        column = new Object[] { "A", "B", "C", "D", "E", "F" };

        table = new JTable(mainData, column);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        footerTable = new JTable(summaryData, column);
        footerTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        footerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        footerTable.setTableHeader(null);
        // footerTable.setColumnModel(table.getColumnModel());

        table.getColumnModel().addColumnModelListener(
                new TableColumnModelListener() {

                    @Override
                    public void columnSelectionChanged(ListSelectionEvent e) {
                    }

                    @Override
                    public void columnRemoved(TableColumnModelEvent e) {
                    }

                    @Override
                    public void columnMoved(TableColumnModelEvent e) {
                    }

                    @Override
                    public void columnMarginChanged(ChangeEvent e) {
                        final TableColumnModel tableColumnModel = table
                                .getColumnModel();
                        TableColumnModel footerColumnModel = footerTable
                                .getColumnModel();
                        for (int i = 0; i < tableColumnModel.getColumnCount(); i++) {
                            int w = tableColumnModel.getColumn(i).getWidth();
                            footerColumnModel.getColumn(i).setMinWidth(w);
                            footerColumnModel.getColumn(i).setMaxWidth(w);
                            // footerColumnModel.getColumn(i).setPreferredWidth(w);
                        }
                        footerTable.doLayout();
                        footerTable.repaint();
                        repaint();
                    }

                    @Override
                    public void columnAdded(TableColumnModelEvent e) {
                    }
                });

        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(400, 100));
        getContentPane().add(scroll, BorderLayout.CENTER);
        getContentPane().add(footerTable, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FixedRow2Tables frame = new FixedRow2Tables();
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                });
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}