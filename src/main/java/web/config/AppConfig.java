package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "web")
@EnableTransactionManagement
public class AppConfig {

    // Настройка DataSource
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/bdforkata");
        dataSource.setUsername("root");
        dataSource.setPassword("1991_Dvs");
        return dataSource;
    }


    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("web.model");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        // Дополнительные свойства Hibernate
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "update"); // Автоматическое создание/обновление таблиц
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect"); // Диалект для MySQL
        properties.setProperty("hibernate.show_sql", "true"); // Показывать SQL-запросы в консоли
        em.setJpaProperties(properties);

        return em;
    }

    // Настройка TransactionManager
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf); // Устанавливаем EntityManagerFactory
        return transactionManager;
    }

    // Настройка Thymeleaf TemplateResolver
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/pages/"); // Папка с HTML-файлами
        templateResolver.setSuffix(".html"); // Расширение файлов
        templateResolver.setTemplateMode("HTML5"); // Режим шаблона
        templateResolver.setCharacterEncoding("UTF-8"); // Кодировка
        return templateResolver;
    }

    // Настройка Thymeleaf TemplateEngine
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver()); // Устанавливаем резолвер
        return templateEngine;
    }

    // Настройка Thymeleaf ViewResolver
    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine()); // Устанавливаем движок
        viewResolver.setCharacterEncoding("UTF-8"); // Кодировка
        return viewResolver;
    }
}