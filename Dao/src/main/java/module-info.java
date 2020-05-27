module Dao {
    requires ModelProject;
    requires slf4j.api;
    requires java.sql;
    opens pl.comp.dao;
    exports pl.comp.dao;
    opens pl.comp.dao.exceptions;
    exports pl.comp.dao.exceptions;
}