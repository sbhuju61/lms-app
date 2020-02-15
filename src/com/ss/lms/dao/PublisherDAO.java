package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ss.lms.entity.Publisher;

public class PublisherDAO extends BaseDAO<Publisher> {

	public PublisherDAO(Connection conn) {
		super(conn);
	}

	public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)", new Object[] {
				publisher.getPublisherName(), publisher.getPublisherAddress(), publisher.getPublisherPhone() });

	}

	public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("update tbl_publisher set publisherName=?, publisherAddress=?, publisherPhone=?"
				+ " where publisherId = ?",
				new Object[] { publisher.getPublisherName(), publisher.getPublisherAddress(),
						publisher.getPublisherPhone(), publisher.getPublisherId() });
	}

	public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		save("delete from tbl_publisher where publisherId = ?", new Object[] { publisher.getPublisherId() });

	}

	public List<Publisher> readPublishers() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return read("select * from tbl_publisher", null);
	}

	@Override
	List extractData(ResultSet rs) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Publisher> publishers = new ArrayList<>();
		BookDAO bdao = new BookDAO(conn);
		// genre doa, branch dao
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			p.setBooks(bdao.readFirstLevel("select * from tbl_book where pubId = ?",
					new Object[]{p.getPublisherId()}));
			publishers.add(p);
		}
		return publishers;
	}

	@Override
	List extractDataFirstLevel(ResultSet rs) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		List<Publisher> publishers = new ArrayList<>();

		// genre doa, branch dao
		while (rs.next()) {
			Publisher p = new Publisher();
			p.setPublisherId(rs.getInt("publisherId"));
			p.setPublisherName(rs.getString("publisherName"));
			p.setPublisherAddress(rs.getString("publisherAddress"));
			p.setPublisherPhone(rs.getString("publisherPhone"));
			publishers.add(p);
		}
		return publishers;
	}

}
