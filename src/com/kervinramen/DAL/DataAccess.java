package com.kervinramen.DAL;

import javax.jdo.PersistenceManager;
import com.kervinramen.entities.FacebookUser;

public class DataAccess {


	public static void saveUser(FacebookUser user) {
		PersistenceManager pm = PMF.get().getPersistenceManager();

		try {
			pm.makePersistent(user);
		} finally {
			pm.close();
		}

	}
}
