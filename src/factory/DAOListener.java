package factory;

public class DAOListener {

    private static DAOFactory daoFactory;

    private DAOListener() { }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
        }
        return daoFactory;
    }
}
