package org.dlt.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dlt.model.Enterprise;
import org.dlt.model.HibernateUtil;
import org.dlt.view.EnterpriseView;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EnterpriseController {
    private static final Logger log = LogManager.getLogger(EnterpriseView.class);

    public static boolean createEnterprise(Enterprise enterprise) {
        boolean success = false;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = session.beginTransaction();
//        Enterprise enterprise = new Enterprise(tinHead, tinNumber, nameEn, nameKh);
//        enterprise.setFullAddress("#168, Street 256, Sangkat Psar Kandal, Khan Daun Penh, Phnom Penh");
            session.persist(enterprise);
            tx.commit();
            session.close();
            success = true;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return success;
    }

    public static List<Enterprise> getEnterprises() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Enterprise> enterprises = session.createQuery("FROM Enterprise ORDER BY nameEn ASC", Enterprise.class).list();
        session.close();
        return enterprises;
    }

    public static void deleteEnterprise(long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Enterprise enterprise = session.get(Enterprise.class, id);
        if (enterprise != null) {
            session.remove(enterprise);
        }

        tx.commit();
        session.close();
    }
}
