package data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQL {
    private final String url;
    private final String username;
    private final String password;
    private Connection connection;

    /**
     * Constructor for new database object.
     * @param url Database connection string.
     * @param username Username for access member.
     * @param password Password for access member.
     */
    public MySQL (String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    /**
     * Opens the connection to the database. Private because connection will be opened and closed by class methods.
     */
    private boolean OpenConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
            return true;
        } catch (Exception err) {
            System.out.println("Failed to connect to database.");
            System.out.println(err.getMessage());
            return false;
        }
    }

    /**
     * Closes the connection to the database. Private for same reason as above.
     */
    private boolean CloseConnection() {
        try {
            connection.close();
            return true;
        } catch (Exception err) {
            System.out.println("Failed to close database connection.");
            System.out.println(err.getMessage());
            return false;
        }
    }

    /**
     * Obtains an arraylist of all agent information from the agents table.
     * @return ArrayList of Agent objects.
     */
    public List<Agent> GetAgents() {
        if (!this.OpenConnection()) {
            return null;
        }

        List<Agent> Agents = new ArrayList<>();
        String sql = "SELECT * FROM agents";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                Agent currentAgent = new Agent(
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getInt(8)
                );

                Agents.add(currentAgent);
            }

        } catch (SQLException err) {
            System.out.println("Error encountered during query.");
            System.out.println(err.getMessage());
        }
        finally {
            this.CloseConnection();
        }

        return Agents;
    }

    /**
     * Obtains the information of a specific agent based on the AgentId argument passed.
     * @param AgentID AgentId of the specific agent to get information for.
     * @return Agent object.
     */
    public Agent GetAgent(int AgentID) {
        if (!this.OpenConnection()) {
            return null;
        }

        Agent currentAgent = new Agent();
        String sql = "SELECT * FROM agents WHERE AgentId = ?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, AgentID);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                currentAgent.setAgentId(result.getInt(1));
                currentAgent.setFirstName(result.getString(2));
                currentAgent.setMiddleInitial(result.getString(3));
                currentAgent.setLastName(result.getString(4));
                currentAgent.setBusPhone(result.getString(5));
                currentAgent.setEmail(result.getString(6));
                currentAgent.setPosition(result.getString(7));
                currentAgent.setAgencyId(result.getInt(8));
            }

        } catch (SQLException err) {
            System.out.println("Error encountered during query.");
            System.out.println(err.getMessage());
        } finally {
            this.CloseConnection();
        }

        return currentAgent;
    }

    /**
     * Obtains an arraylist of AgentIds. Used to populate combobox in client-side application.
     * @return Arraylist of AgentIds (integers)
     */
    public List<Integer> GetAgentsIds() {
        if (!this.OpenConnection()) {
            return null;
        }

        List<Integer> AgentIDs = new ArrayList<>();
        String sql = "SELECT AgentId FROM agents ORDER BY AgentId ASC";

        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()) {
                AgentIDs.add(result.getInt(1));
            }

        } catch (SQLException err) {
            System.out.println("Error encountered during query.");
            System.out.println(err.getMessage());
        } finally {
            this.CloseConnection();
        }

        return AgentIDs;
    }

    /**
     * Updates the information of an agent using an Agent object.
     * @param currentAgent Agent object generated by client-side form, containing new information and AgentId.
     */
    public void UpdateAgent (Agent currentAgent) {
        if (!this.OpenConnection()) {
            return;
        }

        String sql = "UPDATE agents" +
                " SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=?" +
                " WHERE AgentId=?";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, currentAgent.getFirstName());
            statement.setString(2, currentAgent.getMiddleInitial());
            statement.setString(3, currentAgent.getLastName());
            statement.setString(4, currentAgent.getBusPhone());
            statement.setString(5, currentAgent.getEmail());
            statement.setString(6, currentAgent.getPosition());
            statement.setInt(7, currentAgent.getAgencyId());
            statement.setInt(8, currentAgent.getAgentId());

            statement.executeUpdate();

        } catch (SQLException err) {
            System.out.println("Error encountered during update operation.");
            System.out.println(err.getMessage());
        }
        finally {
            this.CloseConnection();
        }
    }
}

