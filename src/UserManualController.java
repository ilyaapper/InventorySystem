import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManualController implements ActionListener {
    private UserManualView view;

    public UserManualController() {
        this.view = new UserManualView(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.getPreviousButton()) {
            view.unflipPage();
        }

        else if (e.getSource() == view.getNextButton()) {
            view.flipPage();
        }

    }
}
