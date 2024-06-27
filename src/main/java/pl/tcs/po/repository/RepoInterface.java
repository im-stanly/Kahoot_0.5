package pl.tcs.po.repository;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import pl.tcs.po.exception.NotFoundException;
import java.util.List;

public interface RepoInterface {
    default boolean isElementOfLibrary(JdbcTemplate jdbcTemplate, String nameOfTableInDB, String kind, Object object){
        var elementsInDB = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM "
                        + nameOfTableInDB + " WHERE " + kind + " = ?",
                Integer.class, object);
        return elementsInDB != null;
    }

    default <T> List<T> getRecordByKind(JdbcTemplate jdbcTemplate, String GET_PROPERTIES_SQL,
                                        String tableName, Class<T> T, String kind, Object object){
        List<T> queryResult = jdbcTemplate.query(GET_PROPERTIES_SQL + " WHERE "
                + kind + "=?", BeanPropertyRowMapper.newInstance(T), object);

        if (queryResult == null || queryResult.size() == 0 || queryResult.get(0) == null){
            if (object.getClass().equals(String.class))
                throw new NotFoundException(tableName, (String) object);
            else
                throw new NotFoundException(tableName, (Integer) object);
        }
        return queryResult;
    }
}