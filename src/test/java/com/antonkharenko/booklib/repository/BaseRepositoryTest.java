package com.antonkharenko.booklib.repository;

import com.mongodb.Mongo;

import de.flapdoodle.embed.mongo.MongodProcess;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Base class for mongodb repositories unit tests
 */
public abstract class BaseRepositoryTest {

    private static final String SPRING_CONFIG_LOCATION = "classpath*:spring-integration-test/booklib-it-*.xml";

    protected static ClassPathXmlApplicationContext applicationContext;

    private static MongodProcess mongoProcess;
    private static Mongo mongo;

    @BeforeClass
    public static void initDB() {
        applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIG_LOCATION);
        mongoProcess = (MongodProcess) applicationContext.getBean("mongoProcess");
        mongo = (Mongo) applicationContext.getBean("mongo");
    }

    @AfterClass
    public static void shutDownDB() {
        mongo.close();
        mongoProcess.stop();
        applicationContext.close();
    }
}
