package ru.javawebinar.topjava;

public class Profiles {
    public static final String
            JDBC = "jdbc",
            JPA = "jpa",
            DATAJPA = "datajpa";

    public static final String REPOSITORY_IMPLEMENTATION = DATAJPA;

    public static final String
            POSTGRES_DB = "postgres",
            HSQL_DB = "hsqldb";

    //  Get DB profile depending of DB driver in classpath
    public static String getActiveDbProfile() {
        try {
            Class.forName("org.postgresql.Driver");
            return POSTGRES_DB;
        } catch (ClassNotFoundException ex) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                return Profiles.HSQL_DB;
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Could not find DB driver");
            }
        }
    }

    public static String getActiveRepositoryProfile() {
        try {
            Class.forName("ru.javawebinar.topjava.repository.datajpa.DataJpaMealRepositoryImpl");
            return Profiles.DATAJPA;
        } catch (ClassNotFoundException e) {
            try {
                Class.forName("ru.javawebinar.topjava.repository.jpa.JpaMealRepositoryImpl");
                return Profiles.JPA;
            } catch (ClassNotFoundException e1) {
                try {
                    Class.forName("ru.javawebinar.topjava.repository.jdbc");
                    return Profiles.JDBC;
                } catch (ClassNotFoundException e2) {
                    throw new IllegalArgumentException("Could not find repository implementation");
                }
            }
        }
    }
}
