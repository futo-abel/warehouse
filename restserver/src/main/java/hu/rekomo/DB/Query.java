package hu.rekomo.DB;

public class Query {
    private StringBuilder query;

    /**
     *
     * @param table
     * @return
     */
    public Query delete(String table){
        query = new StringBuilder();
        query.append("DELETE FROM ");
        query.append(table);
        return this;
    }

    /**
     * Adds the WHERE condition to the SQL query
     * @param requirement
     * @return
     */
    public Query where(String requirement){
    query.append(" WHERE ");
        query.append(requirement);
        return this;
    }

   /**
     *
     * @param table
     * @return
     */
    public Query from(String table){
        query.append(" FROM ");
        query.append(table);
        return this;
    }
        /**
     *
     * @param table
     * @return
     */
    public Query update(String table){
        query = new StringBuilder();
        query.append("UPDATE ");
        query.append(table);
        query.append(" SET ");
        return this;
    }

    /**
     * Adds columns
     * @param columns
     * @return
     */
    public Query set(String[] columns){
        int count = columns.length;
        if(count == 0)
            throw new IllegalArgumentException("Invalid argument count");

        for(String column : columns){
           query.append(column);
           query.append(" = ");
           query.append("?");
           query.append(",");
        }
        query.deleteCharAt(query.lastIndexOf(","));
        return this;
    }
    /**
     *
     * @param table
     * @return
     */
    public Query insert(String table){
        query = new StringBuilder();
        query.append("INSERT INTO ");
        query.append(table);
        return this;
    }

    /**
     *
     * @param params
     * @return
     */
    public Query values(Object[] params){
        query.append(" VALUES(");

        int count = params.length;

        if(count == 0)
            throw new IllegalArgumentException("Invalid parameter count");

        for (int i = 0; i<count; i++) {
            query.append("?,");
        }
        //removes the last comma
        query.deleteCharAt(query.lastIndexOf(","));
        query.append(");");
        return this;
    }
        /**
     *
     * @param columns
     * @return
     */
    public Query select(Object[] columns){
        query = new StringBuilder();
        query.append("SELECT ");
        if(columns != null){
            for(Object column : columns){
                query.append(column);
                query.append(",");
            }
            //removes the last question mark
            query.deleteCharAt(query.lastIndexOf(","));
        }
        else
            query.append("*");

        return this;
    }
    /**
     * Returns the generated SQL query
     * @return query
     */
    public String getQuery(){
        return query.toString();
    }
}
