package es.procoders.spanisholivetechnology.dao;

import java.sql.SQLException;

/**
 * Created by joser on 12/11/2017.
 */

public class UsuarioDAO extends DBConnection implements IUsuarioDAO {

    //Propiedades
    private String table = "jperez_app.usuarios";

    //Constructor
    public UsuarioDAO() {
        super();
    }

    //Métodos
    @Override
    public Boolean crearUsuario (String email, String pass){
        Boolean retVal = false;
        try {
            conectar();
            consultaSQL = "INSERT INTO "+table+" SET email='"+email+"', pass='"+pass+"'";
            retVal = conexionSQL.createStatement().execute(consultaSQL);
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    @Override
    public Boolean crearUsuario(String email, String pass, String datos) {
        Boolean retVal = false;
        try {
            conectar();
            consultaSQL = "INSERT INTO "+table+" SET email='"+email+"', pass='"+pass+"', data='"+datos+"'";
            retVal = conexionSQL.createStatement().execute(consultaSQL);
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retVal;
    }

    @Override
    public Boolean actualizarPass(String email, String pass) {
        Boolean retVal = false;
        try {
            conectar();
            consultaSQL = "update "+table+" set pass='"+pass+"' WHERE email = '"+email+"'";
            if (conexionSQL.createStatement().executeUpdate(consultaSQL)==1) {
                retVal = true;
            }
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;
    }

    @Override
    public Boolean comprobarPass(String email, String pass) {
        Boolean retVal = false;
        try {
            conectar();
            consultaSQL = "SELECT pass FROM "+table+" WHERE email = '"+email+"'";
            stmt = conexionSQL.createStatement();
            rs = stmt.executeQuery(consultaSQL);
            rs.next();
            retVal = pass.equals(rs.getString("pass"));
            desconectar();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retVal;
    }
}
