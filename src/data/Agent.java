package data;

public class Agent {
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

    public int getAgentId() {
        return AgentId;
    }

    public void setAgentId(int agentId) {
        AgentId = agentId;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleInitial() {
        return MiddleInitial;
    }

    public void setMiddleInitial(String middleInitial) {
        MiddleInitial = middleInitial;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBusPhone() {
        return BusPhone;
    }

    public void setBusPhone(String busPhone) {
        BusPhone = busPhone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public int getAgencyId() {
        return AgencyId;
    }

    public void setAgencyId(int agencyId) {
        AgencyId = agencyId;
    }
}
