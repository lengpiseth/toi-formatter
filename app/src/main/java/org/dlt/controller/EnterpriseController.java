package org.dlt.controller;

import org.dlt.model.Enterprise;
import org.dlt.model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EnterpriseController {
    public static void createEnterprise(String tinHead, String tinNumber, String nameEn, String nameKh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Enterprise enterprise = new Enterprise(tinHead, tinNumber, nameEn, nameKh);
        session.save(enterprise);
        tx.commit();
        session.close();
    }

    public static List<Enterprise> getEnterprises() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Enterprise> enterprises = session.createQuery("FROM enterprise", Enterprise.class).list();
        session.close();
        return enterprises;
    }

    public static void deleteEnterprise(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Enterprise enterprise = session.get(Enterprise.class, id);
        if (enterprise != null) {
            session.delete(enterprise);
        }
        tx.commit();
        session.close();
    }
}
