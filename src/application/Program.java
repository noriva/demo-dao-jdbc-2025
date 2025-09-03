package application;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Departament;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {
		Departament obj = new Departament(1, "Books");
		Seller seller = new Seller(1, "Bob", "bog@gmail", new Date(), 3.000, obj);  
		SellerDao sellerDao = DaoFactory.createSellerDao();
		System.out.println(seller);
	}

}
