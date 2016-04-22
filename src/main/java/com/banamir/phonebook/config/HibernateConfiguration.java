package com.banamir.phonebook.config;

import com.banamir.phonebook.manager.PhonebookManager;
import com.banamir.phonebook.manager.UserManager;
import com.banamir.phonebook.storage.hibernate.PhonebookEntryDAO;
import com.banamir.phonebook.storage.hibernate.UserDAO;
import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.hibernate.dialect.Dialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@ConditionalOnProperty(prefix = "storage", name = "type", havingValue = "db")
@EnableTransactionManagement
public class HibernateConfiguration implements DataStorageConfiguration {

    @Value("${storage.db.url}")
    private String dataSourceUrl;

    @Value("${storage.db.username}")
    private String dataSourceUsername;

    @Value("${storage.db.password}")
    private String dataSourcePassword;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DDLAuto;

    @Value("${entitymanager.packagesToScan}")
    private String hibernatePackagesToScan;


    @Bean(name = "dataSource", destroyMethod = "close")
    DataSource dataSource(){

        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(dataSourceUrl);
        dataSource.setUsername(dataSourceUsername);
        dataSource.setPassword(dataSourcePassword);

        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory(){

        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        factoryBean.setDataSource(dataSource());
        factoryBean.setAnnotatedPackages(hibernatePackagesToScan);
        Properties hibernateProperties = new Properties();
        hibernateProperties.put("hibernate.dialect", hibernateDialect);
        hibernateProperties.put("hibernate.show_sql", hibernateShowSQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", hibernateHBM2DDLAuto);
        factoryBean.setHibernateProperties(hibernateProperties);

        return factoryBean;
    }

    @Bean(name="transactionalManager")
    public HibernateTransactionManager transactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return  transactionManager;
    }


    @Bean
    @Override
    public PhonebookManager phonebookManager() {

        PhonebookEntryDAO phonebookManager = new PhonebookEntryDAO();

        phonebookManager.setSessionFactory(sessionFactory().getObject());

        return phonebookManager;
    }

    @Bean
    @Override
    public UserManager userManager() {

        UserDAO userManager = new UserDAO();

        userManager.setSessionFactory(sessionFactory().getObject());

        return userManager;
    }


}
