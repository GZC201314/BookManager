package org.bsm.service;

import java.util.List;

import org.bsm.pageModel.Book;
import org.bsm.pageModel.BookQuery;
import org.bsm.pageModel.Combobox;
import org.bsm.pageModel.PageDataGrid;
import org.bsm.pageModel.PageUser;
import org.bsm.pageModel.Role;

public interface BookServiceI {

    public Book getBookInfobyIsbn(BookQuery bookQuery);

    public PageDataGrid datagrid(BookQuery bookQuery);

    public void removeBook(BookQuery bookQuery);

    public List<Combobox> getRoleItem();

    /**
     * @param role
     * @return
     */
    public void update(BookQuery bookQuery);

    public String uploadBookIcon(BookQuery bookQuery);
}
