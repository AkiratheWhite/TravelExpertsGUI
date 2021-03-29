package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Agent implements Entity{
    /**
     * Parameters corresponding to the columns in the Agents table..
     */
    private int AgentId;
    private String FirstName;
    private String MiddleInitial;
    private String LastName;
    private String BusPhone;
    private String Email;
    private String Position;
    private int AgencyId;

    /**
     * Empty constructor for initializing empty Agent object.
     */
    public Agent() {}

    /**
     * Constructor for Agent object.
     * @param agentId Agent's Agent ID number.
     * @param firstName Agent's first name.
     * @param middleInitial Agent's middle initial.
     * @param lastName Agent's last name.
     * @param busPhone Agent's business phone number.
     * @param email Agent's email address.
     * @param position Agent's positon/title.
     * @param agencyId Agent's Agency ID number.
     */
    public Agent(int agentId, String firstName, String middleInitial, String lastName, String busPhone, String email, String position, int agencyId) {
        AgentId = agentId;
        FirstName = firstName;
        MiddleInitial = middleInitial;
        LastName = lastName;
        BusPhone = busPhone;
        Email = email;
        Position = position;
        AgencyId = agencyId;
    }

    public PreparedStatement CreateUpdateSQL(Connection connection) throws SQLException {

        String sql = "UPDATE agents" +
                " SET AgtFirstName=?, AgtMiddleInitial=?, AgtLastName=?, AgtBusPhone=?, AgtEmail=?, AgtPosition=?, AgencyId=?" +
                " WHERE AgentId=?";
        
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, this.getFirstName());
        statement.setString(2, this.getMiddleInitial());
        statement.setString(3, this.getLastName());
        statement.setString(4, this.getBusPhone());
        statement.setString(5, this.getEmail());
        statement.setString(6, this.getPosition());
        statement.setInt(7, this.getAgencyId());
        statement.setInt(8, this.getAgentId());

        return statement;
    }

    public int getAgentId() {
        return AgentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getMiddleInitial() {
        return MiddleInitial;
    }

    public String getLastName() {
        return LastName;
    }

    public String getBusPhone() {
        return BusPhone;
    }

    public String getEmail() {
        return Email;
    }

    public String getPosition() {
        return Position;
    }

    public int getAgencyId() {
        return AgencyId;
    }
}
