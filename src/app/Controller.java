package app;

import data.Agent;
import data.MySQL;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class Controller {

    String url = "jdbc:mysql://localhost:3306/travelexperts";
    String username = "root";
    String password = "";

    //Instantiates database object.
    MySQL database = new MySQL(url, username, password);

    @FXML
    GridPane agentInfo;
    @FXML
    ComboBox<Integer> comboAgentID;
    @FXML
    TextField txtAgentId;
    @FXML
    TextField txtFirstName;
    @FXML
    TextField txtMiddleInitial;
    @FXML
    TextField txtLastName;
    @FXML
    TextField txtBusPhone;
    @FXML
    TextField txtEmail;
    @FXML
    TextField txtPosition;
    @FXML
    TextField txtAgencyId;
    @FXML
    Button btnEdit;
    @FXML
    Button btnSave;

    /**
     * Populates the Combobox with AgentIDs.
     */
    public void setIDs() {
        try {
            var AgentIds = FXCollections.observableArrayList(database.GetAgentsIds());
            comboAgentID.getItems().addAll(AgentIds);
        } catch (Exception err) {
            System.out.println("Error populating Agent IDs.");
            System.out.println(err.getMessage());
        }
    }

    /**
     * Retrieves all information of an agent after an AgentId has been selected on the form.
     */
    public void fetchAgentInfo() {
        int currentID = comboAgentID.getValue();

        Agent currentAgent = database.GetAgent(currentID);

        txtAgentId.setText(Integer.toString(currentAgent.getAgentId()));
        txtFirstName.setText(currentAgent.getFirstName());
        txtMiddleInitial.setText(currentAgent.getMiddleInitial());
        txtLastName.setText(currentAgent.getLastName());
        txtBusPhone.setText(currentAgent.getBusPhone());
        txtEmail.setText(currentAgent.getEmail());
        txtPosition.setText(currentAgent.getPosition());
        txtAgencyId.setText(Integer.toString(currentAgent.getAgencyId()));
    }

    /**
     * Enables editing of an agent's information on the form. Disables edit button.
     */
    public void enableEdit() {
        btnEdit.setDisable(true);
        btnSave.setDisable(false);

        for (Node node: agentInfo.getChildren()) {
            if (node instanceof TextField && node != txtAgentId) {
                ((TextField) node).setEditable(true);
            }
        }
    }

    /**
     * Saves changes made to an agent's information. Disables save button and re-enables edit button.
     */
    public void saveEdit() {
        try {
            Agent currentAgent = new Agent(
                    Integer.parseInt(txtAgentId.getText()),
                    txtFirstName.getText(),
                    txtMiddleInitial.getText(),
                    txtLastName.getText(),
                    txtBusPhone.getText(),
                    txtEmail.getText(),
                    txtPosition.getText(),
                    Integer.parseInt(txtAgencyId.getText())
            );

            database.UpdateAgent(currentAgent);
        } catch (Exception err) {
            System.out.println("Error while attempting to save data.");
            System.out.println(err.getLocalizedMessage());
        }

        btnSave.setDisable(true);
        btnEdit.setDisable(false);

        for (Node node: agentInfo.getChildren()) {
            if (node instanceof TextField) {
                ((TextField) node).setEditable(false);
            }
        }
    }
}
