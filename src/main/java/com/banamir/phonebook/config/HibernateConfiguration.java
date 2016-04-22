package com.banamir.phonebook.config;

import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.manager.UserManager;
import com.banamir.phonebook.storage.hibernate.PhonebookEntryDAO;
import com.banamir.phonebook.storage.hibernate.UserDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@Configuration
@ConditionalOnProperty(prefix = "storage", name = "type", value = "db")
@EnableTransactionManagement
public class HibernateConfiguration implements DataStorageConfiguration {

    @Value("{storage.db.url}")
    private String dataSourceUrl;

    @Value("{storage.db.username}")
    private String dataSourceUsername;

    @Value("{storage.db.Password}")
    private String dataSourcePassword;


    @Bean(name = "dataSource", destroyMethod = "close")
    DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory sessionFactory(){

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setAnnotatedPackages("com.banamir.phonebook.model");

        return factoryBean.getObject();
    }

    @Bean(name="transactionalManager")
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory());
        return  transactionManager;
    }


    @Bean
    @Override
    public PhonebookManager phonebookManager() {

        PhonebookEntryDAO phonebookManager = new PhonebookEntryDAO();

        phonebookManager.setSessionFactory(sessionFactory());

        return phonebookManager;
    }

    @Bean
    @Override
    public UserManager userManager() {

        UserDAO userManager = new UserDAO();

        userManager.setSessionFactory(sessionFactory());

        return userManager;
    }


}
