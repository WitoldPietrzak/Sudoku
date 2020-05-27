package pl.comp.dao;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class SudokuBoardDaoFactoryTest {

    @Test
    void getFileDao() {
        assertNotNull(SudokuBoardDaoFactory.getFileDao("fileName"));
    }
    @Test
    void getJdbcDao() {
        assertNotNull(SudokuBoardDaoFactory.getJdbcDao("fileName"));
    }
}