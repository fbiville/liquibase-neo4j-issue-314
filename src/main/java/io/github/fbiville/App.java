package io.github.fbiville;

import liquibase.Liquibase;
import liquibase.database.jvm.JdbcConnection;
import liquibase.resource.ClassLoaderResourceAccessor;

import java.sql.DriverManager;

public class App {
    public static void main(String[] args) throws Exception {
        Class.forName("liquibase.ext.neo4j.database.jdbc.Neo4jDriver");
        try (JdbcConnection connection = new JdbcConnection(
                DriverManager.getConnection(System.getenv("NEO4J_JDBC_URI"),
                        System.getenv("NEO4J_USER"),
                        System.getenv("NEO4J_PASSWORD")))) {

            Liquibase liquibase = new Liquibase("/change-log.xml",
                    new ClassLoaderResourceAccessor(),
                    connection);
            liquibase.update();
        }
    }
}
