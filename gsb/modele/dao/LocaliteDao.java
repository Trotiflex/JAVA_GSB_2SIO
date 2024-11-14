package gsb.modele.dao;

import gsb.modele.Localite;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocaliteDao {

    public static Localite rechercher(String codeLocalite) {
        Localite uneLocalite = null;
        ResultSet reqSelection = null;

        try {
            reqSelection = ConnexionMySql.execReqSelection("SELECT * FROM LOCALITE WHERE CODEPOSTAL='" + codeLocalite + "'");
            if (reqSelection.next()) {
                uneLocalite = new Localite(reqSelection.getString(1), reqSelection.getString(2));
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la recherche de la localité avec le code postal : " + codeLocalite);
            e.printStackTrace();
        } finally {
            if (reqSelection != null) {
                try {
                    reqSelection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return uneLocalite;
    }
}
