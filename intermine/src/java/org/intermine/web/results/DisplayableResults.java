package org.flymine.web.results;

/*
 * Copyright (C) 2002-2003 FlyMine
 *
 * This code may be freely distributed and modified under the
 * terms of the GNU Lesser General Public Licence.  This should
 * be distributed with the code.  See the LICENSE file for more
 * information or http://www.gnu.org/copyleft/lesser.html.
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.flymine.objectstore.ObjectStoreException;
import org.flymine.objectstore.query.Results;
import org.flymine.objectstore.query.ResultsInfo;

/**
 * Displayable section of a Results object, containing various
 * bits of configuration information
 *
 * @author Andrew Varley
 */
public class DisplayableResults
{
    protected List columns = new ArrayList();
    protected int start = 0;
    protected int end = 9;

    protected Results results;

    /**
     * Constructor
     *
     * @param results the results object to configure
     */
    public DisplayableResults(Results results) {
        this.results = results;

        // Add some blank column configurations
        Iterator columnIter = results.getColumnAliases().iterator();
        while (columnIter.hasNext()) {
            String alias = (String) columnIter.next();
            Column column = new Column();
            column.setAlias(alias);
            columns.add(column);
        }
    }

    /**
     * Get the list of column configurations
     *
     * @return the list of columns
     */
    public List getColumns() {
        return this.columns;
    }

    /**
     * Set the list of column configurations
     *
     * @param columns the list of column configurations
     */
    public void setColumns(List columns) {
        this.columns = columns;
    }

    /**
     * Get the start row of this table
     *
     * @return the start row
     */
    public int getStart() {
        return this.start;
    }

    /**
     * Set the start row of this table
     *
     * @param start the start row
     */
    public void setStart(int start) {
        this.start = start;
    }

    /**
     * Get the end row of this table
     *
     * @return the end row
     * @throws ObjectStoreException if an error occurs in the underlying ObjectStore
     */
    public int getEnd() throws ObjectStoreException {
        int size = getSize();
        if (this.end >= size) {
            this.end = size - 1;
        }
        return this.end;
    }

    /**
     * Set the end row
     *
     * @param end the end row
     */
    public void setEnd(int end) {
        this.end = end;
    }

    /**
     * Get the underlying results object
     *
     * @return the underlying results object
     */
    public Results getResults() {
        return results;
    }

    /**
     * Get the size of the underlying results object
     * NOTE: this may be approximate
     *
     * @return the size of the underlying results object
     * @throws ObjectStoreException if an error occurs in the underlying ObjectStore
     */
    public int getSize() throws ObjectStoreException {
        return results.getInfo().getRows();
    }

    /**
     * Gets whether or not the size is an estimate
     *
     * @return true if size is an estimate
     * @throws ObjectStoreException if an error occurs in the underlying ObjectStore
     */
    public boolean isSizeEstimate() throws ObjectStoreException {
        return !(results.getInfo().getStatus() == ResultsInfo.SIZE);
    }

}
