module Dao {
    requires ModelProject;
    opens pl.comp.dao;
    exports pl.comp.dao;
    opens pl.comp.dao.exceptions;
    exports pl.comp.dao.exceptions;
}